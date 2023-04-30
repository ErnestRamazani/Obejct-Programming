#include <iostream>
#include <vector>
#include <chrono>
#include <thread>
#include <atomic>
#include <mutex>
#include <queue>
#include <condition_variable>
#include <random>
#include<functional>
#include <future>
#include "graph.h"
#include <vector>



// Struct for messages that will be sent between nodes
struct Message {
    int hops;                       // Number of times the message has been forwarded
    double travel_time;             // Time it takes for the message to travel between nodes
};

// Struct for statistics for each thread in the simulation
struct ThreadStatistics {
    int messages_received;          // Number of messages received by the thread
    int messages_forwarded;         // Number of messages forwarded by the thread
    int total_hops;                 // Total number of hops for all messages received by the thread
    double total_travel_time;       // Total travel time for all messages received by the thread
    std::mutex total_travel_time_mtx;   // Mutex to protect access to total_travel_time variable

    // Default constructor to initialize all members to 0
    ThreadStatistics()
        : messages_received(0),
        messages_forwarded(0),
        total_hops(0),
        total_travel_time(0) {}
};

// Class for a message queue used for communication between nodes
class MessageQueue {
public:
    // Function to send a message to the queue
    void send(const Message& msg) {
        std::unique_lock<std::mutex> lock(mutex_);
        queue_.push(msg);
        lock.unlock();
        cond_var_.notify_one();
    }

    // Function to try to receive a message from the queue, returns false if queue is empty
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
    std::queue<Message> queue_;             // Queue container for messages
    std::mutex mutex_;                      // Mutex to protect access to queue
    std::condition_variable cond_var_;      // Condition variable to notify threads waiting on the queue
};

// Function to generate a random double in a given range
double rand_range(double min, double max) {
    static std::random_device rd;
    static std::mt19937 gen(rd());
    std::uniform_real_distribution<double> dist(min, max);
    return dist(gen);
}
   
//The workingThread function processes messages received by a node, updates the thread statistics, and sends the message to the next node according to a routing algorithm (either ant_colony or random).

void workingThread(ThreadStatistics& thread_stats, MessageQueue& message_queue, std::atomic<bool>& terminate, const Graph& graph, int node_id, std::vector<MessageQueue>& message_queues, int num_nodes, const std::string& routing_algorithm) {
    while (!terminate) {
        Message msg;
        if (message_queue.try_receive(msg)) {  // Check if there is a message to process
            thread_stats.messages_received++;  // Increment the number of messages received by this thread

            if (msg.hops == num_nodes) {  // Check if the message has reached the maximum number of hops
                thread_stats.total_travel_time_mtx.lock();  // Acquire lock to update thread statistics
                thread_stats.total_travel_time += msg.travel_time;  // Add travel time of this message to thread statistics
                thread_stats.total_travel_time_mtx.unlock();  // Release lock
                thread_stats.total_hops += msg.hops;  // Add number of hops of this message to thread statistics
            }
            else {  // If the message has not reached the maximum number of hops
                int target_neighbor;
                if (routing_algorithm == "ant_colony") {  // Check if using ant colony algorithm
                    target_neighbor = graph.chooseNextNode(node_id, msg.hops);  // Choose next node using ant colony algorithm
                }
                else {  // If not using ant colony algorithm
                    target_neighbor = rand_range(0, num_nodes - 1);  // Choose next node randomly
                }

                msg.travel_time += graph.getEdgeWeight(node_id, target_neighbor);  // Add travel time of edge to travel time of message
                msg.hops++;  // Increment number of hops of message

                message_queues[target_neighbor].send(msg);  // Send the message to the target neighbor
                thread_stats.messages_forwarded++;  // Increment the number of messages forwarded by this thread
            }
        }
    }
}



// Definition of a class representing an edge in a graph
class Edge {
public:
    Edge(int src, int dest, double weight)
        : src(src), dest(dest), weight(weight), pheromone(1.0) {}

    int getSrc() const { return src; }         // Method to get the source node ID
    int getDest() const { return dest; }       // Method to get the destination node ID
    double getWeight() const { return weight; }  // Method to get the weight of the edge
    double getPheromone() const { return pheromone; }  // Method to get the pheromone level of the edge

    void increasePheromone(double delta) { pheromone += delta; } // Method to increase the pheromone level by a given amount
    void updatePheromone(double evaporation_rate); // Method to update the pheromone level based on a given evaporation rate

private:
    int src;          // ID of the source node
    int dest;         // ID of the destination node
    double weight;    // Weight of the edge
    double pheromone; // Pheromone level of the edge
};

// Method to update the pheromone level based on a given evaporation rate
void Edge::updatePheromone(double evaporation_rate) {
    pheromone *= (1 - evaporation_rate); // Multiply the current pheromone level by (1 - evaporation rate)
}




// This function runs the simulation for a given duration using the provided graph, routing algorithm, thread statistics,
// and message queues.

void runSimulation(Graph& graph, int duration, const std::string& routing_algorithm,
    std::vector<ThreadStatistics>& thread_statistics,
    std::vector<MessageQueue>& message_queues) {

    // Get the number of nodes in the graph
    int num_nodes = static_cast<int>(graph.getNodes().size());

    // Initialize variables for the simulation
    int num_iterations = 0;
    std::vector<std::thread> threads;
    std::atomic<bool> terminate(false);

    // Start a thread for each node
    for (int i = 0; i < num_nodes; i++) {
        threads.emplace_back([&](int i) {
            // Call the workingThread function for the current node
            workingThread(thread_statistics[i], message_queues[i], terminate, graph, graph.getNodes()[i], message_queues, num_nodes, routing_algorithm);
            }, i);
    }

    // Start a thread to update pheromones at a given interval
    std::atomic<bool> stop_pheromone_updates{ false };
    int pheromone_update_interval = 1;
    std::thread pheromone_update_thread([&]() {
        while (!stop_pheromone_updates) {
            // Wait for the specified interval before updating the pheromones
            std::this_thread::sleep_for(std::chrono::seconds(pheromone_update_interval));
            // Update the pheromones for all edges in the graph
            graph.updateAllPheromones();
        }
        });

    // Run the simulation for the given duration
    std::this_thread::sleep_for(std::chrono::seconds(duration));
    // Set the termination flag to signal the worker threads to stop
    terminate = true;

    // Display a progress bar
    int progressBarWidth = 70;
    int totalProgressSteps = num_iterations;
    std::cout << "Progress: [";
    int currentPosition = 0;

    for (int i = 0; i < num_nodes; ++i) {
        threads[i].join();
        // Update the progress bar based on the number of completed iterations
        int newPosition = 0;
        if (totalProgressSteps != 0) {
            newPosition = (i + 1) * progressBarWidth / totalProgressSteps;
        }
        for (int j = currentPosition; j < newPosition; ++j) {
            std::cout << "=";
        }
        currentPosition = newPosition;
        std::cout << std::flush;
    }

    std::cout << "]\n";

    // Stop the pheromone update thread and join it
    stop_pheromone_updates = true;
    pheromone_update_thread.join();


}

//The main function takes command line arguments to specify the simulation duration, routing algorithm, and input filename.
// It creates a graph from the input file, initializes thread statistics and message queues, and calls the runSimulation function
// to start the simulation.

// Main function that runs the simulation

int main(int argc, char* argv[]) {

    // Set default values for simulation duration and routing algorithm
    int simulation_duration = 10;
    std::string routing_algorithm = "hot";
    std::string input_filename;

    // Parse command line arguments
    std::vector<std::string> args(argv + 1, argv + argc);

    for (size_t i = 0; i < args.size(); ++i) {

        // Check for simulation duration argument
        if (args[i] == "-d" && i + 1 < args.size()) {
            simulation_duration = std::stoi(args[++i]);
        }

        // Check for routing algorithm argument
        else if (args[i] == "-r" && i + 1 < args.size()) {
            routing_algorithm = args[++i];

            // Validate the routing algorithm option
            if (routing_algorithm != "hot" && routing_algorithm != "ant") {
                std::cerr << "Invalid routing algorithm option. Use 'hot' or 'ant'." << std::endl;
                return 1;
            }
        }

        // Set the input filename
        else if (input_filename.empty()) {
            input_filename = args[i];
        }

        // Display usage information for invalid arguments
        else {
            std::cerr << "Usage: " << argv[0] << " [-d <duration>] [-r <routing_algorithm>] <filename>" << std::endl;
            return 1;
        }
    }

    // Check that an input filename was provided
    if (input_filename.empty()) {
        std::cerr << "Usage: " << argv[0] << " [-d <duration>] [-r <routing_algorithm>] <filename>" << std::endl;
        return 1;
    }

    // Load the graph from the input file
    Graph graph(input_filename);

    // Initialize the thread statistics and message queues
    std::vector<ThreadStatistics> thread_statistics(graph.getNodes().size());
    std::vector<MessageQueue> message_queues(graph.getNodes().size());

    // Run the simulation
    runSimulation(graph, simulation_duration, routing_algorithm, thread_statistics, message_queues);

    // Return 0 to indicate successful execution
    return 0;
}

