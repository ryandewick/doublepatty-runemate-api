package com.runemate.doublepatty.api;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class BotGUI extends JFrame {
    private static BotGUI instance;

    private JLabel scriptLabel;
    private JLabel actionLabel;
    private JLabel durationLabel;
    private JLabel logoLabel; // For the branding image
    private long startTime;
    private Color backgroundColor = new Color(34, 34, 34);
    private Color textColor = new Color(255, 255, 255);

    private BotGUI() {
        setTitle("DoublePatty API - GUI");
        setSize(400, 200); // Adjusted size for four rows
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(0, 0); // Set the location to the top-left corner of the screen
        setAlwaysOnTop(true); // Set the window to always stay on top
        setResizable(false);

        JPanel contentPanel = new JPanel(new GridLayout(4, 1));
        contentPanel.setBackground(backgroundColor);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        scriptLabel = createStyledLabel("Script: Not Set");
        actionLabel = createStyledLabel("Action: Idle");
        durationLabel = createStyledLabel("Duration: 0s");

        // Load and create a label for the branding image
        logoLabel = new JLabel();
        try {
            URL imgURL = getClass().getResource("/burger.png");
            if (imgURL != null) {
                ImageIcon logoIcon = new ImageIcon(imgURL);
                // Resize the image to 60x60 pixels
                Image scaledImage = logoIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                logoIcon = new ImageIcon(scaledImage);
                logoLabel.setIcon(logoIcon);
            } else {
                System.err.println("Error: Image not found. Check the path and build configuration.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logoLabel.setHorizontalAlignment(JLabel.RIGHT);

        contentPanel.add(scriptLabel);
        contentPanel.add(actionLabel);
        contentPanel.add(durationLabel);
        contentPanel.add(logoLabel);
        add(contentPanel);

        startTime = System.currentTimeMillis();
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                long duration = (System.currentTimeMillis() - startTime) / 1000;
                durationLabel.setText("Duration: " + formatDuration(duration));
            }
        }, 0, 1000);
    }

    public static synchronized BotGUI getInstance() {
        if (instance == null) {
            instance = new BotGUI();
        }
        return instance;
    }

    public static synchronized void resetInstance() {
        if (instance != null) {
            instance.dispose();
            instance = null;
        }
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(textColor);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        return label;
    }

    private String formatDuration(long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

    public void setScriptName(String scriptName) {
        scriptLabel.setText("Script: " + scriptName);
    }

    public void setAction(String action) {
        actionLabel.setText("Action: " + action);
    }
}
