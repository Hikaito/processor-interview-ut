import DataManage.Database;
import DataManage.StateManager;
import GUI.*;
import Log.Log;

import javax.swing.*;
import java.awt.*;

/**
 * Class responsible for program window
 */
public class GUI {
    private JFrame jframe = null;
    private StateManager state;

    /**
     * Launch program window
     * @param db DataManage.Database object used by window
     */
    public void initialize(Database db){
        // attempt to use native system window design
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            Log.log("Unable to support native look and feel. Using default appearance.");
        }

        // permit initialization only once
        if (jframe == null){
            // create jframe
            jframe = new JFrame("Interview Program Test");

            // create state manager
            state = new StateManager(db, jframe);

            //initialize jframe
            jframe.setSize(850,500);
            jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //enable exit button

            // top pane
            JPanel top = new JPanel();
            top.add(Buttons.generateFileButton(state));
            top.add(Buttons.generateOpenButton(state));
            top.add(Buttons.generateClearRecordButton(state));
            top.add(Buttons.generateButtonLabel(state));

            // bottom pane
            ScrollableTable table = ScrollableTable.make(state);

            JSplitPane pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, top, table);
            pane.setOneTouchExpandable(true);

            // set minimum size for pane components
            Dimension min = new Dimension(100,50);
            top.setMinimumSize(min);
            table.setMinimumSize(min);

            // set starting proportions
            pane.setDividerLocation(200);

            jframe.add(pane);

            // render jframe
            jframe.setVisible(true);
        }
    }
}
