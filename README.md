# Stock Exchange Simulator
The chosen patterns:

1. Bridge Pattern - Structural Pattern:
The Bridge pattern is used to separate a class's interface from its implementation. 
In this project it is used as:

- Results as interface
- ResultsImplementation as class

These contain the end results of the stock exchange simulator. The user uses the Result
interface to view the company with highest share price, company with lowest share price,
investor with highest number of shares bought and investors with lowest number of shares
bought. Using bridge pattern, we have seperated the interface from implementation so that later we can make the changes in the implementation easily. If we feel that the implementation is going to be changed, we just have to make changes in ResultsImplementation and there are no issues in other places. 

2. Iterator Pattern - Behavioral Pattern:
The Iterator pattern is used to access the elements of a collection in sequence without knowing its underlying representation. 
In this project it is used in:

- StockExchangeSimulator class

This class contains an iterator of companies it contains. Other classes like InvestmentThread class uses this iterator for iterating over the companies. In each InvestmentThread, an investor iterates over all the companies one by one and check if their budget can manage this company's shares and then they buy the share or move on.
 
3. Singleton Pattern - Creational Pattern
The Singleton Pattern ensures that there exists only one object of a class in entire project. In my project it is used in:

- StockExchangeSimulator class

Since there is going to only one Stock Exchange in the project with 100 investors and 100 companies, I made sure that there exists only one object of Stock Exchange.
