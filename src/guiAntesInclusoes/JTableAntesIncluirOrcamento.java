package guiAntesInclusoes;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.Cliente;
import dao.ClienteDAO;
import gui.TelaOrcamentos;
import guiIncluirAlterar.JTableIncluirAlterarOrcamento;

@SuppressWarnings("serial")
public class JTableAntesIncluirOrcamento extends javax.swing.JFrame implements KeyListener {

	/**
	 * Cria a janela para escolher o cliente a receber um novo orçamento
	 */
	public JTableAntesIncluirOrcamento() {
	    addKeyListener(this);
	    setFocusable(true);
        setFocusTraversalKeysEnabled(false);
	    initComponents();
	    DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        jTable1.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
        readJTable();
    }
   
	/**
	 * Filtra a tabela de orçamentos a serem selecionados, de acordo com a busca
	 * @param nome - o conteúdo da caixa de busca
	 */
    public void lerJTableParaNome(String nome) {
 	    DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        jTable1.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
        modelo.setNumRows(0);
        ClienteDAO cdao = new ClienteDAO();
        for (Cliente c : cdao.lerParaNome(nome)) {
            modelo.addRow(new Object[]{
	            c.getIdCliente(),
	            c.getNome(),
	            c.getEmpresa(),
	            c.getTelefone(),
	            c.getEndereco(),
	            c.getEmail(),
	            c.getDataNascimento(),
	            c.getCnpj(),
	            c.getCpf(),
	            c.getObservacoes()
            });
        }
	}

    /**
     * Cria a tabela de orçamentos a serem selecionados
     */
    public void readJTable() {
	    DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        jTable1.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
        modelo.setNumRows(0);
        ClienteDAO cdao = new ClienteDAO();
       	for (Cliente c : cdao.lerTabela()) {
       		modelo.addRow(new Object[]{            			
       			c.getIdCliente(),
                c.getNome(),
                c.getEmpresa(),        
                c.getTelefone(),                  
                c.getEndereco(),        
                c.getEmail(),        
                c.getDataNascimento(),        
                c.getCnpj(),        
                c.getCpf(),
                c.getObservacoes()       
       		});
       	}
    }
                      
    /**
     *  Cria a janela e todos os seus componentes para escolher o cliente a receber um novo orçamento
     */
    private void initComponents() {
    	setTitle("Sistema para cálculo de esquadrias -> Orçamentos -> Escolher cliente para incluir");
        lbInstr = new javax.swing.JLabel();
        pnLocalizar = new javax.swing.JPanel();
        txtBusca = new javax.swing.JTextField();
        lbInstr2 = new javax.swing.JLabel();
        scrollPane1 = new java.awt.ScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); 

        lbInstr.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbInstr.setText("Clique duas vezes sobre o cliente escolhido:");
        pnLocalizar.setBorder(javax.swing.BorderFactory.createTitledBorder("Localizar"));
        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscaKeyTyped(evt);
            }
        });
        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscaKeyPressed(evt);
            }
        });
       
        lbInstr2.setText("Digite alguma palavra contida no nome do cliente desejado para filtrar a tabela.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(pnLocalizar);
        pnLocalizar.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBusca, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbInstr2)
                        .addGap(0, 80, Short.MAX_VALUE)))
                .addContainerGap())
        ); 
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbInstr2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
 
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {
                    "idcliente", "nome","empresa","telefone", "endereco", "email", "nascimento", "cnpj","cpf","observacoes"}
            ) { boolean[] podeEditar = new boolean [] {false, false, false, false, false, false, false, false, false, false};
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return podeEditar [columnIndex];
                }
            });
       
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1); 

        scrollPane1.add(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnLocalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbInstr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbInstr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
   	    this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }                        

    int selecao = -1;
    int linhaSelecionada = -1;
   
    /**
     * Seleciona o orçamento ao clicar nele
     * @param evt - o clique do mouse
     */
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {                                     
    	if (selecao != -1) {
    		
    		if (selecao == 0 && linhaSelecionada == jTable1.getSelectedRow()) {
    			selecao = jTable1.getSelectedRow();
   	  
    			if (jTable1.getSelectedRow() != -1) {
    				ClienteDAO cdao = new ClienteDAO();
    				Cliente c = cdao.lerObjCliente(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
    				this.dispose();
    				new JTableIncluirAlterarOrcamento(c).setVisible(true);
    			} 
    			else {
    				JOptionPane.showMessageDialog(null, "Selecione componente para incluir!");
    			}
		   		selecao = -1;
		   		linhaSelecionada = -1;
	   		} 
    		else { 
    			selecao = -1; 
    			linhaSelecionada = -1;
    		}
    	} 
    	else {
    		selecao = 0; 
    		linhaSelecionada = jTable1.getSelectedRow();
    	}   
    }  
   
    /**
     * Confirma a seleção do orçamento ao apertar a tecla enter
     * @param evt - pressionar a tecla
     */
    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {                                   
	    int key = evt.getKeyCode();
	   	
	   	if(key == KeyEvent.VK_ENTER) {
	            
	   		if (jTable1.getSelectedRow() != -1) {
				ClienteDAO cdao = new ClienteDAO();
				Cliente c = cdao.lerObjCliente(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
				this.dispose();
				new JTableIncluirAlterarOrcamento(c).setVisible(true);
			} 
	   		else {
	   			JOptionPane.showMessageDialog(null, "Selecione componente para incluir!");
	   		}
	   		
	   	} 
	   	else if(key == KeyEvent.VK_ESCAPE){
	   		this.dispose();
	   		new TelaOrcamentos().setVisible(true);
	   	}   
	}                                  

    /**
     * Filtra a lista de orçamentos de acordo com o que for digitado na caixa de texto
     * @param evt - pressionar uma tecla
     */
    private void txtBuscaKeyTyped(java.awt.event.KeyEvent evt) {                                     
        lerJTableParaNome(txtBusca.getText());            
    }          
   
    /**
     * Filtra a lista de orçamentos de acordo com o que for digitado na caixa de texto
     * @param evt - pressionar uma tecla
     */
    private void txtBuscaKeyPressed(java.awt.event.KeyEvent evt) {                                     
		int key = evt.getKeyCode();
	   	
	   	if(key == KeyEvent.VK_ENTER) {
	            
	   		if (jTable1.getSelectedRow() != -1) {
					
				ClienteDAO cdao = new ClienteDAO();

				Cliente c = cdao.lerObjCliente(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
					
				this.dispose();
				new JTableIncluirAlterarOrcamento(c).setVisible(true);
	                        
			} 
	   		else {
	   			JOptionPane.showMessageDialog(null, "Selecione componente para incluir!");
	   		}
	   		
	   	} 
	   	else if(key == KeyEvent.VK_ESCAPE) {
	   		this.dispose();
	   		new TelaOrcamentos().setVisible(true);
	    }
	}   

    private javax.swing.JLabel lbInstr;
    private javax.swing.JLabel lbInstr2;
    private javax.swing.JPanel pnLocalizar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtBusca;
    private java.awt.ScrollPane scrollPane1;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
    	int key = e.getKeyCode();
   	
    	if(key == KeyEvent.VK_ENTER) {
            
    		if (jTable1.getSelectedRow() != -1) {
				
				ClienteDAO cdao = new ClienteDAO();

				Cliente c = cdao.lerObjCliente(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
				
				this.dispose();
				new JTableIncluirAlterarOrcamento(c).setVisible(true);
                        
			} 
    		else {
    			JOptionPane.showMessageDialog(null, "Selecione componente para incluir!");
    		}
   		
        } 
    	else if(key == KeyEvent.VK_ESCAPE) {
	   		this.dispose();
	   		new TelaOrcamentos().setVisible(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}