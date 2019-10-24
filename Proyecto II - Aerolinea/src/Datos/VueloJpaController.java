/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.IllegalOrphanException;
import Datos.exceptions.NonexistentEntityException;
import Datos.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.AvionDisponible;
import Logica.Horario;
import Logica.Ruta;
import Logica.Viaje;
import Logica.Vuelo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Monica
 */
public class VueloJpaController implements Serializable {

    public VueloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vuelo vuelo) throws PreexistingEntityException, Exception {
        if (vuelo.getViajeList() == null) {
            vuelo.setViajeList(new ArrayList<Viaje>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AvionDisponible avionAsignado = vuelo.getAvionAsignado();
            if (avionAsignado != null) {
                avionAsignado = em.getReference(avionAsignado.getClass(), avionAsignado.getCodigoMatricula());
                vuelo.setAvionAsignado(avionAsignado);
            }
            Horario horario = vuelo.getHorario();
            if (horario != null) {
                horario = em.getReference(horario.getClass(), horario.getHorario());
                vuelo.setHorario(horario);
            }
            Ruta rutaAsignada = vuelo.getRutaAsignada();
            if (rutaAsignada != null) {
                rutaAsignada = em.getReference(rutaAsignada.getClass(), rutaAsignada.getRuta());
                vuelo.setRutaAsignada(rutaAsignada);
            }
            List<Viaje> attachedViajeList = new ArrayList<Viaje>();
            for (Viaje viajeListViajeToAttach : vuelo.getViajeList()) {
                viajeListViajeToAttach = em.getReference(viajeListViajeToAttach.getClass(), viajeListViajeToAttach.getViaje());
                attachedViajeList.add(viajeListViajeToAttach);
            }
            vuelo.setViajeList(attachedViajeList);
            em.persist(vuelo);
            if (avionAsignado != null) {
                avionAsignado.getVueloList().add(vuelo);
                avionAsignado = em.merge(avionAsignado);
            }
            if (horario != null) {
                horario.getVueloList().add(vuelo);
                horario = em.merge(horario);
            }
            if (rutaAsignada != null) {
                rutaAsignada.getVueloList().add(vuelo);
                rutaAsignada = em.merge(rutaAsignada);
            }
            for (Viaje viajeListViaje : vuelo.getViajeList()) {
                Vuelo oldVueloOfViajeListViaje = viajeListViaje.getVuelo();
                viajeListViaje.setVuelo(vuelo);
                viajeListViaje = em.merge(viajeListViaje);
                if (oldVueloOfViajeListViaje != null) {
                    oldVueloOfViajeListViaje.getViajeList().remove(viajeListViaje);
                    oldVueloOfViajeListViaje = em.merge(oldVueloOfViajeListViaje);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVuelo(vuelo.getVuelo()) != null) {
                throw new PreexistingEntityException("Vuelo " + vuelo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vuelo vuelo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vuelo persistentVuelo = em.find(Vuelo.class, vuelo.getVuelo());
            AvionDisponible avionAsignadoOld = persistentVuelo.getAvionAsignado();
            AvionDisponible avionAsignadoNew = vuelo.getAvionAsignado();
            Horario horarioOld = persistentVuelo.getHorario();
            Horario horarioNew = vuelo.getHorario();
            Ruta rutaAsignadaOld = persistentVuelo.getRutaAsignada();
            Ruta rutaAsignadaNew = vuelo.getRutaAsignada();
            List<Viaje> viajeListOld = persistentVuelo.getViajeList();
            List<Viaje> viajeListNew = vuelo.getViajeList();
            List<String> illegalOrphanMessages = null;
            for (Viaje viajeListOldViaje : viajeListOld) {
                if (!viajeListNew.contains(viajeListOldViaje)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Viaje " + viajeListOldViaje + " since its vuelo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (avionAsignadoNew != null) {
                avionAsignadoNew = em.getReference(avionAsignadoNew.getClass(), avionAsignadoNew.getCodigoMatricula());
                vuelo.setAvionAsignado(avionAsignadoNew);
            }
            if (horarioNew != null) {
                horarioNew = em.getReference(horarioNew.getClass(), horarioNew.getHorario());
                vuelo.setHorario(horarioNew);
            }
            if (rutaAsignadaNew != null) {
                rutaAsignadaNew = em.getReference(rutaAsignadaNew.getClass(), rutaAsignadaNew.getRuta());
                vuelo.setRutaAsignada(rutaAsignadaNew);
            }
            List<Viaje> attachedViajeListNew = new ArrayList<Viaje>();
            for (Viaje viajeListNewViajeToAttach : viajeListNew) {
                viajeListNewViajeToAttach = em.getReference(viajeListNewViajeToAttach.getClass(), viajeListNewViajeToAttach.getViaje());
                attachedViajeListNew.add(viajeListNewViajeToAttach);
            }
            viajeListNew = attachedViajeListNew;
            vuelo.setViajeList(viajeListNew);
            vuelo = em.merge(vuelo);
            if (avionAsignadoOld != null && !avionAsignadoOld.equals(avionAsignadoNew)) {
                avionAsignadoOld.getVueloList().remove(vuelo);
                avionAsignadoOld = em.merge(avionAsignadoOld);
            }
            if (avionAsignadoNew != null && !avionAsignadoNew.equals(avionAsignadoOld)) {
                avionAsignadoNew.getVueloList().add(vuelo);
                avionAsignadoNew = em.merge(avionAsignadoNew);
            }
            if (horarioOld != null && !horarioOld.equals(horarioNew)) {
                horarioOld.getVueloList().remove(vuelo);
                horarioOld = em.merge(horarioOld);
            }
            if (horarioNew != null && !horarioNew.equals(horarioOld)) {
                horarioNew.getVueloList().add(vuelo);
                horarioNew = em.merge(horarioNew);
            }
            if (rutaAsignadaOld != null && !rutaAsignadaOld.equals(rutaAsignadaNew)) {
                rutaAsignadaOld.getVueloList().remove(vuelo);
                rutaAsignadaOld = em.merge(rutaAsignadaOld);
            }
            if (rutaAsignadaNew != null && !rutaAsignadaNew.equals(rutaAsignadaOld)) {
                rutaAsignadaNew.getVueloList().add(vuelo);
                rutaAsignadaNew = em.merge(rutaAsignadaNew);
            }
            for (Viaje viajeListNewViaje : viajeListNew) {
                if (!viajeListOld.contains(viajeListNewViaje)) {
                    Vuelo oldVueloOfViajeListNewViaje = viajeListNewViaje.getVuelo();
                    viajeListNewViaje.setVuelo(vuelo);
                    viajeListNewViaje = em.merge(viajeListNewViaje);
                    if (oldVueloOfViajeListNewViaje != null && !oldVueloOfViajeListNewViaje.equals(vuelo)) {
                        oldVueloOfViajeListNewViaje.getViajeList().remove(viajeListNewViaje);
                        oldVueloOfViajeListNewViaje = em.merge(oldVueloOfViajeListNewViaje);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = vuelo.getVuelo();
                if (findVuelo(id) == null) {
                    throw new NonexistentEntityException("The vuelo with id " + id + " no longer exists.");
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
            Vuelo vuelo;
            try {
                vuelo = em.getReference(Vuelo.class, id);
                vuelo.getVuelo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vuelo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Viaje> viajeListOrphanCheck = vuelo.getViajeList();
            for (Viaje viajeListOrphanCheckViaje : viajeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vuelo (" + vuelo + ") cannot be destroyed since the Viaje " + viajeListOrphanCheckViaje + " in its viajeList field has a non-nullable vuelo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            AvionDisponible avionAsignado = vuelo.getAvionAsignado();
            if (avionAsignado != null) {
                avionAsignado.getVueloList().remove(vuelo);
                avionAsignado = em.merge(avionAsignado);
            }
            Horario horario = vuelo.getHorario();
            if (horario != null) {
                horario.getVueloList().remove(vuelo);
                horario = em.merge(horario);
            }
            Ruta rutaAsignada = vuelo.getRutaAsignada();
            if (rutaAsignada != null) {
                rutaAsignada.getVueloList().remove(vuelo);
                rutaAsignada = em.merge(rutaAsignada);
            }
            em.remove(vuelo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vuelo> findVueloEntities() {
        return findVueloEntities(true, -1, -1);
    }

    public List<Vuelo> findVueloEntities(int maxResults, int firstResult) {
        return findVueloEntities(false, maxResults, firstResult);
    }

    private List<Vuelo> findVueloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vuelo.class));
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

    public Vuelo findVuelo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vuelo.class, id);
        } finally {
            em.close();
        }
    }

    public int getVueloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vuelo> rt = cq.from(Vuelo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
