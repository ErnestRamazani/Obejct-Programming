#ifndef GRAPH_H
#define GRAPH_H

#include <map>
#include<unordered_map>
#include <set>
#include <string>
#include <vector>

class Graph {
public:
    //Constructor that reads in a graph from a file.
    Graph(const std::string& filename);

    // Returns a set of the neighbors of the given node.
    const std::set<int>& getNeighbors(int node) const;

    // Returns a vector of all nodes in the graph.
    const std::vector<int>& getNodes() const;

    // Updates the pheromone level on the edge from 'from' to 'to' by the given value.
    void updatePheromone(int from, int to, double value);

    // Returns a map of all pheromone levels on the edges in the graph.
    const std::map<std::pair<int, int>, double>& getPheromones() const;

    // Sets the parameters for ant colony optimization.
    void setAntColonyParameters(double to, double n, double delta, double t);

    // Updates the pheromone level on all edges in the graph.
    void updateAllPheromones();

    // Chooses the next node for an ant to move to, given the current node and the number of hops taken so far.
    int chooseNextNode(int node_id, int hops) const;

    // Returns the weight of the edge from the given node to the target neighbor.
    double getEdgeWeight(int node_id, int target_neighbor) const;



private:
    // The adjacency list representing the graph.
    std::unordered_map<int, std::set<int>> adjacency_list;


    // The list of all nodes in the graph.
    std::vector<int> nodes;

    // The map of all pheromone levels on the edges in the graph.
    std::map<std::pair<int, int>, double> pheromones;

    // Parameters for ant colony optimization.
    double to;
    double n;
    double delta;
    double t;

    // Helper function to read in a graph from a file.
    void readFile(const std::string& filename);

};


#endif 