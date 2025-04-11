
# Projeto LEDA - transformações 

A primeira etapa do projeto da disciplina de Laboratório de Estrutura de Dados tem como objetivo a transformação das datas do arquivo "tweets.csv", gerando um novo CSV nomeado como "tweets_formated_data.csv".

A partir das transformação da data, cria duas novas colunas contendo novas informações e dados sobre elas, armazenado em um novo CSV chamado “tweets_mentioned_persons.csv”.

## Como rodar o código

1. Clone o repositório:
  - No terminal, execute:
     ```bash
     git clone https://github.com/ellerimx/projetoLEDA-transformacoes.git
     ```

   - Ao executar, digite o seguinte comando para verificar a pasta onde o repositório foi clonado::
     - **no Windows (CMD ou PowerShell):**
       ```cmd
       cd
       ```
     - **no macOS ou Linux (ou git bash no Windows):**
       ```bash
       pwd
       ```

2. Baixe o arquivo que contém a dataBase no link: [Download do dataBase](https://drive.google.com/drive/u/1/folders/1x3Zxj89-YURgY7_dVkE1ONW_qqfSDNyb)

3. Coloque o arquivo na pasta correta.
   - Após o download no passo 2, mova o arquivo ``` tweets.csv ``` para a pasta ```dataBaseTweets```, que está no repositório clonado.

5. Verifique se o caminho do arquivo ```src/ProcessCSV``` está correto.

   - No Windows: o caminho é separado por duas barras invertidas \\ (como está no código original) 
    
    ![imagem](execucao/diretorioWindows.png)

   - No MacOS ou Linux: modifique o caminho para uma barra normal. Exemplo: ```String inputFile = "dataBaseTweets/tweets.csv"; ```
