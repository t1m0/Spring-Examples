# Spring Oauth
This project shows how to implement [Oauth2](http://tools.ietf.org/html/rfc6749) with [Spring Security Oauth2](http://projects.spring.io/spring-security-oauth/docs/oauth2.html).
## Configuration
### Authentication Entrypoint
The Authentication Entrypoint allows users to authenticate with the server and receive a token
```xml
<http pattern="/rest/**" create-session="never" entry-point-ref="oauthAuthenticationEntryPoint" access-decision-manager-ref="accessDecisionManager" >
	<anonymous enabled="false" />
	<intercept-url pattern="/rest/user/**" access="ROLE_USER" />
	<intercept-url pattern="/rest/client/**" access="ROLE_USER" />
	<custom-filter ref="resourceServerFilter" before="PRE_AUTH_FILTER" />
	<access-denied-handler ref="oauthAccessDeniedHandler" />
</http>
```
