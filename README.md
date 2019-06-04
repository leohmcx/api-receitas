# api-receitas

![Alt Text](https://github.com/leohmcx/api-receitas/blob/master/logico.jpg)

```
(GET) -> Pesquisar receita por nome (parâmetro {nome} (tipo String) da receita)
http://localhost:8080/api/v1/receita/{nome}

(GET) -> Pesquisar receita por categoria (parâmetro {nome} (tipo String) da categoria)
http://localhost:8080/api/v1/receita/categoria/{nome}

(GET) -> Pesquisar receita por ingrediente (parâmetro {nome} (tipo String) do ingrediente)
http://localhost:8080/api/v1/receita/ingrediente/{nome}

(DELETE)-> Para deletar a receita (parâmetro {id} (tipo Long) da receita)
http://localhost:8080/api/v1/receita/{id}

################################################ POST #########################################################
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

################################################ PATCH #########################################################
(PATCH) -> Para add/remove ingrediente da receita 
http://localhost:8080/api/v1/receita/{id}/{ingrediente}/{qtde}/{medida}

--> envia (parametros)
	(Identificador {id} (tipo Long) da receita)
	(Nome {ingrediente} (tipo String) do ingrediente)
	(Quantidade {qtde} (tipo String) do ingrediente )
	(Unidade de {medida} (tipo String) do ingrediente)
	
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

#################################################### PUT #######################################################
(PUT) -> Para alterar a receita (parâmetro {id} (tipo Long) da receita). Isto mantém os relacionamentos
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
##############################################################################################################
```
