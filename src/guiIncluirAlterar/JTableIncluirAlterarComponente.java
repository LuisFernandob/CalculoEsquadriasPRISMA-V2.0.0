package guiIncluirAlterar;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JOptionPane;

import dao.Componente;
import dao.ComponenteDAO;
import gui.TelaComponentes;

@SuppressWarnings("serial")
public class JTableIncluirAlterarComponente extends javax.swing.JFrame {
	
	String idComponenteAlterar = new String("-1");
	String descricaoComponente = new String();
	String tipoComponenteAlterar = new String("");
	int indexTipoInicial = -1;
	String novoCodigoComponente = null;

	/**
	 * Cria a janela para inserir componentes
	 * @param novoCodigoComponente - o componente a ser incluído
	 */
    public JTableIncluirAlterarComponente(String novoCodigoComponente) {
    	this.novoCodigoComponente = novoCodigoComponente;
		initComponents();
    }
    
    /**
     * Cria a janela para alterar componentes
     * @param componente - o componente a ser alterado
     */
    public JTableIncluirAlterarComponente(Componente componente) {
        initComponents(componente);
    }

    /**
     * Converte um numero em String para Double
     * @param s - o valor em String a ser convertido
     * @return - convertido, ou 0 caso vazio
     */
	public double Double(String s) {
		if(!s.isEmpty()) {
		return Double.parseDouble(s);
		}else {return 0;}
	}
	
	/**
	 * Converte um numero em String para Integer
	 * @param s - o valor em String a ser convertido
	 * @return - o valor convertido, ou 0 caso vazio
	 */
	public int Integer(String s) {
		if(!s.isEmpty()) {
		return Integer.parseInt(s);
		}else {return 0;}
	}
	
    /**
     * Cria a janela e todos os seus componentes para incluir componentes
     */
    private void initComponents() {

   	    setTitle("Sistema para cálculo de esquadrias -> Componentes -> Incluir cadastro de componente");

        jPanel1 = new javax.swing.JPanel();
        lbDescr = new javax.swing.JLabel();
        lbUnidade = new javax.swing.JLabel();
        lbComprimEnc = new javax.swing.JLabel();
        lbLarguraEnc = new javax.swing.JLabel();
        lbKgMetro = new javax.swing.JLabel();
        lbLargura = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        txtUnidade = new javax.swing.JTextField();
        txtLarguraEncaixe = new javax.swing.JTextField();
        txtComprimentoBarra = new javax.swing.JTextField();
        txtKgMetro = new javax.swing.JTextField();
        txtLargura = new javax.swing.JTextField();
        lbTipo = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox<>();
        lbCabecalho = new javax.swing.JLabel();
        btIncluir = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        lbObrigatorio = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        lbDescr.setText("Descrição:");
        lbUnidade.setText("Unidade:");
        lbComprimEnc.setText("Comprimento da barra (mm):");
        lbLarguraEnc.setText("Largura do encaixe (mm):");
        lbKgMetro.setText("*Kg/Metro:");
        lbLargura.setText("Largura (mm):");
        lbTipo.setText("*Tipo:");
        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione um tipo...", "Arremate", "Contramarco", "Perfil","Paleta","Baguete","Outros" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbTipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbUnidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbComprimEnc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtComprimentoBarra, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbDescr)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbLargura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtLargura, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbLarguraEnc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtLarguraEncaixe, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbKgMetro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtKgMetro)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTipo)
                    .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbUnidade)
                    .addComponent(txtUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbComprimEnc)
                    .addComponent(txtComprimentoBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDescr)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLargura)
                    .addComponent(txtLargura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLarguraEnc)
                    .addComponent(txtLarguraEncaixe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbKgMetro)
                    .addComponent(txtKgMetro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbCabecalho.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        btIncluir.setText("INCLUIR");
        btIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIncluirActionPerformed(evt);
            }
        });

        btCancelar.setText("CANCELAR");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        lbCabecalho.setText("Novo cadastro de componente.");
        lbObrigatorio.setText("Os campos com * são obrigatórios.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCabecalho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btIncluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btCancelar)
                                .addGap(17, 17, 17))))
                    .addComponent(lbObrigatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbCabecalho)
                .addGap(5, 5, 5)
                .addComponent(lbObrigatorio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btIncluir)
                    .addComponent(btCancelar))
                .addContainerGap())
        );

        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    	this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

    }

    /**
     * Cria a janela e todos os seus componentes para alterar componentes
     * @param comp - o componente a ser alterado
     */
	private void initComponents(Componente comp) {
    	
    	idComponenteAlterar = comp.getCodigo_componente();
    	tipoComponenteAlterar = comp.getTipo();
    	descricaoComponente = comp.getDescricao();   
    	
   	    setTitle("Sistema para cálculo de esquadrias -> Componentes -> Alterar cadastro de componente");

        jPanel1 = new javax.swing.JPanel();
        lbDescr = new javax.swing.JLabel();
        lbUnidade = new javax.swing.JLabel();
        lbComprimEnc = new javax.swing.JLabel();
        lbLarguraEnc = new javax.swing.JLabel();
        lbKgMetro = new javax.swing.JLabel();
        lbLargura = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        txtUnidade = new javax.swing.JTextField();
        txtLarguraEncaixe = new javax.swing.JTextField();
        txtComprimentoBarra = new javax.swing.JTextField();
        txtKgMetro = new javax.swing.JTextField();
        txtLargura = new javax.swing.JTextField();
        lbTipo = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox<>();
        lbCabecalho = new javax.swing.JLabel();
        btAlterar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        lbObrigatorio = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Outros","Arremate","Contramarco","Perfil","Paleta","Baguete"}));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        lbDescr.setText("Descrição:");
        lbUnidade.setText("Unidade:");
        lbComprimEnc.setText("Comprimento da barra (mm):");
        lbLarguraEnc.setText("Largura do encaixe (mm):");
        lbKgMetro.setText("*Kg/Metro:");
        lbLargura.setText("Largura (mm):");
        lbTipo.setText("*Tipo:");
        txtDescricao.setText(comp.getDescricao());
   		txtComprimentoBarra.setText(comp.getComprimento_barra_m()+"");
   		txtUnidade.setText(comp.getUnidade());
   		txtLargura.setText(comp.getLargura_mm()+"");
   		txtLarguraEncaixe.setText(comp.getLargura_encaixe_mm()+"");
   		txtKgMetro.setText(comp.getPeso_por_metro()+"");
   		
   		
   		switch (tipoComponenteAlterar) 
        {
          case "Arremate":
        	  indexTipoInicial = 1;
        	  break;
          case "Contramarco":
        	  indexTipoInicial = 2;
              break;
          case "Perfil":
        	  indexTipoInicial = 3;
              break;
          case "Paleta":
        	  indexTipoInicial = 4;
              break;
          case "Baguete":
        	  indexTipoInicial = 5;
              break;
          default:
        	  indexTipoInicial = 0;
        	  break;
        }
   		
   		cbTipo.setSelectedIndex(indexTipoInicial);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbTipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbUnidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbComprimEnc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtComprimentoBarra))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbDescr)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbLargura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtLargura, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbLarguraEnc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtLarguraEncaixe, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbKgMetro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtKgMetro)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTipo)
                    .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbUnidade)
                    .addComponent(txtUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbComprimEnc)
                    .addComponent(txtComprimentoBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDescr)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLargura)
                    .addComponent(txtLargura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLarguraEnc)
                    .addComponent(txtLarguraEncaixe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbKgMetro)
                    .addComponent(txtKgMetro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ComponenteDAO cDAO = new ComponenteDAO();
        
        Componente c = cDAO.lerObjComponente(idComponenteAlterar);
        
        lbCabecalho.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbCabecalho.setText("Alterando componente Cod.: "+idComponenteAlterar+" - "+c.getDescricao());
        btAlterar.setText("ALTERAR");
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarActionPerformed(evt);
            }
        });

        btCancelar.setText("CANCELAR");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        lbObrigatorio.setText("Os campos com * são obrigatórios.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCabecalho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btAlterar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btCancelar)
                                .addGap(17, 17, 17))))
                    .addComponent(lbObrigatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbCabecalho)
                .addGap(5, 5, 5)
                .addComponent(lbObrigatorio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAlterar)
                    .addComponent(btCancelar))
                .addContainerGap())
        );

        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
   	    this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

    }// </editor-fold>                     
    
    /**
     * Realiza as operações necessárias para incluir um componente ao apertar o botão
     * @param evt - apertar o botão
     */
    private void btIncluirActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	
    	Componente c = new Componente();
        ComponenteDAO dao = new ComponenteDAO();
        
        c.setCodigo_componente(novoCodigoComponente);
   		c.setDescricao(txtDescricao.getText());
   		c.setComprimento_barra_m(Double(txtComprimentoBarra.getText()));
   		c.setUnidade(txtUnidade.getText());
   		c.setLargura_mm(Integer(txtLargura.getText()));
   		c.setLargura_encaixe_mm(Integer(txtLarguraEncaixe.getText()));
   		c.setPeso_por_metro(Double(txtKgMetro.getText()));
   		c.setTipo(cbTipo.getSelectedItem().toString());
   		
   		if(c.getPeso_por_metro() != 0.0 && cbTipo.getSelectedIndex()!=0) {  
   		dao.criarComponente(c);
           
   		txtDescricao.setText("");
   		txtComprimentoBarra.setText("");
   		txtUnidade.setText("");
   		txtLargura.setText("");
   		txtLarguraEncaixe.setText("");
   		txtKgMetro.setText("");
	   
   		this.dispose();
   		new TelaComponentes().setVisible(true);
    	} else {JOptionPane.showMessageDialog(null, "Selecione um tipo e preencha o campo 'Kg/Metro'!");}
	   
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
     * Realiza as operações necessárias para alterar um componente ao apertar o botão
     * @param evt - apertar o botão
     */
    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	
		Componente c = new Componente();
		ComponenteDAO dao = new ComponenteDAO();
		c.setCodigo_componente(idComponenteAlterar);
		c.setDescricao(txtDescricao.getText());
		c.setTipo(cbTipo.getSelectedItem().toString());
		c.setComprimento_barra_m(Double.parseDouble(txtComprimentoBarra.getText()));
		c.setUnidade(txtUnidade.getText());
		c.setLargura_mm(Integer.parseInt(txtLargura.getText()));
		c.setLargura_encaixe_mm(Integer.parseInt(txtLarguraEncaixe.getText()));
		c.setPeso_por_metro(Double.parseDouble(txtKgMetro.getText()));
		dao.update(c);
		            
		txtDescricao.setText("");
		txtComprimentoBarra.setText("");
		txtUnidade.setText("");
		txtLargura.setText("");
		txtLarguraEncaixe.setText("");
		txtKgMetro.setText("");
		       		
		this.dispose();
		new TelaComponentes().setVisible(true);
    }     

    private javax.swing.JButton btIncluir;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btAlterar;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JLabel lbObrigatorio;
    private javax.swing.JLabel lbTipo;
    private javax.swing.JLabel lbDescr;
    private javax.swing.JLabel lbUnidade;
	private javax.swing.JLabel lbComprimEnc;
    private javax.swing.JLabel lbLarguraEnc;
    private javax.swing.JLabel lbKgMetro;
    private javax.swing.JLabel lbLargura;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbCabecalho;
    private javax.swing.JTextField txtComprimentoBarra;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtKgMetro;
    private javax.swing.JTextField txtLargura;
    private javax.swing.JTextField txtLarguraEncaixe;
    private javax.swing.JTextField txtUnidade;
}
