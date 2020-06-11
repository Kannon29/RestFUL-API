# RESTful-API
Implementation of RESTful Api using Spring Boot, Bearer and MySQL

# Dependencies
- Maven
- Spring Boot 2.3.0
- JDK 14
- Spring Web
- MySQL
- Bucket4J
- jjwt
- JPA
- Bearer

# Quick demo
[YouTube Video](https://www.youtube.com/watch?v=nkYVHxQB1N8&feature=youtu.be/)

# Description

| Request Type  | Endpoint | Request Body | Purpose |
| ------------- | ------------- | ------------- | ------------- |
| POST  | {url}/register  | { "username: "user", "password" : "password" }  | Creates an user  |
| POST  | {url}/login  | { "username: "user", "password" : "password" }  | Main purpose for loggin in. It will return a Bearer token!  |
| POST  | {url}/products  | { "name: "name", "category" : "category", "price" : "price" }  | Adds a product to the list!  |
| PUT  | {url}/products/$ID  | { "name" : "name" } OR { "category" : "category" } OR { "price" : "price" }  | Updates the product with the $ID with a new name/category/price  |
| GET  | {url}/products  | -  | Retrieves the list with products  |
| GET  | {url}/products/$ID  | -  | $ID needs to be replaced with the wanted it. It will return the product with that id!  |
| GET  | {url}/produtcs/category/$CATEGORY  | -  | $CATEGORY has to be replaced with searched category. It will return a list with products in that category!  |
| GET  | {url}/produtcs/name/$NAME  | -  | $NAME has to be replaced with searched name. It will return the info for the product with that name!  |
| DELETE  | {url}/products/$ID  | -  | Deletes a product.  |


The program has been tested and written using IntelliJ IDEA, MySQL and Postman.

For this program to work, a MySQL server is needed (local) with port 3308, and you have to edit the following lines in **src/main/resources/application.properties**:
```
spring.datasource.username=username
spring.datasource.password=password
```
You can also change the port or the name of the database on the following line, changing the bolded text for the port and the italic text for the name.
```
spring.datasource.url=jdbc:mysql://localhost:**3308**/*bootdb*?createDatabaseIfNotExist=true&autoReconnect=true&useSSL=false
```
where username represents the username for the database, and password the password. There is no need to create a new database, as the application will
create a new one on its own, called **bootdb**.
