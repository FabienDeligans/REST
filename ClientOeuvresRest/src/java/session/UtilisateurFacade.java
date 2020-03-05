package session;

import dal.*;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import outils.Utilitaire;

/**
 * @author Alain Arsane
 */
@Stateless
@LocalBean
public class UtilisateurFacade {

    /**
     * Connexion d'un Utilisateur
     * On récupère un objet Utilisateur sur son login qui est 
     * unique et on compare le mot de passe fourni à celui
     * qui a été ramené
     * @param login Login de l'Utilisateur
     * @param pwd Mot de passe de l'Utilisateur
     * @return instance d'Utilisateur
     * @throws Exception 
     */
    public Utilisateur getUtilisateurByLogin(String login, String pwd) throws Exception {
        Utilisateur utilisateurE = null;
        try {

            return utilisateurE;
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * Lecture d'un Utilisateur sur son Id
     *
     * @param idUtilisateur Id de l'utilisateur à lire
     * @return instance de Utilisateur
     * @throws Exception
     */
    public Utilisateur getUtilisateurById(int idUtilisateur) throws Exception {
        Utilisateur utilisateurE = null;
        try {

            return utilisateurE;
        } catch (Exception e) {
            throw e;
        }
    }


}
