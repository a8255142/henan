package com.henan;


import javax.swing.*;

import javax.swing.border.*;

import javax.swing.event.*;

import java.awt.*;

import java.awt.event.*;

 

// The main program class

public class ButtonDemo extends JFrame implements ActionListener {

 

    // GUI objects displayed in the frame window

    ButtonGroup group; // Groups radio buttons

    JRadioButton redButton; // First radio button

    JRadioButton whiteButton; // Second radio button

    JRadioButton blueButton; // Third radio button

    JPanel colorBox; // Displays selected color

    JCheckBox showColorsButton; // First check box

    JCheckBox exitOnCloseButton; // Second check box

    JButton exitButton; // Plain button

 

    // Constructor initializes the GUI objects and panels

    public ButtonDemo() {

 

        // Select local system look and feel

        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            //    UIManager.getCrossPlatformLookAndFeelClassName());

        } catch (Exception e) {

        }

 

        // End program when window closes

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                System.exit(0);

            }

        });

 

        //==========================================================

        // Radio button panel and GUI objects

        //==========================================================

 

        // Create radio button panel and an inner pane

        // to help display the GUI objects neatly

        JPanel radioPane = new JPanel();

        JPanel innerRadioPane = new JPanel();

        radioPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        innerRadioPane.setLayout(new BoxLayout(innerRadioPane, BoxLayout.Y_AXIS));

        innerRadioPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

 

        // Construct the radio group and its buttons

        // All button events go to the program's ActionListener

        group = new ButtonGroup();

        redButton = new JRadioButton("Red  ");

        whiteButton = new JRadioButton("White");

        blueButton = new JRadioButton("Blue ");

        whiteButton.setSelected(true); // Select one button

        redButton.addActionListener(this); // See ActionPerformed()

        whiteButton.addActionListener(this);

        blueButton.addActionListener(this);

        group.add(redButton); // The group ensures that when one

        group.add(whiteButton); // button is selected, the previously

        group.add(blueButton); // selected button is turned off

 

        // Construct a small panel for displaying the selected color

        colorBox = new JPanel();

        colorBox.setBackground(Color.white);

        colorBox.setPreferredSize(new Dimension(50, 50));

 

        // Add the GUI objects to the inner radio pane

        innerRadioPane.add(redButton);

        innerRadioPane.add(whiteButton);

        innerRadioPane.add(blueButton);

        innerRadioPane.add(Box.createRigidArea(new Dimension(0, 25))); // Spacer

        innerRadioPane.add(colorBox);

 

        // Add the inner pane to the raised radio panel (left side)

        radioPane.add(innerRadioPane);

 

        //==========================================================

        // Check box panel and GUI objects

        //==========================================================

 

        // Create check box panel and an inner panel

        // for a neat appearance

        JPanel checkPane = new JPanel();

        JPanel innerCheckPane = new JPanel();

        checkPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        innerCheckPane.setLayout(new BoxLayout(innerCheckPane, BoxLayout.Y_AXIS));

        innerCheckPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

 

        // Create the "show colors" check box object and

        // enable or disable the color radio buttons

        showColorsButton = new JCheckBox("Show colors");

        showColorsButton.setSelected(true);

        showColorsButton.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {

                boolean t = showColorsButton.isSelected();

                redButton.setEnabled(t); // Enable or disable all

                whiteButton.setEnabled(t); // radio buttons depending on

                blueButton.setEnabled(t); // state of check box

            }

        });

 

        // Create the "exit on close" check box object and

        // enable or disable the Exit Program button

        exitOnCloseButton = new JCheckBox("Exit on close");

        exitOnCloseButton.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {

                boolean t = exitOnCloseButton.isSelected();

                exitButton.setEnabled(t);

            }

        });

 

        // Create the plain "Exit Program" button

        // and its action event listener

        exitButton = new JButton("Exit Program");

        exitButton.setEnabled(false); // Initially disabled

        exitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }

        });

 

        // Add the buttons to the inner pane

        innerCheckPane.add(showColorsButton);

        innerCheckPane.add(exitOnCloseButton);

        innerCheckPane.add(Box.createRigidArea(new Dimension(0, 50)));

        innerCheckPane.add(exitButton);

 

        // Add the inner pane to the raised check box panel

        checkPane.add(innerCheckPane);

 

        // Add the panels and GUI objects to the frame's content pane

        Container content = getContentPane();

        content.setLayout(new GridLayout(1, 3, 2, 2));

        content.add(radioPane);

        content.add(checkPane);

    }

 

    // Change the colorBox background color when user

    // selects a radio button.

    public void actionPerformed(ActionEvent e) {

        Color c;

        if (redButton.isSelected())

            c = Color.red;

        else if (whiteButton.isSelected())

            c = Color.white;

        else

            c = Color.blue;

        colorBox.setBackground(c);

    }

 

    // Main program simply constructs the ButtonDemo

    // application object, and then sizes and shows the window

    public static void main(String[] args) {

        ButtonDemo app = new ButtonDemo();

        app.setTitle("Button and Check Box Demo");

        app.setSize(320, 240);

        app.show();

    }

}