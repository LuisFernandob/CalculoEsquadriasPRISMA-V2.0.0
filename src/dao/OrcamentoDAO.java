package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import sistema.Conexao;

public class OrcamentoDAO {
	Conexao mysql = new Conexao();

	/**
	 * Salva o orçamento no banco de dados
	 * @param o - Entidade orçamento
	 */
	public void criarOrcamento(Orcamento o) {
		Connection conexao = Conexao.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = conexao.prepareStatement(
					"INSERT INTO prisma.orcamento" + "(id_cliente,descricao,peso_total,valor_total,data_hora"
					+ ") VALUES (?,?,?,?,?);");

			stmt.setInt(1, o.getIdCliente());
			stmt.setString(2, o.getDescricao());
			stmt.setDouble(3, 0);
			stmt.setDouble(4, 0);
			o.setDataHora();
			stmt.setString(5, o.getDataHora());
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
	 * Retorna os dados do orçamento de acordo com a busca do código
	 * @param idOrcamento - o id a ser buscado
	 * @return - os dados do orçamento
	 */
    public Orcamento lerObjOrcamento(int idOrcamento) {
        Connection conexao = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Orcamento orcamento= new Orcamento(false);

        try {
            stmt = conexao.prepareStatement("SELECT * FROM orcamento WHERE id_orcamento LIKE ?;");
            stmt.setInt(1, idOrcamento);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
            	orcamento.setIdOrcamento(rs.getInt("id_orcamento"));
            	orcamento.setIdCliente(rs.getInt("id_cliente"));
            	orcamento.setDescricao(rs.getString("descricao"));
            	orcamento.setPesoTotal(rs.getDouble("peso_total"));
            	orcamento.setValorTotal(rs.getDouble("valor_total"));
            	orcamento.setDataHora(rs.getString("data_hora"));
            } 
            else {
            	JOptionPane.showMessageDialog(null, "Não foi possivel encontrar o id do orcamento selecionado na tabela!");
            }
        } 
        catch (Exception e) { 
        	JOptionPane.showMessageDialog(null, "Algo deu errado ao tentar encontrar o id do orcamento selecionado na tabela!");
        } 
        finally { 
        	Conexao.closeConnection(conexao, stmt, rs);
        }
        
        return orcamento;
    }

    /**
     * Retorna uma lista dos orçamentos cadastrados
     * @return - a lista de orçamentos
     */
	public List<Orcamento> lerTabela(){
		List<Orcamento> orcamentos = new ArrayList<>();
		
		try {
      		Connection conexao = Conexao.getConnection();
			PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM orcamento;");
			ResultSet rs = stmt.executeQuery();
		
			while(rs.next()) {
				Orcamento orcamento = new Orcamento(false);
				orcamento.setIdOrcamento(rs.getInt("id_orcamento"));
				orcamento.setIdCliente(rs.getInt("id_cliente"));
				orcamento.setDescricao(rs.getString("descricao"));
                                
                //calcular peso total
                Connection conexao2 = Conexao.getConnection();
                PreparedStatement stmt2 = conexao2.prepareStatement("select peso_total from itens_orcamento where id_orcamento = "+rs.getInt("id_orcamento"));
                ResultSet rs2 = stmt2.executeQuery();
                double pesototal = 0;

                while(rs2.next()) {
                        pesototal = pesototal + rs2.getDouble("peso_total");
                }

                orcamento.setPesoTotal(pesototal);
                Conexao.closeConnection(conexao2, stmt2, rs2);
                
                //calcular valor total
                Connection conexao3 = Conexao.getConnection();
                PreparedStatement stmt3 = conexao3.prepareStatement("select valor_total from itens_orcamento where id_orcamento = "+rs.getInt("id_orcamento"));
                ResultSet rs3 = stmt3.executeQuery();
                double valorTotal = 0;

                while(rs3.next()) {
                    valorTotal = valorTotal + rs3.getDouble("valor_total");
                }

                orcamento.setValorTotal(valorTotal);
                Conexao.closeConnection(conexao3, stmt3, rs3);
				orcamento.setDataHora(rs.getString("data_hora"));
				orcamentos.add(orcamento);
			}
			
            Conexao.closeConnection(conexao, stmt, rs);
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar tabela 'Orcamentos' "+e);
		}
		
		return orcamentos;
	}

	/**
	 * Exclui o orçamento selecionado do banco de dados
	 * @param o - o orçamento a ser deletado
	 */
    public void delete(Orcamento o) {
        Connection conexao = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("DELETE FROM orcamento WHERE id_orcamento = ?;");
            stmt.setInt(1, o.getIdOrcamento());
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
     * Atualiza o orçamento selecionado no banco de dados
     * @param o - o orçamento a ser alterado
     */
    public void update(Orcamento o) {
        Connection conexao = Conexao.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("UPDATE orcamento SET id_cliente = ?,"
            		+ "descricao= ? WHERE id_orcamento = ?;");
      
			stmt.setInt(1, o.getIdCliente());
			stmt.setString(2, o.getDescricao());
            stmt.setInt(3, o.getIdOrcamento());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } 
        catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "ID do cliente nao existe, selecione uma ID valida!");
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + e);
        } 
        finally {
            Conexao.closeConnection(conexao, stmt);
        }
    }
}