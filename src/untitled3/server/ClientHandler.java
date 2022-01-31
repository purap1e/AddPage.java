package untitled3.server;

import untitled3.core.DBManager;
import untitled3.core.PackageData;

import untitled3.core.Student;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {
    private Socket socket;
    private int id;
    ArrayList<Student> students = new ArrayList<>();
    DBManager db;


    public ClientHandler(Socket socket, int id) {
        this.db = new DBManager();
        this.db.connect();
        this.socket = socket;
        this.id = id;
    }

    public void run() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            PackageData requestData;
            while (!socket.isClosed() && (requestData = (PackageData) inputStream.readObject()) != null) {
                System.out.println(String.format("Client #%s sent %s request", id, requestData.getOperationType()));

                switch (requestData.getOperationType()) {
                    case ADD_STUDENT:
                        db.addStudent(requestData.getStudent());
                        break;
                    case LIST_STUDENTS:
                        ArrayList<Student> students = db.getAllStudents();

                        PackageData responseData = new PackageData();
                        responseData.setStudents(students);
                        outputStream.writeObject(responseData);
                        break;
                    case EXIT:
                        return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
