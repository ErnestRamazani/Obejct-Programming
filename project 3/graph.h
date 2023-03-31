#ifndef GRAPH_H
#define GRAPH_H

#include <map>
#include <set>
#include <string>
#include <vector>

class Graph {
public:
    Graph(const std::string& filename);
    const std::set<int>& getNeighbors(int node) const;
    const std::vector<int>& getNodes() const;

private:
    std::map<int, std::set<int>> adjacency_list;
    std::vector<int> nodes;

    void readFile(const std::string& filename);
};

#endif 
