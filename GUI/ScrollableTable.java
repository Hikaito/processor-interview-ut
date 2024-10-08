package GUI;

import DataManager.StateManager;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import java.util.ArrayList;

/**
 * Table scrolling panel
 */
public class ScrollableTable extends JScrollPane {
    private JTable table;
    private DisplayTableModel tableModel;

    /**
     * Concealed constructor
     * @param table JTable, passed to JScrollPane superclass
     */
    private ScrollableTable(JTable table){
        super(table);
    }

    /**
     * Factory constructor for scrollable table
     * @param state State object to register the object to, or null if no state desired
     * @return new ScrollableTable object
     */
    public static ScrollableTable make(StateManager state){
        // construction chain: table model
        DisplayTableModel tableModel = new DisplayTableModel();

        // construction chain: table
        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);

        // construction chain: scrollable table
        ScrollableTable scrollableTable = new ScrollableTable(table);

        // register internal references
        scrollableTable.table = table;
        scrollableTable.tableModel = tableModel;

        //register to state
        if (state != null) state.registerTablePanel(scrollableTable);

        return scrollableTable;
    }


    /**
     * Set data in table to new arrayList of data
     * @param list array list of table rows, given as arrays of strings
     * @param names array of titles to associate with rows
     */
    public void updateData(ArrayList<String[]> list, String[] names){
        if (list == null) return;// reject null values

        // set table contents to copy of given list
        tableModel.setRecords(list, names);
        updateGui();    // mark table for redraw
    }

    /**
     * Mark table for re-rendering when redrawn
     */
    public void updateGui(){
        table.tableChanged(new TableModelEvent(tableModel, TableModelEvent.HEADER_ROW));
//        table.updateUI();
//        table.revalidate();
//        table.repaint();
//        this.revalidate();
    }
}
