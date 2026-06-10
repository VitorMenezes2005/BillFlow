package com.billflow.controller;

import dev.langchain4j.service.Result;
import com.billflow.service.AiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping
    public String message(@RequestBody String message){
        Result<String> result = aiService.handleRequest(message);

        return result.content();
    }
}
