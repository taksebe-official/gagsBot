package ru.taksebe.telegram.gags.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/gags/test")
    public String getTestMessage() {
        return "LOL";
    }
}