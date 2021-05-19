package com.vaadin.tutorial.crm.backend.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

@Entity
public class recipe extends AbstractEntity {

    private String name ="";

    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER)
    private List<ingredient> ingredients = new LinkedList<ingredient>();

    public recipe() {
    }

    public recipe(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
