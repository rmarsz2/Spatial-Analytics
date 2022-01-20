package spatan;

import javax.swing.JPanel;
import javax.swing.*;

import com.jogamp.opengl.glu.GLU;

import ij.IJ;

import com.google.common.collect.Table;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;
import static com.jogamp.opengl.GL.*;  // GL constants
import static com.jogamp.opengl.GL2.*; // GL2 constants

import java.util.Arrays;
import java.util.Collections;

public class CanvasPanel extends JPanel {//implements ActionListener {
	private GLU glu;
	GLProfile glprofile = GLProfile.getDefault();
	GLCapabilities glcapabilities = new GLCapabilities(glprofile);
	final GLCanvas glcanvas = new GLCanvas( glcapabilities );
	JTable refTbl;
	
	public CanvasPanel(TablePanel tp) {  
		refTbl = tp.table;
		glcanvas.addGLEventListener( new GLEventListener() {
	      	 float[] arrayX = tp.getColumnValues(refTbl, "XM");
	      	 float[] arrayY = tp.getColumnValues(refTbl, "YM");
	      	 float[] arrayZ = tp.getColumnValues(refTbl, "Area");
	         float maxArrayX = tp.getMaxValue(refTbl, "XM");
	         float maxArrayY = tp.getMaxValue(refTbl, "YM");
			
			@Override
			public void init( GLAutoDrawable glautodrawable ) {
				GL2 gl = glautodrawable.getGL().getGL2();      // get the OpenGL graphics context
			      glu = new GLU();                         // get GL Utilities
			      gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
			      gl.glClearDepth(1.0f);      // set clear depth value to farthest
			      gl.glEnable(GL_DEPTH_TEST); // enables depth testing
			      gl.glDepthFunc(GL_LEQUAL);  // the type of depth test to do
			      gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // best perspective correction
			      gl.glShadeModel(GL_SMOOTH); // blends colors nicely, and smoothes out lighting
			}
			
			@Override
			public void reshape( GLAutoDrawable glautodrawable, int x, int y, int width, int height ) {
				GL2 gl = glautodrawable.getGL().getGL2();  // get the OpenGL 2 graphics context
				 
			      if (height == 0) height = 1;   // prevent divide by zero
			      float aspect = (float)width / height;
			 
			      // Set the view port (display area) to cover the entire window
			      gl.glViewport(0, 0, width, height);
			 
			      // Setup perspective projection, with aspect ratio matches viewport
			      gl.glMatrixMode(GL_PROJECTION);  // choose projection matrix
			      gl.glLoadIdentity();             // reset projection matrix
			      glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar
			 
			      // Enable the model-view transform
			      gl.glMatrixMode(GL_MODELVIEW);
			      gl.glLoadIdentity(); // reset
			}
			
			@Override
			public void dispose( GLAutoDrawable glautodrawable ) {
			}
        
			@Override
			public void display( GLAutoDrawable glautodrawable ) {
				float xVal, yVal, colorVal;
        	//CanvasPanel.render( glautodrawable.getGL().getGL2(), glautodrawable.getSurfaceWidth(), glautodrawable.getSurfaceHeight() );
				GL2 gl = glautodrawable.getGL().getGL2();  // get the OpenGL 2 graphics context
			      gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
			      gl.glLoadIdentity();  // reset the model-view matrix
			 
			      // ----- Your OpenGL rendering code here (Render a white triangle for testing) -----
			      gl.glTranslatef(-0.5f, -0.5f, -1.5f); // translate into the screen
			      gl.glBegin(GL_POINTS); // draw using triangles
			         for (int i = 0; i < arrayX.length; i++)
			         {
			        	 if(arrayZ[i] > 10) {
			        		 xVal = (arrayX[i] / maxArrayX);
			        		 yVal = (arrayY[i] / maxArrayY);
			        		 colorVal = (arrayZ[i] / 255);
			        		 IJ.log(String.valueOf(xVal));
			        		 gl.glColor3f( colorVal,0.0f,1.0f ); 
			        		 gl.glVertex3f(xVal, yVal, 0.0f);
			        	 }
			         }
			      gl.glEnd();
			}
		});
		glcanvas.setSize(400,400);
		
		add(glcanvas);
		setSize(getPreferredSize());
	/*public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        //if (e.getSource() == myPicture) {

    }*/
	}
}
