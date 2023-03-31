#include "graph.h"
#include <fstream>
#include <sstream>
#include <iostream>

Graph::Graph(const std::string& filename) {
    readFile(filename);
}

const std::set<int>& Graph::getNeighbors(int node) const {
    return adjacency_list.at(node);
}

const std::vector<int>& Graph::getNodes() const {
    return nodes;
}

void Graph::readFile(const std::string& filename) {
    std::ifstream file(filename);

    if (!file.is_open()) {
        std::cerr << "Error opening file: " << filename << std::endl;
        exit(1);
    }

    std::string line;
    while (std::getline(file, line)) {
        if (line[0] == '#') {
            continue; 
        }

        std::istringstream iss(line);
        int node, neighbor;
        char colon;

        iss >> node >> colon;

        
        nodes.push_back(node);

        while (iss >> neighbor) {
            adjacency_list[node].insert(neighbor);
        }
    }

    file.close();
}
