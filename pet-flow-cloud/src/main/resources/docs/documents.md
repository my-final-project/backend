## Requisitos

1. Validação do usuário: verifiar se o e-mail e o CPF já estão cadastrados.
2. Verificação da senha: Certificar que a confirmação de senha é igual à senha.
3. Envio de e-mail: Enviar um e-mail de boas-vindas ao usuário após o cadastro.
4. Tratamento de Exceções: Retornar exceções personalizadas em caso de falhas, como senha e ocnfirmação de senha
   diferentes, e-mail ou CPF já cadastrados, e falha no envio do e-mail.

### Exceções personalizadas

| HttpStatus            | Classe                       |
|:----------------------|:-----------------------------|
| CONFLICT              | UserAlreadyExistsException   |
| BAD_REQUEST           | PasswordsDoNotMatchException |
| INTERNAL_SERVER_ERROR | EmailSendingException        |

---

## Boas práticas de Design de Sofware

1. Separar Responsabilidades
    * **Extração de lógica de negócios**: A lógica de autorização (como `getAuthorities`) e detalhes de segurança podem
      ser movidos para uma classe de serviço ou utilitária dedicada.
    * **Reduzir o uso de Lombok**: Embora o Lombok simplifique o código, o uso excessivo pode dificultar a depuração e
      manutenção. Considere usar `@Data` para gerar `@Getter`, `@Setter`, `@ToString`, `@EqualsAndHashCode`
      e `@RequiredArgsConstructor` de forma compacta, mas seja cautelosa com a geração de métodos `equals` e `hashCode`,
      especialmente em entidades JPA.
2. Imutabilidade dos Campos
    * Torne os campos `id`, `email`, `cpf` e outros que não devem ser alterados após a criação do objeto como `final`.
      Isso melhora a segurança e consistência dos objetos.
3. Remover `@Transient` se não for necessário
    * Se o campo `confirmPassword` não for persistido no banco de dados e for usado apenas para validação temporária,
      considere tratá-lo fora da entidade, talvez em um DTO (Data Transfer Object) específico para operações de criação
      ou atualização de usuários.
4. Crair métodos de utilidade para a entidade
    * Em vez de expor diretamente os setters, considere criar métodos de utilidade para operações comuns,
      como `activate()`, `deactivate()`, `addRole(Role role)`, `removeRole(Role role)`. Isso encapsula a lógica de
      modificação e melhora a coesão.
5. Padronizar as Anotações JPA
    * Centralize as configurações JPA em um único local para facilitar a manutenção. Por exemplo, mova as configurações
      de `@JoinTable` para uma configuração separada ou uma constante, se apropriado.
6. Refatorar a Lógica de Autoridades
    * Extraia a lógica de `getAuthorities` para uma classe utilitária ou um serviço. Isso isola a lógica de autorização,
      tornando a entidade mais limpa.
7. Considerar o uso de uma classe base para Auditing
    * Se você usar `@CreatedDate` e `@LastModifiedDate` em várias entidades, considere criar uma classe base `Auditable`
      que contenha essas propriedades e estenda User dessa classe.

---

## Validação dos Campos

* `@NotNull`: Garante que o campo não seja nulo.
* `@NotBlank`: Garante que o campo não seja vazio ou apenas espaços em branco. É uma boa prática usar ambos para campos
  obrigatórios.
* `@Size`: Define o tamanho mínimo e máximo dos campos. As mensagens personalizadas especificam os requisitos exatos
  para os usuários.
* `@Pattern`:
    * **CPF**: O regex `\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}` garante que o CPF esteja no formato `xxx.xxx.xxx-xx`.
    * **Email**: O regex `^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$` garante que a senha
      tenha pelo menos 1 letra maiúscula, 1 letra minúscula, 1 número e 1 caractere especial, além de ter entre 8 e 50
      caracteres.
* `@Email`: Valida se o campo possui um formato de email válido.
* `@CPF`: Essa anotação, parte do Hibernate Validator, garante que o número do CPF é válido. Note que para usar
  o `@CPF`, você precisa adicionar a dependência do Hibernate Validator.

---

## `try-with-resources`

O `try-with-resources` é uma construção introduziada no Java 7 que facilita o gerenciamento de recursos, como arquivos,
conexões de banco de dados, ou qualquer outro recurso que precise ser fechado após o uso. A principal vantagem é que ele
garante que os recursos seja fechados automaticamente ao final do bloco `try`, mesmo que ocorra uma exceção.