
package com.routewise;

import com.routewise.graph.Graph;
import com.routewise.algorithm.Dijkstra;

public class GraphTest {

    public static void main(String[] args) {

        Graph graph = new Graph();

        // Test 1: Create locations and roads
        graph.addEdge("A", "B", 10);
        graph.addEdge("B", "C", 20);

        // Test 2: Verify the number of locations
        if (graph.getAdjacencyList().size() != 3) {
            throw new AssertionError("Expected 3 locations");
        }

        // Test 3: Verify the road from A to B
        Graph.Edge edgeAB = graph.getAdjacencyList()
                .get("A")
                .get(0);

        if (!edgeAB.getDestination().equals("B")
                || edgeAB.getDistance() != 10) {
            throw new AssertionError("Road A to B is incorrect");
        }

        // Test 4: Verify that roads work in both directions
        Graph.Edge edgeBA = graph.getAdjacencyList()
                .get("B")
                .get(0);

        if (!edgeBA.getDestination().equals("A")
                || edgeBA.getDistance() != 10) {
            throw new AssertionError("Road B to A is incorrect");
        }

        // Test 5: Verify the road from B to C
        Graph.Edge edgeBC = graph.getAdjacencyList()
                .get("B")
                .get(1);

        if (!edgeBC.getDestination().equals("C")
                || edgeBC.getDistance() != 20) {
            throw new AssertionError("Road B to C is incorrect");
        }

        System.out.println("Graph tests passed!");

        // Test 6: Find the shortest distances from A
        Dijkstra.Result result =
                Dijkstra.findShortestPaths(graph, "A");

        if (result.getDistanceTo("C") != 30) {
            throw new AssertionError(
                    "Expected distance A to C = 30");
        }

        // Test 7: Verify the shortest path
        if (!result.getPathTo("C")
                .equals(java.util.List.of("A", "B", "C"))) {
            throw new AssertionError(
                    "Shortest path should be A -> B -> C");
        }

        System.out.println("Dijkstra tests passed!");

        // Test 8: Test competing routes
        Graph routeGraph = new Graph();

        routeGraph.addEdge("A", "B", 4);
        routeGraph.addEdge("A", "C", 2);
        routeGraph.addEdge("C", "B", 1);
        routeGraph.addEdge("B", "D", 5);
        routeGraph.addEdge("C", "D", 8);

        Dijkstra.Result routeResult =
                Dijkstra.findShortestPaths(routeGraph, "A");

        // The shortest distance from A to D should be 8.
        if (routeResult.getDistanceTo("D") != 8) {
            throw new AssertionError(
                    "Expected shortest distance A to D = 8");
        }

        // Verify the actual route.
        if (!routeResult.getPathTo("D")
                .equals(java.util.List.of("A", "C", "B", "D"))) {
            throw new AssertionError(
                    "Expected route A -> C -> B -> D");
        }

        System.out.println("Competing route tests passed!");


        // Test 9: Verify unreachable locations
        Graph disconnectedGraph = new Graph();

        disconnectedGraph.addEdge("Warehouse", "A", 5);

        // Add an isolated delivery location.
        disconnectedGraph.addVertex("Isolated");

        Dijkstra.Result disconnectedResult =
                Dijkstra.findShortestPaths(
                        disconnectedGraph, "Warehouse");

        // The isolated location must be unreachable.
        if (disconnectedResult.getDistanceTo("Isolated")
                != Integer.MAX_VALUE) {
            throw new AssertionError(
                    "Expected Isolated to be unreachable");
        }

        // An unreachable location must have no path.
        if (!disconnectedResult.getPathTo("Isolated").isEmpty()) {
            throw new AssertionError(
                    "Expected an empty path to Isolated");
        }

        System.out.println("Unreachable location tests passed!");

        System.out.println("All tests passed!");
    }
}
