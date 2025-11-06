package enterprises.iwakura.kirara.amiami.response;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;

import lombok.Data;

@Data
public class AmiAmiCurrencyLayerResponse {

    private boolean success;
    private String terms;
    private String privacy;
    private long timestamp;
    private String source;
    private Map<Quote, Double> quotes;

    /**
     * Converts the timestamp to an OffsetDateTime in UTC.
     *
     * @return OffsetDateTime representation of the timestamp in UTC.
     */
    public OffsetDateTime getTimestampAsDateTimeUtc() {
        return Instant.ofEpochSecond(timestamp).atOffset(ZoneOffset.UTC);
    }

    /**
     * Converts an amount in JPY to the specified target currency.
     *
     * @param amount     The amount in JPY to convert.
     * @param toCurrency The target currency to convert to.
     * @return The converted amount in the target currency.
     * @throws IllegalArgumentException if the target currency is unsupported.
     */
    public Double convertJpy(double amount, Currency toCurrency) {
        var usd = amount / quotes.get(Quote.USDJPY);
        Double quote;
        switch (toCurrency) {
            case USD:
                quote = 1.0;
                break;
            case CAD:
                quote = quotes.get(Quote.USDCAD);
                break;
            case CNY:
                quote = quotes.get(Quote.USDCNY);
                break;
            case EUR:
                quote = quotes.get(Quote.USDEUR);
                break;
            case GBP:
                quote = quotes.get(Quote.USDGBP);
                break;
            case HKD:
                quote = quotes.get(Quote.USDHKD);
                break;
            case KRW:
                quote = quotes.get(Quote.USDKRW);
                break;
            default:
                throw new IllegalArgumentException("Unsupported currency: " + toCurrency);
        }
        ;
        return usd * quote;
    }

    public enum Currency {
        JPY,
        USD,
        CAD,
        CNY,
        EUR,
        GBP,
        HKD,
        KRW
    }

    public enum Quote {
        USDJPY,
        USDCAD,
        USDCNY,
        USDEUR,
        USDGBP,
        USDHKD,
        USDKRW
    }
}