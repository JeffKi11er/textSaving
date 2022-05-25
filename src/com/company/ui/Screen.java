package com.company.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.TextAttribute;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Screen extends JPanel implements KeyListener, ActionListener, Runnable {
    private JTextField jInput;
    private String script;
    private List<String> scriptReport = new ArrayList<>();
    public Screen() {
        setLayout(null);
        initComps();
        Thread t = new Thread(this);
        t.start();
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        setFocusTraversalKeysEnabled(false);
    }

    private void initComps() {
        script = "";
        jInput = new JTextField();
        jInput.setSize(Fragment.W-40,25);
        int y = Fragment.H * 2 / 3 + 60;
        jInput.setLocation(10,y);
        jInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode()== KeyEvent.VK_DOWN){
                    String task = jInput.getText();
                    if(task.isEmpty()){
                        return;
                    }
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH : mm");
                    Date date = new Date();
                    String newTask ="";
                    if(scriptReport.size()>0){
                        String[]data = scriptReport.get(scriptReport.size()-1).split("-");
                        String timeLast = data[1];
                        String []separateData = timeLast.split(":");
                        if(separateData[0].trim().equals("12")){
                            newTask+= "13 : 00 - "+simpleDateFormat.format(date)+" : "+"\n"+"- "+task+"\n";
                        }else {
                            String resultLastTime = separateData[0].trim()+" :"+separateData[1];
                            newTask+= resultLastTime+"-"+" "+simpleDateFormat.format(date)+" : "+"\n"+"- "+task+"\n";
                        }
                    }else {
                        newTask+= "08 : 30 - "+ simpleDateFormat.format(date)+" : "+"\n"+"- "+task+"\n";
                    }
                    scriptReport.add(newTask);
                    jInput.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
        add(jInput);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D =(Graphics2D)g;
        super.paintComponent(graphics2D);
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(10, 5, Fragment.W*2/3 - 40, Fragment.H * 4 / 5-30);
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(10 + 1, 6, Fragment.W*2/3 - 40 - 2, Fragment.H * 4 / 5 - 32);
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(Fragment.W*2/3 - 20, 5, Fragment.W*1/3 - 10, Fragment.H * 4 / 5-30);
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(Fragment.W*2/3 - 20 + 1, 6, Fragment.W*1/3 - 10 - 2, Fragment.H * 4 / 5 - 32);
        drawTask(graphics2D);
    }

    private void drawTask(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        Font font = new Font("helvetica", Font.PLAIN, 15);
        graphics2D.setFont(font);
        for (int i = 0; i < scriptReport.size(); i++) {
            graphics2D.drawString(scriptReport.get(i), 40, 20*i+20);
        }
    }

    public List<String> getScriptReport() {
        return scriptReport;
    }

    @Override
    public void run() {
        while (true){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            Date date = new Date();
            if(simpleDateFormat.format(date).equals("01:58")){
                try {
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_WINDOWS);
                    robot.keyRelease(KeyEvent.VK_WINDOWS);
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            }
            repaint();
        }
    }
}
