public class SoftwareEngineer extends TechnicalEmployee {
    private boolean codeAccess;
    public double bonus;

    public SoftwareEngineer(String name) {
        super(name);
        setCodeAccess(false);
    }

    public boolean getCodeAccess() {
        return this.codeAccess;
    }

    public void setCodeAccess(boolean access) {
        this.codeAccess = access;
    }

    public int getSuccessfulCheckIns() {
        return (checkInsCount);
    }

    public boolean checkInCode() {
        // check if this software engineer's manager approves their check in. If check in is approved, their successful
        // check-in count should be increased and the method should return "true". If the manager does not approve the
        // check in, the SE's code access should be changed to false and the method should return "false".
        if (((TechnicalLead) this.getManager()).approveCheckIn(this)) {
            checkInsCount++;
            return true;
        } else {
                setCodeAccess(false);
                return false;
        }
    }
}
