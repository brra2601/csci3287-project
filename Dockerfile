FROM openjdk:8-jre-alpine

COPY target/leaderboard*.jar /app/app.jar

CMD java -Xmx512m -jar -Dserver.port=$PORT /app/app.jar