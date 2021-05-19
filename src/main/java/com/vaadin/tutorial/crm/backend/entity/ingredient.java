package com.vaadin.tutorial.crm.backend.entity;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class ingredient extends AbstractEntity implements Cloneable {

    @NotNull
    @NotEmpty
    private String ingredient ="";

    //private int amount=0;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private recipe recipe;

    public ingredient() {
    }

    public ingredient(String name) {
        this.ingredient = name;
        //this.amount= 1;
    }

    public ingredient(String name, int amount) {
        this.ingredient = name;
        //this.amount = amount;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    // public int getAmount() {
    //    return amount;
    //}

    // public void setAmount(int amount) {
    //this.amount = amount;
    // }

    public com.vaadin.tutorial.crm.backend.entity.recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(recipe recipe) {
        this.recipe = recipe;
    }
}