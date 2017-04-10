FROM java:8-jre
VOLUME /tmp

ADD library-backend.jar /app/

CMD ["java", "-Xmx750m", "-jar", "-Dspring.profiles.active=prod,sample-data", "/app/library-backend.jar"]

EXPOSE 7000