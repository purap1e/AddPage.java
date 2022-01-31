package untitled3.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {
    private Connection connection;

    public void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lol_lol?useUnicode=true&serverTimezone=UTC",
                    "root", "");
            System.out.println("Connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Student> getAllStudents(){
        ArrayList<Student> students = new ArrayList<>();

        try{
            PreparedStatement st = connection.prepareStatement("SELECT * FROM studentss");
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int age = rs.getInt("age");

                students.add(new Student(id,name,surname,age));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return students;
    }

    public void addStudent(Student student){
        try{
            PreparedStatement st = connection.prepareStatement("INSERT INTO studentss(id,name,surname,age) values (NULL,?,?,?)");

            st.setString(1,student.getName());
            st.setString(2,student.getSurname());
            st.setInt(3,student.getAge());

            st.executeUpdate();

            st.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student){
        try{
            PreparedStatement st = connection.prepareStatement("UPDATE studentss set name = ?, surname = ?, age = ?, where id = ?");

            st.setString(1, student.getName());
            st.setString(2, student.getSurname());
            st.setInt(3,student.getAge());
            st.setLong(3, student.getId());

            st.executeUpdate();

            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void deleteStudent(Long id){
        try{
            PreparedStatement st = connection.prepareStatement("DELETE FROM studentss where id = ?");

            st.setLong(1,id);

            st.executeUpdate();

            st.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
