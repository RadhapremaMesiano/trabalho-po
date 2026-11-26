import java.util.ArrayList;
import java.util.LinkedList;

public class ABB implements BuscaPorNome {

    private NoABB raiz;
    private int quant;

    public ABB() {
        this.raiz = null;
        this.quant = 0;
    }

    public int getQuant() {
        return this.quant;
    }

    public NoABB pesquisar(String nome) {
        return pesquisar(nome, this.raiz);
    }

    private NoABB pesquisar(String nome, NoABB no) {
        if (no == null) {
            return null;
        }
        int c = nome.compareTo(no.getNome());
        if (c == 0) {
            return no;
        }
        if (c < 0) {
            return pesquisar(nome, no.getEsq());
        }
        return pesquisar(nome, no.getDir());
    }

    public void inserir(Emprestimo e) {
        NoABB existente = this.pesquisar(e.getNomeUsuario());
        if (existente != null) {
            existente.getLista().add(e);      // nome repetido: guarda na lista do no
        } else {
            this.raiz = this.inserir(e, this.raiz);
            this.quant++;
        }
    }

    private NoABB inserir(Emprestimo e, NoABB no) {
        if (no == null) {
            NoABB novo = new NoABB(e);
            return novo;
        }
        int c = e.getNomeUsuario().compareTo(no.getNome());
        if (c > 0) {
            no.setDir(inserir(e, no.getDir()));
        } else if (c < 0) {
            no.setEsq(inserir(e, no.getEsq()));
        }
        return no;
    }

    public ArrayList<NoABB> camCentral() {
        ArrayList<NoABB> vetor = new ArrayList<NoABB>();
        return (camCentral(this.raiz, vetor));
    }

    private ArrayList<NoABB> camCentral(NoABB no, ArrayList<NoABB> vetor) {
        if (no != null) {
            vetor = this.camCentral(no.getEsq(), vetor);
            vetor.add(no);
            vetor = this.camCentral(no.getDir(), vetor);
        }
        return vetor;
    }

    public ABB balancear() {
        ABB aux = new ABB();
        ArrayList<NoABB> vetor = camCentral();
        balancear(vetor, aux, 0, vetor.size() - 1);
        return aux;
    }

    private void balancear(ArrayList<NoABB> vetor, ABB aux, int inicio, int fim) {
        int meio;
        if (inicio <= fim) {
            meio = (inicio + fim) / 2;
            for (Emprestimo e : vetor.get(meio).getLista()) {
                aux.inserir(e);
            }
            // balancear a parte esquerda do vetor
            balancear(vetor, aux, inicio, meio - 1);
            // balancear a parte direita do vetor
            balancear(vetor, aux, meio + 1, fim);
        }
    }

    public void finalizarCarga() {
        ABB balanceada = this.balancear();
        this.raiz = balanceada.raiz;
        this.quant = balanceada.quant;
    }

    public LinkedList<Emprestimo> buscar(String nome) {
        NoABB no = this.pesquisar(nome);
        if (no == null) {
            return null;
        }
        return no.getLista();
    }
}