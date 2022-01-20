package spatan;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ij.measure.ResultsTable;
import ij.IJ;

//import org.scijava.table.*;

public class TablePanel extends JPanel implements ActionListener {
	JLabel tblLabel, xLabel, yLabel, zLabel;
	JTextField xTextField, yTextField, zTextField;
    JButton openButton, closeButton, saveButton;
    JFileChooser fc;
    JTable table;
    JScrollPane tableScrollPane;
    
	public TablePanel() {
		DelimitedFile2TableIO tbl = new DelimitedFile2TableIO("C:\\Users\\Dan\\Desktop\\Image 12 cd31.csv");
		IJ.log("Did my stupid class work?");
        table = new JTable(tbl.mdlImport);
        table.setPreferredScrollableViewportSize(new Dimension(500, 500));
        table.setFillsViewportHeight(true);
        tableScrollPane = new JScrollPane(table);

        IJ.log("Column number is " + getColumnIndex(table, "XM"));
        IJ.log("Column name of index is " + (String)table.getColumnName(getColumnIndex(table, "XM")));
		
        ResultsTable rt = ResultsTable.getResultsTable();
        int count = rt.getCounter();
        
        IJ.log("Results Table Counter: " + count + ".");
        
        if (count == 0)
        {
        	IJ.log("Checking any other tables available.");
        }

        fc = new JFileChooser();
        openButton = new JButton("Open a File...");
        openButton.addActionListener(this);

        saveButton = new JButton("Save a File...");
        saveButton.addActionListener(this);

        tblLabel = new JLabel("Data Table");
        closeButton = new JButton("Close");
        xLabel = new JLabel("X");
        xTextField = new JTextField("X.m");
        yLabel = new JLabel("Y");
        yTextField = new JTextField("Y.m");
        zLabel = new JLabel("Z");
        zTextField = new JTextField("Area");
        
        GroupLayout tPanelLayout = new GroupLayout(this);
        setLayout(tPanelLayout);

        tPanelLayout.setHorizontalGroup(
        		tPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tableScrollPane)
                .addGroup(tPanelLayout.createSequentialGroup()
                    .addComponent(tblLabel)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(tPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(openButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(saveButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(closeButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(xLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(xTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(yLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(yTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(zLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(zTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );
        tPanelLayout.setVerticalGroup(
        		tPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tPanelLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(tblLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(tPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(openButton)
                        .addComponent(saveButton)
                        .addComponent(closeButton)
                        .addComponent(xLabel)
                        .addComponent(xTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(yLabel)
                        .addComponent(yTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(zLabel)
                        .addComponent(zTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(41, Short.MAX_VALUE))
            );
	}    
	
	public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(TablePanel.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                IJ.log("Opening: " + file.getName() + ".");
            } else {
            	IJ.log("Open command cancelled by user.");
            }

        //Handle save button action.
        } else if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(TablePanel.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would save the file.
                IJ.log("Saving: " + file.getName() + ".");
            } else {
            	IJ.log("Save command cancelled by user.");
            }
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = TablePanel.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            IJ.log("Couldn't find file: " + path);
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
        frame.add(new TablePanel());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public Object getData(JTable table, int row_index, int col_index){
    	  return table.getModel().getValueAt(row_index, col_index);
    }  
    
    public int getColumnIndex(JTable table, String colName) {
        for (int i = 0; i < table.getColumnCount(); i++)
        {
        	if (table.getColumnName(i).equals(colName))
        	{
        		return i;
        	}
        }
        IJ.log("Could not find column with name " + colName);
        return -1;
    }

    public float[] getColumnValues(JTable table, String colName)
    {
    	int colInd = getColumnIndex(table, colName);
    	int numRows = table.getRowCount();
    	float[] colValues = new float[numRows];
    	for (int i = 0; i < numRows; i++)
        {
    		colValues[i] = (float)Float.parseFloat((String)table.getModel().getValueAt(i, colInd));
        }
    	return colValues;
    }
    
    public float getMaxValue(JTable table, String colName)
    {
    	int colInd = getColumnIndex(table, colName);
    	int numRows = table.getRowCount();
    	float maxVal = 0, nextInt;
    	for (int i = 0; i < numRows; i++)
        {
    		nextInt = (float)Float.parseFloat((String)table.getModel().getValueAt(i, colInd));
    		if (nextInt > maxVal)
    		{
    			maxVal = nextInt;
    		}
        }
    	return maxVal;
    }
}
