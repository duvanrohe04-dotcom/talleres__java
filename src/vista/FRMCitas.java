package vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
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

public class FRMCitas extends JInternalFrame {

    private JLabel lblTitulo, lblCliente, lblMoto, lblFecha, lblEstado;
    private JTextField txtCliente, txtMoto, txtFecha;
    private JComboBox<String> cmbEstado;
    private javax.swing.JButton btnGuardar, btnEditar, btnEliminar, btnLimpiar, BTNCerrar;
    private JScrollPane scrollPane;
    private JTable TBLCitas;
    private SENALogo logoSena;

    public FRMCitas() {
        initComponents();
        cargarDatos();
    }

    private void initComponents() {
        setTitle("Citas Programadas");
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(760, 480);
        getContentPane().setLayout(null);
        getContentPane().setBackground(DARK_BG);

        logoSena = new SENALogo(24, true);
        logoSena.setBounds(15, 10, 24, 24);

        lblTitulo = new JLabel("CITAS PROGRAMADAS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(GOLD);
        lblTitulo.setBounds(0, 8, 760, 35);

        int lblW = 80, txtW = 210, row1Y = 55, row2Y = 95, lblX1 = 25, txtX1 = 105, lblX2 = 345, txtX2 = 425;

        lblCliente = crearLabel("Cliente:", lblX1, row1Y, lblW);
        txtCliente = crearCampoTexto(txtX1, row1Y, txtW);
        lblMoto = crearLabel("Moto:", lblX2, row1Y, lblW);
        txtMoto = crearCampoTexto(txtX2, row1Y, txtW);

        lblFecha = crearLabel("Fecha:", lblX1, row2Y, lblW);
        txtFecha = crearCampoTexto(txtX1, row2Y, txtW);
        lblEstado = crearLabel("Estado:", lblX2, row2Y, lblW);
        cmbEstado = new JComboBox<>(new String[]{"Pendiente", "En proceso", "Completada", "Cancelada"});
        cmbEstado.setBounds(txtX2, row2Y, 210, 28);
        cmbEstado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cmbEstado.setBackground(new Color(50, 50, 50));
        cmbEstado.setForeground(Color.WHITE);

        int btnY = 140, btnW = 105, btnH = 32;
        btnGuardar = crearBotonRedondeado("GUARDAR", SENA_GREEN, 25, btnY, btnW, btnH);
        btnEditar = crearBotonRedondeado("EDITAR", GOLD_DARK, 140, btnY, btnW, btnH);
        btnEliminar = crearBotonRedondeado("ELIMINAR", new Color(200, 50, 50), 255, btnY, btnW, btnH);
        btnLimpiar = crearBotonRedondeado("LIMPIAR", new Color(100, 100, 100), 370, btnY, btnW, btnH);
        BTNCerrar = crearBotonRedondeado("CERRAR", new Color(70, 70, 70), 630, btnY, btnW, btnH);
        BTNCerrar.addActionListener(e -> dispose());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        TBLCitas = new JTable();
        TBLCitas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        TBLCitas.setRowHeight(30);
        TBLCitas.setBackground(new Color(42, 42, 42));
        TBLCitas.setForeground(Color.WHITE);
        TBLCitas.setGridColor(new Color(70, 70, 70));
        TBLCitas.setSelectionBackground(new Color(57, 169, 0, 180));
        TBLCitas.setSelectionForeground(Color.WHITE);

        JTableHeader header = TBLCitas.getTableHeader();
        header.setBackground(GOLD);
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        TBLCitas.setDefaultRenderer(Object.class, center);

        scrollPane = new JScrollPane(TBLCitas);
        scrollPane.setBounds(20, 185, 720, 255);
        scrollPane.getViewport().setBackground(new Color(42, 42, 42));
        scrollPane.getViewport().setOpaque(true);

        getContentPane().add(logoSena);
        getContentPane().add(lblTitulo);
        getContentPane().add(lblCliente); getContentPane().add(txtCliente);
        getContentPane().add(lblMoto); getContentPane().add(txtMoto);
        getContentPane().add(lblFecha); getContentPane().add(txtFecha);
        getContentPane().add(lblEstado); getContentPane().add(cmbEstado);
        getContentPane().add(btnGuardar); getContentPane().add(btnEditar);
        getContentPane().add(btnEliminar); getContentPane().add(btnLimpiar);
        getContentPane().add(BTNCerrar);
        getContentPane().add(scrollPane);
    }

    private void limpiarCampos() {
        txtCliente.setText(""); txtMoto.setText("");
        txtFecha.setText(""); cmbEstado.setSelectedIndex(0);
    }

    private void cargarDatos() {
        DefaultTableModel modelo = new DefaultTableModel(
            new String[]{"ID", "Cliente", "Moto", "Fecha", "Estado"}, 0
        );
        TBLCitas.setModel(modelo);
    }
}
