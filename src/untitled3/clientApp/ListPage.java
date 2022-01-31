package untitled3.clientApp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import untitled3.core.Student;

import java.net.Socket;
import java.util.ArrayList;

public class ListPage extends JPanel {
    private MainFrame application;

    private JLabel label;
    private JButton back;

    private String header[] = {"ID","NAME","SURNAME","AGE"};
    private JTable table;
    private JScrollPane scrollPane;


    public ListPage(MainFrame application){
        this.application = application;

        setSize(500,500);
        setLayout(null);

        label = new JLabel("LIST STUDENTS");
        label.setSize(300,30);
        label.setLocation(200,10);
        add(label);

        back = new JButton("BACK");
        back.setSize(300,30);
        back.setLocation(100,400);
        add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                application.getListStudents().setVisible(false);
                application.getMainMenuPage().setVisible(true);
            }
        });

        table = new JTable();
        table.setFont(new Font("Calibti",Font.PLAIN,12));
        table.setRowHeight(30);

        scrollPane = new JScrollPane(table);
        scrollPane.setSize(300,200);
        scrollPane.setLocation(100,100);
        add(scrollPane);
    }

    public void generateTable(ArrayList<Student> students){
        Object data[][] = new Object[students.size()][4];

        for(int i = 0; i < students.size(); i++){
            data[i][0] = students.get(i).getId();
            data[i][1] = students.get(i).getName();
            data[i][2] = students.get(i).getSurname();
            data[i][3] = students.get(i).getAge();
        }
        DefaultTableModel model = new DefaultTableModel(data, header);
        table.setModel(model);
    }
}
