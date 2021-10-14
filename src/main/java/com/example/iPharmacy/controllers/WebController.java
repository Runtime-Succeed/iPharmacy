package com.example.iPharmacy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {

    public static List<String> list= new ArrayList<>();

    static {
        list.add("btn btn-danger");
        list.add("btn btn-success");
        list.add("btn btn-info");
        list.add("btn btn-secondary");
        list.add("btn btn-warning");
        list.add("btn btn-dark");
    }

    @GetMapping("/button")
    public String changeButton(Model model){

        // generating the index using Math.random()
        int index = (int)(Math.random() * list.size());

        model.addAttribute("color", list.get(index));
        return "thymeleaftest";
    }
}