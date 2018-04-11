package dmarsic.stockexchange.listing;

import org.apache.log4j.Logger;

public class Company {

    Logger logger = Logger.getLogger(Company.class);

    private String name;
    private Long shareCount;
    private Double currentValue;
    private Double companyValue = 0.0;

    public Company(String _name, Long _shareCount, Double _value) {
        name = _name;
        shareCount = _shareCount;
        currentValue = _value;
    }

    public Long getShareCount() {
        return shareCount;
    }

    public Double getCurrentValue() {
        return currentValue;
    }

    public void sellShares(Long quantity) {
        shareCount -= quantity;
        companyValue += quantity * currentValue;
        logger.debug(String.format("%s sold %d shares at %.2f, current company value: %.2f, available shares: %d",
                name, quantity, currentValue, companyValue, shareCount));
    }
}
