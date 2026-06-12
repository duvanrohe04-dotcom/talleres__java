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

public class FRMMotos extends JInternalFrame {

    private JLabel lblTitulo, lblMarca, lblModelo, lblPlaca, lblColor, lblCliente;
    private JTextField txtMarca, txtModelo, txtPlaca, txtColor, txtCliente;
    private javax.swing.JButton btnGuardar, btnEditar, btnEliminar, btnLimpiar, BTNCerrar;
    private JScrollPane scrollPane;
    private JTable TBLMotos;
    private SENALogo logoSena;

    public FRMMotos() {
        initComponents();
        cargarDatos();
    }

    private void initComponents() {
        setTitle("Motos del Taller");
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(720, 480);
        getContentPane().setLayout(null);
        getContentPane().setBackground(DARK_BG);

        logoSena = new SENALogo(24, true);
        logoSena.setBounds(15, 10, 24, 24);

        lblTitulo = new JLabel("MOTOS REGISTRADAS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(GOLD);
        lblTitulo.setBounds(0, 8, 720, 35);

        int lblW = 80, txtW = 180, lblX1 = 25, txtX1 = 105, lblX2 = 315, txtX2 = 395;
        int row1Y = 55, row2Y = 95, row3Y = 135;
        int bigTxtW = 280;

        lblMarca = crearLabel("Marca:", lblX1, row1Y, lblW);
        txtMarca = crearCampoTexto(txtX1, row1Y, txtW);
        lblModelo = crearLabel("Modelo:", lblX2, row1Y, lblW);
        txtModelo = crearCampoTexto(txtX2, row1Y, txtW);

        lblPlaca = crearLabel("Placa:", lblX1, row2Y, lblW);
        txtPlaca = crearCampoTexto(txtX1, row2Y, txtW);
        lblColor = crearLabel("Color:", lblX2, row2Y, lblW);
        txtColor = crearCampoTexto(txtX2, row2Y, txtW);

        lblCliente = crearLabel("Cliente:", lblX1, row3Y, 90);
        txtCliente = crearCampoTexto(txtX1, row3Y, bigTxtW);

        int btnY = 170, btnW = 105, btnH = 32;
        btnGuardar = crearBotonRedondeado("GUARDAR", SENA_GREEN, 25, btnY, btnW, btnH);
        btnEditar = crearBotonRedondeado("EDITAR", GOLD_DARK, 140, btnY, btnW, btnH);
        btnEliminar = crearBotonRedondeado("ELIMINAR", new Color(200, 50, 50), 255, btnY, btnW, btnH);
        btnLimpiar = crearBotonRedondeado("LIMPIAR", new Color(100, 100, 100), 370, btnY, btnW, btnH);
        BTNCerrar = crearBotonRedondeado("CERRAR", new Color(70, 70, 70), 590, btnY, btnW, btnH);
        BTNCerrar.addActionListener(e -> dispose());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        TBLMotos = new JTable();
        TBLMotos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        TBLMotos.setRowHeight(30);
        TBLMotos.setBackground(new Color(42, 42, 42));
        TBLMotos.setForeground(Color.WHITE);
        TBLMotos.setGridColor(new Color(70, 70, 70));
        TBLMotos.setSelectionBackground(new Color(57, 169, 0, 180));
        TBLMotos.setSelectionForeground(Color.WHITE);

        JTableHeader header = TBLMotos.getTableHeader();
        header.setBackground(GOLD);
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        TBLMotos.setDefaultRenderer(Object.class, center);

        scrollPane = new JScrollPane(TBLMotos);
        scrollPane.setBounds(20, 215, 680, 225);
        scrollPane.getViewport().setBackground(new Color(42, 42, 42));
        scrollPane.getViewport().setOpaque(true);

        getContentPane().add(logoSena);
        getContentPane().add(lblTitulo);
        getContentPane().add(lblMarca); getContentPane().add(txtMarca);
        getContentPane().add(lblModelo); getContentPane().add(txtModelo);
        getContentPane().add(lblPlaca); getContentPane().add(txtPlaca);
        getContentPane().add(lblColor); getContentPane().add(txtColor);
        getContentPane().add(lblCliente); getContentPane().add(txtCliente);
        getContentPane().add(btnGuardar); getContentPane().add(btnEditar);
        getContentPane().add(btnEliminar); getContentPane().add(btnLimpiar);
        getContentPane().add(BTNCerrar);
        getContentPane().add(scrollPane);
    }

    private void limpiarCampos() {
        txtMarca.setText(""); txtModelo.setText("");
        txtPlaca.setText(""); txtColor.setText(""); txtCliente.setText("");
    }

    private void cargarDatos() {
        DefaultTableModel modelo = new DefaultTableModel(
            new String[]{"ID", "Marca", "Modelo", "Placa", "Color", "Cliente"}, 0
        );
        TBLMotos.setModel(modelo);
    }
}
