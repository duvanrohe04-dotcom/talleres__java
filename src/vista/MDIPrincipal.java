package vista;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MDIPrincipal extends JFrame {

    private JDesktopPane escritorio;
    private JMenuBar menuBar;
    private JMenu MNUAdministrar;
    private JMenuItem ItemClientes;
    private JMenuItem ItemMotos;
    private JMenuItem ItemTrabajadores;
    private JMenuItem ItemCitas;
    private JMenuItem ItemSalir;
    private frmDashboard dashboard;

    public MDIPrincipal() {
        initComponents();
        abrirDashboard();
    }

    private void initComponents() {
        setTitle("Multimarcas Brazo - Taller de Motos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        escritorio = new JDesktopPane();
        escritorio.setBackground(new Color(25, 25, 25));
        setContentPane(escritorio);

        menuBar = new JMenuBar();
        menuBar.setBackground(new Color(45, 45, 45));

        MNUAdministrar = new JMenu("Administrar");
        MNUAdministrar.setForeground(Color.WHITE);
        MNUAdministrar.setFont(MNUAdministrar.getFont().deriveFont(14f));

        ItemClientes = new JMenuItem("Clientes");
        ItemClientes.setFont(ItemClientes.getFont().deriveFont(13f));
        ItemClientes.addActionListener(e -> abrirVentana(new FRMClientes(), 50, 50));

        ItemMotos = new JMenuItem("Motos");
        ItemMotos.setFont(ItemMotos.getFont().deriveFont(13f));
        ItemMotos.addActionListener(e -> abrirVentana(new FRMMotos(), 70, 70));

        ItemTrabajadores = new JMenuItem("Trabajadores");
        ItemTrabajadores.setFont(ItemTrabajadores.getFont().deriveFont(13f));
        ItemTrabajadores.addActionListener(e -> abrirVentana(new FRMTrabajadores(), 90, 90));

        ItemCitas = new JMenuItem("Citas");
        ItemCitas.setFont(ItemCitas.getFont().deriveFont(13f));
        ItemCitas.addActionListener(e -> abrirVentana(new FRMCitas(), 110, 110));

        ItemSalir = new JMenuItem("Salir");
        ItemSalir.setFont(ItemSalir.getFont().deriveFont(13f));
        ItemSalir.addActionListener(e -> System.exit(0));

        MNUAdministrar.add(ItemClientes);
        MNUAdministrar.add(ItemMotos);
        MNUAdministrar.add(ItemTrabajadores);
        MNUAdministrar.add(ItemCitas);
        MNUAdministrar.addSeparator();
        MNUAdministrar.add(ItemSalir);
        menuBar.add(MNUAdministrar);

        setJMenuBar(menuBar);
    }

    private void abrirDashboard() {
        dashboard = new frmDashboard(this);
        dashboard.setVisible(true);
        escritorio.add(dashboard);
    }

    private void abrirVentana(javax.swing.JInternalFrame ventana, int offsetX, int offsetY) {
        ventana.setLocation(offsetX, offsetY);
        escritorio.add(ventana);
        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new MDIPrincipal().setVisible(true);
        });
    }
}
