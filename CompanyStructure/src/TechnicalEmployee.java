abstract public class TechnicalEmployee extends Employee {
    public int checkInsCount;

    public TechnicalEmployee(String name) {
        super(name, 75000);
    }

    public String employeeStatus() {
        return (this.toString() +  " has " + checkInsCount + " successful check ins");
    }
}
