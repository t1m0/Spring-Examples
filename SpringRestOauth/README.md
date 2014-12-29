# Spring Oauth
This project shows how to implement [Oauth2](http://tools.ietf.org/html/rfc6749) with [Spring Security Oauth2](http://projects.spring.io/spring-security-oauth/docs/oauth2.html).
## Configuration
### Request Token 
curl -X POST -vu clientWithSecret:secret http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=user&username=user&grant_type=password&client_secret=secret&client_id=clientWithSecret"
### Request Content with token
curl http://localhost:8080/rest/userAccess/ -H "Authorization: Bearer 588133d3-ec4a-448d-aa8c-e029f1f8b117"
