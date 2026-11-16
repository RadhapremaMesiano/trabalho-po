import java.util.ArrayList;
import java.util.LinkedList;

public class HashEncadeado implements BuscaPorNome {

    private LinkedList<Emprestimo>[] tabela;
    private int m;                                // tamanho do vetor (numero primo)

    @SuppressWarnings("unchecked")
    public HashEncadeado(int quantidadeRegistros) {
        // vetor encadeado: tamanho ideal ~ N / 0,9, usando o primo mais proximo (acima)
        this.m = proximoPrimo((int) (quantidadeRegistros / 0.9));
        this.tabela = new LinkedList[m];
        for (int i = 0; i < m; i++) {
            tabela[i] = new LinkedList<Emprestimo>();
        }
    }

    // H(nome): converte o texto em numero e tira o resto da divisao por m
    private int hash(String nome) {
        int h = 0;
        for (int i = 0; i < nome.length(); i++) {
            h = (h * 31 + nome.charAt(i)) % m;
        }
        return h;
    }

    public void inserir(Emprestimo e) {
        tabela[hash(e.getNomeUsuario())].add(e);
    }

    public void finalizarCarga() {
    }

    // Percorre a lista da posicao e devolve so os emprestimos com exatamente esse nome.
    public ArrayList<Emprestimo> buscar(String nome) {
        ArrayList<Emprestimo> resultado = new ArrayList<Emprestimo>();
        for (Emprestimo e : tabela[hash(nome)]) {
            if (e.getNomeUsuario().equals(nome)) {
                resultado.add(e);
            }
        }
        return resultado.isEmpty() ? null : resultado;
    }

    private static int proximoPrimo(int n) {
        if (n < 2) {
            n = 2;
        }
        while (!ehPrimo(n)) {
            n++;
        }
        return n;
    }

    private static boolean ehPrimo(int n) {
        if (n < 2) {
            return false;
        }
        for (int d = 2; d * d <= n; d++) {
            if (n % d == 0) {
                return false;
            }
        }
        return true;
    }

    public int getTamanho() {
        return m;
    }
}
