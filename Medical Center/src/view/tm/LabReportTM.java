package view.tm;

import java.time.LocalDate;

public class LabReportTM {
    private String labReportId;
    private String testType;
    private LocalDate testedDate;
    private LocalDate receivedDate;
    private double testFee;

    public LabReportTM() {
    }

    public LabReportTM(String labReportId, String testType, LocalDate testedDate, LocalDate receivedDate, double testFee) {
        this.labReportId = labReportId;
        this.testType = testType;
        this.testedDate = testedDate;
        this.receivedDate = receivedDate;
        this.testFee = testFee;
    }


    public LabReportTM(String labReportId, String testType, LocalDate testedDate, LocalDate receivedDate) {
        this.labReportId = labReportId;
        this.testType = testType;
        this.testedDate = testedDate;
        this.receivedDate = receivedDate;
    }

    public String getLabReportId() {
        return labReportId;
    }

    public void setLabReportId(String labReportId) {
        this.labReportId = labReportId;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public LocalDate getTestedDate() {
        return testedDate;
    }

    public void setTestedDate(LocalDate testedDate) {
        this.testedDate = testedDate;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public double getTestFee() {
        return testFee;
    }

    public void setTestFee(double testFee) {
        this.testFee = testFee;
    }

    @Override
    public String toString() {
        return "LabReportTM{" +
                "labReportId='" + labReportId + '\'' +
                ", testType='" + testType + '\'' +
                ", testedDate=" + testedDate +
                ", receivedDate=" + receivedDate +
                ", testFee=" + testFee +
                '}';
    }
}
