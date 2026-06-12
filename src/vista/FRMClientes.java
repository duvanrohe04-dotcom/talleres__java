package vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class FRMClientes extends JInternalFrame {

    private JLabel lblTitulo;
    private JScrollPane scrollPane;
    private JTable TBLClientes;
    private JButton BTNCerrar;

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
        setSize(700, 450);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(28, 28, 28));

        lblTitulo = new JLabel("CLIENTES REGISTRADOS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(255, 215, 0));
        lblTitulo.setBounds(0, 15, 700, 40);

        TBLClientes = new JTable();
        TBLClientes.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        TBLClientes.setRowHeight(28);
        TBLClientes.setBackground(new Color(45, 45, 45));
        TBLClientes.setForeground(Color.WHITE);
        TBLClientes.setGridColor(new Color(80, 80, 80));
        TBLClientes.setSelectionBackground(new Color(255, 215, 0));
        TBLClientes.setSelectionForeground(Color.BLACK);

        JTableHeader header = TBLClientes.getTableHeader();
        header.setBackground(new Color(255, 215, 0));
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        TBLClientes.setDefaultRenderer(Object.class, center);

        scrollPane = new JScrollPane(TBLClientes);
        scrollPane.setBounds(20, 65, 660, 310);
        scrollPane.getViewport().setBackground(new Color(45, 45, 45));

        BTNCerrar = new JButton("CERRAR");
        BTNCerrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        BTNCerrar.setForeground(new Color(28, 28, 28));
        BTNCerrar.setBackground(new Color(255, 215, 0));
        BTNCerrar.setFocusPainted(false);
        BTNCerrar.setBorderPainted(false);
        BTNCerrar.setBounds(560, 385, 120, 35);
        BTNCerrar.addActionListener(e -> dispose());

        getContentPane().add(lblTitulo);
        getContentPane().add(scrollPane);
        getContentPane().add(BTNCerrar);
    }

    private void cargarDatos() {
        DefaultTableModel modelo = new DefaultTableModel(
            new String[]{"ID", "Nombre", "Documento", "Dirección", "Celular"}, 0
        );
        TBLClientes.setModel(modelo);
    }
}
