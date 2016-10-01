package com.ironyard.data;

/**
 * Created by favianalopez on 9/29/16.
 */
public class Budget {
    private long id;
    private String description;
    private String category;
    private double budgetedAmount;
    private double actualAmount;

    public Budget(long id, String description, String category, double budgetedAmount, double actualAmount) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.budgetedAmount = budgetedAmount;
        this.actualAmount = actualAmount;
    }

    public Budget() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getBudgetedAmount() {
        return budgetedAmount;
    }

    public void setBudgetedAmount(double budgetedAmount) {
        this.budgetedAmount = budgetedAmount;
    }

    public double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(double actualAmount) {
        this.actualAmount = actualAmount;
    }
}