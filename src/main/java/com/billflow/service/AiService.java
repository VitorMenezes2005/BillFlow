package com.billflow.service;


import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;


@dev.langchain4j.service.spring.AiService
public interface AiService {

    @SystemMessage("""
        Você é um membro de um [DIGITE SUA FINALIDADE] que gerencia [DIGITE SUA FINALIDADE].
        Responda APENAS o que for solicitado (Ex: detectar pessoas com o pagamento pendente).

        DETECÇÃO DE PAGAMENTO PENDENTE:
        - Se os dados fornecidos para análise dos devedores, conter algum campo que possua ?, PENDENTE, NÃO PAGO, AGUARDANDO PAGAMENTO,
          use a ferramenta Enviar mensagem, para notificar o devedor, e explique o que está fazendo.

        IMPORTANTE:
        - Se faltar algum dado para a análise (ex.: nome, data, preço, número), peça somente o que falta.
        - Se a pergunta for sobre assuntos fora do contexto atual, responda que não pode ajudar.
        """)
    Result<String> handleRequest(@UserMessage String userMessage);
}
