package dal;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Alain Arsane
 */
@Entity
@Table(name = "utilisateur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilisateur.findAll", query = "SELECT a FROM Utilisateur a"),
    @NamedQuery(name = "Utilisateur.findByIdUtilisateur", query = "SELECT a FROM Utilisateur a WHERE a.idUtilisateur = :idUtilisateur"),
    @NamedQuery(name = "Utilisateur.findByNomUtilisateur", query = "SELECT a FROM Utilisateur a WHERE a.nomUtilisateur = :nomUtilisateur"),
    @NamedQuery(name = "Utilisateur.findByPrenomUtilisateur", query = "SELECT a FROM Utilisateur a WHERE a.prenomUtilisateur = :prenomUtilisateur"),
    @NamedQuery(name = "Utilisateur.findByLogin", query = "SELECT a FROM Utilisateur a WHERE a.login = :login"),
    @NamedQuery(name = "Utilisateur.findByPwd", query = "SELECT a FROM Utilisateur a WHERE a.pwd = :pwd"),
    @NamedQuery(name = "Utilisateur.findByRole", query = "SELECT a FROM Utilisateur a WHERE a.role = :role")})
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_utilisateur")
    private Integer idUtilisateur;
    @Size(max = 50)
    @Column(name = "nom_utilisateur")
    private String nomUtilisateur;
    @Size(max = 50)
    @Column(name = "prenom_utilisateur")
    private String prenomUtilisateur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "pwd")
    private String pwd;
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "role")
    private String role;    
    /*
        Les relations OneTomany ont été supprimées car elles ne
        doivent pas figuer dans les jSon produits essentiellement
        pour des raisons de performance
    */
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilisateur")
//    private List<Reservation> reservationList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utilisateur")
//    private List<Oeuvre> oeuvreList;
    
    public Utilisateur() {
    }

    public Utilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Utilisateur(Integer idUtilisateur, String login, String pwd) {
        this.idUtilisateur = idUtilisateur;
        this.login = login;
        this.pwd = pwd;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }    

//    @XmlTransient
//    public List<Reservation> getReservationList() {
//        return reservationList;
//    }
//
//    public void setReservationList(List<Reservation> reservationList) {
//        this.reservationList = reservationList;
//    }
//    
//    @XmlTransient
//    public List<Oeuvre> getOeuvreList() {
//        return oeuvreList;
//    }
//
//    public void setOeuvreList(List<Oeuvre> oeuvreList) {
//        this.oeuvreList = oeuvreList;
//    }    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUtilisateur != null ? idUtilisateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.idUtilisateur == null && other.idUtilisateur != null) || (this.idUtilisateur != null && !this.idUtilisateur.equals(other.idUtilisateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dal.Utilisateur[ idUtilisateur=" + idUtilisateur + " ]";
    }
    
}
