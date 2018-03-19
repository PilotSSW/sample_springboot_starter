This is a sample springboot application! 

This project uses JPA and Hibernate persistence to store entity objects to memory. 

To start the rest client - mvn spring-boot:run

A list of users can be found at :
http:localhost:8080/user/names

Their ages can be found at :
http:localhost:8080/user/ages

Their birthdays can be found at : 
http:localhost:8080/user/birthdays

A new user can be added by posting a json object containing a name, age and birthday formatted as yyyy-MM-dd 

A person's age and birthday can be found at
http:localhost:8080/{user's name}/age
http:localhost:8080/{user's name}/birthday