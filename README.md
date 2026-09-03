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
