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
import modelo.Cliente;

public class ClienteController {

    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, documento, direccion, celular FROM clientes ORDER BY id";
        try {
            Connection con = BaseDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setDocumento(rs.getString("documento"));
                c.setDireccion(rs.getString("direccion"));
                c.setCelular(rs.getString("celular"));
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
                List<Cliente> lista = listar();
                javax.swing.SwingUtilities.invokeLater(() -> {
                    DefaultTableModel model = new DefaultTableModel(
                        new String[]{"ID", "Nombre", "Documento", "Dirección", "Celular"}, 0
                    );
                    for (Cliente c : lista) {
                        model.addRow(new Object[]{
                            c.getId(), c.getNombre(), c.getDocumento(),
                            c.getDireccion(), c.getCelular()
                        });
                    }
                    tabla.setModel(model);
                });
            } catch (Exception e) { /* BD no disponible */ }
        }).start();
    }

    public boolean guardar(Cliente c) {
        String sql = "INSERT INTO clientes (nombre, documento, direccion, celular) VALUES (?, ?, ?, ?)";
        try {
            Connection con = BaseDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDocumento());
            ps.setString(3, c.getDireccion());
            ps.setString(4, c.getCelular());
            boolean r = ps.executeUpdate() > 0;
            ps.close();
            return r;
        } catch (SQLException e) {
            System.err.println("Error al guardar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean editar(Cliente c) {
        String sql = "UPDATE clientes SET nombre=?, documento=?, direccion=?, celular=? WHERE id=?";
        try {
            Connection con = BaseDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDocumento());
            ps.setString(3, c.getDireccion());
            ps.setString(4, c.getCelular());
            ps.setInt(5, c.getId());
            boolean r = ps.executeUpdate() > 0;
            ps.close();
            return r;
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM clientes WHERE id=?";
        try {
            Connection con = BaseDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            boolean r = ps.executeUpdate() > 0;
            ps.close();
            return r;
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }
}
