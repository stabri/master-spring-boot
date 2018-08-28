# Spring boot learning project 

- [Technologies](#used-technologies)
- [Internationalization](#internationalization)


### Used technologies

### Internationalization 

 - Required: 
    -   LocalResolver 
        - default locale - Locale.EN
    - ResourceBundleMessageSource - spring concept to handle properties 
    
 - Usage :
    - Autowired MessageSource
    - @Request(value=" Accept-Language", required = false ) Locale locale
    - messageSource.getMessage("Hello.message", null, locale;)