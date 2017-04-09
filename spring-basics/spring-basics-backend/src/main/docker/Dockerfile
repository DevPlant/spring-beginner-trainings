FROM java:8-jre
VOLUME /tmp

ADD spring-basics-backend.jar /app/

CMD ["java", "-Xmx750m", "-jar", "-Dspring.profiles.active=prod", "/app/spring-basics-backend.jar"]

EXPOSE 7000