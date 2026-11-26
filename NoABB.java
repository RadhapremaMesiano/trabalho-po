import java.util.LinkedList;

public class NoABB {
    private String nome;
    private LinkedList<Emprestimo> lista;
    private NoABB esq, dir;

    public NoABB(Emprestimo e) {
        this.nome = e.getNomeUsuario();
        this.lista = new LinkedList<Emprestimo>();
        this.lista.add(e);
    }

    public String getNome() { return nome; }
    public LinkedList<Emprestimo> getLista() { return lista; }
    public NoABB getEsq() { return esq; }
    public void setEsq(NoABB esq) { this.esq = esq; }
    public NoABB getDir() { return dir; }
    public void setDir(NoABB dir) { this.dir = dir; }
}