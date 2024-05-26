package com.titan;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NPCGenerator extends JFrame {
    private JTextField npcIdField, posXField, posYField, heightField, rangeX1Field, rangeX2Field, rangeY1Field, rangeY2Field, descriptionField, radiusField;
    private JCheckBox autoWalkCheckBox, multipleCoordsCheckBox, newFormatCheckBox, anotherFormatCheckBox;
    private JComboBox<String> walkTypeComboBox;
    private JButton generateButton, newLineButton, clearButton, copyButton;
    private JTextArea outputArea, coordListArea;

    private static final String[] walkTypes = {
            "STAND", "WALK", "FACE_NORTH", "FACE_SOUTH", "FACE_EAST", "FACE_WEST",
            "FACE_NORTH_WEST", "FACE_NORTH_EAST", "FACE_SOUTH_WEST", "FACE_SOUTH_EAST"
    };

    public NPCGenerator() {
        setTitle("Titan Autospawn Generator");
        setResizable(false);
        setSize(750, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Settings Section
        JLabel lblNpcId = new JLabel("NPC ID");
        lblNpcId.setBounds(20, 20, 70, 20);
        add(lblNpcId);
        npcIdField = new JTextField("3200");
        npcIdField.setBounds(100, 20, 100, 20);
        ((AbstractDocument) npcIdField.getDocument()).setDocumentFilter(new LengthFilter(5));
        add(npcIdField);

        JLabel lblPosX = new JLabel("Position X");
        lblPosX.setBounds(20, 50, 70, 20);
        lblPosX.setForeground(Color.GREEN);
        add(lblPosX);
        posXField = new JTextField("8414");
        posXField.setBounds(100, 50, 100, 20);
        ((AbstractDocument) posXField.getDocument()).setDocumentFilter(new LengthFilter(5));
        add(posXField);

        JLabel lblPosY = new JLabel("Position Y");
        lblPosY.setBounds(20, 80, 70, 20);
        lblPosY.setForeground(Color.RED);
        add(lblPosY);
        posYField = new JTextField("8415");
        posYField.setBounds(100, 80, 100, 20);
        ((AbstractDocument) posYField.getDocument()).setDocumentFilter(new LengthFilter(5));
        add(posYField);

        JLabel lblHeight = new JLabel("Height");
        lblHeight.setBounds(20, 110, 70, 20);
        add(lblHeight);
        heightField = new JTextField("2");
        heightField.setBounds(100, 110, 100, 20);
        ((AbstractDocument) heightField.getDocument()).setDocumentFilter(new LengthFilter(1));
        add(heightField);

        JLabel lblRangeX1 = new JLabel("Range X1");
        lblRangeX1.setBounds(250, 20, 70, 20);
        lblRangeX1.setForeground(Color.RED);
        add(lblRangeX1);
        rangeX1Field = new JTextField("1000");
        rangeX1Field.setBounds(320, 20, 100, 20);
        add(rangeX1Field);

        JLabel lblRangeX2 = new JLabel("Range X2");
        lblRangeX2.setBounds(250, 50, 70, 20);
        lblRangeX2.setForeground(Color.RED);
        add(lblRangeX2);
        rangeX2Field = new JTextField("5151");
        rangeX2Field.setBounds(320, 50, 100, 20);
        add(rangeX2Field);

        JLabel lblRangeY1 = new JLabel("Range Y1");
        lblRangeY1.setBounds(250, 80, 70, 20);
        lblRangeY1.setForeground(Color.RED);
        add(lblRangeY1);
        rangeY1Field = new JTextField("4242");
        rangeY1Field.setBounds(320, 80, 100, 20);
        add(rangeY1Field);

        JLabel lblRangeY2 = new JLabel("Range Y2");
        lblRangeY2.setBounds(250, 110, 70, 20);
        lblRangeY2.setForeground(Color.RED);
        add(lblRangeY2);
        rangeY2Field = new JTextField("5151");
        rangeY2Field.setBounds(320, 110, 100, 20);
        add(rangeY2Field);

        autoWalkCheckBox = new JCheckBox("Auto Walk");
        autoWalkCheckBox.setBounds(20, 140, 100, 20);
        autoWalkCheckBox.addActionListener(new AutoWalkCheckBoxListener());
        add(autoWalkCheckBox);

        JLabel lblWalkType = new JLabel("Walk type");
        lblWalkType.setBounds(150, 140, 70, 20);
        lblWalkType.setForeground(Color.GREEN);
        add(lblWalkType);
        walkTypeComboBox = new JComboBox<>(walkTypes);
        walkTypeComboBox.setBounds(220, 140, 100, 20);
        add(walkTypeComboBox);

        JLabel lblDescription = new JLabel("Description");
        lblDescription.setBounds(20, 170, 70, 20);
        lblDescription.setForeground(Color.CYAN);
        add(lblDescription);
        descriptionField = new JTextField("StationaryNPC");
        descriptionField.setBounds(100, 170, 220, 20);
        add(descriptionField);

        multipleCoordsCheckBox = new JCheckBox("Multiple Coordinates");
        multipleCoordsCheckBox.setBounds(20, 200, 200, 20);
        multipleCoordsCheckBox.addActionListener(new MultipleCoordsCheckBoxListener());
        add(multipleCoordsCheckBox);

        anotherFormatCheckBox = new JCheckBox("Zenyte");
        anotherFormatCheckBox.setBounds(20, 230, 200, 20);
        anotherFormatCheckBox.addActionListener(new AnotherFormatCheckBoxListener());
        add(anotherFormatCheckBox);

        newFormatCheckBox = new JCheckBox("Xeros");
        newFormatCheckBox.setBounds(250, 200, 200, 20);
        add(newFormatCheckBox);

        JLabel lblRadius = new JLabel("Radius");
        lblRadius.setBounds(250, 230, 70, 20);
        add(lblRadius);
        radiusField = new JTextField("3");
        radiusField.setBounds(320, 230, 100, 20);
        radiusField.setEnabled(false);
        add(radiusField);

        // Coordinate List Section
        JLabel lblCoordList = new JLabel("Coordinates List (x,y,height)");
        lblCoordList.setBounds(450, 20, 200, 20);
        add(lblCoordList);

        coordListArea = new JTextArea();
        JScrollPane coordListScrollPane = new JScrollPane(coordListArea);
        coordListScrollPane.setBounds(450, 50, 250, 100);
        coordListArea.setEnabled(false);
        add(coordListScrollPane);

        // Controls Section
        generateButton = new JButton("Generate");
        generateButton.setBackground(Color.GREEN);
        generateButton.setBounds(450, 160, 100, 30);
        generateButton.addActionListener(new GenerateButtonListener());
        add(generateButton);

        newLineButton = new JButton("New Line");
        newLineButton.setBackground(Color.YELLOW);
        newLineButton.setBounds(560, 160, 100, 30);
        newLineButton.addActionListener(new NewLineButtonListener());
        add(newLineButton);

        copyButton = new JButton("Copy");
        copyButton.setBackground(Color.BLUE);
        copyButton.setForeground(Color.WHITE);
        copyButton.setBounds(560, 200, 100, 30);
        copyButton.addActionListener(new CopyButtonListener());
        add(copyButton);

        clearButton = new JButton("Clear");
        clearButton.setBackground(Color.RED);
        clearButton.setBounds(450, 200, 100, 30);
        clearButton.addActionListener(new ClearButtonListener());
        add(clearButton);

        // Output Section
        outputArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(20, 270, 700, 350);
        add(scrollPane);

        outputArea.setEditable(false);

        // Add document listeners to relevant fields
        npcIdField.getDocument().addDocumentListener(new GeneralDocumentListener());
        posXField.getDocument().addDocumentListener(new GeneralDocumentListener());
        posYField.getDocument().addDocumentListener(new GeneralDocumentListener());
        heightField.getDocument().addDocumentListener(new GeneralDocumentListener());
        descriptionField.getDocument().addDocumentListener(new GeneralDocumentListener());
    }

    private void updateFields() {
        if (autoWalkCheckBox.isSelected()) {
            try {
                int posX = Integer.parseInt(posXField.getText());
                int posY = Integer.parseInt(posYField.getText());

                rangeX1Field.setText(String.valueOf(posX + 3));
                rangeY1Field.setText(String.valueOf(posY + 3));
                rangeX2Field.setText(String.valueOf(posX - 3));
                rangeY2Field.setText(String.valueOf(posY - 3));

                rangeX1Field.setEditable(false);
                rangeY1Field.setEditable(false);
                rangeX2Field.setEditable(false);
                rangeY2Field.setEditable(false);
            } catch (NumberFormatException ex) {
                rangeX1Field.setText("");
                rangeY1Field.setText("");
                rangeX2Field.setText("");
                rangeY2Field.setText("");
            }
        } else {
            rangeX1Field.setText("1000");
            rangeY1Field.setText("4242");
            rangeX2Field.setText("5151");
            rangeY2Field.setText("5151");

            rangeX1Field.setEditable(true);
            rangeY1Field.setEditable(true);
            rangeX2Field.setEditable(true);
            rangeY2Field.setEditable(true);
        }
    }

    private class AutoWalkCheckBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateFields();
        }
    }

    private class MultipleCoordsCheckBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean isSelected = multipleCoordsCheckBox.isSelected();
            posXField.setEnabled(!isSelected);
            posYField.setEnabled(!isSelected);
            heightField.setEnabled(!isSelected);
            coordListArea.setEnabled(isSelected);
        }
    }

    private class AnotherFormatCheckBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean isSelected = anotherFormatCheckBox.isSelected();
            radiusField.setEnabled(isSelected);
        }
    }

    private class GeneralDocumentListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateFields();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateFields();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateFields();
        }
    }

    private class GenerateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            StringBuilder output = new StringBuilder();

            if (multipleCoordsCheckBox.isSelected()) {
                String[] coords = coordListArea.getText().split("\\n");
                for (String coord : coords) {
                    String[] parts = coord.split(",");
                    if (parts.length == 3) {
                        String posX = parts[0].trim();
                        String posY = parts[1].trim();
                        String height = parts[2].trim();

                        try {
                            int posXInt = Integer.parseInt(posX);
                            int posYInt = Integer.parseInt(posY);

                            if (newFormatCheckBox.isSelected()) {
                                String walkingType = walkTypeComboBox.getSelectedItem().toString();

                                String line = String.format(
                                        "{\n    \"id\": %s,\n    \"position\": {\n      \"x\": %s,\n      \"y\": %s,\n      \"height\": %s\n    },\n    \"walkingType\": \"%s\"\n  }",
                                        npcIdField.getText(),
                                        posX,
                                        posY,
                                        height,
                                        walkingType
                                );
                                output.append(line).append(",\n");
                            } else if (anotherFormatCheckBox.isSelected()) {
                                String direction = walkTypeComboBox.getSelectedItem().toString().replace("FACE_", "");

                                String line = String.format(
                                        "{\n    \"id\": %s,\n    \"x\": %s,\n    \"y\": %s,\n    \"z\": %s,\n    \"direction\": \"%s\",\n    \"radius\": %s\n  }",
                                        npcIdField.getText(),
                                        posX,
                                        posY,
                                        height,
                                        direction,
                                        radiusField.getText()
                                );
                                output.append(line).append(",\n");
                            } else {
                                String rangeX1 = autoWalkCheckBox.isSelected() ? String.valueOf(posXInt + 3) : "1000";
                                String rangeX2 = autoWalkCheckBox.isSelected() ? String.valueOf(posXInt - 3) : "5151";
                                String rangeY1 = autoWalkCheckBox.isSelected() ? String.valueOf(posYInt + 3) : "4242";
                                String rangeY2 = autoWalkCheckBox.isSelected() ? String.valueOf(posYInt - 3) : "5151";

                                String line = String.format(
                                        "spawn = %s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",
                                        npcIdField.getText(),
                                        posX,
                                        posY,
                                        height,
                                        rangeX1,
                                        rangeX2,
                                        rangeY1,
                                        rangeY2,
                                        getWalkingTypeCode(walkTypeComboBox.getSelectedItem().toString()),
                                        descriptionField.getText()
                                );
                                output.append(line).append("\n");
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(NPCGenerator.this,
                                    "Invalid coordinates: " + coord,
                                    "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(NPCGenerator.this,
                                "Invalid coordinate format: " + coord,
                                "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                if (newFormatCheckBox.isSelected()) {
                    String walkingType = walkTypeComboBox.getSelectedItem().toString();

                    String line = String.format(
                            "{\n    \"id\": %s,\n    \"position\": {\n      \"x\": %s,\n      \"y\": %s,\n      \"height\": %s\n    },\n    \"walkingType\": \"%s\"\n  }",
                            npcIdField.getText(),
                            posXField.getText(),
                            posYField.getText(),
                            heightField.getText(),
                            walkingType
                    );
                    output.append(line).append(",\n");
                } else if (anotherFormatCheckBox.isSelected()) {
                    String direction = walkTypeComboBox.getSelectedItem().toString().replace("FACE_", "");

                    String line = String.format(
                            "{\n    \"id\": %s,\n    \"x\": %s,\n    \"y\": %s,\n    \"z\": %s,\n    \"direction\": \"%s\",\n    \"radius\": %s\n  }",
                            npcIdField.getText(),
                            posXField.getText(),
                            posYField.getText(),
                            heightField.getText(),
                            direction,
                            radiusField.getText()
                    );
                    output.append(line).append(",\n");
                } else {
                    String line = String.format(
                            "spawn = %s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",
                            npcIdField.getText(),
                            posXField.getText(),
                            posYField.getText(),
                            heightField.getText(),
                            rangeX1Field.getText(),
                            rangeX2Field.getText(),
                            rangeY1Field.getText(),
                            rangeY2Field.getText(),
                            getWalkingTypeCode(walkTypeComboBox.getSelectedItem().toString()),
                            descriptionField.getText()
                    );
                    output.append(line).append("\n");
                }
            }
            outputArea.append(output.toString());
        }
    }

    private String getWalkingTypeCode(String walkingType) {
        switch (walkingType) {
            case "STAND":
                return "0";
            case "WALK":
                return "1";
            case "FACE_NORTH":
                return "2";
            case "FACE_SOUTH":
                return "3";
            case "FACE_EAST":
                return "4";
            case "FACE_WEST":
                return "5";
            case "FACE_NORTH_WEST":
                return "6";
            case "FACE_NORTH_EAST":
                return "7";
            case "FACE_SOUTH_WEST":
                return "8";
            case "FACE_SOUTH_EAST":
                return "9";
            default:
                return "0";
        }
    }

    private class NewLineButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Does nothing, allowing for new lines to be added without resetting any fields.
        }
    }

    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Clear the main field (output area) only
            outputArea.setText("");
        }
    }

    private class CopyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Copy the content of the output area to the clipboard
            String outputText = outputArea.getText();
            StringSelection stringSelection = new StringSelection(outputText);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NPCGenerator().setVisible(true);
            }
        });
    }
}