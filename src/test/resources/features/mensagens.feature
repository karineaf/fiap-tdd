# language: pt
Funcionalidade: API REST - Mensagens

  Cenario: Registrar uma nova mensagem
    Quando submeter uma nova mensagem
    Entao a mensagem é registrada com sucesso

    Cenario: Buscar uma mensagem existente
      Dado que uma mensagem já foi registrada
      Quando buscar a mensagem registada
      Entao a mensagem é exibida com sucesso

    Cenario: Remover mensagem existente
      Dado que uma mensagem já foi registrada
      Quando requisitar a remoção da mensagem
      Entao a mensagem é removida com sucesso
