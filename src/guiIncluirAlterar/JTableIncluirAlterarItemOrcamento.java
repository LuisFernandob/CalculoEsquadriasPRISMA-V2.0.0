package guiIncluirAlterar;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JOptionPane;

import dao.ItemOrcamento;
import dao.ItemOrcamentoDAO;
import dao.Orcamento;
import dao.OrcamentoDAO;
import dao.Produto;
import gui.TelaItensOrcamento;

@SuppressWarnings("serial")
public class JTableIncluirAlterarItemOrcamento extends javax.swing.JFrame {
	
	int idItemOrcamento = -1;
    ItemOrcamentoDAO itemOrcamentoDAO = new ItemOrcamentoDAO();
    Orcamento orcamentoAberto = new Orcamento(false);
    Produto produtoAberto = new Produto();

	/**
	 * Cria a janela para incluir orçamentos
	 * @param itemOrcamento - o produto a ser incluído
	 */
	public JTableIncluirAlterarItemOrcamento(ItemOrcamento itemOrcamento) {
		initComponents(itemOrcamento);
	}
   
	/**
	 * Cria a janela para alterar orçamentos
	 * @param orcamento - o orçamento que contém o produto a ser alterado
	 * @param produto - o produto a ser alterado
	 */
	public JTableIncluirAlterarItemOrcamento(Orcamento orcamento,Produto produto) {
		orcamentoAberto = orcamento;
		produtoAberto = produto;
		initComponents(orcamento,produto);
	}

	/**
	 * Cria a janela e todos os seus componentes para incluir um produto no orçamento
	 * @param itemOrcamento - o produto que será incluído
	 */
    private void initComponents(ItemOrcamento itemOrcamento) {
 
    	idItemOrcamento = itemOrcamento.getIdItensOrcamento();
	   
	    setTitle("Sistema para cálculo de esquadrias -> Orçamentos -> Itens orçamento -> Alterar produto");
        bgContram = new javax.swing.ButtonGroup();
        bgArrem = new javax.swing.ButtonGroup();
        pnMedidas = new javax.swing.JPanel();
        lbAltura = new javax.swing.JLabel();
        lbLargura = new javax.swing.JLabel();
        lbLatSup = new javax.swing.JLabel();
        lbQuant = new javax.swing.JLabel();
        lbInf = new javax.swing.JLabel();
        lbLatSup2 = new javax.swing.JLabel();
        lbInf2 = new javax.swing.JLabel();

        txtAltura = new javax.swing.JTextField();
        txtLargura = new javax.swing.JTextField();
        txtQuantidade = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btAlterar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        lbObrigatorio = new javax.swing.JLabel();
        pnContram = new javax.swing.JPanel();
        rbSemContra = new javax.swing.JRadioButton();
        rbComContra = new javax.swing.JRadioButton();
        cbContram = new javax.swing.JComboBox<>();
        pnArremate = new javax.swing.JPanel();
        cbArremate = new javax.swing.JComboBox<>();
        cbContram2 = new javax.swing.JComboBox<>();
        cbArremate2 = new javax.swing.JComboBox<>(); 

        rbSemArrem = new javax.swing.JRadioButton();
        rbComArrem = new javax.swing.JRadioButton();
        lbCabecalho = new javax.swing.JLabel(); 

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnMedidas.setBorder(javax.swing.BorderFactory.createTitledBorder("Medidas"));
        lbAltura.setText("*Altura (mm):");
        lbLargura.setText("*Largura (mm):");
        lbQuant.setText("*Quantidade:");
        lbLatSup.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbLatSup.setText("Laterais + Superior");
        lbInf.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbInf.setText("Inferior");
        lbLatSup2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbLatSup2.setText("Laterais + Superior");
        lbInf2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbInf2.setText("Inferior");
        lbCabecalho.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbCabecalho.setText("Alterando o produto nº "+itemOrcamento.getIdProduto()+" do orçamento nº "+itemOrcamento.getIdOrcamento()+".");
        lbObrigatorio.setText("Os campos com * são obrigatórios.");
        txtAltura.setText(""+itemOrcamento.getAlturaMm());
        txtLargura.setText(""+itemOrcamento.getLarguraMm());
        txtQuantidade.setText(""+itemOrcamento.getQuantidade());
        bgContram.add(rbSemContra);
        rbSemContra.setText("Sem contramarco");
        rbSemContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSemContraActionPerformed(evt);
            }
        });
       
        bgContram.add(rbComContra);
        rbComContra.setText("Com contramarco");
        rbComContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbComContraActionPerformed(evt);
            }
        });
       
        bgArrem.add(rbSemArrem);
        rbSemArrem.setText("Sem arremate");
        rbSemArrem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSemArremActionPerformed(evt);
            }
        });

        bgArrem.add(rbComArrem);
        rbComArrem.setText("Com arremate");
        rbComArrem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbComArremActionPerformed(evt);
            }
        });
       
       
       
       
        if(itemOrcamento.getContramarco().toString().contentEquals("Não") && 
        	itemOrcamento.getContramarcoInferior().toString().contentEquals("Não") ) {
    	   
            rbSemContra.setSelected(true);
            cbContram.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Não"}));
            cbContram2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Não"}));
            cbContram.setEnabled(false);
            cbContram2.setEnabled(false);
           
        } 
        else {
            rbComContra.setSelected(true);
            cbContram.setModel(new javax.swing.DefaultComboBoxModel<>(itemOrcamentoDAO.carregarComboxContramarco()));
            cbContram2.setModel(new javax.swing.DefaultComboBoxModel<>(itemOrcamentoDAO.carregarComboxContramarco()));
            cbContram.setSelectedItem(itemOrcamento.getContramarco().toString());
            cbContram2.setSelectedItem(itemOrcamento.getContramarcoInferior().toString());
        }
       
        if(itemOrcamento.getArremate().toString().contentEquals("Não") &&
            itemOrcamento.getArremateInferior().toString().contentEquals("Não")) {
    	   
        	rbSemArrem.setSelected(true);
            cbArremate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Não"}));
            cbArremate2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Não"}));
            cbArremate.setEnabled(false);
            cbArremate2.setEnabled(false);
           
        }
        else {
        	rbComArrem.setSelected(true);
   	        cbArremate.setModel(new javax.swing.DefaultComboBoxModel<>(itemOrcamentoDAO.carregarComboxArremate()));
            cbArremate2.setModel(new javax.swing.DefaultComboBoxModel<>(itemOrcamentoDAO.carregarComboxArremate()));
   	   	    cbArremate.setSelectedItem(itemOrcamento.getArremate());
            cbArremate2.setSelectedItem(itemOrcamento.getArremateInferior());
        }
       
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(pnMedidas);
        pnMedidas.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbAltura)
                .addGap(18, 18, 18)
                .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbLargura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtLargura, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbQuant)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAltura)
                    .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLargura)
                    .addComponent(txtLargura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbQuant)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btAlterar.setText("ALTERAR");
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtualizarActionPerformed(evt);
            }
        });

        btCancelar.setText("CANCELAR");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        pnContram.setBorder(javax.swing.BorderFactory.createTitledBorder("Contramarco"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(pnContram);
        pnContram.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbSemContra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbComContra)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbLatSup, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(cbContram, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbInf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbContram2, 0, 120, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLatSup)
                    .addComponent(lbInf, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbSemContra)
                    .addComponent(rbComContra)
                    .addComponent(cbContram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbContram2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnArremate.setBorder(javax.swing.BorderFactory.createTitledBorder("Arremate"));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(pnArremate);
        pnArremate.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbSemArrem)
                .addGap(18, 18, 18)
                .addComponent(rbComArrem, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lbLatSup2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cbArremate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbArremate2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbInf2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLatSup2)
                    .addComponent(lbInf2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbArremate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbSemArrem)
                    .addComponent(rbComArrem)
                    .addComponent(cbArremate2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btAlterar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btCancelar)
                .addGap(35, 35, 35))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnMedidas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbObrigatorio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCabecalho, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnContram, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnArremate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(lbCabecalho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbObrigatorio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnContram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnArremate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnMedidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAlterar)
                    .addComponent(btCancelar))
                .addContainerGap())
        );

        pack();
       
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

    }    
  
    /**
     * Cria a janela e todos os seus componentes para alterar um produto no orçamento
     * @param orcamento - o orçamento que contém o produto a ser alterado
     * @param produto - o produto a ser alterado
     */
    private void initComponents(Orcamento orcamento,Produto produto) {
	   
	    orcamentoAberto = orcamento;
	    produtoAberto = produto;
	   
	    setTitle("Sistema para cálculo de esquadrias -> Orçamentos -> Itens orçamento -> Incluir produto");

        bgContram = new javax.swing.ButtonGroup();
        bgArrem = new javax.swing.ButtonGroup();
        pnMedidas = new javax.swing.JPanel();
        pnContram = new javax.swing.JPanel();
        pnArremate = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbAltura = new javax.swing.JLabel();
        lbLargura = new javax.swing.JLabel();
        lbLatSup = new javax.swing.JLabel();
        lbQuant = new javax.swing.JLabel();
        lbInf = new javax.swing.JLabel();
        lbLatSup2 = new javax.swing.JLabel();
        lbInf2 = new javax.swing.JLabel();
        lbObrigatorio = new javax.swing.JLabel();
        lbCabecalho = new javax.swing.JLabel();
        txtAltura = new javax.swing.JTextField();
        txtLargura = new javax.swing.JTextField();
        txtQuantidade = new javax.swing.JTextField();
        btIncluir = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        cbContram = new javax.swing.JComboBox<>();
        cbArremate = new javax.swing.JComboBox<>();
        cbContram2 = new javax.swing.JComboBox<>();
        cbArremate2 = new javax.swing.JComboBox<>();
        rbSemContra = new javax.swing.JRadioButton();
        rbComContra = new javax.swing.JRadioButton();
        rbSemArrem = new javax.swing.JRadioButton();
        rbComArrem = new javax.swing.JRadioButton(); 

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnMedidas.setBorder(javax.swing.BorderFactory.createTitledBorder("Medidas"));
        lbAltura.setText("*Altura (mm):");
        lbLargura.setText("*Largura (mm):");
        lbQuant.setText("*Quantidade:");
        lbLatSup.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbLatSup.setText("Laterais + Superior");
        lbInf.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbInf.setText("Inferior");
        lbLatSup2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbLatSup2.setText("Laterais + Superior");
        lbInf2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbInf2.setText("Inferior");
        lbObrigatorio.setText("Os campos com * são obrigatórios.");
        lbCabecalho.setFont(new java.awt.Font("Arial", 0, 18));
        lbCabecalho.setText("Incluindo produto nº "+produto.getIdProduto()+" no orçamento nº "+orcamento.getIdOrcamento()+".");
        bgContram.add(rbSemContra);
        rbSemContra.setText("Sem contramarco");
        rbSemContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSemContraActionPerformed(evt);
            }
        });
       
        bgContram.add(rbComContra);
        rbComContra.setText("Com contramarco");
        rbComContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbComContraActionPerformed(evt);
            }
        });
       
        bgArrem.add(rbSemArrem);
        rbSemArrem.setText("Sem arremate");
        rbSemArrem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSemArremActionPerformed(evt);
            }
        });

        bgArrem.add(rbComArrem);
        rbComArrem.setText("Com arremate");
        rbComArrem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbComArremActionPerformed(evt);
            }
        });
       
       
        rbSemContra.setSelected(true);
        rbSemArrem.setSelected(true); 
        cbContram.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Não"}));
        cbContram2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Não"}));
        cbContram.setEnabled(false);
        cbContram2.setEnabled(false);
        cbArremate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Não"}));
        cbArremate2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Não"}));
        cbArremate.setEnabled(false);
        cbArremate2.setEnabled(false);
       
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(pnMedidas);
        pnMedidas.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbAltura)
                .addGap(18, 18, 18)
                .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbLargura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtLargura, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbQuant)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAltura)
                    .addComponent(txtAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLargura)
                    .addComponent(txtLargura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbQuant)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );


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

        pnContram.setBorder(javax.swing.BorderFactory.createTitledBorder("Contramarco"));
 
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(pnContram);
        pnContram.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbSemContra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbComContra)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbLatSup, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(cbContram, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbInf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbContram2, 0, 120, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLatSup)
                    .addComponent(lbInf, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbSemContra)
                    .addComponent(rbComContra)
                    .addComponent(cbContram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbContram2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        pnArremate.setBorder(javax.swing.BorderFactory.createTitledBorder("Arremate"));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(pnArremate);
        pnArremate.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbSemArrem)
                .addGap(18, 18, 18)
                .addComponent(rbComArrem, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lbLatSup2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cbArremate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbArremate2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbInf2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addContainerGap(18, Short.MAX_VALUE))
        ); 
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLatSup2)
                    .addComponent(lbInf2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbArremate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbSemArrem)
                    .addComponent(rbComArrem)
                    .addComponent(cbArremate2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btIncluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btCancelar)
                .addGap(35, 35, 35))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnMedidas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbObrigatorio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCabecalho, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnContram, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnArremate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(lbCabecalho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbObrigatorio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnContram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnArremate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnMedidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
     * Realiza as operações necessárias para incluir um componente ao apertar o botão
     * @param evt - apertar o botão
     */
    private void btIncluirActionPerformed(java.awt.event.ActionEvent evt) {                                         
	 	
    	if(!cbContram.getSelectedItem().toString().contains("Selecione um item...") &&
		 	   !cbArremate.getSelectedItem().toString().contains("Selecione um item...") &&
			   !cbContram2.getSelectedItem().toString().contains("Selecione um item...") &&
			   !cbArremate2.getSelectedItem().toString().contains("Selecione um item...")) {
	  
 		   try {
 			   ItemOrcamentoDAO iodao = new ItemOrcamentoDAO();
 			   ItemOrcamento io = new ItemOrcamento();

 			   io.setIdOrcamento(orcamentoAberto.getIdOrcamento());
 			   io.setIdProduto(produtoAberto.getIdProduto());
 			   io.setArremate(cbArremate.getSelectedItem().toString());
 			   io.setArremateInferior(cbArremate2.getSelectedItem().toString());
 			   io.setContramarco(cbContram.getSelectedItem().toString());
 			   io.setContramarcoInferior(cbContram2.getSelectedItem().toString());
 			   io.setLarguraMm(Integer.parseInt(txtLargura.getText()));
 			   io.setAlturaMm(Integer.parseInt(txtAltura.getText()));
 			   io.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
	    	
 			   iodao.criarItemOrcamento(io);
	    	
 			   this.dispose();
 			   new TelaItensOrcamento(orcamentoAberto).setVisible(true);;
	    	
 		   } 
 		   catch (Exception e) { 
 			   JOptionPane.showMessageDialog(null, "Certifique-se que os três campos estão preenchidos "
 					   + "com números!"); 
 		   }
	   
    	} 
    	else {
    		JOptionPane.showMessageDialog(null, "selecione um item para o contramarco e arremate ou escolha a opção \"Sem arremate e/ou contramarco\"!");
		}
    }                                             

    /**
     * Realiza as operações necessárias para cancelar e fechar a janela ao apertar o botão
     * @param evt - apertar o botão
     */
    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {                                         
	   
    	if(idItemOrcamento != -1) {
		    OrcamentoDAO oDAO =  new OrcamentoDAO();
		    ItemOrcamento io = itemOrcamentoDAO.lerObjItemOrcamento(idItemOrcamento);
		    Orcamento o = oDAO.lerObjOrcamento(io.getIdOrcamento());
		    this.dispose();
	   	    new TelaItensOrcamento(o).setVisible(true);;
	    } 
    	else {
		   this.dispose();
		   new TelaItensOrcamento(orcamentoAberto).setVisible(true);
	    }
	}                                        

    /**
     * Realiza as operações necessárias para alterar um componente ao apertar o botão
     * @param evt - apertar o botão
     */
	private void btAtualizarActionPerformed(java.awt.event.ActionEvent evt) {                                         
	
	    if(!cbContram.getSelectedItem().toString().contains("Selecione um item...") &&
		   !cbArremate.getSelectedItem().toString().contains("Selecione um item...") &&
		   !cbContram2.getSelectedItem().toString().contains("Selecione um item...") &&
		   !cbArremate2.getSelectedItem().toString().contains("Selecione um item...")) {
	    	try {
    		
	        	ItemOrcamento io = itemOrcamentoDAO.lerObjItemOrcamento(idItemOrcamento);
	        		
	        	OrcamentoDAO oDAO = new OrcamentoDAO();
	        	Orcamento o = oDAO.lerObjOrcamento(io.getIdOrcamento());
	        	
	        	io.setAlturaMm(Integer.parseInt(txtAltura.getText()));
	        	io.setLarguraMm(Integer.parseInt(txtLargura.getText()));
	        	io.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
	        	
	        	io.setArremate(cbArremate.getSelectedItem().toString());
	        	io.setArremateInferior(cbArremate2.getSelectedItem().toString());
	        	io.setContramarco(cbContram.getSelectedItem().toString());
		    	io.setContramarcoInferior(cbContram2.getSelectedItem().toString());
	
	        	itemOrcamentoDAO.update(io);
	        	
	        	this.dispose();
	        	new TelaItensOrcamento(o).setVisible(true);;
        	
        	} 
	    	catch (Exception e) { 
	    		JOptionPane.showMessageDialog(null, "Certifique-se que os tres campos estão preenchidos " + "com números!"); 
	    	}
	    } 
	    else {
	    	JOptionPane.showMessageDialog(null, "selecione um item para o contramarco e arremate ou escolha a opção \"Sem arremate e/ou contramarco\"!");
	    }
    }                                                                                    

	/**
	 * Desativa a combobox de contramarco ao marcar a caixa
	 * @param evt - selecionar a caixa
	 */
    private void rbSemContraActionPerformed(java.awt.event.ActionEvent evt) {  
	   
	    if(rbSemContra.isSelected()) {
		   
	    	cbContram.setEnabled(false);
		    cbContram2.setEnabled(false);
            cbContram.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Não"}));
            cbContram2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Não"}));
            cbContram.setSelectedItem("Não");
            cbContram2.setSelectedItem("Não");
	    }  
    }     
   
    /**
     * Ativa a combobox de contramarco ao marcar a caixa
     * @param evt - selecionar a caixa
     */
    private void rbComContraActionPerformed(java.awt.event.ActionEvent evt) {                                              
	  
    	if(rbComContra.isSelected()) {
		   
    		cbContram.setEnabled(true);
		    cbContram2.setEnabled(true);
		    cbContram.setModel(new javax.swing.DefaultComboBoxModel<>(itemOrcamentoDAO.carregarComboxContramarco()));
		    cbContram2.setModel(new javax.swing.DefaultComboBoxModel<>(itemOrcamentoDAO.carregarComboxContramarco()));

		    if (idItemOrcamento!=-1) {
		 	    cbContram.setSelectedItem(itemOrcamentoDAO.lerObjItemOrcamento(idItemOrcamento).getContramarco());
			    cbContram2.setSelectedItem(itemOrcamentoDAO.lerObjItemOrcamento(idItemOrcamento).getContramarcoInferior());
		    } 
		    else {
			    cbContram.setSelectedItem("Selecione um item...");
			    cbContram2.setSelectedItem("Selecione um item...");
		    }
    	}
	}                                       

    /**
     * Desativa a combobox de arremate ao marcar a caixa
     * @param evt - selecionar a caixa
     */
    private void rbSemArremActionPerformed(java.awt.event.ActionEvent evt) {                                              
	   
	    if(rbSemArrem.isSelected()) {
		   
		    cbArremate.setEnabled(false);
		    cbArremate2.setEnabled(false);
            cbArremate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Não"}));
            cbArremate2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Não"}));
            cbArremate.setSelectedItem("Não");
            cbArremate2.setSelectedItem("Não");
	    }  
    }                                             

    /**
     * Ativa a combobox de arremate ao marcar a caixa
     * @param evt - selecionar a caixa
     */
    private void rbComArremActionPerformed(java.awt.event.ActionEvent evt) {                                              

	    if(rbComArrem.isSelected()) {
		   
		    cbArremate.setEnabled(true);
		    cbArremate2.setEnabled(true);
		    cbArremate.setModel(new javax.swing.DefaultComboBoxModel<>(itemOrcamentoDAO.carregarComboxArremate()));
		    cbArremate2.setModel(new javax.swing.DefaultComboBoxModel<>(itemOrcamentoDAO.carregarComboxArremate()));
  
		    if (idItemOrcamento!=-1) {
			    cbArremate.setSelectedItem(itemOrcamentoDAO.lerObjItemOrcamento(idItemOrcamento).getArremate());
			    cbArremate2.setSelectedItem(itemOrcamentoDAO.lerObjItemOrcamento(idItemOrcamento).getArremateInferior());
		    } 
		    else {
			    cbArremate.setSelectedItem("Selecione um item...");
			    cbArremate2.setSelectedItem("Selecione um item...");
		    }
	    }
	}                                             
   
    private javax.swing.ButtonGroup bgContram;
    private javax.swing.ButtonGroup bgArrem;
    private javax.swing.JButton btIncluir;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btAlterar;
    private javax.swing.JComboBox<String> cbContram;
    private javax.swing.JComboBox<String> cbArremate;
    private javax.swing.JComboBox<String> cbContram2;
    private javax.swing.JComboBox<String> cbArremate2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbObrigatorio;
    private javax.swing.JLabel lbAltura;
    private javax.swing.JLabel lbLargura;
    private javax.swing.JLabel lbLatSup;
    private javax.swing.JLabel lbQuant;
    private javax.swing.JLabel lbInf;
    private javax.swing.JLabel lbLatSup2;
    private javax.swing.JLabel lbInf2;
    private javax.swing.JPanel pnMedidas;
    private javax.swing.JPanel pnContram;
    private javax.swing.JPanel pnArremate;
    private javax.swing.JRadioButton rbSemContra;
    private javax.swing.JRadioButton rbComContra;
    private javax.swing.JRadioButton rbSemArrem;
    private javax.swing.JRadioButton rbComArrem;
    private javax.swing.JLabel lbCabecalho;
    private javax.swing.JTextField txtAltura;
    private javax.swing.JTextField txtLargura;
	private javax.swing.JTextField txtQuantidade;                  
}