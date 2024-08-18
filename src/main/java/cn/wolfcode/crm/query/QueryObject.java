package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;


public class QueryObject {
    private int currentPage = 1;
    private int pageSize = 3;

    private String keyword;
    private String orderBy; //做结果集排序的

    public String getKeyword() {
        return StringUtils.hasLength(keyword) ? keyword : null;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
