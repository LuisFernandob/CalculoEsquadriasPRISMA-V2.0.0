package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import sistema.Conexao;

public class TipologiaDAO {
	
	Conexao mysql = new Conexao();
     
	/**
     * Cadastra um novo componente na tipologia do produto no banco de dados
     * @param t - o objeto com todos os dados a serem cadastrados
     */
	public void criarTipologia(Tipologia t) {

		Connection conexao = Conexao.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = conexao.prepareStatement("INSERT INTO tipologia " 
				    + "(id_produto_tipologia,codigo_componente,descricao_componente,multiplicador_largura,offset_largura_mm,multiplicador_altura,offset_altura_mm,"
					+ " peso_por_metro) VALUES (?,?,?,?,?,?,?,?);");

			stmt.setInt(1, t.getIdProduto());
			stmt.setString(2,t.getCodigo_componente().toString());
			
			// query para procurar a descricao do componente e incluir no banco de dados
			PreparedStatement stmt2 = conexao.prepareStatement("SELECT descricao FROM componente WHERE "
					+ "codigo_componente = '" + t.getCodigo_componente()+"';");
			ResultSet rs = stmt2.executeQuery();

			if(rs.next()) {
				stmt.setString(3,rs.getString("descricao"));
			} 
			else {
				JOptionPane.showMessageDialog(null, "Nao foi possivel localizar o componente "
						+ "no banco de dados, componente ira ficar sem o campo 'descrição' na tabela 'tipologias'"
						+ " (Não irá causar nenhum problema)!");
				stmt.setString(3,"");	
			}
			
			stmt.setDouble(4, t.getMultiplicadorLargura());
			stmt.setDouble(5, t.getOffsetLargura());
			stmt.setDouble(6, t.getMultiplicadorAltura());
			stmt.setDouble(7, t.getOffsetAltura());
			Conexao.closeConnection(stmt2);
			Conexao.closeConnection(rs);

			// query para procurar o peso por metro do componente e incluir no banco de dados
			PreparedStatement stmt3 = conexao.prepareStatement("SELECT peso_por_metro FROM componente WHERE "
					+ "codigo_componente = '" + t.getCodigo_componente().toString()+"';");
			ResultSet rs2 = stmt3.executeQuery();

			if(rs2.next()) {
				stmt.setDouble(8, rs2.getDouble("peso_por_metro"));
			} 
			else {
				JOptionPane.showMessageDialog(null, "Nao foi possivel localizar o componente "
						+ "no banco de dados, definindo o peso por metro para 0.0 (nao irá calcular "
						+ "este componente no orçamento)!");
				stmt.setDouble(6,0);
			}
			
			stmt.executeUpdate();
			
			Conexao.closeConnection(stmt3);
			Conexao.closeConnection(rs2);

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
	 * Retorna um componente cadastrado na tipologia do produto
	 * @param idTipologia - o id do componente a ser buscado na tipologia
	 * @return Objeto - o objeto com todos os dados do componente encontrado
	 */
	public Tipologia lerObjTipologia(int idTipologia) {

		Connection conexao = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Tipologia tipologia = new Tipologia();

        try {
            stmt = conexao.prepareStatement("SELECT * FROM tipologia WHERE id_tipologia LIKE ?;");
            stmt.setInt(1, idTipologia);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                tipologia.setIdTipologia(rs.getInt("id_tipologia"));
            	tipologia.setIdProduto(rs.getInt("id_produto_tipologia"));
            	tipologia.setCodigo_componente(rs.getString("codigo_componente"));
            	tipologia.setDescricaoComponente(rs.getString("descricao_componente"));
            	tipologia.setMultiplicadorLargura(rs.getDouble("multiplicador_largura"));
            	tipologia.setOffsetLargura(rs.getDouble("offset_largura_mm"));
            	tipologia.setMultiplicadorAltura(rs.getDouble("multiplicador_altura"));
            	tipologia.setOffsetAltura(rs.getDouble("offset_altura_mm"));
            	tipologia.setPeso_por_metro(rs.getDouble("peso_por_metro"));
            } 
            else {
            	JOptionPane.showMessageDialog(null, "Não foi possivel encontrar o id da tipologia selecionada na tabela!");
            }
        } 
        catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "Algo deu errado ao tentar encontrar o id da tipologia selecionada na tabela!");
        } 
        finally {
        	Conexao.closeConnection(conexao, stmt, rs);
        }
        
        return tipologia;
	}
	
	/**
	 * Retorna uma lista com todos os componentes cadastrados nas tipologias de todos os produtos
	 * @return a lista de todos os componentes cadastrados nas tipologias
	 */
	public List<Tipologia> lerTabela(){
		Connection conexao = Conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Tipologia> Tipologias = new ArrayList<>();
		
		try {
			//pegar descricao do produto e componente
		    stmt = conexao.prepareStatement("SELECT * FROM tipologia");
			rs = stmt.executeQuery();
		
			while(rs.next()) {
				Tipologia Tipologia = new Tipologia();	
				Tipologia.setIdTipologia(rs.getInt("id_tipologia"));
				Tipologia.setIdProduto(rs.getInt("id_produto_tipologia"));
				Tipologia.setCodigo_componente(rs.getString("codigo_componente"));
				Tipologia.setDescricaoComponente(rs.getString("descricao_componente"));
				Tipologia.setMultiplicadorLargura(rs.getDouble("multiplicador_largura"));
            	Tipologia.setOffsetLargura(rs.getDouble("offset_largura_mm"));
			    Tipologia.setMultiplicadorAltura(rs.getDouble("multiplicador_altura"));
            	Tipologia.setOffsetAltura(rs.getDouble("offset_altura_mm"));
				Tipologia.setPeso_por_metro(rs.getDouble("peso_por_metro"));
				Tipologias.add(Tipologia);
			}
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar tabela 'tipologia' "+e);
		}
		finally { 
			Conexao.closeConnection(conexao, stmt, rs);
		}
		
		return Tipologias;
	}

	/**
     * Busca todos os componentes cadastrados na tipologia do produto selecionado
     * @param produto - o objeto com o id do produto
     * @return a lista de componentes cadastrados na tipologia do produto selecionado
     */
    public List<Tipologia> lerParaProduto(Produto produto) {
        Connection conexao = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Tipologia> tipologias = new ArrayList<>();
        
        try {
            stmt = conexao.prepareStatement("SELECT * FROM tipologia WHERE id_produto_tipologia = ?");
            stmt.setInt(1, produto.getIdProduto());           
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Tipologia tipologia = new Tipologia();
				tipologia.setIdTipologia(rs.getInt("id_tipologia"));
				tipologia.setIdProduto(rs.getInt("id_produto_tipologia"));
				tipologia.setCodigo_componente(rs.getString("codigo_componente"));
				tipologia.setDescricaoComponente(rs.getString("descricao_componente"));
				tipologia.setMultiplicadorLargura(rs.getDouble("multiplicador_largura"));
            	tipologia.setOffsetLargura(rs.getDouble("offset_largura_mm"));
			    tipologia.setMultiplicadorAltura(rs.getDouble("multiplicador_altura"));	
            	tipologia.setOffsetAltura(rs.getDouble("offset_altura_mm"));
			    tipologia.setPeso_por_metro(rs.getDouble("peso_por_metro"));	
				tipologias.add(tipologia);				
            }
        } 
        catch (Exception e) {} 
        
        finally {
        	Conexao.closeConnection(conexao, stmt, rs);
        }
        
        return tipologias;
    }        
    
    /**
     * Exclui o cadastro de um componente da tipologia
     * @param c - o id da tipologia a ser excluida
     */
    public void delete(Tipologia c) {
        Connection conexao = Conexao.getConnection();    
        PreparedStatement stmt = null;
        
        try {
            stmt = conexao.prepareStatement("DELETE FROM tipologia WHERE id_tipologia = ?");
            stmt.setInt(1, c.getIdTipologia());
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
     * Altera o cadastro de um componente da tipologia
     * @param c - o id da tipologia a ser alterada
     */
    public void update(Tipologia c) {
        Connection conexao = Conexao.getConnection();           
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("UPDATE tipologia SET "
            		+ "codigo_componente = ?,"
            		+ "multiplicador_Largura = ?, offset_largura_mm = ?, multiplicador_altura = ?, offset_altura_mm = ? WHERE id_tipologia = ?;");              
    		
			stmt.setString(1, c.getCodigo_componente());
			stmt.setDouble(2, c.getMultiplicadorLargura());
			stmt.setDouble(3, c.getOffsetLargura());
			stmt.setDouble(4, c.getMultiplicadorAltura());		
			stmt.setDouble(5, c.getOffsetAltura());
			stmt.setInt(6, (c.getIdTipologia()));
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