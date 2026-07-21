package vista;

import controlador.TrabajadorController;
import java.awt.Color;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import static vista.UIUtil.*;

public class FRMTrabajadores extends JInternalFrame {

    private JTextField txtNombre, txtCargo, txtTelefono, txtEmail;
    private javax.swing.JButton btnGuardar, btnEditar, btnEliminar, btnLimpiar, BTNCerrar;
    private JScrollPane scrollPane;
    private JTable TBLTrabajadores;
    private MBLogo logoTaller;
    private JPanel header, panelForm, panelBotones;
    private TrabajadorController controller;
    private int idSeleccionado = -1;

    public FRMTrabajadores() {
        controller = new TrabajadorController();
        initComponents();
        new Thread(() -> { try { cargarDatos(); } catch (Exception e) {} }).start();
    }

    private void initComponents() {
        setTitle("Trabajadores del Taller");
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(800, 600);
        getContentPane().setLayout(null);
        getContentPane().setBackground(LIGHT_BG);

        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                reorganizar();
            }
        });

        logoTaller = new MBLogo(22);

        header = crearHeaderBar("TRABAJADORES DEL TALLER", logoTaller, 100);

        panelForm = crearPanelCard(15, 50, 1, 110);
        panelForm.setLayout(null);

        int lblW = 90, txtW = 220, row1Y = 12, row2Y = 55, lblX1 = 15, txtX1 = 105, lblX2 = 365, txtX2 = 455;

        JLabel lblNombre = crearLabel("Nombre:", lblX1, row1Y, lblW);
        txtNombre = crearCampoTexto(txtX1, row1Y, txtW);
        JLabel lblCargo = crearLabel("Cargo:", lblX2, row1Y, lblW);
        txtCargo = crearCampoTexto(txtX2, row1Y, txtW);

        JLabel lblTelefono = crearLabel("Telefono:", lblX1, row2Y, lblW);
        txtTelefono = crearCampoTexto(txtX1, row2Y, txtW);
        JLabel lblEmail = crearLabel("Email:", lblX2, row2Y, lblW);
        txtEmail = crearCampoTexto(txtX2, row2Y, txtW);

        panelForm.add(lblNombre); panelForm.add(txtNombre);
        panelForm.add(lblCargo); panelForm.add(txtCargo);
        panelForm.add(lblTelefono); panelForm.add(txtTelefono);
        panelForm.add(lblEmail); panelForm.add(txtEmail);

        panelBotones = crearPanelCard(15, 168, 1, 50);
        panelBotones.setLayout(null);

        int btnY = 9;
        btnGuardar = crearBotonPrimario("GUARDAR", 15, btnY);
        btnGuardar.addActionListener(e -> guardarTrabajador());
        btnEditar = crearBotonSecundario("EDITAR", 130, btnY);
        btnEditar.addActionListener(e -> editarTrabajador());
        btnEliminar = crearBotonDestructivo("ELIMINAR", 245, btnY);
        btnEliminar.addActionListener(e -> eliminarTrabajador());
        btnLimpiar = crearBotonSecundario("LIMPIAR", 360, btnY);
        btnLimpiar.addActionListener(e -> limpiarCampos());
        BTNCerrar = crearBotonNeutral("CERRAR", 590, btnY);
        BTNCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnGuardar); panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar); panelBotones.add(btnLimpiar);
        panelBotones.add(BTNCerrar);

        TBLTrabajadores = new JTable();
        configurarTabla(TBLTrabajadores);
        TBLTrabajadores.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) seleccionarFila();
        });

        scrollPane = new JScrollPane(TBLTrabajadores);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.getViewport().setOpaque(true);
        scrollPane.setBorder(null);

        getContentPane().add(header);
        getContentPane().add(panelForm);
        getContentPane().add(panelBotones);
        getContentPane().add(scrollPane);
    }

    private void reorganizar() {
        int w = getContentPane().getWidth();
        int h = getContentPane().getHeight();
        if (w < 100) return;

        logoTaller.setBounds(15, 10, 22, 22);
        header.setBounds(0, 0, w, 44);

        panelForm.setBounds(15, 50, w - 30, 110);

        panelBotones.setBounds(15, 168, w - 30, 50);

        int scrollY = 228;
        scrollPane.setBounds(15, scrollY, w - 30, h - scrollY - 15);
    }

    private void guardarTrabajador() {
        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio");
            return;
        }
        modelo.Trabajador t = new modelo.Trabajador();
        t.setNombre(txtNombre.getText().trim());
        t.setCargo(txtCargo.getText().trim());
        t.setTelefono(txtTelefono.getText().trim());
        t.setEmail(txtEmail.getText().trim());
        if (controller.guardar(t)) {
            JOptionPane.showMessageDialog(this, "Trabajador guardado");
            limpiarCampos();
            cargarDatos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar trabajador");
        }
    }

    private void editarTrabajador() {
        if (idSeleccionado < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un trabajador de la tabla");
            return;
        }
        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio");
            return;
        }
        modelo.Trabajador t = new modelo.Trabajador();
        t.setId(idSeleccionado);
        t.setNombre(txtNombre.getText().trim());
        t.setCargo(txtCargo.getText().trim());
        t.setTelefono(txtTelefono.getText().trim());
        t.setEmail(txtEmail.getText().trim());
        if (controller.editar(t)) {
            JOptionPane.showMessageDialog(this, "Trabajador actualizado");
            idSeleccionado = -1;
            limpiarCampos();
            cargarDatos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar");
        }
    }

    private void eliminarTrabajador() {
        if (idSeleccionado < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un trabajador de la tabla");
            return;
        }
        int r = JOptionPane.showConfirmDialog(this, "Eliminar trabajador seleccionado?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            if (controller.eliminar(idSeleccionado)) {
                JOptionPane.showMessageDialog(this, "Trabajador eliminado");
                idSeleccionado = -1;
                limpiarCampos();
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar");
            }
        }
    }

    private void seleccionarFila() {
        int fila = TBLTrabajadores.getSelectedRow();
        if (fila >= 0) {
            idSeleccionado = Integer.parseInt(TBLTrabajadores.getValueAt(fila, 0).toString());
            txtNombre.setText(TBLTrabajadores.getValueAt(fila, 1).toString());
            txtCargo.setText(TBLTrabajadores.getValueAt(fila, 2).toString());
            txtTelefono.setText(TBLTrabajadores.getValueAt(fila, 3).toString());
            txtEmail.setText(TBLTrabajadores.getValueAt(fila, 4).toString());
        }
    }

    private void limpiarCampos() {
        txtNombre.setText(""); txtCargo.setText("");
        txtTelefono.setText(""); txtEmail.setText("");
        idSeleccionado = -1;
        TBLTrabajadores.clearSelection();
    }

    private void cargarDatos() {
        controller.cargarTabla(TBLTrabajadores);
    }
}
