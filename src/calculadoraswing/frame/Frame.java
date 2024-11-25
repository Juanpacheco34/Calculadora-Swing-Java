/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculadoraswing.frame;

import calculadoraswing.panel.Panel;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author jpach
 */
public class Frame extends JFrame {
    
    public Frame() {
        setTitle("Calculadora");
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int w = screen.width;
        int h = screen.height;
        setBounds(w / 3, h / 4, w / 4, h / 2);
        Image icon = Toolkit.getDefaultToolkit().getImage("src/Assets/Icon.png");
        setIconImage(icon);
        
        add(new Panel());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
}
