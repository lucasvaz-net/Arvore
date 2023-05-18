package Arvore;

import java.util.ArrayList;
import java.util.List;

public class ArvoreBinaria {
    private Noh raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public Noh getRaiz() {
        return raiz;
    }

    public void setRaiz(Noh raiz) {
        this.raiz = raiz;
    }

    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }

    private Noh inserirRecursivo(Noh noh, int valor) {
        if (noh == null) {
            return new Noh(valor);
        }

        if (valor < noh.getValor()) {
            noh.setEsquerda(inserirRecursivo(noh.getEsquerda(), valor));
        } else if (valor > noh.getValor()) {
            noh.setDireita(inserirRecursivo(noh.getDireita(), valor));
        }

        return noh;
    }

    public void remover(int valor) {
        raiz = removerRecursivo(raiz, valor);
    }

    private Noh removerRecursivo(Noh noh, int valor) {
        if (noh == null) {
            return null;
        }

        if (valor < noh.getValor()) {
            noh.setEsquerda(removerRecursivo(noh.getEsquerda(), valor));
        } else if (valor > noh.getValor()) {
            noh.setDireita(removerRecursivo(noh.getDireita(), valor));
        } else {
            if (noh.getEsquerda() == null && noh.getDireita() == null) {
                return null;
            } else if (noh.getEsquerda() == null) {
                return noh.getDireita();
            } else if (noh.getDireita() == null) {
                return noh.getEsquerda();
            } else {
                int menorValor = encontrarMenorValor(noh.getDireita());
                noh.setValor(menorValor);
                noh.setDireita(removerRecursivo(noh.getDireita(), menorValor));
            }
        }

        return noh;
    }

    private int encontrarMenorValor(Noh noh) {
        int menorValor = noh.getValor();
        while (noh.getEsquerda() != null) {
            menorValor = noh.getEsquerda().getValor();
            noh = noh.getEsquerda();
        }
        return menorValor;
    }

    public List<Integer> preOrdem() {
        List<Integer> travessia = new ArrayList<>();
        preOrdemRecursivo(raiz, travessia);
        return travessia;
    }

    private void preOrdemRecursivo(Noh noh, List<Integer> travessia) {
        if (noh != null) {
            travessia.add(noh.getValor());
            preOrdemRecursivo(noh.getEsquerda(), travessia);
            preOrdemRecursivo(noh.getDireita(), travessia);
        }
    }

    public List<Integer> emOrdem() {
        List<Integer> travessia = new ArrayList<>();
        emOrdemRecursivo(raiz, travessia);
        return travessia;
    }

    private void emOrdemRecursivo(Noh noh, List<Integer> travessia) {
        if (noh != null) {
            emOrdemRecursivo(noh.getEsquerda(), travessia);
            travessia.add(noh.getValor());
            emOrdemRecursivo(noh.getDireita(), travessia);
        }
    }

    public List<Integer> posOrdem() {
        List<Integer> travessia = new ArrayList<>();
        posOrdemRecursivo(raiz, travessia);
        return travessia;
    }

    private void posOrdemRecursivo(Noh noh, List<Integer> travessia) {
        if (noh != null) {
            posOrdemRecursivo(noh.getEsquerda(), travessia);
            posOrdemRecursivo(noh.getDireita(), travessia);
            travessia.add(noh.getValor());
        }
    }
}
