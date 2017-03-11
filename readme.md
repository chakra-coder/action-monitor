#Build and create docker image

## Maven build
    mvn clean install
    
## Build Docker image
    mvn clean package docker:build -DskipTests
    
# Run the application via docker-compose
    docker-compose up

# Webpage access
    Here you can see all the events related to the database changes. Right now only inserting is available.
    
    http://localhost:8080/
    
# Storing new 'Data'  

   POST http://localhost:8080/v1/data
   
   Headers:
   
    Content-Type: application/json
    
   Payload:
   
       {
        "data": "42"
       }