package dmarsic.stockexchange.client;

import org.apache.log4j.Logger;

public class Client {

    Logger logger = Logger.getLogger(Client.class);

    private String name;
    private Double funds;
    private Portfolio portfolio;

    public Client(String _name, Double _initialFunds, Portfolio _portfolio) {
        name = _name;
        funds = _initialFunds;
        portfolio = _portfolio;
    }

    public Double getFunds() {
        return funds;
    }

    public void buyShares(String shareName, Long quantity, Double acquirePrice) {
        portfolio.addShares(shareName, quantity, acquirePrice);
        funds -= quantity * acquirePrice;
        logger.debug(String.format("Client %s bought %d %s at %.2f, current funds: %.2f",
                name, quantity, shareName, acquirePrice, funds));

    }
}
