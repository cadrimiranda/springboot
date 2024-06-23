package com.dietreino.backend.dto;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageableDTO<Entity, DTO> {
    public Integer pageSize;
    public Integer pageNumber;
    public Integer totalPages;
    public Integer totalItems;
    public List<DTO> items;
    public Boolean first;
    public Boolean last;

    public PageableDTO(Page<Entity> page) {
        this.pageSize = page.getSize();
        this.pageNumber = page.getNumber();
        this.totalPages = page.getTotalPages();
        this.totalItems = page.getContent().size();
        this.first = page.isFirst();
        this.last = page.isLast();

        transformContent(page.getContent());
    }

    public void transformContent(List<Entity> content) {
        this.items = new ArrayList<>();
    }
}
