package dal;

import java.math.BigDecimal;

/**
 * @author Alain Arsane
 */

public class Oeuvre {

    private Integer idOeuvre;
    private String titre;
    private BigDecimal prix;
    private Utilisateur utilisateur;

    public Oeuvre() {
    }

    public Oeuvre(Integer idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    public Integer getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(Integer idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }    
    
}
