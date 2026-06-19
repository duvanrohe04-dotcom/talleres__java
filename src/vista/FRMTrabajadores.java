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

public class FRMTrabajadores extends JInternalFrame {

    private JLabel lblTitulo, lblNombre, lblCargo, lblTelefono, lblEmail;
    private JTextField txtNombre, txtCargo, txtTelefono, txtEmail;
    private javax.swing.JButton btnGuardar, btnEditar, btnEliminar, btnLimpiar, BTNCerrar;
    private JScrollPane scrollPane;
    private JTable TBLTrabajadores;
    private MBLogo logoTaller;

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
        setSize(740, 500);
        getContentPane().setLayout(null);
        getContentPane().setBackground(DARK_BG);

        logoTaller = new MBLogo(22, true);
        logoTaller.setBounds(15, 10, 22, 22);

        JPanel header = crearHeaderBar("TRABAJADORES DEL TALLER", logoTaller, 740);
        getContentPane().add(header);

        JPanel panelForm = crearPanelCard(15, 50, 710, 110);
        panelForm.setLayout(null);

        int lblW = 90, txtW = 220, row1Y = 12, row2Y = 55, lblX1 = 15, txtX1 = 105, lblX2 = 365, txtX2 = 455;

        lblNombre = crearLabel("Nombre:", lblX1, row1Y, lblW);
        txtNombre = crearCampoTexto(txtX1, row1Y, txtW);
        lblCargo = crearLabel("Cargo:", lblX2, row1Y, lblW);
        txtCargo = crearCampoTexto(txtX2, row1Y, txtW);

        lblTelefono = crearLabel("Telefono:", lblX1, row2Y, lblW);
        txtTelefono = crearCampoTexto(txtX1, row2Y, txtW);
        lblEmail = crearLabel("Email:", lblX2, row2Y, lblW);
        txtEmail = crearCampoTexto(txtX2, row2Y, txtW);

        panelForm.add(lblNombre); panelForm.add(txtNombre);
        panelForm.add(lblCargo); panelForm.add(txtCargo);
        panelForm.add(lblTelefono); panelForm.add(txtTelefono);
        panelForm.add(lblEmail); panelForm.add(txtEmail);
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

        TBLTrabajadores = new JTable();
        TBLTrabajadores.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        TBLTrabajadores.setRowHeight(32);
        TBLTrabajadores.setBackground(DARK_BG);
        TBLTrabajadores.setForeground(Color.WHITE);
        TBLTrabajadores.setGridColor(TABLE_GRID);
        TBLTrabajadores.setSelectionBackground(TABLE_SELECTION);
        TBLTrabajadores.setSelectionForeground(Color.WHITE);

        JTableHeader headerTable = TBLTrabajadores.getTableHeader();
        headerTable.setBackground(TABLE_HEADER_BG);
        headerTable.setForeground(TABLE_HEADER_FG);
        headerTable.setFont(new Font("Segoe UI", Font.BOLD, 13));

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        TBLTrabajadores.setDefaultRenderer(Object.class, center);

        scrollPane = new JScrollPane(TBLTrabajadores);
        scrollPane.setBounds(15, 228, 710, 240);
        scrollPane.getViewport().setBackground(DARK_BG);
        scrollPane.getViewport().setOpaque(true);
        scrollPane.setBorder(null);

        getContentPane().add(scrollPane);
    }

    private void limpiarCampos() {
        txtNombre.setText(""); txtCargo.setText("");
        txtTelefono.setText(""); txtEmail.setText("");
    }

    private void cargarDatos() {
        DefaultTableModel modelo = new DefaultTableModel(
            new String[]{"ID", "Nombre", "Cargo", "Telefono", "Email"}, 0
        );
        TBLTrabajadores.setModel(modelo);
    }
}
