FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 80
ARG JAR_FILE=target/service-name-registry-1.0.0.jar
ADD ${JAR_FILE} service-name-registry.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5001", "-jar", "-Dserver.port=80", "/service-name-registry.jar"]
