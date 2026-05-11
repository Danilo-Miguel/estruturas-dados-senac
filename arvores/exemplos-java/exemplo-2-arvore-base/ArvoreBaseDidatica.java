import java.util.LinkedList;
import java.util.Queue;

/**
 * Base didatica para introduzir o conceito de arvore antes de entrar em arvore
 * binaria.
 *
 * A ideia deste arquivo e ser lido em sala passo a passo:
 * - primeiro a classe da arvore;
 * - depois a classe do no;
 * - depois a montagem da estrutura;
 * - por fim, a leitura dos conceitos.
 *
 * O parametro <T> significa que esta classe nao fica presa a um tipo unico de
 * dado. Ela pode trabalhar com String, Integer, objetos proprios e outros
 * tipos, sem precisar reescrever a estrutura da arvore.
 *
 * Responsabilidade desta classe:
 * - guardar a referencia da raiz;
 * - oferecer operacoes comuns da arvore;
 * - servir de base para classes concretas de demonstracao.
 */
public abstract class ArvoreBaseDidatica<T> {

    /**
     * Raiz da arvore.
     *
     * A raiz e o primeiro no da estrutura. A partir dela, todo o resto da
     * arvore e alcançado.
     */
    private NoArvore<T> raiz;

    /**
     * Metodo responsavel por criar a raiz da arvore.
     *
     * O desenho da estrutura sempre comeca por aqui.
     *
     * @param valor valor que sera guardado na raiz
     */
    protected void criarRaiz(T valor) {
        // Se ja existe raiz, nao permitimos criar outra para nao quebrar a estrutura.
        if (raiz != null) {
            throw new IllegalStateException("A raiz ja foi criada.");
        }

        // Aqui a instancia da raiz e criada de fato.
        raiz = new NoArvore<>(valor);
    }

    /**
     * Metodo responsavel por definir uma raiz ja criada em outro lugar.
     *
     * Isso ajuda a deixar o conceito de objeto mais visivel na aula.
     *
     * @param novaRaiz objeto que sera usado como raiz
     */
    protected void definirRaiz(NoArvore<T> novaRaiz) {
        // Apenas aponta a arvore para a raiz recebida.
        raiz = novaRaiz;
    }

    /**
     * Metodo responsavel por retornar a raiz atual da arvore.
     *
     * @return raiz da arvore ou null se ainda nao existir
     */
    protected NoArvore<T> getRaiz() {
        return raiz;
    }

    /**
     * Metodo responsavel por criar um no filho e ligar esse no ao pai informado.
     *
     * @param pai no que recebera o novo filho
     * @param valor valor do novo filho
     * @return no criado e conectado ao pai
     */
    protected NoArvore<T> adicionarFilho(NoArvore<T> pai, T valor) {
        // Sem no pai, nao existe onde conectar o novo no.
        if (pai == null) {
            throw new IllegalArgumentException("O no pai nao pode ser nulo.");
        }

        // Cria a instancia do filho.
        NoArvore<T> novoFilho = new NoArvore<>(valor);

        // Conecta o filho ao pai (e registra o pai dentro do filho).
        pai.adicionarFilho(novoFilho);

        // Retorna o objeto criado para permitir uso imediato na montagem da arvore.
        return novoFilho;
    }

    /**
     * Metodo responsavel por verificar se um no e folha.
     *
     * @param no no que sera analisado
     * @return true quando o no nao tem filhos
     */
    protected boolean eFolha(NoArvore<T> no) {
        // Se o no nem existe, por convencao didatica retornamos false.
        if (no == null) {
            return false;
        }

        // Delega para a regra do proprio no: folha = lista de filhos vazia.
        return no.eFolha();
    }

    /**
     * Metodo responsavel por contar todos os nos da arvore inteira.
     *
     * @return quantidade total de nos
     */
    protected int contarNos() {
        // Reaproveita a versao recursiva iniciando pela raiz.
        return contarNos(raiz);
    }

    /**
     * Metodo responsavel por contar quantos nos existem em uma subarvore.
     *
     * @param no no de inicio da contagem
     * @return quantidade de nos da subarvore
     */
    protected int contarNos(NoArvore<T> no) {
        // Subarvore vazia possui 0 nos.
        if (no == null) {
            return 0;
        }

        // Conta o proprio no atual.
        int total = 1;

        // Soma os nos de cada filho recursivamente.
        for (NoArvore<T> filho : no.getFilhos()) {
            total += contarNos(filho);
        }

        // Retorna a soma final.
        return total;
    }

    /**
     * Metodo responsavel por contar quantas folhas existem na arvore inteira.
     *
     * @return quantidade de folhas
     */
    protected int contarFolhas() {
        // Reaproveita a versao recursiva iniciando pela raiz.
        return contarFolhas(raiz);
    }

    /**
     * Metodo responsavel por contar quantas folhas existem em uma subarvore.
     *
     * @param no no de inicio da contagem
     * @return quantidade de folhas da subarvore
     */
    protected int contarFolhas(NoArvore<T> no) {
        // Subarvore vazia nao possui folhas.
        if (no == null) {
            return 0;
        }

        // Se o no atual e folha, conta 1 e encerra este ramo.
        if (no.eFolha()) {
            return 1;
        }

        // Acumulador da quantidade de folhas dos filhos.
        int totalFolhas = 0;

        // Soma recursivamente as folhas de cada filho.
        for (NoArvore<T> filho : no.getFilhos()) {
            totalFolhas += contarFolhas(filho);
        }

        // Retorna o total de folhas encontradas nesta subarvore.
        return totalFolhas;
    }

    /**
     * Metodo responsavel por calcular a altura da arvore completa.
     *
     * Aqui usamos a definicao por arestas:
     * - arvore vazia = -1
     * - folha = 0
     *
     * Importante para a aula:
     * - estamos contando arestas, nao quantidade de nos;
     * - por isso folha vale 0 (nao existe aresta abaixo da folha);
     * - cada nivel para baixo soma +1 aresta.
     *
     * @return altura da arvore
     */
    protected int calcularAltura() {
        // Inicia o calculo pela raiz da arvore inteira.
        // A regra detalhada esta no metodo recursivo abaixo.
        return calcularAltura(raiz);
    }

    /**
     * Metodo responsavel por calcular a altura de uma subarvore.
     *
     * @param no no de inicio
     * @return altura da subarvore
     */
    protected int calcularAltura(NoArvore<T> no) {
        // PASSO 1: se o no nao existe, nao existe caminho para baixo.
        // Pela convencao de arestas, retornamos -1.
        if (no == null) {
            return -1;
        }

        // PASSO 2: se o no e folha, a altura e 0.
        // Motivo: folha nao possui arestas abaixo dela.
        if (no.eFolha()) {
            return 0;
        }

        // PASSO 3: vamos descobrir qual filho tem o caminho mais profundo.
        // Comecamos com -1 para garantir que o primeiro filho atualize o valor.
        int maiorAlturaEntreFilhos = -1;

        // PASSO 4: percorre todos os filhos do no atual com for-each.
        // Leitura didatica da linha abaixo:
        // - NoArvore<T> = tipo de cada elemento da lista;
        // - filho = variavel que recebe um elemento por vez;
        // - : significa "em" (para cada filho EM no.getFilhos());
        // - no.getFilhos() = lista de filhos que sera percorrida.
        for (NoArvore<T> filho : no.getFilhos()) {
            // PASSO 4.1: calcula a altura do ramo que comeca neste filho.
            int alturaDoFilho = calcularAltura(filho);

            // PASSO 5: compara a altura atual com a maior altura ja encontrada.
            // Se a altura atual for maior, atualizamos o "maior".
            if (alturaDoFilho > maiorAlturaEntreFilhos) {
                // PASSO 5.1: guarda o novo maior valor.
                maiorAlturaEntreFilhos = alturaDoFilho;
            }
        }

        // PASSO 6: soma 1 aresta (do no atual para o filho escolhido).
        // Exemplo rapido:
        // se o melhor filho tem altura 2, o no atual tera altura 3.
        return 1 + maiorAlturaEntreFilhos;
    }

    /**
     * Metodo responsavel por calcular a profundidade de um no a partir da raiz.
     *
     * A raiz tem profundidade 0.
     *
     * Importante para a aula:
     * - estamos contando arestas entre o no e a raiz;
     * - por isso a raiz vale 0;
     * - cada "subida" para o pai soma +1.
     *
     * @param no no que sera avaliado
     * @return profundidade do no
     */
    protected int calcularProfundidade(NoArvore<T> no) {
        // PASSO 1: se o no nao existe, retornamos -1 como "nao encontrado".
        if (no == null) {
            return -1;
        }

        // PASSO 2: profundidade comeca em 0.
        // Se o proprio no for a raiz, esse valor 0 sera o resultado final.
        int profundidade = 0;

        // PASSO 3: usamos a variavel "atual" para caminhar no ramo.
        // Comecamos no proprio no recebido.
        NoArvore<T> atual = no;

        // PASSO 4: sobe no caminho ate chegar na raiz.
        // Regra do while:
        // - enquanto existir pai, ainda nao chegamos na raiz.
        while (atual.getPai() != null) {
            // PASSO 4.1: subiu uma aresta (do no atual para o pai), soma 1.
            profundidade++;

            // PASSO 4.2: move o ponteiro para o pai e repete o processo.
            atual = atual.getPai();
        }

        // PASSO 5: ao sair do while, atual esta na raiz.
        // O valor acumulado em "profundidade" e o resultado.
        return profundidade;
    }
                // Mantido por compatibilidade com a versao anterior da aula.
                // Aqui ele apenas chama a busca em profundidade.
                return buscarEmProfundidade(valor);
     * Metodo responsavel por exibir a arvore inteira de forma visual.
     */
    protected void exibirArvore() {
        // Inicia exibicao pela raiz no nivel 0.
        exibirArvore(raiz, 0);
    }

    /**
     * Metodo responsavel por exibir uma subarvore com recuo por nivel.
            protected NoArvore<T> buscarPorValor(NoArvore<T> no, T valor) {
                // Mantem o nome antigo, mas reaproveita a busca em profundidade.
                return buscarEmProfundidade(no, valor);
            }

            /**
             * Metodo responsavel por buscar um valor em profundidade.
             *
             * Busca em profundidade significa que seguimos um ramo ate o fim antes de
             * tentar o proximo ramo.
             *
             * @param valor valor procurado
             * @return no encontrado ou null
             */
            protected NoArvore<T> buscarEmProfundidade(T valor) {
                // Comeca a busca pela raiz da arvore.
                return buscarEmProfundidade(raiz, valor);
            }

            /**
             * Metodo responsavel por buscar recursivamente em profundidade dentro de
             * uma subarvore.
             *
             * @param no no atual
             * @param valor valor procurado
             * @return no encontrado ou null
             */
            protected NoArvore<T> buscarEmProfundidade(NoArvore<T> no, T valor) {
                // PASSO 1: se o no atual nao existe, este caminho terminou sem sucesso.
     * @param no no de inicio
     * @param nivel nivel atual de recuo
     */
    protected void exibirArvore(NoArvore<T> no, int nivel) {
        // Quando o no e nulo, nao ha nada para imprimir.
                // PASSO 2: se o valor esta no no atual, a busca acabou com sucesso.
        if (no == null) {
            return;
        }

        // Imprime dois espacos por nivel para representar a hierarquia.
                // PASSO 3: percorre os filhos um por um.
        for (int i = 0; i < nivel; i++) {
            System.out.print("  ");
                    // PASSO 3.1: tenta encontrar o valor dentro deste ramo.
        }

                    // PASSO 3.2: se encontrou em algum ramo, encerra a busca.
        // Imprime o valor do no atual.
        System.out.println("- " + no.getValor());

        // Exibe cada filho em um nivel abaixo.
        for (NoArvore<T> filho : no.getFilhos()) {
            exibirArvore(filho, nivel + 1);
                // PASSO 4: se nenhum ramo encontrou, devolve null.
        }
    }


            /**
             * Metodo responsavel por buscar um valor em largura.
             *
             * Busca em largura significa que primeiro olhamos a raiz, depois todos os
             * nos do nivel seguinte, e assim por diante.
             *
             * @param valor valor procurado
             * @return no encontrado ou null
             */
            protected NoArvore<T> buscarEmLargura(T valor) {
                // PASSO 1: se nao existe raiz, a arvore esta vazia.
                if (raiz == null) {
                    return null;
                }

                // PASSO 2: cria uma fila para controlar a ordem de visita.
                Queue<NoArvore<T>> fila = new LinkedList<>();

                // PASSO 3: coloca a raiz na fila como primeiro elemento a visitar.
                fila.add(raiz);

                // PASSO 4: enquanto houver nos na fila, continua a busca.
                while (!fila.isEmpty()) {
                    // PASSO 4.1: remove o primeiro no da fila para analisar agora.
                    NoArvore<T> atual = fila.remove();

                    // PASSO 4.2: se o valor foi encontrado, devolve o no imediatamente.
                    if (atual.getValor().equals(valor)) {
                        return atual;
                    }

                    // PASSO 4.3: adiciona os filhos do no atual no fim da fila.
                    // Assim, os proximos a serem analisados sao os nos do nivel seguinte.
                    for (NoArvore<T> filho : atual.getFilhos()) {
                        fila.add(filho);
                    }
                }

                // PASSO 5: se a fila acabou, o valor nao existe na arvore.
                return null;
            }
    /**
     * Metodo responsavel por exibir um resumo dos principais indicadores.
     */
    protected void exibirResumo() {
        // Exibe a raiz (ou informa que ainda nao existe).
        System.out.println("Raiz: " + (raiz == null ? "sem raiz" : raiz.getValor()));

        // Exibe total de nos, total de folhas e altura.
        System.out.println("Quantidade total de nos: " + contarNos());
        System.out.println("Quantidade de folhas: " + contarFolhas());
        System.out.println("Altura da arvore: " + calcularAltura());
    }

    /**
     * Metodo abstrato responsavel pela demonstracao concreta da arvore.
     *
     * A montagem real da arvore fica na classe concreta, para manter esta base
     * focada em estrutura e comportamento comum.
     */
    public abstract void demonstrar();

    /**
     * Metodo responsavel por acionar a demonstracao da classe filha.
     */
    public void executarDemonstracao() {
        demonstrar();
    }

    /**
     * Metodo responsavel por iniciar a busca de um no pelo valor na arvore toda.
     *
     * Esta busca e feita por profundidade e serve como base para os proximos
     * exemplos.
     *
     * @param valor valor procurado
     * @return no encontrado ou null
     */
    protected NoArvore<T> buscarPorValor(T valor) {
        // Inicia busca pela raiz.
        return buscarPorValor(raiz, valor);
    }

    /**
     * Metodo responsavel por buscar recursivamente um valor em uma subarvore.
     *
     * @param no no atual
     * @param valor valor procurado
     * @return no encontrado ou null
     */
    protected NoArvore<T> buscarPorValor(NoArvore<T> no, T valor) {
        // Se chegou em no nulo, este caminho nao contem o valor.
        if (no == null) {
            return null;
        }

        // Se encontrou o valor no no atual, retorna imediatamente.
        if (no.getValor().equals(valor)) {
            return no;
        }

        // Procura recursivamente em cada filho.
        for (NoArvore<T> filho : no.getFilhos()) {
            NoArvore<T> encontrado = buscarPorValor(filho, valor);

            // Se encontrou em algum ramo, encerra a busca devolvendo o resultado.
            if (encontrado != null) {
                return encontrado;
            }
        }

        // Nenhum ramo continha o valor procurado.
        return null;
    }
}
