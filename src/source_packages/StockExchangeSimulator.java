/**@author  Patricia Aparecida Da Silva (s2017146)
 * Implementation of StockExchangeSimulator class
 */
package source_packages;
import java.util.*;
import java.util.concurrent.TimeUnit;

//This is my class StockExchangeSimulator
public class StockExchangeSimulator {
    private ArrayList<Company> companies;
    private ArrayList<Investor> investors;

    private boolean completed;
    private Results results;

    //Following statement creates an object stockExchangeSimulator
    private static StockExchangeSimulator stockExchangeSimulator
            = new StockExchangeSimulator();

    public static StockExchangeSimulator getStockExchangeSimulator() {
        return stockExchangeSimulator;
    }

    //Companies will be stored in an ArrayList
    public void setCompanies(ArrayList<Company> companies) {
        this.companies = companies;
    }

  //Investors will be stored in an ArrayList
    public void setInvestors(ArrayList<Investor> investors) {
        this.investors = investors;
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public ArrayList<Investor> getInvestors() {
        return investors;
    }

    public void performTradingDay() {
        // sort the companies by their share prices in ascending order
        sortCompaniesBySharePrice();

        // for five times we will check if investment can be made then we will run
        // trading day again. This is done in order to save from infinite running threads
        for (int i = 0; i < 5; i++) {
            if (checkIfAnyMoreInvestmentCanBeMade()) {
                runTradingDay();
            } else {
                break;
            }
        }

        // initialize Result so that user can see the results of trading day
        results = new ResultsImplementation(companies, investors);

        // sort the companies by their capitals in ascending order
        sortCompaniesByCapital();

        // sort the investors by their number of shares bought in ascending order
        sortInvestorsByNumbersOfSharesBought();

    }

    /* Print the companies details */
    public void printEverything() {
        System.out.println("The companies:  ");
        for (Company company : companies) {
            System.out.println("ID:  " + company.getId());
            System.out.println("Total shares:  " + company.getNumberOfShares());
            System.out.println("Share price:  " + company.getSharePrice());
            System.out.println("Share sold:  " + company.getSharesSold());
            System.out.println("Company capital:  " + company.getCompanyCapital());

            System.out.println();

        }

        /* Print the investors details */
        System.out.println("The investors:  ");
        for (Investor investor : investors) {
            System.out.println("ID:  " + investor.getId());
            System.out.println("Total budget:  " + investor.getBudget());
            System.out.println("Shared bought:  " + investor.getNumberOfSharesBought());

            System.out.println();

        }


    }


    //Print the results
    public void getResults() {
        results.printTheMenu();
    }

    //Sorting investors by number of shares bought
    public void sortInvestorsByNumbersOfSharesBought() {
        investors.sort(new Comparator<Investor>() {
            @Override
            public int compare(Investor o1, Investor o2) {
                return Integer.compare(o1.getNumberOfSharesBought(), o2.getNumberOfSharesBought());
            }
        });

    }

    //Sorting companies by capital
    public void sortCompaniesByCapital() {
        companies.sort(new Comparator<Company>() {
            @Override
            public int compare(Company o1, Company o2) {
                return Double.compare(o1.getCompanyCapital(),
                        o2.getCompanyCapital());
            }
        });

    }

    //Sorting companies by share price
    public void sortCompaniesBySharePrice() {
        companies.sort(new Comparator<Company>() {
            @Override
            public int compare(Company o1, Company o2) {
                return Double.compare(o1.getSharePrice(), o2.getSharePrice());
            }
        });

    }

    //Get and return company Iterator
    public CompanyIterator getCompanyIterator() {
        return new CompanyIterator();
    }

    /**
     * Here an inner class is implementing a Company Iterator
     */
    class CompanyIterator implements Iterator<Company> {
        private int companyNumber;

        public CompanyIterator() {
            companyNumber = 0;
        }

        @Override
        public synchronized boolean hasNext() {
            return companyNumber < companies.size();
        }

        @Override
        public synchronized Company next() {
            Company company = companies.get(companyNumber);
            companyNumber++;
            return company;

        }
    }

    private boolean checkIfAnyMoreInvestmentCanBeMade() {
        // if any investor's budget can manage the share price
        // of a company then there is still possibility of another
        // investment for investor
        for (Investor investor : investors) {
            for (Company company : companies) {
                if (investor.getBudget() >= company.getSharePrice() &&
                        company.getNumberOfShares() >= 1) {
                    // investor can buy more!
                    return true;

                }

            }

        }

        // cannot buy more
        return false;

    }

    private void runTradingDay() {
        // create 100 threads, each thread for an investor and start them
        Thread[] investmentThreads = new Thread[100];

        for (int i = 0; i < 100; i++) {
            investmentThreads[i] = new Thread(
                    new InvestmentThread(investors.get(i)));

            investmentThreads[i].start();

        }

        // wait until all investors have their budget ended or
        // all shares of a company are sold

        while (!completed) {
            try {
                // wait for three seconds!
                TimeUnit.SECONDS.sleep(3);
                completed = !isAnyThreadAlive(investmentThreads);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    // If there is any thread that has not ended yet
    // it will return true
    private static boolean isAnyThreadAlive(Thread[] invesmentthreads) {
        for (Thread thread : invesmentthreads) {
            if (thread.isAlive()) {
                return true;
            }

        }

        return false;

    }


}
