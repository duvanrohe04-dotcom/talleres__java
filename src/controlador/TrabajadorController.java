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
import modelo.Trabajador;

public class TrabajadorController {

    public List<Trabajador> listar() {
        List<Trabajador> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, cargo, telefono, email FROM trabajadores ORDER BY id";
        try {
            Connection con = BaseDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Trabajador t = new Trabajador();
                t.setId(rs.getInt("id"));
                t.setNombre(rs.getString("nombre"));
                t.setCargo(rs.getString("cargo"));
                t.setTelefono(rs.getString("telefono"));
                t.setEmail(rs.getString("email"));
                lista.add(t);
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
                List<Trabajador> lista = listar();
                javax.swing.SwingUtilities.invokeLater(() -> {
                    DefaultTableModel model = new DefaultTableModel(
                        new String[]{"ID", "Nombre", "Cargo", "Teléfono", "Email"}, 0
                    );
                    for (Trabajador t : lista) {
                        model.addRow(new Object[]{
                            t.getId(), t.getNombre(), t.getCargo(),
                            t.getTelefono(), t.getEmail()
                        });
                    }
                    tabla.setModel(model);
                });
            } catch (Exception e) { /* BD no disponible */ }
        }).start();
    }

    public boolean guardar(Trabajador t) {
        String sql = "INSERT INTO trabajadores (nombre, cargo, telefono, email) VALUES (?, ?, ?, ?)";
        try {
            Connection con = BaseDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getNombre());
            ps.setString(2, t.getCargo());
            ps.setString(3, t.getTelefono());
            ps.setString(4, t.getEmail());
            boolean r = ps.executeUpdate() > 0;
            ps.close();
            return r;
        } catch (SQLException e) {
            System.err.println("Error al guardar trabajador: " + e.getMessage());
            return false;
        }
    }

    public boolean editar(Trabajador t) {
        String sql = "UPDATE trabajadores SET nombre=?, cargo=?, telefono=?, email=? WHERE id=?";
        try {
            Connection con = BaseDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getNombre());
            ps.setString(2, t.getCargo());
            ps.setString(3, t.getTelefono());
            ps.setString(4, t.getEmail());
            ps.setInt(5, t.getId());
            boolean r = ps.executeUpdate() > 0;
            ps.close();
            return r;
        } catch (SQLException e) {
            System.err.println("Error al actualizar trabajador: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM trabajadores WHERE id=?";
        try {
            Connection con = BaseDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            boolean r = ps.executeUpdate() > 0;
            ps.close();
            return r;
        } catch (SQLException e) {
            System.err.println("Error al eliminar trabajador: " + e.getMessage());
            return false;
        }
    }
}
