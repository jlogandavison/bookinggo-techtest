# BookingGo Techincal Test Submission

## Setup

Build configurations included for maven. Built with `Maven 3.3.9` and `Java 8u181`

In the root directory of this project run:

```
mvn package
```

this should produce jars for both part1 and part2.

## Part 1

### Console application to print the search results for Dave's, Eric's and Jeff's Taxis

Pickup and dropoff parameters can be passed using the `-D` flag.

To run the commandline application use the command:

```
java -jar \
    -Dpickup={lat,long} \
    -Ddropoff={lat,long} \
    cmd/target/cmd-1.0-SNAPSHOT-jar-with-dependencies.jar 
```

## Part 2

Start the API server with:

`java -jar rest/target/rest-1.0-SNAPSHOT.jar`

This should start a spring server available on port `8080`.

There's a single endpoint on `/` and search parameters can be passed as query
strings. Eg:

`curl "http://localhost:8080/?pickup=1.0,1.0&dropoff=1.0,1.0"`

