import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }
    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nPhone Number: " + phoneNumber + "\nEmail Address: " + emailAddress;
    }
}
class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(String name) {
        contacts.removeIf(contact -> contact.getName().equalsIgnoreCase(name));
    }

    public List<Contact> searchContact(String query) {
        List<Contact> result = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(query.toLowerCase()) ||
                    contact.getPhoneNumber().contains(query) ||
                    contact.getEmailAddress().toLowerCase().contains(query.toLowerCase())) {
                result.add(contact);
            }
        }
        return result;
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }
}

public class TASK_5 {
    private AddressBook addressBook;
    private JFrame frame;
    private JList<Contact> contactList;
    private DefaultListModel<Contact> contactListModel;

    public TASK_5() {
        addressBook = new AddressBook();
        frame = new JFrame("Address Book System");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        contactListModel = new DefaultListModel<>();
        contactList = new JList<>(contactListModel);

        JScrollPane scrollPane = new JScrollPane(contactList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Contact");
        JButton removeButton = new JButton("Remove Contact");
        JButton searchButton = new JButton("Search Contact");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addContact();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeContact();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchContact();
            }
        });

        frame.setVisible(true);
    }
    private void addContact() {
        String name = JOptionPane.showInputDialog(frame, "Enter Name:");
        String phoneNumber = JOptionPane.showInputDialog(frame, "Enter Phone Number:");
        String emailAddress = JOptionPane.showInputDialog(frame, "Enter Email Address:");

        if (name != null && !name.isEmpty()) {
            Contact contact = new Contact(name, phoneNumber, emailAddress);
            addressBook.addContact(contact);
            contactListModel.addElement(contact);
        }
    }
    private void removeContact() {
        int selectedIndex = contactList.getSelectedIndex();
        if (selectedIndex != -1) {
            Contact selectedContact = contactListModel.getElementAt(selectedIndex);
            String name = selectedContact.getName();
            addressBook.removeContact(name);
            contactListModel.remove(selectedIndex);
        }
    }

    private void searchContact() {
        String query = JOptionPane.showInputDialog(frame, "Enter search query:");
        if (query != null) {
            contactListModel.clear();
            List<Contact> searchResults = addressBook.searchContact(query);
            for (Contact contact : searchResults) {
                contactListModel.addElement(contact);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TASK_5());
    }
}
