package com.vaadin.tutorial.crm.ui;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.tutorial.crm.backend.entity.ingredient;
import com.vaadin.tutorial.crm.backend.entity.recipe;
import com.vaadin.tutorial.crm.backend.service.IngredientService;
import com.vaadin.tutorial.crm.backend.service.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route("")
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     */
    private Grid<ingredient> grid = new Grid<>(ingredient.class);
    // private ComboBox<String> searchbox = new ComboBox<>();
    private TextField searchbox = new TextField();
    private IngredientService ingredientService;
    private RecipesService recipesService;

    public MainView(IngredientService ingredientService) {
        this.ingredientService = ingredientService;


        // Use TextField for standard text input
        // TextField textField = new TextField("Add Ingredient");
        // textField.addThemeName("bordered");


        // Button click listeners can be defined as lambda expressions
        Button addButton = new Button("search");
        Button finButton = new Button("Reset");

        addButton.addClickListener(click -> addIngredient(searchbox.getValue()));
        // finButton.addClickListener(click -> grid.setItems() );

        HorizontalLayout layout = new HorizontalLayout(searchbox,addButton,finButton);
        layout.setDefaultVerticalComponentAlignment(Alignment.END);


        // Theme variants give you predefined extra styles for components.
        // Example: Primary button has a more prominent look.
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        finButton.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        // You can specify keyboard shortcuts for buttons.
        // Example: Pressing enter in this view clicks the Button.
        addButton.addClickShortcut(Key.ENTER);
        finButton.addClickShortcut(Key.ESCAPE);




        add(layout);
        setSizeFull();
        configureGrid();
        configureCombo();

        add(grid);
        updateGrid();

    }



    private void addIngredient(String ingredient) {
        grid.setItems(ingredientService.findAll(searchbox.getValue()));
    }

    private void configureCombo() {
        //searchbox.setItems();
        searchbox.setPlaceholder("add ingredient here...");
        searchbox.setClearButtonVisible(true);

    }


    private void configureGrid() {
        addClassName("list_view");

        grid.removeColumnByKey("recipe");
        grid.setColumns("ingredient");
        grid.addColumn(ingredient -> {
            recipe recipe = ingredient.getRecipe();
            return recipe == null ? "-" : recipe.getName();
        }).setHeader("Recipe");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.getColumnByKey("ingredient").setSortable(false);
    }

    private void updateGrid() {
        grid.setItems(ingredientService.findAll());
    }

}

