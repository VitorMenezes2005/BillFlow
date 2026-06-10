package com.billflow.configuration;

import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import com.billflow.service.AiService;
import com.billflow.service.impl.ToolsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configs {

    @Value("${gemini.api-key}")
    private String apikey;

    @Value("${gemini.model}")
    private String geminiModel;

    @Bean
    public GoogleAiGeminiChatModel googleAiGeminiChatModel(){
        return GoogleAiGeminiChatModel.builder()
                .apiKey(apikey)
                .modelName(geminiModel)
                .build();
    }

    @Bean
    public AiService service(GoogleAiGeminiChatModel model, ToolsServiceImpl tools){
        return AiServices.builder(AiService.class)
                .chatModel(model)
                .tools(tools)
                .build();
    }

}
