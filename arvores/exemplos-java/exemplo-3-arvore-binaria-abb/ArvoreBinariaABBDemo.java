/**
 * Demonstracao concreta da arvore binaria de busca.
 *
 * Esta classe mostra na pratica como a ABB se comporta:
 * - insercao com regra de menor/esquerda e maior/direita;
 * - busca com menos comparacoes;
 * - ordem final dos elementos.
 */
public class ArvoreBinariaABBDemo extends ArvoreBinariaABBDidatica<Integer> {

    /**
     * Monta a arvore e mostra a busca em ABB.
     */
    @Override
    public void demonstrar() {
        // Criamos os valores da arvore de forma gradual.
        inserirABB(50);
        inserirABB(30);
        inserirABB(70);
        inserirABB(20);
        inserirABB(40);
        inserirABB(60);
        inserirABB(80);

        // Mostra a arvore em ordem para confirmar a organizacao.
        System.out.println("Arvore em ordem crescente: " + exibirEmOrdem());
        System.out.println("Quantidade total de nos na ABB: " + contarNos());
        System.out.println();

        // Busca eficiente: o algoritmo segue apenas o lado necessario.
        // Repare que a busca nao precisa visitar todos os nos da arvore.
        System.out.println("Buscando 60 na ABB...");
        NoBinario<Integer> encontrado60 = buscarABB(60);
        System.out.println("Resultado: " + (encontrado60 != null ? encontrado60.getValor() : "nao encontrado"));
        System.out.println();

        // Outro exemplo para mostrar que a arvore evita caminhos desnecessarios.
        // Aqui a busca desce apenas pelo caminho que faz sentido.
        System.out.println("Buscando 20 na ABB...");
        NoBinario<Integer> encontrado20 = buscarABB(20);
        System.out.println("Resultado: " + (encontrado20 != null ? encontrado20.getValor() : "nao encontrado"));
        System.out.println();

        // Exemplo de busca falha para mostrar a eficiencia da estrutura.
        // Mesmo quando nao encontra, a busca percorre poucos nos comparada a uma varredura completa.
        System.out.println("Buscando 99 na ABB...");
        NoBinario<Integer> encontrado99 = buscarABB(99);
        System.out.println("Resultado: " + (encontrado99 != null ? encontrado99.getValor() : "nao encontrado"));
    }

    /**
     * Ponto de entrada da demonstracao.
     *
     * @param args argumentos da linha de comando
     */
    public static void main(String[] args) {
        new ArvoreBinariaABBDemo().executarDemonstracao();
    }
}
