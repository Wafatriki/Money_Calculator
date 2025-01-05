package es.ulpgc.dis;

import es.ulpgc.dis.view.MainUI;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}
        new MainUI().setVisible(true);
    }
}
