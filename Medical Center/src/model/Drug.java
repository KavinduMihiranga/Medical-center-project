package model;

public class Drug {
    private String drugId;
    private String drugName;
    private String tradeName;

    public Drug() {
    }

    public Drug(String drugId, String drugName, String tradeName) {
        this.drugId = drugId;
        this.drugName = drugName;
        this.tradeName = tradeName;
    }

    public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "drugId='" + drugId + '\'' +
                ", drugName='" + drugName + '\'' +
                ", tradeName='" + tradeName + '\'' +
                '}';
    }
}
