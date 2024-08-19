## Boas práticas - Clean Code - SOLID

* Extração de métodos para melhorar a legibilidade e reuso.
* Remoção de comentários redundantes, substituindo-os por nomes de variáveis e métodos autoexplicativos.
* Evitar a duplicação de strings e o uso de literais mágicas.
* Melhoria na clareza dos nomes dos métodos.
* Redução da complexidade no método.
* Inclusão de Logs em Inglês para melhorar o monitoramento e depuração do código.
* Strings repetidas devem ser substituídas por constantes ou métodos
* 

### Resumo das Refatorações:

1. **Extração de Métodos:** Funções complexas foram divididas em métodos menores para facilitar a leitura e manutenção.
2. **Nomenclatura Clara:** Nomes de métodos e variáveis foram escolhidos para serem autoexplicativos, removendo a
   necessidade de comentários.
3. **Tratamento de Exceções:** Melhoramos o tratamento de exceções.
4. **Responsabilidade Única:** Cada método agora realiza uma única tarefa, seguindo o princípio de responsabilidade
   única.
5. **Inclusão de Logs:** Logs em inglês foram adicionados para melhor monitoramento e depuração do código.

---

1. **Single Responsibility Principle (SRP):** Cada classe ou método deve ter uma única responsabilidade, garantindo que
   o código seja fácil de entender e modificar.
2. **Nomes Significativos:** Use nomes claros e descritivos para variáveis, métodos e classes, para que o código seja
   autoexplicativo.
3. **Evitar Duplicação de Código (DRY):** Reutilize código sempre que possível, evitando a repetição e facilitando a
   manutenção.
4. **Injeção de Dependência:** Use injeção de dependência para promover baixo acoplamento e alta testabilidade,
   preferindo abstrações em vez de implementações concretas.
5. **Testes de Unidade:** Escreva testes unitários para garantir a qualidade e a estabilidade do código, facilitando a
   refatoração segura.

---

### **1. Legibilidade**

- **Nomes Significativos:** Use nomes de variáveis, métodos e classes que sejam autoexplicativos e reflitam claramente o
  propósito.
    - *Exemplo:* `getUserById` em vez de `getData`.
- **Métodos Curtos e Simples:** Métodos devem ter uma única responsabilidade, fazer apenas uma coisa e serem curtos,
  geralmente entre 5 a 15 linhas.
- **Consistência:** Mantenha o código consistente em termos de formatação, convenções de nomenclatura e estilo de
  escrita.
- **Comentários Claros e Úteis:** Use comentários para explicar o "porquê", não o "como". Código bem escrito deve ser
  autoexplicativo.

### **2. SOLID**

- **S: Single Responsibility Principle (SRP):** Cada classe ou método deve ter uma única responsabilidade, ou seja,
  fazer apenas uma coisa.
    - *Exemplo:* Uma classe `UserValidator` que apenas valida usuários, sem misturar lógica de persistência ou de
      apresentação.
- **O: Open/Closed Principle (OCP):** Classes devem estar abertas para extensão, mas fechadas para modificação. Use
  herança ou composição para adicionar funcionalidades, sem alterar o código existente.
    - *Exemplo:* Usar interfaces ou classes abstratas para permitir extensibilidade.
- **L: Liskov Substitution Principle (LSP):** Subclasses devem poder substituir suas classes base sem quebrar o código
  cliente.
    - *Exemplo:* Uma classe `Square` que estende `Rectangle` deve manter o comportamento esperado da classe `Rectangle`.
- **I: Interface Segregation Principle (ISP):** Interfaces específicas são melhores que interfaces genéricas. Os
  clientes não devem ser forçados a implementar interfaces que não utilizam.
    - *Exemplo:* Em vez de uma interface `Vehicle` com métodos `drive()` e `fly()`, crie interfaces `Drivable`
      e `Flyable` separadas.
- **D: Dependency Inversion Principle (DIP):** Dependa de abstrações, não de implementações concretas. Use injeção de
  dependência para evitar acoplamento forte.
    - *Exemplo:* Usar interfaces para injeção de dependência em vez de instanciar diretamente as classes.

### **3. Modularidade**

- **Organização de Pacotes:** Estruture os pacotes de maneira que cada pacote tenha uma responsabilidade clara,
  como `controller`, `service`, `repository`, `model`, etc.
- **Encapsulamento:** Mantenha os detalhes de implementação ocultos. Use modificadores de acesso como `private`
  e `protected` para controlar a visibilidade dos membros da classe.
- **Coesão Alta:** Mantenha as classes altamente coesas, com funcionalidades relacionadas agrupadas em uma única classe.
- **Acoplamento Baixo:** Minimize as dependências entre classes, facilitando a alteração de uma classe sem impactar
  outras.

### **4. Manutenibilidade**

- **DRY (Don't Repeat Yourself):** Evite duplicação de código. Refatore para reutilizar métodos e classes comuns.
- **KISS (Keep It Simple, Stupid):** Mantenha o design simples e direto. Evite complicar o código com soluções complexas
  desnecessárias.
- **Refatoração Contínua:** Refaça o código regularmente para melhorar sua estrutura, sem alterar seu comportamento.
- **Teste de Unidade:** Escreva testes unitários para garantir que o código funcione conforme esperado e para facilitar
  a refatoração segura.
- **Tratamento Adequado de Exceções:** Capture e trate exceções de maneira adequada, fornecendo mensagens de erro úteis
  e usando exceções customizadas quando necessário.

### **5. Boas Práticas**

- **Injeção de Dependência:** Use frameworks como Spring para gerenciar dependências e promover um design desacoplado.
- **Programação para Interfaces:** Programe para interfaces ou abstrações em vez de classes concretas para promover
  flexibilidade e testabilidade.
- **Imutabilidade:** Prefira objetos imutáveis, especialmente em ambientes concorrentes, para evitar problemas de
  sincronização.
- **Documentação:** Documente o código usando JavaDoc para classes e métodos públicos. Mantenha a documentação
  atualizada com as mudanças no código.
- **Manutenção de Código Limpo:** Revise o código regularmente (code reviews) e siga as práticas de linters e
  formatadores de código para manter o código limpo e consistente.

### **6. Design Patterns**

- **Uso Adequado de Padrões de Projeto:** Use padrões de projeto como Singleton, Factory, Strategy, etc., quando
  apropriado, para resolver problemas comuns de design.
- **Evitar Overengineering:** Não implemente padrões de projeto ou abstrações complexas desnecessariamente. Mantenha o
  código simples e direto.

### **7. Consistência com a Plataforma**

- **Conformidade com as Convenções da Plataforma:** Siga as convenções e práticas recomendadas da linguagem Java e dos
  frameworks utilizados para garantir a consistência e facilitar a colaboração.

Aplicando esses princípios e práticas, você pode escrever código que não apenas funcione bem, mas também seja fácil de
entender, manter e evoluir ao longo do tempo.