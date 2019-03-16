package model;

public class Item extends Pedido{
    
	private int codItem;
	private String descricaoItem;
	private int quantItem;		
	private Double valorItem;
		
	
	public Item() {
		
	}

	public Item(int codItem, String descricaoItem, int quantItem, Double valorItem) {
		super();
		this.codItem = codItem;
		this.descricaoItem = descricaoItem;
		this.quantItem = quantItem;
		this.valorItem = valorItem;
	}

	public int getCodItem() {
		return codItem;
	}

	public void setCodItem(int codItem) {
		this.codItem = codItem;
	}

	public String getDescricaoItem() {
		return descricaoItem;
	}

	public void setDescricaoItem(String descricaoItem) {
		this.descricaoItem = descricaoItem;
	}

	public int getQuantItem() {
		return quantItem;
	}

	public void setQuantItem(int quantItem) {
		this.quantItem = quantItem;
	}

	public Double getValorItem() {
		return valorItem;
	}

	public void setValorItem(Double valorItem) {
		this.valorItem = valorItem;
	}

	@Override
	public String toString() {
		return  "Item: " + codItem + "\n" + 
				"  Descrição Item: " + descricaoItem + "\n" + 
				"  Quantidade: " + quantItem + "\n" +
				"  Valor Item=" + valorItem + "\n";
				
	}	
	
}
