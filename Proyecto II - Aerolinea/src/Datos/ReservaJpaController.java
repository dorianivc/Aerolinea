/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Pago;
import Logica.Reserva;
import Logica.Ticket;
import Logica.Usuario;
import Logica.Viaje;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Monica
 */
public class ReservaJpaController implements Serializable {

    public ReservaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reserva reserva) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pago pago = reserva.getPago();
            if (pago != null) {
                pago = em.getReference(pago.getClass(), pago.getPago());
                reserva.setPago(pago);
            }
            Ticket ticket = reserva.getTicket();
            if (ticket != null) {
                ticket = em.getReference(ticket.getClass(), ticket.getTicket());
                reserva.setTicket(ticket);
            }
            Usuario usuario = reserva.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getUsuario());
                reserva.setUsuario(usuario);
            }
            Viaje viaje = reserva.getViaje();
            if (viaje != null) {
                viaje = em.getReference(viaje.getClass(), viaje.getViaje());
                reserva.setViaje(viaje);
            }
            em.persist(reserva);
            if (pago != null) {
                pago.getReservaList().add(reserva);
                pago = em.merge(pago);
            }
            if (ticket != null) {
                ticket.getReservaList().add(reserva);
                ticket = em.merge(ticket);
            }
            if (usuario != null) {
                usuario.getReservaList().add(reserva);
                usuario = em.merge(usuario);
            }
            if (viaje != null) {
                viaje.getReservaList().add(reserva);
                viaje = em.merge(viaje);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reserva reserva) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reserva persistentReserva = em.find(Reserva.class, reserva.getReserva());
            Pago pagoOld = persistentReserva.getPago();
            Pago pagoNew = reserva.getPago();
            Ticket ticketOld = persistentReserva.getTicket();
            Ticket ticketNew = reserva.getTicket();
            Usuario usuarioOld = persistentReserva.getUsuario();
            Usuario usuarioNew = reserva.getUsuario();
            Viaje viajeOld = persistentReserva.getViaje();
            Viaje viajeNew = reserva.getViaje();
            if (pagoNew != null) {
                pagoNew = em.getReference(pagoNew.getClass(), pagoNew.getPago());
                reserva.setPago(pagoNew);
            }
            if (ticketNew != null) {
                ticketNew = em.getReference(ticketNew.getClass(), ticketNew.getTicket());
                reserva.setTicket(ticketNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getUsuario());
                reserva.setUsuario(usuarioNew);
            }
            if (viajeNew != null) {
                viajeNew = em.getReference(viajeNew.getClass(), viajeNew.getViaje());
                reserva.setViaje(viajeNew);
            }
            reserva = em.merge(reserva);
            if (pagoOld != null && !pagoOld.equals(pagoNew)) {
                pagoOld.getReservaList().remove(reserva);
                pagoOld = em.merge(pagoOld);
            }
            if (pagoNew != null && !pagoNew.equals(pagoOld)) {
                pagoNew.getReservaList().add(reserva);
                pagoNew = em.merge(pagoNew);
            }
            if (ticketOld != null && !ticketOld.equals(ticketNew)) {
                ticketOld.getReservaList().remove(reserva);
                ticketOld = em.merge(ticketOld);
            }
            if (ticketNew != null && !ticketNew.equals(ticketOld)) {
                ticketNew.getReservaList().add(reserva);
                ticketNew = em.merge(ticketNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getReservaList().remove(reserva);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getReservaList().add(reserva);
                usuarioNew = em.merge(usuarioNew);
            }
            if (viajeOld != null && !viajeOld.equals(viajeNew)) {
                viajeOld.getReservaList().remove(reserva);
                viajeOld = em.merge(viajeOld);
            }
            if (viajeNew != null && !viajeNew.equals(viajeOld)) {
                viajeNew.getReservaList().add(reserva);
                viajeNew = em.merge(viajeNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reserva.getReserva();
                if (findReserva(id) == null) {
                    throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.");
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
            Reserva reserva;
            try {
                reserva = em.getReference(Reserva.class, id);
                reserva.getReserva();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.", enfe);
            }
            Pago pago = reserva.getPago();
            if (pago != null) {
                pago.getReservaList().remove(reserva);
                pago = em.merge(pago);
            }
            Ticket ticket = reserva.getTicket();
            if (ticket != null) {
                ticket.getReservaList().remove(reserva);
                ticket = em.merge(ticket);
            }
            Usuario usuario = reserva.getUsuario();
            if (usuario != null) {
                usuario.getReservaList().remove(reserva);
                usuario = em.merge(usuario);
            }
            Viaje viaje = reserva.getViaje();
            if (viaje != null) {
                viaje.getReservaList().remove(reserva);
                viaje = em.merge(viaje);
            }
            em.remove(reserva);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reserva> findReservaEntities() {
        return findReservaEntities(true, -1, -1);
    }

    public List<Reserva> findReservaEntities(int maxResults, int firstResult) {
        return findReservaEntities(false, maxResults, firstResult);
    }

    private List<Reserva> findReservaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reserva.class));
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

    public Reserva findReserva(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reserva.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reserva> rt = cq.from(Reserva.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
