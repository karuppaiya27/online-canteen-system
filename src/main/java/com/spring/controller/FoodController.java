// File: FoodController.java
package com.spring.controller;

import com.spring.model.FoodItem;
import com.spring.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/home")
    public String showHomePage(Model model) {
        List<FoodItem> items = foodService.getAllFoodItems();
        model.addAttribute("items", items);
        return "home";
    }

    // Optional endpoint for testing single item retrieval
    @GetMapping("/food/{id}")
    @ResponseBody
    public FoodItem getFoodById(@PathVariable Long id) {
        return foodService.getById(id);
    }
}
