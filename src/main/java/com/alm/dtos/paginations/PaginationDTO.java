package com.alm.dtos.paginations;

public class PaginationDTO {
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public PaginationDTO(int pageNumber, int pageSize, int totalPages, long totalElements, boolean last) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }

    public PaginationDTO() {
    }


    public int getPageNumber() {
        return this.pageNumber;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public long getTotalElements() {
        return this.totalElements;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public boolean isLast() {
        return this.last;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PaginationDTO)) return false;
        final PaginationDTO other = (PaginationDTO) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getPageNumber() != other.getPageNumber()) return false;
        if (this.getPageSize() != other.getPageSize()) return false;
        if (this.getTotalElements() != other.getTotalElements()) return false;
        if (this.getTotalPages() != other.getTotalPages()) return false;
        if (this.isLast() != other.isLast()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PaginationDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getPageNumber();
        result = result * PRIME + this.getPageSize();
        final long $totalElements = this.getTotalElements();
        result = result * PRIME + (int) ($totalElements >>> 32 ^ $totalElements);
        result = result * PRIME + this.getTotalPages();
        result = result * PRIME + (this.isLast() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "PaginationDTO(pageNumber=" + this.getPageNumber() + ", pageSize=" + this.getPageSize() + ", totalElements=" + this.getTotalElements() + ", totalPages=" + this.getTotalPages() + ", last=" + this.isLast() + ")";
    }
}
