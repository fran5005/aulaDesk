package com.alcoleagarridofran.auladeskv3.model;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 *
 * @author franc
 */
@Embeddable
public class ProfesorMateriaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_profesor")
    private int idProfesor;
    @Basic(optional = false)
    @Column(name = "id_materia")
    private int idMateria;

    public ProfesorMateriaPK() {
    }

    public ProfesorMateriaPK(int idProfesor, int idMateria) {
        this.idProfesor = idProfesor;
        this.idMateria = idMateria;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProfesor;
        hash += (int) idMateria;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfesorMateriaPK)) {
            return false;
        }
        ProfesorMateriaPK other = (ProfesorMateriaPK) object;
        if (this.idProfesor != other.idProfesor) {
            return false;
        }
        if (this.idMateria != other.idMateria) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject4.asdasd.ProfesorMateriaPK[ idProfesor=" + idProfesor + ", idMateria=" + idMateria + " ]";
    }
}

