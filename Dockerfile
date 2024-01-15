FROM openjdk:20
ENV APP_HOME /app
RUN mkdir $APP_HOME
WORKDIR $APP_HOME
COPY target/TicketBot-0.0.1-SNAPSHOT.jar $APP_HOME/app.jar
#COPY src/main/resources/application.properties $APP_HOME/application.properties
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]

#FROM adoptopenjdk/
#ARG JAR_FILE=target/*.jar
#WORKDIR /opt/app
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","app.jar"]
