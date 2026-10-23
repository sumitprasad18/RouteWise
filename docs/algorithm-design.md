# RouteWise — Algorithm Design

## 1. Objective

RouteWise aims to minimize total estimated travel cost while assigning delivery orders to vehicles and respecting vehicle capacity constraints.

The system separates shortest-path calculation from multi-vehicle route optimization.

## 2. Graph Representation

The road network is represented as a weighted graph G = (V, E).

- V represents road intersections and delivery locations.
- E represents roads connecting vertices.
- Each edge has a non-negative travel cost.
- An adjacency list stores the graph.
- Each node has a unique identifier.

The initial implementation will support undirected roads. Directed edges can be introduced when one-way roads are needed.

## 3. Core Data Structures

| Data Structure | Purpose |
|---|---|
| Adjacency list | Store graph vertices and edges |
| HashMap | Look up vertices and shortest-path data |
| PriorityQueue | Select the smallest tentative distance |
| Distance map | Store the best known distance to each vertex |
| Predecessor map | Reconstruct shortest paths |
| List | Store delivery sequences and routes |
| Set | Track assigned orders and prevent duplicates |

## 4. Dijkstra's Shortest-Path Algorithm

Dijkstra's algorithm calculates the shortest travel cost from a source vertex to reachable vertices in a graph with non-negative edge weights.

### Planned procedure

1. Initialize the source distance to zero.
2. Initialize all other distances as unreachable.
3. Insert the source into a minimum-priority queue.
4. Remove the entry with the smallest tentative distance.
5. Examine its outgoing edges.
6. Update a neighbour if a lower-cost path is found.
7. Repeat until the queue is empty.
8. Use predecessor information to reconstruct paths when needed.

### Complexity

With an adjacency list and binary heap, the expected time complexity is O((V + E) log V).

The graph and algorithm state require O(V + E) space.

## 5. Shortest-Path Caching

RouteWise will reuse shortest-path results when evaluating different delivery sequences.

The initial implementation will calculate shortest-path distances from relevant locations and cache them for the current graph.

If there are S relevant source locations, preprocessing takes approximately O(S(V + E) log V) time.

The cache must be invalidated if the graph or its travel costs change.

## 6. Greedy Route Construction

The initial route optimizer will use a greedy strategy.

1. Start each vehicle at the depot.
2. Identify unassigned orders that fit the remaining vehicle capacity.
3. Select a feasible nearby order using shortest-path travel cost.
4. Add the order to the vehicle's route.
5. Repeat until no further feasible order can be added.
6. Include the return trip to the depot.
7. Report any unassigned orders.

Greedy construction provides a fast baseline but does not guarantee a globally optimal solution.

## 7. Local Search

A later optimization stage will attempt to improve the greedy solution using operations such as:

- Relocating a stop within a route.
- Swapping two stops.
- Moving a delivery between vehicles.

Only moves that preserve feasibility and improve the objective will be accepted.

The search will stop when no improving move is found or a configured iteration limit is reached.

## 8. Optimization Objective

The primary objective is to minimize total fleet travel cost.

Total fleet cost is the sum of the travel costs of all vehicle routes, including return trips to the depot.

The initial version will use non-negative integer travel-cost units.

Unassigned orders and capacity violations will be reported explicitly. A route plan with constraint violations must never be reported as fully successful.

## 9. Testing Strategy

### Graph Tests
- Empty graph.
- Single vertex.
- Connected graph.
- Disconnected graph.
- Multiple paths with different costs.
- Equal-cost paths.
- Invalid edges and negative weights.

### Routing Tests
- No delivery orders.
- One vehicle and one order.
- Multiple vehicles.
- Vehicle capacity constraints.
- Orders that exceed every vehicle's capacity.
- Unreachable delivery locations.
- Duplicate assignment prevention.
- Return-to-depot cost calculation.

## 10. Benchmarking Strategy

Compare the greedy baseline with the greedy-plus-local-search algorithm using identical datasets and constraints.

Measure:

- Total travel cost.
- Number of assigned orders.
- Number of unassigned orders.
- Constraint violations.
- Algorithm runtime.
- Solution quality against exact solutions on small instances.

All benchmark results must be reproducible. No performance improvement will be claimed without measured evidence.

## 11. Limitations

The first version will not include live traffic, real-time GPS, dynamic order arrivals, or delivery time windows.

The quality of the resulting routes depends on the road graph, the accuracy of its edge costs, and the optimization strategy.