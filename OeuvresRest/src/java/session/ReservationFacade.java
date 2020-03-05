package session;

import dal.Reservation;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import dal.*;
import java.util.List;
import javax.ejb.EJB;
import outils.Utilitaire;
import javax.json.*;

/**
 * @author Alain Arsane
 */
@Stateless
@LocalBean
public class ReservationFacade {

    @PersistenceContext(unitName = "OeuvresRest-ejbPU")
    private EntityManager em;
    @EJB
    private UtilisateurFacade utilisateurF;
    @EJB
    private OeuvreFacade oeuvreF;

    /**
     * Lecture d'une Reservation sur clé primaire
     * @param dateReservation date de la réservation à lire
     * @param idOeuvre id de l'oeuvre de la réservation à lire
     * @return Reservation
     * @throws Exception
     */
    public Reservation getReservationById(Date dateReservation, int idOeuvre) throws Exception {
        try {
            Query query = em.createNamedQuery("Reservation.findByDateReservationIdOeuvre");
            query.setParameter("dateReservation", dateReservation, TemporalType.DATE);
            query.setParameter("idOeuvre", idOeuvre);
            return (Reservation) query.getSingleResult();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Insère une Reservation à l'état En attente
     * @param Reservation objet Reservation complet
     * @throws Exception
     */
    public void addReservation(Reservation reservationE) throws Exception {
        try {
            em.persist(reservationE);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Ajouter une réservation
     * @param reservation Json contenant dateReservation, idOeuvre, idUtilisateur
     * @throws Exception 
     */
    public void addReservation(JsonObject reservationJson) throws Exception {
        try {
            int idOeuvre = reservationJson.getInt("idOeuvre");
            int idUtilisateur = reservationJson.getInt("idUtilisateur");
            String dr = reservationJson.getString("dateReservation");
            Date dateReservation = Utilitaire.StrToDate(dr, "yyyy-MM-dd");
            this.addReservation(dateReservation, idOeuvre, idUtilisateur);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Insère une Reservation à l'état En attente
     * @param dateReservation Date de la réservation
     * @param idOeuvre id de l'oeuvre réservée
     * @param idAdherent id de l'utilisateur qui a réservé
     * @throws Exception
     */
    public void addReservation(Date dateReservation, int idOeuvre, int idUtilisateur) throws Exception {
        try {
            ReservationPK reservationPKE = new ReservationPK(dateReservation, idOeuvre);
            Reservation reservationE = new Reservation(reservationPKE, "En attente");
            Utilisateur utilisateurE = utilisateurF.getUtilisateurById(idUtilisateur);
            reservationE.setUtilisateur(utilisateurE);
            Oeuvre oeuvreE = oeuvreF.getOeuvreById(idOeuvre);
            reservationE.setOeuvre(oeuvreE);
            em.persist(reservationE);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Retourne la liste de toutes les Reservation
     * @return Collection de Reservation
     * @throws Exception
     */
    public List<Reservation> getReservations() throws Exception {
        List<Reservation> lesReservationsE = null;
        try {
            Query query = em.createNamedQuery("Reservation.findAll");
            lesReservationsE = query.getResultList();
            return lesReservationsE;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Passe une réservation de l'état En attente à Confirmée
     * @param reservation Json contenant dateReservation, idOeuvre
     * @throws Exception
     */
    public void modifyReservation(JsonObject reservationJson) throws Exception {
        Reservation reservationE;
        try {
            int idOeuvre = reservationJson.getInt("idOeuvre");
            String dr = reservationJson.getString("dateReservation");
            Date dateReservation = Utilitaire.StrToDate(dr, "yyyy-MM-dd");            
            reservationE = getReservationById(dateReservation, idOeuvre);
            reservationE.setStatut("Confirmée");
            em.merge(reservationE);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Supression d'une réservation
     * @param dateReservation Date de la réservation à supprimer
     * @param idOeuvre Id de l'oeuvre réservée
     * @throws Exception
     */
    public void deleteReservation(Date dateReservation, int idOeuvre) throws Exception {
        try {
            Reservation reservationE = getReservationById(dateReservation, idOeuvre);
            em.remove(reservationE);
        } catch (Exception e) {
            throw e;
        }
    }
}
