package com.agat.webClient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
public class MainController {


    @GetMapping("/main")
    public String main(Model model) {
        GetExample getExample = new GetExample();
        model.addAttribute("a", getExample.result);
        return "main";

    }






}
