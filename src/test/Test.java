package test;
import java.util.ArrayList;
import java.util.Random;

import source_packages.Company;
import source_packages.Investor;
import source_packages.StockExchangeSimulator;

/**@author  Patricia Aparecida Da Silva (s2017146)
 * This is the test class for the Stock Exchange Simulator implemented.
 * It adds random companies and investors in the Stock Exchange, and executes the trading day
 * in Stock Exchange and at the end calls the results of the trading day
 */

	//This is my class Test
	public class Test {
    public static void main(String[] args) {
        // getting the only object of this StockExchangeSimulator
        StockExchangeSimulator simulator = StockExchangeSimulator.getStockExchangeSimulator();

        // initializing the investors and companies lists
        simulator.setInvestors(new ArrayList<>());
        simulator.setCompanies(new ArrayList<>());

        // adding 100 companies with random number of shares between 500 & 1000
        // and random share prices between 10 & 100
        // and uniques IDs
        // adding 100 investors with random budget between 1000 & 10000
        // and uniques IDs
        for (int i = 0; i < 100; i++) {
            Company company = new Company();
            company.setId(i);
            company.setNumberOfShares(getRandomNumberOfShares());
            company.setOriginalShares(company.getNumberOfShares());
            company.setSharePrice(getRandomNumberSharePrice());

            simulator.getCompanies().add(company);

            Investor investor = new Investor();
            investor.setId(i);
            investor.setBudget(getRandomNumberBudget());
            simulator.getInvestors().add(investor);

        }

        // perform trading day
        simulator.performTradingDay();

        //Uncomment this statement and then run the Test class. So, by this, you will see all the companies state and the investors state at the end of the trading day.
        //simulator.printEverything();


        // get results of trading day
        simulator.getResults();



    }

    /**
     * @return random shares between 500 & 1000
     */
    private static int getRandomNumberOfShares() {
        return new Random().nextInt(500) + 500;

    }

    /**
     * @return random share price between 10 & 10000
     */
    private static int getRandomNumberSharePrice() {
        return new Random().nextInt(90) + 10;

    }

    /**
     * @return random budget between 1000 & 10000
     */
    private static int getRandomNumberBudget() {
        return new Random().nextInt(9000) + 1000;

    }

}
