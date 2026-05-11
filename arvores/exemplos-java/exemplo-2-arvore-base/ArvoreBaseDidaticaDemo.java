/**
 * Demonstracao concreta da base de arvore.
 *
 * Esta classe existe so para montar um exemplo real e mostrar como a base pode
 * ser usada em sala antes de evoluir para arvore binaria.
 */
public class ArvoreBaseDidaticaDemo extends ArvoreBaseDidatica<String> {

    /**
     * Monta uma arvore simples e exibe os conceitos principais.
     */
    @Override
    public void demonstrar() {
        // Etapa 1: criar os objetos explicitamente.
        // O aluno ve aqui que a arvore comeca como objetos separados.
        NoArvore<String> empresa = new NoArvore<>("Empresa");
        definirRaiz(empresa);

        // Etapa 2: criar os filhos diretos da raiz como objetos separados.
        NoArvore<String> financeiro = new NoArvore<>("Financeiro");
        NoArvore<String> vendas = new NoArvore<>("Vendas");
        NoArvore<String> suporte = new NoArvore<>("Suporte");

        // Etapa 3: ligar os objetos a raiz.
        empresa.adicionarFilho(financeiro);
        empresa.adicionarFilho(vendas);
        empresa.adicionarFilho(suporte);

        // Etapa 4: criar os filhos dos setores.
        NoArvore<String> contas = new NoArvore<>("Contas");
        NoArvore<String> cobranca = new NoArvore<>("Cobranca");
        NoArvore<String> lojaFisica = new NoArvore<>("Loja Fisica");
        NoArvore<String> lojaOnline = new NoArvore<>("Loja Online");

        // Etapa 5: conectar os novos objetos aos seus pais.
        financeiro.adicionarFilho(contas);
        financeiro.adicionarFilho(cobranca);
        vendas.adicionarFilho(lojaFisica);
        vendas.adicionarFilho(lojaOnline);

        // Apresenta a estrutura montada.
        System.out.println("Arvore base montada em etapas:\n");
        exibirArvore();

        // Mostra a leitura dos conceitos principais.
        System.out.println();
        exibirResumo();
        System.out.println("Profundidade de Financeiro: " + calcularProfundidade(financeiro));
        System.out.println("Profundidade de Loja Online: " + calcularProfundidade(lojaOnline));
        System.out.println("Altura do no Vendas: " + calcularAltura(vendas));
        System.out.println("Altura do no Financeiro: " + calcularAltura(financeiro));
        System.out.println("Contas e folha? " + eFolha(contas));
        System.out.println("Suporte e folha? " + eFolha(suporte));

        // Busca em profundidade: desce por um ramo ate o fim antes de tentar o proximo.
        System.out.println("Busca em profundidade por Cobranca encontrou? " + (buscarEmProfundidade("Cobranca") != null));

        // Busca em largura: visita primeiro a raiz, depois os nos do mesmo nivel.
        System.out.println("Busca em largura por Cobranca encontrou? " + (buscarEmLargura("Cobranca") != null));
    }

    /**
     * Executa a demonstracao.
     *
     * @param args argumentos da linha de comando
     */
    public static void main(String[] args) {
        new ArvoreBaseDidaticaDemo().executarDemonstracao();
    }
}
