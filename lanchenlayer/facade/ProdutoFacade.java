package com.lanchenlayer.facade;

import java.util.ArrayList;

import com.lanchenlayer.applications.ProdutoApplication;
import com.lanchenlayer.entities.Produto;

public class ProdutoFacade {
    private ProdutoApplication produtoApplication;

    public ProdutoFacade(ProdutoApplication produtoApplication) {
        this.produtoApplication = produtoApplication;
    }

    public void adicionar(Produto produto) {
        this.produtoApplication.adicionar(produto);
    }

    public void remover(int id) {
        this.produtoApplication.remover(id);
    }

    public Produto buscarPorId(int id) {
        return this.produtoApplication.buscarPorId(id);
    }

    public ArrayList<Produto> buscarTodos() {
        return this.produtoApplication.buscarTodos();
    }

    public boolean vender(int idVender) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
