# Link Shortener API

This is a simple REST API built with Spring Boot to create and manage short links.

## How to Run (locally)

1. Clone the repository.
2. Setup PostgreSQL locally or with docker.
3. Run the application using the Maven wrapper from the root directory:
   ```bash
   ./mvnw spring-boot:run
   ```
3. Run Tests:
   ```bash
   ./mvnw test
   ```
## How to run with Docker
1. Clone the repository.
2. Run the application using the docker compose from the root directory:
   ```bash
   docker compose up -d --build
   ```
   
## API Endpoints

### Create a Short Link

- Method: POST
- Path: /links
- Description: Creates a new short code for a given long URL.

```bash
 curl -i -X POST \                    
  -H "Content-Type: application/json" \
  -H "X-API-KEY: my-secret-key-12345" \
  -d '{"longUrl": "https://example.com"}' \
  http://localhost:8080/links
```

### Redirect to Long URL

- Method: GET
- Path: /links/{shortCode}
- Description: Redirects to the original long URL associated with the short code. A 404 Not Found is returned if the code does not exist.

```bash
# Replace {shortCode} with a code you've created
curl http://localhost:8080/links/{shortCode} -I
```

