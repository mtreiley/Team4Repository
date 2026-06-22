# Project Structure

```
Team4Repository/
├── todoapp/                        # Main application module
│   ├── build.gradle.kts            # Gradle build config (Kotlin DSL)
│   ├── todo.db                     # SQLite database file (auto-created at runtime)
│   ├── frontend/                   # Angular frontend application
│   │   ├── src/
│   │   │   ├── app/
│   │   │   │   ├── components/     # Feature components (e.g., login, todo-list, subtask)
│   │   │   │   ├── services/       # Angular services for HTTP calls to the backend API
│   │   │   │   ├── models/         # TypeScript interfaces mirroring backend entities
│   │   │   │   ├── guards/         # Route guards (e.g., auth guard)
│   │   │   │   ├── interceptors/   # HTTP interceptors (e.g., attaches JWT to requests)
│   │   │   │   ├── app.routes.ts   # Route definitions
│   │   │   │   └── app.component.ts
│   │   │   ├── assets/
│   │   │   └── index.html
│   │   ├── angular.json
│   │   ├── package.json
│   │   └── tsconfig.json
│   └── src/                        # Spring Boot backend source
│       ├── main/
│       │   ├── java/com/teamtetra/todoapp/
│       │   │   ├── TodoappApplication.java     # Spring Boot entry point
│       │   │   ├── controller/                 # REST controllers
│       │   │   │   ├── UserController.java
│       │   │   │   ├── TodoController.java
│       │   │   │   └── SubtaskController.java
│       │   │   ├── service/                    # Business logic
│       │   │   │   ├── UserService.java
│       │   │   │   ├── TodoService.java
│       │   │   │   └── SubtaskService.java
│       │   │   ├── repo/                       # Spring Data JPA repositories
│       │   │   │   ├── UserRepo.java
│       │   │   │   ├── TodoRepo.java
│       │   │   │   └── SubtaskRepo.java
│       │   │   ├── entity/                     # JPA entity classes
│       │   │   │   ├── User.java
│       │   │   │   ├── Todo.java
│       │   │   │   └── Subtask.java
│       │   │   └── exception/                  # Custom RuntimeException subclasses
│       │   │       ├── RegistrationFailure.java
│       │   │       ├── LoginFailure.java
│       │   │       ├── AddTodoFailure.java
│       │   │       └── AddSubtaskFailure.java
│       │   └── resources/
│       │       └── application.properties
│       └── test/
│           └── java/com/teamtetra/todoapp/
│               └── TodoappApplicationTests.java
```

## Architectural Pattern

Standard Spring layered architecture:

**Controller → Service → Repository → Entity**

- **Controllers** handle HTTP mapping and `@ExceptionHandler` methods for their domain exceptions. They delegate all logic to the service layer.
- **Services** contain business logic and validation. They are annotated `@Service` and use `@RequiredArgsConstructor` for dependency injection.
- **Repositories** are Spring Data JPA interfaces extending `JpaRepository`. Custom finders use Spring's derived query naming (e.g., `findByUserId`).
- **Entities** are plain JPA classes annotated with `@Entity`, `@Data`, and `@NoArgsConstructor` (Lombok). Primary keys are `Long` fields named `{entity}Id` (e.g., `userId`, `todoId`).
- **Exceptions** are `RuntimeException` subclasses, one per failure domain. They are thrown in services and caught by `@ExceptionHandler` in controllers.

## Backend Conventions

- Package root: `com.teamtetra.todoapp`
- New features follow the same four-layer structure: entity → repo → service → controller
- Each controller handles its own exceptions via `@ExceptionHandler` (no global `@ControllerAdvice` currently)
- Foreign keys between entities are stored as raw `Long` IDs (e.g., `userId` on `Todo`), not as JPA `@ManyToOne` object references
- Lombok `@Data` provides getters, setters, `equals`, `hashCode`, and `toString` on all entities

## Frontend Conventions

- Angular app lives in `todoapp/frontend/` — kept separate from the Spring Boot `src/` directory
- TypeScript interfaces in `models/` should mirror backend entity shapes (e.g., `User`, `Todo`, `Subtask`)
- HTTP calls to the backend API are encapsulated in Angular services under `services/` — components do not call `HttpClient` directly
- Backend runs on a different port during development; configure the API base URL via Angular's `environment.ts` files
- Use Angular's standalone component API (Angular 17+) unless the project is initialized with NgModules
