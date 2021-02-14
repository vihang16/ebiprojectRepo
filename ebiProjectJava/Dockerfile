FROM alpine:edge
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /ebiProjectJava.jar
ENTRYPOINT ["java","-jar","/ebiProjectJava.jar"]