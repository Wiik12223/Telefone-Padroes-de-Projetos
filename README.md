# Telefone-Padroes-de-Projetos
Atividade do professor Guilherme

## Prompt utilizado — Questão Telefone

```text
Você é um professor/mentor de Java especializado em padrões de projeto.

Leia integralmente o arquivo “Lista Avaliativa I.pdf” e use-o como fonte principal da atividade. Depois, inspecione os arquivos existentes em:

- src/telephone

Não comece codificando. Primeiro produza um tutorial detalhado explicando exatamente como realizar a atividade “Telefone”, respeitando fielmente todos os requisitos do PDF.

Identifique o padrão de projeto solicitado, explique o papel de cada classe e compare o estado atual do código com o resultado esperado.

O tutorial deve explicar:

1. Como definir a interface dos observadores.
2. Como notificar os observadores sempre que um novo dígito for inserido.
3. Como fazer a classe Screen criar os dois observadores exigidos.
4. Como o primeiro observador deve imprimir o dígito mais recente.
5. Como o segundo observador deve imprimir o número completo no formato solicitado.
6. Como manter o telefone desacoplado da interface gráfica.
7. Como garantir que somente a UI seja responsável por imprimir na tela.
8. Quais arquivos deverão ser modificados ou criados.
9. Como compilar e executar a atividade.
10. Como validar a saída do programa.

Regras obrigatórias:

- Não escreva nem altere código nesta resposta.
- Não invente requisitos que não estejam no PDF.
- Diferencie requisitos obrigatórios, decisões de implementação e sugestões.
- Preserve a estrutura e os nomes das classes existentes, salvo se o PDF exigir mudança.
- Caso exista alguma divergência entre o PDF e o código, apresente a divergência e recomende a correção.
- Ao final, aguarde autorização antes de gerar qualquer código.
```

## Análise da questão — Telefone

A questão Telefone exige a aplicação do padrão Observer.

### Requisitos da atividade

O programa deve:

- definir uma interface para os observadores;
- notificar os observadores sempre que um novo dígito for inserido no número de telefone;
- fazer a classe `Screen` criar dois observadores;
- fazer o primeiro observador imprimir o dígito mais recente;
- fazer o segundo observador imprimir `Agora discando 081999887766...`, substituindo o número pelo número armazenado no modelo;
- manter o telefone desacoplado da UI;
- garantir que somente a UI seja responsável por imprimir na tela.

### Situação atual

`PhoneModel` já armazena os dígitos e possui os métodos `addDigit` e `getDigits`.

`KeyPad` já simula a inserção de dígitos e envia cada novo dígito para o modelo.

`Screen` recebe o modelo no construtor, mas ainda não cria nem registra observadores.

`PhoneModel` ainda não possui uma lista de observadores nem realiza notificações.

`KeyPad` atualmente imprime diretamente a mensagem de pressionamento, o que deve ser avaliado de acordo com a restrição do PDF de que somente a UI pode imprimir na tela.

### Plano de implementação

#### 1. Criar a interface dos observadores

Criar uma interface para representar os observadores do telefone. Ela deverá definir um método chamado pelo modelo quando um novo dígito for inserido.

O método deverá receber informações suficientes para que a UI possa exibir o dígito recém-inserido e o número atualizado.

#### 2. Registrar observadores no modelo

Adicionar ao `PhoneModel` uma coleção de observadores e um método de registro.

O modelo deverá armazenar os observadores sem conhecer a implementação da `Screen` ou de qualquer outra UI.

#### 3. Notificar após inserir um dígito

O método `addDigit` deverá:

1. adicionar o novo dígito ao número;
2. notificar todos os observadores registrados;
3. permitir que cada observador reaja ao número atualizado.

A notificação deve ocorrer depois que o dígito estiver armazenado, para que a UI consiga consultar o estado atual do telefone.

#### 4. Criar os observadores na `Screen`

A `Screen` deverá registrar dois observadores no modelo:

- um observador que imprime o dígito mais recente;
- um observador que imprime o número completo no formato `Agora discando <número>...`.

As regras de apresentação devem permanecer na `Screen`, e não no `PhoneModel` ou no `KeyPad`.

### Fluxo esperado

```text
Main
 ├── cria PhoneModel
 ├── cria Screen e registra os observadores
 ├── cria KeyPad
 └── inicia a simulação das teclas

KeyPad
 └── envia um novo dígito para PhoneModel

PhoneModel
 ├── armazena o dígito
 └── notifica todos os observadores

Screen
 ├── imprime o dígito mais recente
 └── imprime o número completo atualizado
```

### Arquivos envolvidos

Serão necessários:

- `src/telephone/PhoneModel.java`;
- `src/telephone/Screen.java`;
- `src/telephone/KeyPad.java`, caso seja necessário remover a impressão que pertence à UI;
- `src/telephone/Main.java`;
- um novo arquivo de interface para os observadores.

### Checklist

- [x] Interface dos observadores criada.
- [x] `PhoneModel` armazena os observadores.
- [x] `PhoneModel` possui método para registrar observadores.
- [x] `PhoneModel` notifica após inserir cada dígito.
- [x] `PhoneModel` não conhece a UI.
- [x] `Screen` cria dois observadores.
- [x] Primeiro observador imprime o dígito mais recente.
- [x] Segundo observador imprime o número completo atualizado.
- [x] Impressões ficam concentradas na UI.
- [x] `KeyPad` permanece responsável apenas pela entrada dos dígitos.
- [x] `KeyPad` gera somente dígitos telefônicos válidos de `0` a `9`.
- [x] Saída validada com a execução do programa.

## Como executar

### Pré-requisito

É necessário ter um JDK instalado, pois o comando `javac` será usado para compilar os arquivos Java.

Verifique a instalação com:

```powershell
java -version
javac -version
```

### Compilação

Execute os comandos a partir da raiz deste repositório:

```powershell
New-Item -ItemType Directory -Force out
javac -d out src\telephone\*.java
```

### Execução

Ainda a partir da raiz do repositório:

```powershell
java -cp out Main
```

Para salvar a saída em um arquivo e também exibi-la no terminal:

```powershell
java -cp out Main | Tee-Object saida.txt
```
