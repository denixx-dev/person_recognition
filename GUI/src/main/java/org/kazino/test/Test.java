package org.kazino.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;

public class Test {
    static JFrame jFrame = getFrame();
    static JPanel jPanel = new JPanel();

    public static void main(String[] args) throws Exception {
        jFrame.add(jPanel);
        AbstractAction myAction1 = new changeColorToBlue();
        AbstractAction myAction2 = new changeColorToRed();


        KeyStroke keyStroke1 = KeyStroke.getKeyStroke("ctrl B");
        KeyStroke keyStroke2 = KeyStroke.getKeyStroke("ctrl R");
        InputMap inputMap = jPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(keyStroke1, "changeColorToBlue");
        inputMap.put(keyStroke2, "changeColorToRed");

        ActionMap actionMap = jPanel.getActionMap();
        actionMap.put("changeColorToBlue", myAction1);
        actionMap.put("changeColorToRed", myAction2);

    }

    static class changeColorToBlue extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e){
            jPanel.setBackground(Color.BLUE);
        }
    }

    static class changeColorToRed extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e){
            jPanel.setBackground(Color.RED);
        }
    }

    static class AddPic extends JComponent {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;

            URL url = null;
            try {
                url = new URL("https://icon2.cleanpng.com/20180717/vvc/kisspng-kitty-katswell-dudley-puppy-deviantart-drawing-kitty-vector-5b4d7f738a6fb4.585560691531805555567.jpg");
                Image image = new ImageIcon(url).getImage();
                g2.drawImage(image, 220, 20, null);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
//            Image image = new ImageIcon("C:/Users/denis/OneDrive/Изображения/kk.png.png").getImage();
//            g2.drawImage(image, 220, 20, null);


        }
    }

    static JFrame getFrame() {
        JFrame jframe = new JFrame();
        jframe.setVisible(true);

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();

        Dimension dimension = toolkit.getScreenSize();

        jframe.setBounds(dimension.width / 2 - 250, dimension.height / 2 - 150, 500, 300);
        jframe.setTitle("Person Recognition");

        return jframe;
    }
}
