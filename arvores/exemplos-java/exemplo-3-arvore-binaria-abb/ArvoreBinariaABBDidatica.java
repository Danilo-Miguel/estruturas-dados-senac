/**
 * Base didatica para uma arvore binaria de busca.
 *
 * O objetivo aqui e mostrar a estrutura e a busca em ABB com bastante clareza:
 * - no separado da arvore;
 * - raiz da estrutura;
 * - insercao obedecendo esquerda/direita;
 * - busca eficiente aproveitando a ordenacao.
 */
public abstract class ArvoreBinariaABBDidatica<T extends Comparable<T>> {

    /**
     * Raiz da arvore binaria.
     *
     * A raiz e o ponto de partida para qualquer operacao na arvore.
     */
    private NoBinario<T> raiz;

    /**
     * Metodo responsavel por criar a raiz da arvore.
     *
     * @param valor valor que sera guardado na raiz
     */
    protected void criarRaiz(T valor) {
        // Se ja existe raiz, nao criamos outra.
        if (raiz != null) {
            throw new IllegalStateException("A raiz ja foi criada.");
        }

        // Aqui a instancia da raiz e criada de fato.
        raiz = new NoBinario<>(valor);
    }

    /**
     * Metodo responsavel por retornar a raiz.
     *
     * @return raiz atual da arvore
     */
    protected NoBinario<T> getRaiz() {
        return raiz;
    }

    /**
     * Metodo responsavel por inserir um valor seguindo a regra da ABB.
     *
     * @param valor valor que sera inserido
     */
    protected void inserirABB(T valor) {
        // Se a arvore esta vazia, o novo valor vira a raiz.
        if (raiz == null) {
            raiz = new NoBinario<>(valor);
            return;
        }

        // Se ja existe raiz, usamos a insercao recursiva.
        inserirABB(raiz, valor);
    }

    /**
     * Metodo responsavel por inserir recursivamente em uma subarvore.
     *
     * @param atual no atual da busca de insercao
     * @param valor valor que sera inserido
     */
    protected void inserirABB(NoBinario<T> atual, T valor) {
        // Compara o valor novo com o valor do no atual.
        int comparacao = valor.compareTo(atual.getValor());

        // Se o novo valor for menor, vai para a esquerda.
        if (comparacao < 0) {
            if (atual.getEsquerda() == null) {
                atual.setEsquerda(new NoBinario<>(valor));
                return;
            }
            inserirABB(atual.getEsquerda(), valor);
            return;
        }

        // Se o novo valor for maior, vai para a direita.
        if (comparacao > 0) {
            if (atual.getDireita() == null) {
                atual.setDireita(new NoBinario<>(valor));
                return;
            }
            inserirABB(atual.getDireita(), valor);
        }
    }

    /**
     * Metodo responsavel por buscar um valor na ABB.
     *
     * A eficiencia vem daqui:
     * - se o valor procurado for menor, nao precisamos olhar a direita;
     * - se o valor procurado for maior, nao precisamos olhar a esquerda.
     *
     * Isso reduz bastante a quantidade de comparacoes quando a arvore esta
     * organizada.
     *
     * @param valor valor procurado
     * @return no encontrado ou null
     */
    protected NoBinario<T> buscarABB(T valor) {
        // Comeca a busca pela raiz.
        return buscarABB(raiz, valor);
    }

    /**
     * Metodo responsavel por buscar recursivamente em uma ABB.
     *
     * @param atual no atual
     * @param valor valor procurado
     * @return no encontrado ou null
     */
    protected NoBinario<T> buscarABB(NoBinario<T> atual, T valor) {
        // PASSO 1: se o no atual nao existe, a busca terminou sem sucesso.
        if (atual == null) {
            return null;
        }

        // PASSO 2: mostra ao aluno qual no esta sendo visitado.
        System.out.println("Visitando ABB: " + atual.getValor());

        // PASSO 3: compara o valor procurado com o valor do no atual.
        int comparacao = valor.compareTo(atual.getValor());

        // PASSO 4: se forem iguais, encontramos o valor.
        if (comparacao == 0) {
            return atual;
        }

        // PASSO 5: se o valor for menor, procura apenas na esquerda.
        if (comparacao < 0) {
            return buscarABB(atual.getEsquerda(), valor);
        }

        // PASSO 6: se o valor for maior, procura apenas na direita.
        return buscarABB(atual.getDireita(), valor);
    }

    /**
     * Metodo responsavel por contar todos os nos da ABB.
     *
     * Isso ajuda a mostrar, na aula, que a busca pode visitar apenas uma parte
     * pequena da arvore, e nao a estrutura inteira.
     *
     * @return quantidade total de nos
     */
    protected int contarNos() {
        return contarNos(raiz);
    }

    /**
     * Metodo responsavel por contar nos em uma subarvore.
     *
     * @param atual no atual
     * @return quantidade de nos da subarvore
     */
    protected int contarNos(NoBinario<T> atual) {
        if (atual == null) {
            return 0;
        }

        int total = 1;
        total += contarNos(atual.getEsquerda());
        total += contarNos(atual.getDireita());
        return total;
    }

    /**
     * Exibe a arvore em ordem crescente.
     *
     * @return texto com os valores em ordem
     */
    protected String exibirEmOrdem() {
        StringBuilder texto = new StringBuilder();
        exibirEmOrdem(raiz, texto);
        return texto.toString().trim();
    }

    /**
     * Exibe uma subarvore em ordem crescente.
     *
     * @param atual no atual
     * @param texto acumulador de texto
     */
    protected void exibirEmOrdem(NoBinario<T> atual, StringBuilder texto) {
        if (atual == null) {
            return;
        }

        exibirEmOrdem(atual.getEsquerda(), texto);
        texto.append(atual.getValor()).append(' ');
        exibirEmOrdem(atual.getDireita(), texto);
    }

    /**
     * Metodo abstrato responsavel pela demonstracao concreta da ABB.
     */
    public abstract void demonstrar();
}
