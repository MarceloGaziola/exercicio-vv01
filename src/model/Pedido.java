package model;

import java.util.ArrayList;
import java.util.List;

public class Pedido extends Base{
   
	private String nomeCliente;
	private String nomeFilial;
	private List<Item> itens = new ArrayList<>();
	
	public Pedido() {
		
	}

	public Pedido(String nomeCliente, String nomeFilial, List<Item> itens) {
		super();
		this.nomeCliente = nomeCliente;
		this.nomeFilial = nomeFilial;
		this.itens = itens;
	}

	public Pedido(int codigo, String dataHoraInclusao, String dataHoraAlteracao, String nomeCliente, String nomeFilial,
			List<Item> itens) {
		super(codigo, dataHoraInclusao, dataHoraAlteracao);
		this.nomeCliente = nomeCliente;
		this.nomeFilial = nomeFilial;
		this.itens = itens;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeFilial() {
		return nomeFilial;
	}

	public void setNomeFilial(String nomeFilial) {
		this.nomeFilial = nomeFilial;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		return getCodigo() + "," + getDataHoraInclusao() + "," + nomeCliente + "," + nomeFilial + "," + itens + "\n";
	}

	//@Override
	//public String toString() {
	//	return "Pedido: " + getCodigo() + "\n" +
	//		   "Incluido em: " + getDataHoraInclusao() + "\n" +
	//		   "Alterado em: " + getDataHoraAlteracao() + "\n" +
	//		   "Nome do Cliente: " + nomeCliente + "\n" +
	//		   "Nome da Filial: " + nomeFilial + ";\n" + "\n" + 
	//			itens + "\n";				
	//}
	
	
	
}