package spatan;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;

import ij.IJ;

public class MainGUI extends JFrame implements ActionListener{
	//Declaring class variables
	JMenuBar menuBar;
	JMenu fileMenu, mappingMenu;
	JMenuItem fileOpenItem, fileImportItem, fileExportItem, interpItem, pointItem, configureItem;
	JFileChooser fc;
	TablePanel tp;
	CanvasPanel cp;
	LogPanel lp;
	OptionsPanel op;
	
	public MainGUI () {
		initComponents();
	}
	public MainGUI (String title) {
		this.setTitle(title);
		initComponents();
	}
	
	//getInsets method for obtaining frame dimensions
	
	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//DISPOSE_ON_CLOSE when finished debugging.
		
		//GroupLayout layout = new GroupLayout(getContentPane());
		Container pane = getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		tp = new TablePanel();
		//this.getContentPane().add(sp);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(tp, c);

		cp = new CanvasPanel(tp);
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(cp, c);
		
		//layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
		//layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));
		initMenuBar();
		pack();
	}
	
	private void initMenuBar() {
		menuBar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_A);
		fileMenu.getAccessibleContext().setAccessibleDescription("File input/output");
		menuBar.add(fileMenu);

		fileOpenItem = new JMenuItem("Open Project", KeyEvent.VK_T);
		fileOpenItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		fileOpenItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		fileOpenItem.addActionListener(this);
		fileOpenItem.setActionCommand("open");
		fileMenu.add(fileOpenItem);

		fileImportItem = new JMenuItem("Import");
		fileImportItem.setMnemonic(KeyEvent.VK_B);
		fileMenu.add(fileImportItem);
		
		fileExportItem = new JMenuItem("Export");
		fileExportItem.setMnemonic(KeyEvent.VK_D);
		fileMenu.add(fileExportItem);
		
		mappingMenu = new JMenu("Mapping");
		mappingMenu.setMnemonic(KeyEvent.VK_S);
		mappingMenu.getAccessibleContext().setAccessibleDescription("Representation/Interpolation");
		menuBar.add(mappingMenu);

		interpItem = new JMenuItem("Interpolation", KeyEvent.VK_T);
		interpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		interpItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		mappingMenu.add(interpItem);

		pointItem = new JMenuItem("Point Represent");
		pointItem.setMnemonic(KeyEvent.VK_B);
		mappingMenu.add(pointItem);
		
		configureItem = new JMenuItem("Configure");
		configureItem.setMnemonic(KeyEvent.VK_B);
		mappingMenu.add(configureItem);
		
		setJMenuBar(menuBar);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == fileOpenItem) {
			fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                IJ.log(file.getName());
			}
		}
	}
}
