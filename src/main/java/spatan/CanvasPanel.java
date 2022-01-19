package spatan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;

import ij.IJ;

public class CanvasPanel extends JPanel {//implements ActionListener {
	public CanvasPanel() {  
		GLProfile glprofile = GLProfile.getDefault();
		GLCapabilities glcapabilities = new GLCapabilities(glprofile);
		final GLCanvas glcanvas = new GLCanvas( glcapabilities );

		glcanvas.addGLEventListener( new GLEventListener() {
			
			@Override
			public void reshape( GLAutoDrawable glautodrawable, int x, int y, int width, int height ) {
				//CanvasPanel.setup( glautodrawable.getGL().getGL2(), width, height );
			}	
        
			@Override
			public void init( GLAutoDrawable glautodrawable ) {
			}
        
			@Override
			public void dispose( GLAutoDrawable glautodrawable ) {
			}
        
			@Override
			public void display( GLAutoDrawable glautodrawable ) {
        	//CanvasPanel.render( glautodrawable.getGL().getGL2(), glautodrawable.getSurfaceWidth(), glautodrawable.getSurfaceHeight() );

			      final GL2 gl = glautodrawable.getGL().getGL2();
			            
			      gl.glBegin (GL2.GL_LINES);//static field
			      gl.glVertex3f(0.50f,-0.50f,0);
			      gl.glVertex3f(-0.50f,0.50f,0);
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
