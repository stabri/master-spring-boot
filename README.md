# Spring boot learning project 

- [Technologies](#used-technologies)
- [Internationalization](#internationalization)
- [HATEOAS](#hateoes)
- [ACTUATOR](#actuator)
- [Versioning](#versioning)
- [Authentication](#authentication)
- [JPA](#jpa)
- [Richardson Maturity Model](#richardson-maturity-model)
### Used technologies

-  SpringBoot
- JPA
- Hateoas
- Swagger2
- Dev-tools spring
- Actuator + hal-browser 

### Internationalization 

 - Required: 
    -   LocalResolver 
        - default locale - Locale.EN
    - ResourceBundleMessageSource - spring concept to handle properties 
    
 - Usage :
    - Autowired MessageSource
    - @Request(value=" Accept-Language", required = false ) Locale locale
    - messageSource.getMessage("Hello.message", null, locale;)

### Hateoes

Provide Resource object which can be use to wrap return bean with additional information like links to other location id app - not hardcoded but with using calls to method id src code

### Actuator
 - Monitoring application (/actuator)
 - since 2.0.1 springBoot - to expose all actuator endpoint we need add to properties : management.endpoints.web.exposure.include=*
 - hal-browser - easy consume the actuator links (/browser/index.html#/actuator)
 - CAREFUL - a lot of confident information about app expose, need to secure properly   
 
### Versioning
 There is no one single best approach to versioning so the most challenging is to chose strategy for our API

 1. using different uri for API versions : **"/v1/user" - "v2/user"**
 2. using parameters id uri like **Mapping(value="/user/param", param="version=X")** and later URI with : **/user/param?version=X**
 3. header parameter  **Mapping(value="/user/header", headers="X-API-VERSION=X")** and specify id request header 
 4. Produces / Content negotiation **Mapping(value="/user/produces", produces="application/com.company.app-version-x+json")** it says what kind of output is produced by application - it this case it's json from application id version 1.
  
  What to consider :
  - URI pollution
  - Misuse of HTTP header
  - Caching - hard with header 
  - API Documentation
  - Can we execute the request on browser 
  
### Authentication

 1. **Basic authentication** : user + password - simple add spring-security
 2. **Adjust authentication** : adjust password created and send access application to not send to server actual password
 3. **OAuth or OAuth2**
 
### JPA

h2 default:  _**jdbc:h2:mem:testdb**_

### Richardson Maturity Model

 three levels of restful services [article](#https://martinfowler.com/articles/richardsonMaturityModel.html)
 
 **Best practices**:
 - Consumer first and good documentation
 - Best use of http provides (use http codes not server errors)
 - No secure info in URI
 - Always use plurals (users not user)
 - Use nouns for resources 
 - Consistent approach for exceptions