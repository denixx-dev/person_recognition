package org.kazino.Source_Java;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

public class FrameFunctions {
    static JFrame getFrame() {
        JFrame jframe = new JFrame();

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();

        Dimension dimension = toolkit.getScreenSize();

        jframe.setBounds(dimension.width / 2 - 250, dimension.height / 2 - 150, 500, 300);
        jframe.setTitle("Person Recognition");

        return jframe;
    }

    static void setjButton(JButton jButton){
        jButton.setText("Choose video");
        jButton.setSize(150, 30);
        jButton.setLocation(1, 20);
    }
    static void setjButton(JButton jButton, String name, int[] size, int[] loc){
        jButton.setText(name);
        jButton.setSize(size[0], size[1]);
        jButton.setLocation(loc[0], loc[1]);
    }
    static void setWelcomeJLabel(JLabel jLabel){
        jLabel.setText("Welcome to the Person Recognition");
        jLabel.setSize(300, 300);
        jLabel.setLocation(130, -100);
    }
    static void setCounterLabel(JLabel label){
        label.setSize(300,300);
        label.setLocation(130,-50);
    }

    static void setJLabel(JLabel jLabel, String name, int[] size, int[] loc){
        jLabel.setText(name);
        jLabel.setSize(size[0], size[1]);
        jLabel.setLocation(loc[0], loc[1]);
    }
}
