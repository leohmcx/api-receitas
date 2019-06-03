# api-receitas

![Alt Text](https://github.com/leohmcx/api-receitas/blob/master/logico.jpg)

```
(GET) -> Pesquisar receita por nome
http://localhost:8080/api/v1/receita/{nome}

(GET) -> Pesquisar receita por categoria
http://localhost:8080/api/v1/receita/categoria/{nome}

(GET) -> Pesquisar receita por ingrediente
http://localhost:8080/api/v1/receita/ingrediente/{nome}

(DELETE)-> Para deletar a receita
http://localhost:8080/api/v1/receita/{id}

(PATCH) -> Para adicionar/remover um ingrediente da receita
http://localhost:8080/api/v1/receita/{id}/{ingrediente}/{qtde}/{medida}

(POST)  -> Para criar uma receita
http://localhost:8080/api/v1/receita

@RequestBody
{
"nome": "prato",
    "tempo_preparo": 90,
    "rendimento": 6,
    "modo_preparo": "teste",
    "categoria": {
    	"nome":"TESTE"
    }
}

(PUT) -> Para alterar a receita
http://localhost:8080/api/v1/receita/{id}

@RequestBody
{
"nome": "prato",
    "tempo_preparo": 90,
    "rendimento": 6,
    "modo_preparo": "teste",
    "categoria": {
    	"nome":"TESTE"
    }
}
```
