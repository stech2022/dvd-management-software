# DVD Management Software

This is a project made for the course Software Technology 

## Technologies

- Java 11
- MySQL
- JUnit
- Maven
- JSON
- Tomcat (for deploying the app)

## How to run

```
git clone https://stech22/dvd-management-software
```


Import the program into an IDE(Intellij IDEA, Eclipse, Netbeans ...) for convinience

Download Tomcat into a folder and extract the file and set it up with preferred IDE

- [Tomcat](https://tomcat.apache.org/download-90.cgi) -> Tested in 9.0.65 Version

### How to setup in Intellij IDEA Community

- Import the project
- Download the free plugin SmartTomcat (File -> Settings -> Plugins)
- Edit configurations and make the setup for Tomcat and JUnit

### Setup MySQL

First create the database
```
CREATE DATABASE dvdMS;
```

And change the credentials in the file [Database.java](https://github.com/stech2022/dvd-management-software/blob/main/src/main/java/com/example/dvdmanagementsoftware/database/Database.java)
```
 String url = "jdbc:mysql://0.0.0.0:3306/dvdMS?allowPublicKeyRetrieval=true&useSSL=false";
 String username = "root";
 String password = "root";
```

### Endpoints

A collection of endpoints have been created for fast testing in the file 

[DVDManagementSoftware.postman_collection.json](https://github.com/stech2022/dvd-management-software/blob/main/DVDManagementSoftware.postman_collection.json)

You just have to import it in Postman and all the available endpoints will
be ready.

### Video proof of concept

[Download](https://github.com/stech2022/dvd-management-software/blob/main/dvdms.mp4)