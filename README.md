
# REST API for Flash food (Online food delivery system)


<img
  align="right"
        width="25%"
        src="https://i.postimg.cc/hGY2tr5r/Flash-food.png"
        alt=""
      />    

Flash food is an online food delivery system where both customers and restaurants can interact with the application interface. After authentication, The restaurant can add the item and able to perform various tasks such as update and delete. The customers can register and log in to add available items from different restaurants to their cart.

This is a collaborative project, completed by a team of 5 backend developers.

# Tech Stack
- Java
- Hibernate
- Spring Framework
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Swagger UI


# Services

- Login service
- Restaurant Service
- Customer Service
- Item Service
- Cart Service

# Installation & Run
 - Before running the API server, you should update the database config inside the application.properties file.
- Update the port number, username and password as per your local database configuration.

```
    server.port=8088

    spring.datasource.url=jdbc:mysql://localhost:3306/cabdb;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root
```

# API Root Endpoint
```
https://localhost:8088/
```
```
http://localhost:8088/swagger-ui/
```


# Swagger Home UI (Controllers & Schema)

<img src="https://i.postimg.cc/v8j5R5MM/Swagger-home.png" >
<img src="https://i.postimg.cc/Gm3DGKxF/customer-login.png" >
<img src="https://i.postimg.cc/sDsGCm8k/restaurant.png" >
<img src="https://i.postimg.cc/hvgvg4CL/schema1.png" >
<img src="https://i.postimg.cc/pTQV0b2p/schema-2.png" >

# Collaborators

- [Subham Panda](https://www.github.com/subhampanda7)
- [Nitya Baranwal](https://www.github.com/nitya-nb)
- [Md Irfan](https://www.github.com/irfan9955)
- [Prashant Anand](https://github.com/annax3)

### Thank You😊

