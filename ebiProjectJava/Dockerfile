FROM alpine:edge
VOLUME /tmp
ARG JAR_FILE=target/*.jar
RUN apk add --no-cache openjdk11
COPY ${JAR_FILE} /ebiProjectJava.jar
ENTRYPOINT ["java","-jar","/ebiProjectJava.jar"]