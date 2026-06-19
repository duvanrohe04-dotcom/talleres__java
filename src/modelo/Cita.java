package modelo;

public class Cita {

    private int id;
    private String cliente;
    private String moto;
    private String fecha;
    private String estado;

    public Cita() {
    }

    public Cita(int id, String cliente, String moto, String fecha, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.moto = moto;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getMoto() {
        return moto;
    }

    public void setMoto(String moto) {
        this.moto = moto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return cliente + " - " + fecha;
    }
}
