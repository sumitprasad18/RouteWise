# RouteWise — System Architecture

## 1. Overview

RouteWise is a delivery route optimization platform designed to help small delivery businesses assign orders to vehicles and generate efficient delivery routes.

The system uses a React frontend, a Python FastAPI backend, a Java-based algorithm engine, and a PostgreSQL database.

The architecture separates the user interface, business logic, optimization algorithms, and data storage so that each component can be developed and tested independently.

## 2. Technology Stack

| Component | Technology | Responsibility |
|---|---|---|
| Frontend | React | User interface and dashboard |
| Styling | Tailwind CSS | Responsive interface design |
| Backend API | Python and FastAPI | Request validation and business logic |
| Algorithm Engine | Java | Graph algorithms and route optimization |
| Database | PostgreSQL | Persistent application data |
| Version Control | Git and GitHub | Source control and collaboration |
| Testing | JUnit and pytest | Algorithm and backend testing |

## 3. High-Level Architecture

The system follows a layered architecture.

### Layer 1: Frontend

The React frontend allows users to:

- Enter or import delivery orders.
- Configure vehicles and capacities.
- Select the depot.
- Request route optimization.
- View generated routes and statistics.
- Compare baseline and optimized results.

The frontend communicates with the FastAPI backend through REST APIs.

### Layer 2: Backend API

FastAPI acts as the main application backend.

Its responsibilities include:

- Validating incoming requests.
- Managing orders and vehicle information.
- Communicating with the Java algorithm engine.
- Storing optimization results.
- Returning results to the frontend.
- Handling errors and invalid inputs.

### Layer 3: Java Algorithm Engine

The Java engine performs the computationally intensive routing operations.

Its planned modules are:

1. Graph representation using an adjacency list.
2. Dijkstra's shortest-path algorithm.
3. Shortest-path distance caching.
4. Greedy route construction.
5. Vehicle capacity validation.
6. Local search for route improvement.
7. Route cost calculation and result validation.

The engine will be developed and tested independently before integration with FastAPI.

### Layer 4: Database

PostgreSQL will store:

- Delivery orders.
- Vehicle details and capacities.
- Depot and location information.
- Optimization run metadata.
- Generated routes and route statistics.
- Algorithm configurations and benchmark results.

The database schema will be designed during backend development.

## 4. Data Flow

A typical optimization request follows these steps:

1. The user submits delivery orders and vehicle information through the frontend.
2. The frontend sends the request to FastAPI.
3. FastAPI validates the request and retrieves or prepares the required data.
4. FastAPI sends the optimization input to the Java engine.
5. The Java engine constructs the graph representation and calculates shortest-path costs.
6. The route optimizer assigns orders to vehicles and constructs delivery sequences.
7. The engine validates the resulting routes and returns structured results.
8. FastAPI stores the optimization run and its results in PostgreSQL.
9. FastAPI returns the results to the frontend.
10. The frontend displays routes, costs, assignments, and baseline comparisons.

## 5. Communication Between Components

The frontend and FastAPI backend will communicate using HTTP and JSON.

FastAPI and the Java algorithm engine will also communicate using a documented JSON interface. The initial integration is planned through a local HTTP service.

The interface will define:

- Request format and schema version.
- Graph nodes and edges.
- Depot location.
- Delivery orders and demands.
- Vehicle capacities.
- Optimization settings.
- Route results and travel costs.
- Unassigned orders and error information.

## 6. Graph and Routing Design

The road network will be represented as a weighted graph.

- Vertices represent road intersections or delivery locations.
- Edges represent roads connecting vertices.
- Edge weights represent non-negative travel costs.
- An adjacency list stores the graph efficiently.

Dijkstra's algorithm will calculate shortest-path costs between relevant locations.

The route optimization module will use these costs to construct and improve routes for multiple vehicles while respecting capacity constraints.

The shortest-path algorithm and vehicle-routing optimizer will be separate modules because they solve different problems.

## 7. Testing and Performance Evaluation

Each component will be tested independently before integration.

### Java Algorithm Engine

- Graph construction tests.
- Dijkstra correctness tests.
- Unreachable-node tests.
- Vehicle capacity tests.
- Route validity tests.
- Algorithm runtime benchmarks.

### FastAPI Backend

- Request validation tests.
- API response tests.
- Error-handling tests.
- Java engine integration tests.
- Database integration tests.

### Frontend

- Form validation tests.
- API integration tests.
- Route result rendering tests.
- Error and empty-state tests.

The system will compare route quality and runtime against documented baseline algorithms using repeatable datasets.

## 8. Deployment Strategy

During development, the frontend, FastAPI backend, Java engine, and PostgreSQL database will run locally.

The MVP will eventually be deployed with:

- A hosted React frontend.
- A hosted FastAPI backend.
- A separately deployable Java algorithm service.
- A managed PostgreSQL database.

Deployment choices will be finalized after local integration and performance testing.

## 9. Architectural Decisions and Limitations

- Java is used for the core DSA algorithms to make their implementation and benchmarking explicit.
- FastAPI handles application-level business logic and API requests.
- PostgreSQL provides persistent storage.
- The initial optimizer targets a fixed set of orders and vehicles.
- Live traffic, GPS tracking, dynamic orders, and time-window constraints are outside the first MVP.
- Optimization results will be evaluated experimentally; no performance improvement or business savings will be assumed in advance.

## 10. Future Extensions

Potential future improvements include:

- Real road-network data.
- One-way streets and realistic travel times.
- Delivery time windows.
- Larger fleets and more complex capacity constraints.
- Historical optimization reports.
- User accounts and role-based access.
- Real-world pilot integrations.