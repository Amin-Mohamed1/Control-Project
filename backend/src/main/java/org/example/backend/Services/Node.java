package org.example.backend.Services;
import java.util.*;
public class Node {
    String name;
    List<Edge> edges;

    public Node(String name) {
        this.name = name;
        edges = new ArrayList<>();
    }
}