package com.example.employeems.dto;


public class TheaterDTO {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(Double seatPrice) {
        this.seatPrice = seatPrice;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public Integer getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(Integer columnCount) {
        this.columnCount = columnCount;
    }

    public TheaterDTO(int id, String name, String description, Double seatPrice, Integer rowCount, Integer columnCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.seatPrice = seatPrice;
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }

    public TheaterDTO() {
    }

    private String name;
    private String description;
    private Double seatPrice;
    private Integer rowCount;
    private Integer columnCount;

}