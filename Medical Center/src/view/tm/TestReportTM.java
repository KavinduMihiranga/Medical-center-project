package view.tm;

import java.time.LocalDateTime;

public class TestReportTM {
    private String testType;
    private LocalDateTime testedDay;
    private LocalDateTime receivedDay;
    private double testFee;

    public TestReportTM() {
    }

    public TestReportTM(String testType, LocalDateTime testedDay, LocalDateTime receivedDay, double testFee) {
        this.testType = testType;
        this.testedDay = testedDay;
        this.receivedDay = receivedDay;
        this.testFee = testFee;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public LocalDateTime getTestedDay() {
        return testedDay;
    }

    public void setTestedDay(LocalDateTime testedDay) {
        this.testedDay = testedDay;
    }

    public LocalDateTime getReceivedDay() {
        return receivedDay;
    }

    public void setReceivedDay(LocalDateTime receivedDay) {
        this.receivedDay = receivedDay;
    }

    public double getTestFee() {
        return testFee;
    }

    public void setTestFee(double testFee) {
        this.testFee = testFee;
    }

    @Override
    public String toString() {
        return "TestReportTM{" +
                "testType='" + testType + '\'' +
                ", testedDay=" + testedDay +
                ", ReceivedDay=" + receivedDay +
                ", testFee=" + testFee +
                '}';
    }
}
