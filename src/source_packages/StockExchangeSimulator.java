package source_packages;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class StockExchangeSimulator {
    private ArrayList<Company> companies;
    private ArrayList<Investor> investors;

    private boolean completed;
    private Results results;

    private static StockExchangeSimulator stockExchangeSimulator
            = new StockExchangeSimulator();

    public static StockExchangeSimulator getStockExchangeSimulator() {
        return stockExchangeSimulator;
    }

    public void setCompanies(ArrayList<Company> companies) {
        this.companies = companies;
    }

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

    public void getResults() {
        results.printTheMenu();
    }

    public void sortInvestorsByNumbersOfSharesBought() {
        investors.sort(new Comparator<Investor>() {
            @Override
            public int compare(Investor o1, Investor o2) {
                return Integer.compare(o1.getNumberOfSharesBought(), o2.getNumberOfSharesBought());
            }
        });

    }

    public void sortCompaniesByCapital() {
        companies.sort(new Comparator<Company>() {
            @Override
            public int compare(Company o1, Company o2) {
                return Double.compare(o1.getNumberOfShares() * o1.getSharePrice(),
                        o2.getNumberOfShares() * o2.getSharePrice());
            }
        });

    }

    public void sortCompaniesBySharePrice() {
        companies.sort(new Comparator<Company>() {
            @Override
            public int compare(Company o1, Company o2) {
                return Double.compare(o1.getSharePrice(), o2.getSharePrice());
            }
        });

    }

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
        Thread[] invesmentThreads = new Thread[100];

        for (int i = 0; i < 100; i++) {
            invesmentThreads[i] = new Thread(
                    new InvestmentThread(investors.get(i), companies));

            invesmentThreads[i].start();

        }

        // wait until all investors have their budget ended or
        // all shares of a company are sold

        while (!completed) {
            try {
                // wait for three seconds!
                TimeUnit.SECONDS.sleep(3);
                completed = !isAThreadAlive(invesmentThreads);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static boolean isAThreadAlive(Thread[] invesmentThreads) {
        for (Thread thread : invesmentThreads) {
            if (thread.isAlive()) {
                return true;
            }

        }

        return false;

    }


}
