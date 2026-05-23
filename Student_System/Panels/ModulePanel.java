package Student_System.Panels;

//Name:Tharik Parvas S/O Checkoussain
//Admission Number: p2349288
//Class: DIT/FT/2A/03

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import Student_System.Model.Module;

public class ModulePanel extends JPanel {
    private LabelledInputPanel moduleCodePanel, moduleNamePanel, moduleCreditPanel, moduleMarksPanel, moduleGradePanel;
    private JButton moduleNextButton, modulePrevButton;
    private ArrayList<Module> modules;
    private int currentIndex = 0;

    public ModulePanel() {
        // Set up the panel with a titled border and preferred size
        setBorder(BorderFactory.createTitledBorder("Module"));
        setPreferredSize(new Dimension(350, 200));
        setLayout(new GridBagLayout());

        // Initialize the labelled input panels for module information
        moduleCodePanel = new LabelledInputPanel("Code:");
        moduleNamePanel = new LabelledInputPanel("Name:");
        moduleCreditPanel = new LabelledInputPanel("Credit:");
        moduleMarksPanel = new LabelledInputPanel("Marks:");
        moduleGradePanel = new LabelledInputPanel("Grade:");

        // Initialize buttons
        moduleNextButton = new JButton("Next");
        modulePrevButton = new JButton("Prev");

        // Disable input fields to make it readonly
        moduleCodePanel.SetEditable(false);
        moduleNamePanel.SetEditable(false);
        moduleCreditPanel.SetEditable(false);
        moduleMarksPanel.SetEditable(false);
        moduleGradePanel.SetEditable(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL; // Allow components to resize horizontally

        // Add each component to the panel with specified grid positions
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(moduleCodePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(moduleNamePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(moduleCreditPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(moduleMarksPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(moduleGradePanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(moduleNextButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(modulePrevButton, gbc);

        moduleNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (modules != null && !modules.isEmpty() && currentIndex < modules.size() - 1) {
                    currentIndex++; // Move to the next module
                    displayModuleInfo(currentIndex);
                    updateButtonStates();
                }
            }
        });

        modulePrevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (modules != null && !modules.isEmpty() && currentIndex > 0) {
                    currentIndex--; // Move to the previous module
                    displayModuleInfo(currentIndex);
                    updateButtonStates();
                }
            }
        });

        // Initial button state update
        updateButtonStates();
    }

    // Method to set the list of modules to display
    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
        currentIndex = 0;
        if (modules != null && !modules.isEmpty()) {
            displayModuleInfo(currentIndex); // Display the information for the first module
        } else {
            clearModuleInfo(); // Clear the display if no modules are available
        }
        updateButtonStates();
    }

    // Method to display the information of a module at a specific index
    private void displayModuleInfo(int index) {
        Module module = modules.get(index);
        moduleCodePanel.setText(module.getCode());
        moduleNamePanel.setText(module.getName());
        moduleCreditPanel.setText(String.valueOf(module.getCredit()));
        moduleMarksPanel.setText(String.valueOf(module.getMarks()));
        moduleGradePanel.setText(String.valueOf(module.getGrade()));
        // Update the border title with the current module number
        setBorder(BorderFactory.createTitledBorder("Module " + (currentIndex + 1) + " of " + modules.size()));
    }

    // Method to clear the module information display
    public void clearModuleInfo() {
        moduleCodePanel.setText("");
        moduleNamePanel.setText("");
        moduleCreditPanel.setText("");
        moduleMarksPanel.setText("");
        moduleGradePanel.setText("");
        // Update the border title to show no modules
        setBorder(BorderFactory.createTitledBorder("Modules 0 of 0"));
        moduleNextButton.setEnabled(false);
        moduleNextButton.setEnabled(false);
    }

    // Method to update the state of navigation buttons
    private void updateButtonStates() {
        modulePrevButton.setEnabled(currentIndex > 0);
        moduleNextButton.setEnabled(modules != null && currentIndex < modules.size() - 1);
    }
}
