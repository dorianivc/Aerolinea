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
import Logica.Pago;
import Logica.TipodePago;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Monica
 */
public class TipodePagoJpaController implements Serializable {

    public TipodePagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipodePago tipodePago) throws PreexistingEntityException, Exception {
        if (tipodePago.getPagoList() == null) {
            tipodePago.setPagoList(new ArrayList<Pago>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pago> attachedPagoList = new ArrayList<Pago>();
            for (Pago pagoListPagoToAttach : tipodePago.getPagoList()) {
                pagoListPagoToAttach = em.getReference(pagoListPagoToAttach.getClass(), pagoListPagoToAttach.getPago());
                attachedPagoList.add(pagoListPagoToAttach);
            }
            tipodePago.setPagoList(attachedPagoList);
            em.persist(tipodePago);
            for (Pago pagoListPago : tipodePago.getPagoList()) {
                TipodePago oldTipoPagocodigopagoOfPagoListPago = pagoListPago.getTipoPagocodigopago();
                pagoListPago.setTipoPagocodigopago(tipodePago);
                pagoListPago = em.merge(pagoListPago);
                if (oldTipoPagocodigopagoOfPagoListPago != null) {
                    oldTipoPagocodigopagoOfPagoListPago.getPagoList().remove(pagoListPago);
                    oldTipoPagocodigopagoOfPagoListPago = em.merge(oldTipoPagocodigopagoOfPagoListPago);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipodePago(tipodePago.getTipoDePago()) != null) {
                throw new PreexistingEntityException("TipodePago " + tipodePago + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipodePago tipodePago) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipodePago persistentTipodePago = em.find(TipodePago.class, tipodePago.getTipoDePago());
            List<Pago> pagoListOld = persistentTipodePago.getPagoList();
            List<Pago> pagoListNew = tipodePago.getPagoList();
            List<String> illegalOrphanMessages = null;
            for (Pago pagoListOldPago : pagoListOld) {
                if (!pagoListNew.contains(pagoListOldPago)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pago " + pagoListOldPago + " since its tipoPagocodigopago field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Pago> attachedPagoListNew = new ArrayList<Pago>();
            for (Pago pagoListNewPagoToAttach : pagoListNew) {
                pagoListNewPagoToAttach = em.getReference(pagoListNewPagoToAttach.getClass(), pagoListNewPagoToAttach.getPago());
                attachedPagoListNew.add(pagoListNewPagoToAttach);
            }
            pagoListNew = attachedPagoListNew;
            tipodePago.setPagoList(pagoListNew);
            tipodePago = em.merge(tipodePago);
            for (Pago pagoListNewPago : pagoListNew) {
                if (!pagoListOld.contains(pagoListNewPago)) {
                    TipodePago oldTipoPagocodigopagoOfPagoListNewPago = pagoListNewPago.getTipoPagocodigopago();
                    pagoListNewPago.setTipoPagocodigopago(tipodePago);
                    pagoListNewPago = em.merge(pagoListNewPago);
                    if (oldTipoPagocodigopagoOfPagoListNewPago != null && !oldTipoPagocodigopagoOfPagoListNewPago.equals(tipodePago)) {
                        oldTipoPagocodigopagoOfPagoListNewPago.getPagoList().remove(pagoListNewPago);
                        oldTipoPagocodigopagoOfPagoListNewPago = em.merge(oldTipoPagocodigopagoOfPagoListNewPago);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tipodePago.getTipoDePago();
                if (findTipodePago(id) == null) {
                    throw new NonexistentEntityException("The tipodePago with id " + id + " no longer exists.");
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
            TipodePago tipodePago;
            try {
                tipodePago = em.getReference(TipodePago.class, id);
                tipodePago.getTipoDePago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipodePago with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Pago> pagoListOrphanCheck = tipodePago.getPagoList();
            for (Pago pagoListOrphanCheckPago : pagoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipodePago (" + tipodePago + ") cannot be destroyed since the Pago " + pagoListOrphanCheckPago + " in its pagoList field has a non-nullable tipoPagocodigopago field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipodePago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipodePago> findTipodePagoEntities() {
        return findTipodePagoEntities(true, -1, -1);
    }

    public List<TipodePago> findTipodePagoEntities(int maxResults, int firstResult) {
        return findTipodePagoEntities(false, maxResults, firstResult);
    }

    private List<TipodePago> findTipodePagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipodePago.class));
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

    public TipodePago findTipodePago(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipodePago.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipodePagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipodePago> rt = cq.from(TipodePago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
