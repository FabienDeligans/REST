package session;

import dal.*;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import java.util.List;
import javax.ejb.EJB;
import javax.json.*;

/**
 * @author Alain Arsane
 */
@Stateless
@LocalBean
public class ReservationFacade {

    @EJB
    private UtilisateurFacade utilisateurF;
    @EJB
    private OeuvreFacade oeuvreF;


    /**
     * Insère une Reservation à l'état En attente
     *
     * @param dateReservation Date de la réservation
     * @param idOeuvre id de l'oeuvre réservée
     * @param idUtilisateur id de l'adhérent qui a réservé
     * @throws Exception
     */
    public void addReservationV1(Date dateReservation, int idOeuvre, int idUtilisateur) throws Exception {
        try {

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Ajout d'une Reservation Version 2
     * @param dateReservation Date de la réservation
     * @param idOeuvre Id de l'oeuvre réservée
     * @param idUtilisateur Id de l'utilisateur qui a réservé
     * @throws Exception 
     */
    public void addReservationV2(String dateReservation, int idOeuvre, int idUtilisateur) throws Exception {
        try {

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Ajout d'une Reservation Version 3
     * @param dateReservation Date de la réservation
     * @param idOeuvre Id de l'oeuvre réservée
     * @param idUtilisateur Id de l'utilisateur qui a réservé
     * @throws Exception 
     */    
    public void addReservationV3(String dateReservation, int idOeuvre, int idUtilisateur) throws Exception {
        try {

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Retourne la liste de toutes les Reservation
     *
     * @return Collection de Reservation
     * @throws Exception
     */
    public List<Reservation> getReservations() throws Exception {
        List<Reservation> lesReservations = null;
        try {

            return lesReservations;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Passe une réservation de l'état En attente à Confirmée
     *
     * @param dateReservation Date de la réservation à confirmer
     * @param idOeuvre Id de l'oeuvre réservée
     * @throws Exception
     */
    public void modifyReservation(String dateReservation, int idOeuvre) throws Exception {
        try {

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Supression d'une réservation
     *
     * @param dateReservation Date de la réservation à supprimer
     * @param idOeuvre Id de l'oeuvre réservée
     * @throws Exception
     */
    public void deleteReservation(String dateReservation, int idOeuvre) throws Exception {
        try {

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Encapsulation de propriétés dans un jSon
     * @param dateReservation
     * @param idOeuvre
     * @param idUtilisateur
     * @return instance de JsonObject
     */
    public JsonObject getObjectJson(String dateReservation, int idOeuvre, int idUtilisateur) {

        return null;
    }
}
