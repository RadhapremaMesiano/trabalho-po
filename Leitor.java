import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Leitor {

    // Le um arquivo de emprestimos e devolve um ArrayList.
    public static ArrayList<Emprestimo> carregar(String caminho) throws IOException {
        ArrayList<Emprestimo> lista = new ArrayList<Emprestimo>();
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha = br.readLine();
        while (linha != null) {
            if (!linha.trim().isEmpty()) {
                String[] c = linha.split(";");
                lista.add(new Emprestimo(Integer.parseInt(c[0].trim()), c[1], c[2], c[3], c[4], c[5]));
            }
            linha = br.readLine();
        }
        br.close();
        return lista;
    }

    // Le o arquivo com os 400 nomes que serao pesquisados (um por linha).
    public static ArrayList<String> carregarNomes(String caminho) throws IOException {
        ArrayList<String> nomes = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha = br.readLine();
        while (linha != null) {
            if (!linha.trim().isEmpty()) {
                nomes.add(linha.trim());
            }
            linha = br.readLine();
        }
        br.close();
        return nomes;
    }
}
