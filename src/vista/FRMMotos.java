package vista;

import controlador.MotoController;
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

public class FRMMotos extends JInternalFrame {

    private JTextField txtMarca, txtModelo, txtPlaca, txtColor, txtCliente;
    private javax.swing.JButton btnGuardar, btnEditar, btnEliminar, btnLimpiar, BTNCerrar;
    private JScrollPane scrollPane;
    private JTable TBLMotos;
    private MBLogo logoTaller;
    private JPanel header, panelForm, panelBotones;
    private MotoController controller;
    private int idSeleccionado = -1;

    public FRMMotos() {
        controller = new MotoController();
        initComponents();
        new Thread(() -> { try { cargarDatos(); } catch (Exception e) {} }).start();
    }

    private void initComponents() {
        setTitle("Motos del Taller");
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

        header = crearHeaderBar("MOTOS REGISTRADAS", logoTaller, 100);

        panelForm = crearPanelCard(15, 50, 1, 140);
        panelForm.setLayout(null);

        int lblW = 80, txtW = 180, lblX1 = 15, txtX1 = 95, lblX2 = 325, txtX2 = 405;
        int row1Y = 12, row2Y = 52, row3Y = 92;
        int bigTxtW = 280;

        JLabel lblMarca = crearLabel("Marca:", lblX1, row1Y, lblW);
        txtMarca = crearCampoTexto(txtX1, row1Y, txtW);
        JLabel lblModelo = crearLabel("Modelo:", lblX2, row1Y, lblW);
        txtModelo = crearCampoTexto(txtX2, row1Y, txtW);

        JLabel lblPlaca = crearLabel("Placa:", lblX1, row2Y, lblW);
        txtPlaca = crearCampoTexto(txtX1, row2Y, txtW);
        JLabel lblColor = crearLabel("Color:", lblX2, row2Y, lblW);
        txtColor = crearCampoTexto(txtX2, row2Y, txtW);

        JLabel lblCliente = crearLabel("Cliente:", lblX1, row3Y, 90);
        txtCliente = crearCampoTexto(txtX1, row3Y, bigTxtW);

        panelForm.add(lblMarca); panelForm.add(txtMarca);
        panelForm.add(lblModelo); panelForm.add(txtModelo);
        panelForm.add(lblPlaca); panelForm.add(txtPlaca);
        panelForm.add(lblColor); panelForm.add(txtColor);
        panelForm.add(lblCliente); panelForm.add(txtCliente);

        panelBotones = crearPanelCard(15, 198, 1, 50);
        panelBotones.setLayout(null);

        int btnY = 9;
        btnGuardar = crearBotonPrimario("GUARDAR", 15, btnY);
        btnGuardar.addActionListener(e -> guardarMoto());
        btnEditar = crearBotonSecundario("EDITAR", 130, btnY);
        btnEditar.addActionListener(e -> editarMoto());
        btnEliminar = crearBotonDestructivo("ELIMINAR", 245, btnY);
        btnEliminar.addActionListener(e -> eliminarMoto());
        btnLimpiar = crearBotonSecundario("LIMPIAR", 360, btnY);
        btnLimpiar.addActionListener(e -> limpiarCampos());
        BTNCerrar = crearBotonNeutral("CERRAR", 590, btnY);
        BTNCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnGuardar); panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar); panelBotones.add(btnLimpiar);
        panelBotones.add(BTNCerrar);

        TBLMotos = new JTable();
        configurarTabla(TBLMotos);
        TBLMotos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) seleccionarFila();
        });

        scrollPane = new JScrollPane(TBLMotos);
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

        panelForm.setBounds(15, 50, w - 30, 140);

        panelBotones.setBounds(15, 198, w - 30, 50);

        int scrollY = 258;
        scrollPane.setBounds(15, scrollY, w - 30, h - scrollY - 15);
    }

    private void guardarMoto() {
        if (txtMarca.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La marca es obligatoria");
            return;
        }
        modelo.Moto m = new modelo.Moto();
        m.setMarca(txtMarca.getText().trim());
        m.setModelo(txtModelo.getText().trim());
        m.setPlaca(txtPlaca.getText().trim());
        m.setColor(txtColor.getText().trim());
        m.setCliente(txtCliente.getText().trim());
        if (controller.guardar(m)) {
            JOptionPane.showMessageDialog(this, "Moto guardada");
            limpiarCampos();
            cargarDatos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar moto");
        }
    }

    private void editarMoto() {
        if (idSeleccionado < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una moto de la tabla");
            return;
        }
        if (txtMarca.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La marca es obligatoria");
            return;
        }
        modelo.Moto m = new modelo.Moto();
        m.setId(idSeleccionado);
        m.setMarca(txtMarca.getText().trim());
        m.setModelo(txtModelo.getText().trim());
        m.setPlaca(txtPlaca.getText().trim());
        m.setColor(txtColor.getText().trim());
        m.setCliente(txtCliente.getText().trim());
        if (controller.editar(m)) {
            JOptionPane.showMessageDialog(this, "Moto actualizada");
            idSeleccionado = -1;
            limpiarCampos();
            cargarDatos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar");
        }
    }

    private void eliminarMoto() {
        if (idSeleccionado < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una moto de la tabla");
            return;
        }
        int r = JOptionPane.showConfirmDialog(this, "Eliminar moto seleccionada?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            if (controller.eliminar(idSeleccionado)) {
                JOptionPane.showMessageDialog(this, "Moto eliminada");
                idSeleccionado = -1;
                limpiarCampos();
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar");
            }
        }
    }

    private void seleccionarFila() {
        int fila = TBLMotos.getSelectedRow();
        if (fila >= 0) {
            idSeleccionado = Integer.parseInt(TBLMotos.getValueAt(fila, 0).toString());
            txtMarca.setText(TBLMotos.getValueAt(fila, 1).toString());
            txtModelo.setText(TBLMotos.getValueAt(fila, 2).toString());
            txtPlaca.setText(TBLMotos.getValueAt(fila, 3).toString());
            txtColor.setText(TBLMotos.getValueAt(fila, 4).toString());
            txtCliente.setText(TBLMotos.getValueAt(fila, 5).toString());
        }
    }

    private void limpiarCampos() {
        txtMarca.setText(""); txtModelo.setText("");
        txtPlaca.setText(""); txtColor.setText(""); txtCliente.setText("");
        idSeleccionado = -1;
        TBLMotos.clearSelection();
    }

    private void cargarDatos() {
        controller.cargarTabla(TBLMotos);
    }
}
