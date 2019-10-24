/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.IllegalOrphanException;
import Datos.exceptions.NonexistentEntityException;
import Datos.exceptions.PreexistingEntityException;
import Logica.Ciudad;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Pais;
import Logica.Ruta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Monica
 */
public class CiudadJpaController implements Serializable {

    public CiudadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ciudad ciudad) throws PreexistingEntityException, Exception {
        if (ciudad.getRutaList() == null) {
            ciudad.setRutaList(new ArrayList<Ruta>());
        }
        if (ciudad.getRutaList1() == null) {
            ciudad.setRutaList1(new ArrayList<Ruta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pais pais = ciudad.getPais();
            if (pais != null) {
                pais = em.getReference(pais.getClass(), pais.getPais());
                ciudad.setPais(pais);
            }
            List<Ruta> attachedRutaList = new ArrayList<Ruta>();
            for (Ruta rutaListRutaToAttach : ciudad.getRutaList()) {
                rutaListRutaToAttach = em.getReference(rutaListRutaToAttach.getClass(), rutaListRutaToAttach.getRuta());
                attachedRutaList.add(rutaListRutaToAttach);
            }
            ciudad.setRutaList(attachedRutaList);
            List<Ruta> attachedRutaList1 = new ArrayList<Ruta>();
            for (Ruta rutaList1RutaToAttach : ciudad.getRutaList1()) {
                rutaList1RutaToAttach = em.getReference(rutaList1RutaToAttach.getClass(), rutaList1RutaToAttach.getRuta());
                attachedRutaList1.add(rutaList1RutaToAttach);
            }
            ciudad.setRutaList1(attachedRutaList1);
            em.persist(ciudad);
            if (pais != null) {
                pais.getCiudadList().add(ciudad);
                pais = em.merge(pais);
            }
            for (Ruta rutaListRuta : ciudad.getRutaList()) {
                Ciudad oldCiudadSalidaOfRutaListRuta = rutaListRuta.getCiudadSalida();
                rutaListRuta.setCiudadSalida(ciudad);
                rutaListRuta = em.merge(rutaListRuta);
                if (oldCiudadSalidaOfRutaListRuta != null) {
                    oldCiudadSalidaOfRutaListRuta.getRutaList().remove(rutaListRuta);
                    oldCiudadSalidaOfRutaListRuta = em.merge(oldCiudadSalidaOfRutaListRuta);
                }
            }
            for (Ruta rutaList1Ruta : ciudad.getRutaList1()) {
                Ciudad oldCiudadLlegadaOfRutaList1Ruta = rutaList1Ruta.getCiudadLlegada();
                rutaList1Ruta.setCiudadLlegada(ciudad);
                rutaList1Ruta = em.merge(rutaList1Ruta);
                if (oldCiudadLlegadaOfRutaList1Ruta != null) {
                    oldCiudadLlegadaOfRutaList1Ruta.getRutaList1().remove(rutaList1Ruta);
                    oldCiudadLlegadaOfRutaList1Ruta = em.merge(oldCiudadLlegadaOfRutaList1Ruta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCiudad(ciudad.getCiudad()) != null) {
                throw new PreexistingEntityException("Ciudad " + ciudad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ciudad ciudad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ciudad persistentCiudad = em.find(Ciudad.class, ciudad.getCiudad());
            Pais paisOld = persistentCiudad.getPais();
            Pais paisNew = ciudad.getPais();
            List<Ruta> rutaListOld = persistentCiudad.getRutaList();
            List<Ruta> rutaListNew = ciudad.getRutaList();
            List<Ruta> rutaList1Old = persistentCiudad.getRutaList1();
            List<Ruta> rutaList1New = ciudad.getRutaList1();
            List<String> illegalOrphanMessages = null;
            for (Ruta rutaListOldRuta : rutaListOld) {
                if (!rutaListNew.contains(rutaListOldRuta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ruta " + rutaListOldRuta + " since its ciudadSalida field is not nullable.");
                }
            }
            for (Ruta rutaList1OldRuta : rutaList1Old) {
                if (!rutaList1New.contains(rutaList1OldRuta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ruta " + rutaList1OldRuta + " since its ciudadLlegada field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (paisNew != null) {
                paisNew = em.getReference(paisNew.getClass(), paisNew.getPais());
                ciudad.setPais(paisNew);
            }
            List<Ruta> attachedRutaListNew = new ArrayList<Ruta>();
            for (Ruta rutaListNewRutaToAttach : rutaListNew) {
                rutaListNewRutaToAttach = em.getReference(rutaListNewRutaToAttach.getClass(), rutaListNewRutaToAttach.getRuta());
                attachedRutaListNew.add(rutaListNewRutaToAttach);
            }
            rutaListNew = attachedRutaListNew;
            ciudad.setRutaList(rutaListNew);
            List<Ruta> attachedRutaList1New = new ArrayList<Ruta>();
            for (Ruta rutaList1NewRutaToAttach : rutaList1New) {
                rutaList1NewRutaToAttach = em.getReference(rutaList1NewRutaToAttach.getClass(), rutaList1NewRutaToAttach.getRuta());
                attachedRutaList1New.add(rutaList1NewRutaToAttach);
            }
            rutaList1New = attachedRutaList1New;
            ciudad.setRutaList1(rutaList1New);
            ciudad = em.merge(ciudad);
            if (paisOld != null && !paisOld.equals(paisNew)) {
                paisOld.getCiudadList().remove(ciudad);
                paisOld = em.merge(paisOld);
            }
            if (paisNew != null && !paisNew.equals(paisOld)) {
                paisNew.getCiudadList().add(ciudad);
                paisNew = em.merge(paisNew);
            }
            for (Ruta rutaListNewRuta : rutaListNew) {
                if (!rutaListOld.contains(rutaListNewRuta)) {
                    Ciudad oldCiudadSalidaOfRutaListNewRuta = rutaListNewRuta.getCiudadSalida();
                    rutaListNewRuta.setCiudadSalida(ciudad);
                    rutaListNewRuta = em.merge(rutaListNewRuta);
                    if (oldCiudadSalidaOfRutaListNewRuta != null && !oldCiudadSalidaOfRutaListNewRuta.equals(ciudad)) {
                        oldCiudadSalidaOfRutaListNewRuta.getRutaList().remove(rutaListNewRuta);
                        oldCiudadSalidaOfRutaListNewRuta = em.merge(oldCiudadSalidaOfRutaListNewRuta);
                    }
                }
            }
            for (Ruta rutaList1NewRuta : rutaList1New) {
                if (!rutaList1Old.contains(rutaList1NewRuta)) {
                    Ciudad oldCiudadLlegadaOfRutaList1NewRuta = rutaList1NewRuta.getCiudadLlegada();
                    rutaList1NewRuta.setCiudadLlegada(ciudad);
                    rutaList1NewRuta = em.merge(rutaList1NewRuta);
                    if (oldCiudadLlegadaOfRutaList1NewRuta != null && !oldCiudadLlegadaOfRutaList1NewRuta.equals(ciudad)) {
                        oldCiudadLlegadaOfRutaList1NewRuta.getRutaList1().remove(rutaList1NewRuta);
                        oldCiudadLlegadaOfRutaList1NewRuta = em.merge(oldCiudadLlegadaOfRutaList1NewRuta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = ciudad.getCiudad();
                if (findCiudad(id) == null) {
                    throw new NonexistentEntityException("The ciudad with id " + id + " no longer exists.");
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
            Ciudad ciudad;
            try {
                ciudad = em.getReference(Ciudad.class, id);
                ciudad.getCiudad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ciudad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ruta> rutaListOrphanCheck = ciudad.getRutaList();
            for (Ruta rutaListOrphanCheckRuta : rutaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ciudad (" + ciudad + ") cannot be destroyed since the Ruta " + rutaListOrphanCheckRuta + " in its rutaList field has a non-nullable ciudadSalida field.");
            }
            List<Ruta> rutaList1OrphanCheck = ciudad.getRutaList1();
            for (Ruta rutaList1OrphanCheckRuta : rutaList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ciudad (" + ciudad + ") cannot be destroyed since the Ruta " + rutaList1OrphanCheckRuta + " in its rutaList1 field has a non-nullable ciudadLlegada field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Pais pais = ciudad.getPais();
            if (pais != null) {
                pais.getCiudadList().remove(ciudad);
                pais = em.merge(pais);
            }
            em.remove(ciudad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ciudad> findCiudadEntities() {
        return findCiudadEntities(true, -1, -1);
    }

    public List<Ciudad> findCiudadEntities(int maxResults, int firstResult) {
        return findCiudadEntities(false, maxResults, firstResult);
    }

    private List<Ciudad> findCiudadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ciudad.class));
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

    public Ciudad findCiudad(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ciudad.class, id);
        } finally {
            em.close();
        }
    }

    public int getCiudadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ciudad> rt = cq.from(Ciudad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
