package com.agat.webClient.controller;

import com.agat.webClient.model.Text;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@Controller
public class MainController {



    static final String URL = "http://localhost:8080/message";
    @GetMapping("/main")
    public String main2(Model model) {
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        // Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("my_other_key", "my_other_value");

        // HttpEntity<String>: To get result as String.
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Send request with GET method, and Headers.
        ResponseEntity<String> response = restTemplate.exchange(URL, //
                HttpMethod.GET, entity, String.class);

        String result = response.getBody();
        model.addAttribute("b", result);
        GetEx getEx = new GetEx();
        model.addAttribute("a", getEx.result);
        return "main";

    }

    @PostMapping("/main")
    public String postEx(@RequestParam String TEXT,
                           Map<String, Object> model) {

        Text text = new Text(TEXT);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();

        // Data attached to the request.
        HttpEntity<Text> requestBody = new HttpEntity<>(text, headers);

        // Send request with POST method.
        Text e = restTemplate.postForObject(URL, requestBody, Text.class);

        if (e != null && e.getText() != null) {

            System.out.println("Employee created: " + e.getText());
        } else {
            System.out.println("Something error!");
        }
        return "redirect:/main";
    }





}
