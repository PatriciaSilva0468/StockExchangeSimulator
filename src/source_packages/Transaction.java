package source_packages;

public class Transaction {
    private int investorID;
    private double sharePrice;
    private int companyID;

    public int getInvestorID() {
        return investorID;
    }

    public void setInvestorID(int investorID) {
        this.investorID = investorID;
    }

    public double getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(double sharePrice) {
        this.sharePrice = sharePrice;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }
}
