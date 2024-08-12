package com.lanchenlayer.repositories;

import com.lanchenlayer.entities.Produto;

import java.util.ArrayList;

public class ProdutoRepository {
    private ArrayList<Produto> produtos = new ArrayList<Produto>();

    public void adicionar(Produto produto) {
        produtos.add(produto);
    }

    public void remover(int id) {
        produtos.removeIf(produto -> produto.getId() == id);
    }

    public Produto buscarPorId(int id) {
        return produtos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public ArrayList<Produto> buscarTodos() {
        return produtos;
    }

    public boolean atualizar(int id, String descricao, double preco, String imagem) {

        Produto produto = buscarPorId(id);
        if (produto != null) {

            produto.setDescricao(descricao);
            produto.setPreco(preco);
            produto.setImagem(imagem);
            return true;
        }
        return false;
    }
}
