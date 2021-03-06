# Spring Data JPA

This project shows the basic usage of [Spring Data JPA](http://projects.spring.io/spring-data-jpa/), with [Hibernate](http://hibernate.org/) as persistence provider.
An embedded [HSQL](http://hsqldb.org/) database is used to persist the entities.

## Java Persistence Api (JPA)
JPA is an API to persist objects(entities) by object relational mapping.
It consists of 5 major components
-Entity
--POJO annotated with “@Entity”
--Persistence object
-Persistence: 
--Defines at least one persistence-unit
--Specify managed persistence classes
--Object relational mapping information
--Configure entity manager and entity manager factory
-Entity Manager Factory
--Used to create an EntityManager
Entity Manager
--Associated to a persistence context (set of entities)
--Central interface to interact with the database
-Persistence Context
--Caches persistent entities
--May correspond to one or more JTA entity manager instances (all associated with the same entity manager factory)
--Controls synchronization of entities to the database
--decides when entities are transferred to the database

## Application configuration
Embedded hsql database
```xml
<jdbc:embedded-database type="HSQL" id="dataSource"/>
```
References the Hibernate persistence provider used by the entity manager factory.
```xml
<bean id="hibernatePersistenceProvider" class="org.hibernate.jpa.HibernatePersistenceProvider"/>
```
Entity Manager Factory, which creates later the entity manager
```xml
<bean id="emf" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
		<!-- Declares which packages should be scanned for entities -->
		<property name="packagesToScan" value="com.t1m0.spring.SpringJPA.entities"/>
		<!-- Declares the JPA-properties -->
		<property name="jpaProperties" >
			<props>
				<prop key="hibernate.show_sql">true</prop>
				<prop key="hibernate.hbm2ddl.auto">create</prop>
			</props>
		</property>
		<!-- Referencing of the data-source and the persistence-provider -->
		<property name="dataSource" ref="dataSource"/>
		<property name="persistenceProvider" ref="hibernatePersistenceProvider"/>
	</bean>
```
## Implementation of an persistence layer
The entity manager is automatically generated by the configured entity manager factory and injected in to our class.
We can now access the database through the entity manager.
```java
@Component
@Transactional
public class TodoDAO implements LITodo {	
	
	/** The entity manager. */
	@PersistenceContext
	private EntityManager manager;

	/** Method to store an object in the database */
	@Override
	public Todo create(final Todo a) {
		this.manager.persist(a);
		return a;
	}
}
```