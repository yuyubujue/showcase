#🌐 Project 22 - An online portfolio to showcase capstone projects.

<center>**Java, Spring Boot, Mysql, Vue, Bootstrap, Jquery**</center>


<center>*This is a full-stack project with a separation of front-end and back-end architecture. It uses to showcase capstone students’ work & skills.*</center>

------------


#### Project Management tool

------------

##Clone or download
```
$ git clone https://github.com/uoa-compsci399-s2-2022/showcase.git
```

## Usage 

### Prerequirements

------------
- [Java 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html "Java 11") ([Install tutorial for CentOS7](https://phoenixnap.com/kb/install-java-on-centos "Install tutorial for CentOS7"))
- [Mysql>=5.6](https://downloads.mysql.com/archives/community/ "Mysql")[(Install tutorial for CentOS7)](https://sourceexample.com/article/en/a3b034368d02b9fbc3ed2c6fc003dd4d/ "(Install tutorial for CentOS7)")
- [Nginx>=1.19](https://www.nginx.com/ "Nginx")[(Install tutorial for CentOS7)](https://phoenixnap.com/kb/how-to-install-nginx-on-centos-7 "(Install tutorial for CentOS7)")


### Client-side(Frontend) usage(PORT: 80)
Configure a new website after you install Nginx. ([Tutorial](https://docs.nginx.com/nginx/admin-guide/basic-functionality/managing-configuration-files/ "Tutorial")). Edit your website .conf and add the following code under the server section. Change the IP to your server's IP address.
```
	location /user {
        proxy_pass http://your IP:8080;
        proxy_set_header Host $http_host;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection "upgrade";
    }
    location /project {
        proxy_pass http://your IP:8080;
        proxy_set_header Host $http_host;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection "upgrade";
    }
```

It should be look like this.
```
server
{
    listen 80;
	...
	...
	
	location /user {
        proxy_pass http://1.1.1.1:8080;
        proxy_set_header Host $http_host;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection "upgrade";
    }
    location /project {
        proxy_pass http://1.1.1.1:8080;
        proxy_set_header Host $http_host;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection "upgrade";
    }
}
```
Restart or reload the Nginx. Copy the contents of the frontend folder to your website folder.  

------------


#### Server-side(Backend) usage(PORT: 8080)

------------
Run sqlscript.sql in the backend folder in MySQL([Tutorial](https://sebhastian.com/mysql-running-sql-file/ "Tutorial"))
Edit the application.properties in the project folder.
```
spring.datasource.url=jdbc:mysql://IP:Port/project?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8
spring.datasource.username=Username
spring.datasource.password=Password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl

mybatis.type-aliases-package=com.eureka.domain
mybatis.configuration.map-underscore-to-camel-case=true

spring.servlet.multipart.max-file-size=30MB
spring.servlet.multipart.max-request-size=30MB
spring.mvc.pathmatch.matching-strategy=ant_path_matcher

setting.websiteDomain = "http://Your domain"
```
Change "IP", "Port", "Username" and "Password" as your MySQL server setting in the previous step. Change the "Your domain" to the domain you set in Nginx.

------------


Find your java 11 installed path.
```
sudo update-alternatives --config java
```
It should shows like this
```
There are 2 programs which provide 'java'.

  Selection    Command
-----------------------------------------------
*+ 1           /usr/java/jdk1.8.0_121/jre/bin/java
   2           java-11-openjdk.x86_64 (/usr/lib/jvm/java-11-openjdk-11.0.16.1.1-1.el7_9.x86_64/bin/java)

Enter to keep the current selection[+], or type selection number: 
```
Replace the JAVAPATH with the path you got in the previous step and replace the WARFILE_PATH with the path where the project's code is stored.
```
sudo -u root nohup JAVAPATH -jar WARFILE_PATH/project.war --server.port=8080>> /tmp/V73XES10G1.log 2>&1 &
```
It should shows like this
```
sudo -u root nohup /usr/lib/jvm/java-11-openjdk-11.0.16.1.1-1.el7_9.x86_64/bin/java -jar /www/wwwroot/Your Domain/project.war --server.port=8080>> /tmp/V73XES10G1.log 2>&1 &
```
If you disconnect the SSH the backend may stop, you can use [Screen](https://linuxhint.com/screen-command-centos/ "Screen") to make it keep running.

------------
### DEMO Website

------------
[api.crya.me](https://api.crya.me "api.crya.me")
------------

### Future Plan

------------


------------



### Dependencies(tech-stacks)

------------
| Client-side  | Server-side  |
| ------------ | ------------ |
|  [jQuery: ^3.6.1](https://jquery.com/ "jQuery") |   |
|  [Bootstrap: ^3.4.1](https://getbootstrap.com/ "Bootstrap") |   |
|  [Vue.js: ^3.2.41](https://vuejs.org/ "Vue.js") |   |
|  [wangEditor: ^V5](https://www.wangeditor.com/en/ "wangEditor") |   |
| [Sweetalert2:^11.5.2](https://sweetalert2.github.io/ "Sweetalert2")  |   |
| [Axios: ^1.13](https://github.com/axios/axios "Axios") |   |
|  [Bootstrap-Table: ^1.21.1](https://github.com/wenzhixin/bootstrap-table "Bootstrap-Table") |   |
| [html5shiv.js: ^3.7.3](https://github.com/aFarkas/html5shiv "html5shiv") |   |
| [respond.js 1.4.2](https://github.com/scottjehl/Respond "respond.js 1.4.2") |   |



