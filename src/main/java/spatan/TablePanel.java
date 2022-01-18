package spatan;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ij.measure.ResultsTable;


public class SwingPanel extends JPanel implements ActionListener {
	   // initializing using constructor  
	static private final String newline = "\n";
    JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;
    
	public SwingPanel() {  
		super(new BorderLayout());
        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        
        String[] columnNames = {"First Name", "Last Name", "Sport", "# of Years", "Vegetarian"};
        
        Object[][] data = {
                {"Kathy", "Smith",
                 "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                 "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                 "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                 "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                 "Pool", new Integer(10), new Boolean(false)}
                };
        
        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
 
        JScrollPane logScrollPane = new JScrollPane(log);
        JScrollPane tableScrollPane = new JScrollPane(table);

        ResultsTable rt = ResultsTable.getResultsTable();
        int count = rt.getCounter();
        
        log.append("Results Table Counter: " + count + "." + newline);
        
        if (count == 0)
        {
            log.append("Checking any other tables available." + newline);
        }

        fc = new JFileChooser();
        //JLF Graphics Repository extracted from jar).
        openButton = new JButton("Open a File...", createImageIcon("images/Open16.gif"));
        openButton.addActionListener(this);

        //Create the save button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        saveButton = new JButton("Save a File...",
                                 createImageIcon("images/Save16.gif"));
        saveButton.addActionListener(this);

        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        //Add the buttons and the log to this panel.
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
        add(tableScrollPane);
	}    
	
	public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(SwingPanel.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                log.append("Opening: " + file.getName() + "." + newline);
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());

        //Handle save button action.
        } else if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(SwingPanel.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would save the file.
                log.append("Saving: " + file.getName() + "." + newline);
            } else {
                log.append("Save command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = SwingPanel.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("FileChooserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new SwingPanel());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
