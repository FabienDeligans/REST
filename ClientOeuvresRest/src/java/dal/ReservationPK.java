package dal;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * @author Alain Arsane
 */
public class ReservationPK {
    private Date dateReservation;
    private int idOeuvre;

    public ReservationPK() {
    }

    public ReservationPK(Date dateReservation, int idOeuvre) {
        this.dateReservation = dateReservation;
        this.idOeuvre = idOeuvre;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public int getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(int idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    
}
