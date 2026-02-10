@echo off
set SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3307/review_db?createDatabaseIfNotExist=true
mvn spring-boot:run
