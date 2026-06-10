package com.billflow.service.impl;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

@Component
public class ToolsServiceImpl {

    private final WhatsAppServiceImpl whatsAppService;

    public ToolsServiceImpl(WhatsAppServiceImpl whatsAppService) {
        this.whatsAppService = whatsAppService;
    }

    @Tool("Enviar mensagem")
    public String messageFormatting(String name, String phone, String value, String date){

        whatsAppService.sendMessage(phone,
                "Olá, "
                        + name + "!"
                        + " Tudo bem? "
                        + "Em nossas controles constamos um pagamento pendente no valor de "
                        + value
                        + ". O valor é referente a uma compra realizada no dia "
                        + date
                        + ". Caso ja tenha efetuado o pagamento, peço a gentileza de nos enviar o comprovante.");

        return "Mensagem enviada com sucesso!";
    }
}
