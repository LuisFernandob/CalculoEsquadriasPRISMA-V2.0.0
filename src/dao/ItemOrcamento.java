package dao;

public class ItemOrcamento {
	
    private int idItensOrcamento;
    private int idOrcamento;
    private int idProduto;
    private String contramarco = new String();
    private String contramarcoInferior = new String();
    private String arremate = new String();
    private String arremateInferior = new String();
    private int larguraMm;
    private int alturaMm;
    private int quantidade;
    private double pesoTotal;   
    private double valorTotal;        

    public int getIdItensOrcamento() {
        return idItensOrcamento;
    }

    public void setIdItensOrcamento(int idItensOrcamento) {
        this.idItensOrcamento = idItensOrcamento;
    }

    public int getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(int idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getLarguraMm() {
        return larguraMm;
    }

    public void setLarguraMm(int larguraMm) {
        this.larguraMm = larguraMm;
    }

    public int getAlturaMm() {
        return alturaMm;
    }

    public void setAlturaMm(int alturaMm) {
        this.alturaMm = alturaMm;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(double pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

	public String getContramarco() {
		return contramarco;
	}

	public void setContramarco(String contramarco) {
		this.contramarco = contramarco;
	}

	public String getArremate() {
		return arremate;
	}

	public void setArremate(String arremate) {
		this.arremate = arremate;
	}

	public String getContramarcoInferior() {
		return contramarcoInferior;
	}

	public void setContramarcoInferior(String contramarcoInferior) {
		this.contramarcoInferior = contramarcoInferior;
	}

	public String getArremateInferior() {
		return arremateInferior;
	}

	public void setArremateInferior(String arremateInferior) {
		this.arremateInferior = arremateInferior;
	}
}