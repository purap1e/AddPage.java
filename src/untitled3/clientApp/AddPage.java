package untitled3.clientApp;

import untitled3.core.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddPage extends JPanel {
    private MainFrame application;
    private JButton add;
    private JButton back;
    private JLabel label;
    private JTextField textField;
    private JTextField textField1;
    private JTextField textField2;
    private ServerConnector sc;

    public AddPage(MainFrame application, ServerConnector sc) {
        this.application = application;
        this.sc = sc;

        setSize(500, 500);
        setLayout(null);

        label = new JLabel("Name:");
        label.setSize(300, 30);
        label.setLocation(100, 150);
        add(label);
        textField = new JTextField("");
        textField.setSize(300, 30);
        textField.setLocation(250, 150);
        add(textField);

        label = new JLabel("Surname:");
        label.setSize(300, 30);
        label.setLocation(100, 200);
        add(label);
        textField1 = new JTextField("");
        textField1.setSize(300, 30);
        textField1.setLocation(250, 200);
        add(textField1);

        label = new JLabel("Age:");
        label.setSize(300, 30);
        label.setLocation(100, 250);
        add(label);
        textField2 = new JTextField("");
        textField2.setSize(300, 30);
        textField2.setLocation(250, 250);
        add(textField2);

        add = new JButton("ADD");
        add.setSize(150, 30);
        add.setLocation(150, 300);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.equals("") && !textField1.equals("") && !textField2.equals("")) {
                    String name = textField.getText();
                    String surname = textField1.getText();
                    int age = Integer.parseInt((String.valueOf(textField2.getText())));

                    Student s = new Student(null, name, surname, age);

                    try {
                        sc.addStudent(s);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    textField.setText("");
                    textField1.setText("");
                    textField2.setText("");
                }
            }
        });
        add(add);

        back = new JButton("BACK");
        back.setSize(150, 30);
        back.setLocation(350, 300);
        add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                application.getAddPage().setVisible(false);
                application.getMainMenuPage().setVisible(true);
            }
        });
    }

}

