package com.example.demo.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping("/callback")
    public String result(HttpServletRequest req){
        String code = req.getParameter("code");

        String baseUrl = "http://localhost:8080";

        RestTemplate t = new RestTemplate();
        URI  uri = URI.create(baseUrl+"/oauth/token");

        Map params = new HashMap();
        params.put("grant_type","authorization_code");
        params.put("scope","read_profile");
        params.put("code",code );
        params.put("redirect_uri","http://localhost:9000/callback");
        params.put("client_id","client");

        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "Basic id password 합친거");

        ResponseEntity<String> responseEntity = t.exchange(uri, HttpMethod.POST ,new HttpEntity(params,header),String.class);
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());

        return "index";
    }
}
