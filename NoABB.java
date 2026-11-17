import java.util.LinkedList;

// No da ABB. Igual ao NoABB do slide da professora (num, esq, dir), exceto: a chave e o
// nome do usuario (String) e o no guarda a lista de emprestimos desse nome.
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