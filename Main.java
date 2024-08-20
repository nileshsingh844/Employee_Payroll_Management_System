//ABSTRACTION: Here we will create an abstract class and declare an abstract method inside it

import java.util.ArrayList;

abstract class Employee{
    private String name;
    private int id;

    //Encapsulation: Here using private access modifiers and getter for them,
    //to protect our instance variable by controlling access
    public Employee(String name, int id){
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }
    
    //ABSTRACTION: Here we're declaring an abstract method 
    public abstract double calculateSalary();

    //POLYMORPHISM: here we're override default java toString method,
    //so that when we print object of this class using sout, it'll be printed in this format
    @Override
    public String toString(){
        return "Employee: [ name:"+ name + ", id:" + id +", Salary: " + calculateSalary()+ " ]";
    }

}

//INHERITANCE: We'll create first type of Employee, FullTimeEmployee and
//extend properties from Employee class, FullTimeEmployee have fixed monthly salary

class FullTimeEmployee extends Employee{
    private double monthlySalary;

    //Inheritance: we'll declare contructor and fetch properties of parent class
    //using super() keyword
    public FullTimeEmployee(String name, int id, double monthlySalary){
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    //ABSTRACTION and POLYMORPHISM, since we extended abstract class, we need to provide
    //its implementation, by overridng it(Runtime Polymorphism)
    @Override
    public double calculateSalary(){
        return monthlySalary;
    }
}

//We'll declare PartTimeEmployee which have salary based on monthHours and HourlyRate
class PartTimeEmployee extends Employee{
    private int monthlyHours;
    private double hourlyRate;

    //Inheritance, we will declare a constructor, and fetch properties from parent class
    public PartTimeEmployee(String name, int id, int monthHours, double hourlyRate){
        super(name, id);
        this.monthlyHours = monthHours;
        this.hourlyRate = hourlyRate;
    }

    //POLYMORPHISM, we'll implement the abstract calculateSalary method
    @Override
    public double calculateSalary(){
        return monthlyHours * hourlyRate;
    }

}

//Now we'll create PayrolSystem to add, remove and display employee,
//we'll store employees in a list and perform CRUD operations
class PayrolSystem{
    ArrayList<Employee> employeeList;

    //we'll declare a constructor, when PayrolSystem object created,
    // an employeeList will be created
    public PayrolSystem(){
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee){
        employeeList.add(employee);
    }

    public void removeEmployee(int id){
        Employee employeeToRemove = null;

        for(Employee employee : employeeList){
            if(employee.getId() == id){
                employeeToRemove = employee;
                break;
            }
        }

        if(employeeToRemove != null){
            System.out.println("Removed " + employeeToRemove.getName());
            employeeList.remove(employeeToRemove);
        }        
    }

    public void displayEmployee(){
        for(Employee employee : employeeList){
            System.out.println(employee);
        }
    }
}

class Main{
    public static void main(String[] args) {
        PayrolSystem payrolSystem = new PayrolSystem();
        FullTimeEmployee emp1 = new FullTimeEmployee("John", 1, 80000.0);
        PartTimeEmployee emp2 = new PartTimeEmployee("Jeniffer", 2, 40, 100);

        payrolSystem.addEmployee(emp1);
        payrolSystem.addEmployee(emp2);

        System.out.println("Displaying all Employees");
        payrolSystem.displayEmployee();

        payrolSystem.removeEmployee(1);

        System.out.println("Displaying remaining Employees");
        payrolSystem.displayEmployee();
    }
}