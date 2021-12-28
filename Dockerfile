FROM openjdk:8
ADD target/StorageBlobService.jar StorageBlobService.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","StorageBlobService.jar"]