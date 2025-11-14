package enterprises.iwakura.kirara.amiami.request;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import enterprises.iwakura.kirara.core.RequestQuery;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AmiAmiSearchRequest {

    /**
     * Maximum items per page (default: 20, max: 50), required.
     */
    @Builder.Default
    private long maximumItemsPerPage = 20L;

    /**
     * Language code (default: "eng"), required.
     */
    @Builder.Default
    private String language = "eng";

    /**
     * Search keyword, optional.
     */
    private String searchKeywords;

    /**
     * Filters any availability, optional.
     */
    private Boolean filterAnyAvailability;

    /**
     * Filters pre-order items, optional.
     */
    private Boolean filterPreOrder;

    /**
     * Filters back-order items, optional.
     */
    private Boolean filterBackOrder;

    /**
     * Filters new items, optional.
     */
    private Boolean filterNewItems;

    /**
     * Filters pre-owned items, optional.
     */
    private Boolean filterPreOwnedItems;

    /**
     * Filters w/AmiAmi bonus items, optional.
     */
    private Boolean filterAmiAmiBonus;

    /**
     * Filters items on sale, optional.
     */
    private Boolean filterOnSaleItems;

    /**
     * Filters by category (1st level), optional.
     */
    private Integer category1Id;

    /**
     * Filters by category (2nd level), optional.
     */
    private Integer category2Id;

    /**
     * Filters by category (3rd level), optional.
     */
    private Integer category3Id;

    /**
     * Filters by category (4th level), optional.
     */
    private Integer category4Id;

    /**
     * Filters by category tag ID, optional.
     */
    private Integer categoryTagId;

    /**
     * Filters by character name ID, optional.
     */
    private Integer characterNameId;

    /**
     * Filters by maker ID, optional.
     */
    private Integer makerId;

    /**
     * Filters by game's or show's ID, optional.
     */
    private Integer originalTitleId;

    /**
     * Filters by series title ID, optional.
     */
    private Integer seriesTitleId;

    /**
     * Sort key, optional. See {@link SortKeys} for possible values.
     */
    private String sortKey;

    /**
     * Converts the search request parameters into a set of request queries.
     *
     * @return a set of RequestQuery objects representing the search parameters
     */
    public Set<RequestQuery> toRequestQueries() {
        if (maximumItemsPerPage <= 0 || maximumItemsPerPage > 50) {
            throw new IllegalArgumentException("maximumItemsPerPage must be greater than 0 or smaller than 50");
        }

        if (Boolean.TRUE.equals(filterAnyAvailability)) {
            filterPreOrder = true;
            filterBackOrder = true;
            filterNewItems = true;
            filterPreOwnedItems = true;
        }

        var queries = new HashSet<RequestQuery>();

        queries.add(RequestQuery.of("pagemax", String.valueOf(maximumItemsPerPage)));
        queries.add(RequestQuery.of("lang", language));

        addIfNonEmpty(queries, "s_keywords", searchKeywords);
        addIfNonEmpty(queries, "s_st_list_preorder_available", filterPreOrder);
        addIfNonEmpty(queries, "s_st_list_backorder_available", filterBackOrder);
        addIfNonEmpty(queries, "s_st_list_newitem_available", filterNewItems);
        addIfNonEmpty(queries, "s_st_condition_flg", filterPreOwnedItems);
        addIfNonEmpty(queries, "s_st_list_store_bonus", filterAmiAmiBonus);
        addIfNonEmpty(queries, "s_st_saleitem", filterOnSaleItems);
        addIfNonEmpty(queries, "s_cate1", category1Id);
        addIfNonEmpty(queries, "s_cate2", category2Id);
        addIfNonEmpty(queries, "s_cate3", category3Id);
        addIfNonEmpty(queries, "s_cate4", category4Id);
        addIfNonEmpty(queries, "s_cate_tag", categoryTagId);
        addIfNonEmpty(queries, "s_charaname_search_id", characterNameId);
        addIfNonEmpty(queries, "s_maker_id", makerId);
        addIfNonEmpty(queries, "s_originaltitle_id", originalTitleId);
        addIfNonEmpty(queries, "s_seriestitle_id", seriesTitleId);
        addIfNonEmpty(queries, "s_sortkey", sortKey);

        return queries;
    }

    private void addIfNonEmpty(Collection<RequestQuery> queries, String key, String value) {
        if (value != null && !value.isEmpty()) {
            queries.add(RequestQuery.of(key, value));
        }
    }

    private void addIfNonEmpty(Collection<RequestQuery> queries, String key, Boolean value) {
        if (value != null) {
            queries.add(RequestQuery.of(key, value ? "1" : "0"));
        }
    }

    private void addIfNonEmpty(Collection<RequestQuery> queries, String key, Integer value) {
        if (value != null && value > 0) {
            queries.add(RequestQuery.of(key, String.valueOf(value)));
        }
    }

    /**
     * Sort key constants for AmiAmi search requests.
     */
    public static class SortKeys {

        public static final String RECENTLY_UPDATED = "regtimed";
        public static final String RECOMMENDED = "recommend";
        public static final String RELEASE_DATE = "releasedated";
    }
}
