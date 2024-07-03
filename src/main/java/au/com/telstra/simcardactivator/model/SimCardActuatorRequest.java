package au.com.telstra.simcardactivator.model;

public class SimCardActuatorRequest {
    private String iccid;

    public SimCardActuatorRequest(String iccid) {
        this.iccid = iccid;
    }

    // Getters and Setters
    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }
}
