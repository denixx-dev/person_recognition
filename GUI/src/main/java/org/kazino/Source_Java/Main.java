package org.kazino.Source_Java;

import py4j.GatewayServer;
import py4j.PythonClient;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    public static JLabel countLabel = new JLabel();
    public static JLabel notReadyLabel = new JLabel("YOLO is preparing to work. Please wait.");
    public static JCheckBox checkToShowDetections = new JCheckBox("Show detections");
    static int counter =0;
    static int i =0;
    static Timer timer;
    static boolean fileChosenFLag = false;
    static File videoFile;
    static boolean isWindowClosed = false;

    //
    // Scope with functions called from python side
    //
    public static void setFileChosenFLag(boolean flag){
        fileChosenFLag = flag;
    }
    public static boolean getFileChosenFlag(){
        return fileChosenFLag;
    }
    public static void setFileChosenFlag(boolean flag){ fileChosenFLag = flag; }
    public String getFileAbsolutePath() throws IOException {
        return videoFile.getCanonicalPath();
    }
    public static void setCounterLabelText(String text){
        countLabel.setText(text);
    }
    public static boolean getWindowClosedFlag (){
        return isWindowClosed;
    }
    public static void setjButtonEnabled(){
        jButton.setEnabled(true);
    }
    public static boolean getCheckBoxState(){
        return checkToShowDetections.isSelected();
    }
    public static void disableNotReadyLabel(){
        notReadyLabel.setVisible(false);
    }
    //
    //
    //

    public static void drawInterface(){
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

        FrameFunctions.setCounterLabel(countLabel);
        jPanel.add(countLabel);
//        countLabel.setText("test");

        FrameFunctions.setJCheckBox();
        jPanel.add(checkToShowDetections);

        FrameFunctions.setNotReadyLabel();
        jPanel.add(notReadyLabel);

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
        }
        );

        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isWindowClosed = true;
            }
        });

    }

    public static void main(String[] args) throws Exception {
        // prod
        GatewayServer gatewayServer = new GatewayServer(new Main());
        gatewayServer.start();
        System.out.println("Gateway started");

        // test
//        drawInterface();

    }


}
