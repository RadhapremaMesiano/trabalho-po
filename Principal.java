import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Principal {

    private static final int[] TAMANHOS = {1000, 5000, 10000, 50000};
    private static final String[] TIPOS = {"alea", "ord", "inv"};
    private static final String[] METODOS = {"Heapsort", "Quicksort", "QuickIns", "ABB", "AVL", "Hashing"};
    private static final int EXECUCOES = 5;

    private static final String PASTA_DADOS = "dados/";
    private static final String PASTA_SAIDA = "saida/";

    public static void main(String[] args) throws IOException {
        ArrayList<String> nomes = Leitor.carregarNomes(PASTA_DADOS + "nomes.txt");

        // tempos[metodo][tamanho][tipo] em milissegundos (media de 5 execucoes)
        double[][][] tempos = new double[METODOS.length][TAMANHOS.length][TIPOS.length];

        for (int t = 0; t < TAMANHOS.length; t++) {
            for (int ti = 0; ti < TIPOS.length; ti++) {
                String sufixo = TAMANHOS[t] + TIPOS[ti];
                String entrada = PASTA_DADOS + "emprestimo" + sufixo + ".txt";

                tempos[0][t][ti] = medirOrdenacao(0, entrada, PASTA_SAIDA + "heap" + sufixo + ".txt");
                tempos[1][t][ti] = medirOrdenacao(1, entrada, PASTA_SAIDA + "quick" + sufixo + ".txt");
                tempos[2][t][ti] = medirOrdenacao(2, entrada, PASTA_SAIDA + "QuickIns" + sufixo + ".txt");
                tempos[3][t][ti] = medirPesquisa(3, entrada, nomes, PASTA_SAIDA + "ABB" + sufixo + ".txt");
                tempos[4][t][ti] = medirPesquisa(4, entrada, nomes, PASTA_SAIDA + "AVL" + sufixo + ".txt");
                tempos[5][t][ti] = medirPesquisa(5, entrada, nomes, PASTA_SAIDA + "Hash" + sufixo + ".txt");

                System.out.println("Concluido: " + sufixo);
            }
        }
        imprimirTabela(tempos);
    }

    // Procedimento do enunciado: 5 vezes { carregar, ordenar, gravar }; tempo = total / 5.
    private static double medirOrdenacao(int metodo, String entrada, String saida) throws IOException {
        long inicio = System.nanoTime();
        for (int vez = 0; vez < EXECUCOES; vez++) {
            ArrayList<Emprestimo> lista = Leitor.carregar(entrada);
            if (metodo == 0) {
                Heapsort.ordenar(lista);
            } else if (metodo == 1) {
                Quicksort.ordenar(lista);
            } else {
                QuicksortInsercao.ordenar(lista);
            }
            Gravador.gravarLista(lista, saida);
        }
        long fim = System.nanoTime();
        return (fim - inicio) / (double) EXECUCOES / 1000000.0;
    }

    // Procedimento do enunciado: 5 vezes { carregar na estrutura, pesquisar os 400 nomes, gravar }.
    private static double medirPesquisa(int metodo, String entrada, ArrayList<String> nomes, String saida) throws IOException {
        long inicio = System.nanoTime();
        for (int vez = 0; vez < EXECUCOES; vez++) {
            ArrayList<Emprestimo> lista = Leitor.carregar(entrada);
            BuscaPorNome estrutura;
            if (metodo == 3) {
                estrutura = new ABB();
            } else if (metodo == 4) {
                estrutura = new AVL();
            } else {
                estrutura = new HashEncadeado(lista.size());
            }
            for (int i = 0; i < lista.size(); i++) {
                estrutura.inserir(lista.get(i));
            }
            estrutura.finalizarCarga();                 // ABB balanceia aqui
            Gravador.gravarPesquisa(estrutura, nomes, saida);
        }
        long fim = System.nanoTime();
        return (fim - inicio) / (double) EXECUCOES / 1000000.0;
    }

    // Mostra na tela e grava saida/tempos.txt (tempo medio em ms).
    private static void imprimirTabela(double[][][] tempos) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(PASTA_SAIDA + "tempos.txt"));
        for (int t = 0; t < TAMANHOS.length; t++) {
            String cab = "Arquivos de " + TAMANHOS[t] + " registros (tempo medio em ms)";
            System.out.println();
            System.out.println(cab);
            bw.newLine();
            bw.write(cab);
            bw.newLine();
            String linha0 = String.format("%-10s %12s %12s %12s", "Metodo", "aleatorio", "ordenado", "invertido");
            System.out.println(linha0);
            bw.write(linha0);
            bw.newLine();
            for (int m = 0; m < METODOS.length; m++) {
                String linha = String.format("%-10s %12.2f %12.2f %12.2f",
                        METODOS[m], tempos[m][t][0], tempos[m][t][1], tempos[m][t][2]);
                System.out.println(linha);
                bw.write(linha);
                bw.newLine();
            }
        }
        bw.close();
    }
}
