import java.util.ArrayList;
import java.util.List;

/**
 * Representa um no de arvore de forma bem simples e direta.
 *
 * Esta classe fica separada para deixar claro o conceito de objeto:
 * primeiro criamos o no, depois ligamos esse no a outros nos.
 *
 * O <T> significa "tipo generico". Isso quer dizer que este no pode guardar
 * qualquer tipo de dado: String, Integer, Double, ou outro objeto.
 */
public class NoArvore<T> {

    // Valor guardado dentro deste no.
    private T valor;

    // Referencia para o no pai. Se este no for a raiz, o pai fica null.
    private NoArvore<T> pai;

    // Lista com os filhos diretos deste no.
    private List<NoArvore<T>> filhos;

    /**
     * Construtor responsavel por criar um novo no com o valor informado.
     *
     * @param valor dado armazenado no no
     */
    public NoArvore(T valor) {
        // Aqui o objeto NoArvore nasce de fato.
        // O valor recebido vira o conteudo desse no.
        this.valor = valor;
        // O no nasce sem filhos. Eles serao ligados depois.
        this.filhos = new ArrayList<>();
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
     * Retorna o pai deste no.
     *
     * @return pai do no ou null quando este no e a raiz
     */
    public NoArvore<T> getPai() {
        return pai;
    }

    /**
     * Retorna os filhos deste no.
     *
     * @return lista de filhos
     */
    public List<NoArvore<T>> getFilhos() {
        return filhos;
    }

    /**
     * Liga um filho a este no.
     *
     * @param filho no que sera conectado como filho
     */
    public void adicionarFilho(NoArvore<T> filho) {
        // Quando conectamos um filho, tambem registramos quem e o pai dele.
        filho.pai = this;
        filhos.add(filho);
    }

    /**
     * Verifica se este no nao possui filhos.
     *
     * @return true quando o no nao tem filhos; false caso contrario
     */
    public boolean eFolha() {
        // Se a lista de filhos estiver vazia, este no e uma folha.
        return filhos.isEmpty();
    }
}