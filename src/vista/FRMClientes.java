package vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
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
    private SENALogo logoSena;

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
        setSize(720, 480);
        getContentPane().setLayout(null);
        getContentPane().setBackground(DARK_BG);

        logoSena = new SENALogo(24, true);
        logoSena.setBounds(15, 10, 24, 24);

        lblTitulo = new JLabel("CLIENTES REGISTRADOS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(GOLD);
        lblTitulo.setBounds(0, 8, 720, 35);

        int lblW = 90, txtW = 220, row1Y = 55, row2Y = 95, lblX1 = 25, txtX1 = 115, lblX2 = 365, txtX2 = 455;

        lblNombre = crearLabel("Nombre:", lblX1, row1Y, lblW);
        txtNombre = crearCampoTexto(txtX1, row1Y, txtW);
        lblDocumento = crearLabel("Documento:", lblX2, row1Y, lblW);
        txtDocumento = crearCampoTexto(txtX2, row1Y, txtW);

        lblDireccion = crearLabel("Dirección:", lblX1, row2Y, lblW);
        txtDireccion = crearCampoTexto(txtX1, row2Y, txtW);
        lblCelular = crearLabel("Celular:", lblX2, row2Y, lblW);
        txtCelular = crearCampoTexto(txtX2, row2Y, txtW);

        int btnY = 140, btnW = 105, btnH = 32;
        btnGuardar = crearBotonRedondeado("GUARDAR", SENA_GREEN, 25, btnY, btnW, btnH);
        btnEditar = crearBotonRedondeado("EDITAR", GOLD_DARK, 140, btnY, btnW, btnH);
        btnEliminar = crearBotonRedondeado("ELIMINAR", new Color(200, 50, 50), 255, btnY, btnW, btnH);
        btnLimpiar = crearBotonRedondeado("LIMPIAR", new Color(100, 100, 100), 370, btnY, btnW, btnH);
        BTNCerrar = crearBotonRedondeado("CERRAR", new Color(70, 70, 70), 590, btnY, btnW, btnH);
        BTNCerrar.addActionListener(e -> dispose());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        TBLClientes = new JTable();
        TBLClientes.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        TBLClientes.setRowHeight(30);
        TBLClientes.setBackground(new Color(42, 42, 42));
        TBLClientes.setForeground(Color.WHITE);
        TBLClientes.setGridColor(new Color(70, 70, 70));
        TBLClientes.setSelectionBackground(new Color(57, 169, 0, 180));
        TBLClientes.setSelectionForeground(Color.WHITE);

        JTableHeader header = TBLClientes.getTableHeader();
        header.setBackground(GOLD);
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        TBLClientes.setDefaultRenderer(Object.class, center);

        scrollPane = new JScrollPane(TBLClientes);
        scrollPane.setBounds(20, 185, 680, 255);
        scrollPane.getViewport().setBackground(new Color(42, 42, 42));
        scrollPane.getViewport().setOpaque(true);

        getContentPane().add(logoSena);
        getContentPane().add(lblTitulo);
        getContentPane().add(lblNombre); getContentPane().add(txtNombre);
        getContentPane().add(lblDocumento); getContentPane().add(txtDocumento);
        getContentPane().add(lblDireccion); getContentPane().add(txtDireccion);
        getContentPane().add(lblCelular); getContentPane().add(txtCelular);
        getContentPane().add(btnGuardar); getContentPane().add(btnEditar);
        getContentPane().add(btnEliminar); getContentPane().add(btnLimpiar);
        getContentPane().add(BTNCerrar);
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
            new String[]{"ID", "Nombre", "Documento", "Dirección", "Celular"}, 0
        );
        TBLClientes.setModel(modelo);
    }
}
