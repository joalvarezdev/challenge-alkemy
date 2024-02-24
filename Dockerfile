FROM joalvarezdev/springboot-alpine-17:latest

MAINTAINER joalvarez

COPY target/Challenge Alkemy.jar .

EXPOSE 8090

ENTRYPOINT ["java","-jar","Challenge Alkemy.jar"]
