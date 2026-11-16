import java.util.ArrayList;

public class AVL implements BuscaPorNome {

    private NoAVL raiz;

    public void inserir(Emprestimo e) {
        raiz = inserir(raiz, e);
    }

    private NoAVL inserir(NoAVL no, Emprestimo e) {
        if (no == null) {
            return new NoAVL(e);
        }
        int c = e.getNomeUsuario().compareTo(no.nome);
        if (c == 0) {
            no.lista.add(e);                       // mesmo nome: so entra na lista, a arvore nao muda
            return no;
        } else if (c < 0) {
            no.esq = inserir(no.esq, e);
        } else {
            no.dir = inserir(no.dir, e);
        }
        atualizarAltura(no);
        return balancear(no);
    }

    public void finalizarCarga() {
        // a AVL ja esta balanceada a cada insercao
    }

    private int altura(NoAVL no) {
        return no == null ? 0 : no.altura;
    }

    private void atualizarAltura(NoAVL no) {
        no.altura = 1 + Math.max(altura(no.esq), altura(no.dir));
    }

    // FB = altura(direita) - altura(esquerda)
    private int fb(NoAVL no) {
        return altura(no.dir) - altura(no.esq);
    }

    private NoAVL balancear(NoAVL no) {
        int fb = fb(no);
        if (fb == 2) {                             // pesado a direita
            if (fb(no.dir) < 0) {
                no.dir = rotacaoDireita(no.dir);   // rotacao dupla
            }
            return rotacaoEsquerda(no);
        }
        if (fb == -2) {                            // pesado a esquerda
            if (fb(no.esq) > 0) {
                no.esq = rotacaoEsquerda(no.esq);  // rotacao dupla
            }
            return rotacaoDireita(no);
        }
        return no;
    }

    private NoAVL rotacaoEsquerda(NoAVL p) {
        NoAVL q = p.dir;
        p.dir = q.esq;
        q.esq = p;
        atualizarAltura(p);
        atualizarAltura(q);
        return q;
    }

    private NoAVL rotacaoDireita(NoAVL p) {
        NoAVL q = p.esq;
        p.esq = q.dir;
        q.dir = p;
        atualizarAltura(p);
        atualizarAltura(q);
        return q;
    }

    public ArrayList<Emprestimo> buscar(String nome) {
        NoAVL no = raiz;
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
