# Spring boot learning project 

- [Technologies](#used-technologies)
- [Internationalization](#internationalization)
- [HATEOAS](#Hateoes)
- [ACTUATOR](#Actuator)


### Used technologies

- SpringBoot
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

### Actuator
 - Monitoring application (/actuator)
 - since 2.0.1 springBoot - to expose all actuator endpoint we need add to properties : management.endpoints.web.exposure.include=*
 - hal-browser - easy consume the actuator links (/browser/index.html#/actuator)
 - CAREFUL - a lot of confident information about app expose, need to secure properly   