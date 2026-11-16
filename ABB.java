import java.util.ArrayList;

public class ABB implements BuscaPorNome {

    private NoABB raiz;

    public void inserir(Emprestimo e) {
        raiz = inserir(raiz, e);
    }

    private NoABB inserir(NoABB no, Emprestimo e) {
        if (no == null) {
            return new NoABB(e);
        }
        int c = e.getNomeUsuario().compareTo(no.nome);
        if (c == 0) {
            no.lista.add(e);                       // mesmo nome: guarda na lista, nao descarta
        } else if (c < 0) {
            no.esq = inserir(no.esq, e);
        } else {
            no.dir = inserir(no.dir, e);
        }
        return no;
    }

    // Balanceamento: caminhamento central gera o vetor ordenado (VO);
    // o elemento do meio vira a raiz e o processo se repete nas duas metades.
    public void finalizarCarga() {
        ArrayList<NoABB> vo = new ArrayList<NoABB>();
        centralOrdem(raiz, vo);
        raiz = montar(vo, 0, vo.size() - 1);
    }

    private void centralOrdem(NoABB no, ArrayList<NoABB> vo) {
        if (no != null) {
            centralOrdem(no.esq, vo);
            vo.add(no);
            centralOrdem(no.dir, vo);
        }
    }

    private NoABB montar(ArrayList<NoABB> vo, int ini, int fim) {
        if (ini > fim) {
            return null;
        }
        int meio = (ini + fim) / 2;
        NoABB no = vo.get(meio);
        no.esq = montar(vo, ini, meio - 1);
        no.dir = montar(vo, meio + 1, fim);
        return no;
    }

    public ArrayList<Emprestimo> buscar(String nome) {
        NoABB no = raiz;
        while (no != null) {
            int c = nome.compareTo(no.nome);
            if (c == 0) {
                return no.lista;
            } else if (c < 0) {
                no = no.esq;
            } else {
                no = no.dir;
            }
        }
        return null;
    }
}
