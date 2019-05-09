# Task 2:Design a new service
I will follow the below steps to design any new service to make it secure

1) I will always keep in mind to follow OWASP 10 rule.
2) I will analyse the current  security of the application such on 
`i)Platform Layer - Secret Management, Traffic Proxy(Authenticated,Authorized,Encrypted etc)
 ii)Application Middleware - Auth0,HashiCorp Vault,Key Mangement,Cryto API,Data Protection
 iii)Frameworks - XSS,SQL Injection, Session Managment
 iv)Application Logic `
3)Encrypt all user sensitive data.
4)I will understand and then design my service based on behavior of the service by using OAuth2
5)Code it by witing all the Unit Test,Integration Test by thinking all the possible sceneiors of the requirement
6)Code Quality by SonarQube and any other tools.
7)Security Test by using tool such HP Fortify
8)Performance,Load,Regration Test
9)Smoke Test

In Backend
1) Firewall
2) Two factor authentication
3) Secured Login
4) Authorization
5) Hashing

I will always cosider
1) Authentication such as OpenID Connect
2) Authorization such as OAuth2
3) Transport Protection
4) Cross Origin Resource Sharing(CORS)
5) Cross Site Request Forgery(CSRF)
6) Cross Site Scripting(XSS)

Client Library
1) angular-jwt
2) oidc-client

JWT(JSON Web Toke) Token Type are ID Token and Access Token.
1) JSON encoded
2) Symmetric & Asymmetric Signature
3) Symmetric & Asymmetric encryption

Design within service in Career Market Place (CMP):
1) **Model:** Design the model within service based on requirement. Also analyse whether it's interacting with any external source.
2) **Mapper / DTO:** If any Data Transfer Object(DTO) required. Write your mapper class or use dependency mapper class to convert model to dto
3) **Dao:** Write Dao using Hibernate & JPA to interact with Database.
4) **Service:** Write Service & ServiceImpl to write business logic which interact with Dao class, internal & external service
5) **Controller:** Write controller class where all the endpoint will interact based on http call type. It will output as Response Entity along with Http Status.
6) **Handler & Custom Exception:** All the exception thrown by service are handled by custom exception. Custom exception help to response readable message that can be easily understood.
7) **Enums /Other:** Write all the enums & other in this folder
8) **Properties:** Write a separate file for each environment and make sure that there is no sensitive data and encrypt all the sensitive data. Avoid any hard code within application, instead use properties file.
9) **Configuration**: Write a configuration such as to secure your service using Basic Authentication or OAuth2 Authentication by using Spring Security.


