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

