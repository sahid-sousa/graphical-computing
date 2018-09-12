/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trabalho.shapes;

import br.com.trabalho.interfaces.IShape;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL2ES1;
import com.jogamp.opengl.fixedfunc.GLLightingFunc;
import com.jogamp.opengl.glu.GLU;
import java.awt.Color;

public class Cube implements IShape {

    private int speed = 0;
    private float rtri = 0.0f;
    private int texture;
    private float angleCube = 0;
    private float blue = 1;
    private float green = 1;
    private float red = 1;
    private boolean flag = true; // true: color; false: texture

    @Override
    public void draw(GL2 gl) {
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reset The View
        gl.glTranslatef(-0.5f, 0.0f, -6.0f); // Move the cube
        gl.glRotatef(rtri, 0.0f, 1.0f, 0.0f);
        if(!flag){
            gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
        }else{
            gl.glColor3f( red, green, blue); // Color
        }

        gl.glLoadIdentity();                // redefine a matriz atual da vista do modelo 

        gl.glTranslatef(-0.5f, 0.0f, -6.0f); // traduzir para a direita e para a tela 
        gl.glRotatef(angleCube, 1.0f, 1.0f, 1.0f); // gira em torno dos eixos x, y e z

        gl.glBegin(GL2.GL_QUADS); // do cubo de cores

        // Face superior 
        //gl.glColor3f(0.0f, 1.0f, 0.0f); // green
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);

        // Face inferior 
        //gl.glColor3f(1.0f, 0.5f, 0.0f); // laranja
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);// Face frontal 
        // gl.glColor3f(1.0f, 0.0f, 0.0f); // red
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);

        // Face 
        //gl.glColor3f(1.0f, 1.0f, 0.0f); // amarelo
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);

        // Face esquerda 
        // gl.glColor3f(0.0f, 0.0f, 1.0f); // blue
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);

        // Face direita 
        // gl.glColor3f(1.0f, 0.0f, 1.0f); // magenta
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);

        gl.glEnd(); // do cubo de cores 
        //rtri += speed;
        angleCube += speed;
    }

    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();      // obtém o contexto gráfico OpenGL 
        GLU glu = new GLU(); // obtenha GL Utilities 
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // definir cor de fundo (claro) 
        gl.glClearDepth(1.0f);      // defina um valor de profundidade claro para o mais distante 
        gl.glEnable(GL.GL_DEPTH_TEST); // ativa o teste de profundidade 
        gl.glDepthFunc(GL.GL_LEQUAL);  // o tipo de teste de profundidade para fazer 
        gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST); // melhor correção de perspectiva 
        gl.glShadeModel(GLLightingFunc.GL_SMOOTH); // combina bem as cores e suaviza a iluminação
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
            this.red = (float) color.getRed()/255;
            this.green = (float) color.getGreen()/255;
            this.blue = (float) color.getBlue()/255;
            this.flag = true;
            
        } catch (Exception e) {
        }

    }
}
