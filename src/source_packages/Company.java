package source_packages;

import java.util.ArrayList;

public class Company {
    private int id;
    private int numberOfShares;
    private int originalShares;
    private int totalNumberOfShares;
    private double sharePrice;
    private int sharesSold;

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

    public synchronized void incrementSharesSold() {
        ++sharesSold;
        --numberOfShares;
    }


    public boolean checkIfTenSharesSold() {
        if (sharesSold >= 10 && sharesSold % 10 == 0) {
            // double share price
            sharePrice *= 2;
            return true;

        }

        return false;

    }

    @Override
    public String toString() {
        return "Company ID: " + this.id +
                "\nCompany shares: " + this.numberOfShares +
                "\nCompany share price: " + this.sharePrice +
                "\nCompany share sold: " + this.sharesSold +
                "\nCompany capital: " + (this.sharePrice * this.numberOfShares);
    }
}
