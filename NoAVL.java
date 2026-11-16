import java.util.ArrayList;

public class NoAVL {
    String nome;
    ArrayList<Emprestimo> lista;
    NoAVL esq;
    NoAVL dir;
    int altura;                           // altura em niveis: folha = 1

    NoAVL(Emprestimo e) {
        this.nome = e.getNomeUsuario();
        this.lista = new ArrayList<Emprestimo>();
        this.lista.add(e);
        this.altura = 1;
    }
}
