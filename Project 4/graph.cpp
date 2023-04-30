#include "graph.h"
#include <fstream>
#include <sstream>
#include <iostream>

Graph::Graph(const std::string& filename) {
    // Read the graph from the specified file
    readFile(filename);
}

const std::set<int>& Graph::getNeighbors(int node) const {
    // Return the set of neighbors for the specified node
    return adjacency_list.at(node);
}

const std::vector<int>& Graph::getNodes() const {
    // Return the vector of all nodes in the graph
    return nodes;
}

void Graph::readFile(const std::string& filename) {
    // Open the specified file
    std::ifstream file(filename);

    // Check if the file was opened successfully
    if (!file.is_open()) {
        std::cerr << "Error opening file: " << filename << std::endl;
        exit(1);
    }

    std::string line;
    while (std::getline(file, line)) {
        // Ignore comment lines starting with #
        if (line[0] == '#') {
            continue;
        }

        std::istringstream iss(line);
        int node, neighbor;
        char colon;

        // Parse the line to get the node and its neighbors
        iss >> node >> colon;

        // Add the node to the vector of all nodes in the graph
        nodes.push_back(node);

        // Add the node's neighbors to the adjacency list and initialize their pheromone values to 1.0
        while (iss >> neighbor) {
            adjacency_list[node].insert(neighbor);
            pheromones[std::make_pair(node, neighbor)] = 1.0;
        }
    }

    // Close the file
    file.close();
}

//This function updates the pheromone value for a given edge from node "from" to node "to" with the provided value.
void Graph::updatePheromone(int from, int to, double value) {
    pheromones[std::make_pair(from, to)] = value;
}

// Returns a reference to the internal map containing the pheromone levels for each edge.
const std::map<std::pair<int, int>, double>& Graph::getPheromones() const {
    return pheromones; // Return the pheromones map by reference.
}

// Set the parameters for the ant colony algorithm
void Graph::setAntColonyParameters(double to, double n, double delta, double t) {
    this->to = to;      // The initial pheromone value
    this->n = n;        // The number of ants in the simulation
    this->delta = delta;// The pheromone increase factor
    this->t = t;        // The pheromone evaporation rate
}


// This method updates the pheromone values between two nodes
void Graph::updateAllPheromones() {
    // Multiply all pheromone values by a decay factor
    for (auto& pheromone_entry : pheromones) {
        pheromone_entry.second *= (1 - t);
    }
}

// This method returns the weight of an edge between two nodes
double Graph::getEdgeWeight(int node_id, int target_neighbor) const {
    auto key = std::make_pair(node_id, target_neighbor);
    // Check if there is a pheromone value for the given edge
    if (pheromones.find(key) != pheromones.end()) {
        return pheromones.at(key);
    }
    // Return 0 if there is no pheromone value for the edge
    return 0.0;
}

// This method returns the ID of a randomly chosen neighbor of a node
int Graph::chooseNextNode(int node_id, int hops) const {
    // Create a vector of neighbors of the node
    std::vector<int> neighbors(getNeighbors(node_id).begin(), getNeighbors(node_id).end());
    // Return a random neighbor from the vector
    return neighbors[rand() % neighbors.size()];
}