# ABOUT

This version needs both the repository codetest_b_backend_spring and the repository codetest_b_frontend_angular. The readme tells about both.

I used Visual Studio Code for this assignment. Visual Studio Code gives some convenient help. Below are some bash commands that can be used from the terminal to handle this assignment. However the bash commands to require support for java maven and a suitable jdk or similar.

The frontend is made as an Angular application. The backend is made with Spring. And the database is MariaDB.

I have frontend on http://localhost:4200, backend on http://localhost:8080 and database on http://localhost:3306. If you use different ports you must edit the project files containing port info, fronend - environment file(s), backend application.properties. 

In application properties you must set up a connection to your database, using your credentials.

For running this project in development mode start both frontend and backend. The frontend is started with ng serve --open. This requires Angular support. The backend is started with mvn spring-boot:run. This requires support for spring boot projects in maven (java). The java version is set to 12.

Testing backend logic can be done with ./mvnw test from the root of the backend.

I have not seen any purpose of forwarding any more to production.




## LICENSE

This project is only intended as a sample for Crosskey. Licencing follows what the dependecies and so on gives.
