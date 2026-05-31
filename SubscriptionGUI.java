
/**
 * Write a description of class SubscriptionGUI here.
 *
 * @author (Krish)
 */

import java.awt.*;
import java.awt.event.*; // IMPORTANT
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class SubscriptionGUI {

    private ArrayList<AIModel> modelList = new ArrayList<>();

    private JFrame frame;
    private JTextField nameField, priceField, paramField, windowField;
    private JTextField quotaField, slotField, promptTextField, lengthField;
    private JTextField memberField, indexField;
    private JTextArea displayArea;

    public SubscriptionGUI() {
        frame = new JFrame("AI Subscription System - 'Krish'");
        frame.setBackground(Color.lightGray);
        frame.setSize(950, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1050, 810));
        frame.setLayout(new BorderLayout(10, 10));

        //TOP SECTION
        JPanel topPanel = new JPanel(new GridLayout(1, 3, 5, 5));

        // 1. Model Info
        JPanel modelInfoPanel = new JPanel(new GridBagLayout());
        modelInfoPanel.setBorder(BorderFactory.createTitledBorder("Model Info"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 5, 2, 5);

        nameField = addRow(modelInfoPanel, "Model Name", 0);
        priceField = addRow(modelInfoPanel, "Price", 1);
        paramField = addRow(modelInfoPanel, "Parameters", 2);
        windowField = addRow(modelInfoPanel, "Context Window", 3);
        topPanel.add(modelInfoPanel);

        // 2. Personal Plan
        JPanel personalPanel = new JPanel(new GridBagLayout());
        personalPanel.setBorder(BorderFactory.createTitledBorder("Personal Plan"));
        quotaField = addRow(personalPanel, "Prompt Quota", 0);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        JButton btnAddPersonal = new JButton("Add Personal Plan");
        btnAddPersonal.setBackground(Color.BLUE);
        btnAddPersonal.setForeground(Color.WHITE);
        btnAddPersonal.setFocusPainted(false);
        btnAddPersonal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPersonalPlan();
            }
        });
        personalPanel.add(btnAddPersonal, gbc);

        gbc.gridy = 2;
        JButton btnGivePrompt = new JButton("Give Prompt");
        btnGivePrompt.setBackground(Color.gray);
        btnGivePrompt.setForeground(Color.WHITE);
        btnGivePrompt.setFocusPainted(false);
        btnGivePrompt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                givePrompt();
            }
        });
        personalPanel.add(btnGivePrompt, gbc);
        topPanel.add(personalPanel);

        // 3. Pro Plan
        JPanel proPanel = new JPanel(new GridBagLayout());
        proPanel.setBorder(BorderFactory.createTitledBorder("Pro Plan"));
        slotField = addRow(proPanel, "Team Slots", 0);
        memberField = addRow(proPanel, "Member Name", 1);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        JButton btnAddPro = new JButton("Add Pro Plan");
        btnAddPro.setBackground(Color.BLUE);
        btnAddPro.setForeground(Color.WHITE);
        btnAddPro.setFocusPainted(false);
        btnAddPro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addProPlan();
            }
        });
        proPanel.add(btnAddPro, gbc);

        gbc.gridx = 1;
        JButton btnAddMember = new JButton("Add Member");
        btnAddMember.setBackground(Color.BLUE);
        btnAddMember.setForeground(Color.WHITE);
        btnAddMember.setFocusPainted(false);
        btnAddMember.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTeamMember();
            }
        });
        proPanel.add(btnAddMember, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        JButton btnRemoveMember = new JButton("Remove Member");
        btnRemoveMember.setBackground(Color.gray);
        btnRemoveMember.setForeground(Color.WHITE);
        btnRemoveMember.setFocusPainted(false);
        btnRemoveMember.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeTeamMember();
            }
        });
        proPanel.add(btnRemoveMember, gbc);

        topPanel.add(proPanel);

        // CENTER
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createTitledBorder("Output"));
        displayArea = new JTextArea();
        displayArea.setEditable(false);

        JScrollPane scroll = new JScrollPane(displayArea);

        ImageIcon icon = new ImageIcon("C:\\Users\\user\\OneDrive\\Pictures\\Screenshots\\Screenshot 2026-05-08 151322.png");

        Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);

        JLabel imageLabel = new JLabel(new ImageIcon(img));

        JPanel outputPanel = new JPanel(new BorderLayout());

        outputPanel.add(scroll, BorderLayout.CENTER);
        outputPanel.add(imageLabel, BorderLayout.EAST);

        centerPanel.add(outputPanel, BorderLayout.CENTER);

        // BOTTOM
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        bottomPanel.add(new JLabel("Index:"));
        indexField = new JTextField(5);
        bottomPanel.add(indexField);

        bottomPanel.add(new JLabel("Prompt:"));
        promptTextField = new JTextField(10);
        bottomPanel.add(promptTextField);

        bottomPanel.add(new JLabel("Length:"));
        lengthField = new JTextField(5);
        bottomPanel.add(lengthField);

        JButton btnCheck = new JButton("Check Plan Type");
        btnCheck.setBackground(Color.cyan);
        btnCheck.setForeground(Color.black);
        btnCheck.setFocusPainted(false);
        btnCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkPlanType();
            }
        });
        bottomPanel.add(btnCheck);

        JButton btnDisplay = new JButton("Display All");
        btnDisplay.setBackground(Color.cyan);
        btnDisplay.setForeground(Color.black);
        btnDisplay.setFocusPainted(false);
        btnDisplay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayAll();
            }
        });
        bottomPanel.add(btnDisplay);

        JButton btnClear = new JButton("Clear");
        btnClear.setBackground(Color.cyan);
        btnClear.setForeground(Color.black);
        btnClear.setFocusPainted(false);
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        bottomPanel.add(btnClear);

        JButton btnExport = new JButton("Export");
        btnExport.setBackground(Color.red);
        btnExport.setForeground(Color.black);
        btnExport.setFocusPainted(false);
        btnExport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportData();
            }
        });
        bottomPanel.add(btnExport);

        JButton btnLoad = new JButton("Load");
        btnLoad.setBackground(Color.red);
        btnLoad.setForeground(Color.black);
        btnLoad.setFocusPainted(false);
        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });
        bottomPanel.add(btnLoad);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JTextField addRow(JPanel panel, String label, int row) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 5, 2, 5);

        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.3;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1; gbc.weightx = 0.7;
        JTextField tf = new JTextField();
        panel.add(tf, gbc);
        return tf;
    }

    private int getIndex() {
        String text = indexField.getText().trim();
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Index field is empty! Please enter an index.");
            return -1;
        }
        int idx;
        try {
            idx = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Index must be a whole number! '" + text + "' is not valid.");
            return -1;
        }
        if (idx < 0) {
            JOptionPane.showMessageDialog(frame, "Index cannot be negative!");
            return -1;
        }
        if (idx >= modelList.size()) {
            JOptionPane.showMessageDialog(frame, "Index " + idx + " is out of range! Valid range: 0 to " + (modelList.size() - 1));
            return -1;
        }
        return idx;
    }

    private void addPersonalPlan() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Model Name cannot be empty!");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Price must be a valid number! Example: 9.99");
            return;
        }

        int params;
        try {
            params = Integer.parseInt(paramField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Parameters must be a whole number! Example: 7");
            return;
        }

        int window;
        try {
            window = Integer.parseInt(windowField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Context Window must be a whole number! Example: 128000");
            return;
        }

        int quota;
        try {
            quota = Integer.parseInt(quotaField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Prompt Quota must be a whole number! Example: 100");
            return;
        }

        modelList.add(new PersonalPlan(name, price, params, window, quota));
        JOptionPane.showMessageDialog(frame, "Personal Plan Added!");
    }

    private void addProPlan() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Model Name cannot be empty!");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Price must be a valid number! Example: 19.99");
            return;
        }

        int params;
        try {
            params = Integer.parseInt(paramField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Parameters must be a whole number! Example: 7");
            return;
        }

        int window;
        try {
            window = Integer.parseInt(windowField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Context Window must be a whole number! Example: 128000");
            return;
        }

        int slots;
        try {
            slots = Integer.parseInt(slotField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Team Slots must be a whole number! Example: 5");
            return;
        }

        modelList.add(new ProPlan(name, price, params, window, slots));
        JOptionPane.showMessageDialog(frame, "Pro Plan Added!");
    }

    private void displayAll() {
        displayArea.setText("");
        if (modelList.isEmpty()) {
            displayArea.append("No models added yet.\n");
            return;
        }
        for (int i = 0; i < modelList.size(); i++) {
            displayArea.append("Index: " + i + "\n");
            displayArea.append(modelList.get(i).display() + "\n\n");
        }
    }

    private void clearFields() {
        JTextField[] fields = {nameField, priceField, paramField, windowField,
                quotaField, slotField, promptTextField, lengthField,
                memberField, indexField};
        for (JTextField f : fields) f.setText("");
    }

    private void givePrompt() {
        int idx = getIndex();
        if (idx == -1) return;

        AIModel obj = modelList.get(idx);
        if (!(obj instanceof PersonalPlan)) {
            JOptionPane.showMessageDialog(frame, "Only Personal Plan allowed!");
            return;
        }

        String prompt = promptTextField.getText().trim();
        if (prompt.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Prompt cannot be empty!");
            return;
        }

        int length;
        try {
            length = Integer.parseInt(lengthField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Response length must be a whole number! Example: 200");
            return;
        }

        String result = ((PersonalPlan) obj).usePrompt(prompt, length);
        JOptionPane.showMessageDialog(frame, result);
    }

    private void addTeamMember() {
        int idx = getIndex();
        if (idx == -1) return;

        AIModel obj = modelList.get(idx);
        if (!(obj instanceof ProPlan)) {
            JOptionPane.showMessageDialog(frame, "Only Pro Plan allowed!");
            return;
        }

        String member = memberField.getText().trim();
        if (member.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Member Name cannot be empty!");
            return;
        }

        String result = ((ProPlan) obj).addTeamMember(member);
        JOptionPane.showMessageDialog(frame, result);
    }

    private void removeTeamMember() {
        int idx = getIndex();
        if (idx == -1) return;

        AIModel obj = modelList.get(idx);
        if (!(obj instanceof ProPlan)) {
            JOptionPane.showMessageDialog(frame, "Only Pro Plan allowed!");
            return;
        }

        String member = memberField.getText().trim();
        if (member.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Member Name cannot be empty!");
            return;
        }

        String result = ((ProPlan) obj).removeTeamMember(member);
        JOptionPane.showMessageDialog(frame, result);
    }

    private void checkPlanType() {
        int idx = getIndex();
        if (idx == -1) return;

        AIModel obj = modelList.get(idx);
        if (obj instanceof PersonalPlan) {
            JOptionPane.showMessageDialog(frame, "This is a Personal Plan");
        } else if (obj instanceof ProPlan) {
            JOptionPane.showMessageDialog(frame, "This is a Pro Plan");
        }
    }

    private void exportData() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.dat"));
            out.writeObject(modelList);
            out.close();
            JOptionPane.showMessageDialog(frame, "Saved successfully!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Save Error: Could not create or find the file 'data.dat'!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Save Error: Problem writing to file! " + e.getMessage());
        }
    }

    private void loadData() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.dat"));
            modelList = (ArrayList<AIModel>) in.readObject();
            in.close();
            displayAll();
            JOptionPane.showMessageDialog(frame, "Loaded successfully!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Load Error: File 'data.dat' not found! Export data first.");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Load Error: Data format not recognized!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Load Error: Problem reading the file! " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new SubscriptionGUI();
    }
}