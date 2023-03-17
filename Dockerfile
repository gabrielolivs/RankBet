FROM maven:3.6.3-jdk-11-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -B -f pom.xml clean package -DskipTests

FROM tomcat:10.1-jre11-temurin
COPY --from=build /workspace/target/*.war /usr/local/tomcat/webapps/
