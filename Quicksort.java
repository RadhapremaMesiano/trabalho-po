import java.util.ArrayList;

public class Quicksort {

    public static void ordenar(ArrayList<Emprestimo> v) {
        ordenar(v, 0, v.size() - 1);
    }

    private static void ordenar(ArrayList<Emprestimo> v, int esq, int dir) {
        int i = esq;
        int j = dir;
        Emprestimo pivo = v.get((esq + dir) / 2);   // pivo = elemento do meio

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

    private static void trocar(ArrayList<Emprestimo> v, int a, int b) {
        Emprestimo aux = v.get(a);
        v.set(a, v.get(b));
        v.set(b, aux);
    }
}
