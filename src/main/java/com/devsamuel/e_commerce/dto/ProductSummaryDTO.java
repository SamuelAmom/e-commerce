package com.devsamuel.e_commerce.dto;

public class ProductSummaryDTO
{
    private Long id;
    private String name;

    public ProductSummaryDTO(){ }

    public ProductSummaryDTO(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
