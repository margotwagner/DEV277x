import java.util.ArrayList;

public class BusinessLead extends BusinessEmployee {
    private int headCount;
    public ArrayList<Accountant> directReports = new ArrayList<>();

    public BusinessLead(String name) {
        super(name);
        this.setBaseSalary(2 * getBaseSalary());
        this.headCount = 10;
    }

    // make all this stuff and interface
    public boolean hasHeadCount() {
        return directReports.size() < headCount;
    }

    public boolean addReport(Accountant e, TechnicalLead supportTeam) {
        // Accept the reference to an Accountant object. If the BusinessLead has head count left, should add this
        // employee to their list of direct reports. If the employee is successfully added to the TechnicalLead's
        // direct reports true should be returned, false should be returned otherwise.
        // Each time a report is added, the BusinessLead's bonus budget should be increased by 1.1 times that new
        // employee's base salary.
        // Team should be updated to reflect reference to the TechnicalLead given
        if (hasHeadCount()) {
            directReports.add(e);
            e.setManager(this);
            this.bonusBudget += 1.1 * e.getBaseSalary();
            return true;
        } else { return false;}
    }

    public boolean requestBonus(Employee e, double bonus) {
        // Check if bonus amount fits in budget. If it does, employee gets that bonus and budget is deducted and return
        // true.
        if (bonus <= this.bonusBudget) {
            ((SoftwareEngineer) e).bonus += bonus;
            this.bonusBudget -= bonus;
            return true;
        }
        return false;
    }

    public boolean approveBonus(Employee e, double bonus) {
        // Looks through Accountants that BusinessLead manages. If any of them are supporting the TechnicalLead that is
        // the manager of the Employee passed in then the Accountant's budget should be consulted to see if the bonus
        // could be afforded. If the team can afford the bonus, it should be rewarded and true returned.
        for (int i = 0; i < directReports.size(); i++) {
            if (directReports.get(i).equals(e.getManager()) && directReports.get(i).approveBonus(bonus)) {
                return true;
            }
        }
        return false;
    }

    public String getTeamStatus() {
        // Return a string that gives into this Manager and all their direct report. Returns a string that is a combination
        // of the Business's employee status followed by each of their direct employee's status on subsequent lines.
        // If the BusinessLead has no direct reports, it should print their employee status followed by the text "and
        // no direct reports yet."
        String output;
        if (directReports.size() == 0) {
            output = this.employeeStatus() + " and has no direct reports yet.";
        }
        else {
            output = this.employeeStatus() + " and is managing: \n";
            for (int i = 0; i < directReports.size(); i++) {
                output += directReports.get(i).employeeStatus() + " \n";
            }
        }
        return output;
    }

}
