=============
# chirp-java
=============

to discover services using chirp on the same network

## Example:


* execute the following commands
  
  - ``` java -jar chirp-example/target/chirp-example-1.0-SNAPSHOT.jar server chirp-example/example/chirper1.yml```
  - ``` java -jar chirp-example/target/chirp-example-1.0-SNAPSHOT.jar server chirp-example/example/chirper2.yml```
  - open [http://localhost:9001/chirpers](http://localhost:9001/chirpers), you will see that chirper1 has discovered chirper2, the response should be as follows
   
  
  ```json   
      {
        "chirper2": {
            "config": {},
            "uri": "chirp.org",
            "protocol": "http",
            "name": "chirper2",
            "port": "9002"
        }
      }
```

  - Also if you, open [http://localhost:9002/chirpers](http://localhost:9002/chirpers), you will see that chirper2 has discovered chirper1, the response should be as follows

  ```json   
    {
        "chirper1": {
            "config": {},
            "uri": "chirp.org",
            "protocol": "http",
            "name": "chirper1",
            "port": "9001"
        }
    }
  ```

- now execute

    ``` java -jar chirp-example/target/chirp-example-1.0-SNAPSHOT.jar server chirp-example/example/chirper3.yml```

  - and open

    - [http://localhost:9001/chirpers](http://localhost:9001/chirpers)
    - [http://localhost:9002/chirpers](http://localhost:9002/chirpers)
    - [http://localhost:9003/chirpers](http://localhost:9003/chirpers)
      

  - you will see that they have all discovered each other
  - To understand the apis provided by chirp-core have a look at the [chirp-example](chirp-example)
      
      
      
