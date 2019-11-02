/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.NonexistentEntityException;
import Datos.exceptions.PreexistingEntityException;
import Logica.Pago;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.TipodePago;
import Logica.Reserva;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Monica
 */
public class PagoJpaController implements Serializable {

    public PagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pago pago) throws PreexistingEntityException, Exception {
        if (pago.getReservaList() == null) {
            pago.setReservaList(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipodePago tipoPagocodigopago = pago.getTipoPagocodigopago();
            if (tipoPagocodigopago != null) {
                tipoPagocodigopago = em.getReference(tipoPagocodigopago.getClass(), tipoPagocodigopago.getTipoDePago());
                pago.setTipoPagocodigopago(tipoPagocodigopago);
            }
            List<Reserva> attachedReservaList = new ArrayList<Reserva>();
            for (Reserva reservaListReservaToAttach : pago.getReservaList()) {
                reservaListReservaToAttach = em.getReference(reservaListReservaToAttach.getClass(), reservaListReservaToAttach.getReserva());
                attachedReservaList.add(reservaListReservaToAttach);
            }
            pago.setReservaList(attachedReservaList);
            em.persist(pago);
            if (tipoPagocodigopago != null) {
                tipoPagocodigopago.getPagoList().add(pago);
                tipoPagocodigopago = em.merge(tipoPagocodigopago);
            }
            for (Reserva reservaListReserva : pago.getReservaList()) {
                Pago oldPagoOfReservaListReserva = reservaListReserva.getPago();
                reservaListReserva.setPago(pago);
                reservaListReserva = em.merge(reservaListReserva);
                if (oldPagoOfReservaListReserva != null) {
                    oldPagoOfReservaListReserva.getReservaList().remove(reservaListReserva);
                    oldPagoOfReservaListReserva = em.merge(oldPagoOfReservaListReserva);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPago(pago.getPago()) != null) {
                throw new PreexistingEntityException("Pago " + pago + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pago pago) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pago persistentPago = em.find(Pago.class, pago.getPago());
            TipodePago tipoPagocodigopagoOld = persistentPago.getTipoPagocodigopago();
            TipodePago tipoPagocodigopagoNew = pago.getTipoPagocodigopago();
            List<Reserva> reservaListOld = persistentPago.getReservaList();
            List<Reserva> reservaListNew = pago.getReservaList();
            if (tipoPagocodigopagoNew != null) {
                tipoPagocodigopagoNew = em.getReference(tipoPagocodigopagoNew.getClass(), tipoPagocodigopagoNew.getTipoDePago());
                pago.setTipoPagocodigopago(tipoPagocodigopagoNew);
            }
            List<Reserva> attachedReservaListNew = new ArrayList<Reserva>();
            for (Reserva reservaListNewReservaToAttach : reservaListNew) {
                reservaListNewReservaToAttach = em.getReference(reservaListNewReservaToAttach.getClass(), reservaListNewReservaToAttach.getReserva());
                attachedReservaListNew.add(reservaListNewReservaToAttach);
            }
            reservaListNew = attachedReservaListNew;
            pago.setReservaList(reservaListNew);
            pago = em.merge(pago);
            if (tipoPagocodigopagoOld != null && !tipoPagocodigopagoOld.equals(tipoPagocodigopagoNew)) {
                tipoPagocodigopagoOld.getPagoList().remove(pago);
                tipoPagocodigopagoOld = em.merge(tipoPagocodigopagoOld);
            }
            if (tipoPagocodigopagoNew != null && !tipoPagocodigopagoNew.equals(tipoPagocodigopagoOld)) {
                tipoPagocodigopagoNew.getPagoList().add(pago);
                tipoPagocodigopagoNew = em.merge(tipoPagocodigopagoNew);
            }
            for (Reserva reservaListOldReserva : reservaListOld) {
                if (!reservaListNew.contains(reservaListOldReserva)) {
                    reservaListOldReserva.setPago(null);
                    reservaListOldReserva = em.merge(reservaListOldReserva);
                }
            }
            for (Reserva reservaListNewReserva : reservaListNew) {
                if (!reservaListOld.contains(reservaListNewReserva)) {
                    Pago oldPagoOfReservaListNewReserva = reservaListNewReserva.getPago();
                    reservaListNewReserva.setPago(pago);
                    reservaListNewReserva = em.merge(reservaListNewReserva);
                    if (oldPagoOfReservaListNewReserva != null && !oldPagoOfReservaListNewReserva.equals(pago)) {
                        oldPagoOfReservaListNewReserva.getReservaList().remove(reservaListNewReserva);
                        oldPagoOfReservaListNewReserva = em.merge(oldPagoOfReservaListNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pago.getPago();
                if (findPago(id) == null) {
                    throw new NonexistentEntityException("The pago with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pago pago;
            try {
                pago = em.getReference(Pago.class, id);
                pago.getPago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pago with id " + id + " no longer exists.", enfe);
            }
            TipodePago tipoPagocodigopago = pago.getTipoPagocodigopago();
            if (tipoPagocodigopago != null) {
                tipoPagocodigopago.getPagoList().remove(pago);
                tipoPagocodigopago = em.merge(tipoPagocodigopago);
            }
            List<Reserva> reservaList = pago.getReservaList();
            for (Reserva reservaListReserva : reservaList) {
                reservaListReserva.setPago(null);
                reservaListReserva = em.merge(reservaListReserva);
            }
            em.remove(pago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pago> findPagoEntities() {
        return findPagoEntities(true, -1, -1);
    }

    public List<Pago> findPagoEntities(int maxResults, int firstResult) {
        return findPagoEntities(false, maxResults, firstResult);
    }

    private List<Pago> findPagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pago.class));
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

    public Pago findPago(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pago.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pago> rt = cq.from(Pago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
