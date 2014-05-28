# Spring MVC REST

This project shows how to implement an REST controller with [Spring MVC](http://docs.spring.io/spring-framework/docs/2.0.8/reference/mvc.html).
## Representational state transfer [REST](http://en.wikipedia.org/wiki/Representational_state_transfer)
A REST architecture provides it's data as resource to other applications.
Resources are accessed by a Uniform Resource Identifiers (URIs), which are links in the context of web applications.
These URIs are accessed with different HTTP methods to perfom different operations.
|Methods | Behavior                                   |
|------- | -------------------------------------------|
|GET     | Returns a resource / resource list|
|POST    | Creates a resource|
|PUT     | Updates a resource (should be repeadable)|
|DELETE  | Deletes a resource|

##Project Target
In this project we want to implement a REST web service which exposes a 'Todo' resource.

'GET SpringREST/todo'
```json
[
	{
		"uid" : 0,
		"description" : "Test 0",
		"name" : "Test 0"
	},
	{
		"uid" : 1,
		"description" : "Test 1",
		"name" : "Test 1"
	},
	{
		"uid" : 2,
		"description" : "Test 2",
		"name" : "Test 2"
	}
]
```
'GET SpringREST/todo/0'
```json
{
	"uid" : 0,
	"description" : "Test 0",
	"name" : "Test 0"
}
```

## Application configuration
Registers the MVC servlet dispatcher to process the requests under the given url.
```xml
<servlet>
		<servlet-name>rest</servlet-name>
 		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
 		<load-on-startup>1</load-on-startup>
	</servlet>
	<servlet-mapping>
 		<servlet-name>rest</servlet-name>
 		<url-pattern>/*</url-pattern>
	</servlet-mapping>
```
Declares where to look for spring mvc controllers, which are annotated with '@Controller'
```xml
<context:component-scan base-package="com.t1m0.spring.SpringREST.endpoints" />
<mvc:annotation-driven />
```
## Implementation of an REST controller
The class is annotated with '@RestController',  to tell spring that it is an REST web service implementation.
Each method needs it's own '@RequestMapping', to handle requests.
```java
@RestController
@RequestMapping(value="/todo", produces=MediaType.APPLICATION_JSON_VALUE)
public class TodoEndpoint {
	/** The todos. */
	@SuppressWarnings("serial")
	private Map<Long,Todo> todos = new HashMap<Long,Todo>(){{
		put(0,new Todo(0, "Test 0", "Test 0"));
		put(1,new Todo(1, "Test 1", "Test 1"));
		put(2,new Todo(2, "Test 2", "Test 2"));
	}};
	/** Method to return all entries */
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Collection<Todo>> getListTodo() {
		return new ResponseEntity<Collection<Todo>>(todos.values(),HttpStatus.OK);
	}
	/** Method to return a single entry */
	@RequestMapping(method = RequestMethod.GET, value = "/{uid}")
	public ResponseEntity<Todo> getTodo(@PathVariable long uid) {
		return new ResponseEntity<Todo>(todos.get(uid),HttpStatus.OK);
	}
```
