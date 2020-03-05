package session;

import dal.*;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
//import org.hibernate.Hibernate;
import outils.Utilitaire;

/**
 * @author Alain Arsane
 */
@Stateless
@LocalBean
public class UtilisateurFacade {
    @PersistenceContext(unitName="OeuvresRest-ejbPU")
    private EntityManager em;
    
    /**
     * Retourne un utilisateur lu sur son login
     * @param login login de l'utilisateur à lire
     * @return Utilisateur
     * @throws Exception 
     */
    public Utilisateur getUtilisateurByLogin(String login) throws Exception{
        Utilisateur utilisateurE = null;
        try{
            Query query = em.createNamedQuery("Utilisateur.findByLogin");
            query.setParameter("login", login);
            try {
                utilisateurE = (Utilisateur) query.getSingleResult();
            } catch (Exception e) {
                String msg = Utilitaire.getExceptionCause(e);
                if (!msg.contains("No entity found for query")) {
                    throw e;
                }
            }            
            return utilisateurE;
        }catch(Exception e){
            throw e;
        }
    }
      
    
    /**
     * Lecture d'un Utilisateur sur son Id
     * @param idUtilisateur
     * @return instance de Utilisateur
     * @throws Exception 
     */
    public Utilisateur getUtilisateurById(int idUtilisateur) throws Exception{
        Utilisateur utilisateurE = null;
        try{
            utilisateurE = em.find(Utilisateur.class, idUtilisateur);
            return utilisateurE;
        }catch(Exception e){
            throw e;
        }
    }
    
    /**
     * Liste de tousd les utilisateurs
     * @returnCollectio d'Utilisateur
     * @throws Exception 
     */
    public List<Utilisateur> getUtilisateurs() throws Exception{
        List<Utilisateur> lesUtilisateursE = null;
        try{
            Query query = em.createNamedQuery("Utilisateur.findAll");
            lesUtilisateursE = query.getResultList();
            return lesUtilisateursE;
        }catch(Exception e){
            throw e;
        }
    }
}
