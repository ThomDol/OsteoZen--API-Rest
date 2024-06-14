package org.gestion_patient.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class PratiquerId implements java.io.Serializable {
    private static final long serialVersionUID = 7767132151718580035L;
    @Column(name = "id_sport", nullable = false)
    private int idSport;

    @Column(name = "id_patient", nullable = false)
    private int idPatient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PratiquerId entity = (PratiquerId) o;
        return Objects.equals(this.idSport, entity.idSport) &&
                Objects.equals(this.idPatient, entity.idPatient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSport, idPatient);
    }

}