package com.LearnSpring.OneShot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// The @RestController annotation indicates that this class will handle web requests.
// It's a specialized version of @Controller that combines @Controller and @ResponseBody,
// allowing the methods to return data directly as HTTP responses (often in JSON format).

@RestController
public class HelloController {

    // The @GetMapping("/") annotation maps HTTP GET requests to the "/" URL path
    // (i.e., the root path of the application) to this method.
    // When a GET request is made to this URL, the HelloWorld method is called.

    @GetMapping("/")
    public String HelloWorld() {
        // This method returns a simple string response: "First API".
        // The returned string will be sent as the response body of the GET request.
        return "First API";
    }
}
