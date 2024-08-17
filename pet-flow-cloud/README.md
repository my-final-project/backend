### Utilizando o Docker via WSL 2 do Windows

```
> wsl
$ sudo services docker start
$ docker compose up -d --build
$ docker ps
```

--- 

### Entidades para o Sistema de Gerenciamento de PetShop

1. Pet: Para armazenar informações sobre os animais de estimação.
2. Serviços: Para gerenciar os serviços oferecidos pelo petshop, como banho, tosa, consultas veterinárias, etc.
3. Agendamento: Para gerenciar os horários e datas dos serviços agendados.
4. Produto: Para gerenciar os produtos vendidos no petshop.
5. Venda: Para registrar as vendas de produtos e serviços.
6. Funcionário: Para gerenciar os funcionários do petshop.
7. Cliente: Para armazenar informações sobre os clientes do petshop.

---

### Segurança dos dados

Proteger uma aplicação Spring envolve várias práticas recomendadas para garantir a segurança dos dados e a integridade
do sistema. Aqui estão algumas das melhores práticas:

### 1. **Autenticação e Autorização**

- **Use Spring Security**: Utilize o Spring Security para gerenciar autenticação e autorização. Configure roles e
  permissões adequadas para diferentes usuários.
- **Autenticação Multifator (MFA)**: Considere implementar MFA para adicionar uma camada extra de segurança.

### 2. **Criptografia**

- **HTTPS**: Sempre use HTTPS para garantir que os dados transmitidos entre o cliente e o servidor sejam criptografados.
- **Criptografia de Dados Sensíveis**: Criptografe dados sensíveis, como senhas, antes de armazená-los no banco de
  dados.

### 3. **Proteção Contra CSRF**

- **CSRF Tokens**: Utilize tokens CSRF para proteger contra ataques de Cross-Site Request Forgery. O Spring Security
  oferece suporte nativo para isso.

### 4. **Configuração de CORS**

- **CORS**: Configure CORS (Cross-Origin Resource Sharing) adequadamente para controlar quais domínios podem acessar sua
  API.

### 5. **Cabeçalhos de Segurança HTTP**

- **Security Headers**: Configure cabeçalhos de segurança HTTP,
  como `Content-Security-Policy`, `X-Content-Type-Options`, `X-Frame-Options`, e `X-XSS-Protection`.

### 6. **Validação de Entrada**

- **Sanitização de Dados**: Sempre valide e sanitize a entrada do usuário para evitar injeção de SQL, XSS e outras
  vulnerabilidades.

### 7. **Gerenciamento de Sessões**

- **Sessões Seguras**: Configure sessões seguras e defina um tempo de expiração adequado para sessões inativas.
- **Controle de Sessões**: Implemente controle de sessões para evitar que múltiplas sessões sejam abertas
  simultaneamente pelo mesmo usuário.

### 8. **Monitoramento e Logs**

- **Monitoramento Contínuo**: Implemente monitoramento contínuo para detectar atividades suspeitas.
- **Logs de Segurança**: Mantenha logs detalhados de eventos de segurança e revise-os regularmente.

### 9. **Atualizações e Patches**

- **Manter Dependências Atualizadas**: Sempre mantenha suas dependências e bibliotecas atualizadas para proteger contra
  vulnerabilidades conhecidas.

### 10. **Práticas de Desenvolvimento Seguro**

- **Revisão de Código**: Realize revisões de código regulares focadas em segurança.
- **Testes de Penetração**: Realize testes de penetração para identificar e corrigir vulnerabilidades.

