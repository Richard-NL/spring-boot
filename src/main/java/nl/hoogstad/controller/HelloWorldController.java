/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hoogstad.controller;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.ExecutionException;
import model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;


@Controller
@EnableAutoConfiguration
public class HelloWorldController {
    
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
    
    @RequestMapping(value="/hello/{name}", method=RequestMethod.GET)
    @ResponseBody
    String name(@PathVariable String name) throws ExecutionException {
        Ingredient ingredient = new Ingredient();
        ingredient.name = "paella";
        ingredient.description = "Spanish rice dish";
        
        
        LoadingCache<String, Ingredient> cache = getLoadingCache();
        cache.put("ingredient1", ingredient);
        Ingredient ingredientMatch = cache.get("ingredient1");
        return "Hello " + name + " a good day to you " + ingredientMatch.name;
    }
    
    
    @RequestMapping("/ingredient")
    @ResponseBody
    String ingredient() throws ExecutionException {
        LoadingCache<String, Ingredient> cache = getLoadingCache();
        Ingredient ingredient = cache.get("ingredient1");
        return "Ingredient found name " + ingredient.name;
    }
    
    private LoadingCache getLoadingCache() {
        CacheLoader<String, Ingredient> loader = new CacheLoader<String, Ingredient>() {
            @Override
            public Ingredient load(String key) {
                return new Ingredient();
            }
        };
        
        LoadingCache<String, Ingredient> cache = CacheBuilder.newBuilder().build(loader);
        return cache;
    }

}