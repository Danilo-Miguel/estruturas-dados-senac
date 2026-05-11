/**
 * Representa um no de arvore binaria de busca.
 *
 * A classe e separada para deixar claro o conceito de objeto:
 * um no guarda um valor e referencia para dois lados:
 * - esquerda;
 * - direita.
 *
 * O tipo <T> precisa ser comparavel porque a ABB precisa decidir se um valor
 * vai para a esquerda ou para a direita.
 */
public class NoBinario<T extends Comparable<T>> {

    // Valor guardado dentro do no.
    private T valor;

    // Referencia para o filho da esquerda.
    private NoBinario<T> esquerda;

    // Referencia para o filho da direita.
    private NoBinario<T> direita;

    /**
     * Construtor responsavel por criar um novo no binario com o valor recebido.
     *
     * @param valor conteudo que sera guardado no no
     */
    public NoBinario(T valor) {
        // Aqui o objeto no nasce de fato.
        this.valor = valor;
    }

    /**
     * Retorna o valor guardado no no.
     *
     * @return valor do no
     */
    public T getValor() {
        return valor;
    }

    /**
     * Retorna o filho da esquerda.
     *
     * @return no da esquerda ou null
     */
    public NoBinario<T> getEsquerda() {
        return esquerda;
    }

    /**
     * Retorna o filho da direita.
     *
     * @return no da direita ou null
     */
    public NoBinario<T> getDireita() {
        return direita;
    }

    /**
     * Define o filho da esquerda.
     *
     * @param esquerda novo filho esquerdo
     */
    public void setEsquerda(NoBinario<T> esquerda) {
        this.esquerda = esquerda;
    }

    /**
     * Define o filho da direita.
     *
     * @param direita novo filho direito
     */
    public void setDireita(NoBinario<T> direita) {
        this.direita = direita;
    }

    /**
     * Verifica se o no nao possui filhos.
     *
     * @return true quando nao ha filho na esquerda nem na direita
     */
    public boolean eFolha() {
        return esquerda == null && direita == null;
    }
}
