# Projeto de Testes - TDD (Test-Driven Development)

Este repositório contém os exercícios e projetos práticos desenvolvidos para a disciplina de **TDD (Test-Driven Development)** do curso de Pós-Graduação da **FIAP**.

---

## 🎯 Objetivo

O objetivo deste projeto é aplicar os conceitos e práticas fundamentais do Desenvolvimento Guiado por Testes (TDD), garantindo cobertura de testes automatizados, qualidade de software e um design de código modular e sustentável.

---

## 🔁 O Ciclo do TDD (Red, Green, Refactor)

A estrutura das atividades e testes segue estritamente os três passos do ciclo TDD:

1. 🔴 **Red:** Escrever um teste automatizado que falha para representar uma nova funcionalidade ou regra de negócio.
2. 🟢 **Green:** Escrever o código mínimo necessário para fazer o teste passar com sucesso.
3. 🔵 **Refactor:** Refatorar e limpar o código (e o teste), garantindo boas práticas de design sem alterar o comportamento esperado.

---

## 🛠️ Tecnologias Utilizadas

*(Ajuste a lista abaixo com a linguagem e framework utilizados no curso)*

- **Linguagem:** Java
- **Framework de Testes:** JUnit 5
- **Ferramentas de Mocks:** Mockito
- **Gerenciador de Dependências:** Maven
- **Versionamento:** Git & GitHub

---

## 📁 Estrutura do Repositório

```text
.
├── src/            # Código-fonte principal da aplicação
│   └── ...
├── tests/          # Casos de teste e suítes TDD (ou src/test/)
│   └── ...
├── .gitignore      # Arquivos ignorados pelo Git
├── README.md       # Documentação do repositório
└── [pom.xml / package.json / requirements.txt]
```

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas:
- [Git](https://git-scm.com/)
- Ambiente de execução da linguagem escolhida ( JDK 17+)

### Passo a Passo

1. **Clonar o repositório:**
   ```bash
   git clone https://github.com/SEU-USUARIO/SEU-REPOSITORIO.git
   cd SEU-REPOSITORIO
   ```

2. **Instalar dependências:**
   ```bash
   # Java
   mvn clean install
   ```



3. **Executar a suíte de testes:**
   ```bash
   # Exemplo para Java (Maven)
   mvn test

   ```

---

## ✒️ Autor

Desenvolvido por **Karine**  
Aluna da Pós-Graduação de Software Architect na **FIAP**.
