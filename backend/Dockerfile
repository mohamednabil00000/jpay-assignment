### stage 0
FROM maven:3.6.3-jdk-11 AS maven-build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=maven-build /home/app/target/assignment-0.0.1-SNAPSHOT.jar assignment-0.0.1-SNAPSHOT.jar
COPY ./sqlitesample.db sqlitesample.db
ENTRYPOINT ["java","-jar","assignment-0.0.1-SNAPSHOT.jar"]
