# Sports Watch Web Application
A web application built with Spring Boot Thymeleaf and MySQL.

Ideally keep the [application.properties](https://github.com/zuolizhu/SportsWatchWebApp/blob/master/src/main/resources/application.properties) file as : 
```javascript
spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.datasource.url=jdbc:mysql://localhost:3306/${dbname}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWD}
```

And also, in order to make sure Facebook Login API works, please change your app id.

```javascript
FB.init({
            appId      : 'yourappid',
            cookie     : true,
            xfbml      : true,
            version    : 'v3.2'
          });
```



#### Screenshots

#### - UML Diagram

![UML Diagram](https://github.com/zuolizhu/SportsWatchWebApp/blob/master/screenshots/umldiagram.png)

#### - Homepage

![Screen Shot 2018-12-06 at 9.09.47 AM](https://github.com/zuolizhu/SportsWatchWebApp/blob/master/screenshots/Screen%20Shot%202018-12-06%20at%209.09.47%20AM.png)

#### - List of all teams

![Screen Shot 2018-12-06 at 9.10.08 AM](https://github.com/zuolizhu/SportsWatchWebApp/blob/master/screenshots/Screen%20Shot%202018-12-06%20at%209.10.08%20AM.png)

#### - Team profile

![Screen Shot 2018-12-08 at 10.09.13 PM](https://github.com/zuolizhu/SportsWatchWebApp/blob/master/screenshots/Screen%20Shot%202018-12-08%20at%2010.09.13%20PM.png)

#### - Facebook signup and login

![Screen Shot 2018-12-06 at 9.10.38 AM](https://github.com/zuolizhu/SportsWatchWebApp/blob/master/screenshots/Screen%20Shot%202018-12-06%20at%209.10.38%20AM.png)

#### - User homepage with favorite teams list, along with team update info

![Screen Shot 2018-12-06 at 9.11.21 AM](https://github.com/zuolizhu/SportsWatchWebApp/blob/master/screenshots/Screen%20Shot%202018-12-06%20at%209.11.21%20AM.png)

#### - Admin panel for user managing

![Screen Shot 2018-12-06 at 9.12.47 AM](https://github.com/zuolizhu/SportsWatchWebApp/blob/master/screenshots/Screen%20Shot%202018-12-06%20at%209.12.47%20AM.png)

