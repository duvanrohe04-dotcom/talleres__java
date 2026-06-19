package modelo;

public class Trabajador {

    private int id;
    private String nombre;
    private String cargo;
    private String telefono;
    private String email;

    public Trabajador() {
    }

    public Trabajador(int id, String nombre, String cargo, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.cargo = cargo;
        this.telefono = telefono;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return nombre + " - " + cargo;
    }
}
