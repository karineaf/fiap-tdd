Para gerar reports do allure (testes)

1. instale um instalador
```
   Set-ExecutionPolicy RemoteSigned -scope CurrentUser
   irm get.scoop.sh | iex
```

2. instale o allure
```
scoop install allure
```
3. execute o teste
```
mvn test
```

4. execute o allure
```
allure serve ./target/allure-results/              
```

