package org.example;

class DadosEntrada<K, V> {
    private K chave;
    private V valor;

    public DadosEntrada(K chave, V valor) {
        this.chave = chave;
        this.valor = valor;
    }

    public K getChave() {
        return chave;
    }

    public V getValor() {
        return valor;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }
}

