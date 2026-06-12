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

public class FRMMotos extends JInternalFrame {

    private JLabel lblTitulo;
    private JScrollPane scrollPane;
    private JTable TBLMotos;
    private JButton BTNCerrar;

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
        setSize(700, 450);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(28, 28, 28));

        lblTitulo = new JLabel("MOTOS REGISTRADAS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(255, 215, 0));
        lblTitulo.setBounds(0, 15, 700, 40);

        TBLMotos = new JTable();
        TBLMotos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        TBLMotos.setRowHeight(28);
        TBLMotos.setBackground(new Color(45, 45, 45));
        TBLMotos.setForeground(Color.WHITE);
        TBLMotos.setGridColor(new Color(80, 80, 80));
        TBLMotos.setSelectionBackground(new Color(255, 215, 0));
        TBLMotos.setSelectionForeground(Color.BLACK);

        JTableHeader header = TBLMotos.getTableHeader();
        header.setBackground(new Color(255, 215, 0));
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        TBLMotos.setDefaultRenderer(Object.class, center);

        scrollPane = new JScrollPane(TBLMotos);
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
            new String[]{"ID", "Marca", "Modelo", "Placa", "Color", "Cliente"}, 0
        );
        TBLMotos.setModel(modelo);
    }
}
