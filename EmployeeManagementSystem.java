import java.util.*;

class Employee {
    private int id;
    private String name;
    private double salary;
    private int age;
    private String employeeType;

    public Employee(int id, String name, double salary, int age, String employeeType) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.employeeType = employeeType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            System.out.println("Error: Salary cannot be less than 0.");
            return;
        }
        this.salary = salary;
    }

    public void setAge(int age) {
        if (age < 1) {
            System.out.println("Error: Age cannot be less than 1.");
            return;
        }
        this.age = age;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Salary: " + salary + ", Age: " + age + ", Type: " + employeeType;
    }
}

public class EmployeeManagementSystem {
    private static List<Employee> employeeList = new ArrayList<>();
    private static int nextId = 1;

    private static void addEmployee() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        if (salary < 0) {
            System.out.println("Error: Salary cannot be less than 0.");
            return;
        }

        if (age < 1) {
            System.out.println("Error: Age cannot be less than 1.");
            return;
        }

        sc.nextLine(); // Consume newline
        sc.close();

        System.out.println("Select Employee Type:");
        System.out.println("1. Full-Time Employee");
        System.out.println("2. Part-Time Employee");
        System.out.println("3. Intern Employee");
        System.out.print("Enter your choice: ");

        int typeChoice = sc.nextInt();
        String employeeType;

        switch (typeChoice) {
            case 1:
                employeeType = "Full-Time Employee";
                break;
            case 2:
                employeeType = "Part-Time Employee";
                break;
            case 3:
                employeeType = "Intern Employee";
                break;
            default:
                System.out.println("Invalid choice! Defaulting to General Employee.");
                employeeType = "General Employee";
        }

        Employee newEmployee = new Employee(nextId, name, salary, age, employeeType);
        employeeList.add(newEmployee);
        System.out.println(employeeType + " added successfully! ID: " + nextId);
        nextId++;
    }

    private static void viewEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        System.out.println("Employee List:");
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    private static void updateEmployee(int id, String newName, double newSalary, int newAge) {
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                if (newSalary < 0) {
                    System.out.println("Error: Salary cannot be less than 0.");
                    return;
                }
                if (newAge < 1) {
                    System.out.println("Error: Age cannot be less than 1.");
                    return;
                }
                employee.setName(newName);
                employee.setSalary(newSalary);
                employee.setAge(newAge);
                System.out.println("Employee updated successfully!");
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }

    private static void deleteEmployee(int id) {
        Iterator<Employee> iterator = employeeList.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getId() == id) {
                iterator.remove();
                System.out.println("Employee deleted successfully!");
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Employee Management System ===");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;

                case 2:
                    viewEmployees();
                    break;

                case 3:
                    System.out.print("Enter Employee ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter New Salary: ");
                    double newSalary = sc.nextDouble();
                    System.out.print("Enter New Age: ");
                    int newAge = sc.nextInt();
                    updateEmployee(updateId, newName, newSalary, newAge);
                    break;

                case 4:
                    System.out.print("Enter Employee ID to delete: ");
                    int deleteId = sc.nextInt();
                    deleteEmployee(deleteId);
                    break;

                case 5:
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
