package untitled3.clientApp;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class MainFrame extends JFrame {
    private MainMenu mainMenuPage;
    private AddPage addStudent;
    private ListPage listStudents;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("BITLAB Application");
        setSize(500,500);
        setLayout(null);

        try {
            Socket socket = new Socket("127.0.0.1", 2020);

            ServerConnector sc = new ServerConnector(socket);

            mainMenuPage = new MainMenu(this, sc);
            mainMenuPage.setVisible(true);
            add(mainMenuPage);

            addStudent = new AddPage(this, sc);
            addStudent.setVisible(false);
            add(addStudent);

            listStudents = new ListPage(this);
            listStudents.setVisible(false);
            add(listStudents);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public MainMenu getMainMenuPage() {
        return mainMenuPage;
    }
    public AddPage getAddPage(){
        return addStudent;
    }
    public ListPage getListStudents(){
        return listStudents;
    }
}
