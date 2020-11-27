package guiAntesInclusoes;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.Orcamento;
import dao.OrcamentoDAO;
import dao.Produto;
import dao.ProdutoDAO;
import gui.TelaItensOrcamento;
import guiIncluirAlterar.JTableIncluirAlterarItemOrcamento;

@SuppressWarnings("serial")
public class JTableAntesIncluirItemOrcamento extends javax.swing.JFrame implements KeyListener {

	int idOrcamento;
	Orcamento orcamentoAberto = new Orcamento(false);
	
	/**
	 * Cria a janela para escolher o produto a ser incluído no orçamento
	 * @param orcamento - o orçamento a receber o produto
	 */
    public JTableAntesIncluirItemOrcamento(Orcamento orcamento) {
       
    	idOrcamento = orcamento.getIdOrcamento();
	    orcamentoAberto = orcamento;
	   
	    addKeyListener(this);
	    setFocusable(true);
        setFocusTraversalKeysEnabled(false);
	    initComponents();
	    DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        jTable1.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
	    readJTable();
    }
   
    /**
     * Cria a tabela de produtos do orçamento a serem selecionados
     */
	public void readJTable() {
     
		DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
		jTable1.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
		modelo.setNumRows(0);
		ProdutoDAO pdao = new ProdutoDAO();
     	for (Produto p : pdao.lerTabela()) {
     		modelo.addRow(new Object[]{            			
				p.getIdProduto(),
				p.getTipo(),
				p.getDescricao(),
				p.getFolhas(),
				p.getObservacoes()     	     
     		});
     	}
	}

	/**
	 * Filtra a tabela de produtos do orçamento a serem selecionados, de acordo com a busca
	 * @param descricao - o conteúdo da caixa de busca
	 */
	public void lerJTableParaDescricao(String descricao) {
	   
		DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
		jTable1.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
		modelo.setNumRows(0);
		ProdutoDAO pdao = new ProdutoDAO();
		for (Produto p : pdao.lerParaDescricao(descricao)) {
			modelo.addRow(new Object[]{
				p.getIdProduto(),
				p.getTipo(),
				p.getDescricao(),
				p.getFolhas(),
				p.getObservacoes()
			});
		}
	}

	/**
	 * Cria a janela e todos os seus componentes para escolher o produto a ser incluído no orçamento
	 */
	private void initComponents() {
		setTitle("Sistema para cálculo de esquadrias -> Orçamentos -> Itens orçamento -> Escolher produto para incluir");
        lbInstr = new javax.swing.JLabel();
        pnLocalizar = new javax.swing.JPanel();
        txtBusca = new javax.swing.JTextField();
        lbInstr2 = new javax.swing.JLabel();
        scrollPane1 = new java.awt.ScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbInstr.setFont(new java.awt.Font("Arial", 0, 18));
        lbInstr.setText("Clique duas vezes sobre o produto à ser incluído:");
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


        lbInstr2.setText("Digite alguma palavra contida na descrição do produto desejado para filtrar a tabela.");

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
                        .addGap(0, 48, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbInstr2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
        	new Object [][] {},
            new String [] {"idproduto", "tipo", "descricao", "folhas", "observacoes"}
        	){ 
        		boolean[] podeEditar = new boolean [] {false,false, false, false,false
        	};
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
                    .addComponent(pnLocalizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbInstr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    	   this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }    

    int selecao = -1;
    int linhaSelecionada = -1;
    
	/**
	 * Seleciona o produto do orçamento ao clicar nele
	 * @param evt - o clique do mouse
	 */
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {                                     
    	if (selecao != -1) {
    		if (selecao == 0 && linhaSelecionada == jTable1.getSelectedRow()) {
	   			selecao = jTable1.getSelectedRow();
	   	  
	   	
	   			if (jTable1.getSelectedRow() != -1) {
	   				ProdutoDAO pdao = new ProdutoDAO();		
	   				Produto p = pdao.lerObjProduto(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
	   				
	   				this.dispose();
	   				new JTableIncluirAlterarItemOrcamento(orcamentoAberto,p).setVisible(true);
	                            
	   			} 
	   			else {
	   				JOptionPane.showMessageDialog(null, "Selecione produto para incluir!");
	   			}
	   			
	   		selecao = -1;
	   		linhaSelecionada = -1;
	   		} 
    		else { 
    			selecao = -1; linhaSelecionada = -1;
    		}
	   	} 
    	else {
    		selecao = 0; linhaSelecionada = jTable1.getSelectedRow();
    	} 
   }                                    

    /**
     * Confirma a seleção do produto do orçamento ao apertar a tecla enter
     * @param evt - pressionar a tecla
     */
    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {                                   
    	int key = evt.getKeyCode();
	   	
	   	if(key == KeyEvent.VK_ENTER) {
	            
	   		if (jTable1.getSelectedRow() != -1) {
				ProdutoDAO pdao = new ProdutoDAO();		
				Produto p = pdao.lerObjProduto(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
					
				this.dispose();
				new JTableIncluirAlterarItemOrcamento(orcamentoAberto,p).setVisible(true);
			} 
	   		else {
	   			JOptionPane.showMessageDialog(null, "Selecione produto para incluir!");
	   		}
	   		
	   	} 
	   	else if(key == KeyEvent.VK_ESCAPE) {
	        OrcamentoDAO oDAO = new OrcamentoDAO();
	       	Orcamento o = oDAO.lerObjOrcamento(idOrcamento);
	   		this.dispose();
	   		new TelaItensOrcamento(o).setVisible(true);
	   	}
	}                                  

    /**
     * Filtra a lista de produtos do orçamento de acordo com o que for digitado na caixa de texto
     * @param evt - pressionar uma tecla
     */
    private void txtBuscaKeyTyped(java.awt.event.KeyEvent evt) {                                     
        lerJTableParaDescricao(txtBusca.getText());            
    }                             

    /**
     * Filtra a lista de produtos do orçamento de acordo com o que for digitado na caixa de texto
     * @param evt - pressionar uma tecla
     */
    private void txtBuscaKeyPressed(java.awt.event.KeyEvent evt) {                                     
	 	 
	   	int key = evt.getKeyCode();
	   	
	   	if(key == KeyEvent.VK_ENTER) {
	            
	   		if (jTable1.getSelectedRow() != -1) {
				ProdutoDAO pdao = new ProdutoDAO();		
				Produto p = pdao.lerObjProduto(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
					
				this.dispose();
				new JTableIncluirAlterarItemOrcamento(orcamentoAberto,p).setVisible(true);
	                        
	   		} 
	   		else {
	   			JOptionPane.showMessageDialog(null, "Selecione produto para incluir!");
	   		}
	   		
	   	} 
	   	else if(key == KeyEvent.VK_ESCAPE) {
	        OrcamentoDAO oDAO = new OrcamentoDAO();
	       	Orcamento o = oDAO.lerObjOrcamento(idOrcamento);
	   		this.dispose();
	   		new TelaItensOrcamento(o).setVisible(true);
	    }
   }      
   
    @Override
	public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
   	 
   	int key = e.getKeyCode();
   	
   	if(key == KeyEvent.VK_ENTER) {
            
   		if (jTable1.getSelectedRow() != -1) {
			ProdutoDAO pdao = new ProdutoDAO();		
			Produto p = pdao.lerObjProduto(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
				
			this.dispose();
			new JTableIncluirAlterarItemOrcamento(orcamentoAberto,p).setVisible(true);
                        
		} 
   		else {
   			JOptionPane.showMessageDialog(null, "Selecione produto para incluir!");
   		}
   		
   	} 
   	else if(key == KeyEvent.VK_ESCAPE) {
        OrcamentoDAO oDAO = new OrcamentoDAO();
       	Orcamento o = oDAO.lerObjOrcamento(idOrcamento);
   		this.dispose();
   		new TelaItensOrcamento(o).setVisible(true);
        }
	}

    @Override
    public void keyReleased(KeyEvent e) {}

    private javax.swing.JLabel lbInstr;
    private javax.swing.JLabel lbInstr2;
    private javax.swing.JPanel pnLocalizar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtBusca;
    private java.awt.ScrollPane scrollPane1;
}
