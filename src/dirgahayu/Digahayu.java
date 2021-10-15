/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dirgahayu;
import usu.widget.util.WidgetUtilities;
/**
 *
 * @author Mini Hp
 */
public class Digahayu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        WidgetUtilities.invokeLater(() -> {
           frmUtama utama=frmUtama.getInstance();
//           MainFrame utama=MainFrame.getInstance();
           utama.isWall();
           utama.setVisible(true);
       });
    }
    
}
