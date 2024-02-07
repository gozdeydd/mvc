/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.mvc.control;

import es.mvc.control.exceptions.NonexistentEntityException;
import es.mvc.control.exceptions.PreexistingEntityException;
import es.mvc.control.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author Gozde Yazganoglu
 * 
 ArticulosJpaController (Clase Controladora):

Esta clase actúa como el componente controlador responsable de manejar las 
operaciones CRUD (Crear, Leer, Actualizar, Eliminar) relacionadas con los
artículos.
Interactúa directamente con la base de datos utilizando Java Persistence API 
(JPA) para realizar operaciones de base de datos como insertar, consultar,
actualizar y eliminar registros de artículos.
Los métodos en esta clase son típicamente invocados por el Servlet (Controlador)
en respuesta a acciones de usuario, como enviar un formulario para agregar, 
editar o eliminar artículos.
 */
public class ArticulosJpaController implements Serializable {

    public ArticulosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Articulos articulos) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(articulos);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findArticulos(articulos.getIdprod()) != null) {
                throw new PreexistingEntityException("Articulos " + articulos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Articulos articulos) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            articulos = em.merge(articulos);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = articulos.getIdprod();
                if (findArticulos(id) == null) {
                    throw new NonexistentEntityException("The articulos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Articulos articulos;
            try {
                articulos = em.getReference(Articulos.class, id);
                articulos.getIdprod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The articulos with id " + id + " no longer exists.", enfe);
            }
            em.remove(articulos);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Articulos> findArticulosEntities() {
        return findArticulosEntities(true, -1, -1);
    }

    public List<Articulos> findArticulosEntities(int maxResults, int firstResult) {
        return findArticulosEntities(false, maxResults, firstResult);
    }

    private List<Articulos> findArticulosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Articulos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Articulos findArticulos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Articulos.class, id);
        } finally {
            em.close();
        }
    }

    public int getArticulosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Articulos> rt = cq.from(Articulos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
