package lanzaayuda;

import java.awt.event.*;
import java.net.URL;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/* MenuDemo.java requires images/middle.gif. */

 /*
 * This class is just like MenuLookDemo, except the menu items
 * actually do something, thanks to event listeners.
 */
public class LanzaAyuda{

    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("Ayuda");
        menuBar.add(menu);

        //a group of JMenuItems
        JMenuItem menuItem1 = new JMenuItem("Contenido de Ayuda");
        menu.add(menuItem1);
        JMenuItem menuItem = new JMenuItem("About");
        menu.add(menuItem);
        
        // Asocia la tecla F1 al menuItem "Contenido de Ayuda"
        menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0));
        
        // Creamos objetos helpSet y helpBroker
        HelpSet hs = obtenFicAyuda();
        HelpBroker hb = hs.createHelpBroker();
        
        hb.enableHelpOnButton(menuItem1,"top",hs);
        hb.enableHelpKey(menuItem1, "top", hs);
        
        return menuBar;
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Aplicacion Swing con Ayuda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        LanzaAyuda demo = new LanzaAyuda();
        frame.setJMenuBar(demo.createMenuBar());

        //Display the window.
        frame.setSize(450, 260);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public HelpSet obtenFicAyuda() {
        try {
            ClassLoader cl = LanzaAyuda.class.getClassLoader();
            //File file = new File(LanzaAyuda.class.getResource("lanzaayuda/help/helpset.hs").getFile());
            URL url = new URL("jar:file:LanzaAyuda.jar!/lanzaayuda/help/helpset.hs");
            //URL url = file.toURI().toURL();
            // crea un objeto Helpset
            HelpSet hs = new HelpSet(null, url);
            return hs;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Fichero HelpSet no encontrado");
            return null;
        }
    }

}
