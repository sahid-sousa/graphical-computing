package br.com.trabalho.interfaces;

import com.jogamp.opengl.GL2;
import java.awt.Color;

public interface IShape {

    public void draw(GL2 gl2);

    public void setSpeed(int speed);

    public void setTexture(int texture);
    
    public void setColor(Color color);
}
