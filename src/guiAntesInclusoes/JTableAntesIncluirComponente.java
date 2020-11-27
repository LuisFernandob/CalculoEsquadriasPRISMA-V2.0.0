package guiAntesInclusoes;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import gui.TelaComponentes;
import guiIncluirAlterar.JTableIncluirAlterarComponente;
import sistema.Conexao;

@SuppressWarnings("serial")
public class JTableAntesIncluirComponente extends javax.swing.JFrame implements KeyListener{

	/**
	 * Cria a janela para inserir o código do componente a ser incluído
	 */
	public JTableAntesIncluirComponente() {
    	initComponents();
    }
     
	/**
	 * Cria a janela e todos os seus componentes para inserir o código do componente a ser incluído
	 */
	private void initComponents() {

		btOk = new javax.swing.JButton();
		btCancelar = new javax.swing.JButton();
		txtBusca = new javax.swing.JTextField();
		jLabel1 = new javax.swing.JLabel();

		setResizable(false);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		btOk.setText("OK");
		btOk.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btOkActionPerformed(evt);
            }
        });

		btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });
       
        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscaKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14));
        jLabel1.setText("Digite o código do novo componente:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBusca)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(btOk, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btOk)
                    .addComponent(btCancelar))
                .addContainerGap())
        );

        txtBusca.requestFocus(true);
       
        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }                     

	/**
	 * Filtra a lista de componentes de acordo com o que for digitado na caixa de texto
	 * @param evt - pressionar uma tecla
	 */
	private void txtBuscaKeyPressed(java.awt.event.KeyEvent evt) {                                     
		if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
			String novoCodigoComponente = new String();
			novoCodigoComponente = txtBusca.getText().toString();
       	
       		if(!novoCodigoComponente.contentEquals("")) {
       			try {
       				Connection conexao = Conexao.getConnection();
       				PreparedStatement	stmt = conexao.prepareStatement("SELECT codigo_componente FROM prisma.componente WHERE codigo_componente LIKE ?;");
	        		stmt.setString(1, novoCodigoComponente);
	        		ResultSet rs = stmt.executeQuery();
	        		
	        		if(rs.next()) {
	        			JOptionPane.showMessageDialog(null, "Código ja existente no banco de dados, digite um código diferente ou caso deseje alterar "
	        				+ "o componente '"+novoCodigoComponente+"' o selecione na tela 'Componentes' e clique em ALTERAR ou EXCLUIR!");
	        				
	        			txtBusca.setText("");
	        				
	        		} 
	        		else {
	        			this.dispose();
	        			new JTableIncluirAlterarComponente(novoCodigoComponente).setVisible(true);;
	        		}
       			} 
       			catch(SQLException e) {
       				JOptionPane.showMessageDialog(null, "Erro ao verificar se o componente digitado ja existe no Banco de dados!");}
       		} 
       		else {
       			JOptionPane.showMessageDialog(null, "Digite um código para o novo componente!");
       		}
       } 
	   else if(evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
    	   this.dispose();
    	   new TelaComponentes().setVisible(true);
       }
    }   

	/**
	 * Realiza as operações necessárias para cancelar e fechar a janela ao apertar o botão
	 * @param evt - apertar o botão
	 */
	private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {                                         
	    this.dispose();
	    new TelaComponentes().setVisible(true);
    }                                        

	/**
	 * Realiza as operações necessárias para confirmar a seleção e trocar para a janela de Incluir Componentes ao apertar o botão
	 * @param evt - apertar o botão
	 */
	private void btOkActionPerformed(java.awt.event.ActionEvent evt) {                                         
	    String novoCodigoComponente = new String();
		novoCodigoComponente = txtBusca.getText().toString();
        	
        if(!novoCodigoComponente.contentEquals("")) {
        	try {
        		Connection conexao = Conexao.getConnection();
        		PreparedStatement	stmt = conexao.prepareStatement("SELECT codigo_componente FROM prisma.componente WHERE codigo_componente LIKE ?;");
	        	stmt.setString(1, novoCodigoComponente);
	        	ResultSet rs = stmt.executeQuery();
				
	        	if(rs.next()) {
	        				
	        		JOptionPane.showMessageDialog(null, "Código ja existente no banco de dados, digite um código diferente ou caso deseje alterar "
	        			+ "o componente '"+novoCodigoComponente+"' o selecione na tela 'Componentes' e clique em ALTERAR ou EXCLUIR!");
	        				
	        		txtBusca.setText("");
	        				
	        	} 
	        	else {
	        		this.dispose();
	        		new JTableIncluirAlterarComponente(novoCodigoComponente).setVisible(true);;
	        	}
        	} 
        	catch(SQLException e) {
        		JOptionPane.showMessageDialog(null, "Erro ao verificar se o componente digitado ja existe no Banco de dados!");
        	}
        } 
        else {
        	JOptionPane.showMessageDialog(null, "Digite um código para o novo componente!");
        }
 	   	   
	}                                        

    private javax.swing.JButton btOk;
    private javax.swing.JButton btCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtBusca;
   
    @Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent evt) {
   	 
	   	int key = evt.getKeyCode();
	   	
	   	if(key == KeyEvent.VK_ENTER) {
	   		String novoCodigoComponente = new String();
			novoCodigoComponente = txtBusca.getText().toString();
	 	
	 		if(!novoCodigoComponente.contentEquals("")) {
	 			try {
	 				Connection conexao = Conexao.getConnection();
	     			PreparedStatement	stmt = conexao.prepareStatement("SELECT codigo_componente FROM prisma.componente WHERE codigo_componente LIKE ?;");
	     			stmt.setString(1, novoCodigoComponente);
	     			ResultSet rs = stmt.executeQuery();
				
	     			if(rs.next()) {
	     				JOptionPane.showMessageDialog(null, "Código ja existente no banco de dados, digite um código diferente ou caso deseje alterar "
	     						+ "o componente '"+novoCodigoComponente+"' o selecione na tela 'Componentes' e clique em ALTERAR ou EXCLUIR!");
	     				txtBusca.setText("");
	     			} 
	     			else {
	     					this.dispose();
	     					new JTableIncluirAlterarComponente(novoCodigoComponente).setVisible(true);;
	     			}
	 			} 
	 			catch(SQLException e) {
	 				JOptionPane.showMessageDialog(null, "Erro ao verificar se o componente digitado ja existe no Banco de dados!");
	 			}
	 		} 
	 		else {
	 				JOptionPane.showMessageDialog(null, "Digite um código para o novo componente!");
	 		}
	   			
	   	} 
	   	else if(key == KeyEvent.VK_ESCAPE) {
	   		this.dispose();
	     	new TelaComponentes().setVisible(true);
	    }
   }

    @Override
    public void keyReleased(KeyEvent e) {}
}
