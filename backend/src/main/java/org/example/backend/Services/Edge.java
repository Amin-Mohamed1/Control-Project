package org.example.backend.Services;

public class Edge {
    private final Node destination;
    private final double gain;

    public Edge(Node destination, double gain) {
        this.destination = destination;
        this.gain = gain;
    }

    public Node getDestination() {
        return destination;
    }

    public double getGain() {
        return gain;
    }
}
