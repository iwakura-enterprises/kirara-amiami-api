package enterprises.iwakura.kirara.amiami.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import enterprises.iwakura.kirara.amiami.AmiAmiApi;
import enterprises.iwakura.kirara.core.KiraraResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Base AmiAmi API response. Holds common response fields.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class AmiAmiResponse extends KiraraResponse<AmiAmiApi> {

    /**
     * RSuccess indicating whether the request was successful ("true") or not ("false"). Other values were not
     * observed.
     */
    @SerializedName("RSuccess")
    private String responseSuccess;

    /**
     * RValue holding some response data. Currently holds list of errors. Null if no errors.
     */
    @SerializedName("RValue")
    private RValue responseValue;

    /**
     * Holds an error message, if {@link #responseSuccess} is "false". Otherwise, will be usually "OK"
     */
    @SerializedName("RMessage")
    private String responseMessage;

    /**
     * Check if the response indicates a successful operation.
     *
     * @return true if successful, false otherwise
     */
    public boolean isSuccessful() {
        return "true".equals(responseSuccess);
    }

    /**
     * Something called "RValue". Currently, holds only list of {@link ErrorDTO}.
     */
    @Data
    public static class RValue {

        @SerializedName("Error")
        private List<ErrorDTO> errors;

        /**
         * Error details DTO for AmiAmi API responses.
         */
        @Data
        public static class ErrorDTO {

            /**
             * Name of the required parameter
             */
            @SerializedName("RequiredParameter")
            private String requiredParameter;

            /**
             * Error message
             */
            @SerializedName("ErrorMessage")
            private String errorMessage;

            /**
             * Error code
             */
            @SerializedName("ErrorCode")
            private String errorCode;

            /**
             * Double-nested list of fields, for some reason.
             */
            @SerializedName("Fields")
            private List<String> fields;
        }
    }
}
