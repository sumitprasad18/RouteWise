# RouteWise — MVP Requirements

## 1. Objective

Generate delivery plans for a fleet of vehicles while minimizing total estimated travel cost and respecting vehicle capacity constraints.

## 2. Inputs

- A depot represented by a node in a road or travel-cost graph.
- A set of delivery orders.
- A demand value for each order.
- A set of vehicles with defined capacities.
- A non-negative travel-cost matrix or weighted graph.

## 3. Functional Requirements

### FR-01: Order Management
The system shall accept a collection of delivery orders and their demand values.

### FR-02: Vehicle Management
The system shall accept multiple vehicles with individual capacity limits.

### FR-03: Route Generation
The system shall assign orders to vehicles and determine a delivery sequence for each vehicle.

### FR-04: Capacity Validation
The system shall ensure that the total assigned demand does not exceed a vehicle's capacity.

### FR-05: Route Cost Calculation
The system shall calculate the total travel cost of each route, including the return trip to the depot.

### FR-06: Baseline Comparison
The system shall compare the optimized plan against a documented baseline algorithm using the same inputs and constraints.

### FR-07: Results
The system shall report route sequences, assigned orders, total travel cost, computation time, and any unassigned orders.

### FR-08: Testing
The system shall include tests for algorithm correctness, edge cases, and capacity constraints.

## 4. Non-Functional Requirements

- The algorithms must produce reproducible results for fixed inputs.
- Invalid inputs must be rejected with useful error messages.
- The backend and algorithm engine must have documented interfaces.
- Performance measurements must use repeatable datasets and test conditions.
- The project must support version control and reproducible setup instructions.

## 5. Initial Constraints

- One depot.
- Multiple vehicles.
- One delivery per order.
- Each order has a positive demand.
- Each vehicle has a positive capacity.
- All travel costs are non-negative.
- Vehicles begin and end at the depot.
- No live traffic or dynamic order arrivals.
- No time-window constraints in the first version.

## 6. Evaluation Metrics

- Total fleet travel cost.
- Number of orders successfully assigned.
- Number of unassigned orders.
- Capacity constraint violations.
- Algorithm runtime.
- Relative cost difference compared with the baseline.

## 7. Out of Scope

- Real-time GPS tracking.
- Driver mobile applications.
- Live traffic prediction.
- Machine learning.
- Billing and payment processing.
- Automatic dispatch to external