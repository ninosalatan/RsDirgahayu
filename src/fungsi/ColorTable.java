/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Owner
 */
public class ColorTable extends DefaultTableCellRenderer {
	private final Map<String, Color> colorMap = new HashMap<>();
	 @Override
     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
             int column) {
         setBackground(null);
         super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
         getColorForCell(row, column).ifPresent(this::setBackground);
         return this;
     }
	 public void setColorForCell(int row, int col, Color color) {
         colorMap.put(row + ":" + col, color);
     }

     public Optional<Color> getColorForCell(int row, int col) {
         return Optional.ofNullable(colorMap.get(row + ":" + col));
     }

}
