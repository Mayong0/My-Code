import java.awt.*;
import java.io.*;
import javax.swing.*;

public class ExpenseTracker extends JFrame {

    String txtReceiptStr, txtStoreStr, txtCostStr;
    private JTextField txtReceipt, txtStore, txtCost;

    public static void main(String[] args) {
        new ExpenseTracker();
    }

    ExpenseTracker() {

        JLabel lblReceipt = new JLabel("Receipt Number");
        lblReceipt.setBounds(95, 10, 180, 100);
        add(lblReceipt);

        txtReceipt = new JTextField();
        txtReceipt.setBounds(200, 50, 200, 25);
        add(txtReceipt);

        JLabel lblStore = new JLabel("Store Name");
        lblStore.setBounds(95, 60, 180, 100);
        add(lblStore);

        txtStore = new JTextField();
        txtStore.setBounds(200, 100, 200, 25);
        add(txtStore);

        JLabel lblCost = new JLabel("Total Cost");
        lblCost.setBounds(95, 110, 180, 100);
        add(lblCost);

        txtCost = new JTextField();
        txtCost.setBounds(200, 150, 200, 25);
        add(txtCost);

        JButton btnRecord = new JButton("Record");
        btnRecord.setBounds(150, 220, 160, 30);
        add(btnRecord);

        JButton btnViewAll = new JButton("View All");
        btnViewAll.setBounds(150, 260, 160, 30);
        add(btnViewAll);

        JButton btnCalcTotal = new JButton("Calculate");
        btnCalcTotal.setBounds(150, 300, 160, 30);
        add(btnCalcTotal);

        JButton btnDeleteAll = new JButton("Delete");
        btnDeleteAll.setBounds(150, 340, 160, 30);
        add(btnDeleteAll);

        setLayout(null);
        getContentPane().setBackground(new Color(230, 240, 255));
        setTitle("Expense Tracker");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnRecord.addActionListener(e -> {
            txtReceiptStr = txtReceipt.getText().trim();
            txtStoreStr = txtStore.getText().trim();
            txtCostStr = txtCost.getText().trim();

            if (txtReceiptStr.isEmpty() || txtStoreStr.isEmpty() || txtCostStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                return;
            }

            try {
                double totalCost = Double.parseDouble(txtCostStr);
                double tax = totalCost * 0.12;
                double finalAmount = totalCost + tax;

                String record = txtReceiptStr + "|" + txtStoreStr + "|" +
                        String.format("%.2f", totalCost) + "|" +
                        String.format("%.2f", tax) + "|" +
                        String.format("%.2f", finalAmount);

                saveToFile(record);

                txtReceipt.setText("");
                txtStore.setText("");
                txtCost.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Total Cost must be a valid number.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving record.");
            }
        });

        btnViewAll.addActionListener(e -> {
            File file = new File("data.txt");
            if (!file.exists() || file.length() == 0) {
                JOptionPane.showMessageDialog(null, "No records found.");
                return;
            }

            try {
                StringBuilder sb = new StringBuilder();
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length == 5) {
                        sb.append("Receipt No : ").append(parts[0]).append("\n");
                        sb.append("Store Name : ").append(parts[1]).append("\n");
                        sb.append("Total Cost : ").append(parts[2]).append("\n");
                        sb.append("Tax (12%)  : ").append(parts[3]).append("\n");
                        sb.append("Final Amt  : ").append(parts[4]).append("\n");
                        sb.append("---------------------------\n");
                    }
                }
                br.close();
                JOptionPane.showMessageDialog(null, sb.toString(), "All Records", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error reading file.");
            }
        });

        btnCalcTotal.addActionListener(e -> {
            File file = new File("data.txt");
            if (!file.exists() || file.length() == 0) {
                JOptionPane.showMessageDialog(null, "No records found.");
                return;
            }

            try {
                double total = 0;
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length == 5) {
                        total += Double.parseDouble(parts[4]);
                    }
                }
                br.close();
                JOptionPane.showMessageDialog(null, "Total Expenses: " + String.format("%.2f", total), "Total Expenses", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error reading file.");
            }
        });

        btnDeleteAll.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete all records?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    PrintWriter pw = new PrintWriter(new FileWriter("data.txt", false));
                    pw.print("");
                    pw.close();
                    JOptionPane.showMessageDialog(null, "All records deleted.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error deleting records.");
                }
            }
        });

        setVisible(true);
    }

    static void saveToFile(String record) throws IOException {
        FileWriter fw = new FileWriter("data.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(record);
        bw.newLine();
        bw.close();
        JOptionPane.showMessageDialog(null, "Record saved successfully!");
    }
}