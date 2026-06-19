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

public class FRMMotos extends JInternalFrame {

    private JLabel lblTitulo, lblMarca, lblModelo, lblPlaca, lblColor, lblCliente;
    private JTextField txtMarca, txtModelo, txtPlaca, txtColor, txtCliente;
    private javax.swing.JButton btnGuardar, btnEditar, btnEliminar, btnLimpiar, BTNCerrar;
    private JScrollPane scrollPane;
    private JTable TBLMotos;
    private MBLogo logoTaller;

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
        setSize(740, 500);
        getContentPane().setLayout(null);
        getContentPane().setBackground(DARK_BG);

        logoTaller = new MBLogo(22, true);
        logoTaller.setBounds(15, 10, 22, 22);

        JPanel header = crearHeaderBar("MOTOS REGISTRADAS", logoTaller, 740);
        getContentPane().add(header);

        JPanel panelForm = crearPanelCard(15, 50, 710, 140);
        panelForm.setLayout(null);

        int lblW = 80, txtW = 180, lblX1 = 15, txtX1 = 95, lblX2 = 325, txtX2 = 405;
        int row1Y = 12, row2Y = 52, row3Y = 92;
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

        panelForm.add(lblMarca); panelForm.add(txtMarca);
        panelForm.add(lblModelo); panelForm.add(txtModelo);
        panelForm.add(lblPlaca); panelForm.add(txtPlaca);
        panelForm.add(lblColor); panelForm.add(txtColor);
        panelForm.add(lblCliente); panelForm.add(txtCliente);
        getContentPane().add(panelForm);

        JPanel panelBotones = crearPanelCard(15, 198, 710, 50);
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

        TBLMotos = new JTable();
        TBLMotos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        TBLMotos.setRowHeight(32);
        TBLMotos.setBackground(DARK_BG);
        TBLMotos.setForeground(Color.WHITE);
        TBLMotos.setGridColor(TABLE_GRID);
        TBLMotos.setSelectionBackground(TABLE_SELECTION);
        TBLMotos.setSelectionForeground(Color.WHITE);

        JTableHeader headerTable = TBLMotos.getTableHeader();
        headerTable.setBackground(TABLE_HEADER_BG);
        headerTable.setForeground(TABLE_HEADER_FG);
        headerTable.setFont(new Font("Segoe UI", Font.BOLD, 13));

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        TBLMotos.setDefaultRenderer(Object.class, center);

        scrollPane = new JScrollPane(TBLMotos);
        scrollPane.setBounds(15, 258, 710, 210);
        scrollPane.getViewport().setBackground(DARK_BG);
        scrollPane.getViewport().setOpaque(true);
        scrollPane.setBorder(null);

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
