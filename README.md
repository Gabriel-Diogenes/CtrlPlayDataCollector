# ctrlplayDataCollector

Automação desenvolvida em Java utilizando Selenium para coleta de dados de clientes e pagamentos no sistema da Ctrl+Play, com geração automática de planilha Excel baseada em um template.

---

# Objetivo

Automatizar o processo de:

- Acessar o sistema da Ctrl+Play
- Coletar dados de clientes e pagamentos
- Evitar duplicidade de registros
- Gerar uma planilha Excel pronta para uso (ex: emissão de notas fiscais)

---

# Como a aplicação funciona

## Fluxo geral

1. Acessa o site:

https://loja.ctrlplay.com.br


2. Realiza login com credenciais via `.env`

3. Navega até:
- Clientes
- Detalhes de cada cliente
- Pedidos

4. Coleta os dados:
- Nome do responsável
- CPF
- Telefone
- Email
- Endereço completo
- Nome do aluno (filho)
- Parcela
- Valor pago
- Data de pagamento
- Data de vencimento

5. Valida duplicidade:
- Utiliza arquivo `controle.json`
- Chave única:
  ```
  CPF + nomeFilho + parcela
  ```

6. Gera planilha Excel:
- Baseada no template `.xls`
- Aba: `Contas-Receber-CNA`
- Arquivo final: `resultado.xls`

# Controle de duplicidade

A aplicação utiliza um arquivo:


controle.json


Exemplo:

```json
[
  "12345678900_joao_6",
  "12345678900_maria_6"
]
