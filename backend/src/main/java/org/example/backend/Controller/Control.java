package org.example.backend.Controller;
import org.example.backend.Services.RouthArray;
import org.example.backend.Services.SignalFlowGraph;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController()
@CrossOrigin("*")
@RequestMapping(path = "/programming_assignment")
public class Control {

    @PostMapping(path = "/computeTransferFunction")
    public double computeTransferFunction(@RequestBody double[][] matrix) {
        SignalFlowGraph graph = createGraph(matrix);
        return graph.masonsGainFormula("Node1",("Node"+matrix.length));
    }

    private SignalFlowGraph createGraph(double[][] matrix) {
        SignalFlowGraph graph = new SignalFlowGraph();

        // Adding nodes to the graph
        for (int i = 0; i < matrix.length; i++) {
            graph.addNode("Node" + (i + 1));
        }

        // Adding edges to the graph based on the matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    graph.addEdge("Node" + (i + 1), "Node" + (j + 1), matrix[i][j]);
                }
            }
        }
        return graph;
    }

    @PostMapping(path = "/computeRouthArray")
    public RouthArray computeRouthArray(@RequestBody double[] coefficients) {
        RouthArray routhArray = new RouthArray();
        return routhArray.isSystemStable(coefficients);
    }
}
