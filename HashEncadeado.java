import java.util.LinkedList;

public class HashEncadeado implements BuscaPorNome {

    private LinkedList<Emprestimo>[] tabela;
    private int m;                             

    @SuppressWarnings("unchecked")
    public HashEncadeado(int quantidadeRegistros) {
        this.m = proximoPrimo((int) (quantidadeRegistros * 1.1));
        this.tabela = new LinkedList[m];
        for (int i = 0; i < m; i++) {
            tabela[i] = new LinkedList<Emprestimo>();
        }
    }

    private int hashing(String chave) {
        char carac;
        int i, soma = 0;
        for (i = 0; i < chave.length(); i++) {
            carac = chave.charAt(i);
            soma += Character.getNumericValue(carac);
        }
        if (soma < 0) {
            soma = -soma;                         
        }
        return soma % m;
    }

    public void inserir(Emprestimo e) {
        tabela[hashing(e.getNomeUsuario())].add(e);
    }

    public void finalizarCarga() {
    }

    public LinkedList<Emprestimo> buscar(String nome) {
        LinkedList<Emprestimo> resultado = new LinkedList<Emprestimo>();
        for (Emprestimo e : tabela[hashing(nome)]) {
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