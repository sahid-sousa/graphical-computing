/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trabalho.shapes;

import br.com.trabalho.interfaces.IShape;
import com.jogamp.opengl.GL2;
import java.awt.Color;


public class Triangle implements IShape {

    private int speed = 0;
    private float rtri = 0.0f;
    private int texture;
    private float blue = 1;
    private float green = 1;
    private float red = 1;
    private boolean flag = true; // true: color; false: texture
    
    @Override
    public void draw(GL2 gl) {
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reset The View
        gl.glTranslatef(-0.5f, 0.0f, -6.0f); // Move the triangle
        gl.glRotatef(rtri, 0.0f, 1.0f, 0.0f);

        if(!flag){
            gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
        }else{
            gl.glColor3f( red, green, blue); // Color
        }

        gl.glBegin(GL2.GL_TRIANGLES);

        //Front
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(1.0f, 2.0f, 0.0f); // Top Of Triangle (Front)
        // gl.glColor3f( 0.0f, 1.0f, 0.0f ); // Green
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f); // Left Of Triangle (Front)
        //gl.glColor3f( 0.0f, 0.0f, 1.0f ); // Blue
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f); // Right Of Triangle (Front)

        //Right
        // gl.glColor3f( 1.0f, 0.0f, 0.0f ); // Red
        gl.glVertex3f(1.0f, 2.0f, 0.0f); // Top Of Triangle (Right)
        //gl.glColor3f( 0.0f, 0.0f, 1.0f ); // Blue
        gl.glVertex3f(1.0f, -1.0f, 1.0f); // Left Of Triangle (Right)
        //  gl.glColor3f( 0.0f, 1.0f, 0.0f ); // Green
        gl.glVertex3f(1.0f, -1.0f, -1.0f); // Right Of Triangle (Right)

        //LEft
        // gl.glColor3f( 1.0f, 0.0f, 0.0f ); // Red
        gl.glVertex3f(1.0f, 2.0f, 0.0f); // Top Of Triangle (Back)
        //  gl.glColor3f( 0.0f, 1.0f, 0.0f ); // Green
        gl.glVertex3f(1.0f, -1.0f, -1.0f); // Left Of Triangle (Back)
        //  gl.glColor3f( 0.0f, 0.0f, 1.0f ); // Blue
        gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Right Of Triangle (Back)

        //back
        // gl.glColor3f( 0.0f, 1.0f, 0.0f ); // Red
        gl.glVertex3f(1.0f, 2.0f, 0.0f); // Top Of Triangle (Left)
        //  gl.glColor3f( 0.0f, 0.0f, 1.0f ); // Blue
        gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Left Of Triangle (Left)
        //  gl.glColor3f( 0.0f, 1.0f, 0.0f ); // Green
        gl.glVertex3f(-1.0f, -1.0f, 1.0f); // Right Of Triangle (Left)
        gl.glEnd(); // Done Drawing 3d triangle (Pyramid)
        gl.glFlush();
        rtri += speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void setTexture(int texture) {
        this.texture = texture;
        this.flag = false;
    }
    
     @Override
    public void setColor(Color color) {
        try {
            this.red = color.getRed()/255;
            this.green = color.getGreen()/255;
            this.blue = color.getBlue()/255;
            this.flag = true;
            
        } catch (Exception e) {
        }

    }
}
