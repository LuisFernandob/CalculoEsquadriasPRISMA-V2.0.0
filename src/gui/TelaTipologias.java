package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.Produto;
import dao.Tipologia;
import dao.TipologiaDAO;
import dao.VariaveisDAO;
import guiAntesInclusoes.JTableAntesIncluirTipologia;
import guiIncluirAlterar.JTableIncluirAlterarTipologia;
import sistema.MySQLDumper;

@SuppressWarnings("serial")
public class TelaTipologias extends javax.swing.JFrame implements KeyListener{
	
	Produto ProdutoAberto;

    /**
     * Cria a tela principal de edição de Tipologia
     * @param produto - o produto a ter sua tipologia alterada
     */
    public TelaTipologias(Produto produto) {
    	addKeyListener(this);
 	    setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    	ProdutoAberto = produto;
        initComponents(produto);
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        jTable1.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
        readJTable(produto);
    }

    /**
     * Cria a tabela de componentes do produto
     * @param produto - o produto a ter sua tipologia alterada
     */
	public void readJTable(Produto produto) {
		DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        jTable1.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
        modelo.setNumRows(0);
        TipologiaDAO tdao = new TipologiaDAO();
    	for (Tipologia t : tdao.lerParaProduto(produto)) {
    		modelo.addRow(new Object[]{  
	            t.getIdTipologia(),		
	            t.getCodigo_componente(),
	            t.getDescricaoComponente(),
	            t.getMultiplicadorAltura(),
	            t.getOffsetAltura(),
	            t.getMultiplicadorLargura(),
	            t.getOffsetLargura(),
	            t.getPeso_por_metro()
    		});
    	}
	}
    
	/**
	 * Cria a janela de edição de tipologia e todos os seus componentes
	 * @param produto - o produto a ter sua tipologia alterada
	 */
    private void initComponents(Produto produto) {
    	setTitle("Sistema para cálculo de esquadrias -> Produtos -> Tipologia");

        pnNavegacao = new javax.swing.JPanel();
        btVoltar = new javax.swing.JButton();
        pnEdicao = new javax.swing.JPanel();
        btIncluir = new javax.swing.JButton();
        btExclur = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        scrollPane2 = new java.awt.ScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lbTitulo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnBackup = new javax.swing.JMenu();
        miExportar = new javax.swing.JMenuItem();
        miImportar = new javax.swing.JMenuItem();
        mnValoresGlobais = new javax.swing.JMenu();
        miAluminio = new javax.swing.JMenuItem();
        miMaoDeObra = new javax.swing.JMenuItem();
        miQuiloContram = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnNavegacao.setBorder(javax.swing.BorderFactory.createTitledBorder("Navegação"));
        btVoltar.setText("Voltar (produtos)");
        btVoltar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProdutosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(pnNavegacao);
        pnNavegacao.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btVoltar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnEdicao.setBorder(javax.swing.BorderFactory.createTitledBorder("Edição"));

        btIncluir.setText("INCLUIR COMPONENTE");
        btIncluir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIncluirActionPerformed(evt);
            }
        });
        btIncluir.setToolTipText("Inclui um componente na tipologia do produto");

        btExclur.setText("EXCLUIR COMPONENTE");
        btExclur.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btExclur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });
        btExclur.setToolTipText("Exclui o componente selecionado da tipologia do produto");

        btAlterar.setText("ALTERAR COMPONENTE");
        btAlterar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarActionPerformed(evt);
            }
        });
        btAlterar.setToolTipText("Altera os multiplicadores e offsets do componente selecionado");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {
                    "id_tipologia", "codigo_componente", "descricao", "multiplicadoraltura", "offsetaltura", "multiplicadorlargura", "offsetlargura", "peso_por_metro"}
            ) { boolean[] podeEditar = new boolean [] {false, false, false, false, false, false, false,false};
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return podeEditar [columnIndex];
                }
        });
        
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt2) {
            	jTable1MouseClicked(evt2);
            }
        });
        
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        
        jScrollPane2.setViewportView(jTable1);

        scrollPane2.add(jScrollPane2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(pnEdicao);
        pnEdicao.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btExclur, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 224, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btIncluir)
                    .addComponent(btExclur)
                    .addComponent(btAlterar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addContainerGap())
        );

        lbTitulo.setFont(new java.awt.Font("Arial", 0, 24));
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("Editando tipologia do produto nº "+produto.getIdProduto()+" - "+produto.getDescricao());
        lbTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER); 
        mnBackup.setText("Backup");
        miExportar.setText("Exportar BD");
        miExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExportarActionPerformed(evt);
            }
        });
        
        mnBackup.add(miExportar);

        miImportar.setText("Importar BD");
        miImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miImportarActionPerformed(evt);
            }
        });
        mnBackup.add(miImportar);
        jMenuBar1.add(mnBackup);
        mnValoresGlobais.setText("Valores Globais");
        miAluminio.setText("Valor do quilo do alumínio");
        miAluminio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAluminioActionPerformed(evt);
            }
        });
        mnValoresGlobais.add(miAluminio);

        miQuiloContram.setText("Valor do quilo do contramarco");
        miQuiloContram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miQuiloContramActionPerformed(evt);
            }
        });
        mnValoresGlobais.add(miQuiloContram);
        
        miMaoDeObra.setText("Mão de obra (%)");
        miMaoDeObra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miMaoDeObraActionPerformed(evt);
            }
        });
        mnValoresGlobais.add(miMaoDeObra);
        jMenuBar1.add(mnValoresGlobais);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnNavegacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbTitulo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                               ))
                        .addGap(0, 0, Short.MAX_VALUE))
                    )
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTitulo)
                    )
                .addGap(5, 5, 5)
                
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnNavegacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    	this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    
    int selecao = -1;
    int linhaSelecionada = -1;
    
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {                                     
    	if (selecao != -1) {
    		if (selecao == 0 && linhaSelecionada == jTable1.getSelectedRow()) {
    			selecao = jTable1.getSelectedRow();
    	
    			if(jTable1.getSelectedRow() != -1) {
    		    	TipologiaDAO tdao = new TipologiaDAO();
    		    	Tipologia t = tdao.lerObjTipologia(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
    		    	this.dispose();
    		    	new JTableIncluirAlterarTipologia(t).setVisible(true);
    		    } 
    			else {
    				JOptionPane.showMessageDialog(null, "Selecione um componente da tipologia para alterar!");
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
    
    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {
    	int key = evt.getKeyCode();
    	if(key == KeyEvent.VK_ENTER) {
             
    		if(jTable1.getSelectedRow() != -1) {
    	    	TipologiaDAO tdao = new TipologiaDAO();
    	    	Tipologia t = tdao.lerObjTipologia(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
    	    	this.dispose();
    	    	new JTableIncluirAlterarTipologia(t).setVisible(true);
    	    } 
    		else {
    			JOptionPane.showMessageDialog(null, "Selecione um componente da tipologia para alterar!");
    		}
    	} 
    	else if(key == KeyEvent.VK_DELETE) {
        	 
    	 	if(jTable1.getSelectedRow() != -1) {
    	        
           		if(JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o componente\n da tipologia deste produto?")==0) {
	           		TipologiaDAO tdao = new TipologiaDAO();
	            	Tipologia t = tdao.lerObjTipologia(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
	            	tdao.delete(t);
	            	readJTable(ProdutoAberto);
           		}
            } 
    	 	else {
    	 		JOptionPane.showMessageDialog(null, "Selecione uma componente da tipologia para excluir!");
    	 	}
        }
    }
    
    private void miExportarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        MySQLDumper.export();
    }                                          

    private void btProdutosActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	this.dispose();
    	new TelaProdutos().setVisible(true);
    }                                        

    private void miImportarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        JOptionPane.showMessageDialog(null, "Função ainda não implementada, caso precise recuperar o backup fale com o desenvolvedor do sistema!");

    }                                          

    private void miAluminioActionPerformed(java.awt.event.ActionEvent evt) {                                           
    	VariaveisDAO vdao = new VariaveisDAO();
        if(!vdao.procurarReferencia()){
            vdao.criarReferencia();
        }
        double valorAluminio = vdao.lerVariavel("precoaluminio");
        
        String novoValor =  JOptionPane.showInputDialog("O preço atual do aluminio é: R$"+valorAluminio+"\n"
                + "Digite um novo valor para atualizar ou cancele para manter o valor atual");
        if(!novoValor.isEmpty()){
            double novoValor2 = Double.parseDouble(novoValor);
            vdao.update("precoaluminio", novoValor2);
        }
        vdao.atualizarVariavelLocal();
    }                                          

    private void miMaoDeObraActionPerformed(java.awt.event.ActionEvent evt) {                                           
    	VariaveisDAO vdao = new VariaveisDAO();
    	if(!vdao.procurarReferencia()){
    		vdao.criarReferencia();
    	}
    	double porcentagemMDO = vdao.lerVariavel("porcentagemmdo");
           
    	String novoValor =  JOptionPane.showInputDialog("A porcentagem atual da mao de obra é: "+porcentagemMDO+"%\n"
    			+ "Digite um novo valor para atualizar ou cancele para manter o valor atual");
    	if(!novoValor.isEmpty()){
    		double novoValor2 = Integer.parseInt(novoValor);
    		vdao.update("porcentagemmdo", novoValor2);
    	}
    	vdao.atualizarVariavelLocal();
    }                        
    
	private void miQuiloContramActionPerformed(java.awt.event.ActionEvent evt) {                                           
        VariaveisDAO vdao = new VariaveisDAO();
        if(!vdao.procurarReferencia()){
            vdao.criarReferencia();
        }
        double valorContramarco = vdao.lerVariavel("precocontramarco");
        
        String novoValor =  JOptionPane.showInputDialog("O preço atual do contramarco é: R$"+valorContramarco+"\n"
                + "Digite um novo valor para atualizar ou cancele para manter o valor atual");
        if(!novoValor.isEmpty()){
            double novoValor2 = Integer.parseInt(novoValor);
            vdao.update("precocontramarco", novoValor2);
        }
        vdao.atualizarVariavelLocal();
    }   

    private void btIncluirActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	this.dispose();
    	new JTableAntesIncluirTipologia(ProdutoAberto).setVisible(true);
    }                                        

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {                                         
       	if(jTable1.getSelectedRow() != -1) {
        
       		if(JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o componente\n da tipologia deste produto?")==0) {
	       		TipologiaDAO tdao = new TipologiaDAO();
	        	Tipologia t = tdao.lerObjTipologia(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
	        	tdao.delete(t);
	        	readJTable(ProdutoAberto);
       		}
        } 
       	else {
       		JOptionPane.showMessageDialog(null, "Selecione uma componente da tipologia para excluir!");
       	}
    }                                                            

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	if(jTable1.getSelectedRow() != -1) {
	    	TipologiaDAO tdao = new TipologiaDAO();
	    	Tipologia t = tdao.lerObjTipologia(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
	    	this.dispose();
	    	new JTableIncluirAlterarTipologia(t).setVisible(true);
    	} 
    	else {
    		JOptionPane.showMessageDialog(null, "Selecione um componente da tipologia para alterar!");
    	}
    }                  
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
    	int key = e.getKeyCode();
    	if(key == KeyEvent.VK_ENTER) {
             
    		if(jTable1.getSelectedRow() != -1) {
    	    	TipologiaDAO tdao = new TipologiaDAO();
    	    	Tipologia t = tdao.lerObjTipologia(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
    	    	this.dispose();
    	    	new JTableIncluirAlterarTipologia(t).setVisible(true);
    	    } 
    		else {
    			JOptionPane.showMessageDialog(null, "Selecione um componente da tipologia para alterar!");
    		}
    	} 
    	else if(key == KeyEvent.VK_DELETE) {
        	 
    		if(jTable1.getSelectedRow() != -1) {
        	        
    			if(JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o componente\n da tipologia deste produto?")==0) {
    				TipologiaDAO tdao = new TipologiaDAO();
    				Tipologia t = tdao.lerObjTipologia(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
    				tdao.delete(t);
    				readJTable(ProdutoAberto);
    			}
    		} 
    		else {
    			JOptionPane.showMessageDialog(null, "Selecione uma componente da tipologia para excluir!");
    		}
    	}
	}

    @Override
	public void keyReleased(KeyEvent e) {}

    private javax.swing.JButton btIncluir;
    private javax.swing.JButton btExclur;
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btVoltar;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JMenu mnBackup;
    private javax.swing.JMenu mnValoresGlobais;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem miExportar;
    private javax.swing.JMenuItem miImportar;
    private javax.swing.JMenuItem miAluminio;
    private javax.swing.JMenuItem miMaoDeObra;
    private javax.swing.JMenuItem miQuiloContram;
    private javax.swing.JPanel pnNavegacao;
    private javax.swing.JPanel pnEdicao;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private java.awt.ScrollPane scrollPane2;
}