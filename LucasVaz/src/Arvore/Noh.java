package Arvore;

public class Noh {
    private int valor;
    private Noh esquerda;
    private Noh direita;

    public Noh(int valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Noh getEsquerda() {
        return esquerda;
    }

    public Noh getDireita() {
        return direita;
    }

    public void setEsquerda(Noh esquerda) {
        this.esquerda = esquerda;
    }

    public void setDireita(Noh direita) {
        this.direita = direita;
    }
}
