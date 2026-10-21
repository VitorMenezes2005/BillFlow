# BillFlow

## Pré-requisitos

- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- Uma IDE de sua preferência (recomendado: [IntelliJ IDEA](https://www.jetbrains.com/idea/))
- [Postman](https://www.postman.com/downloads/)

## Instalando a Evolution API

O BillFlow envia as mensagens pelo WhatsApp usando a Evolution API. Para rodá-la com Docker, baixe o pacote [`setup/n8n-evo-docker-starter-main-guerra.rar`](setup/n8n-evo-docker-starter-main-guerra.rar) e siga os passos abaixo.

> O pacote é baseado no template [war721/N8N](https://github.com/war721/N8N/tree/main/n8n), criado por **Guerra721**.

1. Descompacte o arquivo no gerenciador de arquivos.
2. No VS Code, clique em **File → Open Folder** e abra a pasta descompactada.
3. Dentro do VS Code, crie uma cópia do arquivo `.env.example` e renomeie a cópia para `.env`.
4. Abra o `.env` e troque o valor da chave `AUTHENTICATION_API_KEY`.
   > Dica: use uma IA para gerar uma chave nova a partir da chave original que veio no `AUTHENTICATION_API_KEY`.
5. Aperte **Ctrl + S** para salvar o arquivo. Depois clique em **Terminal → New Terminal** e rode o comando:
   ```bash
   docker compose up -d
   ```
6. Espere o download de todas as imagens e abra o Docker Desktop.
7. No Docker Desktop, entre na aba **Containers** e clique na porta do `evolution_api`. Depois que a porta abrir, copie o endereço HTTP e cole no navegador em que a porta foi aberta.
8. Por fim, realize o cadastro.

A chave que você colocou em `AUTHENTICATION_API_KEY` é o valor de `WHATSAPP_API_KEY` no BillFlow, e o endereço da Evolution API (por padrão `http://localhost:8080`) é o `WHATSAPP_BASE_URL`.

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
