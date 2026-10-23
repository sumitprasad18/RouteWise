
package com.routewise.algorithm;

import com.routewise.graph.Graph;
import java.util.*;

public class Dijkstra {

    public static class Result {
        private final Map<String, Integer> distances;
        private final Map<String, String> previous;

        public Result(
                Map<String, Integer> distances,
                Map<String, String> previous) {
            this.distances = distances;
            this.previous = previous;
        }

        public int getDistanceTo(String destination) {
            return distances.getOrDefault(destination, Integer.MAX_VALUE);
        }

        public List<String> getPathTo(String destination) {
            if (getDistanceTo(destination) == Integer.MAX_VALUE) {
                return Collections.emptyList();
            }

            LinkedList<String> path = new LinkedList<>();
            String current = destination;

            while (current != null) {
                path.addFirst(current);
                current = previous.get(current);
            }

            return path;
        }
    }

    private static class NodeDistance {
        private final String node;
        private final int distance;

        public NodeDistance(String node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static Result findShortestPaths(Graph graph, String source) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();

        for (String vertex : graph.getAdjacencyList().keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }

        if (!distances.containsKey(source)) {
            throw new IllegalArgumentException(
                    "Source vertex does not exist: " + source);
        }

        PriorityQueue<NodeDistance> queue = new PriorityQueue<>(
                Comparator.comparingInt(node -> node.distance));

        distances.put(source, 0);
        queue.offer(new NodeDistance(source, 0));

        while (!queue.isEmpty()) {
            NodeDistance current = queue.poll();

            if (current.distance != distances.get(current.node)) {
                continue;
            }

            for (Graph.Edge edge :
                    graph.getAdjacencyList().get(current.node)) {

                String neighbor = edge.getDestination();
                int weight = edge.getDistance();

                if (current.distance > Integer.MAX_VALUE - weight) {
                    continue;
                }

                int newDistance = current.distance + weight;

                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    previous.put(neighbor, current.node);

                    queue.offer(
                            new NodeDistance(neighbor, newDistance));
                }
            }
        }

        return new Result(distances, previous);
    }
}
