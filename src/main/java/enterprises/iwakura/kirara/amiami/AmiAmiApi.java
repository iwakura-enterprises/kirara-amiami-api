package enterprises.iwakura.kirara.amiami;

import java.net.http.HttpClient;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import enterprises.iwakura.kirara.amiami.request.AmiAmiItemDetailsRequest;
import enterprises.iwakura.kirara.amiami.request.AmiAmiSearchRequest;
import enterprises.iwakura.kirara.amiami.response.AmiAmiCurrencyLayerResponse;
import enterprises.iwakura.kirara.amiami.response.AmiAmiItemResponse;
import enterprises.iwakura.kirara.amiami.response.AmiAmiSearchResponse;
import enterprises.iwakura.kirara.core.ApiRequest;
import enterprises.iwakura.kirara.core.HttpCore;
import enterprises.iwakura.kirara.core.Kirara;
import enterprises.iwakura.kirara.core.RequestHeader;
import enterprises.iwakura.kirara.core.impl.ByteSerializer;
import enterprises.iwakura.kirara.gson.GsonSerializer;
import enterprises.iwakura.kirara.httpclient.HttpClientHttpCore;

/**
 * AmiAmi API client for accessing AmiAmi's public API endpoints. Be aware that their API is behind Cloudflare, so
 * expect some limitations when it comes to request rates and such. The client uses HTTP/1.1 to ensure it can bypass
 * Cloudflare's restrictions, but sometimes it might still get blocked.<br>
 * Your requests may be rate limited as well. I recommend using at least 250ms delay between requests to avoid this.
 * When you get rate limited, the client will not retry automatically, so you have to handle that on your side. Retrying
 * too-soon will likely result in being rate limited again, thus I recommend implementing an exponential backoff strategy
 * when retrying.
 */
public class AmiAmiApi extends Kirara {

    public static final String FIREFOX_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:146.0) "
        + "Gecko/20100101 Firefox/146.0";
    public static final String USER_KEY_HEADER = "x-user-key";
    public static final String USER_KEY_VALUE = "amiami_dev";
    public static final List<String> SUPPORTED_CONTENT_TYPES = Arrays.asList(
        "application/json",
        "text/json",
        "text/html" // AmiAmi returns answers with text/html...
    );
    public static final String DEFAULT_API_URL = "https://api.amiami.com/api/v1.0";
    public static final String DEFAULT_IMAGE_API_URL = "https://img.amiami.com";
    public static final String DEFAULT_AMIAMI_URL = "https://www.amiami.com";
    public static final ByteSerializer BYTE_SERIALIZER = new ByteSerializer();
    public static final List<RequestHeader> DEFAULT_REQUEST_HEADERS = Arrays.asList(
        // "I am an average windows firefox user, teehee"
        new RequestHeader("User-Agent", FIREFOX_USER_AGENT),
        new RequestHeader("Accept", "*/*"),
        // AmiAmi requires this header for API access, for ... some reason
        new RequestHeader(USER_KEY_HEADER, USER_KEY_VALUE)
    );
    public static final HttpClient DEFAULT_HTTP_CLIENT = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_1_1) // Cloudflare hates HTTP/2
        .build();

    /**
     * The base URL of the AmiAmi image API.
     */
    protected final String imageApiUrl;

    /**
     * URL of the AmiAmi AmiAmi site.
     */
    protected final String amiAmiUrl;

    /**
     * Creates a new instance of the AmiAmiApi with default settings.
     *
     * @param gson The GSON instance to use for JSON serialization and deserialization.
     */
    public AmiAmiApi(Gson gson) {
        this(
            DEFAULT_API_URL,
            DEFAULT_IMAGE_API_URL,
            DEFAULT_AMIAMI_URL,
            new HttpClientHttpCore(DEFAULT_HTTP_CLIENT),
            new GsonSerializer(gson, SUPPORTED_CONTENT_TYPES)
        );
    }

    /**
     * Creates a new instance of the AmiAmiApi with the specified API URL and http client (for proxies etc.)
     *
     * @param apiUrl         The base URL of the AmiAmi API.
     * @param imageApiUrl    The base URL of the AmiAmi image API.
     * @param amiAmiUrl      The base URL of the AmiAmi site.
     * @param httpCore       The HTTP core to use for making requests. If you are using HttpClient, please ensure it is
     *                       running in HTTP/1.1 mode to avoid Cloudflare blocks.
     * @param gsonSerializer The GSON serializer to use for serializing and deserializing JSON data. Please ensure it
     *                       can handle the AmiAmi API responses correctly, as AmiAmi returns JSON with content type of
     *                       text/html.
     */
    public AmiAmiApi(
        String apiUrl,
        String imageApiUrl,
        String amiAmiUrl,
        HttpCore httpCore,
        GsonSerializer gsonSerializer
    ) {
        super(httpCore, gsonSerializer, apiUrl);
        this.imageApiUrl = imageApiUrl;
        this.amiAmiUrl = amiAmiUrl;
    }

    @Override
    public List<RequestHeader> getDefaultRequestHeaders() {
        return DEFAULT_REQUEST_HEADERS;
    }

    /**
     * Calls the search endpoint with the specified search request.
     *
     * @param searchRequest The search request parameters.
     *
     * @return The API request for the search operation.
     */
    public ApiRequest<AmiAmiSearchResponse> search(AmiAmiSearchRequest searchRequest) {
        return this.createRequest("GET", "/items", AmiAmiSearchResponse.class)
            .withExplicitRequestQueries(searchRequest.toRequestQueries());
    }

    /**
     * Calls the item details endpoint with the specified item details request.
     *
     * @param itemDetailsRequest The item details request parameters.
     *
     * @return The API request for the item details operation.
     */
    public ApiRequest<AmiAmiItemResponse> getItemDetails(AmiAmiItemDetailsRequest itemDetailsRequest) {
        return this.createRequest("GET", "/item", AmiAmiItemResponse.class)
            .withExplicitRequestQueries(itemDetailsRequest.toRequestQueries());
    }

    /**
     * Calls the currency layer endpoint to get currency exchange rates. This overrides the base URL to point to
     * {@link #amiAmiUrl}
     *
     * @return The API request for the currency layer operation.
     */
    public ApiRequest<AmiAmiCurrencyLayerResponse> getCurrencyLayer() {
        return this.createRequest("GET", "/files/currencylayer.json", AmiAmiCurrencyLayerResponse.class)
            .withUrl(amiAmiUrl);
    }

    /**
     * Retrieves an image from the specified image path. This overrides the base URL to point to {@link #imageApiUrl}
     *
     * @param imagePath The path of the image to retrieve.
     *
     * @return The API request for the image retrieval operation.
     */
    public ApiRequest<byte[]> getImage(String imagePath) {
        return this.createRequest("GET", imagePath, byte[].class)
            .withSerializerOverride(BYTE_SERIALIZER)
            .withUrl(imageApiUrl);
    }
}
