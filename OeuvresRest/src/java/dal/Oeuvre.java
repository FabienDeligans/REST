package dal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Alain Arsane
 */
@Entity
@Table(name = "oeuvre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Oeuvre.findAll", query = "SELECT o FROM Oeuvre o"),
    @NamedQuery(name = "Oeuvre.findByIdOeuvre", query = "SELECT o FROM Oeuvre o WHERE o.idOeuvre = :idOeuvre"),
    @NamedQuery(name = "Oeuvre.findByTitre", query = "SELECT o FROM Oeuvre o WHERE o.titre = :titre"),
    @NamedQuery(name = "Oeuvre.findByPrix", query = "SELECT o FROM Oeuvre o WHERE o.prix = :prix"),
    @NamedQuery(name = "Oeuvre.findByUtilisateur", query = "SELECT o FROM Oeuvre o WHERE o.utilisateur = :utilisateur")})
public class Oeuvre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_oeuvre")
    private Integer idOeuvre;
    @Size(max = 250)
    @Column(name = "titre")
    private String titre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix")
    private BigDecimal prix;
    @JoinColumn(name = "id_utilisateur", referencedColumnName = "id_utilisateur")
    @ManyToOne(optional = false)
    private Utilisateur utilisateur;
    /*
        Les relations OneTomany ont été supprimées car elles ne
        doivent pas figuer dans les jSon produits essentiellement
        pour des raisons de performance
    
        @OneToMany(mappedBy = "oeuvre")    
        private List<Reservation> reservationList;    
    */
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

//    @XmlTransient
//    public List<Reservation> getReservationList() {
//        return reservationList;
//    }
//
//    public void setReservationList(List<Reservation> reservationList) {
//        this.reservationList = reservationList;
//    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOeuvre != null ? idOeuvre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oeuvre)) {
            return false;
        }
        Oeuvre other = (Oeuvre) object;
        if ((this.idOeuvre == null && other.idOeuvre != null) || (this.idOeuvre != null && !this.idOeuvre.equals(other.idOeuvre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dal.Oeuvre[ idOeuvre=" + idOeuvre + " ]";
    }
    
}
