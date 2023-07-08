package com.example.resto.EntityDTO;

public class EmpleadoDTO {


    private Long id;

    private String userName;

    private String nombre;

    private String apellido;

    private String password;

    private Integer dni;

    private Rol rol;

    public EmpleadoDTO(Long id, String userName, String nombre, String apellido, String password, Integer dni, Rol rol) {
        this.id = id;
        this.userName = userName;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.dni = dni;
        this.rol = rol;
    }

    public EmpleadoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public Rol getIdRol() {
        return this.rol;
    }

    public void setIdRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "EmpleadoDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", password='" + password + '\'' +
                ", dni=" + dni +
                ", Rol=" + rol +
                '}';
    }
}
