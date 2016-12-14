# Axon EDA

This is a sample application to demonstrate Event Driven Architecture using [Axon](http://axonframework.org/) and [Spring Boot](http://start.spring.io/). It is composed by three (micro) services which exchange information by publishing and subscribing to events. The idea of the example was to implement a "Ratings" app similar to the one featured in the [Black Mirror E1S3](http://www.imdb.com/title/tt5497778/).

## Getting Started

- Ensure Java >= v8.
- Ensure recent Maven.
- Ensure [RabbitMQ]() is running.
- Build and run the 3 services (Users, Ratings, Stats):

```
$ cd users && mvn install && mvn -Dserver.port=9001 spring-boot:run
$ cd ../ratings && mvn install && mvn -Dserver.port=9002 spring-boot:run
$ cd ../stats && mvn install && mvn -Dserver.port=9003 spring-boot:run
```

# Using The App

The idea behind the Ratings app is that users will rate each other based on their real-world interactions in a scale from 1 to 5 stars.

## Sign Up User

```
$ curl localhost:9001/users/commands/sign-up -X POST \
  --data '{"username": "angoh", "fullname": "Akon Ngoh"}' \
  -H 'Content-Type:application/json'
```

## Fetch Users

```
$ curl localhost:9001/users/ -H 'Content-Type:application/json'
```

## Add Ratings

```
$ curl localhost:9002/ratings/commands/add -X POST \
  --data '{"raterUsername":"angoh", "receiverUsername":"vgomes", "stars":3}' \
  -H 'Content-Type:application/json'
```

## Check Stats

```
$ curl localhost:9003/stats/ -H 'Content-Type:application/json'
```

===
