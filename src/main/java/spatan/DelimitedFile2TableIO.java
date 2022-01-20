package spatan;

import ij.IJ;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class DelimitedFile2TableIO {
	private static String sep = ",";
	private static boolean header = true;
	DefaultTableModel mdlImport;
	
	public DelimitedFile2TableIO (String filename) {
		
		    BufferedReader br = null;
		    try{
		      br = new BufferedReader(new FileReader(filename));
		      if (header)
		      {
		    	  String firstLine = br.readLine().trim();
		    	  String[] colNames = firstLine.split(sep);
		    	  mdlImport = new DefaultTableModel(colNames, 0);
		      }
		      Object[] tableLines = br.lines().toArray();
		      for (int i = 0; i < tableLines.length; i++)
		      {
		    	  String line = tableLines[i].toString().trim();
		    	  String[] dataRow = line.split(sep);
		    	  mdlImport.addRow(dataRow);
		    			  
		      }
		    }catch(IOException exp){
		      IJ.log("Error while reading file " + exp.getMessage());
		    }finally {
		      try {
		        // Close the stream
		        if(br != null){
		          br.close();
		        }
		      } catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		      }
		    }  
	}
}
