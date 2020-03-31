public class Accountant extends BusinessEmployee {
    private TechnicalLead teamSupported;

    public Accountant(String name) {
        super(name);
    }

    public TechnicalLead getTeamSupported() {
        return this.teamSupported;
    }

    public void supportTeam(TechnicalLead lead) {
        // Allow a reference to a TL to be passed in and saved. Once this happens, the Accountant's bonus budget should
        // be updated to be the total of each SoftwareEngineer's base salary that reports to that TL plus 10%.
        this.teamSupported = lead;
        double budgetTotal = 0;
        for (int i = 0; i < lead.directReports.size(); i++) {
            budgetTotal += lead.directReports.get(i).getBaseSalary();
        }

        this.bonusBudget = budgetTotal * 1.1;

    }

    public boolean approveBonus(double bonus) {
        // take in suggested bonus amount and check if there is still enough room in the budget.
        // if the bonus is greater than the remaining budget, return false.
        // If the account is not supporting any team, false should be returned.

        return (teamSupported != null && bonus < bonusBudget);

    }

    public String employeeStatus() {
        return (super.employeeStatus() + " is supporting " + this.teamSupported);
    }

}
