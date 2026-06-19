package modelo;

public class Cliente {

    private int id;
    private String nombre;
    private String documento;
    private String direccion;
    private String celular;

    public Cliente() {
    }

    public Cliente(int id, String nombre, String documento, String direccion, String celular) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.direccion = direccion;
        this.celular = celular;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
