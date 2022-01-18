/*
 * To the extent possible under law, the ImageJ developers have waived
 * all copyright and related or neighboring rights to this tutorial code.
 *
 * See the CC0 1.0 Universal license for details:
 *     http://creativecommons.org/publicdomain/zero/1.0/
 */

package spatan;

import ij.plugin.PlugIn;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * An interface for processing sample point data into polar graphs and interpolated grids.
 *
 * @author Richard Marszalek
 */
public class SpatialAnalytics implements PlugIn {
	
	@Override
	public void run(String arg) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI gui = new MainGUI("Spatial Analytics");
					gui.setVisible(true);
					UIManager.put("swing.boldMetal", Boolean.FALSE); 
					//MainGUI.createAndShowGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
        });
	}
}