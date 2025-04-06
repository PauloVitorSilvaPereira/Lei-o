/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        conn = new conectaDAO().connectDB();
        String sql = "INSERT INTO produtos(nome, valor, status) VALUES "
                            + "(?, ?, ?)"; 
                    try {
                        prep = this.conn.prepareStatement(sql);
                        prep.setString(1, produto.getNome());
                        prep.setInt(2, produto.getValor());
                        prep.setString(3, produto.getStatus());
                        prep.execute();  
                        
                        JOptionPane.showMessageDialog(null, "O produto foi cadastrado com sucesso!");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto: " + e.getMessage());
                    }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
    conn = new conectaDAO().connectDB();
    String sql = "SELECT * FROM produtos";

    try {
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();

        listagem = new ArrayList<>(); // reinicia a lista para evitar duplicações

        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor")); // ou getDouble, depende do tipo no banco
            produto.setStatus(resultset.getString("status"));

            listagem.add(produto);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
    }

        
        return listagem;
    }
    
    public void venderProduto(int id){
    
    conn = new conectaDAO().connectDB();
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = (?)"; 
                    try {
                        prep = this.conn.prepareStatement(sql);
                        prep.setInt(1, id);
                        prep.execute();  
                        
                        JOptionPane.showMessageDialog(null, "O produto foi vendido com sucesso!");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Erro ao veder o produto: " + e.getMessage());
                    }
    
    
    
    
    
    
    }
    
    
    
        
}

