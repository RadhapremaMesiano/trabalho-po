import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Gravador {

    // Grava a lista ordenada, uma linha por emprestimo. O FileWriter sobrescreve o arquivo.
    public static void gravarLista(ArrayList<Emprestimo> lista, String caminho) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(caminho));
        for (int i = 0; i < lista.size(); i++) {
            bw.write(lista.get(i).paraLinha());
            bw.newLine();
        }
        bw.close();
    }

    // Grava o resultado da pesquisa dos nomes, no formato pedido no enunciado.
    public static void gravarPesquisa(BuscaPorNome estrutura, ArrayList<String> nomes, String caminho) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(caminho));
        for (int i = 0; i < nomes.size(); i++) {
            String nome = nomes.get(i);
            bw.write("NOME: " + nome);
            bw.newLine();
            ArrayList<Emprestimo> achados = estrutura.buscar(nome);
            if (achados == null) {
                bw.write("NÃO POSSUI EMPRÉSTIMOS");
                bw.newLine();
            } else {
                for (int k = 0; k < achados.size(); k++) {
                    Emprestimo e = achados.get(k);
                    bw.write("Empréstimo: " + e.getCodigo() + " - Livro: " + e.getTituloLivro()
                            + " - Autor: " + e.getAutor());
                    bw.newLine();
                    bw.write("Data do empréstimo: " + e.getDataEmprestimo()
                            + " - Data de devolução: " + e.getDataDevolucao());
                    bw.newLine();
                    bw.newLine();
                }
                bw.write("TOTAL: " + achados.size() + (achados.size() == 1 ? " empréstimo" : " empréstimos"));
                bw.newLine();
            }
            bw.newLine();
        }
        bw.close();
    }
}
