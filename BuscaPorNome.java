import java.util.LinkedList;

// Contrato comum de ABB, AVL e Hashing: inserir emprestimos e buscar todos de um nome.
public interface BuscaPorNome {
    void inserir(Emprestimo e);
    void finalizarCarga();                         // ABB balanceia aqui; os outros nao fazem nada
    LinkedList<Emprestimo> buscar(String nome);     // null se o nome nao existe
}