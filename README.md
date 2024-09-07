# URL Shortener Service

This project is a simple URL shortener built with Spring Boot. It provides RESTful APIs to shorten a URL, redirect to the original URL using the shortened one, and retrieve metrics of the most shortened domains.

## Features

- URL Shortening: Accepts a long URL and returns a shortened URL.
- Redirection: Redirects the user to the original URL when accessing the shortened URL.
- Metrics API: Provides the top 3 domains that have been shortened the most number of times.
- In-Memory Storage: Stores URL mappings and metrics in memory.
- Docker Support: The application can be containerized using Docker.

## Endpoints

### 1. Shorten a URL
- **URL**: `/api/shorten`
- **Method**: `POST`
- **Request Body**: Raw URL to be shortened.
- **Response**: Shortened URL string.

### 2. Redirect to Original URL
- **URL**: `/{shortUrl}`
- **Method**: `GET`
- **Response**: Redirects to the original URL.

### 3. Get Metrics
- **URL**: `/api/metrics`
- **Method**: `GET`
- **Response**: JSON response with the top 3 most shortened domains.

### Prerequisites
- Java 17 or above
- Maven 3.6.3 or above
- Docker (optional, for containerization)
