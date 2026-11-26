import java.util.LinkedList;

public class AVL implements BuscaPorNome {

    private NoAVL raiz;
    private boolean balancear;

    public AVL() {
        this.raiz = null;
        this.balancear = false;
    }

    public void inserir(Emprestimo e) {
        this.raiz = this.inserir(e, this.raiz);
    }

    private NoAVL inserir(Emprestimo e, NoAVL no) {
        if (no == null) {
            NoAVL novo = new NoAVL(e);
            this.balancear = true;
            return novo;
        }
        int c = e.getNomeUsuario().compareTo(no.getNome());
        if (c == 0) {            
            no.getLista().add(e);
            this.balancear = false;
            return no;
        } else if (c < 0) {          
            no.setEsq(this.inserir(e, no.getEsq()));
            no = this.balancearDir(no);
            return no;
        } else {
            no.setDir(this.inserir(e, no.getDir()));
            no = this.balancearEsq(no);
            return no;
        }
    }

    public void finalizarCarga() {
    }

    public LinkedList<Emprestimo> buscar(String nome) {
        NoAVL no = this.pesquisar(nome, this.raiz);
        if (no == null) {
            return null;
        }
        return no.getLista();
    }

    private NoAVL pesquisar(String nome, NoAVL no) {
        if (no == null) {
            return null;
        }
        int c = nome.compareTo(no.getNome());
        if (c == 0) {
            return no;
        }
        if (c < 0) {
            return this.pesquisar(nome, no.getEsq());
        }
        return this.pesquisar(nome, no.getDir());
    }

    private NoAVL balancearDir(NoAVL no) {
        if (this.balancear)
            switch (no.getFatorBalanceamento()) {
                case 1:
                    no.setFatorBalanceamento(0);
                    this.balancear = false;
                    break;
                case 0:
                    no.setFatorBalanceamento(-1);
                    break;
                case -1:
                    no = this.rotacaoDireita(no);
            }
        return no;
    }

    private NoAVL balancearEsq(NoAVL no) {
        if (this.balancear)
            switch (no.getFatorBalanceamento()) {
                case -1:
                    no.setFatorBalanceamento(0);
                    this.balancear = false;
                    break;
                case 0:
                    no.setFatorBalanceamento(1);
                    break;
                case 1:
                    no = this.rotacaoEsquerda(no);
            }
        return no;
    }

    private NoAVL rotacaoDireita(NoAVL no) {
        NoAVL temp1, temp2;
        temp1 = no.getEsq();
        if (temp1.getFatorBalanceamento() == -1) {
            no.setEsq(temp1.getDir());
            temp1.setDir(no);
            no.setFatorBalanceamento(0);
            no = temp1;
        } else {
            temp2 = temp1.getDir();
            temp1.setDir(temp2.getEsq());
            temp2.setEsq(temp1);
            no.setEsq(temp2.getDir());
            temp2.setDir(no);
            
            if (temp2.getFatorBalanceamento() == -1)
                no.setFatorBalanceamento(1);
            else
                no.setFatorBalanceamento(0);
            if (temp2.getFatorBalanceamento() == 1)
                temp1.setFatorBalanceamento(-1);
            else
                temp1.setFatorBalanceamento(0);
            no = temp2;
        }
        no.setFatorBalanceamento(0);
        this.balancear = false;
        return no;
    }

    // Rotacao a esquerda (RE) e rotacao dupla a esquerda (RDE)
    private NoAVL rotacaoEsquerda(NoAVL no) {
        NoAVL temp1, temp2;
        temp1 = no.getDir();
        if (temp1.getFatorBalanceamento() == 1) {
            no.setDir(temp1.getEsq());
            temp1.setEsq(no);
            no.setFatorBalanceamento(0);
            no = temp1;
        } else {
            temp2 = temp1.getEsq();
            temp1.setEsq(temp2.getDir());
            temp2.setDir(temp1);
            no.setDir(temp2.getEsq());
            temp2.setEsq(no);
            if (temp2.getFatorBalanceamento() == 1)
                no.setFatorBalanceamento(-1);
            else
                no.setFatorBalanceamento(0);
            if (temp2.getFatorBalanceamento() == -1)
                temp1.setFatorBalanceamento(1);
            else
                temp1.setFatorBalanceamento(0);
            no = temp2;
        }
        no.setFatorBalanceamento(0);
        this.balancear = false;
        return no;
    }
}