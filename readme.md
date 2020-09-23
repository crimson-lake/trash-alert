# Type 200-190
## Idea
Application is inspired by [*Uwaga śmieciarka jedzie*](https://www.facebook.com/UwagaSmieciarkaJedzie) - a community of people interested in so-called dumpster diving (picking useful treasures from other people's trash).
Dumpster diving is getting trendy among young people - some of them want to live with zero-waste philosophy, and some are just looking for classics of modern design ([like this Chierowski 366 armchair to die for](https://www.homebook.pl/artykuly/3952/fotel-chierowskiego-366-najslynniejszy-fotel-prl-u)) to spice up home decor. 

The app allows users to publish alerts about interesting items left by trash containers or post about things they want to give away. 
All alerts are displayed on the map, which makes it easier to look for treasures and plan dumpster hunt in the neighborhood. 
## Tech
* Spring Boot, Spring JPA, Spring MVC, Spring Security
* JDK 8
* MS SQL Server Express 2017
* [Thymeleaf](https://www.thymeleaf.org/)
* [Leaflet 1.6.0](https://leafletjs.com/)
## Demo
Demo version of project was deployed on AWS with Elastic Beanstalk. Application connects with database running on Amazon's RDS.

You can check out [the demo version of application here](http://190-env.eba-3axppzns.eu-central-1.elasticbeanstalk.com/).
### Login
Using the application requires logging in. You can create new user account or use test credentials from below.

**username:** user

**password:** 123456