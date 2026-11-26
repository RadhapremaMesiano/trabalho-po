import java.util.LinkedList;

public interface BuscaPorNome {
    void inserir(Emprestimo e);
    void finalizarCarga();                         
    LinkedList<Emprestimo> buscar(String nome);     
}