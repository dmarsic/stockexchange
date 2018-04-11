package dmarsic.stockexchange;

import dmarsic.stockexchange.client.Client;

public class Run {

    public static void main(String[] args) {
        StockExchange se = new StockExchange();

       /* Client c_frodo = new Client("Frodo", 30000.0);

        Client c_sam = new Client("Sam", 30000.0);
        Client c_merry = new Client("Merry", 30000.0);
        Client c_pippin = new Client("Pippin", 30000.0);
        */

        se.requestBuy("GOOG", "Frodo", 12L, 1020.0);
        se.requestBuy("GOOG", "Frodo", 8000L, 1050.0);
        se.requestBuy("ETSY", "Frodo", 310L, 100.0);
        se.requestBuy("ETSY", "Frodo", 31L, 100.0);
        se.requestBuy("ETSY", "Frodo", 9L, 100.0);

    }
}
