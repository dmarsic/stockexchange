package dmarsic.stockexchange.auctionmarket;

import dmarsic.stockexchange.client.Client;
import dmarsic.stockexchange.client.Portfolio;
import dmarsic.stockexchange.listing.Company;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import org.apache.log4j.Logger;


public class Market {

    private static final String COMPANY_LISTING_FILE = "/company_listing.txt";
    private static final String CLIENT_FILE = "/clients.txt";

    Logger logger = Logger.getLogger(Market.class);

    private HashMap<String, Company> listedCompanies = new HashMap<>();
    private HashMap<String, Client> clients = new HashMap<>();

    public Market() {
        loadListedCompanies();
        loadClients();
    }

    public void listCompanies() {
        Iterator it = listedCompanies.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            String name = (String)pair.getKey();
            Company company = (Company)pair.getValue();
            System.out.println(String.format("%s: value: %.2f available: %d",
                    name, company.getCurrentValue(), company.getShareCount()));
        }
    }

    public boolean buy(String shareName, String clientName, Long quantity, Double maxPrice) {
        // check if share's price is affordable
        // check availability of the share
        // check client funds
        // add shares to client's portfolio
        // reduce shares from company's listing
        Company company = listedCompanies.get(shareName);
        Client client = clients.get(clientName);
        if (company.getCurrentValue() > maxPrice) {
            logger.debug(String.format("%s's value is %.2f, higher than client %s's max price: %.2f",
                    shareName, company.getCurrentValue(), clientName, maxPrice));
            return false;
        }

        if (company.getShareCount() < quantity) {
            logger.debug(String.format("%s has %d available shares, less than client %s requested: %d",
                    shareName, company.getShareCount(), clientName, quantity));
            return false;
        }

        if (client.getFunds() < quantity * company.getCurrentValue()) {
            logger.debug(String.format("Client %s has insufficient funds: %.2f to buy %d %s shares at %.2f",
                    clientName, client.getFunds(), quantity, shareName, company.getCurrentValue()));
            return false;
        }

        client.buyShares(shareName, quantity, company.getCurrentValue());
        company.sellShares(quantity);

        return true;
    }

    private void loadListedCompanies() {

        InputStream stream = getClass().getResourceAsStream(COMPANY_LISTING_FILE);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                String name = tokens[0];
                Long shareCount = Long.valueOf(tokens[1]);
                Double currentValue = Double.valueOf(tokens[2]);
                listedCompanies.put(name, new Company(name, shareCount, currentValue));
            }
        } catch (java.io.IOException e) {
            System.out.println("Look Ma, I'm handling the exception!");
        }
    }

    private void loadClients() {
        InputStream stream = getClass().getResourceAsStream(CLIENT_FILE);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                String name = tokens[0];
                Double funds = Double.valueOf(tokens[1]);
                Portfolio portfolio;
                if (tokens.length == 2) {
                    portfolio = new Portfolio();
                } else {
                    portfolio = new Portfolio(parsePortfolio(tokens[2]));
                }
                clients.put(name, new Client(name, funds, portfolio));
            }
        } catch (java.io.IOException e) {
            System.out.println("Look Ma, I'm handling the exception!");
        }
    }

    private HashMap<String, Long> parsePortfolio(String encodedPortfolio) {
        HashMap<String, Long> portfolioMap = new HashMap<>();
        String[] pairs = encodedPortfolio.split("&");
        for (int i = 0; i < pairs.length; i++) {
            String[] share = pairs[i].split(":");
            portfolioMap.put(share[0], Long.valueOf(share[1]));
        }
        return portfolioMap;
    }
}
