package dmarsic.stockexchange.client;

import java.util.Date;
import java.util.HashMap;

public class Share {

    private String ACTION_BUY = "b";
    private String ACTION_SELL = "s";

    private String company;
    private Long totalNumber = 0L;
    private HashMap<Date, ShareHistoryItem> history = new HashMap<>();

    public Share(String _company) {
        company = _company;
    }

    public void addShare(Long count, Double acquirePrice) {
        totalNumber += count;
        Date timestamp = new Date();
        history.put(timestamp, new ShareHistoryItem(ACTION_BUY, acquirePrice, count));
    }

    public void removeShare(Long count, Double sellPrice) {
        totalNumber -= count;
        Date timestamp = new Date();
        history.put(timestamp, new ShareHistoryItem(ACTION_SELL, sellPrice, count));
    }

    public Long getTotalNumber() {
        return totalNumber;
    }
}
