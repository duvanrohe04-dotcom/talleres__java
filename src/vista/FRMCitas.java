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

public class FRMCitas extends JInternalFrame {

    private JLabel lblTitulo;
    private JScrollPane scrollPane;
    private JTable TBLCitas;
    private JButton BTNCerrar;

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
        setSize(750, 450);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(28, 28, 28));

        lblTitulo = new JLabel("CITAS PROGRAMADAS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(255, 215, 0));
        lblTitulo.setBounds(0, 15, 750, 40);

        TBLCitas = new JTable();
        TBLCitas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        TBLCitas.setRowHeight(28);
        TBLCitas.setBackground(new Color(45, 45, 45));
        TBLCitas.setForeground(Color.WHITE);
        TBLCitas.setGridColor(new Color(80, 80, 80));
        TBLCitas.setSelectionBackground(new Color(255, 215, 0));
        TBLCitas.setSelectionForeground(Color.BLACK);

        JTableHeader header = TBLCitas.getTableHeader();
        header.setBackground(new Color(255, 215, 0));
        header.setForeground(Color.BLACK);
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        TBLCitas.setDefaultRenderer(Object.class, center);

        scrollPane = new JScrollPane(TBLCitas);
        scrollPane.setBounds(20, 65, 710, 310);
        scrollPane.getViewport().setBackground(new Color(45, 45, 45));

        BTNCerrar = new JButton("CERRAR");
        BTNCerrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        BTNCerrar.setForeground(new Color(28, 28, 28));
        BTNCerrar.setBackground(new Color(255, 215, 0));
        BTNCerrar.setFocusPainted(false);
        BTNCerrar.setBorderPainted(false);
        BTNCerrar.setBounds(610, 385, 120, 35);
        BTNCerrar.addActionListener(e -> dispose());

        getContentPane().add(lblTitulo);
        getContentPane().add(scrollPane);
        getContentPane().add(BTNCerrar);
    }

    private void cargarDatos() {
        DefaultTableModel modelo = new DefaultTableModel(
            new String[]{"ID", "Cliente", "Moto", "Fecha", "Estado"}, 0
        );
        TBLCitas.setModel(modelo);
    }
}
