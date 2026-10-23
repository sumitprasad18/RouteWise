
package com.routewise.graph;

import java.util.*;

public class Graph {
    private final Map<String, List<Edge>> adjacencyList = new HashMap<>();

    public void addVertex(String vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(String source, String destination, int distance) {
        if (distance < 0) {
            throw new IllegalArgumentException("Distance cannot be negative.");
        }

        addVertex(source);
        addVertex(destination);

        adjacencyList.get(source).add(new Edge(destination, distance));
        adjacencyList.get(destination).add(new Edge(source, distance));
    }

    public Map<String, List<Edge>> getAdjacencyList() {
        return Collections.unmodifiableMap(adjacencyList);
    }

    public static class Edge {
        private final String destination;
        private final int distance;

        public Edge(String destination, int distance) {
            this.destination = destination;
            this.distance = distance;
        }

        public String getDestination() {
            return destination;
        }

        public int getDistance() {
            return distance;
        }
    }
}
