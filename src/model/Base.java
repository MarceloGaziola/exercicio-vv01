package model;

public class Base {

	private int codigo;
	private String dataHoraInclusao;
	private String dataHoraAlteracao;
		
	public Base() {
		
	}

	public Base(int codigo, String dataHoraInclusao, String dataHoraAlteracao) {
		super();
		this.codigo = codigo;
		this.dataHoraInclusao = dataHoraInclusao;
		this.dataHoraAlteracao = dataHoraAlteracao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDataHoraInclusao() {
		return dataHoraInclusao;
	}

	public void setDataHoraInclusao(String dataHoraInclusao) {
		this.dataHoraInclusao = dataHoraInclusao;
	}

	public String getDataHoraAlteracao() {
		return dataHoraAlteracao;
	}

	public void setDataHoraAlteracao(String dataHoraAlteracao) {
		this.dataHoraAlteracao = dataHoraAlteracao;
	}

		
}
