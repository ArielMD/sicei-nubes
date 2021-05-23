package mx.uady.sicei.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "alumnos")
public class Alumno {

    // POJO: Plain Java Object. No existe ninguna accion

    // int vs Integer

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // f(x) = y
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column
    @Enumerated(EnumType.STRING)
    private Licenciatura licenciatura;

    // JOIN Usuario WHERE alumno.id_usario = usuarios.id

    // LAZY vs EAGER

    @OneToOne
    @JoinColumn(name = "id_usuario") // alumno.id_usuario
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_equipo")
    @JsonBackReference
    private Equipo equipo;

    public Alumno() {
    }

    public Alumno(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Alumno id(Integer id) {
        this.id = id;
        return this;
    }

    public Alumno nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Licenciatura getLicenciatura() {
        return licenciatura;
    }

    public void setLicenciatura(Licenciatura licenciatura) {
        this.licenciatura = licenciatura;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", nombre='" + getNombre() + "'" + "}";
    }

}
