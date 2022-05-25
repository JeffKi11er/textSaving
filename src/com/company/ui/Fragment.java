package com.company.ui;

import com.company.UnicodeWrite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Fragment extends JFrame implements WindowListener {
    public static final int W = 800;
    public static final int H = 500;
    public Screen screen;

    public Fragment() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(W,H);
        setResizable(true);
        setLocationRelativeTo(null);
        setTitle("Plant");
        addWindowListener(this);
        init();
        dispose();
    }

    private void init() {
        screen = new Screen();
        add(screen);
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        int result = JOptionPane.showConfirmDialog(null,"Do you want to save?","Exit Task",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (result==JOptionPane.YES_OPTION){
            UnicodeWrite unicodeWrite = UnicodeWrite.getInstance();
            unicodeWrite.savingReport(screen.getScriptReport());
            System.exit(0);
        }
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
