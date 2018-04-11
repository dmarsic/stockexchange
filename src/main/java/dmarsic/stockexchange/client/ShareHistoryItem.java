package dmarsic.stockexchange.client;

public class ShareHistoryItem {

    String action;
    Double price;
    Long quantity;

    public ShareHistoryItem(String _action, Double _price, Long _quantity) {
        action = _action;
        price = _price;
        quantity = _quantity;
    }
}
