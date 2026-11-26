import java.util.ArrayList;

public class QuicksortInsercao {

    private static final int LIMITE = 20;

    public static void ordenar(ArrayList<Emprestimo> v) {
        if (v.size() > 1) {
            ordena(v, 0, v.size() - 1);
        }
    }

    private static void ordena(ArrayList<Emprestimo> v, int esq, int dir) {
        if (dir - esq + 1 <= LIMITE) {
            insertionSort(v, esq, dir);
            return;
        }
        int i = esq, j = dir;
        Emprestimo pivo, temp;

        pivo = v.get((i + j) / 2);
        do {
            while (v.get(i).comparar(pivo) < 0)
                i++;
            while (v.get(j).comparar(pivo) > 0)
                j--;
            if (i <= j) {
                temp = v.get(i);
                v.set(i, v.get(j));
                v.set(j, temp);
                i++;
                j--;
            }
        } while (i <= j);
        if (esq < j)
            ordena(v, esq, j);
        if (dir > i)
            ordena(v, i, dir);
    }

    private static void insertionSort(ArrayList<Emprestimo> v, int esq, int dir) {
        int i, j;
        Emprestimo temp;

        for (i = esq + 1; i <= dir; i++) {
            temp = v.get(i);
            j = i - 1;
            while ((j >= esq) && (v.get(j).comparar(temp) > 0)) {
                v.set(j + 1, v.get(j));
                j--;
            }
            v.set(j + 1, temp);
        }
    }
}