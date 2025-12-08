package com.alcoleagarridofran.auladeskv3.model;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;

/**
 *
 * @author franc
 */
@Entity
@Table(name = "profesor_materia")
@NamedQueries({
        @NamedQuery(name = "ProfesorMateria.findAll", query = "SELECT p FROM ProfesorMateria p"),
        @NamedQuery(name = "ProfesorMateria.findByIdProfesor", query = "SELECT p FROM ProfesorMateria p WHERE p.profesorMateriaPK.idProfesor = :idProfesor"),
        @NamedQuery(name = "ProfesorMateria.findByIdMateria", query = "SELECT p FROM ProfesorMateria p WHERE p.profesorMateriaPK.idMateria = :idMateria")})
public class ProfesorMateria implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProfesorMateriaPK profesorMateriaPK;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profesorMateria")
    private List<Matricula> matriculaList;
    @JoinColumn(name = "id_materia", referencedColumnName = "id_materia", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Materia materia;
    @JoinColumn(name = "id_profesor", referencedColumnName = "id_profesor", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Profesor profesor;

    public ProfesorMateria() {
    }

    public ProfesorMateria(ProfesorMateriaPK profesorMateriaPK) {
        this.profesorMateriaPK = profesorMateriaPK;
    }

    public ProfesorMateria(int idProfesor, int idMateria) {
        this.profesorMateriaPK = new ProfesorMateriaPK(idProfesor, idMateria);
    }

    public ProfesorMateriaPK getProfesorMateriaPK() {
        return profesorMateriaPK;
    }

    public void setProfesorMateriaPK(ProfesorMateriaPK profesorMateriaPK) {
        this.profesorMateriaPK = profesorMateriaPK;
    }

    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (profesorMateriaPK != null ? profesorMateriaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfesorMateria)) {
            return false;
        }
        ProfesorMateria other = (ProfesorMateria) object;
        if ((this.profesorMateriaPK == null && other.profesorMateriaPK != null) || (this.profesorMateriaPK != null && !this.profesorMateriaPK.equals(other.profesorMateriaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject4.asdasd.ProfesorMateria[ profesorMateriaPK=" + profesorMateriaPK + " ]";
    }

}

