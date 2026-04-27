# Arvores: Conceitos Basicos

## O que e uma arvore

Em estruturas de dados, uma arvore e uma forma de organizar informacoes em niveis.

Em vez de colocar tudo em uma unica sequencia, como acontece em um vetor ou em uma lista, a arvore separa os elementos em relacoes de pai e filho.

Uma forma simples de imaginar isso e pensar em um organograma:

- existe um elemento principal no topo;
- abaixo dele aparecem outros elementos ligados;
- cada elemento pode se dividir em novos elementos.

## Vamos construir a ideia em pedaços

### Pedaço 1: a raiz

A arvore sempre comeca por um primeiro no.

Esse no inicial recebe o nome de **raiz**.

No exemplo desta pasta, a raiz sera:

```text
Empresa
```

### Pedaço 2: os filhos da raiz

Depois da raiz, ligamos novos nos abaixo dela.

No exemplo:

```text
Empresa
|- Financeiro
|- Vendas
\- Suporte
```

Agora ja temos uma estrutura em niveis.

### Pedaço 3: expandindo a arvore

Depois, alguns desses nos recebem filhos proprios.

No exemplo completo:

```text
Empresa
|- Financeiro
|  |- Contas
|  \- Cobranca
|- Vendas
|  |- Loja Fisica
|  \- Loja Online
\- Suporte
```

Perceba que a construcao foi acontecendo aos poucos:

1. primeiro a raiz;
2. depois os filhos da raiz;
3. depois os filhos de alguns desses filhos.

## Terminologia principal

### Raiz

A **raiz** e o no inicial da arvore.

Ela fica no topo e serve como ponto de partida para chegar aos demais nos.

No exemplo, a raiz e `Empresa`.

### No

Um **no** e cada elemento da arvore.

Entao, no exemplo, `Empresa`, `Financeiro`, `Vendas`, `Suporte`, `Contas`, `Cobranca`, `Loja Fisica` e `Loja Online` sao nos.

### Folha

Uma **folha** e um no que nao possui filhos.

No exemplo, sao folhas:

- `Contas`;
- `Cobranca`;
- `Loja Fisica`;
- `Loja Online`;
- `Suporte`.

Esses nos encerram o caminho naquele ramo.

### Profundidade

A **profundidade** mede a distancia da raiz ate um no.

Aqui estamos contando a profundidade pelo numero de arestas.

No exemplo:

- profundidade de `Empresa`: `0`;
- profundidade de `Vendas`: `1`;
- profundidade de `Loja Online`: `2`.

### Altura

A **altura** mede o caminho mais longo da raiz ate uma folha.

No exemplo, um dos maiores caminhos e:

```text
Empresa -> Vendas -> Loja Online
```

Esse caminho possui `2` arestas. Logo, a altura da arvore e `2`.

## O que o codigo faz

### 1. Cria a classe `No`

Cada no guarda duas coisas simples:

- um nome;
- uma lista de filhos.

Essa escolha e proposital para manter o foco nos conceitos mais basicos de arvore.

### 2. Monta a arvore por etapas

O `main` foi escrito como uma construcao passo a passo:

```java
No raiz = new No("Empresa");

No financeiro = new No("Financeiro");
No vendas = new No("Vendas");
No suporte = new No("Suporte");

raiz.adicionarFilho(financeiro);
raiz.adicionarFilho(vendas);
raiz.adicionarFilho(suporte);
```

Depois disso, surgem os filhos dos setores:

```java
No contas = new No("Contas");
No cobranca = new No("Cobranca");
No lojaFisica = new No("Loja Fisica");
No lojaOnline = new No("Loja Online");
```

E por fim esses nos sao ligados aos seus pais.

### 3. Identifica folhas

O metodo `ehFolha` verifica se a lista de filhos esta vazia.

Se estiver vazia, significa que o no nao se divide mais.

### 4. Calcula altura

O metodo `altura` procura o ramo mais profundo da arvore.

Ele verifica a altura de cada filho e fica com a maior.

### 5. Calcula profundidade

O metodo `profundidade` procura um nome dentro da arvore.

Cada vez que ele desce um nivel, soma `1`.

Quando encontra o no procurado, devolve esse total.

## Resumo didatico

Para fixar:

- raiz e o primeiro no;
- no e cada elemento da estrutura;
- folha e o no sem filhos;
- profundidade mede o quanto um no esta distante da raiz;
- altura mede o maior caminho da raiz ate uma folha.

So depois de essa base ficar clara faz sentido entrar em tipos mais especificos, como arvore binaria.