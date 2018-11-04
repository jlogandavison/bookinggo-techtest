package me.jackdavison.techtest.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RideSearchController {

    @GetMapping("/")
    String helloworld() {
        return "Hello World";
    }
}

