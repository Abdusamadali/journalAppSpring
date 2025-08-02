# journalAppSpring
Client (e.g., Browser / Postman)
    |  
    v
HTTP Request → Spring Boot Embedded Tomcat Server (Port 8080)
    |
    v
Spring Security Filter Chain
    |
    ├── Check URL matches security rules (.requestMatchers("/user/**"))
    |           ├── If not authenticated → 401 Unauthorized
    |           └── If authenticated → next filter  
    |
    v
    DispatcherServlet (Front Controller)
    |
    v
    Handler Mapping → Finds UserController.getAllUsers()
    |
    v
    UserController.getAllUsers()
    |
    v
    userService.getAlldata()
    |
    v
    UserRepository (likely JPA / Mongo repository)
    |
    v
    Database (fetch all users)
    |
    v
    UserRepository returns List<User>
    |
    v
    userService returns List<User>
    |
    v
    UserController → builds ResponseEntity
    |
    v
    DispatcherServlet → HTTP Response
    |
    v
    Spring Security → adds headers (e.g., WWW-Authenticate if 401)
    |
    v
    Tomcat → sends response to client
    |
    v
    Client receives JSON (or 204 if no content)

----------------------
Client → Tomcat → Spring Security Filter Chain
→ AuthenticationManager authenticates user (via Basic Auth)
→ SecurityContext is populated with Authentication object
→ DispatcherServlet → UserController.getAllUsers()
→ SecurityContextHolder.getContext().getAuthentication() returns auth object
