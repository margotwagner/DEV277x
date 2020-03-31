abstract public class Employee {
    private String name;
    private double baseSalary;
    private int employeeID;
    private static int employeeCount;
    private Employee manager;


    public Employee(String name, double baseSalary) {
        // employee object constructor that takes in name of user and their base salary
        employeeCount++;
        this.name = name;
        this.baseSalary = baseSalary;
        this.employeeID = employeeCount;
    }

    public double getBaseSalary() {
        return this.baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public String getName() {
        return this.name;
    }

    public int getEmployeeID() {
        // returns the employee's ID, which is just the employee number
        return this.employeeID;
    }

    public Employee getManager() {
        return this.manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public boolean equals(Employee other) {
        return this.employeeID == other.employeeID;
    }

    public String toString() {
        // returns a string representation of the employee (id name)
        return (this.employeeID + " " + this.name);
    }

    abstract public String employeeStatus();

}
