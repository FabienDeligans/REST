package session;

import dal.*;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import outils.Utilitaire;

/**
 * @author Alain Arsane
 */
@Stateless
@LocalBean
//Gestion des transactions multitables : ici le conteneur gère la transaction (commit / rollback)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OeuvreFacade {

    @PersistenceContext(unitName = "OeuvresRest-ejbPU")
    private EntityManager em;

    /**
     * Lecture d'une occurrence d'oeuvre sur la clé primaire
     * @param idOeuvre
     * @return Oeuvre
     * @throws Exception
     */
    public Oeuvre getOeuvreById(int idOeuvre) throws Exception {
        try {
            return em.find(Oeuvre.class, idOeuvre);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Retourne une liste d'objets Oeuvre
     * @return Collection d'Oeuvre
     * @throws Exception
     */
    public List<Oeuvre> getOeuvres() throws Exception {
        try {
            return (em.createNamedQuery("Oeuvre.findAll").getResultList());
        } catch (Exception e) {
            throw e;
        }
    }
    
    /**
     * Retourne la liste des oeuvres d'un propriétaire
     * @param Utilisateur propriétaire dont on veut la liste des oeuvres
     * @return Collection d'Oeuvre
     * @throws Exception
     */
    public List<Oeuvre> getOeuvresByUtilisateur(Utilisateur utilisateurE) throws Exception {
        List<Oeuvre> oeuvres = null;
        try {
            Query query = em.createNamedQuery("Oeuvre.findByUtilisateur");
            query.setParameter("utilisateur", utilisateurE);
            try {
                oeuvres = (List<Oeuvre>) query.getResultList();
            } catch (Exception e) {
                String msg = Utilitaire.getExceptionCause(e);
                if (!msg.contains("No entity found for query")) {
                    throw e;
                }
            }
            return oeuvres;
        } catch (Exception e) {
            throw e;
        }
    }    
    
    /**
     * Ajout d'une Oeuvre
     * @param Oeuvre objet Oeuvre à enregistrer
     * @throws Exception 
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addOeuvre(Oeuvre oeuvre) throws Exception{
        try{
            em.persist(oeuvre);
        }catch(Exception e){
            throw e;
        }
    }    
    
    /**
     * Modification d'une oeuvre
     * @param idOeuvre Id de l'oeuvre à modifier
     * @param titre titre de l'oeuvre à modifier
     * @param prix prix de l'oeuvre à modifier
     * @param idUtilisateur Id du propriétaire de l'oeuvre à modifier
     * @throws Exception 
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void modifyOeuvre(int idOeuvre, String titre, BigDecimal prix, int idUtilisateur) throws Exception{
        try{
            Oeuvre oeuvreE = getOeuvreById(idOeuvre);
            oeuvreE.setTitre(titre);
            oeuvreE.setPrix(prix);
            em.merge(oeuvreE);
        }catch(Exception e){
            throw e;
        }
    }    
    
    /**
     * Suppression d'une Oeuvre
     * @param idOeuvre Id de l'oeuvre à supprimer
     * @throws Exception 
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteOeuvre(int idOeuvre) throws Exception{
        try{
            Oeuvre oeuvreE = getOeuvreById(idOeuvre);
            em.remove(oeuvreE);
        }catch(Exception e){
            throw e;
        }
    }    

}
