[![Build Status](https://travis-ci.org/calvached/HTTPServer.svg?branch=master)](https://travis-ci.org/calvached/HTTPServer)
# HTTPServer
A server written in Java that responds to the basic HTTP methods, GET, POST, PUT, and DELETE.
## Configuration
The default port is set to `5000`. Please make sure to have gradle installed as we will use this to build the project.  

To build the project navigate to the root directory and execute:  
`gradle build`  

To start the server:  
`java -jar build/libs/http-server-0.1.0.jar`  

Use a web browser or Postman to interact with the server at:  
`localhost:5000`
## Cob Spec
Cob spec is included in this project as a submodule.  
RubySlim is included in this project as a submodule.  

To run Cob Spec, navigate to the cob_spec directory and execute:  
`mvn package`  
Then run Cob Spec with:  
`java -jar fitnesse.jar -p 9090`

Navigate to the following url to view Cob Spec:  
`http://localhost:9090/HttpTestSuite`

__Note:__  
Make sure to have the server running when testing with Cob Spec.
## Test Suite
To run the unit tests, navigate to the root directory and execute:  
`gradle check` or `gradle test`
## How to demo GET/POST/PUT/DELETE with Postman
Use [Postman](https://www.getpostman.com/) for GET, POST, PUT, and DELETE methods.

Follow the steps below:

__GET__  
`localhost:5000/form`

__POST__  
POST the following data to `localhost:5000/form`
```
"name"="Jane"
```

__GET__  
`localhost:5000/form`  
Posted data will be the response.

__PUT__  
PUT the following data to `localhost:5000/form`
```
"name"="Diana"
```

__GET__  
`localhost:5000/form`  
Data is be updated.

__DELETE__  
`localhost:5000/form`  
Deletes data.

__GET__  
`localhost:5000/form`  
Data is deleted.
## Contributing
1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request
