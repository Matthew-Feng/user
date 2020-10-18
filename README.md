# user detail service
How to run?  
using JDK 11+  
make sure you have maven installed

`git clone https://github.com/Matthew-Feng/user`  
`cd user`  
`mvn clean install`  
`cd target`  
`java -jar user-0.0.1-SNAPSHOT.jar`

Get user detail  
`curl  -s http://localhost:8080/api/userdetails/1`  
`curl -u user:password -s http://localhost:8080/api/userdetails/1`  
`curl -u admin:password -s http://localhost:8080/api/userdetails/1`  
`curl -u user:password -s http://localhost:8080/api/userdetails/800`  



Update user detail, there are two users with two roles, user/password with role USER, admin/password with role ADMIN, only admin can update data.  
`curl -u user:password -s --header "Content-Type: application/json" --request PATCH --data '{ "title": "Mr", "firstn": "Luke", "lastname": "Tong", "gender": "Male", "address": { "street": "abc street", "city": "Eastwood", "state": "NSW", "postcode": "2001" } }' http://localhost:8080/api/userdetails/1`  

`curl -u admin:password -s --header "Content-Type: application/json" --request PATCH --data '{ "title": "Mr", "firstn": "Luke", "lastname": "Tong", "gender": "Male", "address": { "street": "abc street", "city": "Eastwood", "state": "NSW", "postcode": "2001" } }' http://localhost:8080/api/userdetails/1`

Test Circuit  Breaker 
`curl -u user:password -s http://localhost:8080/api/userdetails/fault-tolerance-example`  

Thank you.

