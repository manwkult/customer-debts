FROM adoptopenjdk/openjdk11-openj9:x86_64-alpine-jre-11.0.11_9_openj9-0.26.0
WORKDIR /app
COPY *.jar app.jar
ENV TZ America/Bogota
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75 -Djava.security.egd=file:/dev/./urandom"
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar ./app.jar"]