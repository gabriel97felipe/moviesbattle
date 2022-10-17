# moviesbattle
Movies Battle é um card game que utiliza a API do IMDB para coletar dois filmes. O usuário tem que acertar qual deles tem a melhor avaliação, baseado na nota multiplicado pelaquantidade de avaliações

### Collection

A collection do projeto para utilização no Insomnia ou Postman está na raíz chamado [collection.json](./collection.json).

### Database

O banco de dados já está populado com uma quantia considerável de filmes. Para efetuar um novo populamento, pode se utilizar o endpoint /webscrapping. Para isso, é necessário gerar uma chave para a utilização da API por meio do RAPID API.

### API

Para acessar os endpoints da API, é necessário estar logado. Utilize a seguinte header nos endpoints protegidos: Authorization: Bearer <access_token>.
O access_token é gerado toda vez que é efetuado um login.

### Use Cases

Após devidamente autenticado na aplicação, o usuário deve criar uma nova partida a partir do endpoint "Create new match" (POST match/user/{userId}/start).

A partir da criação de uma nova partida, o usuário deve iniciar uma rodada, então, é necessário ir para a collection de Rounds, onde ele irá para o endpoint "Play Round" (POST round/user/{userId}). Este endpoint mostrará os dois filmes aleatórios, onde usuário poderá escolher qual filme acha que é o mais bem avaliado.

Após verificar os filmes da rodada, o usuário deve enviar sua resposta no endpoint "Send you answer" (POST round/user/{userId}/answer?answer=<filme_escolhido>), onde será necessário enviar via parâmetro sua resposta.

Como resposta, irá ser apresentado se o usuário acertou, ou se errou. Caso tenha acertado, ele poderá voltar para o endpoint de rodada para a próxima rodada. Caso tenha errado, é contabilizado um erro na partida.

Caso o usuário tenha mais de três erros na partida, a partida é encerrada. O usuário também pode encerrar a qualquer momento a partida pelo endpoint "Finish Match" (match/user/{userId}/finish).

Na Collection de Match, também há um endpoint chamado "Ranking", onde é apresentado uma lista dos usuários que obtiveram mais sucesso em uma partida.