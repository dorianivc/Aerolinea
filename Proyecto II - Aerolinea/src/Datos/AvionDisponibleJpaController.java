/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.IllegalOrphanException;
import Datos.exceptions.NonexistentEntityException;
import Datos.exceptions.PreexistingEntityException;
import Logica.AvionDisponible;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Vuelo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Monica
 */
public class AvionDisponibleJpaController implements Serializable {

    public AvionDisponibleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AvionDisponible avionDisponible) throws PreexistingEntityException, Exception {
        if (avionDisponible.getVueloList() == null) {
            avionDisponible.setVueloList(new ArrayList<Vuelo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Vuelo> attachedVueloList = new ArrayList<Vuelo>();
            for (Vuelo vueloListVueloToAttach : avionDisponible.getVueloList()) {
                vueloListVueloToAttach = em.getReference(vueloListVueloToAttach.getClass(), vueloListVueloToAttach.getVuelo());
                attachedVueloList.add(vueloListVueloToAttach);
            }
            avionDisponible.setVueloList(attachedVueloList);
            em.persist(avionDisponible);
            for (Vuelo vueloListVuelo : avionDisponible.getVueloList()) {
                AvionDisponible oldAvionAsignadoOfVueloListVuelo = vueloListVuelo.getAvionAsignado();
                vueloListVuelo.setAvionAsignado(avionDisponible);
                vueloListVuelo = em.merge(vueloListVuelo);
                if (oldAvionAsignadoOfVueloListVuelo != null) {
                    oldAvionAsignadoOfVueloListVuelo.getVueloList().remove(vueloListVuelo);
                    oldAvionAsignadoOfVueloListVuelo = em.merge(oldAvionAsignadoOfVueloListVuelo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAvionDisponible(avionDisponible.getCodigoMatricula()) != null) {
                throw new PreexistingEntityException("AvionDisponible " + avionDisponible + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AvionDisponible avionDisponible) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AvionDisponible persistentAvionDisponible = em.find(AvionDisponible.class, avionDisponible.getCodigoMatricula());
            List<Vuelo> vueloListOld = persistentAvionDisponible.getVueloList();
            List<Vuelo> vueloListNew = avionDisponible.getVueloList();
            List<String> illegalOrphanMessages = null;
            for (Vuelo vueloListOldVuelo : vueloListOld) {
                if (!vueloListNew.contains(vueloListOldVuelo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Vuelo " + vueloListOldVuelo + " since its avionAsignado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Vuelo> attachedVueloListNew = new ArrayList<Vuelo>();
            for (Vuelo vueloListNewVueloToAttach : vueloListNew) {
                vueloListNewVueloToAttach = em.getReference(vueloListNewVueloToAttach.getClass(), vueloListNewVueloToAttach.getVuelo());
                attachedVueloListNew.add(vueloListNewVueloToAttach);
            }
            vueloListNew = attachedVueloListNew;
            avionDisponible.setVueloList(vueloListNew);
            avionDisponible = em.merge(avionDisponible);
            for (Vuelo vueloListNewVuelo : vueloListNew) {
                if (!vueloListOld.contains(vueloListNewVuelo)) {
                    AvionDisponible oldAvionAsignadoOfVueloListNewVuelo = vueloListNewVuelo.getAvionAsignado();
                    vueloListNewVuelo.setAvionAsignado(avionDisponible);
                    vueloListNewVuelo = em.merge(vueloListNewVuelo);
                    if (oldAvionAsignadoOfVueloListNewVuelo != null && !oldAvionAsignadoOfVueloListNewVuelo.equals(avionDisponible)) {
                        oldAvionAsignadoOfVueloListNewVuelo.getVueloList().remove(vueloListNewVuelo);
                        oldAvionAsignadoOfVueloListNewVuelo = em.merge(oldAvionAsignadoOfVueloListNewVuelo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = avionDisponible.getCodigoMatricula();
                if (findAvionDisponible(id) == null) {
                    throw new NonexistentEntityException("The avionDisponible with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AvionDisponible avionDisponible;
            try {
                avionDisponible = em.getReference(AvionDisponible.class, id);
                avionDisponible.getCodigoMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The avionDisponible with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Vuelo> vueloListOrphanCheck = avionDisponible.getVueloList();
            for (Vuelo vueloListOrphanCheckVuelo : vueloListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AvionDisponible (" + avionDisponible + ") cannot be destroyed since the Vuelo " + vueloListOrphanCheckVuelo + " in its vueloList field has a non-nullable avionAsignado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(avionDisponible);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AvionDisponible> findAvionDisponibleEntities() {
        return findAvionDisponibleEntities(true, -1, -1);
    }

    public List<AvionDisponible> findAvionDisponibleEntities(int maxResults, int firstResult) {
        return findAvionDisponibleEntities(false, maxResults, firstResult);
    }

    private List<AvionDisponible> findAvionDisponibleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AvionDisponible.class));
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

    public AvionDisponible findAvionDisponible(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AvionDisponible.class, id);
        } finally {
            em.close();
        }
    }

    public int getAvionDisponibleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AvionDisponible> rt = cq.from(AvionDisponible.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
