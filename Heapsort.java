import java.util.ArrayList;

// Heapsort seguindo o material da professora (slide 1.8 e quadro "Quick e Heap"):
// heapSort() com os dois while e refazHeap(esq, dir), com raiz, mF (maior filho) e a flag heap.
// Adaptacao: ordena objetos Emprestimo em um ArrayList, comparando com comparar()
// (nome e, no empate, codigo), no lugar dos operadores < e > do vetor de inteiros.
public class Heapsort {

    public static void ordenar(ArrayList<Emprestimo> v) {
        int dir = v.size() - 1, esq = (dir - 1) / 2;
        Emprestimo temp;

        // 1o heap: transforma o vetor em heap, do ultimo pai ate a raiz
        while (esq >= 0) {
            refazHeap(v, esq, v.size() - 1);
            esq--;
        }
        while (dir > 0) {
            // troca o 1o com o ultimo que ainda nao esta ordenado
            temp = v.get(0);
            v.set(0, v.get(dir));
            v.set(dir, temp);
            dir--;
            refazHeap(v, 0, dir);
        }
    }

    private static void refazHeap(ArrayList<Emprestimo> v, int esq, int dir) {
        int i = esq, mF = 2 * i + 1;            // mF = maior filho
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