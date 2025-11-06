package enterprises.iwakura.kirara.amiami.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The response for a single item from AmiAmi.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AmiAmiItemResponse extends AmiAmiResponse {

    @SerializedName("item")
    private Item item;

    @SerializedName("_embedded")
    private Embedded embedded;

    @Data
    public static class Item {

        @SerializedName("gcode")
        private String gCode;
        @SerializedName("scode")
        private String sCode;
        @SerializedName("gname")
        private String gName;
        @SerializedName("sname")
        private String sName;
        @SerializedName("gname_sub")
        private String gNameSub;
        @SerializedName("sname_simple")
        private String sNameSimple;
        @SerializedName("sname_simple_j")
        private String sNameSimpleJapanese;
        @SerializedName("main_image_url")
        private String mainImageUrl;
        @SerializedName("main_image_alt")
        private String mainImageAlt;
        @SerializedName("main_image_title")
        private String mainImageTitle;
        @SerializedName("image_comment")
        private String imageComment;
        @SerializedName("youtube")
        private String youtube;

        /**
         * This is the price after discount plus the points.
         */
        @SerializedName("list_price")
        private int listPriceJpy;

        /**
         * Taxed price. If the item is on sale, this will be the price before discount.
         */
        @SerializedName("c_price_taxed")
        private int priceTaxedJpy;
        @SerializedName("price")
        private int priceJpy;

        /**
         * AmiAmi points that the user will earn with this purchase.
         */
        @SerializedName("point")
        private int points;

        /**
         * The sale status, e.g., "Pre-order", "Released" etc.
         */
        @SerializedName("salestatus")
        private String saleStatus;
        @SerializedName("releasedate")
        private String releaseDate;
        @SerializedName("period_from")
        private String periodFrom;
        @SerializedName("period_to")
        private String periodTo;
        @SerializedName("cart_type")
        private int cartType;
        @SerializedName("max_cartin_count")
        private int maxCartInCount;
        @SerializedName("include_instock_only_flg")
        private int includeInStockOnlyFlag;
        @SerializedName("remarks")
        private String remarks;
        @SerializedName("size_info")
        private String sizeInfo;
        @SerializedName("watch_list_available")
        private int watchListAvailable;
        @SerializedName("jancode")
        private String janCode;
        @SerializedName("maker_name")
        private String makerName;
        @SerializedName("modeler")
        private String modeler;
        @SerializedName("modelergroup")
        private String modelerGroup;
        @SerializedName("spec")
        private String spec;
        @SerializedName("memo")
        private String memo;
        @SerializedName("copyright")
        private String copyright;
        @SerializedName("saleitem")
        private int saleItem;
        @SerializedName("condition_flg")
        private int conditionFlag;
        @SerializedName("preorderitem")
        private int preOrderItem;
        @SerializedName("backorderitem")
        private int backOrderItem;
        @SerializedName("store_bonus")
        private int storeBonus;
        @SerializedName("amiami_limited")
        private int amiamiLimited;
        @SerializedName("instock_flg")
        private int inStockFlag;
        @SerializedName("order_closed_flg")
        private int orderClosedFlag;
        @SerializedName("preown_attention")
        private int preownAttention;
        @SerializedName("producttypeattention")
        private int productTypeAttention;
        @SerializedName("agelimit")
        private int ageLimit;
        @SerializedName("customs_warning_flg")
        private int customsWarningFlag;
        @SerializedName("preorderattention")
        private String preorderAttention;
        @SerializedName("preorder_bonus_flg")
        private int preorderBonusFlag;
        @SerializedName("domesticitem")
        private int domesticItem;
        @SerializedName("metadescription")
        private String metaDescription;
        @SerializedName("metawords")
        private String metaWords;
        @SerializedName("releasechange_text")
        private String releaseChangeText;
        @SerializedName("cate1")
        private List<Integer> category1;
        @SerializedName("cate2")
        private List<Integer> category2;
        @SerializedName("cate3")
        private List<Integer> category3;
        @SerializedName("cate4")
        private List<Integer> category4;
        @SerializedName("cate5")
        private List<Integer> category5;
        @SerializedName("cate6")
        private String category6;
        @SerializedName("cate7")
        private String category7;
        @SerializedName("salestalk")
        private String saleStalk;
        @SerializedName("buy_flg")
        private int buyFlag;
        @SerializedName("buy_price")
        private int buyPrice;
        @SerializedName("buy_remarks")
        private String buyRemarks;
        @SerializedName("end_flg")
        private int endFlag;
        @SerializedName("disp_flg")
        private int displayFlag;
        @SerializedName("onsale_flg")
        private int onSaleFlag;
        @SerializedName("handling_store")
        private String handlingStore;
        @SerializedName("salestatus_detail")
        private String saleStatusDetail;
        @SerializedName("stock")
        private int stock;
        @SerializedName("newitem")
        private int newItem;
        @SerializedName("saletopitem")
        private int saleTopItem;
        @SerializedName("resale_flg")
        private int resaleFlag;
        @SerializedName("preowned_sale_flg")
        private int preownedSaleFlag;
        @SerializedName("big_title_flg")
        private int bigTitleFlag;
        @SerializedName("soldout_flg")
        private int soldOutFlag;
        @SerializedName("inc_txt1")
        private int incTxt1;
        @SerializedName("inc_txt2")
        private int incTxt2;
        @SerializedName("inc_txt3")
        private int incTxt3;
        @SerializedName("inc_txt4")
        private int incTxt4;
        @SerializedName("inc_txt5")
        private int incTxt5;
        @SerializedName("inc_txt6")
        private int incTxt6;
        @SerializedName("inc_txt7")
        private int incTxt7;
        @SerializedName("inc_txt8")
        private int incTxt8;
        @SerializedName("inc_txt9")
        private int incTxt9;
        @SerializedName("inc_txt10")
        private int incTxt10;
        @SerializedName("image_on")
        private int imageOn;
        @SerializedName("image_category")
        private String imageCategory;
        @SerializedName("image_name")
        private String imageName;
        @SerializedName("metaalt")
        private String metaAlt;
        @SerializedName("image_reviewnumber")
        private int imageReviewNumber;
        @SerializedName("image_reviewcategory")
        private String imageReviewCategory;
        @SerializedName("price1")
        private int price1;
        @SerializedName("price2")
        private int price2;
        @SerializedName("price3")
        private int price3;
        @SerializedName("price4")
        private int price4;
        @SerializedName("price5")
        private int price5;
        @SerializedName("discountrate1")
        private int discountRate1;
        @SerializedName("discountrate2")
        private int discountRate2;
        @SerializedName("discountrate3")
        private int discountRate3;
        @SerializedName("discountrate4")
        private int discountRate4;
        @SerializedName("discountrate5")
        private int discountRate5;
        @SerializedName("sizew")
        private String sizeW;
        @SerializedName("colorw")
        private String colorW;
        @SerializedName("thumb_url")
        private String thumbnailUrl;
        @SerializedName("thumb_alt")
        private String thumbnailAlt;
        @SerializedName("thumb_title")
        private String thumbnailTitle;
        @SerializedName("thumb_agelimit")
        private int thumbnailAgeLimit;

        /**
         * Gets the preferred name of the item, prioritizing the gName if available.
         *
         * @return the preferred name of the item
         */
        public String getName() {
            if (gName != null) {
                return gName;
            } else {
                return sName;
            }
        }
    }

    @Data
    public static class Embedded {

        @SerializedName("review_images")
        private List<Image> reviewImages;
        @SerializedName("bonus_images")
        private List<Image> bonusImages;
        @SerializedName("related_items")
        private List<Item> relatedItems;
        @SerializedName("other_items")
        private List<Item> otherItems;
        @SerializedName("makers")
        private List<Tag> makers;
        @SerializedName("series_titles")
        private List<Tag> seriesTitles;
        @SerializedName("original_titles")
        private List<Tag> originalTitles;
        @SerializedName("character_names")
        private List<Tag> characterNames;

        @Data
        public static class Image {

            @SerializedName("image_url")
            private String imageUrl;
            @SerializedName("thumb_url")
            private String thumbnailUrl;
            @SerializedName("alt")
            private String alt;
            @SerializedName("title")
            private String title;
        }

        @Data
        public static class Item {

            @SerializedName("gcode")
            private String gCode;
            @SerializedName("gname")
            private String gName;
            @SerializedName("thumb_url")
            private String thumbnailUrl;
            @SerializedName("thumb_alt")
            private String thumbnailAlt;
            @SerializedName("thumb_title")
            private String thumbnailTitle;
            @SerializedName("thumb_agelimit")
            private int thumbnailAgeLimit;
        }

        @Data
        public static class Tag {

            private long id;
            private String name;
        }
    }
}
