package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.Componente;
import dao.ComponenteDAO;
import dao.VariaveisDAO;
import guiAntesInclusoes.JTableAntesIncluirComponente;
import guiIncluirAlterar.JTableIncluirAlterarComponente;
import sistema.MySQLDumper;

@SuppressWarnings("serial")
public class TelaComponentes extends javax.swing.JFrame implements KeyListener {

	/**
	 * Cria a tela principal de Componentes
	 */
    public TelaComponentes() {
    	 
    	addKeyListener(this);
  	    setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        initComponents();
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        jTable1.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
        readJTable();
    }

    /**
     * Cria a tabela de componentes
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
	 * Filtra a tabela de componentes de acordo com a busca
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
	 * Cria a janela de componentes e todos os seus componentes
	 */
    private void initComponents() {

    	setTitle("Sistema para cálculo de esquadrias -> Componentes");
        pnNavegacao = new javax.swing.JPanel();
        btOrcamentos = new javax.swing.JButton();
        btClientes = new javax.swing.JButton();
        btProdutos = new javax.swing.JButton();
        pnEdicao = new javax.swing.JPanel();
        scrollPane1 = new java.awt.ScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btIncluir = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        btBuscar = new javax.swing.JButton();
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
        btOrcamentos.setText("Orçamentos");
        btOrcamentos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btOrcamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOrcamentosActionPerformed(evt);
            }
        });

        btClientes.setText("Clientes");
        btClientes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClientesActionPerformed(evt);
            }
        });

        btProdutos.setText("Produtos");
        btProdutos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProdutosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(pnNavegacao);
        pnNavegacao.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(btOrcamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btOrcamentos)
                    .addComponent(btClientes)
                    .addComponent(btProdutos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnEdicao.setBorder(javax.swing.BorderFactory.createTitledBorder("Edição"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {
                    "codigo_componente", "tipo", "descricao","comprimento_barra_m","unidade", "largura_mm", "largura_encaixe_mm", "peso_por_metro"}
            ) { boolean[] podeEditar = new boolean [] {false,false, false, false, false, false, false, false};
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
            	jTable1keyPressed(evt);
            }
        });
        
        jScrollPane1.setViewportView(jTable1);

        scrollPane1.add(jScrollPane1);

        btIncluir.setText("INCLUIR");
        btIncluir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIncluirActionPerformed(evt);
            }
        });
		btIncluir.setToolTipText("Inclui um novo cadastro de componente");

        btExcluir.setText("EXCLUIR");
        btExcluir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });
		btExcluir.setToolTipText("Exclui o cadastro de componente selecionado");

        btAlterar.setText("ALTERAR");
        btAlterar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarActionPerformed(evt);
            }
        });
		btAlterar.setToolTipText("Altera o cadastro de componente selecionado");

        btBuscar.setText("BUSCAR");
        btBuscar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });
		btBuscar.setToolTipText("Procura o componente pela descrição");
		jTextField1.setToolTipText("Procura o componente pela descrição");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(pnEdicao);
        pnEdicao.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btIncluir)
                    .addComponent(btExcluir)
                    .addComponent(btAlterar)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addContainerGap())
        );

        lbTitulo.setFont(new java.awt.Font("Arial", 0, 40));
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("COMPONENTES");
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
                    .addComponent(pnNavegacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitulo)
                .addGap(13, 13, 13)
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
    	
    			if (jTable1.getSelectedRow() != -1) {
    				ComponenteDAO dao = new ComponenteDAO();
    				Componente c = dao.lerObjComponente(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
    				this.dispose();
    				new JTableIncluirAlterarComponente(c).setVisible(true);
                             
    			} 
    			else {
    				JOptionPane.showMessageDialog(null, "Selecione um cliente para alterar!");
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
   
    private void jTable1keyPressed(java.awt.event.KeyEvent evt) {                                     
    	int key = evt.getKeyCode();
    	
    	if(key == KeyEvent.VK_ENTER) {
             
    		if (jTable1.getSelectedRow() != -1) {
    	 		ComponenteDAO dao = new ComponenteDAO();
    	 		Componente c = dao.lerObjComponente(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
    	 		this.dispose();
    	        new JTableIncluirAlterarComponente(c).setVisible(true);
    		} 
    		else {
    			JOptionPane.showMessageDialog(null, "Selecione um componente para alterar!");
    		}
    	} 
    	else if(key == KeyEvent.VK_DELETE) {
        	 
        	if (jTable1.getSelectedRow() != -1) {
                 
            	if(JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o componente selecionado?\n"
            			+ "Importante: ao excluir o componente ele será removido de todas\n as tipologias de produto "
            			+ "às quais faz parte!")==0) {

            		Componente c = new Componente();
                	ComponenteDAO dao = new ComponenteDAO();
                	c.setCodigo_componente(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                	dao.delete(c);         
                	readJTable();
            	}
        	} 
        	else {
        		JOptionPane.showMessageDialog(null, "Selecione um componente para excluir!");
        	}    
    	}
	}      
   
    private void miExportarActionPerformed(java.awt.event.ActionEvent evt) {                                           
    	MySQLDumper.export();
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

    private void btOrcamentosActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	this.dispose();
		new TelaOrcamentos().setVisible(true);
    }                                        

    private void btClientesActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	this.dispose();
		new TelaClientes().setVisible(true);    
    }                                        

    private void btProdutosActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	this.dispose();
		new TelaProdutos().setVisible(true);      
    }                                     

    private void btIncluirActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	this.dispose();
        new JTableAntesIncluirComponente().setVisible(true);
    }                                        

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {                                         
        if (jTable1.getSelectedRow() != -1) {
            
       		if(JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o componente selecionado?\n"
       				+ "Importante: ao excluir o componente ele será removido de todas\n as tipologias de produto "
       				+ "às quais faz parte!")==0) {

        	Componente c = new Componente();
            ComponenteDAO dao = new ComponenteDAO();
            c.setCodigo_componente(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            dao.delete(c);         
            readJTable();
       		}
        } 
        else {
        	JOptionPane.showMessageDialog(null, "Selecione um componente para excluir!");
        }    
    }                                        

    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {                                         

    	if (jTable1.getSelectedRow() != -1) {
 			ComponenteDAO dao = new ComponenteDAO();
 			Componente c = dao.lerObjComponente(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
 			this.dispose();
            new JTableIncluirAlterarComponente(c).setVisible(true);
        } 
    	else {
    		JOptionPane.showMessageDialog(null, "Selecione um componente para alterar!");
    	}
    }                                        

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	lerJTableParaDescricao(jTextField1.getText());
    }   
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
    	int key = e.getKeyCode();
    	
    	if(key == KeyEvent.VK_ENTER) {
             
    		if (jTable1.getSelectedRow() != -1) {
    	 		ComponenteDAO dao = new ComponenteDAO();
    	 		Componente c = dao.lerObjComponente(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
    	 		this.dispose();
    	    	new JTableIncluirAlterarComponente(c).setVisible(true);
    	    } 
    		else {
    			JOptionPane.showMessageDialog(null, "Selecione um componente para alterar!");
    		}
    	} 
    	else if(key == KeyEvent.VK_DELETE) {
        	 
        	if (jTable1.getSelectedRow() != -1) {
                 
            	if(JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o componente selecionado?\n"
            			+ "Importante: ao excluir o componente ele será removido de todas\n as tipologias de produto "
            			+ "às quais faz parte!")==0) {

            		Componente c = new Componente();
            		ComponenteDAO dao = new ComponenteDAO();
            		c.setCodigo_componente(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            		dao.delete(c);         
            		readJTable();
            	}
        	} 
        	else {
        		JOptionPane.showMessageDialog(null, "Selecione um componente para excluir!");
        	}    
    	}
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    private javax.swing.JButton btIncluir;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btOrcamentos;
    private javax.swing.JButton btClientes;
    private javax.swing.JButton btProdutos;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private java.awt.ScrollPane scrollPane1;
}
