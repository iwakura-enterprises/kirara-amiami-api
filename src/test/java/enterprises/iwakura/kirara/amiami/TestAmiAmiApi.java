package enterprises.iwakura.kirara.amiami;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import enterprises.iwakura.kirara.amiami.request.AmiAmiItemDetailsRequest;
import enterprises.iwakura.kirara.amiami.request.AmiAmiSearchRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Disabled
public class TestAmiAmiApi {

    @Test
    public void testSearch() {
        var api = new AmiAmiApi();

        var searchResult = api.search(AmiAmiSearchRequest.builder()
                .searchKeywords("reimu")
                .build())
            .send()
            .join();

        log.info("Search Result: {}", searchResult);
        int x = 0;
    }

    @SneakyThrows
    @Test
    public void testItemDetails() {
        var api = new AmiAmiApi();

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
        var api = new AmiAmiApi();

        var currencyLayerResult = api.getCurrencyLayer()
            .send()
            .join();

        log.info("Currency Layer Result: {}", currencyLayerResult);
        int x = 0;
    }
}
