import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

// Main class for the Payroll Management System GUI
public class payrollsystem extends JFrame {
    // Instance variables for storing user input strings
    String txtEnameStr, txtHrateStr, txtHworkedStr;
    // Static variables for the table model, table, and scroll pane
    static DefaultTableModel model;
    static JTable table;
    static JScrollPane scrollPane;
    // Private text fields for employee name, hourly rate, and hours worked
    private JTextField txtEname, txtHrate, txtHworked;

    // Main method to launch the application
    public static void main(String[] args) throws IOException {
        new payrollsystem();
    }

    // Constructor to set up the GUI components
    payrollsystem() {
        // Label for Employee Name
        JLabel lblHeader = new JLabel("Employee Name");
        lblHeader.setBounds(40, 10, 180, 100);
        add(lblHeader);

        // Text field for Employee Name
        JTextField txtEname = new JTextField();
        txtEname.setBounds(41, 75, 220, 25);
        add(txtEname);

        // Label for Hourly Rate
        JLabel lblHeader2 = new JLabel("Hourly Rate");
        lblHeader2.setBounds(40, 60, 180, 100);
        add(lblHeader2);

        // Text field for Hourly Rate
        JTextField txtHrate = new JTextField();
        txtHrate.setBounds(41, 125, 220, 25);
        add(txtHrate);

        // Label for Hours Worked
        JLabel lblHeader3 = new JLabel("Hours Worked");
        lblHeader3.setBounds(40, 110, 180, 100);
        add(lblHeader3);

        // Text field for Hours Worked
        JTextField txtHworked = new JTextField();
        txtHworked.setBounds(41, 175, 220, 25);
        add(txtHworked);

        // Button to calculate salary
        JButton btnAdd = new JButton("Calculate Salary");
        btnAdd.setBounds(70, 220, 160, 30);
        btnAdd.setBackground(new Color(46, 204, 113));
        add(btnAdd);

        // Button to add record to table
        JButton btnAddtoTable = new JButton("Add to Table");
        btnAddtoTable.setBounds(70, 260, 160, 30);
        btnAddtoTable.setBackground(new Color(52, 152, 219));
        add(btnAddtoTable);

        // Button to clear input fields
        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(70, 300, 160, 30);
        btnClear.setBackground(new Color(241, 196, 15));
        add(btnClear);

        // Button to delete selected record
        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(70, 340, 160, 30);
        btnDelete.setBackground(new Color(231, 76, 60));
        add(btnDelete);

        // Button to update selected record
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(70, 380, 160, 30);
        btnUpdate.setBackground(new Color(155, 89, 182));
        add(btnUpdate);

        // Define table columns
        String[] columns = {"Employee Name", "Hourly Rate", "Hours Worked", "Gross Pay", "Tax Deduction", "Net Pay"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(280, 50, 580, 450);
        add(scrollPane);

        // Action listener for Calculate Salary button
        btnAdd.addActionListener(e -> {
            try {
                // Parse input values
                double rate = Double.parseDouble(txtHrate.getText());
                double hours = Double.parseDouble(txtHworked.getText());
                // Calculate gross pay
                double grossPay = calculateGrossPay(rate, hours);
                // Calculate tax and net pay
                double tax = grossPay * 0.12;
                double netPay = grossPay - tax;

                // Display results in a dialog
                String message = "Gross Pay: " + String.format("$%.2f", grossPay) + "\n" +
                        "Tax Deduction: " + String.format("$%.2f", tax) + "\n" +
                        "Net Pay: " + String.format("$%.2f", netPay);
                JOptionPane.showMessageDialog(null, message);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numeric values.");
            }
        });

        // Action listener for Add to Table button
        btnAddtoTable.addActionListener(e -> {
            // Get input strings
            txtEnameStr = txtEname.getText();
            txtHrateStr = txtHrate.getText();
            txtHworkedStr = txtHworked.getText();

            try {
                // Parse and calculate
                double rate = Double.parseDouble(txtHrateStr);
                double hours = Double.parseDouble(txtHworkedStr);
                double grossPay = calculateGrossPay(rate, hours);
                double tax = grossPay * 0.12;
                double netPay = grossPay - tax;

                // Create record string
                String payrollRecord = txtEnameStr + "|" + txtHrateStr + "|" + txtHworkedStr + "|" +
                        String.format("$%.2f", grossPay) + "|" +
                        String.format("$%.2f", tax) + "|" + String.format("$%.2f", netPay);

                // Save to file and refresh table
                saveToFile(payrollRecord);
                txtEname.setText("");
                txtHrate.setText("");
                txtHworked.setText("");
                refreshTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error adding record. Please try again.");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        // Set layout and window properties
        setLayout(null);
        getContentPane().setBackground(new Color(230, 240, 255));
        setTitle("Payroll Management System");
        setSize(880, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Action listener for Clear button
        btnClear.addActionListener(e -> {
            txtEname.setText("");
            txtHrate.setText("");
            txtHworked.setText("");
        });

        // Action listener for Delete button
        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Select one to delete.");
                return;
            }

            model.removeRow(selectedRow);

            try {
                removeRecordFromFile(selectedRow);
                refreshTable();
                JOptionPane.showMessageDialog(null, "Deleted successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error deleting record.");
            }
        });

        // Action listener for Update button
        btnUpdate.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a record to update.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String name = txtEname.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                // Parse and calculate new values
                double rate = Double.parseDouble(txtHrate.getText().trim());
                double hours = Double.parseDouble(txtHworked.getText().trim());
                double gross = calculateGrossPay(rate, hours);
                double tax = gross * 0.12;
                double net = gross - tax;

                // Create updated record
                String updatedRecord = String.join("|",
                        name,
                        String.format("%.2f", rate),
                        String.format("%.2f", hours),
                        String.format("$%.2f", gross),
                        String.format("$%.2f", tax),
                        String.format("$%.2f", net)
                );

                // Read file, update line, write back
                ArrayList<String> lines = new ArrayList<>();
                try (BufferedReader br = new BufferedReader(new FileReader("payrolldata.txt"))) {
                    String line;
                    int i = 0;
                    while ((line = br.readLine()) != null) {
                        if (i == row) {
                            lines.add(updatedRecord);
                        } else {
                            lines.add(line);
                        }
                        i++;
                    }
                }

                try (BufferedWriter bw = new BufferedWriter(new FileWriter("payrolldata.txt"))) {
                    for (String ln : lines) {
                        bw.write(ln);
                        bw.newLine();
                    }
                }

                JOptionPane.showMessageDialog(this, "Updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                txtEname.setText("");
                txtHrate.setText("");
                txtHworked.setText("");

                refreshTable();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Rate and Hours must be valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error updating record:\n" + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

    // Method to calculate gross pay with overtime
    private double calculateGrossPay(double rate, double hours) {
        if (hours <= 40) {
            return rate * hours;
        } else {
            double regularPay = 40 * rate;
            double overtimePay = (hours - 40) * (rate * 1.5);
            return regularPay + overtimePay;
        }
    }

    // Method to save a record to file
    static void saveToFile(String record) throws IOException {
        FileWriter fw = new FileWriter("payrolldata.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(record);
        bw.newLine();
        bw.close();
        JOptionPane.showMessageDialog(null, "Record saved successfully!");
    }

    // Method to refresh the table from file
    private void refreshTable() throws IOException {
        model.setRowCount(0);
        File file = new File("payrolldata.txt");
        if (!file.exists()) {
            file.createNewFile();
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] row = line.split("\\|");
            model.addRow(row);
        }
        br.close();
    }

    // Method to remove a record from file
    private void removeRecordFromFile(int rowToDelete) throws IOException {
        File file = new File("payrolldata.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder fileContents = new StringBuilder();
        String line;
        int currentRow = 0;

        while ((line = br.readLine()) != null) {
            if (currentRow != rowToDelete) {
                fileContents.append(line).append("\n");
            }
            currentRow++;
        }
        br.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(fileContents.toString());
        writer.close();
    }
}