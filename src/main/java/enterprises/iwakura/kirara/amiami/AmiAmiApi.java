package enterprises.iwakura.kirara.amiami;

import java.net.http.HttpClient;
import java.util.List;

import com.google.gson.Gson;

import enterprises.iwakura.kirara.amiami.request.AmiAmiItemDetailsRequest;
import enterprises.iwakura.kirara.amiami.request.AmiAmiSearchRequest;
import enterprises.iwakura.kirara.amiami.response.AmiAmiCurrencyLayerResponse;
import enterprises.iwakura.kirara.amiami.response.AmiAmiItemResponse;
import enterprises.iwakura.kirara.amiami.response.AmiAmiSearchResponse;
import enterprises.iwakura.kirara.core.ApiRequest;
import enterprises.iwakura.kirara.core.Kirara;
import enterprises.iwakura.kirara.core.RequestHeader;
import enterprises.iwakura.kirara.core.impl.ByteSerializer;
import enterprises.iwakura.kirara.gson.GsonSerializer;
import enterprises.iwakura.kirara.httpclient.HttpClientHttpCore;

/**
 * AmiAmi API client for accessing AmiAmi's public API endpoints.
 */
public class AmiAmiApi extends Kirara {

    protected static final Gson GSON = new Gson();
    protected static final String DEFAULT_API_URL = "https://api.amiami.com/api/v1.0";
    protected static final ByteSerializer BYTE_SERIALIZER = new ByteSerializer();
    protected static final List<String> SUPPORTED_CONTENT_TYPES = List.of(
        "application/json",
        "text/json",
        "text/html" // AmiAmi returns answers with text/html...
    );

    protected static final List<RequestHeader> DEFAULT_REQUEST_HEADERS = List.of(
        new RequestHeader("Accept", "*/*"),
        new RequestHeader("x-user-key", "amiami_dev")
    );

    /**
     * Creates a new instance of the AmiAmiApi with default settings.
     */
    public AmiAmiApi() {
        this(DEFAULT_API_URL, HttpClient.newHttpClient());
    }

    /**
     * Creates a new instance of the AmiAmiApi with the specified API URL and http client (for proxies etc.)
     *
     * @param apiUrl     The base URL of the AmiAmi API.
     * @param httpClient The HttpClient to use for making requests.
     */
    public AmiAmiApi(String apiUrl, HttpClient httpClient) {
        super(new HttpClientHttpCore(httpClient), new GsonSerializer(GSON, SUPPORTED_CONTENT_TYPES), apiUrl);
        setDefaultRequestHeaders(DEFAULT_REQUEST_HEADERS);
    }

    /**
     * Calls the search endpoint with the specified search request.
     *
     * @param searchRequest The search request parameters.
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
     * @return The API request for the item details operation.
     */
    public ApiRequest<AmiAmiItemResponse> getItemDetails(AmiAmiItemDetailsRequest itemDetailsRequest) {
        return this.createRequest("GET", "/item", AmiAmiItemResponse.class)
            .withExplicitRequestQueries(itemDetailsRequest.toRequestQueries());
    }

    /**
     * Calls the currency layer endpoint to get currency exchange rates. This overrides the base URL to point to <a
     * href="https://www.amiami.com">https://www.amiami.com</a>.
     *
     * @return The API request for the currency layer operation.
     */
    public ApiRequest<AmiAmiCurrencyLayerResponse> getCurrencyLayer() {
        return this.createRequest("GET", "/files/currencylayer.json", AmiAmiCurrencyLayerResponse.class)
            .withUrl("https://www.amiami.com");
    }

    /**
     * Retrieves an image from the specified image path. This overrides the base URL to point to <a
     * href="https://img.amiami.com">https://img.amiami.com</a>.
     *
     * @param imagePath The path of the image to retrieve.
     * @return The API request for the image retrieval operation.
     */
    public ApiRequest<byte[]> getImage(String imagePath) {
        return this.createRequest("GET", imagePath, byte[].class)
            .withSerializerOverride(BYTE_SERIALIZER)
            .withUrl("https://img.amiami.com");
    }
}
