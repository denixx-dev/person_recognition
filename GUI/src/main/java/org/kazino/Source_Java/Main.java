package org.kazino.Source_Java;

import py4j.GatewayServer;
import py4j.PythonClient;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class Main {
    static JFrame jFrame = FrameFunctions.getFrame();
    static JPanel jPanel = new JPanel();
    static JPanel jPanel2 = new JPanel();
    static JButton jButton = new JButton();
    static JLabel welcomeLabel = new JLabel();
    static JButton testButton = new JButton();
    static JLabel testLabel = new JLabel();
    static JLabel countLabel = new JLabel();
    static int counter =0;
    static Timer timer;
    static boolean fileChosenFLag = false;
    static File videoFile;
    public void setFileChosenFLag(boolean flag){
        fileChosenFLag = flag;
    }
    public boolean getFileChosenFlag(){
        return fileChosenFLag;
    } // Используется в питоне

    public String getFileAbsolutePath() throws IOException {
        return videoFile.getCanonicalPath();
    }
    public void drawInterface(){
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.add(jPanel);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();

        FrameFunctions.setjButton(jButton, "Choose video", new int[] {125, 30}, new int[] {175, 190});

        Rectangle r = jFrame.getBounds();
        System.out.println(Integer.toString(r.width) + " " + Integer.toString(r.height));

        jPanel.setLayout(null);
        jPanel.add(jButton);

        FrameFunctions.setWelcomeJLabel(welcomeLabel);
        jPanel.add(welcomeLabel);

        jButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JFileChooser fileChooser = new JFileChooser();
//                File currentDirectory = new File(System.getProperty("E:\Programs\Python_projects\PersonRecognition"));
                fileChooser.setCurrentDirectory(new File("E:\\Programs\\Python_projects\\PersonRecognition"));
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("MP4 File", "mp4"));
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("MKV File", "mkv"));
                fileChooser.setAcceptAllFileFilterUsed(false);
                int ret = fileChooser.showOpenDialog(null);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    videoFile = fileChooser.getSelectedFile();
                    setFileChosenFLag(true);
                    try{
                        JOptionPane.showMessageDialog(null, videoFile.getCanonicalPath());

                    }
                    catch (IOException ioException){
                        ioException.printStackTrace();
                    }
                }
            }
        });
    }

    public static void main(String[] args) throws Exception {
        GatewayServer gatewayServer = new GatewayServer(new Main());
        gatewayServer.start();
        System.out.println("Gateway started");
    }


}
