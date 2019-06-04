# api-receitas

![Alt Text](https://github.com/leohmcx/api-receitas/blob/master/logico.jpg)

```
(GET) -> Pesquisar receita por nome (parâmetro {nome})
http://localhost:8080/api/v1/receita/{nome}

(GET) -> Pesquisar receita por categoria (parâmetro {nome})
http://localhost:8080/api/v1/receita/categoria/{nome}

(GET) -> Pesquisar receita por ingrediente (parâmetro {nome})
http://localhost:8080/api/v1/receita/ingrediente/{nome}

(DELETE)-> Para deletar a receita (parâmetro {id} da receita)
http://localhost:8080/api/v1/receita/{id}

############################################## POST ##################################################################
(POST)  -> Para criar uma receita
http://localhost:8080/api/v1/receita

--> envia(consumes="application/json")
{
	"categoria": {
    	"nome":"TESTE"
    },
	"nome": "prato",
    "tempo_preparo": 90,
    "rendimento": 6,
    "modo_preparo": "teste"
}

--> retorna(produces="application/json")
{
    "categoria": {
        "id": 9,
        "nome": "TESTE"
    },
    "ingredientes": [],
    "id": 3,
    "nome": "prato",
    "tempo_preparo": 90,
    "rendimento": 6,
    "modo_preparo": "teste"
}
################################################################################################################

################################################ PATCH ################################################################
(PATCH) -> Para add/remove ingrediente da receita 
http://localhost:8080/api/v1/receita/{id}/{ingrediente}/{qtde}/{medida}

--> envia (parâmetro {id} da receita e nome {ingrediente}, quantidade {qtde} e unidade {medida} do ingrediente)
--> retorna
{
    "categoria": {
        "id": 9,
        "nome": "TESTE"
    },
    "ingredientes": [
        {
            "itensIngrediente": {
                "id": 6,
                "nome": "FERMENTO"
            },
            "qtde_item": "1",
            "medida": "Colher"
        }
    ],
    "id": 2,
    "nome": "prato",
    "tempo_preparo": 90,
    "rendimento": 6,
    "modo_preparo": "teste"
}
################################################################################################################

#################################################### PUT ############################################################
(PUT) -> Para alterar a receita (parâmetro {id} da receita). Esta operação mantém os relacionamentos existentes.
http://localhost:8080/api/v1/receita/{id}

--> envia(consumes="application/json")
{
	"nome": "prato alteração",
    "tempo_preparo": 9,
    "rendimento": 60,
    "modo_preparo": "teste",
    "categoria": {
    	"nome":"TESTE"
    }
}

--> retorna(produces="application/json")
{
    "categoria": {
        "id": 9,
        "nome": "TESTE"
    },
    "ingredientes": [
        {
            "itensIngrediente": {
                "id": 6,
                "nome": "FERMENTO"
            },
            "qtde_item": "1",
            "medida": "Colher"
        }
    ],
    "id": 2,
    "nome": "prato alteração",
    "tempo_preparo": 9,
    "rendimento": 60,
    "modo_preparo": "teste"
}
################################################################################################################
```
