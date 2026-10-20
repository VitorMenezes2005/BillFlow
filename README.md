# BillFlow

## Variáveis de ambiente

| Variável | Obrigatória | Exemplo | Função |
|---|---|---|---|
| `GEMINI_API_KEY` | sim | — | Chave da API do Google Gemini, usada pelo modelo de IA. |
| `GEMINI_MODEL` | sim | `gemini-2.5-flash-lite` | Nome do modelo do Gemini usado. |
| `SERVER_PORT` | sim | `8081` | Porta HTTP em que a aplicação sobe. |
| `WHATSAPP_BASE_URL` | sim | `http://localhost:8080` | URL da Evolution API, usada para enviar as mensagens no WhatsApp. |
| `WHATSAPP_API_KEY` | sim | — | Chave (`apikey`) de autenticação da Evolution API. |
| `WHATSAPP_INSTANCE` | sim | — | Nome da instância do WhatsApp configurada na Evolution API. |

## Configurando o `.env`

Copie o arquivo de exemplo e preencha com os seus valores:

```bash
cp .env.example .env
```

## Configurando o `AiService`

O prompt do assistente fica em `src/main/java/com/billflow/service/AiService.java`, na anotação `@SystemMessage`. A primeira linha tem dois campos que você precisa preencher com o contexto do seu uso:

```
Você é um membro de um [ESCREVA SUA FINALIDADE] que gerencia [DIGITE SUA FINALIDADE].
```

| Campo | O que colocar | Exemplo |
|---|---|---|
| `[ESCREVA SUA FINALIDADE]` | O setor ou a organização que o assistente representa. | `departamento financeiro` |
| `[DIGITE SUA FINALIDADE]` | O que esse setor gerencia, ou seja, de onde vêm as cobranças. | `a venda de produtos da loja` |

Com o exemplo preenchido, a linha fica assim:

```
Você é um membro de um departamento financeiro que gerencia a venda de produtos da loja.
```

O restante do prompt já define o comportamento e pode ser usado como está:
- **Detecção de pagamento pendente:** registros com `?`, `PENDENTE`, `NÃO PAGO` ou `AGUARDANDO PAGAMENTO` fazem o assistente usar a ferramenta *Enviar mensagem* para avisar o devedor pelo WhatsApp.
- **Dados faltando:** se faltar nome, data, valor ou número, o assistente pede só o que falta.
- **Fora do contexto:** perguntas sobre outros assuntos são recusadas.

Se quiser, você pode adaptar essas regras, por exemplo adicionando outros termos que indiquem pagamento pendente.
