package com.alcoleagarridofran.auladeskv3.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "alumno")
@NamedQueries({
        @NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a"),
        @NamedQuery(name = "Alumno.findByNre", query = "SELECT a FROM Alumno a WHERE a.nre = :nre"),
        @NamedQuery(name = "Alumno.findByNombre", query = "SELECT a FROM Alumno a WHERE a.nombre = :nombre"),
        @NamedQuery(name = "Alumno.findByApellidos", query = "SELECT a FROM Alumno a WHERE a.apellidos = :apellidos"),
        @NamedQuery(name = "Alumno.findByFechaNacimiento", query = "SELECT a FROM Alumno a WHERE a.fechaNacimiento = :fechaNacimiento")})
public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nre")
    private Integer nre;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "nre")
    private List<Matricula> matriculaList;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @OneToOne
    private Usuario usuario;

    public Alumno() {
    }

    public Alumno(Integer nre) {
        this.nre = nre;
    }

    public Alumno(Integer nre, String nombre, String apellidos) {
        this.nre = nre;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Integer getNre() {
        return nre;
    }

    public void setNre(Integer nre) {
        this.nre = nre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    public Usuario getUsuario() { // 💡 CAMBIADO
        return usuario;
    }

    public void setUsuario(Usuario usuario) { // 💡 CAMBIADO
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nre != null ? nre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.nre == null && other.nre != null) || (this.nre != null && !this.nre.equals(other.nre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DATOS ALUMNO: NRE: " + nre + " NOMBRE: " + nombre + " APELLIDOS: " + apellidos + " FECHA NACIMIENTO: " + fechaNacimiento;
    }
}

