# Holinvan Web Project

This is the project created by students of the first edition of #EmpleoDigital. With colaboration of [Fundación Telefónica](https://www.fundaciontelefonica.com/) and [Tarragona Impulsa](https://www.tarragona.cat/empreses/tarragonaimpulsa) we worked in this project for three weeks and built [www.holinvan.com](http://www.holinvan.com/) web for [Camping Els Prats Village](http://www.campingelsprats.com/) company.  

# Features

  - Log in & Sign up with form, Google and Facebook 
  - Sending mail for registration process
  - Personal data and billing data for any user
  - Caravan and camping registration
  - i18n
  - Responsive design

# Technologies

  - Java 8
  - Mysql
  - Spring Data
  - Spring Security
  - Spring MVC
  - Thymeleaf
  - Bootstrap
  - Maven

# Installation

Run next commands in your console:

    $ git clone https://github.com/Holinvan/holinvan-web.git
    $ cd holinvan-web
    $ mvn clean install
    
For connect with your database, you must set the environment variable:

    DATABASE_URL= mysql://<user>:<password>@localhost:3306/<database_name>

Verify the deployment by navigating to your server address in your preferred browser.

    localhost:8080/holinvan-web