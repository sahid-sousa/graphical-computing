package br.com.trabalho.main;

import br.com.trabalho.interfaces.IShape;
import br.com.trabalho.shapes.Cube;
import br.com.trabalho.shapes.Sphere;
import br.com.trabalho.shapes.Triangle;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.awt.TextRenderer;
import javafx.scene.text.Text;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;


public class ShapesMain extends JFrame implements GLEventListener {

    private GLU glu = new GLU();
    private static File im = null;
    private int texture;
    private static int rspeed = 0;
    private JRadioButton textRb;
    private JRadioButton particlesRb;
    private JRadioButton backgroundRb;
    private JRadioButton reflectionRb;

    private Color color = null;
    
    public ShapesMain() {
        super("OpenGl 2D - Java 2D com opengl Jogl");
    }
    JRadioButton triangRb;
    JRadioButton cubeRb;
    JRadioButton sphereRb;
    
    static Texture t;

    public void start() {
        GLCanvas canvas = new GLCanvas();

        final FPSAnimator animator = new FPSAnimator(canvas, 60, true);
        animator.start();
        canvas.addGLEventListener(this);

        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, 1);

        speedSlider.addChangeListener((e) -> {
            JSlider source = (JSlider) e.getSource();
            rspeed = source.getValue();
        });
        JButton choosetexture = new JButton("Choose Texture");

        choosetexture.addActionListener((e) -> {
            t = null;
            JFileChooser jfc = new JFileChooser();
            int returnValue = jfc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {

                File selectedFile = jfc.getSelectedFile();
                System.out.println(selectedFile.getAbsolutePath());
                im = selectedFile;
            }
        });

        JButton choosecolor = new JButton("Choose Color");

        choosecolor.addActionListener((ActionEvent e) -> {

            color = JColorChooser.showDialog(this, "Select a color", Color.RED);
            System.out.println("Color selected");
            System.out.println(color);
            texture = 0;
        });

        RadioButtonHandler handler = new RadioButtonHandler();
        triangRb = new JRadioButton("Triangle", false);
        cubeRb = new JRadioButton("Cube", false);
        sphereRb = new JRadioButton("Sphere", false);
        
         // Implemente alguma das funcionalidades abaixo
        //https://www3.ntu.edu.sg/home/ehchua/programming/opengl/JOGL2.0.html
//        textRb = new JRadioButton("Text", false);
//        particlesRb = new JRadioButton("Particles", false);
//        backgroundRb = new JRadioButton("Background", false);
//        reflectionRb = new JRadioButton("Reflection", false);

        triangRb.addItemListener(handler);
        cubeRb.addItemListener(handler);
        sphereRb.addItemListener(handler);
//        textRb.addItemListener(handler);
//        particlesRb.addItemListener(handler);
//        backgroundRb.addItemListener(handler);
//        reflectionRb.addItemListener(handler);
        
        JPanel radiobuttonPanel = new JPanel();

        radiobuttonPanel.setLayout(new BoxLayout(radiobuttonPanel, BoxLayout.Y_AXIS));
        radiobuttonPanel.add(new JLabel("Shapes"));
        radiobuttonPanel.add(triangRb);
        radiobuttonPanel.add(cubeRb);
        radiobuttonPanel.add(sphereRb);
//        radiobuttonPanel.add(textRb);
//        radiobuttonPanel.add(particlesRb);
//        radiobuttonPanel.add(backgroundRb);
//        radiobuttonPanel.add(reflectionRb);

        JCheckBox cb = new JCheckBox("Triangle");

        cb.addActionListener((e) -> {
            if (((JCheckBox) e.getSource()).isSelected()) {
                shape = new Triangle();
            } else {
                shape = null;
            }
        });

        //JFileChooser jfc = new JFileChooser();
        // int returnValue = jfc.showOpenDialog(null);
        JPanel jp = new JPanel();
        jp.setSize(800, 100);
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

        jp.add(new JLabel("Choose Texture"));
        jp.add(choosetexture);
        jp.add(new JLabel("--------------------------------"));

        jp.add(new JLabel("Choose Color"));
        jp.add(choosecolor);
        jp.add(new JLabel("--------------------------------"));

        jp.add(new JLabel("Choose Speed"));
        jp.add(speedSlider);
        jp.add(new JLabel("--------------------------------"));

        jp.add(radiobuttonPanel);
        jp.add(new JLabel("--------------------------------"));

        add(jp, BorderLayout.WEST);
        // janela.add(jb,BorderLayout.WEST);
        add(canvas, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }
    private TextRenderer textRenderer;

    @Override
    public void init(GLAutoDrawable drawable) {
        
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    public void drawShape(IShape shape, GL2 gl) {
        shape.draw(gl);
    }

    private static IShape shape = null;

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        if (shape != null) {
            shape.setSpeed(rspeed);
            if(texture != 0){
                shape.setTexture(texture);
            }
            if(color != null){
                shape.setColor(color);
            }
            drawShape(shape, gl);

        } else {
            gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        }

        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0f, 0f, 0f, 0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        
        //
        gl.glEnable(GL2.GL_TEXTURE_2D);
        try {

            if (im != null) {
                if (t == null) {
                    t = TextureIO.newTexture(im, false);
                    texture = t.getTextureObject(gl);
                    color = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final GL2 gl = drawable.getGL().getGL2();
        if (height <= 0) {
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public static void main(String[] args) {
        ShapesMain janela = new ShapesMain();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                janela.start();
            }
        });

    }

    class RadioButtonHandler implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange() == 1){
                if (e.getItemSelectable() == triangRb) {
                    sphereRb.setSelected(false);
                    cubeRb.setSelected(false);
                    shape = new Triangle();
                    triangRb.setSelected(true);
                }
            
                if (e.getItemSelectable() == sphereRb) {
                    triangRb.setSelected(false);
                    cubeRb.setSelected(false);
                    shape = new Sphere();
                    sphereRb.setSelected(true);
                }
            
                if (e.getItemSelectable() == cubeRb) {
                    triangRb.setSelected(false);
                    sphereRb.setSelected(false);
                    shape = new Cube();
                    cubeRb.setSelected(true);
                }
                
            }else{
                triangRb.setSelected(false);
                sphereRb.setSelected(false);
                cubeRb.setSelected(false);
                shape = null;
            }      
        }
    }
}
