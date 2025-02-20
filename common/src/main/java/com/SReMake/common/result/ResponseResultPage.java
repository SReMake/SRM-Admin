package com.SReMake.common.result;

import lombok.Data;
import org.babyfish.jimmer.Page;
import org.babyfish.jimmer.spring.repo.PageParam;

import java.util.List;
import java.util.Objects;

@Data
public class ResponseResultPage<T> {
    private int code;
    private String message;
    private List<T> data;
    private long total;
    private long totalPage;
    private int page;
    private int size;

    public ResponseResultPage(int code, String message, Page<T> data, PageParam pageParam) {
        this.code = code;
        this.message = message;
        this.data = data.getRows();
        this.total = data.getTotalRowCount();
        this.totalPage = data.getTotalPageCount();
        if (!Objects.isNull(pageParam)) {
            this.page = pageParam.getIndex();
            this.size = pageParam.getSize();
        }
    }

    public ResponseResultPage(int code, String message, List<T> data, long total, long totalPage, PageParam pageParam) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.total = total;
        this.totalPage = totalPage;
        if (!Objects.isNull(pageParam)) {
            this.page = pageParam.getIndex();
            this.size = pageParam.getSize();
        }

    }

    public static <T> ResponseResultPage<T> success(Page<T> data, PageParam pageParam) {
        return new ResponseResultPage<>(200, "Success", data, pageParam);
    }

    public static <T> ResponseResultPage<T> error(int code, String message) {
        return new ResponseResultPage<>(code, message, null, 0, 0, null);
    }


}
