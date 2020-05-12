/**@author  Patricia Aparecida Da Silva (s2017146)
 * This thread will run for one investor.
 * The investor will iterate over all the companies and try to buy maximum
 * number of shares in maximum companies he can buy with his budget. This thread ends once the
 * investor is not able to buy more shares.
 */
package source_packages;

public class InvestmentThread implements Runnable {
    private Investor investor;

    public InvestmentThread(Investor investor) {
        this.investor = investor;
    }

    @Override
    public void run() {
        // getting the least share the investor can buy at this time
        double leastLimit = getCompanyWithLeastSharesPrice();

        boolean aCompanySoldTenShares = false;

        while (investor.getBudget() >= leastLimit) {
            // investor has enough budget to buy

            // get company iterator
            StockExchangeSimulator.CompanyIterator companyIterator =
                    StockExchangeSimulator.getStockExchangeSimulator().getCompanyIterator();

            while (companyIterator.hasNext()) {
                Company company = companyIterator.next();

                if (company.getNumberOfShares() == 0) {
                    // no shares of this company left now
                    continue;
                }

                if (company.getSharesSold() == 0 && aCompanySoldTenShares) {
                    // this company didn't sell a single share
                    // meanwhile another company sold ten shares!
                    // reduces 2% share price
                    double sharePrice = company.getSharePrice();
                    sharePrice = sharePrice - (0.02 * sharePrice);
                    company.setSharePrice(sharePrice);

                }

                double sharePrice = company.getSharePrice();
                // check if investor can afford share of this company
                // if yes then buy one share of this company
                if (sharePrice <= investor.getBudget()) {

                    // cut share price from investor budget
                    investor.setBudget(investor.getBudget() - sharePrice);

                    // increment number of shares bought
                    investor.setNumberOfSharesBought(investor.getNumberOfSharesBought() + 1);

                    // manageable according to the investor's budget
                    Transaction transaction = new Transaction();
                    transaction.setInvestorID(investor.getId());
                    transaction.setSharePrice(sharePrice);
                    transaction.setCompanyID(company.getId());

                    // add this transaction to the investor
                    investor.getTransactions().add(transaction);

                    // one share sold of this company;
                    // decrement remaining shares of the company
                    company.incrementSharesSold();

                    if (company.checkIfTenSharesSold()) {
                        aCompanySoldTenShares = true;

                    }

                    // update the capital
                    company.setCompanyCapital(company.getSharePrice() * company.getOriginalShares());

                }

                if (investor.getBudget() < leastLimit) {
                    // cannot buy more!
                    break;
                }

            }

            // there is a chance a company reduced it's shares and now has least share price
            leastLimit = getCompanyWithLeastSharesPrice();

        }

    }

    private double getCompanyWithLeastSharesPrice() {
        // get a company iterator here
        StockExchangeSimulator.CompanyIterator iterator =
                StockExchangeSimulator.getStockExchangeSimulator().getCompanyIterator();

        // the first company
        Company company = iterator.next();

        // share price of the first company
        double minShare = company.getSharePrice();

        // match the share price with other companies share prices and collect the min share price
        while (iterator.hasNext()) {
            // the company
            company = iterator.next();
            // match if this company has more low share price than current min share
            if (minShare > company.getSharePrice()) {
                // update the min share
                minShare = company.getSharePrice();
            }

        }

        return minShare;

    }
}
