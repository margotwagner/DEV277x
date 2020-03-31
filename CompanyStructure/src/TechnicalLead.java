import java.util.ArrayList;

public class TechnicalLead extends TechnicalEmployee {
    private int headCount;
    public ArrayList<SoftwareEngineer> directReports = new ArrayList<>();


    public TechnicalLead(String name) {
        super(name);
        this.setBaseSalary(getBaseSalary()*1.3);
        this.headCount = 4;
        this.checkInsCount = 0;
    }

    public boolean hasHeadCount() {
        // return true if the number of direct reports this manager has is less than their headcount
        return (directReports.size() < headCount);
    }

    public boolean addReport(SoftwareEngineer e) {
        // accept the reference to a SoftwareEngineer object. If the TechnicalLead has head count left, should add this
        // employee to their list of direct reports. If the employee is successfully added to the TechnicalLead's
        // direct reports true should be returned, false should be returned otherwise.
        if (this.hasHeadCount()) {
            directReports.add(e);
            e.setManager(this);
            return true;
        } else { return false;}
    }

    public boolean approveCheckIn(SoftwareEngineer e) {
        // see if the employee passed in reports to this manager and if their code access is currently set to "true".
        // If both true, return true.
        return (directReports.contains(e) && e.getCodeAccess());
    }

    public boolean requestBonus(Employee e, double bonus) {
        // Check if the bonus amount requested would be approved by the BusinessLead supporting this TechnicalLead.
        // If it is, that employee should get that bonus return true.
        if (((Accountant) getManager()).approveBonus(bonus)) {
            ((SoftwareEngineer) e).bonus += bonus;
            return true;
        }
        return false;
    }

    public String getTeamStatus() {
        // Return a string that gives into this Manager and all their direct report. Returns a string that is a combination
        // of the TechnicalLead's employee status followed by each of their direct employee's status on subsequent lines.
        // If the TechnicalLead has no direct reports, it should print their employee status followed by the text "and
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
