#include <iostream>
#include <vector>
#include <chrono>
#include <thread>
#include <atomic>
#include <mutex>
#include <queue>
#include <condition_variable>
#include <random>
#include "graph.h"

struct Message {
    int hops;
    double travel_time;
};

struct ThreadStatistics {
    int messages_received;
    int messages_forwarded;
    int total_hops;
    double total_travel_time;
    std::mutex total_travel_time_mutex;
};

class MessageQueue {
public:
    void send(const Message& msg) {
        std::unique_lock<std::mutex> lock(mutex_);
        queue_.push(msg);
        lock.unlock();
        cond_var_.notify_one();
    }

    bool try_receive(Message& msg) {
        std::unique_lock<std::mutex> lock(mutex_);
        if (!queue_.empty()) {
            msg = queue_.front();
            queue_.pop();
            return true;
        }
        return false;
    }

private:
    std::queue<Message> queue_;
    std::mutex mutex_;
    std::condition_variable cond_var_;
};

double rand_range(double min, double max) {
    static std::random_device rd;
    static std::mt19937 gen(rd());
    std::uniform_real_distribution<double> dist(min, max);
    return dist(gen);
}

void workingThread(ThreadStatistics& stats, MessageQueue& mq, std::atomic<bool>& terminate, const Graph& graph, int nodeId, std::vector<MessageQueue>& all_queues) {
    const auto& neighbors = graph.getNeighbors(nodeId);

    while (!terminate) {
        Message received_message;
        if (mq.try_receive(received_message)) {
            
            stats.messages_received++;
            received_message.hops++;
            received_message.travel_time += rand_range(0.01, 0.1);

            
            {
                std::unique_lock<std::mutex> lock(stats.total_travel_time_mutex);
                stats.total_travel_time += received_message.travel_time;
            }

          //Forward message if necessary 
            if (received_message.hops < 20) {
                std::this_thread::sleep_for(std::chrono::duration<double, std::milli>(rand_range(100, 500)));

                // Select a random neighbor
                int random_neighbor_index = static_cast<int>(rand_range(0, neighbors.size() - 1));
                auto it = neighbors.begin();
                std::advance(it, random_neighbor_index);
                int target_neighbor = *it;

                all_queues[target_neighbor].send(received_message);
                stats.messages_forwarded++;
            }

            stats.total_hops += received_message.hops;
        }
        else {
            std::this_thread::sleep_for(std::chrono::milliseconds(10));
        }
    }
}
int main() {
    Graph graph("a28.dat"); 

    // Get the number of nodes from the graph
    const int num_threads = graph.getNodes().size();


    //const int num_threads = 10;
    const int num_messages = 100;

    std::vector<ThreadStatistics> all_stats(num_threads);
    std::vector<MessageQueue> all_queues(num_threads);
    std::vector<std::thread> all_threads;
    std::atomic<bool> terminate(false);

    for (int i = 0; i < num_threads; i++) {
        all_threads.emplace_back(workingThread, std::ref(all_stats[i]), std::ref(all_queues[i]), std::ref(terminate), std::cref(graph), graph.getNodes()[i], std::ref(all_queues));
    }

    for (int i = 0; i < num_messages; i++) {
        Message initial_message{ 0, 0 };
        all_queues[i % num_threads].send(initial_message);
    }

    std::this_thread::sleep_for(std::chrono::seconds(10));

    terminate = true;
    for (auto& thread : all_threads) {
        thread.join();
    }

  
    for (int i = 0; i < num_threads; i++) {
        std::cout << "Thread " << i << " statistics:\n";
        std::cout << "  Messages received: " << all_stats[i].messages_received << '\n';
        std::cout << "  Messages forwarded: " << all_stats[i].messages_forwarded << '\n';
        std::cout << "  Total hops: " << all_stats[i].total_hops << '\n';
        std::cout << "  Total travel time: " << all_stats[i].total_travel_time << '\n';
    }

    return 0;
}
