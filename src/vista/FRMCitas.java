package vista;

import controlador.CitaController;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import static vista.UIUtil.*;

public class FRMCitas extends JInternalFrame {

    private JTextField txtCliente, txtMoto, txtFecha;
    private JComboBox<String> cmbEstado;
    private javax.swing.JButton btnGuardar, btnEditar, btnEliminar, btnLimpiar, BTNCerrar;
    private JScrollPane scrollPane;
    private JTable TBLCitas;
    private MBLogo logoTaller;
    private JPanel header, panelForm, panelBotones;
    private CitaController controller;
    private int idSeleccionado = -1;

    public FRMCitas() {
        controller = new CitaController();
        initComponents();
        new Thread(() -> { try { cargarDatos(); } catch (Exception e) {} }).start();
    }

    private void initComponents() {
        setTitle("Citas Programadas");
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

        header = crearHeaderBar("CITAS PROGRAMADAS", logoTaller, 100);

        panelForm = crearPanelCard(15, 50, 1, 110);
        panelForm.setLayout(null);

        int lblW = 80, txtW = 210, row1Y = 12, row2Y = 55, lblX1 = 15, txtX1 = 95, lblX2 = 340, txtX2 = 420;

        JLabel lblCliente = crearLabel("Cliente:", lblX1, row1Y, lblW);
        txtCliente = crearCampoTexto(txtX1, row1Y, txtW);
        JLabel lblMoto = crearLabel("Moto:", lblX2, row1Y, lblW);
        txtMoto = crearCampoTexto(txtX2, row1Y, txtW);

        JLabel lblFecha = crearLabel("Fecha:", lblX1, row2Y, lblW);
        txtFecha = crearCampoTexto(txtX1, row2Y, txtW);
        JLabel lblEstado = crearLabel("Estado:", lblX2, row2Y, lblW);
        cmbEstado = new JComboBox<>(new String[]{"Pendiente", "En proceso", "Completada", "Cancelada"});
        cmbEstado.setBounds(txtX2, row2Y, 210, 30);
        cmbEstado.setFont(FONT_INPUT);
        cmbEstado.setBackground(Color.WHITE);
        cmbEstado.setForeground(TEXT_DARK);

        panelForm.add(lblCliente); panelForm.add(txtCliente);
        panelForm.add(lblMoto); panelForm.add(txtMoto);
        panelForm.add(lblFecha); panelForm.add(txtFecha);
        panelForm.add(lblEstado); panelForm.add(cmbEstado);

        panelBotones = crearPanelCard(15, 168, 1, 50);
        panelBotones.setLayout(null);

        int btnY = 9;
        btnGuardar = crearBotonPrimario("GUARDAR", 15, btnY);
        btnGuardar.addActionListener(e -> guardarCita());
        btnEditar = crearBotonSecundario("EDITAR", 130, btnY);
        btnEditar.addActionListener(e -> editarCita());
        btnEliminar = crearBotonDestructivo("ELIMINAR", 245, btnY);
        btnEliminar.addActionListener(e -> eliminarCita());
        btnLimpiar = crearBotonSecundario("LIMPIAR", 360, btnY);
        btnLimpiar.addActionListener(e -> limpiarCampos());
        BTNCerrar = crearBotonNeutral("CERRAR", 630, btnY);
        BTNCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnGuardar); panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar); panelBotones.add(btnLimpiar);
        panelBotones.add(BTNCerrar);

        TBLCitas = new JTable();
        configurarTabla(TBLCitas);
        TBLCitas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) seleccionarFila();
        });

        scrollPane = new JScrollPane(TBLCitas);
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

    private void guardarCita() {
        if (txtCliente.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El cliente es obligatorio");
            return;
        }
        modelo.Cita c = new modelo.Cita();
        c.setCliente(txtCliente.getText().trim());
        c.setMoto(txtMoto.getText().trim());
        c.setFecha(txtFecha.getText().trim());
        c.setEstado(cmbEstado.getSelectedItem().toString());
        if (controller.guardar(c)) {
            JOptionPane.showMessageDialog(this, "Cita guardada");
            limpiarCampos();
            cargarDatos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar cita");
        }
    }

    private void editarCita() {
        if (idSeleccionado < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una cita de la tabla");
            return;
        }
        if (txtCliente.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El cliente es obligatorio");
            return;
        }
        modelo.Cita c = new modelo.Cita();
        c.setId(idSeleccionado);
        c.setCliente(txtCliente.getText().trim());
        c.setMoto(txtMoto.getText().trim());
        c.setFecha(txtFecha.getText().trim());
        c.setEstado(cmbEstado.getSelectedItem().toString());
        if (controller.editar(c)) {
            JOptionPane.showMessageDialog(this, "Cita actualizada");
            idSeleccionado = -1;
            limpiarCampos();
            cargarDatos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar");
        }
    }

    private void eliminarCita() {
        if (idSeleccionado < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una cita de la tabla");
            return;
        }
        int r = JOptionPane.showConfirmDialog(this, "Eliminar cita seleccionada?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            if (controller.eliminar(idSeleccionado)) {
                JOptionPane.showMessageDialog(this, "Cita eliminada");
                idSeleccionado = -1;
                limpiarCampos();
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar");
            }
        }
    }

    private void seleccionarFila() {
        int fila = TBLCitas.getSelectedRow();
        if (fila >= 0) {
            idSeleccionado = Integer.parseInt(TBLCitas.getValueAt(fila, 0).toString());
            txtCliente.setText(TBLCitas.getValueAt(fila, 1).toString());
            txtMoto.setText(TBLCitas.getValueAt(fila, 2).toString());
            txtFecha.setText(TBLCitas.getValueAt(fila, 3).toString());
            String estado = TBLCitas.getValueAt(fila, 4).toString();
            for (int i = 0; i < cmbEstado.getItemCount(); i++) {
                if (cmbEstado.getItemAt(i).equals(estado)) {
                    cmbEstado.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    private void limpiarCampos() {
        txtCliente.setText(""); txtMoto.setText("");
        txtFecha.setText(""); cmbEstado.setSelectedIndex(0);
        idSeleccionado = -1;
        TBLCitas.clearSelection();
    }

    private void cargarDatos() {
        controller.cargarTabla(TBLCitas);
    }
}
