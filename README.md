# GameClothesAPI
API 

Documentação de USO

Passo 1 - Baixe o projeto na maquina

Passo 2 - Rode o servidor do banco de dados de acordo com a imagem abaixo 
![screenshot](https://user-images.githubusercontent.com/54107073/120707550-db638800-c490-11eb-90a7-963222fdd53b.png)

Passo 3 - Depois de inicializado é importante que deixe o terminal aberto com o servidor rodando, caso feche a pagina o servidor deixará de rodar... se for uma opção, você também pode adicionar o arquivo .bat como um serviço do sistema e deixar rodando em background o server

Passo 4 - Agora abra o SGBD do banco de acordo com a imagem abaixo

![image](https://user-images.githubusercontent.com/54107073/120707987-5d53b100-c491-11eb-8bfa-117c4492feb2.png)

Passo 5 - Teste a conexão do Banco de dados com as informações abaixo

![image](https://user-images.githubusercontent.com/54107073/120707738-18c81580-c491-11eb-904c-e53f26385f85.png)

Passo 6 - Caso os passos acima funcionaram, tente agora buildar a aplicação na sua maquina seguindo os seguintes passos.

Passo 7 - Tente buildar o projeto abrindo a pasta root do projeto pelo terminal e rodando o seguinte comando "mvn clean install -DskipTests", caso não tenha o maven instalado no computador, pode tentar fazer o build pelo eclipse através da ferramenta abaixo 

![image](https://user-images.githubusercontent.com/54107073/120708373-dd7a1680-c491-11eb-9699-96e326c01932.png)

Passo 7.1 - Abrirá uma tela e dentro dessa tela você irá especificar os comandos que serão rodados no build através da box "goals" 

![image](https://user-images.githubusercontent.com/54107073/120708520-0a2e2e00-c492-11eb-8479-263852f95179.png)

Passo 7.2 - Agora só rodar o build, depois que estiver finalizado, o arquivo .jar da api deverá aparecer na pasta target do projeto

![image](https://user-images.githubusercontent.com/54107073/120708707-4c576f80-c492-11eb-8c58-d8241f718ed2.png)

Passo 8 - Assim como o servidor do DB, esse arquivo também deve ser aberto através do terminal, entre na pasta target do projeto pelo terminal e rode o comando abaixo 

java -jar GameClothesAPI.jar

Passo 8.1 - Deverá subir o tomcat embutido do spring na porta 9090, como na imagem abaixo

![image](https://user-images.githubusercontent.com/54107073/120708951-9c363680-c492-11eb-95f2-22b37fe89030.png)

Passo 9 - Faça o teste se os serviços da API estão de pé, abra o navegador e rode a url

http://localhost:9090/services/product

o retorno esperado é a lista dos produtos 

![image](https://user-images.githubusercontent.com/54107073/120711667-09979680-c496-11eb-87f5-390204c5cefa.png)


