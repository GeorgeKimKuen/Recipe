package com.vaadin.tutorial.crm.backend.service;

import com.vaadin.tutorial.crm.backend.entity.ingredient;
import com.vaadin.tutorial.crm.backend.entity.recipe;
import com.vaadin.tutorial.crm.backend.repository.IngredientsRepository;
import com.vaadin.tutorial.crm.backend.repository.RecipesRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class IngredientService {

    private RecipesRepository recipesRepository;
    private IngredientsRepository ingredientsRepository;
    private static final Logger LOGGER = Logger.getLogger(RecipesService.class.getName());


    public IngredientService(RecipesRepository recipesRepository,
                             IngredientsRepository ingredientsRepository){
        this.recipesRepository = recipesRepository;
        this.ingredientsRepository = ingredientsRepository;
    }
    public List<ingredient> findAll(){
        return ingredientsRepository.findAll();
    }
    public List<ingredient> findAll(String ingredient){
        if(ingredient==null || ingredient.isEmpty()){
            LOGGER.log(Level.SEVERE,
                    "Did not input ingredient");
            return ingredientsRepository.findAll();
        }
        else
            return ingredientsRepository.search(ingredient);
    }


    public Long count(){
        return ingredientsRepository.count();
    }
    public void delete(ingredient ingredient){
        ingredientsRepository.delete(ingredient);
    }
    public void save(ingredient ingredient){
        if(ingredient==null){
            LOGGER.log(Level.SEVERE,
                    "There is no name for this recipe, please input a name");
            return;
        }
        ingredientsRepository.save(ingredient);
    }
    @PostConstruct
    public void populateTestData() {
        if(recipesRepository.count()==0){
            recipesRepository.saveAll(Stream.of("Chicken Scampi: https://www.simplyrecipes.com/recipes/chicken_scampi_with_angel_hair_pasta/",
                    "Nachos: https://www.simplyrecipes.com/recipes/loaded_sheet_pan_nachos/",
                    "Shell Pasta: https://www.simplyrecipes.com/recipes/shell_pasta_with_sausage_and_greens/",
                    "Chicken Pad Thai: https://www.simplyrecipes.com/recipes/chicken_pad_thai/",
                    "Risotto: https://www.simplyrecipes.com/recipes/risotto_with_balsamic_roasted_asparagus_and_peas/ ",
                    "Mexican Green Rice: https://www.simplyrecipes.com/recipes/mexican_green_rice/",
                    "Brownie: https://www.simplyrecipes.com/recipes/brownie_in_a_mug/ ",
                    "Oatmeal Cookies: https://www.simplyrecipes.com/recipes/grandmas_oatmeal_cookies/",
                    "Rice Pudding: https://www.simplyrecipes.com/recipes/rice_pudding/,",
                    "Shakshuka: https://www.simplyrecipes.com/shakshuka-with-feta-olives-and-peppers-5114919 ",
                    "Hash Browns: https://www.simplyrecipes.com/recipes/crispy_hash_browns/",
                    "Chia Pudding: https://www.simplyrecipes.com/recipes/chia_pudding_with_blueberries_and_almonds/",
                    "Apple Pie Smoothie: https://www.simplyrecipes.com/recipes/apple_pie_smoothie/"
            )
                    .map(recipe::new)
                    .collect(Collectors.toList()));
        }
        if(ingredientsRepository.count()==0){
            List<recipe> recipes = recipesRepository.findAll();
            String tmp ="-";
            AtomicInteger c= new AtomicInteger();
            ingredientsRepository.saveAll(
                    Stream.of("pasta","butter","oil","chicken","salt","pepper","garlic","wine","lemon","parsley","-",
                            "oil", "onion",  "beef", "chili powder", "coriander", "dried oregano", "salt", "chip", "bean", "corn", "olive", "cheese","-",
                            "pasta", "salt", "water", "sausage", "kale", "onions", "cloves", "cheese","-",
                            "noodle", "fish sauce", "rice", "tamarind", "sugar", "oil", "egg", "chicken", "peanut","-",
                            "asparagus", "oil", "salt", "onion", "rice", "stock", "pea", "lemon", "parmesan", "-",
                            "oil", "rice", "stock", "parsley", "cilantro", "poblano chile", "onion", "clove", "salt", "-",
                            "butter", "flour", "sugar", "oil", "chocolate chips", "salt", "cinnamon", "milk", "water", "coffee", "vanilla extract", "ice cream", "-",
                            "sugar", "egg", "vanilla extract", "flour", "salt", "cinnamon", "oat", "walnut", "raisin","-",
                            "milk", "rice", "salt", "egg", "sugar", "vanilla extract", "cinnamon", "raisin","-",
                            "olive", "smoked paprika", "cumin", "onion", "garlic", "tomatoes", "salt", "cheese", "egg","-",
                            "oil", "potato","salt","pepper","-",
                            "milk", "blueberry", "chia seed", "honey", "cinnamon", "vanilla extract", "almond","-",
                            "milk", "apple",  "cashew", "vanilla extract", "cinnamon", "date", "ice"
                    )
                            .map(name->{
                                ingredient ingredient = new ingredient();
                                ingredient.setIngredient(name);

                                if(tmp==name){
                                    c.getAndIncrement();
                                }
                                else
                                    ingredient.setRecipe(recipes.get(c.get()));
                                return ingredient;
                            })
                            .collect(Collectors.toList()));
        }

    }

}
