package com.alcoleagarridofran.auladeskv3.model;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;

/**
 *
 * @author franc
 */
@Entity
@Table(name = "profesor")
@NamedQueries({
        @NamedQuery(name = "Profesor.findAll", query = "SELECT p FROM Profesor p"),
        @NamedQuery(name = "Profesor.findByIdProfesor", query = "SELECT p FROM Profesor p WHERE p.idProfesor = :idProfesor"),
        @NamedQuery(name = "Profesor.findByNombre", query = "SELECT p FROM Profesor p WHERE p.nombre = :nombre"),
        @NamedQuery(name = "Profesor.findByApellidos", query = "SELECT p FROM Profesor p WHERE p.apellidos = :apellidos")})
public class Profesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_profesor")
    private Integer idProfesor;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellidos")
    private String apellidos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profesor")
    private List<ProfesorMateria> profesorMateriaList;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @OneToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    public Profesor() {
    }

    public Profesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Profesor(Integer idProfesor, String nombre, String apellidos) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }
    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
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

    public List<ProfesorMateria> getProfesorMateriaList() {
        return profesorMateriaList;
    }

    public void setProfesorMateriaList(List<ProfesorMateria> profesorMateriaList) {
        this.profesorMateriaList = profesorMateriaList;
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
        hash += (idProfesor != null ? idProfesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesor)) {
            return false;
        }
        Profesor other = (Profesor) object;
        if ((this.idProfesor == null && other.idProfesor != null) || (this.idProfesor != null && !this.idProfesor.equals(other.idProfesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DATOS PROFESOR: ID: " + idProfesor + " NOMBRE: " + nombre + " APELLIDOS: " + apellidos;
    }

}

