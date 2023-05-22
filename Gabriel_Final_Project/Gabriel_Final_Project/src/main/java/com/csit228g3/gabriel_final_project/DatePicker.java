/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csit228g3.gabriel_final_project;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author L14X10W20
 */

import java.awt.event.*;

public class DatePicker {
    private int month;
    private int year;
    private JLabel label;
    private String selectedDay;
    private JDialog dialog;
    private JButton[] buttons;
 
    public DatePicker(JFrame parent) {
        dialog = new JDialog();
        dialog.setModal(true);
        dialog.setTitle("Date Picker");
 
        month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
        year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
 
        label = new JLabel("", JLabel.CENTER);
 
        JPanel panel1 = new JPanel(new GridLayout(7, 7));
        panel1.setPreferredSize(new Dimension(430, 120));
 
        buttons = new JButton[49];
 
        String[] header = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        for (int i = 0; i < 7; i++) {
            buttons[i] = new JButton(header[i]);
            buttons[i].setForeground(Color.red);
            panel1.add(buttons[i]);
        }
 
        for (int i = 7; i < buttons.length; i++) {
            buttons[i] = new JButton();
            panel1.add(buttons[i]);
            final int selection = i;
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    selectedDay = buttons[selection].getText();
                    dialog.dispose();
                }
            });
        }
 
        JPanel panel2 = new JPanel(new GridLayout(1, 3));
        JButton previous = new JButton("<");
        JButton next = new JButton(">");
        previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                month--;
                displayDate();
            }
        });
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                month++;
                displayDate();
            }
        });
        panel2.add(previous);
        panel2.add(label);
        panel2.add(next);
 
        dialog.add(panel1, BorderLayout.CENTER);
        dialog.add(panel2, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        displayDate();
        dialog.setVisible(true);
    }
 
    public void displayDate() {
        for (int i = 7; i < buttons.length; i++) {
            buttons[i].setText("");
        }
 
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMMM yyyy");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, 1);
 
        int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
 
        for (int i = 6 + dayOfWeek, day = 1; day <= daysInMonth; i++, day++) {
            buttons[i].setText("" + day);
        }
 
        label.setText(sdf.format(cal.getTime()));
    }
 
    public String setPickedDate() {
        if (selectedDay == null || selectedDay.isEmpty()) {
            return selectedDay;
        }
 
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, Integer.parseInt(selectedDay));
 
        return sdf.format(cal.getTime());
    }
}

