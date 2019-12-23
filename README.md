
# Task
Design and implement a RESTful API (including data model and the backing implementation) for
money transfers between accounts.
Explicit requirements:
1. You can use Java or Kotlin.
2. Keep it simple and to the point (e.g. no need to implement any authentication).
3. Assume the API is invoked by multiple systems and services on behalf of end users.
4. You can use frameworks/libraries if you like (except Spring), but don't forget about
requirement #2 and keep it simple and avoid heavy frameworks.
5. The datastore should run in-memory for the sake of this test.
6. The final result should be executable as a standalone program (should not require a
pre-installed container/server).
7. Demonstrate with tests that the API works as expected.
Implicit requirements:
1. The code produced by you is expected to be of high quality.
2. There are no detailed requirements, use common sense.

# Technologies
* Java 11
* Micronaut Framework
* gradle

# Starting the application
`
./gradlew run
`

# Running tests
`
./gradlew test
`

The application runs at localhost:8080

# API
```
POST http://localhost:8080/account/create

GET http://localhost:8080/account/balance/{UUID}

PUT http://localhost:8080/account/deposit

PUT http://localhost:8080/account/withdraw

PUT http://localhost:8080/account/transfer
```
# Sample requests

Below is a hypothetical workflow of creating 2 new accounts and transfering money between them.
```
Create a new account with id: 0030db93-593a-48d7-b85a-57c988460149 and balance 100.00
POST http://localhost:8080/account/create
{
    "accountId": "0030db93-593a-48d7-b85a-57c988460149", 
    "initialBalance": 100.00
}

Response
200
{
    "accountId": "0030db93-593a-48d7-b85a-57c988460149",
    "initialBalance": 100.0,
    "timeCreated": "2019-12-23T20:17:41.126437Z"
}


Create a new account with id: 0030db93-593a-48d7-b85a-57c988460150 and balance 150.00
POST http://localhost:8080/account/create
{
    "accountId": "0030db93-593a-48d7-b85a-57c988460150", 
    "initialBalance":150.00
}

Response
200
{
    "accountId": "0030db93-593a-48d7-b85a-57c988460150",
    "initialBalance": 150.0,
    "timeCreated": "2019-12-23T20:18:12.665536Z"
}


Query balance of account with id: 0030db93-593a-48d7-b85a-57c988460149
GET http://localhost:8080/account/balance/0030db93-593a-48d7-b85a-57c988460150

Response
200
{
    "accountId": "0030db93-593a-48d7-b85a-57c988460150",
    "timeUtc": "2019-12-23T20:18:31.121874Z",
    "balance": 150.0
}

Transfer money between accounts
PUT localhost:8080/account/transfer
{
	"senderId":"0030db93-593a-48d7-b85a-57c988460149",
	"recieverId": "0030db93-593a-48d7-b85a-57c988460150",
	"amount": "100.00"
}

Response 
200
{
    "senderId": "0030db93-593a-48d7-b85a-57c988460150",
    "recieverId": "0030db93-593a-48d7-b85a-57c988460149",
    "amount": 100.0,
    "transferTime": "2019-12-23T20:19:08.297664Z"
}

Query balance of account: 0030db93-593a-48d7-b85a-57c988460150

GET http://localhost:8080/account/balance/0030db93-593a-48d7-b85a-57c988460150
Response
200
{
    "accountId": "0030db93-593a-48d7-b85a-57c988460150",
    "timeUtc": "2019-12-23T20:20:27.151815Z",
    "balance": 250.0
}

```

