package dmarsic.stockexchange.client;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Iterator;

public class Portfolio {

    Logger logger = Logger.getLogger(Portfolio.class);

    private HashMap<String, Share> portfolio = new HashMap<>();

    public Portfolio() {}

    public Portfolio(HashMap<String, Long> shares) {
        Double ACQUIRE_PRICE = 0.0;
        Iterator it = shares.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            logger.debug(String.format("Loading client's portfolio: %s : %d",
                    pair.getKey(), pair.getValue()));
            Share share = new Share((String)pair.getKey());
            share.addShare((Long)pair.getValue(), ACQUIRE_PRICE);
        }
    }

    public void addShares(String shareName, Long quantity, Double acquirePrice) {
        Share share;
        if (!portfolio.containsKey(shareName)) {
            share = new Share(shareName);
        } else {
            share = portfolio.get(shareName);
        }
        share.addShare(quantity, acquirePrice);
        portfolio.put(shareName, share);
        logger.debug(String.format("Added share %s (%d) to client's portfolio, total: %d",
                shareName, quantity, share.getTotalNumber()));

    }
}
