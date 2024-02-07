# Model View Controller
This is my first java EE project that I have created as a final project of my Oracle Java Course. It is a basic Model-View-Controller project. It should work on NetBeans8 with JEE7. Here I expalain basically.


1. **Articulos (Entity Class)**:
   - This represents the entity or object model of an "Articulo" (article) in the application. It typically mirrors the structure of the "Articulos" table in the database, with attributes such as ID, category, description, and price.
   - This class is generated automatically by NetBeans based on the database schema and is used for mapping database records to Java objects and vice versa.

2. **ArticulosJpaController (Controller Class)**:
   - This class serves as the controller component responsible for handling CRUD (Create, Read, Update, Delete) operations related to articles.
   - It interacts directly with the database using Java Persistence API (JPA) to perform database operations such as inserting, querying, updating, and deleting article records.
   - Methods in this class are typically invoked by the Servlet (Controller) in response to user actions, such as submitting a form to add, edit, or delete articles.

3. **ControlServlet (Servlet)**:
   - This is a Java servlet that acts as a controller in the MVC architecture. It receives and processes HTTP requests from clients, such as web browsers, and dispatches them to appropriate actions.
   - The servlet typically contains logic to parse form data, validate inputs, and delegate business logic to other components such as service classes or controller classes.
   - In the "AplicaMVC" project, the ControlServlet likely receives form submissions from the web interface, extracts data, and invokes methods in the ArticulosJpaController to perform CRUD operations on articles.

4. **ArticulosBean (Service Class)**:
   - This class acts as a service layer component in the MVC architecture, encapsulating business logic related to articles.
   - It typically contains methods to perform complex operations or coordinate interactions between the controller (servlet) and the model (entity classes and controller classes).
   - In the "AplicaMVC" project, the ArticulosBean class may handle tasks such as transaction management, data validation, and coordination between the servlet and the JPA controller.
   - Additionally, it might provide additional functionalities or abstractions to simplify the interaction between the controller and the model.

