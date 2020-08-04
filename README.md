# marketplace
Projeto Marketplace de produtos

## Testes Unitários
  * Instalar o banco de dados H2 para executar os testes unitários
    * http://www.h2database.com/html/download.html
    
## Passos para geração do Token
  * Usuários
    * Administrador
        * Usuário: admin
        * Senha: 123456
    * Usuário Padrão
        * Usuário: usuario_01
        * Senha: 123456


  1) Para gerar o token ir no http://localhost:8080/auth, passar o usuário e senha acima.
  
  
  * Exemplo
  	
		Required
		{
		  "email": "admin",
		  "senha": "123456"
		}

		Response
		{
		  "data": {
		    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBtYXhtaWxoYXMuY29tLmJyIiwicm9sZSI6IlJPTEVfQURNSU4iLCJjcmVhdGVkIjoxNTc3NDUxMTE0MjkyLCJleHAiOjE1Nzc0NTE3MjB9.lE9mFOtHiomg-kQZxJ8FDDPT_A-YEK1l2ceHt2O28o7Kn933UK-mYEGmEpMG2gbX0XFAUUIT1X0OmHAAN7Jg9Q"
		  },
		  "errors": []
		}

  2) Para utilizar a api do cliente o token deve ser concatenado com a string "Bearer "
  
  * Exemplo

	Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBtYXhtaWxoYXMuY29tLmJyIiwicm9sZSI6IlJPTEVfQURNSU4iLCJjcmVhdGVkIjoxNTc3NDUxMTE0MjkyLCJleHAiOjE1Nzc0NTE3MjB9.lE9mFOtHiomg-kQZxJ8FDDPT_A-YEK1l2ceHt2O28o7Kn933UK-mYEGmEpMG2gbX0XFAUUIT1X0OmHAAN7Jg9Q