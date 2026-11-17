import java.util.ArrayList;

// No da AVL. Igual ao NoAvl do slide da professora (num, esq, dir, fatorBalanceamento),
// exceto: a chave e o nome do usuario (String) e o no guarda a lista de emprestimos desse nome.
public class NoAVL {
    private String nome;
    private ArrayList<Emprestimo> lista;
    private NoAVL esq, dir;
    private int fatorBalanceamento;

    public NoAVL(Emprestimo e) {
        this.nome = e.getNomeUsuario();
        this.lista = new ArrayList<Emprestimo>();
        this.lista.add(e);
        this.fatorBalanceamento = 0;
    }

    public String getNome() { return nome; }
    public ArrayList<Emprestimo> getLista() { return lista; }
    public NoAVL getEsq() { return esq; }
    public void setEsq(NoAVL esq) { this.esq = esq; }
    public NoAVL getDir() { return dir; }
    public void setDir(NoAVL dir) { this.dir = dir; }
    public int getFatorBalanceamento() { return fatorBalanceamento; }
    public void setFatorBalanceamento(int fb) { this.fatorBalanceamento = fb; }
}