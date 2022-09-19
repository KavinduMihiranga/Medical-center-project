package model;

import java.time.LocalDateTime;

public class TestLabReport {
    private String testLabReportId;
    private String testId;
    private String labReportId;
    private LocalDateTime receiveDate;

    public TestLabReport() {
    }

    public TestLabReport(String testLabReportId, String testId, String labReportId, LocalDateTime receiveDate) {
        this.testLabReportId = testLabReportId;
        this.testId = testId;
        this.labReportId = labReportId;
        this.receiveDate = receiveDate;
    }

    public String getLabReportId() {
        return labReportId;
    }

    public void setLabReportId(String labReportId) {
        this.labReportId = labReportId;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public LocalDateTime getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDateTime receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getTestLabReportId() {
        return testLabReportId;
    }

    public void setTestLabReportId(String testLabReportId) {
        this.testLabReportId = testLabReportId;
    }

    @Override
    public String toString() {
        return "TestLabReport{" +
                "testLabReportId='" + testLabReportId + '\'' +
                ", testId='" + testId + '\'' +
                ", labReportId='" + labReportId + '\'' +
                ", receiveDate=" + receiveDate +
                '}';
    }
}
