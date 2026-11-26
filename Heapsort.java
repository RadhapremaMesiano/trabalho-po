import java.util.ArrayList;

public class Heapsort {

    public static void ordenar(ArrayList<Emprestimo> v) {
        int dir = v.size() - 1, esq = (dir - 1) / 2;
        Emprestimo temp;

        while (esq >= 0) {
            refazHeap(v, esq, v.size() - 1);
            esq--;
        }
        while (dir > 0) {        
            temp = v.get(0);
            v.set(0, v.get(dir));
            v.set(dir, temp);
            dir--;
            refazHeap(v, 0, dir);
        }
    }

    private static void refazHeap(ArrayList<Emprestimo> v, int esq, int dir) {
        int i = esq, mF = 2 * i + 1;            
        Emprestimo raiz = v.get(i);
        boolean heap = false;

        while ((mF <= dir) && (!heap)) {
            if (mF < dir)
                if (v.get(mF).comparar(v.get(mF + 1)) < 0)
                    mF++;
            if (raiz.comparar(v.get(mF)) < 0) {
                v.set(i, v.get(mF));
                i = mF;
                mF = 2 * i + 1;
            } else
                heap = true;
        }
        v.set(i, raiz);
    }
}