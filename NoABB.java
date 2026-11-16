import java.util.ArrayList;

public class NoABB {
    String nome;                          // chave secundaria
    ArrayList<Emprestimo> lista;          // todos os emprestimos desse nome
    NoABB esq;
    NoABB dir;

    NoABB(Emprestimo e) {
        this.nome = e.getNomeUsuario();
        this.lista = new ArrayList<Emprestimo>();
        this.lista.add(e);
    }
}
