package dao;

import sistema.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProdutoDAO {
    
	Conexao mysql = new Conexao();

	/**
	 * Salva o produto no banco de dados
	 * @param p - Entidade produto
	 */
	public void criarProduto(Produto p) {
		Connection conexao = Conexao.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = conexao.prepareStatement(
					"INSERT INTO prisma.produto" + "(tipo,descricao,folhas,observacoes"
					+ ") VALUES (?,?,?,?);");

			stmt.setString(1, p.getTipo());
			stmt.setString(2, p.getDescricao());
			stmt.setString(3, p.getFolhas());
			stmt.setString(4, p.getObservacoes());
			stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar!" + e);
		} 
		finally {
			Conexao.closeConnection(conexao, stmt);
		}
	}

	/**
	 * Retorna uma lista dos produtos cadastrados
	 * @return - a lista de produtos
	 */
	public List<Produto> lerTabela(){
		Connection conexao = Conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Produto> produtos = new ArrayList<>();
		
		try {
			stmt = conexao.prepareStatement("SELECT * FROM produto;");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Produto produto = new Produto();
				produto.setIdProduto(rs.getInt("id_produto"));
				produto.setTipo(rs.getString("tipo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setFolhas(rs.getString("folhas"));
				produto.setObservacoes(rs.getString("observacoes"));
				produtos.add(produto);
			}
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar tabela 'Produtos' "+e);
		}
		finally {
			Conexao.closeConnection(conexao, stmt, rs);
		}
		
		return produtos;
	}
	
	/**
	 * Retorna os dados do produto de acordo com a busca do id
	 * @param idProduto - o id a ser buscado
	 * @return - os dados do produto
	 */
    public Produto lerObjProduto(int idProduto) {
        Connection conexao = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto produto = new Produto();

        try {
            stmt = conexao.prepareStatement("SELECT * FROM produto WHERE id_produto LIKE ?;");
            stmt.setInt(1, idProduto);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
            	produto.setIdProduto(rs.getInt("id_produto"));
            	produto.setTipo(rs.getString("tipo"));
            	produto.setDescricao(rs.getString("descricao"));
            	produto.setFolhas(rs.getString("folhas"));
            	produto.setObservacoes(rs.getString("observacoes"));
            } 
            else {
            	JOptionPane.showMessageDialog(null, "N�o foi possivel encontrar o id do produto selecionado na tabela!");
            }
        } 
        catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "Algo deu errado ao tentar encontrar o id do produto selecionado na tabela!");
        } 
        finally {
        	Conexao.closeConnection(conexao, stmt, rs);
        }
        
        return produto;
    }
    
    /**
     * Retorna uma lista dos produtos cadastrados de acordo com a busca
     * @param descricao - o nome/descri��o do produto a ser buscado
     * @return - a lista de produtos filtrada
     */
    public List<Produto> lerParaDescricao(String descricao) {
        Connection conexao = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = conexao.prepareStatement("SELECT * FROM produto WHERE descricao LIKE ?;");
            stmt.setString(1, "%"+descricao+"%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("id_produto"));
				produto.setTipo(rs.getString("tipo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setFolhas(rs.getString("folhas"));
				produto.setObservacoes(rs.getString("observacoes"));
				produtos.add(produto);
            }
        } 
        catch (Exception e) {} 
        
        finally {
        	Conexao.closeConnection(conexao, stmt, rs);
        }
        
        return produtos;
    }
    
    /**
     * Exclui o produto selecionado do banco de dados
     * @param p - o produto a ser deletado
     */
    public void delete(Produto p) {
        Connection conexao = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("DELETE FROM produto WHERE id_produto = ?;");
            stmt.setInt(1, p.getIdProduto());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e);
        } 
        finally {
            Conexao.closeConnection(conexao, stmt);
        }
    }
    
    /**
     * Atualiza o produto selecionado no banco de dados
     * @param p - o produto a ser alterado
     */
    public void update(Produto p) {
        Connection conexao = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("UPDATE produto SET tipo = ?,"
            		+ "descricao= ?,folhas= ?,observacoes = ? WHERE id_produto = ?;");
			stmt.setString(1, p.getTipo());
			stmt.setString(2, p.getDescricao());
			stmt.setString(3, p.getFolhas());
			stmt.setString(4, p.getObservacoes());
            stmt.setInt(5, p.getIdProduto());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + e);
        } 
        finally {
            Conexao.closeConnection(conexao, stmt);
        }
    }
}