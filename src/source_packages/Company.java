/**@author  Patricia Aparecida Da Silva (s2017146)
 * This class represents Company.
 * It has an id, number of shares (which might be updated later),
 * original shares (cannot be updated),
 * share price
 * and number of shared sold
 */
package source_packages;

public class Company {
    private int id;
    private int numberOfShares;
    private int originalShares;
    private int totalNumberOfShares;
    private double sharePrice;
    private int sharesSold;
    private double companyCapital;

    public int getOriginalShares() {
        return originalShares;
    }

    public void setOriginalShares(int originalShares) {
        this.originalShares = originalShares;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public double getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(double sharePrice) {
        this.sharePrice = sharePrice;
    }

    public int getSharesSold() {
        return sharesSold;
    }

    public double getCompanyCapital() {
        return companyCapital;
    }

    public void setCompanyCapital(double companyCapital) {
        this.companyCapital = companyCapital;
    }

    /*
     * Many investment threads will be running parallel and there is a chance
     * that more than one thread accesses a company at same time. So to prevent problems
     * caused by parallel calling to this method it is made synchronized
     */
    public synchronized void incrementSharesSold() {
        ++sharesSold;
        --numberOfShares;
    }


    /*
    * On each selling of 10 shares, the share price doubles up*/
    public boolean checkIfTenSharesSold() {
        if (sharesSold >= 10 && sharesSold % 10 == 0) {
            // double share price
            sharePrice *= 2;
            return true;

        }

        return false;

    }

    /*
    * To string method representing the object in appropriate string form*/
    @Override
    public String toString() {
        return "Company ID: " + this.id +
                "\nCompany shares: " + this.numberOfShares +
                "\nCompany share price: " + this.sharePrice +
                "\nCompany share sold: " + this.sharesSold +
                "\nCompany capital: " + this.getCompanyCapital();
    }
}
