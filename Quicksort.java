import java.util.ArrayList;

// Quicksort seguindo o slide 1.7 da professora: quicksort() chama ordena(0, quant-1);
// pivo = elemento do meio; i e j percorrem e trocam ate se cruzarem; depois ordena os dois lados.
// Adaptacao: ordena objetos Emprestimo, comparando com comparar() (nome e, no empate, codigo).
public class Quicksort {

    public static void ordenar(ArrayList<Emprestimo> v) {
        if (v.size() > 1) {
            ordena(v, 0, v.size() - 1);
        }
    }

    private static void ordena(ArrayList<Emprestimo> v, int esq, int dir) {
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
}