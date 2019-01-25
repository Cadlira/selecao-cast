# Seleção Cast Group
Repositório utilizado para apresentar as implementações das questões apresentadas no processo de seleção do Cast Group.

Foi solicitado a resolução de 3 questões, os quais estão elencados abaixo:
- [Questão 01](https://drive.google.com/open?id=1KaxqHrnWCGT2-RhNl8uCcROTtSvEbdNA) - *Comparação de arquivos em base 64;*
- [Questão 02](https://drive.google.com/open?id=16Moa20GaqE0IylXRUdSV4_rBMBpKWoF7) - *Maior diferença entre elementos de um array;*
- [Questão 01](https://drive.google.com/open?id=1ttnmGNe24ksvmiVJp7F8bL5jRILNRhZK) - *Implementação de CRUD básico com Spring boot e Angular;*

Foi criado um projeto único em Spring boot para a a resolução das 3 questões, conforme a estrutura que será apresentada logo abaixo. Para a questão 03 foi utilizado SGBD, e o escolhido para esse projeto foi `H2` devido a facilidade de deploy para testes. Tambem foi utilizado o [Liquibase](https://www.liquibase.org/) para a geração do schema inicial do banco de dados.  
![](https://i.imgur.com/9j03rEW.jpg)

As configurações comuns ficam logo abaixo do pacote `lira.leo.selecaocast`. A resolução de cada questão ficam em seus respectivos pacotes.

Foram implementados testes unitários e de integração básicos utilizando a ferramenta `JUnit`, sendo os mesmo encontrados no pacote de testes do projeto conforme imagem abaixo. Cada projeto fica em seu respectivo pacote:
![](https://i.imgur.com/6J11Lct.jpg)

Tambem foi necessário a criação de um projeto em Angular para servir de frontend da questão 03. O código fonte do mesmo encontra-se na pasta `src/main/frontend`, e, ao realizar o build, o ***"executável"*** é enviado para a pasta `src/main/resources/static`, conforme imagem abaixo:
![](https://i.imgur.com/N0S1umz.jpg)

O projeto encontra-se publicado no [Heroku](https://www.heroku.com/), no seguinte endereço: [https://selecao-cast.herokuapp.com/](https://selecao-cast.herokuapp.com/)
![](https://i.imgur.com/lcsxqZC.jpg)




### QUESTÃO 01
