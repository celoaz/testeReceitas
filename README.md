#API REST para Receitas

----------------------

- Linguagem utilizada: Java
- Banco de Dados: MySQL

##Características Gerais

Esse sistema consiste em uma API REST para Receitas com 4 métodos principais:
-   Criação de receitas (/1/liber/create):
    -   Tipo de entrada: Payload
    -   Este método é responsável pela criação de receitas no sistema. 
        Ele receberá um payload, e:
        -   caso o payload for considerado válido, será salvo no banco de dados e uma mensagem de sucesso será 
            exibida ao usuário;
        -   caso o payload for considerado inválido, com campos nulos, não será salvo no BD e uma mensagem de 
            erro será exibida ao usuário.

-   Atualizar receita (/1/liber/update):
    -   Tipo de entrada: Payload 
    -   Este método é responsável pela atualização de receitas no sistema.
        Ele receberá um payload, e:
        -    procura a receita no sistema. Se encontrar, remove e adiciona a nova receita no lugar. Caso não encontre, retorna erro.
        
-   Recuperar uma receita no sistema (/1/liber/recover):         
    -   Tipo de entrada: parâmetro passado pela URL
    -   Este método é responsável pela recuperação de receitas no sistema. O sistema checa se um dado parâmetro possui entradas no sistema. Essa entrada pode ser o **nome**, **ingredientes** ou ainda **categoria** de uma determinada receita.
        Caso a(s) receita(s) exista(m) no banco, é/são retornada(s) ao usuário.
    
-   Deletar uma receita (/1/liber/delete):
    -   Tipo de entrada: parâmetro passado pela URL
    -   Este método é responsável pela remoção de receitas no sistema. Baseado no nome da receita fornecido por parâmetro, o sistema exclui a receita do sistema.

##Utilização

-   Na primeira utilização, é necessário fazer o build do projeto. 
    Utilizando maven, basta executar o comando *mvn clean install* para gerar a build do projeto.
-   Após a build gerada, execute o arquivo com o comando *java -jar liberTesteReceitas-0.0.1-SNAPSHOT*
-   Com o sistema em execução, basta enviar uma requisição em um dos endpoints para acessar o sistema.

##Exemplo de payload de criação de receita
- Tipo de arquivo: JSON
- ```
    {
      "name": "Bolo complexo",
      "ingredients": [
      	"ovos", "leite", "farinha de trigo", "fermento"
      ],
      "preparationMethod": "Bata os ingredientes em uma batedeira e leve ao forno por 45 minutos",
      "categories": [
      	"bolo", "doce", "facil"],
      "metadata": {
      	"preparationTimeInMinutes": 55,
      	"numberOfServings": 3,
      	"additionalNotes": "Não abra o forno antes dos 30 minutos"
      }
    }
  ```          