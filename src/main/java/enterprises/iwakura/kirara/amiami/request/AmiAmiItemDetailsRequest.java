package enterprises.iwakura.kirara.amiami.request;

import java.util.HashSet;
import java.util.Set;

import enterprises.iwakura.kirara.core.RequestQuery;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AmiAmiItemDetailsRequest {

    private String gCode;

    private String sCode;

    /**
     * Language code (default: "eng"), required.
     */
    @Builder.Default
    private String language = "eng";

    /**
     * Converts the request parameters to a set of request queries.
     *
     * @return Set of RequestQuery objects representing the request parameters.
     * @throws IllegalStateException if neither or both gCode and sCode are provided.
     */
    public Set<RequestQuery> toRequestQueries() {
        if (gCode == null && sCode == null) {
            throw new IllegalStateException("Either gCode or sCode must be provided");
        }

        if (gCode != null && sCode != null) {
            throw new IllegalStateException("Only one of gCode or sCode can be provided");
        }

        var queries = new HashSet<RequestQuery>();

        if (gCode != null) {
            queries.add(new RequestQuery("gcode", gCode));
        }

        if (sCode != null) {
            queries.add(new RequestQuery("scode", sCode));
        }

        return queries;
    }
}
