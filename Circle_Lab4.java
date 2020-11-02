package circle_lab4;

/**
 *
 * @author mnijh
 * 
 */
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;
import java.util.Scanner;
import java.util.*;

class CircleDrawing  implements GLEventListener {
    
    private GLU glu;

    public void init(GLAutoDrawable gld) {

        
        
        
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(-250, -250, 250, 250);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-250.0, 250.0, -250.0, 250.0);
        
      
        
    }

    public void display(GLAutoDrawable drawable) {
        
        
        
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glPushMatrix();
        
      
        
        
        
        double rad = 100.0;
        drawCircle(gl,rad); //for outer circle
        drawCircle2(gl,rad); //for N,S,E,W circles
        drawCircle3(gl,rad); // for NE,NW,SE,SW circles
        
        
        
       
        
        
    }

    private void drawCircle(GL2 gl, double r) {
        
       double x = r, y = 0, d = 5 - 4*r;
        draw8Way(gl, x, y);
        while (y <= x) {
            if (d < 0) {
                d = d + ((2 * y + 3) * 4);
                y++;
            } else {
                d += ((-2 * x + 2 * y + 5) * 4);
                x--;
                y++;
            }
            draw8Way(gl, x, y);
        }        
       
    }
    
    void drawCircle2(GL2 gl, double r)
    {
            r=r/2;
            double add= r*Math.sin(Math.toRadians(90));
            
            
         double x = r, y = 0, d = 5 - 4*r;
           // draw8Way(gl, x, y);
        while (y <= x) {
            if (d < 0) {
                d = d + ((2 * y + 3) * 4);
                y++;
                
            } else {
                d += ((-2 * x + 2 * y + 5) * 4);
                x--;
                y++;
               
            }
            
            draw8Way(gl, x, y+add);
            draw8Way(gl, x, y-add);
            
            draw8Way(gl, -x, y+add);
            draw8Way(gl, -x, y-add);
            
            draw8Way(gl, x+add, y);
            draw8Way(gl, x-add, y);
            
        }        
    }
    
     
     
      void drawCircle3(GL2 gl, double r)
    {
            r=r/2;
            double add= r*Math.sin(Math.toRadians(45));
         
            double x = r, y = 0, d = 5 - 4*r;
            draw8Way(gl, x+add, y+add);
        while (y <= x) {
            if (d < 0) {
                d = d + ((2 * y + 3) * 4);
                y++;
            } else {
                d += ((-2 * x + 2 * y + 5) * 4);
                x--;
                y++;
            }
         
            draw8Way(gl, x+add, y+add);
            draw8Way(gl, x-add, y-add);
            
            draw8Way(gl, -x+add, y+add);
            draw8Way(gl, -x-add, y-add);
    
        }        
    }
     
     

    private void draw8Way(GL2 gl, double x, double y) {
        gl.glBegin(GL2.GL_POINTS);
        
        
        
            gl.glVertex2d(x, y);
            gl.glVertex2d(y, x);

            gl.glVertex2d(-x, y);
            gl.glVertex2d(-y, x);

            gl.glVertex2d(-x, -y);
            gl.glVertex2d(-y, -x);


            gl.glVertex2d(x, -y);
            gl.glVertex2d(y, -x);
        
        gl.glEnd();
        
    }
    

    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
                        int height) {
    }

    public void dispose(GLAutoDrawable arg0) {

    }
     


}

public class Circle_Lab4{
    
    public static void main(String[] args) {

      //getting the capabilities object of GL2 profile        
      final GLProfile profile = GLProfile.get(GLProfile.GL2);
      GLCapabilities capabilities = new GLCapabilities(profile);
   
      // The canvas
      final GLCanvas glcanvas = new GLCanvas(capabilities);
      CircleDrawing drawing = new CircleDrawing();
      glcanvas.addGLEventListener(drawing);
      glcanvas.setSize(800, 800);
   
      //creating frame
      final JFrame frame = new JFrame ("Circle");
   
      //adding canvas to frame
      frame.getContentPane().add(glcanvas);                 
      frame.setSize(frame.getContentPane().getPreferredSize());
      frame.setVisible(true);
      
      
   }
}
