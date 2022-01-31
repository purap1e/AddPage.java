package untitled3.clientApp;

import untitled3.core.Student;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class MainMenu extends JPanel {
    private MainFrame application;

    private JButton addStudent;
    private JButton listStudents;
    private JButton exit;

    private ServerConnector sc;

    public MainMenu(MainFrame application, ServerConnector sc){
        this.application = application;
        this.sc = sc;

        setSize(500,500);
        setLayout(null);

        addStudent = new JButton("ADD STUDENT");
        addStudent.setSize(300,30);
        addStudent.setLocation(100,100);
        add(addStudent);
        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                application.getMainMenuPage().setVisible(false);
                application.getAddPage().setVisible(true);
            }
        });

        listStudents = new JButton("LIST STUDENTS");
        listStudents.setSize(300,30);
        listStudents.setLocation(100,150);
        add(listStudents);
        listStudents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<Student> students = sc.listStudents();

                    application.getListStudents().generateTable(students);
                    application.getMainMenuPage().setVisible(false);
                    application.getListStudents().setVisible(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        exit = new JButton("EXIT");
        exit.setSize(300,30);
        exit.setLocation(100,200);
        add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sc.closeSocket();
                System.exit(0);
            }
        });
    }
}
