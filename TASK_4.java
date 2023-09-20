import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

public class TASK_4{
    private BankAccount bankAccount;
    private JFrame frame;
    private JTextField amountField;
    private JTextArea displayArea;

    public TASK_4(BankAccount account) {
        bankAccount = account;
        frame = new JFrame("ATM Machine");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel amountLabel = new JLabel("Enter Amount:");
        amountField = new JTextField();
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton balanceButton = new JButton("Check Balance");
        displayArea = new JTextArea();

        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(balanceButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(displayArea, BorderLayout.CENTER);

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        balanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        frame.setVisible(true);
    }

    private void withdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (bankAccount.withdraw(amount)) {
                displayMessage("Withdrawal of $" + amount + " successful.");
            } else {
                displayMessage("Withdrawal failed. Insufficient balance or invalid amount.");
            }
        } catch (NumberFormatException ex) {
            displayMessage("Invalid input. Please enter a valid amount.");
        }
        clearInputField();
    }

    private void deposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            bankAccount.deposit(amount);
            displayMessage("Deposit of $" + amount + " successful.");
        } catch (NumberFormatException ex) {
            displayMessage("Invalid input. Please enter a valid amount.");
        }
        clearInputField();
    }

    private void checkBalance() {
        double balance = bankAccount.getBalance();
        displayMessage("Your account balance is: $" + balance);
        clearInputField();
    }

    private void displayMessage(String message) {
        displayArea.append(message + "\n");
    }

    private void clearInputField() {
        amountField.setText("");
    }

    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0); // Initial balance of $1000
        SwingUtilities.invokeLater(() -> new TASK_4(userAccount));
    }
}
