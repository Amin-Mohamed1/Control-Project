package org.example.backend.Services;


import java.util.ArrayList;
import java.util.List;

public class Node {
    private final String name;
    private final List<Edge> edges;

    public Node(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Node destination, double gain) {
        edges.add(new Edge(destination, gain));
    }

    @Override
    public String toString() {
        return name;
    }
}
