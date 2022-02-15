# jpay-assignment

A single page application that shows customers info such as their name, phone number, country and state(valid or not valid).

## Technologies

* Backend: Java (Spring Boot)
* Frontend: react

## requirements

* Implement Filtering by country, phone number validity or both.
* Implement Pagination, Sorting by name and phone (bonus requirement).
* Add API documentation (bonus requirement).
* Add unit test for services.
* Dockerize the project.

## Enhancement plan for existing code in the future

* May we need use specification for advanced filtering
* Adding authentication and Authorization
* Adding feature flags
* Adding logs
* Adding functional tests
* Adding testing cycle into CI using github actions

## How to run the project

1. Clone the project
   ```sh
   git clone https://github.com/mohamednabil00000/jpay-assignment.git
   ```
2. Move to /jpay-assignment and run
   ```sh
   docker-compose build
   docker-compose up

3. The application is now running and can be accessed through
   ```sh
   http://localhost:8083/

# API Documentation

   ```sh
   http://localhost:8083/swagger-ui.html

# Run samples

