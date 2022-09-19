package view.tm;

import java.time.LocalDate;
import java.time.LocalTime;

public class SessionTM {
    private String sessionId;
    private String doctorName;
    private String doctorSpeciality;
    private int maxNoOfPatients;
    private LocalDate sessionDate;
    private LocalTime sessionStartTime;
    private LocalTime sessionEndTime;

    public SessionTM() {
    }

    public SessionTM(String sessionId, String doctorName, String doctorSpeciality, int maxNoOfPatients, LocalDate sessionDate, LocalTime sessionStartTime, LocalTime sessionEndTime) {
        this.sessionId = sessionId;
        this.doctorName = doctorName;
        this.doctorSpeciality = doctorSpeciality;
        this.maxNoOfPatients = maxNoOfPatients;
        this.sessionDate = sessionDate;
        this.sessionStartTime = sessionStartTime;
        this.sessionEndTime = sessionEndTime;
    }

    public SessionTM(String sessionId, int maxNoOfPatients, LocalDate sessionDate, LocalTime sessionStartTime, LocalTime sessionEndTime) {
        this.sessionId = sessionId;
        this.maxNoOfPatients = maxNoOfPatients;
        this.sessionDate = sessionDate;
        this.sessionStartTime = sessionStartTime;
        this.sessionEndTime = sessionEndTime;
    }



    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSpeciality() {
        return doctorSpeciality;
    }

    public void setDoctorSpeciality(String doctorSpeciality) {
        this.doctorSpeciality = doctorSpeciality;
    }

    public int getMaxNoOfPatients() {
        return maxNoOfPatients;
    }

    public void setMaxNoOfPatients(int maxNoOfPatients) {
        this.maxNoOfPatients = maxNoOfPatients;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public LocalTime getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(LocalTime sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    public LocalTime getSessionEndTime() {
        return sessionEndTime;
    }

    public void setSessionEndTime(LocalTime sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }

    @Override
    public String toString() {
        return "SessionTM{" +
                "sessionId='" + sessionId + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", doctorSpeciality='" + doctorSpeciality + '\'' +
                ", maxNoOfPatients=" + maxNoOfPatients +
                ", sessionDate=" + sessionDate +
                ", sessionStartTime=" + sessionStartTime +
                ", sessionEndTime=" + sessionEndTime +
                '}';
    }
}
