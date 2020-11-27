package guiIncluirAlterar;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import dao.Cliente;
import dao.ClienteDAO;
import gui.TelaClientes;

@SuppressWarnings("serial")
public class JTableIncluirAlterarCliente extends javax.swing.JFrame implements KeyListener {
	
	int idClienteAlterar = -1;
	String nomeCliente = new String();

    /**
     * Cria a janela para inserir clientes
     */
    public JTableIncluirAlterarCliente() {
    	addKeyListener(this);
  	    setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    	initComponents();
       
    }
    
    /**
     * Cria a janela para alterar clientes
     * @param cliente - o cliente a ser alterado
     */
    public JTableIncluirAlterarCliente(Cliente cliente) {
    	addKeyListener(this);
  	    setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    	initComponents(cliente);
    }

    /**
     * Converte a data do formato compatível com o MySQL para o formato dd/mm/aa
     * @param data - a data inserida no campo em formato dd-mm-aa
     * @return - a data inserida no campo em formato dd/mm/aa
     */
	public String converterDataMysqlParaJTable(String data) {
		
		String[] elementos = data.split("-",3);
 		String dataJTable = elementos[2];
 		dataJTable = dataJTable.concat("/");
 		dataJTable = dataJTable.concat(elementos[1]);
 		dataJTable = dataJTable.concat("/");
 		dataJTable = dataJTable.concat(elementos[0]);
	    return dataJTable;			
	}
	
	/**
	 * Converte a data para o formato compatível com o MySQL
	 * @param data - a data inserida no campo em formato dd/mm/aa
	 * @return - a data inserida no campo em formato dd-mm-aa
	 */
	public String converterDataJTableParaMysql(String data)  {
				
			if(!data.isEmpty()) {
			String[] elementos = data.split("/",3);
	 		String datamysql = elementos[2];
	 		datamysql = datamysql.concat("-");
	 		datamysql = datamysql.concat(elementos[1]);
	 		datamysql = datamysql.concat("-");
	 		datamysql = datamysql.concat(elementos[0]);
		 	return datamysql;
			} else { String blank = new String("0000-00-00");
				return blank;}
			
	}

	/**
	 * Cria a janela e todos os seus componentes para incluir clientes
	 */
	private void initComponents() {
	
		setTitle("Sistema para cálculo de esquadrias -> Clientes -> Incluir");

        jPanel1 = new javax.swing.JPanel();
        lbNome = new javax.swing.JLabel();
        lbEmpresa = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        lbEnd = new javax.swing.JLabel();
        lbCpf = new javax.swing.JLabel();
        lbCnpj = new javax.swing.JLabel();
        lbObs = new javax.swing.JLabel();
        lbTel = new javax.swing.JLabel();
        lbNasc = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtEmpresa = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtCPF = new javax.swing.JTextField();
        txtEndereco = new javax.swing.JTextField();
        txtCNPJ = new javax.swing.JTextField();
        txtObservacoes = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JTextField();
        txtNascimento = new javax.swing.JTextField();
        labelCabecalho = new javax.swing.JLabel();
        btIncluir = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        lbNome.setText("Nome:*");
        lbEmpresa.setText("Empresa:");
        lbEmail.setText("Email:");
        lbEnd.setText("Endereço:");
        lbCpf.setText("CPF:*");
        lbCnpj.setText("CNPJ:");
        lbObs.setText("Observações:");
        lbTel.setText("Telefone:");
        lbNasc.setText("Nascimento:");
        
        txtEmpresa = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtCPF = new javax.swing.JTextField();
        txtEndereco = new javax.swing.JTextField();
        txtCNPJ = new javax.swing.JTextField();
        txtObservacoes = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JTextField();
        txtNascimento = new javax.swing.JTextField();
        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbEmail)
                            .addComponent(lbCpf))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbCnpj)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTel)
                            .addComponent(lbNasc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNascimento, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                            .addComponent(txtTelefone)))
                    .addComponent(txtObservacoes)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbObs)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbEmpresa)
                            .addComponent(lbNome)
                            .addComponent(lbEnd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEndereco)
                            .addComponent(txtNome)
                            .addComponent(txtEmpresa))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmpresa)
                    .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEnd)
                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTel)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCpf)
                    .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCnpj)
                    .addComponent(txtCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNasc)
                    .addComponent(txtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbObs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtObservacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addContainerGap())
        );


        labelCabecalho.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        labelCabecalho.setText("Novo cadastro de cliente.");

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

        jLabel11.setText("Os campos com * são obrigatórios.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCabecalho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btIncluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btCancelar))
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCabecalho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btIncluir)
                    .addComponent(btCancelar))
                .addContainerGap())
        );

        pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    	this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

    }// </editor-fold>          
    
    /**
     * Cria a janela e todos os seus componentes para alterar clientes
     * @param cliente - o cliente a ser alterado
     */
    private void initComponents(Cliente cliente) {
    	
    	idClienteAlterar = cliente.getIdCliente();
    	nomeCliente = cliente.getNome();

  	    setTitle("Sistema para cálculo de esquadrias -> Clientes -> Alterar");

        jPanel1 = new javax.swing.JPanel();
        lbNome = new javax.swing.JLabel();
        lbEmpresa = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        lbEnd = new javax.swing.JLabel();
        lbCpf = new javax.swing.JLabel();
        lbCnpj = new javax.swing.JLabel();
        lbObs = new javax.swing.JLabel();
        lbTel = new javax.swing.JLabel();
        lbNasc = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtEmpresa = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtCPF = new javax.swing.JTextField();
        txtEndereco = new javax.swing.JTextField();
        txtCNPJ = new javax.swing.JTextField();
        txtObservacoes = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JTextField();
        txtNascimento = new javax.swing.JTextField();
        labelCabecalho = new javax.swing.JLabel();
        btCancelar = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        lbNome.setText("Nome:*");
        lbEmpresa.setText("Empresa:");
        lbEmail.setText("Email:");
        lbEnd.setText("Endereço:");
        lbCpf.setText("CPF:*");
        lbCnpj.setText("CNPJ:");
        lbObs.setText("Observações:");
        lbTel.setText("Telefone:");
        lbNasc.setText("Nascimento:");
        
        txtCNPJ.setText(cliente.getCnpj());
        txtCPF.setText(cliente.getCpf());
        txtEmail.setText(cliente.getEmail());
        txtEmpresa.setText(cliente.getEmpresa());
        txtEndereco.setText(cliente.getEndereco());
        txtNascimento.setText(converterDataMysqlParaJTable(cliente.getDataNascimento()));
        txtNome.setText(cliente.getNome());
        txtObservacoes.setText(cliente.getObservacoes());
        txtTelefone.setText(cliente.getTelefone());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbEmail)
                            .addComponent(lbCpf))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbCnpj)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTel)
                            .addComponent(lbNasc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNascimento, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                            .addComponent(txtTelefone)))
                    .addComponent(txtObservacoes)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbObs)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbEmpresa)
                            .addComponent(lbNome)
                            .addComponent(lbEnd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEndereco)
                            .addComponent(txtNome)
                            .addComponent(txtEmpresa))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmpresa)
                    .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEnd)
                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTel)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCpf)
                    .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCnpj)
                    .addComponent(txtCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNasc)
                    .addComponent(txtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbObs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtObservacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addContainerGap())
        );


        labelCabecalho.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        labelCabecalho.setText("Editando cliente nº "+ idClienteAlterar+" - "+nomeCliente);

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

        jLabel11.setText("Os campos com * são obrigatórios.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCabecalho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btAlterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btCancelar))
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCabecalho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
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
     * Realiza as operações necessárias para incluir um cliente ao apertar o botão
     * @param evt - apertar o botão
     */
    private void btIncluirActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	  
    	if (!txtNome.getText().isEmpty()) {
    	Cliente c = new Cliente();
    	ClienteDAO cDAO = new ClienteDAO();
    	
    	
    	c.setNome(txtNome.getText());
		c.setEmpresa(txtEmpresa.getText());
		c.setTelefone(txtTelefone.getText());
		c.setEndereco(txtEndereco.getText());
		c.setEmail(txtEmail.getText());
		c.setDataNascimento(converterDataJTableParaMysql(txtNascimento.getText()));
		c.setCnpj(txtCNPJ.getText());
		c.setCpf(txtCPF.getText());
		c.setObservacoes(txtObservacoes.getText());
		
		cDAO.criarCliente(c);
		
		txtCNPJ.setText("");
        txtCPF.setText("");
        txtEmail.setText("");
        txtEmpresa.setText("");
        txtEndereco.setText("");
        txtNascimento.setText("");
        txtNome.setText("");
        txtObservacoes.setText("");
        txtTelefone.setText("");
		
        this.dispose();
        new TelaClientes().setVisible(true);
    	} 
    	else {
    		JOptionPane.showMessageDialog(null, "e");
    	}
    }                                        
   
    /**
     * Realiza as operações necessárias para cancelar e fechar a janela ao apertar o botão
     * @param evt - apertar o botão
     */
    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {    
        
        this.dispose();
        new TelaClientes().setVisible(true);
    }                   
    
    /**
     * Realiza as operações necessárias para alterar um cliente ao apertar o botão
     * @param evt - apertar o botão
     */
    private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {                                         
    		
    		
            Cliente c = new Cliente();
            ClienteDAO dao = new ClienteDAO();
            
           	c.setIdCliente(idClienteAlterar);
    		c.setNome(txtNome.getText());
    		c.setEmpresa(txtEmpresa.getText());
    		c.setTelefone(txtTelefone.getText());
    		c.setEndereco(txtEndereco.getText());
    		c.setEmail(txtEmail.getText());
    		c.setDataNascimento(converterDataJTableParaMysql(txtNascimento.getText()));
    		c.setCnpj(txtCNPJ.getText());
    		c.setCpf(txtCPF.getText());
    		c.setObservacoes(txtObservacoes.getText());
    		
            dao.update(c);
           
            txtNome.setText("");
            txtEmpresa.setText("");
            txtTelefone.setText("");
            txtEndereco.setText("");
            txtEmail.setText("");
            txtNascimento.setText("");
            txtCNPJ.setText("");
            txtCPF.setText("");
            txtObservacoes.setText("");
            
            this.dispose();
            new TelaClientes().setVisible(true);  
        }                                        

    private javax.swing.JButton btIncluir;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btAlterar;
    private javax.swing.JLabel lbNasc;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbEmpresa;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbEnd;
    private javax.swing.JLabel lbCpf;
    private javax.swing.JLabel lbCnpj;
    private javax.swing.JLabel lbObs;
    private javax.swing.JLabel lbTel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelCabecalho;
    private javax.swing.JTextField txtCNPJ;
    private javax.swing.JTextField txtCPF;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmpresa;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNascimento;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtObservacoes;
    private javax.swing.JTextField txtTelefone;   

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
    	 
    	int key = e.getKeyCode();
    	
    	if(key == KeyEvent.VK_ENTER){
             
    		if(idClienteAlterar==-1) {
    			
	    		if (!txtNome.getText().isEmpty()) {
	    			
	    	    	Cliente c = new Cliente();
	    	    	ClienteDAO cDAO = new ClienteDAO();
	    	    	
	    	    	c.setNome(txtNome.getText());
	    			c.setEmpresa(txtEmpresa.getText());
	    			c.setTelefone(txtTelefone.getText());
	    			c.setEndereco(txtEndereco.getText());
	    			c.setEmail(txtEmail.getText());
	    			c.setDataNascimento(converterDataJTableParaMysql(txtNascimento.getText()));
	    			c.setCnpj(txtCNPJ.getText());
	    			c.setCpf(txtCPF.getText());
	    			c.setObservacoes(txtObservacoes.getText());
	    			
	    			cDAO.criarCliente(c);
	    			
	    			txtCNPJ.setText("");
	    	        txtCPF.setText("");
	    	        txtEmail.setText("");
	    	        txtEmpresa.setText("");
	    	        txtEndereco.setText("");
	    	        txtNascimento.setText("");
	    	        txtNome.setText("");
	    	        txtObservacoes.setText("");
	    	        txtTelefone.setText("");
	    			
	    	        this.dispose();
	    	        new TelaClientes().setVisible(true);
	    	    } 
	    		else {
	    	    		JOptionPane.showMessageDialog(null, "Digite o nome do cliente!");
	    	    }
    		} 
    		else {
    				Cliente c = new Cliente();
    	            ClienteDAO dao = new ClienteDAO();
    	            
    	           	c.setIdCliente(idClienteAlterar);
    	    		c.setNome(txtNome.getText());
    	    		c.setEmpresa(txtEmpresa.getText());
    	    		c.setTelefone(txtTelefone.getText());
    	    		c.setEndereco(txtEndereco.getText());
    	    		c.setEmail(txtEmail.getText());
    	    		c.setDataNascimento(converterDataJTableParaMysql(txtNascimento.getText()));
    	    		c.setCnpj(txtCNPJ.getText());
    	    		c.setCpf(txtCPF.getText());
    	    		c.setObservacoes(txtObservacoes.getText());
    	    		
    	            dao.update(c);
    	           
    	            txtNome.setText("");
    	            txtEmpresa.setText("");
    	            txtTelefone.setText("");
    	            txtEndereco.setText("");
    	            txtEmail.setText("");
    	            txtNascimento.setText("");
    	            txtCNPJ.setText("");
    	            txtCPF.setText("");
    	            txtObservacoes.setText("");
    	            
    	            this.dispose();
    	            new TelaClientes().setVisible(true);  
    		}
    	} else if(key == KeyEvent.VK_ESCAPE) {
    		this.dispose();
    		new TelaClientes().setVisible(true);
    	}
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
