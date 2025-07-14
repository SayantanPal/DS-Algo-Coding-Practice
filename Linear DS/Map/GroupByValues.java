import java.util.*;
import java.util.stream.*;

class Student {
    String name;
    String grade;
    Student(String name, String grade) {
        this.name = name; this.grade = grade;
    }
    String getGrade() { return grade; }
    public String toString() { return name; }
}
public class GroupByValues {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("A", "10"), new Student("B", "9"), new Student("C", "10")
        );
        Map<String, List<Student>> grouped = students.stream()
                .collect(Collectors.groupingBy(Student::getGrade));
        System.out.println(grouped);
    }
}