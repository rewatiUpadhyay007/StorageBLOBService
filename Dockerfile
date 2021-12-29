FROM openjdk:11
ADD target/StorageBlobService.jar StorageBlobService.jar
EXPOSE 9006
ENTRYPOINT ["java","-jar","StorageBlobService.jar"]
