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

public class FRMTrabajadores extends JInternalFrame {

    private JLabel lblTitulo, lblNombre, lblCargo, lblTelefono, lblEmail;
    private JTextField txtNombre, txtCargo, txtTelefono, txtEmail;
    private javax.swing.JButton btnGuardar, btnEditar, btnEliminar, btnLimpiar, BTNCerrar;
    private JScrollPane scrollPane;
    private JTable TBLTrabajadores;
    private SENALogo logoSena;

    public FRMTrabajadores() {
        initComponents();
        cargarDatos();
    }

    private void initComponents() {
        setTitle("Trabajadores del Taller");
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(720, 480);
        getContentPane().setLayout(null);
        getContentPane().setBackground(DARK_BG);

        logoSena = new SENALogo(24, true);
        logoSena.setBounds(15, 10, 24, 24);

        lblTitulo = new JLabel("TRABAJADORES DEL TALLER", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(GOLD);
        lblTitulo.setBounds(0, 8, 720, 35);

        int lblW = 90, txtW = 220, row1Y = 55, row2Y = 95, lblX1 = 25, txtX1 = 115, lblX2 = 365, txtX2 = 455;

        lblNombre = crearLabel("Nombre:", lblX1, row1Y, lblW);
        txtNombre = crearCampoTexto(txtX1, row1Y, txtW);
        lblCargo = crearLabel("Cargo:", lblX2, row1Y, lblW);
        txtCargo = crearCampoTexto(txtX2, row1Y, txtW);

        lblTelefono = crearLabel("Teléfono:", lblX1, row2Y, lblW);
        txtTelefono = crearCampoTexto(txtX1, row2Y, txtW);
        lblEmail = crearLabel("Email:", lblX2, row2Y, lblW);
        txtEmail = crearCampoTexto(txtX2, row2Y, txtW);

        int btnY = 140, btnW = 105, btnH = 32;
        btnGuardar = crearBotonRedondeado("GUARDAR", SENA_GREEN, 25, btnY, btnW, btnH);
        btnEditar = crearBotonRedondeado("EDITAR", GOLD_DARK, 140, btnY, btnW, btnH);
        btnEliminar = crearBotonRedondeado("ELIMINAR", new Color(200, 50, 50), 255, btnY, btnW, btnH);
        btnLimpiar = crearBotonRedondeado("LIMPIAR", new Color(100, 100, 100), 370, btnY, btnW, btnH);
        BTNCerrar = crearBotonRedondeado("CERRAR", new Color(70, 70, 70), 590, btnY, btnW, btnH);
        BTNCerrar.addActionListener(e -> dispose());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        TBLTrabajadores = new JTable();
        TBLTrabajadores.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        TBLTrabajadores.setRowHeight(30);
        TBLTrabajadores.setBackground(new Color(42, 42, 42));
        TBLTrabajadores.setForeground(Color.WHITE);
        TBLTrabajadores.setGridColor(new Color(70, 70, 70));
        TBLTrabajadores.setSelectionBackground(new Color(57, 169, 0, 180));
        TBLTrabajadores.setSelectionForeground(Color.WHITE);

        JTableHeader header = TBLTrabajadores.getTableHeader();
        header.setBackground(GOLD);
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        TBLTrabajadores.setDefaultRenderer(Object.class, center);

        scrollPane = new JScrollPane(TBLTrabajadores);
        scrollPane.setBounds(20, 185, 680, 255);
        scrollPane.getViewport().setBackground(new Color(42, 42, 42));
        scrollPane.getViewport().setOpaque(true);

        getContentPane().add(logoSena);
        getContentPane().add(lblTitulo);
        getContentPane().add(lblNombre); getContentPane().add(txtNombre);
        getContentPane().add(lblCargo); getContentPane().add(txtCargo);
        getContentPane().add(lblTelefono); getContentPane().add(txtTelefono);
        getContentPane().add(lblEmail); getContentPane().add(txtEmail);
        getContentPane().add(btnGuardar); getContentPane().add(btnEditar);
        getContentPane().add(btnEliminar); getContentPane().add(btnLimpiar);
        getContentPane().add(BTNCerrar);
        getContentPane().add(scrollPane);
    }

    private void limpiarCampos() {
        txtNombre.setText(""); txtCargo.setText("");
        txtTelefono.setText(""); txtEmail.setText("");
    }

    private void cargarDatos() {
        DefaultTableModel modelo = new DefaultTableModel(
            new String[]{"ID", "Nombre", "Cargo", "Teléfono", "Email"}, 0
        );
        TBLTrabajadores.setModel(modelo);
    }
}
