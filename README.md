# 🐾 Sistema de Atendimento de Clínica Veterinária

Um sistema desenvolvido em Java para gerenciar o fluxo de atendimentos em uma clínica veterinária. O projeto foi arquitetado com foco na flexibilidade, escalabilidade e no baixo acoplamento, utilizando **Princípios S.O.L.I.D.** e **Padrões de Projeto (Design Patterns)** para permitir a fácil adição de novas regras de negócio sem a necessidade de modificar o código já consolidado.

## 👥 Dupla de Desenvolvimento

- **Igor Gabriel Rodrigues**
- **Pedro José Guimarães Coelho**

---

## 🛠️ Padrões de Projeto Utilizados

A arquitetura do sistema foi desenhada em torno de três padrões de projeto principais (GoF) e um padrão auxiliar de construção:

1. **State Pattern:**
   - **Objetivo:** Gerenciar o ciclo de vida do atendimento (`Agendado`, `Em Atendimento`, `Finalizado` e `Cancelado`).
   - **Vantagem:** Elimina estruturas condicionais complexas (`if/switch`) dentro da classe de `Atendimento`, isolando as regras de transição (ex: impedir que um atendimento finalizado seja cancelado).

2. **Observer Pattern:**
   - **Objetivo:** Estabelecer uma mensageria interna para notificar setores e pessoas interessadas (`Tutor`, `Veterinário`, `Recepção`, `Controle de Medicação`, `Faturamento`, `Auditoria`).
   - **Vantagem:** Desacopla a classe principal dos setores da clínica. A adição de um novo interessado nos eventos do atendimento exige impacto zero na entidade `Atendimento`.

3. **Decorator Pattern:**
   - **Objetivo:** Compor dinamicamente o preço final do serviço prestado com diferentes variáveis (`Taxa Domiciliar`, `Banho Pós-Consulta`, `Desconto Animal Adotado`, `Convênio ONG`, `Adicional Noturno`).
   - **Vantagem:** Evita a explosão de subclasses e permite o empilhamento ilimitado e sequencial de regras matemáticas e descrições de serviços.

4. **Builder Pattern (Bônus de Refatoração):**
   - **Objetivo:** Criar o objeto `Atendimento` de forma fluente, validando a obrigatoriedade dos dados e facilitando a injeção dos Decorators e Observers.

---

## ⚙️ Principais Funcionalidades

- Controle rigoroso da máquina de estados do atendimento.
- Geração de histórico e log de auditoria automático para rastreabilidade de ações.
- Envio de notificações simuladas para preparar medicações e emitir notas fiscais conforme os gatilhos de status.
- Composição combinada de múltiplos serviços em uma única cobrança.
- Suíte de testes automatizados validando todas as transições de negócio.

---

## 📊 Diagrama de Classes

Abaixo, a representação estrutural da aplicação, detalhando a comunicação entre a entidade núcleo, os padrões comportamentais e os padrões estruturais.

<div align="center">
  <img width="100%" alt="Diagrama de Classes da Clínica Veterinária" src="https://github.com/user-attachments/assets/b9898525-5345-4513-b5f5-719255477f28" />
</div>

---

## 💻 Tecnologias e Testes

- **Linguagem:** Java 17+
- **Testes:** JUnit 5 (Testes de unidade cobrindo o fluxo de criação, transições de estado, cálculos matemáticos do Decorator e disparo de mensagens do Observer).

### Como executar os testes

1. Clone o repositório.
2. Abra o projeto em sua IDE de preferência (Eclipse, IntelliJ, VS Code).
3. Execute o arquivo `ClinicaVeterinariaAvancadaDemo.java` para visualizar o fluxo completo no console.
4. Execute a classe `ClinicaVeterinariaTest.java` para rodar as validações unitárias.
