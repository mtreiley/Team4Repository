# Tech Stack

## Backend

### Language & Runtime
- Java 21
- Spring Boot 4.1.0

### Frameworks & Libraries
- **Spring Data JPA** — ORM and repository layer
- **Spring Web MVC** — REST controllers
- **Hibernate** — JPA provider with `create-drop` DDL strategy (schema is recreated on each start)
- **SQLite** via `org.xerial:sqlite-jdbc:3.53.2.0` — file-based database (`todo.db` in project root)
- **Hibernate Community Dialects** `8.0.0.Alpha1` — provides `SQLiteDialect`
- **Lombok** — reduces boilerplate (`@Data`, `@NoArgsConstructor`, `@RequiredArgsConstructor`)
- **JJWT** `0.13.0` — JWT utilities (dependency present, not yet fully implemented)

### Test Dependencies
- JUnit 5 (via `junit-platform-launcher`)
- Spring Boot test starters for JPA and Web MVC
- H2 in-memory database for tests

### Build System
- **Gradle** with Kotlin DSL (`build.gradle.kts`)
- Group: `com.teamtetra`, artifact: `todoapp`

### Database
- SQLite file at `todoapp/todo.db`
- `spring.jpa.hibernate.ddl-auto=create-drop` — the schema is dropped and recreated on every application start; all data is lost between runs
- SQL logging is enabled (`show-sql=true`, `format_sql=true`)

---

## Frontend

### Language & Runtime
- TypeScript
- Node.js (required for Angular CLI and npm)

### Frameworks & Libraries
- **Angular** (v17+) — component-based SPA framework
- **Angular CLI** — project scaffolding, build, and dev server
- **RxJS** — reactive HTTP and state management (included with Angular)
- **Angular HttpClient** — HTTP calls to the Spring Boot API

### Frontend Location
- Source lives in `todoapp/frontend/` — sibling to the backend `src/` directory

---

## Common Commands

### Backend — run from `todoapp/`

```
# Build the project
gradlew.bat build

# Run the application
gradlew.bat bootRun

# Run backend tests
gradlew.bat test

# Clean build outputs
gradlew.bat clean
```

### Frontend — run from `todoapp/frontend/`

```
# Install dependencies
npm install

# Start dev server (default: http://localhost:4200)
ng serve

# Build for production
ng build

# Run frontend tests
ng test --watch=false
```

> During development the Angular dev server runs on port 4200 and the Spring Boot API runs on port 8080. Configure the API base URL in `src/environments/environment.ts`.
