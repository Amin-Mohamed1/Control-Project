package org.example.backend.Services;
import java.util.*;
public class Edge {
    Node source;
    Node destination;
    double gain;

    public Edge(Node source, Node destination, double gain) {
        this.source = source;
        this.destination = destination;
        this.gain = gain;
    }
}
