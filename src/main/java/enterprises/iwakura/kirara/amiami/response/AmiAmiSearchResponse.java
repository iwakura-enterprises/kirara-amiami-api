package enterprises.iwakura.kirara.amiami.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The response for searching items on AmiAmi.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AmiAmiSearchResponse extends AmiAmiResponse {

    /**
     * Search results
     */
    @SerializedName("search_result")
    private SearchResults searchResults;

    /**
     * List of result items
     */
    private List<ResultItem> items;

    /**
     * Embedded data, such as category tags.
     */
    @SerializedName("_embedded")
    private Embedded embedded;

    /**
     * Holds search results, such as total results count.
     */
    @Data
    public static class SearchResults {

        @SerializedName("total_results")
        private long totalResults;
    }

    /**
     * Search result item. Holds some basic information about an item.
     */
    @Data
    public static class ResultItem {

        /**
         * Item code, used for identification across the site.
         */
        @SerializedName("gcode")
        private String gCode;

        /**
         * Item name in english.
         */
        @SerializedName("gname")
        private String gName;

        /**
         * Thumbnail URL of the item. Used in {@link enterprises.iwakura.kirara.amiami.AmiAmiImagesApi}
         */
        @SerializedName("thumb_url")
        private String thumbnailUrl;

        /**
         * Alternative text for the thumbnail image. Usually just the gcode appended with jpg.
         */
        @SerializedName("thumb_alt")
        private String thumbnailAlt;

        /**
         * Title attribute for the thumbnail image.
         */
        @SerializedName("thumb_title")
        private String thumbnailTitle;

        /**
         * Minimum price in JPY.
         */
        @SerializedName("min_price")
        private long minimumPriceJpy;

        /**
         * Maximum price in JPY.
         */
        @SerializedName("max_price")
        private long maximumPriceJpy;

        /**
         * Price including tax in JPY. Usually the same as other prices though.
         */
        @SerializedName("c_price_taxed")
        private long priceTaxedJpy;

        /**
         * Maker name. SQUARE ENIX, etc.
         */
        @SerializedName("maker_name")
        private String makerName;

        @SerializedName("saleitem")
        private int saleItem;

        @SerializedName("condition_flg")
        private int listPreOwnedAvailable;

        @SerializedName("list_preorder_available")
        private int listPreorderAvailable;

        @SerializedName("list_backorder_available")
        private int listBackorderAvailable;

        @SerializedName("list_store_bonus")
        private int listStoreBonus;

        @SerializedName("list_amiami_limited")
        private int listAmiAmiLimited;

        @SerializedName("instock_flg")
        private int inStockFlag;

        @SerializedName("order_closed_flg")
        private int orderClosedFlag;

        @SerializedName("element_id")
        private String elementId;

        /**
         * Sale status, such as "Sold Out", "Pre-order", etc.
         */
        @SerializedName("salestatus")
        private String saleStatus;

        @SerializedName("salestatus_detail")
        private String saleStatusDetail;

        @SerializedName("releasedate")
        private String releaseDate;

        /**
         * JAN code
         */
        @SerializedName("jancode")
        private String janCode;

        @SerializedName("preorderitem")
        private int preOrderItem;

        @SerializedName("saletopitem")
        private int saleTopItem;

        @SerializedName("resale_flg")
        private int resaleFlag;

        /**
         * Not sure what this is. If item is on Pre-Owned, this is still 0. Check {@link #listPreOwnedAvailable} for that.
         */
        @SerializedName("preowned_sale_flg")
        private int preownedSaleFlag;

        @SerializedName("for_women_flg")
        private int forWomenFlag;

        @SerializedName("genre_moe")
        private int genreMoe;

        @SerializedName("cate6")
        private String category6;

        @SerializedName("cate7")
        private String category7;

        @SerializedName("buy_flg")
        private int buyFlag;

        /**
         * Some buy price in JPY. If you're looking for price, check also {@link #minimumPriceJpy} and
         * {@link #maximumPriceJpy}
         */
        @SerializedName("buy_price")
        private int buyPriceJpy;

        @SerializedName("buy_remarks")
        private String buyRemarks;

        @SerializedName("stock_flg")
        private int stockFlag;

        @SerializedName("image_on")
        private int imageOn;

        @SerializedName("image_category")
        private String imageCategory;

        /**
         * Image name, usually just the {@link #gCode}
         */
        @SerializedName("image_name")
        private String imageName;

        @SerializedName("metaalt")
        private String metaAlt;
    }

    /**
     * Embedded data. On the website. Basically the data on the left side, when you search for something.
     */
    @Data
    public static class Embedded {

        @SerializedName("category_tags")
        private List<Tag> categoryTags;

        @SerializedName("makers")
        private List<Tag> makerTags;

        @SerializedName("series_titles")
        private List<Tag> seriesTitleTags;

        @SerializedName("original_titles")
        private List<Tag> originalTitleTags;

        @SerializedName("character_names")
        private List<Tag> characterTags;

        /**
         * Holds a category id, name and number of items in that category that fulfills the search.
         */
        @Data
        public static class Tag {

            private long id;
            private String name;
            private long count;
        }
    }
}
