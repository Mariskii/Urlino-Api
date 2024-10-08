
# Urlino Api

Urlino app is an URL shortener build with [Angular CLI](https://github.com/angular/angular-cli) version 17.3.1 and Spring. This is the backend of the project. The api manages the OAuth2 lLogin and all the requests of the app like redirect by the shortened url or create a custom URL.

![showcase_urlino-01 png](https://github.com/user-attachments/assets/3a2a95a8-bb3c-4bfc-bd66-641e434adc54)

## Technologies Used

This project leverages the following technologies:

- **Java 17**: The programming language used for building the application. Java 17 is a Long-Term Support (LTS) version, offering enhanced features, performance improvements, and increased security.

- **Spring Boot 3.3.2**: A framework built on top of Spring that simplifies the development of standalone, production-grade Spring-based applications. Spring Boot provides auto-configuration and easy setup for web applications and microservices.

- **Spring Data MongoDB**: A Spring module that provides easy integration with MongoDB, a NoSQL database, enabling efficient data persistence using BSON documents.

- **Spring Security**: A powerful and customizable authentication and access control framework for securing Spring applications, ensuring that resources are protected and accessible only to authorized users.

- **OAuth2 Client**: Support for OAuth 2.0 clients, enabling the application to integrate with external services for user authentication and authorization.

- **Maven**: A build automation tool used to manage project dependencies, compile code, run tests, and package the project. Maven streamlines the project lifecycle and ensures consistent configuration management.

- **Lombok**: A library that reduces boilerplate code in Java through annotations, automatically generating getters, setters, constructors, and other common methods.



## Installation

Install Urlino-app:

```bash
  git clone https://github.com/usuario/urlino-api.git
  cd urlino-api
  mvn clean install
```
    
## Configuration database

Set the following configuration at application.properties:

```
spring.data.mongodb.username=${mongodb_user}

spring.data.mongodb.password=${mongodb_password}

spring.data.mongodb.uri=${db_uri_environment}

spring.security.oauth2.client.registration.github.client-id=${client_id}

spring.security.oauth2.client.registration.github.client-secret=${client_secret}

```
## Usage

To execute the app:

```javascript
mvn spring-boot:run
```

Or just start the project with your IDE(Intelij, Eclipse...)

Then, use postman or your web browser at http://localhost:8080/ to test the app.
## API Reference

#### Redirect to the original URL using the shortened one

```http
  GET /api/{shortUrl}
```

| PathVariable | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `shortUrl` | `string` | **Required**. The key of the short URL |

Response: Redirects to the original URL

#### Shorten URL

```http
  POST /api/shorten
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `shorten`      | `string` | **Required**. Long URL to shorten |

Response:
```
{
    shortUrl: 'na23Dle'
}
```

#### Shorten URL(Needs OAuth2 authentication)

```http
  POST /api/custom-url
```

| RequestBody | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `customUrl`      | `CustomUrlDTO` | **Required**. Custom url created by the user |

CustomUrlDTO example:
```
{
    longUrl: 'https://github.com/Mariskii',
    customBody: 'custom-body',
    userId: '12324124'
}
```

Response:
```
{
    id: 'b19022B-ciaub91',
    longUrl: 'https://github.com/Mariskii',
    customBody: 'custom-body',
    customUrl: '"custom-body-8bdacf0'
}
```

#### Delete shortened URL(Needs OAuth2 authentication)

```http
  DELETE /api/delete-by-id
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of Url to delete |

#### Get user urls by Short URL (Needs OAuth2 authentication)

```http
  GET /api/user-urls-by-short-url
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `user`      | `OAuth2User` | **Required**. Data of the user |
| `shortUrl`      | `string` | **Required**. short url to search |
| `pageable`      | `Pageable` | **Required**. Pageable of the request |

Response:
```
{
    content:[
        {
            id: 'b19022B-ciaub91',
            longUrl: 'https://github.com/Mariskii',
            customBody: 'custom-body',
            customUrl: '"custom-body-8bdacf0'
        }
    ],
    totalElements: 1,
    totalPages: 1
}
```

#### Get user URLs (Needs OAuth2 authentication)

```http
  GET /api/user-urls
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `user`      | `OAuth2User` | **Required**. Data of the user |
| `pageable`      | `Pageable` | **Required**. Pageable of the request |

Response:
```
{
    content:[
        {
            id: 'b19022B-ciaub91',
            longUrl: 'https://github.com/Mariskii',
            customBody: 'custom-body',
            customUrl: '"custom-body-8bdacf0'
        }
    ],
    totalElements: 1,
    totalPages: 1
}
```

#### Update user URL (Needs OAuth2 authentication)

```http
  PUT /api/update-user-url
```

| RequestBody | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `updateUrlDTO`      | `UpdateUrlDTO` | **Required**. Data of the URL to update |

UpdateUrlDTO example:
```
{
    id: 'b19022B-ciaub91',
    longUrl: 'https://github.com/DRuedam',
    shortURL: 'new-custom'
}
```

Response:
```
{
    id: 'b19022B-ciaub91',
    longUrl: 'https://github.com/DRuedam',
    customBody: 'new-custom',
    customUrl: 'new-custom-8bdacf0'
}
```

#### Get user Data (Needs OAuth2 authentication)

```http
  PUT /auth/user
```

| RequestBody | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `user`      | `OAuth2User` | **Required**. Data of the user |

UpdateUrlDTO example:
```
{
    id: '21421312',
    avatar_url: 'https://github.com/avatar_url',
    login: 'NameOfUser'
}
```
## Authors

- [@Mariskii](https://www.github.com/Mariskii)

