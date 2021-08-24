package com.khodko.RoyalHotel.pagination;

import javax.servlet.http.HttpServletRequest;

public class PaginationHandler {

    public static final String INPUT_FORM_ATTR = "inputForm";
    public static final String PAGINATION_ATTR = "pagination";

    public static final int NAV_OFFSET = 3;

    private final int[] limits;
    private final int count;
    private final String url;
    private int limit;
    private int offset;
    private int begin;
    private int end;
    private int pagesCount;

    private PaginationHandler(int[] limits, int count, String url) {
        this.limits = limits;
        this.count = count;
        this.url = url;
    }

    public static PaginationHandler handle(int[] limits, int count, String paginationUrl, HttpServletRequest request) {
        PaginationHandler paginationHandler = new PaginationHandler(limits, count, paginationUrl);
        paginationHandler.handle(request);
        return paginationHandler;
    }

    private void handle(HttpServletRequest request) {
        InputForm inputForm = new InputForm(request, limits[0]);
        limit = inputForm.getLimitValue();
        pagesCount = count / limit;
        if (count % limit > 0) {
            pagesCount++;
        }
        offset = inputForm.getPageValue() * limit - limit;

        begin = Math.max(inputForm.getPageValue() - NAV_OFFSET, 1);
        end = Math.min(inputForm.getPageValue() + NAV_OFFSET, pagesCount);

        request.setAttribute(INPUT_FORM_ATTR, inputForm);
        request.setAttribute(PAGINATION_ATTR, this);
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public int[] getLimits() {
        return limits;
    }

    public String getUrl() {
        return url;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    public int getPagesCount() {
        return pagesCount;
    }
}
