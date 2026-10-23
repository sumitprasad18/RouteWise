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
5. The Java engine constructs