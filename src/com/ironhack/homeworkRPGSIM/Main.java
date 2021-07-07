package com.ironhack.homeworkRPGSIM;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {



    public static void main(String[] args) throws IOException {

        //**** A BIT OF FUN COLOURFUL GUI ***
        JFrame frame = new JFrame("WELCOME TO MORTAL WOMBATS!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BufferedImage myPicture = ImageIO.read(new File("src/mortal_wombatsGUI-01.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        JLabel line = new JLabel("ARE YOU READY TO BATTLE?");
        line.setFont(new Font("Serif", Font.BOLD,30));
        line.setBorder(new EmptyBorder(10,90,60,0));
        JLabel line2 = new JLabel("Tip: Make your console full screen :)");
        line2.setFont(new Font("Serif", Font.CENTER_BASELINE,16));
        line2.setBorder(new EmptyBorder(10,180,40,0));
        frame.getContentPane().setLayout(new FlowLayout());
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        topPanel.add(picLabel, BorderLayout.NORTH);
        topPanel.add(line);
        JButton button = new JButton("LET'S DO IT!");
        button.setFont(new Font("Serif", Font.BOLD,20));
        button.setBackground(Color.lightGray);
        button.setPreferredSize(new Dimension(60,40));
        button.addActionListener(e -> { frame.dispose();});
        bottomPanel.add(button, BorderLayout.SOUTH);
        bottomPanel.add(line2);
        bottomPanel.setSize(40,40);
        panel.add(topPanel, BorderLayout.PAGE_START);
        panel.add(bottomPanel, BorderLayout.PAGE_END);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //**** MAIN GAME ****
        MainMenu.mainMenu();
    }


}