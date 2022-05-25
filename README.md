# CascadeBackend

Do the following to run this app:

1. Run PostgreSQL database on docker:
   1. `docker pull postgres`
   2. `docker run --name my-postgres -p 5432:5432 -e POSTGRES_PASSWORD=mypassword -d postgres`
   3. If you are using different connection settings, please adjust `src/main/resources/application.properties`:
       ```
       spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
       spring.datasource.username=postgres
       spring.datasource.password=mypassword
       ```
2. Run `gradlew bootRun` to start backend.
3. Start frontend app.