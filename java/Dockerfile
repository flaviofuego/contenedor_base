FROM openjdk:17-alpine

WORKDIR /app
COPY Main.java .
RUN javac Main.java

#RUN mkdir data

CMD ["java", "Main"]