package vista;

import controlador.ClienteController;
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

public class FRMClientes extends JInternalFrame {

    private JTextField txtNombre, txtDocumento, txtDireccion, txtCelular;
    private javax.swing.JButton btnGuardar, btnEditar, btnEliminar, btnLimpiar, BTNCerrar;
    private JScrollPane scrollPane;
    private JTable TBLClientes;
    private MBLogo logoTaller;
    private JPanel header, panelForm, panelBotones;
    private ClienteController controller;
    private int idSeleccionado = -1;

    public FRMClientes() {
        controller = new ClienteController();
        initComponents();
        new Thread(() -> { try { cargarDatos(); } catch (Exception e) {} }).start();
    }

    private void initComponents() {
        setTitle("Clientes Registrados");
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

        header = crearHeaderBar("CLIENTES REGISTRADOS", logoTaller, 100);

        panelForm = crearPanelCard(15, 50, 1, 110);
        panelForm.setLayout(null);

        int lblW = 90, txtW = 220, row1Y = 12, row2Y = 55, lblX1 = 15, txtX1 = 105, lblX2 = 365, txtX2 = 455;

        JLabel lblNombre = crearLabel("Nombre:", lblX1, row1Y, lblW);
        txtNombre = crearCampoTexto(txtX1, row1Y, txtW);
        JLabel lblDocumento = crearLabel("Documento:", lblX2, row1Y, lblW);
        txtDocumento = crearCampoTexto(txtX2, row1Y, txtW);

        JLabel lblDireccion = crearLabel("Direccion:", lblX1, row2Y, lblW);
        txtDireccion = crearCampoTexto(txtX1, row2Y, txtW);
        JLabel lblCelular = crearLabel("Celular:", lblX2, row2Y, lblW);
        txtCelular = crearCampoTexto(txtX2, row2Y, txtW);

        panelForm.add(lblNombre); panelForm.add(txtNombre);
        panelForm.add(lblDocumento); panelForm.add(txtDocumento);
        panelForm.add(lblDireccion); panelForm.add(txtDireccion);
        panelForm.add(lblCelular); panelForm.add(txtCelular);

        panelBotones = crearPanelCard(15, 168, 1, 50);
        panelBotones.setLayout(null);

        int btnY = 9;
        btnGuardar = crearBotonPrimario("GUARDAR", 15, btnY);
        btnGuardar.addActionListener(e -> guardarCliente());
        btnEditar = crearBotonSecundario("EDITAR", 130, btnY);
        btnEditar.addActionListener(e -> editarCliente());
        btnEliminar = crearBotonDestructivo("ELIMINAR", 245, btnY);
        btnEliminar.addActionListener(e -> eliminarCliente());
        btnLimpiar = crearBotonSecundario("LIMPIAR", 360, btnY);
        btnLimpiar.addActionListener(e -> limpiarCampos());
        BTNCerrar = crearBotonNeutral("CERRAR", 590, btnY);
        BTNCerrar.addActionListener(e -> dispose());

        panelBotones.add(btnGuardar); panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar); panelBotones.add(btnLimpiar);
        panelBotones.add(BTNCerrar);

        TBLClientes = new JTable();
        configurarTabla(TBLClientes);
        TBLClientes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) seleccionarFila();
        });

        scrollPane = new JScrollPane(TBLClientes);
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

    private void guardarCliente() {
        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio");
            return;
        }
        modelo.Cliente c = new modelo.Cliente();
        c.setNombre(txtNombre.getText().trim());
        c.setDocumento(txtDocumento.getText().trim());
        c.setDireccion(txtDireccion.getText().trim());
        c.setCelular(txtCelular.getText().trim());
        if (controller.guardar(c)) {
            JOptionPane.showMessageDialog(this, "Cliente guardado");
            limpiarCampos();
            cargarDatos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar cliente");
        }
    }

    private void editarCliente() {
        if (idSeleccionado < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente de la tabla");
            return;
        }
        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio");
            return;
        }
        modelo.Cliente c = new modelo.Cliente();
        c.setId(idSeleccionado);
        c.setNombre(txtNombre.getText().trim());
        c.setDocumento(txtDocumento.getText().trim());
        c.setDireccion(txtDireccion.getText().trim());
        c.setCelular(txtCelular.getText().trim());
        if (controller.editar(c)) {
            JOptionPane.showMessageDialog(this, "Cliente actualizado");
            idSeleccionado = -1;
            limpiarCampos();
            cargarDatos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar");
        }
    }

    private void eliminarCliente() {
        if (idSeleccionado < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente de la tabla");
            return;
        }
        int r = JOptionPane.showConfirmDialog(this, "Eliminar cliente seleccionado?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            if (controller.eliminar(idSeleccionado)) {
                JOptionPane.showMessageDialog(this, "Cliente eliminado");
                idSeleccionado = -1;
                limpiarCampos();
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar");
            }
        }
    }

    private void seleccionarFila() {
        int fila = TBLClientes.getSelectedRow();
        if (fila >= 0) {
            idSeleccionado = Integer.parseInt(TBLClientes.getValueAt(fila, 0).toString());
            txtNombre.setText(TBLClientes.getValueAt(fila, 1).toString());
            txtDocumento.setText(TBLClientes.getValueAt(fila, 2).toString());
            txtDireccion.setText(TBLClientes.getValueAt(fila, 3).toString());
            txtCelular.setText(TBLClientes.getValueAt(fila, 4).toString());
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDocumento.setText("");
        txtDireccion.setText("");
        txtCelular.setText("");
        idSeleccionado = -1;
        TBLClientes.clearSelection();
    }

    private void cargarDatos() {
        controller.cargarTabla(TBLClientes);
    }
}
