# Backend Engineering - Internship -- Izicap Homework -- LAKHL Rachid #

## Spring Boot Microservice for ChatGPT Communication ##

This Spring Boot application includes a microservice that communicates with ChatGPT AI via an exposed endpoint. The microservice updates a CSV database with questions we send to ChatGPT and answers that the API provides us. The microservice is built using Spring Boot 3, Java 17, swagger/openapi, maven and actuator and encapsulated in a Docker container for consistency and portability across different environments. The endpoint accepts POST requests containing a question in the request body, sends the question to the OpenAI GPT API, and stores the question and API's response in a CSV file.



### Project Architecture: ###
The project uses Spring Boot 3 with Java 17, swagger/openapi, JUnit, Maven.
In the package com.backend.chatGPT : The main controller is:
- **ChatGPTController** which handles the POST requests sent to /chatGPT.
- The controller uses the **DataClient** class to store the provided question
- And the **DataRequest** class to store the parameters needed to make a request to the OpenAI GPT API.

In the package com.backend.UnitTest : The main Test Class is: **TestUnit**
- this unit test is testing the chatGPT method of the ChatGPTController class to ensure that it returns a non-null response with an HTTP status code of 200 and a non-empty response body.


### Steps to run the microservice in a Docker container: ###

1. Clone the repository to your local machine. 
   > git clone https://github.com/dihcvr/Backend_Intern_IZICAP.git
2. Open a terminal and navigate to the root of the project.
3. Build the Docker image by running the command:
   > docker build -t lakhl/springboot .

   > docker rm my_container   
4. Run the Docker container by running the command:
   #### In Windows Command Line
   > docker run --name my_container -p 8080:8080 -v %cd%\data_csv:/data_csv lakhl/springboot
   ###### OR
   > docker run --name my_container -p 8080:8080 --mount type=bind,source="%cd%\data_csv",target=/data_csv lakhl/springboot
   #### In PowerShell
   > docker run --name my_container -p 8080:8080 -v ${PWD}/data_csv:/data_csv lakhl/springboot
   ###### OR
   > docker run --name my_container -p 8080:8080 --mount type=bind,source=${PWD}/data_csv,target=/data_csv lakhl/springboot

   #### In Mac use:
   > docker run --name my_container -p 8080:8080 -v "$(pwd)"/data_csv:/data_csv lakhl/springboot
   ###### OR
   > docker run --name my_container -p 8080:8080 --mount type=bind,source="$(pwd)"/data_csv,target=/data_csv lakhl/springboot

   #### In Linux use :
   > docker run --name my_container -p 8080:8080 -v $(pwd)/data_csv:/data_csv lakhl/springboot
   
5. After the container is running,
Make requests to the microservice by sending a POST request to
   ###### http://localhost:8080/chatGPT
With a JSON body containing the question you want to ask.
<div>Exemple: ` {"question": "What is gluten sensitivity?"}) `</div>

### Using Swagger UI  ###
use the microservice with Swagger UI, open your browser and navigate to the following URL:

> http://localhost:8080/swagger-ui/index.html

This will display the Swagger UI interface where you can browse and test the different endpoints of your microservice.

### Unit tests: ###
To run the unit tests for the Spring Boot application, you can use the following command:
>docker run --name my_container -p 8080:8080 -v %cd%\data_csv:/data_csv lakhl/springboot mvn test -Dtest=UnitTest

This will run the unit tests in the UnitTest class under the com.backend.UnitTest package

##### OR
Run It manually using an IDE


### Notes: ###

- The OpenAI API key is stored in the application.properties file as api.key.
- The project uses the HttpClient from Java standard library to make requests to the OpenAI API.
- The response from the OpenAI API is written to a CSV file named Question_Answer.csv in the root directory.
- The microservice is encapsulated in a Docker container to ensure consistency and portability across different environments.
