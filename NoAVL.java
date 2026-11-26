import java.util.LinkedList;


public class NoAVL {
    private String nome;
    private LinkedList<Emprestimo> lista;
    private NoAVL esq, dir;
    private int fatorBalanceamento;

    public NoAVL(Emprestimo e) {
        this.nome = e.getNomeUsuario();
        this.lista = new LinkedList<Emprestimo>();
        this.lista.add(e);
        this.fatorBalanceamento = 0;
    }

    public String getNome() { return nome; }
    public LinkedList<Emprestimo> getLista() { return lista; }
    public NoAVL getEsq() { return esq; }
    public void setEsq(NoAVL esq) { this.esq = esq; }
    public NoAVL getDir() { return dir; }
    public void setDir(NoAVL dir) { this.dir = dir; }
    public int getFatorBalanceamento() { return fatorBalanceamento; }
    public void setFatorBalanceamento(int fb) { this.fatorBalanceamento = fb; }
}