import java.util.ArrayList;
import java.util.List;

/**
 * Exemplo didatico de arvore generica para iniciantes.
 *
 * O foco e mostrar a estrutura em etapas e trabalhar conceitos basicos:
 * raiz, no, folha, altura e profundidade.
 */
public class ArvoreExemploSimples {

    /**
     * No de uma arvore generica.
     *
     * Cada no guarda um rotulo e uma lista de filhos.
     */
    private static class NoArvore {
        private String rotulo;
        private List<NoArvore> filhos;

        private NoArvore(String rotulo) {
            this.rotulo = rotulo;
            this.filhos = new ArrayList<>();
        }

        private void adicionarFilho(NoArvore filho) {
            filhos.add(filho);
        }

        private String getRotulo() {
            return rotulo;
        }

        private List<NoArvore> getFilhos() {
            return filhos;
        }
    }

    /**
     * No folha e aquele que nao possui filhos.
     */
    public static boolean eFolha(NoArvore no) {
        if (no == null) {
            return false;
        }
        return no.getFilhos().isEmpty();
    }

    /**
     * Conta todos os nos da arvore.
     */
    public static int contarNos(NoArvore raiz) {
        if (raiz == null) {
            return 0;
        }

        int total = 1;
        for (NoArvore filho : raiz.getFilhos()) {
            total += contarNos(filho);
        }
        return total;
    }

    /**
     * Conta quantos nos folha existem na arvore.
     */
    public static int contarFolhas(NoArvore raiz) {
        if (raiz == null) {
            return 0;
        }

        if (eFolha(raiz)) {
            return 1;
        }

        int totalFolhas = 0;
        for (NoArvore filho : raiz.getFilhos()) {
            totalFolhas += contarFolhas(filho);
        }
        return totalFolhas;
    }

    /**
     * Altura = quantidade de arestas no caminho mais longo da raiz ate uma folha.
     */
    public static int calcularAltura(NoArvore raiz) {
        if (raiz == null) {
            return -1;
        }

        if (eFolha(raiz)) {
            return 0;
        }

        int maiorAlturaEntreFilhos = -1;
        for (NoArvore filho : raiz.getFilhos()) {
            int alturaFilhoAtual = calcularAltura(filho);
            if (alturaFilhoAtual > maiorAlturaEntreFilhos) {
                maiorAlturaEntreFilhos = alturaFilhoAtual;
            }
        }
        return 1 + maiorAlturaEntreFilhos;
    }

    /**
     * Profundidade de um no a partir do seu rotulo.
     *
     * A raiz tem profundidade 0.
     */
    public static int calcularProfundidade(NoArvore raiz, String rotuloProcurado) {
        return calcularProfundidadeRecursiva(raiz, rotuloProcurado, 0);
    }

    private static int calcularProfundidadeRecursiva(NoArvore atual, String rotuloProcurado, int nivelAtual) {
        if (atual == null) {
            return -1;
        }

        if (atual.getRotulo().equals(rotuloProcurado)) {
            return nivelAtual;
        }

        for (NoArvore filho : atual.getFilhos()) {
            int resultado = calcularProfundidadeRecursiva(filho, rotuloProcurado, nivelAtual + 1);
            if (resultado != -1) {
                return resultado;
            }
        }
        return -1;
    }

    /**
     * Altura de um no especifico.
     *
     * Se o no nao existir, retorna -1.
     */
    public static int calcularAlturaDeUmNo(NoArvore raiz, String rotuloNo) {
        NoArvore noEncontrado = buscarNoPorRotulo(raiz, rotuloNo);
        if (noEncontrado == null) {
            return -1;
        }
        return calcularAltura(noEncontrado);
    }

    private static NoArvore buscarNoPorRotulo(NoArvore atual, String rotuloProcurado) {
        if (atual == null) {
            return null;
        }

        if (atual.getRotulo().equals(rotuloProcurado)) {
            return atual;
        }

        for (NoArvore filho : atual.getFilhos()) {
            NoArvore encontrado = buscarNoPorRotulo(filho, rotuloProcurado);
            if (encontrado != null) {
                return encontrado;
            }
        }

        return null;
    }

    /**
     * Mostra a arvore com indentacao para facilitar a leitura visual.
     */
    public static void exibirArvore(NoArvore raiz, int nivel) {
        if (raiz == null) {
            return;
        }

        for (int i = 0; i < nivel; i++) {
            System.out.print("  ");
        }

        System.out.println("- " + raiz.getRotulo());

        for (NoArvore filho : raiz.getFilhos()) {
            exibirArvore(filho, nivel + 1);
        }
    }

    /**
     * Monta uma arvore pequena por etapas para demonstrar os conceitos.
     */
    public static void main(String[] args) {
        // Etapa 1: criar a raiz.
        NoArvore raiz = new NoArvore("Empresa");

        // Etapa 2: criar os filhos diretos da raiz.
        NoArvore setorFinanceiro = new NoArvore("Financeiro");
        NoArvore setorVendas = new NoArvore("Vendas");
        NoArvore setorSuporte = new NoArvore("Suporte");

        // Etapa 3: ligar os filhos na raiz.
        raiz.adicionarFilho(setorFinanceiro);
        raiz.adicionarFilho(setorVendas);
        raiz.adicionarFilho(setorSuporte);

        // Etapa 4: criar netos da raiz.
        NoArvore contas = new NoArvore("Contas");
        NoArvore cobranca = new NoArvore("Cobranca");
        NoArvore lojaFisica = new NoArvore("Loja Fisica");
        NoArvore lojaOnline = new NoArvore("Loja Online");

        // Etapa 5: ligar cada no ao seu pai.
        setorFinanceiro.adicionarFilho(contas);
        setorFinanceiro.adicionarFilho(cobranca);
        setorVendas.adicionarFilho(lojaFisica);
        setorVendas.adicionarFilho(lojaOnline);

        // Desenho aproximado da arvore montada:
        //
        // Empresa
        // |- Financeiro
        // |  |- Contas
        // |  \- Cobranca
        // |- Vendas
        // |  |- Loja Fisica
        // |  \- Loja Online
        // \- Suporte

        System.out.println("Arvore montada em etapas:\n");
        exibirArvore(raiz, 0);

        System.out.println("\nRaiz: " + raiz.getRotulo());
        System.out.println("Quantidade total de nos: " + contarNos(raiz));
        System.out.println("Quantidade de folhas: " + contarFolhas(raiz));
        System.out.println("Altura da arvore: " + calcularAltura(raiz));
        System.out.println("Profundidade de Empresa: " + calcularProfundidade(raiz, "Empresa"));
        System.out.println("Profundidade de Vendas: " + calcularProfundidade(raiz, "Vendas"));
        System.out.println("Profundidade de Loja Online: " + calcularProfundidade(raiz, "Loja Online"));
        System.out.println("Altura do no Vendas: " + calcularAlturaDeUmNo(raiz, "Vendas"));
        System.out.println("Altura do no Financeiro: " + calcularAlturaDeUmNo(raiz, "Financeiro"));
        System.out.println("\nContas e folha? " + eFolha(contas));
        System.out.println("Financeiro e folha? " + eFolha(setorFinanceiro));
    }
}