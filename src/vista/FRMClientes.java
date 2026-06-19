package vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import static vista.UIUtil.*;

public class FRMClientes extends JInternalFrame {

    private JLabel lblTitulo, lblNombre, lblDocumento, lblDireccion, lblCelular;
    private JTextField txtNombre, txtDocumento, txtDireccion, txtCelular;
    private javax.swing.JButton btnGuardar, btnEditar, btnEliminar, btnLimpiar, BTNCerrar;
    private JScrollPane scrollPane;
    private JTable TBLClientes;
    private MBLogo logoTaller;

    public FRMClientes() {
        initComponents();
        cargarDatos();
    }

    private void initComponents() {
        setTitle("Clientes Registrados");
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(740, 500);
        getContentPane().setLayout(null);
        getContentPane().setBackground(DARK_BG);

        logoTaller = new MBLogo(22, true);
        logoTaller.setBounds(15, 10, 22, 22);

        JPanel header = crearHeaderBar("CLIENTES REGISTRADOS", logoTaller, 740);
        getContentPane().add(header);

        JPanel panelForm = crearPanelCard(15, 50, 710, 110);
        panelForm.setLayout(null);

        int lblW = 90, txtW = 220, row1Y = 12, row2Y = 55, lblX1 = 15, txtX1 = 105, lblX2 = 365, txtX2 = 455;

        lblNombre = crearLabel("Nombre:", lblX1, row1Y, lblW);
        txtNombre = crearCampoTexto(txtX1, row1Y, txtW);
        lblDocumento = crearLabel("Documento:", lblX2, row1Y, lblW);
        txtDocumento = crearCampoTexto(txtX2, row1Y, txtW);

        lblDireccion = crearLabel("Direccion:", lblX1, row2Y, lblW);
        txtDireccion = crearCampoTexto(txtX1, row2Y, txtW);
        lblCelular = crearLabel("Celular:", lblX2, row2Y, lblW);
        txtCelular = crearCampoTexto(txtX2, row2Y, txtW);

        panelForm.add(lblNombre); panelForm.add(txtNombre);
        panelForm.add(lblDocumento); panelForm.add(txtDocumento);
        panelForm.add(lblDireccion); panelForm.add(txtDireccion);
        panelForm.add(lblCelular); panelForm.add(txtCelular);
        getContentPane().add(panelForm);

        JPanel panelBotones = crearPanelCard(15, 168, 710, 50);
        panelBotones.setLayout(null);

        int btnY = 9, btnW = 105, btnH = 32;
        btnGuardar = crearBotonRedondeado("GUARDAR", RED_PRIMARY, 15, btnY, btnW, btnH);
        btnEditar = crearBotonRedondeado("EDITAR", RED_DARK, 130, btnY, btnW, btnH);
        btnEliminar = crearBotonRedondeado("ELIMINAR", new Color(180, 15, 15), 245, btnY, btnW, btnH);
        btnLimpiar = crearBotonRedondeado("LIMPIAR", new Color(70, 70, 70), 360, btnY, btnW, btnH);
        BTNCerrar = crearBotonRedondeado("CERRAR", new Color(45, 45, 45), 590, btnY, btnW, btnH);
        BTNCerrar.addActionListener(e -> dispose());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        panelBotones.add(btnGuardar); panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar); panelBotones.add(btnLimpiar);
        panelBotones.add(BTNCerrar);
        getContentPane().add(panelBotones);

        TBLClientes = new JTable();
        TBLClientes.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        TBLClientes.setRowHeight(32);
        TBLClientes.setBackground(DARK_BG);
        TBLClientes.setForeground(Color.WHITE);
        TBLClientes.setGridColor(TABLE_GRID);
        TBLClientes.setSelectionBackground(TABLE_SELECTION);
        TBLClientes.setSelectionForeground(Color.WHITE);

        JTableHeader headerTable = TBLClientes.getTableHeader();
        headerTable.setBackground(TABLE_HEADER_BG);
        headerTable.setForeground(TABLE_HEADER_FG);
        headerTable.setFont(new Font("Segoe UI", Font.BOLD, 13));

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        TBLClientes.setDefaultRenderer(Object.class, center);

        scrollPane = new JScrollPane(TBLClientes);
        scrollPane.setBounds(15, 228, 710, 240);
        scrollPane.getViewport().setBackground(DARK_BG);
        scrollPane.getViewport().setOpaque(true);
        scrollPane.setBorder(null);

        getContentPane().add(scrollPane);
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDocumento.setText("");
        txtDireccion.setText("");
        txtCelular.setText("");
    }

    private void cargarDatos() {
        DefaultTableModel modelo = new DefaultTableModel(
            new String[]{"ID", "Nombre", "Documento", "Direccion", "Celular"}, 0
        );
        TBLClientes.setModel(modelo);
    }
}
