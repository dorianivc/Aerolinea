/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.IllegalOrphanException;
import Datos.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Ciudad;
import Logica.Ruta;
import Logica.Vuelo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Monica
 */
public class RutaJpaController implements Serializable {

    public RutaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ruta ruta) {
        if (ruta.getVueloList() == null) {
            ruta.setVueloList(new ArrayList<Vuelo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ciudad ciudadSalida = ruta.getCiudadSalida();
            if (ciudadSalida != null) {
                ciudadSalida = em.getReference(ciudadSalida.getClass(), ciudadSalida.getCiudad());
                ruta.setCiudadSalida(ciudadSalida);
            }
            Ciudad ciudadLlegada = ruta.getCiudadLlegada();
            if (ciudadLlegada != null) {
                ciudadLlegada = em.getReference(ciudadLlegada.getClass(), ciudadLlegada.getCiudad());
                ruta.setCiudadLlegada(ciudadLlegada);
            }
            List<Vuelo> attachedVueloList = new ArrayList<Vuelo>();
            for (Vuelo vueloListVueloToAttach : ruta.getVueloList()) {
                vueloListVueloToAttach = em.getReference(vueloListVueloToAttach.getClass(), vueloListVueloToAttach.getVuelo());
                attachedVueloList.add(vueloListVueloToAttach);
            }
            ruta.setVueloList(attachedVueloList);
            em.persist(ruta);
            if (ciudadSalida != null) {
                ciudadSalida.getRutaList().add(ruta);
                ciudadSalida = em.merge(ciudadSalida);
            }
            if (ciudadLlegada != null) {
                ciudadLlegada.getRutaList().add(ruta);
                ciudadLlegada = em.merge(ciudadLlegada);
            }
            for (Vuelo vueloListVuelo : ruta.getVueloList()) {
                Ruta oldRutaAsignadaOfVueloListVuelo = vueloListVuelo.getRutaAsignada();
                vueloListVuelo.setRutaAsignada(ruta);
                vueloListVuelo = em.merge(vueloListVuelo);
                if (oldRutaAsignadaOfVueloListVuelo != null) {
                    oldRutaAsignadaOfVueloListVuelo.getVueloList().remove(vueloListVuelo);
                    oldRutaAsignadaOfVueloListVuelo = em.merge(oldRutaAsignadaOfVueloListVuelo);
                }
            }
            em.getTransaction().commit();
        }finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ruta ruta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ruta persistentRuta = em.find(Ruta.class, ruta.getRuta());
            Ciudad ciudadSalidaOld = persistentRuta.getCiudadSalida();
            Ciudad ciudadSalidaNew = ruta.getCiudadSalida();
            Ciudad ciudadLlegadaOld = persistentRuta.getCiudadLlegada();
            Ciudad ciudadLlegadaNew = ruta.getCiudadLlegada();
            List<Vuelo> vueloListOld = persistentRuta.getVueloList();
            List<Vuelo> vueloListNew = ruta.getVueloList();
            List<String> illegalOrphanMessages = null;
            for (Vuelo vueloListOldVuelo : vueloListOld) {
                if (!vueloListNew.contains(vueloListOldVuelo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Vuelo " + vueloListOldVuelo + " since its rutaAsignada field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (ciudadSalidaNew != null) {
                ciudadSalidaNew = em.getReference(ciudadSalidaNew.getClass(), ciudadSalidaNew.getCiudad());
                ruta.setCiudadSalida(ciudadSalidaNew);
            }
            if (ciudadLlegadaNew != null) {
                ciudadLlegadaNew = em.getReference(ciudadLlegadaNew.getClass(), ciudadLlegadaNew.getCiudad());
                ruta.setCiudadLlegada(ciudadLlegadaNew);
            }
            List<Vuelo> attachedVueloListNew = new ArrayList<Vuelo>();
            for (Vuelo vueloListNewVueloToAttach : vueloListNew) {
                vueloListNewVueloToAttach = em.getReference(vueloListNewVueloToAttach.getClass(), vueloListNewVueloToAttach.getVuelo());
                attachedVueloListNew.add(vueloListNewVueloToAttach);
            }
            vueloListNew = attachedVueloListNew;
            ruta.setVueloList(vueloListNew);
            ruta = em.merge(ruta);
            if (ciudadSalidaOld != null && !ciudadSalidaOld.equals(ciudadSalidaNew)) {
                ciudadSalidaOld.getRutaList().remove(ruta);
                ciudadSalidaOld = em.merge(ciudadSalidaOld);
            }
            if (ciudadSalidaNew != null && !ciudadSalidaNew.equals(ciudadSalidaOld)) {
                ciudadSalidaNew.getRutaList().add(ruta);
                ciudadSalidaNew = em.merge(ciudadSalidaNew);
            }
            if (ciudadLlegadaOld != null && !ciudadLlegadaOld.equals(ciudadLlegadaNew)) {
                ciudadLlegadaOld.getRutaList().remove(ruta);
                ciudadLlegadaOld = em.merge(ciudadLlegadaOld);
            }
            if (ciudadLlegadaNew != null && !ciudadLlegadaNew.equals(ciudadLlegadaOld)) {
                ciudadLlegadaNew.getRutaList().add(ruta);
                ciudadLlegadaNew = em.merge(ciudadLlegadaNew);
            }
            for (Vuelo vueloListNewVuelo : vueloListNew) {
                if (!vueloListOld.contains(vueloListNewVuelo)) {
                    Ruta oldRutaAsignadaOfVueloListNewVuelo = vueloListNewVuelo.getRutaAsignada();
                    vueloListNewVuelo.setRutaAsignada(ruta);
                    vueloListNewVuelo = em.merge(vueloListNewVuelo);
                    if (oldRutaAsignadaOfVueloListNewVuelo != null && !oldRutaAsignadaOfVueloListNewVuelo.equals(ruta)) {
                        oldRutaAsignadaOfVueloListNewVuelo.getVueloList().remove(vueloListNewVuelo);
                        oldRutaAsignadaOfVueloListNewVuelo = em.merge(oldRutaAsignadaOfVueloListNewVuelo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ruta.getRuta();
                if (findRuta(id) == null) {
                    throw new NonexistentEntityException("The ruta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ruta ruta;
            try {
                ruta = em.getReference(Ruta.class, id);
                ruta.getRuta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ruta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Vuelo> vueloListOrphanCheck = ruta.getVueloList();
            for (Vuelo vueloListOrphanCheckVuelo : vueloListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ruta (" + ruta + ") cannot be destroyed since the Vuelo " + vueloListOrphanCheckVuelo + " in its vueloList field has a non-nullable rutaAsignada field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Ciudad ciudadSalida = ruta.getCiudadSalida();
            if (ciudadSalida != null) {
                ciudadSalida.getRutaList().remove(ruta);
                ciudadSalida = em.merge(ciudadSalida);
            }
            Ciudad ciudadLlegada = ruta.getCiudadLlegada();
            if (ciudadLlegada != null) {
                ciudadLlegada.getRutaList().remove(ruta);
                ciudadLlegada = em.merge(ciudadLlegada);
            }
            em.remove(ruta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ruta> findRutaEntities() {
        return findRutaEntities(true, -1, -1);
    }

    public List<Ruta> findRutaEntities(int maxResults, int firstResult) {
        return findRutaEntities(false, maxResults, firstResult);
    }

    private List<Ruta> findRutaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ruta.class));
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

    public Ruta findRuta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ruta.class, id);
        } finally {
            em.close();
        }
    }

    public int getRutaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ruta> rt = cq.from(Ruta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
