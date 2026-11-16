import java.util.ArrayList;

public class QuicksortInsercao {

    private static final int LIMITE = 20;   // trechos com 20 elementos ou menos usam insercao direta

    public static void ordenar(ArrayList<Emprestimo> v) {
        ordenar(v, 0, v.size() - 1);
    }

    private static void ordenar(ArrayList<Emprestimo> v, int esq, int dir) {
        if (dir - esq + 1 <= LIMITE) {
            insercaoDireta(v, esq, dir);
            return;
        }
        int i = esq;
        int j = dir;
        Emprestimo pivo = v.get((esq + dir) / 2);

        while (i <= j) {
            while (v.get(i).comparar(pivo) < 0) {
                i++;
            }
            while (v.get(j).comparar(pivo) > 0) {
                j--;
            }
            if (i <= j) {
                trocar(v, i, j);
                i++;
                j--;
            }
        }
        if (esq < j) {
            ordenar(v, esq, j);
        }
        if (i < dir) {
            ordenar(v, i, dir);
        }
    }

    // Insercao direta apenas no trecho [esq, dir]
    private static void insercaoDireta(ArrayList<Emprestimo> v, int esq, int dir) {
        for (int i = esq + 1; i <= dir; i++) {
            Emprestimo x = v.get(i);
            int j = i - 1;
            while (j >= esq && v.get(j).comparar(x) > 0) {
                v.set(j + 1, v.get(j));
                j--;
            }
            v.set(j + 1, x);
        }
    }

    private static void trocar(ArrayList<Emprestimo> v, int a, int b) {
        Emprestimo aux = v.get(a);
        v.set(a, v.get(b));
        v.set(b, aux);
    }
}
