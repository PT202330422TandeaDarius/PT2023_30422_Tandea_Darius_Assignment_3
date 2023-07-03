package Presentation;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    
    public MainFrame() {
        this.setContentPane(new MainPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Store Management");
        this.setSize(600, 700);
        this.setResizable(false);
        this.setMinimumSize(new Dimension(600, 700));
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
    
}
