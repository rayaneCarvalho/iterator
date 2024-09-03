package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChaveIteratorTest {

    private List<DadosEntrada<Chave, String>>[] tabela;

    @BeforeEach
    void setUp() {
        tabela = new LinkedList[3];
        for (int i = 0; i < tabela.length; i++) {
            tabela[i] = new LinkedList<>();
        }

        tabela[0].add(new DadosEntrada<>(new Chave("chave1"), "valor1"));
        tabela[1].add(new DadosEntrada<>(new Chave("chave2"), "valor2"));
        tabela[2].add(new DadosEntrada<>(new Chave("chave3"), "valor3"));
    }

    @Test
    void deveIterarSobreChavesExistentes() {
        ChaveIterator<Chave, String> iterador = new ChaveIterator<>(tabela);
        assertTrue(iterador.hasNext());
        assertEquals("chave1", iterador.next().getValor());
        assertTrue(iterador.hasNext());
        assertEquals("chave2", iterador.next().getValor());
        assertTrue(iterador.hasNext());
        assertEquals("chave3", iterador.next().getValor());
        assertFalse(iterador.hasNext());
    }

    @Test
    void deveRetornarFalsoSeTabelaVazia() {

        List<DadosEntrada<Chave, String>>[] tabelaVazia = new LinkedList[3];
        for (int i = 0; i < tabelaVazia.length; i++) {
            tabelaVazia[i] = new LinkedList<>();
        }

        ChaveIterator<Chave, String> iterador = new ChaveIterator<>(tabelaVazia);
        assertFalse(iterador.hasNext());
    }

    @Test
    void deveIgnorarBucketsVaziosAoIterar() {
        tabela[1] = new LinkedList<>();

        ChaveIterator<Chave, String> iterador = new ChaveIterator<>(tabela);
        assertTrue(iterador.hasNext());
        assertEquals("chave1", iterador.next().getValor());
        assertTrue(iterador.hasNext());
        assertEquals("chave3", iterador.next().getValor());
        assertFalse(iterador.hasNext());
    }

    @Test
    void deveIterarCorretamenteComColisaoNoBucket() {
        tabela[0].add(new DadosEntrada<>(new Chave("chaveExtra"), "valorExtra")); // Colisão no bucket 0

        ChaveIterator<Chave, String> iterador = new ChaveIterator<>(tabela);
        assertTrue(iterador.hasNext());
        assertEquals("chave1", iterador.next().getValor());
        assertTrue(iterador.hasNext());
        assertEquals("chaveExtra", iterador.next().getValor());
        assertTrue(iterador.hasNext());
        assertEquals("chave2", iterador.next().getValor());
        assertTrue(iterador.hasNext());
        assertEquals("chave3", iterador.next().getValor());
        assertFalse(iterador.hasNext());
    }
}
