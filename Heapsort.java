import java.util.ArrayList;

public class Heapsort {

    // Ordena a lista por nome e, no empate, por codigo (usa Emprestimo.comparar).
    public static void ordenar(ArrayList<Emprestimo> v) {
        int n = v.size();

        // Fase 1: transforma o vetor em um heap maximo.
        // Comeca no ultimo pai, (n/2) - 1, e vai ate a raiz.
        for (int i = n / 2 - 1; i >= 0; i--) {
            peneirar(v, i, n);
        }

        // Fase 2: troca a raiz (maior) com o ultimo elemento do heap,
        // diminui o heap em 1 e reorganiza a raiz.
        for (int fim = n - 1; fim > 0; fim--) {
            trocar(v, 0, fim);
            peneirar(v, 0, fim);   // o heap agora tem 'fim' elementos (posicoes 0 a fim-1)
        }
    }

    // Faz o elemento da posicao 'pai' "descer" ate o lugar certo
    // dentro de um heap com 'tamanho' elementos.
    private static void peneirar(ArrayList<Emprestimo> v, int pai, int tamanho) {
        while (true) {
            int esq = 2 * pai + 1;
            int dir = 2 * pai + 2;
            int maior = pai;

            if (esq < tamanho && v.get(esq).comparar(v.get(maior)) > 0) {
                maior = esq;
            }
            if (dir < tamanho && v.get(dir).comparar(v.get(maior)) > 0) {
                maior = dir;
            }
            if (maior == pai) {
                return;           // ja esta no lugar certo
            }
            trocar(v, pai, maior);
            pai = maior;          // continua descendo
        }
    }

    private static void trocar(ArrayList<Emprestimo> v, int a, int b) {
        Emprestimo aux = v.get(a);
        v.set(a, v.get(b));
        v.set(b, aux);
    }
}
