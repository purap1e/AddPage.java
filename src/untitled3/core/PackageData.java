package untitled3.core;

import java.io.Serializable;
import java.util.ArrayList;

public class PackageData implements Serializable {
    private CommandEnum operationType;
    private ArrayList<Student> students = new ArrayList<>();
    private Student student;

    public PackageData() {
    }

    public PackageData(CommandEnum operationType) {
        this.operationType = operationType;
    }

    public PackageData(CommandEnum operationType,Student student) {
        this.operationType = operationType;
        this.student = student;
    }

    public PackageData(ArrayList<Student> students, Student student) {
        this.students = students;
        this.student = student;
    }

    public CommandEnum getOperationType() {
        return operationType;
    }

    public void setOperationType(CommandEnum operationType) {
        this.operationType = operationType;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String toString() {
        return operationType + " " + students + " " + student;
    }
}
