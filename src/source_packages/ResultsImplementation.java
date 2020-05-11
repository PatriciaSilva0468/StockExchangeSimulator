package source_packages;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Implementation of the Results interface.
 * Supporting the bridge pattern
 */
public class ResultsImplementation implements Results {
    private ArrayList<Company> companies;
    private ArrayList<Investor> investors;

    public ResultsImplementation(ArrayList<Company> companies,
                                 ArrayList<Investor> investors) {
        this.companies = companies;
        this.investors = investors;
    }

    public void printTheMenu() {
        int choice;
        do {
            // Print the menu and prompt for user choice
            System.out.println("-- MENU -- ");
            System.out.println("1 Company with the highest capital ");
            System.out.println("2 Company with the lowest capital ");
            System.out.println("3 Investor with the highest number of shares  ");
            System.out.println("4 Investor with the lowest number of shares  ");
            System.out.println("5 Exit  ");
            System.out.print("Choice:  ");

            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();

            // adding a new line for neat interface
            System.out.println();

            switch (choice) {
                case 1:
                    printMaxCapitalCompany();
                    break;
                case 2:
                    printMinCapitalCompany();
                    break;
                case 3:
                    printMaxSharesInvestor();
                    break;
                case 4:
                    printMinSharesInvestor();
                    break;
                case 5:
                    continue;
                default:
                    System.out.println("Wrong option entered");
            }

            // adding new line for neat interface
            System.out.println();

        } while (choice != 5);

    }

    @Override
    public void printMaxCapitalCompany() {
        // as the companies are sorted. The company with largest capital is
        // at last. We are going to print all companies with same capital
        double highestCapital = companies.get(99).getOriginalShares() * companies.get(99).getSharePrice();

        for (int i = 99; i >= 0; i--) {
            if (companies.get(i).getOriginalShares() * companies.get(i).getSharePrice() == highestCapital) {
                System.out.println(companies.get(i));
            } else {
                break;
            }

        }

    }

    @Override
    public void printMinCapitalCompany() {
        // as the companies are sorted. The company with lowest capital is
        // at first. We are going to print all companies with same capital
        double lowestCapital = companies.get(0).getOriginalShares() * companies.get(0).getSharePrice();

        for (int i = 0; i <= 99; i++) {
            if (companies.get(i).getOriginalShares() * companies.get(i).getSharePrice() == lowestCapital) {
                System.out.println(companies.get(i));
            } else {
                break;
            }

        }
    }

    @Override
    public void printMinSharesInvestor() {
        // as the investors are sorted. The investor with largest number of shares bought is
        // at last. We are going to print all investors with same number of shares
        double lowestShares = investors.get(0).getTransactions().size();

        for (int i = 0; i <= 99; i++) {
            if (investors.get(i).getTransactions().size() == lowestShares) {
                System.out.println(investors.get(i));
            } else {
                break;
            }

        }
    }

    @Override
    public void printMaxSharesInvestor() {
        // as the investors are sorted. The investor with smallest number of shares bought is
        // at first. We are going to print all investors with same number of shares
        double highestShares = investors.get(99).getTransactions().size();

        for (int i = 99; i >= 0; i--) {
            if (investors.get(i).getTransactions().size() == highestShares) {
                System.out.println(investors.get(i));
            } else {
                break;
            }

        }
    }
}
