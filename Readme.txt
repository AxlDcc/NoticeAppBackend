

//Ingreso al sistema 

http://localhost:8080/v1/auth/login

{
    "email" : "axeldcc@gmail.com",
    "password" : "ADCC1234"
}

//Configuración backend En aplication.properties

spring.datasource.url= jdbc:sqlserver://{servername};encrypt=true;trustServerCertificate=true;databaseName=NoticeAppDevelop
spring.datasource.username= {sqlUser}
spring.datasource.password= {sqlPassword}