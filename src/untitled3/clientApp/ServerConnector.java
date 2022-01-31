package untitled3.clientApp;

import untitled3.core.CommandEnum;
import untitled3.core.PackageData;
import untitled3.core.Student;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerConnector {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ServerConnector(Socket socket) {
        try {
            this.socket = socket;
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student student) throws IOException {
        try {
            PackageData requestData = new PackageData(CommandEnum.ADD_STUDENT, student);
            outputStream.writeObject(requestData);
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<Student> listStudents() throws IOException, ClassNotFoundException {
        try {
            PackageData requestData = new PackageData(CommandEnum.LIST_STUDENTS);
            outputStream.writeObject(requestData);

            PackageData responseData;
            while ((responseData = (PackageData) inputStream.readObject()) != null) {
                return responseData.getStudents();
            }
            return new ArrayList();
        } catch (Exception e) {
            throw e;
        }
    }



    public void closeSocket(){
        try {
            PackageData requestData = new PackageData(CommandEnum.EXIT);
            outputStream.writeObject(requestData);

            this.inputStream.close();
            this.outputStream.close();
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
