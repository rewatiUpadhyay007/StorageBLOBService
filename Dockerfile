FROM openjdk:8
ADD target/StorageBlobService.jar StorageBlobService.jar
EXPOSE 9006
ENTRYPOINT ["java","-jar","StorageBlobService.jar"]
