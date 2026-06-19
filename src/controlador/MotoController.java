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
import modelo.Moto;

public class MotoController {

    public List<Moto> listar() {
        List<Moto> lista = new ArrayList<>();
        String sql = "SELECT id, marca, modelo, placa, color, cliente FROM motos ORDER BY id";
        try (Connection con = BaseDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Moto m = new Moto();
                m.setId(rs.getInt("id"));
                m.setMarca(rs.getString("marca"));
                m.setModelo(rs.getString("modelo"));
                m.setPlaca(rs.getString("placa"));
                m.setColor(rs.getString("color"));
                m.setCliente(rs.getString("cliente"));
                lista.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void cargarTabla(JTable tabla) {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "Marca", "Modelo", "Placa", "Color", "Cliente"}, 0
        );
        List<Moto> lista = listar();
        for (Moto m : lista) {
            model.addRow(new Object[]{
                m.getId(), m.getMarca(), m.getModelo(),
                m.getPlaca(), m.getColor(), m.getCliente()
            });
        }
        tabla.setModel(model);
    }

    public boolean guardar(Moto m) {
        String sql = "INSERT INTO motos (marca, modelo, placa, color, cliente) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = BaseDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getMarca());
            ps.setString(2, m.getModelo());
            ps.setString(3, m.getPlaca());
            ps.setString(4, m.getColor());
            ps.setString(5, m.getCliente());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(Moto m) {
        String sql = "UPDATE motos SET marca=?, modelo=?, placa=?, color=?, cliente=? WHERE id=?";
        try (Connection con = BaseDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getMarca());
            ps.setString(2, m.getModelo());
            ps.setString(3, m.getPlaca());
            ps.setString(4, m.getColor());
            ps.setString(5, m.getCliente());
            ps.setInt(6, m.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM motos WHERE id=?";
        try (Connection con = BaseDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
