package vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
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

public class FRMCitas extends JInternalFrame {

    private JLabel lblTitulo, lblCliente, lblMoto, lblFecha, lblEstado;
    private JTextField txtCliente, txtMoto, txtFecha;
    private JComboBox<String> cmbEstado;
    private javax.swing.JButton btnGuardar, btnEditar, btnEliminar, btnLimpiar, BTNCerrar;
    private JScrollPane scrollPane;
    private JTable TBLCitas;
    private MBLogo logoTaller;

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
        setSize(780, 500);
        getContentPane().setLayout(null);
        getContentPane().setBackground(DARK_BG);

        logoTaller = new MBLogo(22, true);
        logoTaller.setBounds(15, 10, 22, 22);

        JPanel header = crearHeaderBar("CITAS PROGRAMADAS", logoTaller, 780);
        getContentPane().add(header);

        JPanel panelForm = crearPanelCard(15, 50, 750, 110);
        panelForm.setLayout(null);

        int lblW = 80, txtW = 210, row1Y = 12, row2Y = 55, lblX1 = 15, txtX1 = 95, lblX2 = 340, txtX2 = 420;

        lblCliente = crearLabel("Cliente:", lblX1, row1Y, lblW);
        txtCliente = crearCampoTexto(txtX1, row1Y, txtW);
        lblMoto = crearLabel("Moto:", lblX2, row1Y, lblW);
        txtMoto = crearCampoTexto(txtX2, row1Y, txtW);

        lblFecha = crearLabel("Fecha:", lblX1, row2Y, lblW);
        txtFecha = crearCampoTexto(txtX1, row2Y, txtW);
        lblEstado = crearLabel("Estado:", lblX2, row2Y, lblW);
        cmbEstado = new JComboBox<>(new String[]{"Pendiente", "En proceso", "Completada", "Cancelada"});
        cmbEstado.setBounds(txtX2, row2Y, 210, 30);
        cmbEstado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cmbEstado.setBackground(DARK_INPUT);
        cmbEstado.setForeground(Color.WHITE);

        panelForm.add(lblCliente); panelForm.add(txtCliente);
        panelForm.add(lblMoto); panelForm.add(txtMoto);
        panelForm.add(lblFecha); panelForm.add(txtFecha);
        panelForm.add(lblEstado); panelForm.add(cmbEstado);
        getContentPane().add(panelForm);

        JPanel panelBotones = crearPanelCard(15, 168, 750, 50);
        panelBotones.setLayout(null);

        int btnY = 9, btnW = 105, btnH = 32;
        btnGuardar = crearBotonRedondeado("GUARDAR", RED_PRIMARY, 15, btnY, btnW, btnH);
        btnEditar = crearBotonRedondeado("EDITAR", RED_DARK, 130, btnY, btnW, btnH);
        btnEliminar = crearBotonRedondeado("ELIMINAR", new Color(180, 15, 15), 245, btnY, btnW, btnH);
        btnLimpiar = crearBotonRedondeado("LIMPIAR", new Color(70, 70, 70), 360, btnY, btnW, btnH);
        BTNCerrar = crearBotonRedondeado("CERRAR", new Color(45, 45, 45), 630, btnY, btnW, btnH);
        BTNCerrar.addActionListener(e -> dispose());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        panelBotones.add(btnGuardar); panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar); panelBotones.add(btnLimpiar);
        panelBotones.add(BTNCerrar);
        getContentPane().add(panelBotones);

        TBLCitas = new JTable();
        TBLCitas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        TBLCitas.setRowHeight(32);
        TBLCitas.setBackground(DARK_BG);
        TBLCitas.setForeground(Color.WHITE);
        TBLCitas.setGridColor(TABLE_GRID);
        TBLCitas.setSelectionBackground(TABLE_SELECTION);
        TBLCitas.setSelectionForeground(Color.WHITE);

        JTableHeader headerTable = TBLCitas.getTableHeader();
        headerTable.setBackground(TABLE_HEADER_BG);
        headerTable.setForeground(TABLE_HEADER_FG);
        headerTable.setFont(new Font("Segoe UI", Font.BOLD, 13));

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        TBLCitas.setDefaultRenderer(Object.class, center);

        scrollPane = new JScrollPane(TBLCitas);
        scrollPane.setBounds(15, 228, 750, 240);
        scrollPane.getViewport().setBackground(DARK_BG);
        scrollPane.getViewport().setOpaque(true);
        scrollPane.setBorder(null);

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
