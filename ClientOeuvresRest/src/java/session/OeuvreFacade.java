package session;

import dal.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 * @author Alain Arsane
 */
@Stateless
@LocalBean
public class OeuvreFacade {

    @EJB
    private UtilisateurFacade utilisateurF;

    /**
     * Lecture d'une occurrence d'oeuvre sur la clé primaire
     *
     * @param idOeuvre
     * @return instance d'Oeuvre
     * @throws Exception
     */
    public Oeuvre getOeuvreById(int idOeuvre) throws Exception {
        Oeuvre oeuvre = null;
        try {

            return oeuvre;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Liste de toutes les Oeuvre
     *
     * @return Collection d'oeuvre
     * @throws Exception
     */
    public List<Oeuvre> getOeuvres() throws Exception {
        List<Oeuvre> oeuvres = new ArrayList<Oeuvre>();
        try {

            return oeuvres;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Retourne la liste des Oeuvre d'un propriétaire
     *
     * @param idProprietaire Id de l'utilisateur propriétaire
     * @return Collection d'oeuvre
     * @throws Exception
     */
    public List<Oeuvre> getOeuvresByUtilisateur(int idProprietaire) throws Exception {
        List<Oeuvre> oeuvres = new ArrayList<Oeuvre>();
        try {

            return oeuvres;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Ajout d'une Oeuvre
     *
     * @param titre Titre de l'oeuvre
     * @param prix Prix de l'oeuvre
     * @param idUtilisateur id du propriétaire de l'oeuvre
     * @throws Exception
     */
    public void addOeuvre(String titre, BigDecimal prix, int idUtilisateur) throws Exception {
        try {

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Modification d'une oeuvre
     *
     * @param idOeuvre Id de l'oeuvre à modifier
     * @param titre titre de l'oeuvre à modifier
     * @param prix prix de l'oeuvre à modifier
     * @param idUtilisateur Id du propriétaire de l'oeuvre à modifier
     * @throws Exception
     */
    public void modifyOeuvre(int idOeuvre, String titre, BigDecimal prix, int idUtilisateur) throws Exception {
        try {

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Suppression d'une Oeuvre
     *
     * @param idOeuvre Id de l'oeuvre à supprimer
     * @throws Exception
     */
    public void deleteOeuvre(int idOeuvre) throws Exception {
        try {

        } catch (Exception e) {
            throw e;
        }
    }

}
