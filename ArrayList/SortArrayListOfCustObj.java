import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortArrayListOfCustObj {
    public static void main(String[] args) {
        List<Employee> list = Arrays.asList(
                new Employee("John", 50000),
                new Employee("Alice", 70000),
                new Employee("Bob", 60000)
        );
        list.sort(Comparator.comparingDouble(Employee::getSalary));
        System.out.println(list);
    }
}

class Employee {
    String name;
    double salary;
    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    public String toString() {
        return name + ": $" + salary;
    }
    public double getSalary() {
        return salary;
    }
}
