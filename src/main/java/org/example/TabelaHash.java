package org.example;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TabelaHash<K, V> implements Iterable<K> {
    private static final int TAMANHO = 10;
    private List<DadosEntrada<K, V>>[] tabela;

    @SuppressWarnings("unchecked")
    public TabelaHash() {
        tabela = new LinkedList[TAMANHO];
        for (int i = 0; i < TAMANHO; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    private int hash(K chave) {
        return Math.abs(chave.hashCode()) % TAMANHO;
    }

    public void colocar(K chave, V valor) {
        int indice = hash(chave);
        for (DadosEntrada<K, V> entrada : tabela[indice]) {
            if (entrada.getChave().equals(chave)) {
                entrada.setValor(valor);
                return;
            }
        }
        tabela[indice].add(new DadosEntrada<>(chave, valor));
    }

    public V obter(K chave) {
        int indice = hash(chave);
        for (DadosEntrada<K, V> entrada : tabela[indice]) {
            if (entrada.getChave().equals(chave)) {
                return entrada.getValor();
            }
        }
        return null;
    }

    public void remover(K chave) {
        int indice = hash(chave);
        tabela[indice].removeIf(entrada -> entrada.getChave().equals(chave));
    }

    public Iterator<K> chaveIterator() {
        return new ChaveIterator<>(tabela);
    }

    @Override
    public Iterator<K> iterator() {
        return chaveIterator();
    }
}
