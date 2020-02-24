# HeyCar Backend Challenge
Java Case Study – Hey Car Backend Challenge 

# Task
Imagine you are creating the first version of heycar. We need to provide a platform
that can receive the listings from the dealers through different providers, and make
them available in the platform.

• Dealer - the company that is publishing the car.
• Listing - the car that is being published.
• Provider - the platform the dealers already use to manage their own listings.

# Description
We need to get vehicle listings from different sources and make it available in the
platform in a standardized format. Some of our providers are sending data via CSV,
with the following format (+example) to the /upload_csv/<dealer_id>/endpoint via
POST, where <dealer_id> will be the id of the dealer who is sending the data:

```
code,make/model,power-in-ps,year,color,price
1,mercedes/a 180,123,2014,black,15950
2,audi/a3,111,2016,white,17210
3,vw/golf,86,2018,green,14980
4,skoda/octavia,86,2018,16990
```
The rest of the providers are sending the information via JSON API, using the
following format to the /vehicle_listings/endpoint via POST:
```
[
{
"code": "a",
"make": "renault",
"model": "megane",
"kW": 132,
"year": 2014,
"color": "red",
"price": 13990
}
]
```
 
# Tools Used

1. Spring Boot 2.
2. Java 8.
3. H2 in-memory database
4. Lombok
5. JPA Specifications
6. IntelliJ IDE

# APIs Exposed

1. GET : http://localhost:8080/search?make=mercedes&model=222&year=2019&color=green ('The query params are optional')
2. POST : http://localhost:8080/upload_csv/{dealerId}/
3. POST : http://localhost:8080/vehicle_listings/{dealerId}/

# Sample cURL 

1. GET : /search API : 

```
GET /search?make=mercedes&amp; model=222&amp; year=2019&amp; color=green HTTP/1.1
Host: localhost:8080
cache-control: no-cache
Postman-Token: 43d3895a-d820-4330-a4dc-c13778966d48

```

2. POST : /upload_csv/{dealerId}

```
POST /upload_csv/113/ HTTP/1.1
Host: localhost:8080
Content-Type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW
cache-control: no-cache
Postman-Token: 8401e383-13a6-41fe-84f9-c2a61513e530

Content-Disposition: form-data; name="file"; filename="C:\Users\vingupta3\Desktop\ss.csv


------WebKitFormBoundary7MA4YWxkTrZu0gW--
```

3. POST : /vehicle_listings/{dealerId}/

```
POST /vehicle_listings/123/ HTTP/1.1
Host: localhost:8080
Content-Type: application/json
cache-control: no-cache
Postman-Token: 84c33c0f-72e0-4025-94f5-7004014e1aef
[
{
"code": "a",
"make": "renault",
"model": "megane",
"kW": 132,
"year": 2015,
"color": "red",
"price": 13990
}
]------WebKitFormBoundary7MA4YWxkTrZu0gW--

```


## 

Import into your IDE as existing Maven project and run.

or

Donwload the source code and navigate to the root folder where the pom.xml is located and run 

`mvn clean Install`

`mvn spring-boot:run`

# Example of usage 

[localhost:8080/search](localhost:8080/search).
This is a GET request.

On running the above URL with GET method on POSTMAN or any other rest client of your choice, the API will generate a result as below :

`{
    "content": [
        {
            "id": 1,
            "make": "mercedes",
            "model": "a 180",
            "color": "black",
            "price": 15950,
            "year": 2014,
            "kw": 123
        },
        {
            "id": 3,
            "make": "audi",
            "model": "a3",
            "color": "white",
            "price": 17210,
            "year": 2016,
            "kw": 111
        },
        {
            "id": 5,
            "make": "vw",
            "model": "golf",
            "color": "green",
            "price": 14980,
            "year": 2018,
            "kw": 86
        },
        {
            "id": 7,
            "make": "skoda",
            "model": "octavia",
            "color": "16990",
            "price": null,
            "year": 2018,
            "kw": 86
        }
    ],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 0,
        "pageSize": 10,
        "pageNumber": 0,
        "unpaged": false,
        "paged": true
    },
    "totalPages": 1,
    "totalElements": 4,
    "last": true,
    "size": 10,
    "number": 0,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "numberOfElements": 4,
    "first": true,
    "empty": false
}`

# Documentation

The documentation can be found in swagger API : http://localhost:8080/swagger-ui.html# 

# Questions Asked
• Problems you discovered
```
1. The main problem I found here is the contract for request parameter was different when it comes to save the data.
   Because of which, I need to create a mapper in order to specify the format for saving the data.
2. No authenticity of information.

```
• Executed tests and results

```
All test cases are written in the test folders which can be executed directly to get the results

```
• Ideas you would like to implement if you had time - explain how you would
implement them

```
Initially, I thought of creating this project in play with akka in order to make all the calls async and 
thereby tuning the application better.
Thereafter, I switched to Spring as the community of Spring is very large as compared to play with akka.

If I had time, then I would have preferred to use Spring 5 in order to make all the calls async by making the full use of CompletableFuture and CompletionStage APIs.

I would also like to dockerize my application had that application already installed in my machine. 

```

• Decisions you had to take and why

```
My first decision is to use Spring Framework due to it's large community. 
The other one is to make full use of Lombok in order to remove the getters and setters in POJOs. 
The last one is database, I could have used the general purpose database like Oracle, MongoDB. 
But I moved to H2, since it's a small application and not meant for production. 
Ideally, in production, general purpose database needs to be used.

```

• Tested architectures and reasons why you chose them instead of other options

```
I mostly focused on layered architecture of Spring. 
From design perspective, each dealer has their own set of cars needs to be placed in repository.
The dealer may send this information either by csv or by json.
The customer will come in and search for his favourite car based upon his specifications. It will list down all the information of the available cars posed by all the dealers.

```
