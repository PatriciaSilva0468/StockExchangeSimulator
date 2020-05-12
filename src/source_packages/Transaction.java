/**@author  Patricia Aparecida Da Silva (s2017146)
 * This class represents Transactions made by the investor.
 * It contains the ID of investor,
 * ID of company in which an investment is made
 * and share price of the share bought
 */
package source_packages;
public class Transaction {
    private int investorID;
    private double sharePrice;
    private int companyID;

    /*
     * Here comes getters and setters of the attributes
     * */

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
