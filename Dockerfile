FROM openjdk:11
RUN mkdir /usr/src/kbn
COPY target/kbn-0.0.1-SNAPSHOT.jar /usr/src/kbn
WORKDIR /usr/src/kbn
CMD ["java", "-jar", "kbn-0.0.1-SNAPSHOT.jar"]
