package enterprises.iwakura.kirara.amiami;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import enterprises.iwakura.kirara.amiami.request.AmiAmiItemDetailsRequest;
import enterprises.iwakura.kirara.amiami.request.AmiAmiSearchRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Disabled
public class TestAmiAmiApi {

    @Test
    public void testSearch() {
        var api = new AmiAmiApi(new Gson());

        int timesRateLimited = 0;

        while (true) {
            for (int page = 1; page <= 5; page++) {
                try {
                    var searchResult = api.search(AmiAmiSearchRequest.builder()
                            .searchKeywords("fumo")
                            .pageNumber(page)
                            .build())
                        .send()
                        .join();

                    log.info("Search Result: {}", searchResult);
                    System.out.println("rate limited: " + searchResult.isRateLimited() + ": " + searchResult);
                    Thread.sleep(250);
                    if (!searchResult.isSuccessful()) {
                        System.out.println("Sleeping for " + 10000 + timesRateLimited * 5000L);
                        Thread.sleep(10000 + timesRateLimited * 5000L);
                        timesRateLimited++;
                    } else {
                        timesRateLimited = 0;
                    }
                } catch (Exception exception) {
                    System.err.println("Failed to fetch page");
                }
            }
        }
    }

    @SneakyThrows
    @Test
    public void testItemDetails() {
        var api = new AmiAmiApi(new Gson());

        var itemDetailsResult = api.getItemDetails(
                AmiAmiItemDetailsRequest.builder()
                    .gCode("GOODS-04700694")
                    .build())
            .send()
            .join();

        log.info("Item Details Result: {}", itemDetailsResult);

        var image = api.getImage(itemDetailsResult.getItem().getMainImageUrl()).send().join();

        log.info("Image size: {} bytes", image.length);

        try {
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(image));
            ImageIcon icon = new ImageIcon(img);
            JFrame frame = new JFrame("AmiAmi Image");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(new JLabel(icon));
            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
            log.error("Failed to display image", e);
        }

        Thread.sleep(1000000);
        int x = 0;
    }

    @Test
    public void testCurrencyLayer() {
        var api = new AmiAmiApi(new Gson());

        var currencyLayerResult = api.getCurrencyLayer()
            .send()
            .join();

        log.info("Currency Layer Result: {}", currencyLayerResult);
        int x = 0;
    }
}
