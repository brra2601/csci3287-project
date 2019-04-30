# CSCI 3287 Project Writeup
### Brandon Rajkowski

## Overview
I constructed a cloud-based leaderboard system comprised of a backend MySQL database and a RESTful API for data access, creation, and deletion.  The system supports account registration and log-in (authorization), friend relationships with other users, and filterable high scores.  The idea is that any game requiring leaderboard services could provision an instance of this application and have a fully functional leaderboard system available via REST calls.

## Project Design Overview
![](ProjectDesign.png)

The image above describes the high-level design of the application.  There is a MySQL database that resides in the cloud that is connected to a RESTful API layer.  The API layer is written in Java and utilizes the Spring Framework to handle game client requests and executes SQL statements to retrieve data for the game clients.  The API layer also handles authentication.  In order to post and get data, a user needs to be registered in the database and needs to provide basic authentication on each REST call to the API.

## Database Design
![](MySQLDiagram.png)

There are several different entities and relationships required to create this application.  The first entity is the `user`.  A `user` has a username, password, email, and an optional name.  In order to create the social network aspect of the application, a `relationship` entity was created.  A `relationship` stores a friendship relation between two `user` entities.  In order to prevent duplicate friendship relations between users, a `relationship` entity is created in such a way that the `user_low` and `user_high` fields are populated using the lexicographical ordering of the two usernames participating in the relation.  Since these fields are foriegn keys supplied from the `users` table, no same two users can have more than one relationship.  Thus the state of their relationship can be represented using a single numerical digit, the `status`.  The `status` of the relationship can be `0` for `pending` or `1` for `friends`.

A `score` entity was created that is related to both the `users` table and the `levels` table.  This allows users to post scores on various levels.  In addition to that information, the time when the score was created, and the actual score value are a part of the `score` entity.

Finally, a simple entity was created to represent the various levels a game could have.  The `level` entity has a unique identifier as well as a descriptive name.

## API Design
The API layer was created using Java and the Spring Framework.  The overall design leverages the use of Spring authentication in order to provide a requesting user with the relevent data.  Every CRUD request send to the API requires basic authentication.  With Spring, the authenication can be used to identify which user is making the request and thus return data that is appropriate to them.  For example, there is a generic end point that can be called to get a user's friend list.  This end point uses the authentication to determine which user is making the request, and then sends back their friend list.  The entire API specs are given below.

## Signup
`POST` `user/signup`

Authorization: None

Body:
```json
{
    "username":"user",
    "password":"user",
    "name":"Tester",
    "email":"user@test.com"
}
```
Responses:
* 200 - User created successfully
* 400 - Bad request (username already taken or email already associated with another user)

## Send Friend Request
`POST` `/request`

Parameters:
* username (required) = username of recipient for the friend request

Authorization: Basic

Body: None

Responses:
* 200 - Friend request created
* 400 - Bad request (username doesn't exist, or there is already an established relationship with that user)

## View Friend Requests
`GET` `/request`

Authorization: Basic

Responses:
* 200
```json
{
    "count": 2,
    "usernames": [
        "user1",
        "user2"
    ]
}
```

## Accept Friend Request
`POST` `/friend`

Parameters:
* username (required) - username that sent the friend request to the user making the request

Body: None

Authorization: Basic

Responses:
* 200 - Friend request accepted
* 400 - Bad request (friend request does not exist)

## Post High Score
`POST` `/score`

Parameters:
* level (required) - id of the level associated with the score
* score (required) - the value of the score to be posted

Authorization: Basic

Responses:
* 200 - Score posted succesfully
* 400 - Bad request (level doesn't exist)

## Get Filterable High Scores (descending order from high to low score)
`GET` `/score/filter`

Parameters:
* level (required) - id of the level to filter scores by
* view (optional) - [`day`, `month`, `year`] view high scores for the day, month, year, or all time if not specified
* user (optional) - [`me`, `friends`] view personal high scores, high scores of just your friends, or global user high scores if not specified

Authorization: Basic

Responses:
* 200
```json
// Example request: /score/filter?level=1&view=month&user=friends
[
    {
        "id": 6,
        "username": "user",
        "level_id": 1,
        "value": 888,
        "timestamp": "2019-04-20T01:12:47.000+0000"
    },
    {
        "id": 2,
        "username": "user",
        "level_id": 1,
        "value": 106,
        "timestamp": "2019-04-19T16:51:14.000+0000"
    },
    {
        "id": 1,
        "username": "user",
        "level_id": 1,
        "value": 100,
        "timestamp": "2019-04-19T16:50:49.000+0000"
    }
]
```

## Java/SQL Interface
Entities can be created inside of Java using the Javax Persistance API.  This allows you to identify which table in SQL the entity model belongs to as well as specify the id for the entity.  Here is an example of the `score` entity created inside of Java using Javax Persistance:
```java
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "scores")
public class Score {
    @Id
    public int id;
    public String username;
    public int level_id;
    public int value;
    public Timestamp timestamp;
}
```
This snippet shows the `Score` class in Java being associated with the SQL table `scores`.  There is also an `@Id` annotation that is a shorthand way of saying that the id for this object should be a integer type with name `id`.

The Spring Framework provides a little bit of SQL magic as well as full control to implement custom SQL queries.  There is a class in the framework called a `CrudRepository`.  This class allows you to associate any entity object (like the `Score` object above) with an id and a set of SQL queries.  Some queries do not require any SQL and are already implemented in Spring.  Custom queries can be added by using an annotation and specifying a Java method for it.  An example of this is shown below.
```java
public interface RelationshipRepository extends CrudRepository<Relationship, RelationshipIdentity> {
    @Override
    List<Relationship> findAll();

    @Query(value = "SELECT * FROM relationships WHERE user_low = :username OR user_high = :username", nativeQuery=true)
    List<Relationship> findByUsername(@Param("username") String username);
```
The `findAll()` method is already specified from Spring and returns all of the relationship entities in the relationship table, however the `findByUsername()` method is custom and requires the SQL query to be written.

## Cloud Deployment
The application is running at https://leaderboard-project.herokuapp.com.  In order to deploy the application to the cloud, it needed to be placed into a container.  Docker was used to perform the containerization of the jar file, and push the container up to the Heroku registry which utimately allows it to run on a cloud computer.

## Learning Goals Met
My goal for this project was to learn about querying a database from an application in order to serve users content.  I also wanted to learn about deploying a cloud application that is persisted by an external database.

I was able to achieve both of these goals by combining MySQL, Java, Docker, and Heroku.  I was able to query the MySQL database from inside a Java application.  Furthermore, the database and API work as independent services and even if the API goes down, the database is still up and running.  In this sense, the cloud application is persisted by an external database.

I was also able to meet an unexpected goal of securing a API with authentication.  All of the endpoints, except for user sign up, are protected with Spring authorization.  This was another new concept for me, and I was able to learn and apply the concepts to this project.

## Source
https://github.com/brra2601/csci3287-project

## Application
https://leaderboard-project.herokuapp.com/

Interact with the API using the API documentation above.  The first step would be to sign up for an account using the `/user/signup` API.