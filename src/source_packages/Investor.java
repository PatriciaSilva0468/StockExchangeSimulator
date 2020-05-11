package source_packages;
import java.util.ArrayList;

/**
 * This represents an Investor with attributes:
 * id,
 * budget
 * number of shares bought
 * the transactions (each will contain one share bought)
 */
public class Investor {
    private int id;
    private double budget;
    private int numberOfSharesBought;
    private ArrayList<Transaction> transactions;

    public Investor() {
        transactions = new ArrayList<>();
    }

    /*
     * The getters and setters of the attributes
     * */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public int getNumberOfSharesBought() {
        return numberOfSharesBought;
    }

    public void setNumberOfSharesBought(int numberOfSharesBought) {
        this.numberOfSharesBought = numberOfSharesBought;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    /*
     * The toString method to represent an Investor object with appropriate string*/
    @Override
    public String toString() {
        return "Investor ID: " + this.id +
                "\nInvestor budget: " + this.getBudget() +
                "\nNumber of shares bought: " + this.numberOfSharesBought;
    }
}
