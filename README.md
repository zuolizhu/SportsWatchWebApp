# Sports Watch Web Application
A web application built with Spring Boot Thymeleaf and MySQL.

Ideally keep the [application.properties](https://github.com/zuolizhu/SportsWatchWebApp/blob/master/src/main/resources/application.properties) file as : 
```javascript
spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.datasource.url=jdbc:mysql://localhost:3306/dbname
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWD}
```

And also, in order to make sure Facebook Login API works, please change you app id.

```javascript
FB.init({
            appId      : 'yourappid',
            cookie     : true,
            xfbml      : true,
            version    : 'v3.2'
          });
```



#### Screenshots

![Screen Shot 2018-12-06 at 9.09.47 AM](/Users/slipper/Documents/GitHub/SportsWatchWebApp/screenshots/Screen Shot 2018-12-06 at 9.09.47 AM.png)

![Screen Shot 2018-12-06 at 9.10.08 AM](/Users/slipper/Documents/GitHub/SportsWatchWebApp/screenshots/Screen Shot 2018-12-06 at 9.10.08 AM.png)

![Screen Shot 2018-12-06 at 9.10.24 AM](/Users/slipper/Documents/GitHub/SportsWatchWebApp/screenshots/Screen Shot 2018-12-06 at 9.10.24 AM.png)

![Screen Shot 2018-12-06 at 9.10.38 AM](/Users/slipper/Documents/GitHub/SportsWatchWebApp/screenshots/Screen Shot 2018-12-06 at 9.10.38 AM.png)

![Screen Shot 2018-12-06 at 9.11.21 AM](/Users/slipper/Documents/GitHub/SportsWatchWebApp/screenshots/Screen Shot 2018-12-06 at 9.11.21 AM.png)

![Screen Shot 2018-12-06 at 9.12.47 AM](/Users/slipper/Documents/GitHub/SportsWatchWebApp/screenshots/Screen Shot 2018-12-06 at 9.12.47 AM.png)

