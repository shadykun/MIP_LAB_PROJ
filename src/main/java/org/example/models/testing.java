package org.example.models;

import javax.swing.*;
import java.awt.*;

public class testing extends JFrame {

    public testing() {
        setTitle("Vertical Alignment Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a main container with vertical BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Create a nested container with FlowLayout for horizontal alignment
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Add buttons to the nested container
        buttonPanel.add(new JButton("Button 1"));
        buttonPanel.add(new JButton("Button 2"));
        buttonPanel.add(new JButton("Button 3"));

        // Add the nested container to the main container
        mainPanel.add(buttonPanel);

        // Add the main container to the JFrame
        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        testing example = new testing();
        example.setVisible(true);
    }
}