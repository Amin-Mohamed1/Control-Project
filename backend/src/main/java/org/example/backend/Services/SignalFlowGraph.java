
/*
package org.example.backend.Services;
import java.util.*;
public class SignalFlowGraph {
    List<Node> nodes;

    public SignalFlowGraph() {
        nodes = new ArrayList<>();
    }

    public void addNode(String name) {
        nodes.add(new Node(name));
    }

    public void addEdge(String sourceName, String destName, double gain) {
        Node source = getNode(sourceName);
        Node dest = getNode(destName);
        if (source != null && dest != null) {
            source.edges.add(new Edge(source, dest, gain));
        }
    }

    private Node getNode(String name) {
        for (Node node : nodes) {
            if (node.name.equals(name)) {
                return node;
            }
        }
        return null;
    }

//    public double computeForwardPathGain(List<String> path) {
//        double gain = 1;
//        for (int i = 0; i < path.size() - 1; i++) {
//            Node source = getNode(path.get(i));
//            Node dest = getNode(path.get(i + 1));
//            Edge edge = getEdge(source, dest);
//            if (edge != null) {
//                gain *= edge.gain;
//            } else {
//                return 0;
//            }
//        }
//        return gain;
//    }



    public List<Set<Node>> findNonTouchingLoops() {
        List<Set<Node>> nonTouchingLoops = new ArrayList<>();
        List<List<Node>> allLoops = findAllLoops();
        for (int i = 0; i < allLoops.size(); i++) {
            for (int j = i + 1; j < allLoops.size(); j++) {
                if (!isTouching(allLoops.get(i), allLoops.get(j))) {
                    Set<Node> nonTouchingPair = new HashSet<>();
                    nonTouchingPair.addAll(allLoops.get(i));
                    nonTouchingPair.addAll(allLoops.get(j));
                    nonTouchingLoops.add(nonTouchingPair);
                }
            }
        }

        return nonTouchingLoops;
    }

    private List<List<Node>> findAllLoops() {
        List<List<Node>> allLoops = new ArrayList<>();
        for (Node node : nodes) {
            Stack<Node> stack = new Stack<>();
            stack.push(node);
            findLoops(node, node, stack, allLoops);
        }
        return allLoops;
    }

    private void findLoops(Node startNode, Node currentNode, Stack<Node> stack, List<List<Node>> allLoops) {
        for (Edge edge : currentNode.edges) {
            Node nextNode = edge.destination;
            if (nextNode == startNode && stack.size() > 2) {
                List<Node> loop = new ArrayList<>(stack);
                allLoops.add(loop);
            } else if (!stack.contains(nextNode)) {
                stack.push(nextNode);
                findLoops(startNode, nextNode, stack, allLoops);
                stack.pop();
            }
        }
    }

    private boolean isTouching(List<Node> loop1, List<Node> loop2) {
        for (Node node : loop1) {
            if (loop2.contains(node)) {
                return true;
            }
        }
        return false;
    }
    public void fromAdjacencyMatrix(double[][] matrix) {
        Map<Integer, Node> nodeMap = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    Node source = nodeMap.computeIfAbsent(i, k -> new Node(String.valueOf(k)));
                    Node dest = nodeMap.computeIfAbsent(j, k -> new Node(String.valueOf(k)));
                    source.edges.add(new Edge(source, dest, matrix[i][j]));
                }
            }
        }
        this.nodes.addAll(nodeMap.values());
    }

    public List<List<Node>> findAllPaths(String sourceName, String destName) {
        Node source = getNode(sourceName);
        Node dest = getNode(destName);
        if (source == null || dest == null) {
            return null;
        }

        List<List<Node>> allPaths = new ArrayList<>();
        List<Node> currentPath = new ArrayList<>();
        currentPath.add(source);
        findPaths(source, dest, currentPath, allPaths);
        return allPaths;
    }

    public List<List<Node>> loopsNonTouchingPath(List<Node> path, List<List<Node>> loops) {
        List<List<Node>> nonTouchingLoops = new ArrayList<>();
        for (List<Node> loop : loops) {
            if (!isTouching(path, loop)) {
                nonTouchingLoops.add(loop);
            }
        }
        return nonTouchingLoops;
    }

    private void findPaths(Node current, Node dest, List<Node> currentPath, List<List<Node>> allPaths) {
        if (current == dest) {
            allPaths.add(new ArrayList<>(currentPath));
            return;
        }
        for (Edge edge : current.edges) {
            if (!currentPath.contains(edge.destination)) {
                currentPath.add(edge.destination);
                findPaths(edge.destination, dest, currentPath, allPaths);
                currentPath.remove(currentPath.size() - 1);
            }
        }
    }
}


*/

package org.example.backend.Services;

import java.util.*;

public class SignalFlowGraph {
    private final List<Node> nodes;

    public SignalFlowGraph() {
        nodes = new ArrayList<>();
    }

    public void addNode(String name) {
        nodes.add(new Node(name));
    }

    public void addEdge(String sourceName, String destName, double gain) {
        Node source = getNode(sourceName);
        Node dest = getNode(destName);
        if (source != null && dest != null) {
            source.addEdge(dest, gain);
        }
    }

    private Node getNode(String name) {
        for (Node node : nodes) {
            if (node.getName().equals(name)) {
                return node;
            }
        }
        return null;
    }

    public double masonsGainFormula(String sourceName, String destName) {
        List<List<Node>> allPaths = findAllPaths(sourceName, destName);
        List<Double> forwardPathsGain = new ArrayList<>();


        // Calculate total forward path gain
        for (List<Node> path : allPaths) {
            double forwardPathGain = computeForwardPathGain(path);
            forwardPathsGain.add(forwardPathGain);
        }

        // Print all lists
        System.out.print("All Paths:");
        int i = 0;
        for (List<Node> path : allPaths) {
            System.out.print(pathToString(path));
            System.out.print(", Gain = " + forwardPathsGain.get(i));
            i++;
        }
        return 0;
    }

    private String pathToString(List<Node> path) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i).getName());
            if (i < path.size() - 1) {
                sb.append(" -> ");
            }
        }
        return sb.toString();
    }


    private double computeForwardPathGain(List<Node> path) {
        double gain = 1.0;
        for (int i = 0; i < path.size() - 1; i++) {
            Node source = path.get(i);
            Node dest = path.get(i + 1);
            Edge edge = getEdge(source, dest);
            if (edge != null) {
                gain *= edge.getGain();
            } else {
                return 0.0;
            }
        }
        return gain;
    }

    private Edge getEdge(Node source, Node destination) {
        for (Edge edge : source.getEdges()) {
            if (edge.getDestination() == destination) {
                return edge;
            }
        }
        return null;
    }

    private List<List<Node>> findAllPaths(String sourceName, String destName) {
        Node source = getNode(sourceName);
        Node dest = getNode(destName);
        if (source == null || dest == null) {
            return new ArrayList<>();
        }

        List<List<Node>> allPaths = new ArrayList<>();
        List<Node> currentPath = new ArrayList<>();
        currentPath.add(source);
        findPaths(source, dest, currentPath, allPaths);
        return allPaths;
    }

    private void findPaths(Node current, Node dest, List<Node> currentPath, List<List<Node>> allPaths) {
        if (current == dest) {
            allPaths.add(new ArrayList<>(currentPath));
            return;
        }
        for (Edge edge : current.getEdges()) {
            if (!currentPath.contains(edge.getDestination())) {
                currentPath.add(edge.getDestination());
                findPaths(edge.getDestination(), dest, currentPath, allPaths);
                currentPath.remove(currentPath.size() - 1);
            }
        }
    }
}
