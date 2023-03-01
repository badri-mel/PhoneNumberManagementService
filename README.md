
# PhoneNumberManagementService

We are a Telecom operator. In our database, we are starting to store phone numbers
associated to customers (1 customer: N phone numbers) and we will provide
interfaces to manage them.
We need to provide the below capabilities:
• get all phone numbers
• get all phone numbers of a single customer
• activate a phone number

### Challenge

Provide API specifications for the above functions/capabilities.
Provide a REST API implementation of the formulated specifications.
You can assume the phone numbers as a static data structure that is initialised when
your program runs.

### Language

Java

### Deliverables

Interface specifications, the source files and any test code – preferably in a github /
bitbucket public repo.
The solution will be evaluated based on the following criteria:
1. Correctness
2. Code structure
3. Data structures
4. Extensibility
5. Maintainability
6. Test coverage
7. Performance

### API Interfaces 

- Running the application API [Swagger UI] (http://localhost:8080/swagger-ui/#/phone-number-controller)

Method |Path| Description                       |               Format                |
-------| ------------------------- |-----------------------------------|:-----------------------------------:|
GET   | /v1/phone-numbers?page={page}&size={size}| Get all the phone numbers         |   |
GET   | /v1/customers/{id}/phone-numbers         | Get all the phones for a customer ||
PUT   | /v1/phone-number/{id}/activate           | Activate a phone number           ||


### Response DTOs formats 
## [All Phone Numbers Response Example]()
```json
{
  "content": [
    {
      "customerId": 1,
      "phoneNumberId": 1,
      "number": "0444555666",
      "status": "ACTIVE"
    },
    {
      "customerId": 2,
      "phoneNumberId": 2,
      "number": "0444555667",
      "status": "ACTIVE"
    },
    {
      "customerId": 2,
      "phoneNumberId": 3,
      "number": "0444555668",
      "status": "ACTIVE"
    }
  ],
  "pageable": {
    "sort": {
      "empty": true,
      "unsorted": true,
      "sorted": false
    },
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 20,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalElements": 3,
  "totalPages": 1,
  "first": true,
  "size": 20,
  "number": 0,
  "sort": {
    "empty": true,
    "unsorted": true,
    "sorted": false
  },
  "numberOfElements": 3,
  "empty": false
}
```
## [Get all Phones  for a specific customer]

```json
{
  "content": [
    {
      "customerId": 2,
      "phoneNumberId": 2,
      "number": "0444555667",
      "status": "ACTIVE"
    },
    {
      "customerId": 2,
      "phoneNumberId": 3,
      "number": "0444555668",
      "status": "ACTIVE"
    }
  ],
  "pageable": {
    "sort": {
      "empty": true,
      "unsorted": true,
      "sorted": false
    },
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 20,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalElements": 2,
  "totalPages": 1,
  "first": true,
  "size": 20,
  "number": 0,
  "sort": {
    "empty": true,
    "unsorted": true,
    "sorted": false
  },
  "numberOfElements": 2,
  "empty": false
}


```

### [Activate phone number ]

```json
{
  "message": "Activated successfully"
}
```

### Database setup 
- Postgres need to install , to install follow the instruction @ https://www.moncefbelyamani.com/how-to-install-postgresql-on-a-mac-with-homebrew-and-lunchy/
- or need access to postgres instance 
- Configure the application.properties for the db instance 
- Execute the following DDL and Insert script to create initial data setup on that db instance 

``` sql
CREATE SEQUENCE  IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE customer (
  id BIGINT NOT NULL,
   firstname VARCHAR(255),
   lastname VARCHAR(255),
   email VARCHAR(255),
   CONSTRAINT pk_customer PRIMARY KEY (id)
);


CREATE TABLE phone_number (
  id BIGINT NOT NULL,
   number VARCHAR(255) NOT NULL,
   status VARCHAR(255),
   customer_id BIGINT,
   CONSTRAINT pk_phone_number PRIMARY KEY (id)
);

ALTER TABLE phone_number ADD CONSTRAINT FK_PHONE_NUMBER_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id);


INSERT INTO public.customer
(id, firstname, lastname, email)
VALUES(1, 'badri', 'sugavanam', 'a@a.com');
INSERT INTO public.customer
(id, firstname, lastname, email)
VALUES(2, 'vyas', 'badri', 'v@a.com');


INSERT INTO public.phone_number
(id, "number", status, customer_id)
VALUES(2, '0444555667', 'ACTIVE', 2);
INSERT INTO public.phone_number
(id, "number", status, customer_id)
VALUES(1, '0444555666', 'ACTIVE', 1);
INSERT INTO public.phone_number
(id, "number", status, customer_id)
VALUES(3, '0444555668', 'ACTIVE', 2);

```
### HOW TO RUN 
- Clone the repo 
- mvn spring-boot:run

### PROJECT SETUP 
- IntelliJ IDE https://www.jetbrains.com/idea/download/#section=mac
- JAVA 17 setup refer to https://intellij-support.jetbrains.com/hc/en-us/community/posts/360004337880-Setting-Amazon-Corretto-11-as-Project-SDK
- If not using IntelliJ IDE or only need Generic JAVA setup can be done using SDKMAN - Install SDKMAN if mac brew install sdkman - Install Amazon java by using sdk install java x.y.z-amzn 
- Install Maven brew install maven

### ASSUMPTIONS
- Each phone number stored in the phone table is assumed to be unique 
- The API is assumed to be already secured.
- The Initial data is assumed to be fed from the database.

### FUTURE WORK
- Write integration tests using test containers in actual H2 DB in test profile
