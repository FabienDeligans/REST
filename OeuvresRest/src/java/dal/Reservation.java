package dal;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Alain Arsane
 */
@Entity
@Table(name = "reservation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r"),
    @NamedQuery(name = "Reservation.findByDateReservation", query = "SELECT r FROM Reservation r WHERE r.reservationPK.dateReservation = :dateReservation"),
    @NamedQuery(name = "Reservation.findByIdOeuvre", query = "SELECT r FROM Reservation r WHERE r.reservationPK.idOeuvre = :idOeuvre"),
    @NamedQuery(name = "Reservation.findByStatut", query = "SELECT r FROM Reservation r WHERE r.statut = :statut"),
    @NamedQuery(name = "Reservation.findByDateReservationIdOeuvre", query = "SELECT r FROM Reservation r WHERE r.reservationPK.dateReservation = :dateReservation "
            + "AND r.reservationPK.idOeuvre = :idOeuvre")})
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReservationPK reservationPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "statut")
    private String statut;
    @JoinColumn(name = "id_utilisateur", referencedColumnName = "id_utilisateur")
    @ManyToOne(optional = false)
    private Utilisateur utilisateur;
    @JoinColumn(name = "id_oeuvre", referencedColumnName = "id_oeuvre", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Oeuvre oeuvre;

    public Reservation() {
    }

    public Reservation(ReservationPK reservationPK) {
        this.reservationPK = reservationPK;
    }

    public Reservation(ReservationPK reservationPK, String statut) {
        this.reservationPK = reservationPK;
        this.statut = statut;
    }

    public Reservation(Date dateReservation, int idOeuvre) {
        this.reservationPK = new ReservationPK(dateReservation, idOeuvre);
    }

    public ReservationPK getReservationPK() {
        return reservationPK;
    }

    public void setReservationPK(ReservationPK reservationPK) {
        this.reservationPK = reservationPK;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservationPK != null ? reservationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.reservationPK == null && other.reservationPK != null) || (this.reservationPK != null && !this.reservationPK.equals(other.reservationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dal.Reservation[ reservationPK=" + reservationPK + " ]";
    }
    
}
