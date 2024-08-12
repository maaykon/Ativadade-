package com.lanchenlayer.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.lanchenlayer.entities.Produto;
import com.lanchenlayer.repositories.ProdutoRepository;

public class ProdutoService {
    private String caminhoDestino = "C:\\Users\\aluno\\LancheNLayer\\src\\main\\resources\\images\\";
    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public static String getFileExtension(String filePath) {
        String fileName = new File(filePath).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "No extension" : fileName.substring(dotIndex + 1);
    }

    public boolean salvarImagem(Produto produto) {
        Path path = Paths.get(produto.getImagem());

        Path pastaDestino = Paths.get(String.format("%s%d.%s", caminhoDestino, produto.getId(), getFileExtension(produto.getImagem())));

        if (Files.exists(path)) {
            try {
                Files.copy(path, pastaDestino, StandardCopyOption.REPLACE_EXISTING);
                produto.setImagem(pastaDestino.getFileName().toString());
                return true;
            } catch (IOException ex) {
                ex.printStackTrace(); // Adiciona log ou tratamento de erro adequado
                return false;
            }
        }

        return false;
    }

    public boolean removerImagem(Produto produto) {
        Path caminhoImagem = Paths.get(caminhoDestino + produto.getImagem());
        try {
            return Files.deleteIfExists(caminhoImagem);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizar(Produto produto) {

        boolean atualizado = produtoRepository.atualizar(
                produto.getId(), 
                produto.getDescricao(), 
                produto.getPreco(), 
                produto.getImagem()
        );

 
        if (atualizado && produto.getImagem() != null) {
            return salvarImagem(produto);
        }

        return atualizado;
    }
}
