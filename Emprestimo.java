public class Emprestimo {
    private int codigo;
    private String nomeUsuario;
    private String tituloLivro;
    private String autor;
    private String dataEmprestimo;
    private String dataDevolucao;

    public Emprestimo(int codigo, String nomeUsuario, String tituloLivro,
                      String autor, String dataEmprestimo, String dataDevolucao) {
        this.codigo = codigo;
        this.nomeUsuario = nomeUsuario;
        this.tituloLivro = tituloLivro;
        this.autor = autor;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public int getCodigo() { return codigo; }
    public String getNomeUsuario() { return nomeUsuario; }
    public String getTituloLivro() { return tituloLivro; }
    public String getAutor() { return autor; }
    public String getDataEmprestimo() { return dataEmprestimo; }
    public String getDataDevolucao() { return dataDevolucao; }

    // Ordena pelo nome; se o nome for igual, desempata pelo codigo.
    // Retorna negativo se este < outro, zero se iguais, positivo se este > outro.
    public int comparar(Emprestimo outro) {
        int c = this.nomeUsuario.compareTo(outro.nomeUsuario);
        if (c != 0) {
            return c;
        }
        return Integer.compare(this.codigo, outro.codigo);
    }

    // Linha no formato original do arquivo
    public String paraLinha() {
        return codigo + ";" + nomeUsuario + ";" + tituloLivro + ";" + autor
                + ";" + dataEmprestimo + ";" + dataDevolucao;
    }
}
