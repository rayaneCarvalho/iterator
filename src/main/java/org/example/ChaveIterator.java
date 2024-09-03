package org.example;

import java.util.Iterator;
import java.util.List;

class ChaveIterator<K, V> implements Iterator<K> {
    private int indiceTabela = 0;
    private Iterator<DadosEntrada<K, V>> iteradorLista;
    private final List<DadosEntrada<K, V>>[] tabela;

    public ChaveIterator(List<DadosEntrada<K, V>>[] tabela) {
        this.tabela = tabela;
        this.iteradorLista = tabela[indiceTabela].iterator();
        moverParaProximaLista();
    }

    private void moverParaProximaLista() {
        while (!iteradorLista.hasNext() && indiceTabela < tabela.length - 1) {
            indiceTabela++;
            iteradorLista = tabela[indiceTabela].iterator();
        }
    }

    @Override
    public boolean hasNext() {
        return iteradorLista.hasNext();
    }

    @Override
    public K next() {
        K chave = iteradorLista.next().getChave();
        moverParaProximaLista();
        return chave;
    }
}

