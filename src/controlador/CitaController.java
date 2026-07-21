package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.BaseDB;
import modelo.Cita;

public class CitaController {

    public List<Cita> listar() {
        List<Cita> lista = new ArrayList<>();
        String sql = "SELECT id, cliente, moto, fecha, estado FROM citas ORDER BY id";
        try {
            Connection con = BaseDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cita c = new Cita();
                c.setId(rs.getInt("id"));
                c.setCliente(rs.getString("cliente"));
                c.setMoto(rs.getString("moto"));
                c.setFecha(rs.getString("fecha"));
                c.setEstado(rs.getString("estado"));
                lista.add(c);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void cargarTabla(JTable tabla) {
        new Thread(() -> {
            try {
                List<Cita> lista = listar();
                javax.swing.SwingUtilities.invokeLater(() -> {
                    DefaultTableModel model = new DefaultTableModel(
                        new String[]{"ID", "Cliente", "Moto", "Fecha", "Estado"}, 0
                    );
                    for (Cita c : lista) {
                        model.addRow(new Object[]{
                            c.getId(), c.getCliente(), c.getMoto(),
                            c.getFecha(), c.getEstado()
                        });
                    }
                    tabla.setModel(model);
                });
            } catch (Exception e) { /* BD no disponible */ }
        }).start();
    }

    public boolean guardar(Cita c) {
        String sql = "INSERT INTO citas (cliente, moto, fecha, estado) VALUES (?, ?, ?, ?)";
        try {
            Connection con = BaseDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getCliente());
            ps.setString(2, c.getMoto());
            ps.setString(3, c.getFecha());
            ps.setString(4, c.getEstado());
            boolean r = ps.executeUpdate() > 0;
            ps.close();
            return r;
        } catch (SQLException e) {
            System.err.println("Error al guardar cita: " + e.getMessage());
            return false;
        }
    }

    public boolean editar(Cita c) {
        String sql = "UPDATE citas SET cliente=?, moto=?, fecha=?, estado=? WHERE id=?";
        try {
            Connection con = BaseDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getCliente());
            ps.setString(2, c.getMoto());
            ps.setString(3, c.getFecha());
            ps.setString(4, c.getEstado());
            ps.setInt(5, c.getId());
            boolean r = ps.executeUpdate() > 0;
            ps.close();
            return r;
        } catch (SQLException e) {
            System.err.println("Error al actualizar cita: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM citas WHERE id=?";
        try {
            Connection con = BaseDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            boolean r = ps.executeUpdate() > 0;
            ps.close();
            return r;
        } catch (SQLException e) {
            System.err.println("Error al eliminar cita: " + e.getMessage());
            return false;
        }
    }
}
