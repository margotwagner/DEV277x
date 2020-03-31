public class BusinessEmployee extends Employee{
    public double bonusBudget;

    public BusinessEmployee(String name) {
        super(name, 50000);
    }

    public double getBonusBudget() {
        // establish tally of remaining bonusBudget
        return bonusBudget;
    }

    public String employeeStatus() {
        return (String.format(toString() + " with a budget of %.2f", bonusBudget));
    }
}
