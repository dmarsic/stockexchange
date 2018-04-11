package dmarsic.stockexchange;

import dmarsic.stockexchange.auctionmarket.Market;
import org.apache.log4j.Logger;

public class StockExchange {

    Logger logger = Logger.getLogger(StockExchange.class);
    private boolean opened = false;
    private Market market = new Market();

    public StockExchange() {
        logger.debug("Initialize StockExchange");
        market.listCompanies();
    }

    public void open() {
        logger.info("StockExchange open.");
        opened = true;
    }

    public void close() {
        logger.info("StockExchange closed.");
        opened = false;
    }

    public boolean isOpen() {
        return opened;
    }

    public void requestBuy(String shareName, String clientName, Long quantity, Double maxPrice) {
        market.buy(shareName, clientName, quantity, maxPrice);

    }
}
