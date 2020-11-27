package guiAntesInclusoes;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.Componente;
import dao.ComponenteDAO;
import dao.Produto;
import dao.ProdutoDAO;
import gui.TelaTipologias;
import guiIncluirAlterar.JTableIncluirAlterarTipologia;

@SuppressWarnings("serial")
public class JTableAntesIncluirTipologia extends javax.swing.JFrame implements KeyListener{

	int idProduto;
	
	/**
	 * Cria a janela para escolher o produto a receber uma nova tipologia
	 * @param produto - o produto que receberá a tipologia
	 */
	public JTableAntesIncluirTipologia(Produto produto) {
       
		idProduto = produto.getIdProduto();
	   
	    addKeyListener(this);
	    setFocusable(true);
        setFocusTraversalKeysEnabled(false);
	    initComponents();
	    DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        jTable1.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
        readJTable();
    }
   
	/**
	 * Filtra a tabela de componentes a serem selecionados, de acordo com a busca
	 * @param descricao - o conteúdo da caixa de busca
	 */
    public void lerJTableParaDescricao(String descricao) {
	   
	    DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        jTable1.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
        modelo.setNumRows(0);
        ComponenteDAO cdao = new ComponenteDAO();
        for (Componente c : cdao.lerParaNome(descricao)) {
            modelo.addRow(new Object[]{
	            c.getCodigo_componente(),
	            c.getTipo(),
	            c.getDescricao(),
	            c.getComprimento_barra_m(),
	            c.getUnidade(),
	            c.getLargura_mm(),
	            c.getLargura_encaixe_mm(),
	            c.getPeso_por_metro() 
            });
        }
    }
   
    /**
     * Cria a tabela de componentes a serem selecionados
     */
    public void readJTable() {
       
    	DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        jTable1.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
        modelo.setNumRows(0);
        ComponenteDAO cdao = new ComponenteDAO();
       	for (Componente c : cdao.lerTabela()) {
       		modelo.addRow(new Object[]{            			
       			c.getCodigo_componente(),
       			c.getTipo(),
       	    	c.getDescricao(),
       	    	c.getComprimento_barra_m(),
       	    	c.getUnidade(),
       	    	c.getLargura_mm(),
       	    	c.getLargura_encaixe_mm(),
       	    	c.getPeso_por_metro()       	     
       		});
       	}
    }
   
    /**
     * Cria a janela e todos os seus componentes para escolher o componente a ser adicionado na tipologia
     */
    private void initComponents() {

    	setTitle("Sistema para cálculo de esquadrias -> Produtos -> Tipologia -> Selecionar componente para incluir");

        lbInstr = new javax.swing.JLabel();
        pnLocalizar = new javax.swing.JPanel();
        txtBusca = new javax.swing.JTextField();
        lbInstr2 = new javax.swing.JLabel();
        scrollPane1 = new java.awt.ScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbInstr.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbInstr.setText("Clique duas vezes sobre o componente à ser incluído:");

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

        lbInstr2.setText("Digite alguma palavra contida na descrição do componente desejado para filtrar a tabela.");

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
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbInstr2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {
            		    "codigo_componente", "tipo", "descricao","comprimento_barra_m","unidade", "largura_mm", "largura_encaixe_mm", "peso_por_metro"}
            ){ 
        	boolean[] podeEditar = new boolean [] {false, false, false, false, false,false,false,false};
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnLocalizar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbInstr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbInstr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnLocalizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
   	    this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }                        

    /**
     * Filtra a lista de componentes de acordo com o que for digitado na caixa de texto
     * @param evt - pressionar uma tecla
     */
    private void txtBuscaKeyTyped(java.awt.event.KeyEvent evt) {                                     
        lerJTableParaDescricao(txtBusca.getText());            
    }      
   
    /**
     * Filtra a lista de componentes de acordo com o que for digitado na caixa de texto
     * @param evt - pressionar uma tecla
     */
    private void txtBuscaKeyPressed(java.awt.event.KeyEvent evt) {                                     
	   
		int key = evt.getKeyCode();
		
		if(key == KeyEvent.VK_ENTER) {
	         
			if (jTable1.getSelectedRow() != -1) {
					
	   			ProdutoDAO pdao = new ProdutoDAO();
	   			ComponenteDAO cdao = new ComponenteDAO();   				
	   			Produto p = pdao.lerObjProduto(idProduto);
	   			Componente c = cdao.lerObjComponente(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
	   			this.dispose();
	   			new JTableIncluirAlterarTipologia(p,c).setVisible(true);
	                            
	   		} 
			else {
				JOptionPane.showMessageDialog(null, "Selecione componente para incluir!");
			}
			 
		} 
		else if(key == KeyEvent.VK_ESCAPE) {
	    	Produto p = new Produto();
	    	p.setIdProduto(idProduto);
			this.dispose();
			new TelaTipologias(p).setVisible(true);
	    }
    }      

    int selecao = -1;
    int linhaSelecionada = -1;
   
    /**
     * Confirma a seleção do orçamento ao apertar a tecla enter
     * @param evt - pressionar a tecla
     */
    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) { 
   
		int key = evt.getKeyCode();
		
		if(key == KeyEvent.VK_ENTER) {
	         
			if (jTable1.getSelectedRow() != -1) {
					
	   			ProdutoDAO pdao = new ProdutoDAO();
	   			ComponenteDAO cdao = new ComponenteDAO();   				
	   			Produto p = pdao.lerObjProduto(idProduto);
	   			Componente c = cdao.lerObjComponente(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
	   			this.dispose();
	   			new JTableIncluirAlterarTipologia(p,c).setVisible(true);
	                            
	   		} 
			else {
				JOptionPane.showMessageDialog(null, "Selecione componente para incluir!");
			}
			 
		} 
		else if(key == KeyEvent.VK_ESCAPE) {
	    	Produto p = new Produto();
	    	p.setIdProduto(idProduto);
			this.dispose();
			new TelaTipologias(p).setVisible(true);
	    }
    }
   
    /**
     * Seleciona o orçamento ao clicar nele
     * @param evt - o clique do mouse
     */
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {                                     
	 	
	   	if (selecao != -1) {
	   		if (selecao == 0 && linhaSelecionada == jTable1.getSelectedRow()) {
	   			selecao = jTable1.getSelectedRow();
	   	  
	   	
	   			if (jTable1.getSelectedRow() != -1) {
	   				ProdutoDAO pdao = new ProdutoDAO();
	   				ComponenteDAO cdao = new ComponenteDAO();   				
	   				Produto p = pdao.lerObjProduto(idProduto);
	   				Componente c = cdao.lerObjComponente(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
	   				this.dispose();
	   				new JTableIncluirAlterarTipologia(p,c).setVisible(true);
	                            
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

    private javax.swing.JLabel lbInstr;
    private javax.swing.JLabel lbInstr2;
    private javax.swing.JPanel pnLocalizar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtBusca;
    private java.awt.ScrollPane scrollPane1;

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
    	int key = e.getKeyCode();
	
		if(key == KeyEvent.VK_ENTER) {
         
			if (jTable1.getSelectedRow() != -1) {
   				ProdutoDAO pdao = new ProdutoDAO();
   				ComponenteDAO cdao = new ComponenteDAO();   				
   				Produto p = pdao.lerObjProduto(idProduto);
   				Componente c = cdao.lerObjComponente(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
   				this.dispose();
   				new JTableIncluirAlterarTipologia(p,c).setVisible(true);
   			} 
			else {
				JOptionPane.showMessageDialog(null, "Selecione componente para incluir!");
			}
		} 
		else if(key == KeyEvent.VK_ESCAPE) {
	    	Produto p = new Produto();
	    	p.setIdProduto(idProduto);
			this.dispose();
			new TelaTipologias(p).setVisible(true);
		}
    }

    public void keyReleased(KeyEvent e) {}
}
