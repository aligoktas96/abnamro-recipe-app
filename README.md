Recipe Service Assigment 
---------------
## 🔨 Environments & Dependencies
```
------------------------------
- JDK 11
- Maven 4.0.0
- Docker Engine v20.10.5
------------------------------
- Spring Boot - 2.6.2
    - starter-web
    - starter-data-jpa
    - starter-validation
    - springdoc-openapi-ui
    - starter-test

- lombok
- postgresql
- test-containers
------------------------------
```


## 🚀 Run all instances
 - `docker-compose.yml` file has been created to run all modules.
 - `run.sh` starts the docker-compose. 

 ##### To make it work;
  1. `chmod a+x *.sh`
  2. `./run.sh`



#####  🎉 Swagger Documentation : `http://localhost/doc/`

```
I wrote the tests as much as I can.