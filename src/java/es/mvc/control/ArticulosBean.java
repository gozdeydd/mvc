
package es.mvc.control;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;

/**
 *
 * @author Gozde Yazganoglu
 
ArticulosBean (Clase de Servicio):

Esta clase actúa como un componente de capa de servicio en la arquitectura MVC, 
encapsulando la lógica de negocio relacionada con los artículos.
Típicamente contiene métodos para realizar operaciones complejas o coordinar 
interacciones entre el controlador (servlet) y el modelo (clases de entidad y 
controladoras).
En el proyecto "AplicaMVC", la clase ArticulosBean puede manejar tareas como 
gestión de transacciones, validación de datos y coordinación entre el servlet y
el controlador JPA.
Además, puede proporcionar funcionalidades o abstracciones adicionales para 
simplificar la interacción entre el controlador y el modelo.
 */
class ArticulosBean {

    @PersistenceUnit(unitName = "PracticaFinalPU")
    EntityManagerFactory entityManagerFactory;

    @Inject
    UserTransaction userTransaction;

    public void añadirArticulos(Articulos articulos) throws Exception {

        getArticulosJpaController().create(articulos);

    }

    public void borrarArticulos(int id) throws Exception {

        getArticulosJpaController().destroy(id);

    }

    public void editarArticulos(Articulos articulos) throws Exception {

        getArticulosJpaController().edit(articulos);

    }

    public List<Articulos> listarArticulos() {

        return getArticulosJpaController().findArticulosEntities();

    }

    private ArticulosJpaController getArticulosJpaController() {
        return new ArticulosJpaController(userTransaction, entityManagerFactory);
    }

}
