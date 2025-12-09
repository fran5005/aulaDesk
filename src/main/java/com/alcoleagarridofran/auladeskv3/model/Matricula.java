package com.alcoleagarridofran.auladeskv3.model;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.persistence.*;

/**
 *
 * @author franc
 */
@Entity
@Table(name = "matricula")
@NamedQueries({
        @NamedQuery(name = "Matricula.findAll", query = "SELECT m FROM Matricula m"),
        @NamedQuery(name = "Matricula.findByIdMatricula", query = "SELECT m FROM Matricula m WHERE m.idMatricula = :idMatricula"),
        @NamedQuery(name = "Matricula.findByCurso", query = "SELECT m FROM Matricula m WHERE m.curso = :curso"),
        @NamedQuery(name = "Matricula.findByConvocatoria", query = "SELECT m FROM Matricula m WHERE m.convocatoria = :convocatoria"),
        @NamedQuery(name = "Matricula.findByNota", query = "SELECT m FROM Matricula m WHERE m.nota = :nota")})
public class Matricula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_matricula")
    private Integer idMatricula;
    @Basic(optional = false)
    @Column(name = "curso")
    private String curso;
    @Basic(optional = false)
    @Column(name = "convocatoria")
    private int convocatoria;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "nota")
    private BigDecimal nota;
    @JoinColumn(name = "nre", referencedColumnName = "nre")
    @ManyToOne(optional = false)
    private Alumno nre;
    @JoinColumns({
            @JoinColumn(name = "id_profesor", referencedColumnName = "id_profesor"),
            @JoinColumn(name = "id_materia", referencedColumnName = "id_materia")})
    @ManyToOne(optional = false)
    private ProfesorMateria profesorMateria;

    public Matricula() {
    }

    public Matricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Matricula(Integer idMatricula, String curso, int convocatoria) {
        this.idMatricula = idMatricula;
        this.curso = curso;
        this.convocatoria = convocatoria;
    }

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(int convocatoria) {
        this.convocatoria = convocatoria;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    public Alumno getNre() {
        return nre;
    }

    public void setNre(Alumno nre) {
        this.nre = nre;
    }

    public ProfesorMateria getProfesorMateria() {
        return profesorMateria;
    }

    public void setProfesorMateria(ProfesorMateria profesorMateria) {
        this.profesorMateria = profesorMateria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMatricula != null ? idMatricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matricula)) {
            return false;
        }
        Matricula other = (Matricula) object;
        if ((this.idMatricula == null && other.idMatricula != null) || (this.idMatricula != null && !this.idMatricula.equals(other.idMatricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject4.asdasd.Matricula[ idMatricula=" + idMatricula + " ]";
    }
}

