package view.tm;

public class PatientListTM {
    private int appointmentNo;
    private String patientName;

    public PatientListTM() {
    }

    public PatientListTM(int appointmentNo, String patientName) {
        this.appointmentNo = appointmentNo;
        this.patientName = patientName;
    }

    public int getAppointmentNo() {
        return appointmentNo;
    }

    public void setAppointmentNo(int appointmentNo) {
        this.appointmentNo = appointmentNo;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @Override
    public String toString() {
        return "PatientListTM{" +
                "appointmentNo=" + appointmentNo +
                ", patientName='" + patientName + '\'' +
                '}';
    }
}
