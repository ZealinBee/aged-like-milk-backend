package com.alm.dtos.paginations;

import com.alm.dtos.users.GetUserDTO;
import lombok.Data;

import java.util.List;
public class UsersPaginationDTO extends PaginationDTO {
    private List<GetUserDTO> users;

    public UsersPaginationDTO(int pageNumber, int pageSize, int totalPages, long totalElements,  boolean last, List<GetUserDTO> users) {
        super(pageNumber, pageSize, totalPages, totalElements, last);
        this.users = users;
    }

    public UsersPaginationDTO() {
    }

    public List<GetUserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<GetUserDTO> users) {
        this.users = users;
    }
}
