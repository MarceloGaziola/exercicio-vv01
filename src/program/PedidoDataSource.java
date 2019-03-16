package program;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.*;

public class PedidoDataSource {

	private static Scanner ent;	
	static int codigoPedido = 0;	
	static List<Pedido> pedidos = new ArrayList<Pedido>();
	
	public static void main(String[] args) {
		menu();
	}
	
	public static void menu() {
		System.out.println("\nMENU PRINCIPAL PEDIDOS\n");
		System.out.println("1 - CONSULTAR");
		System.out.println("2 - INCLUIR");
		System.out.println("3 - ALTERAR");
		System.out.println("4 - EXCLUIR");
		System.out.println("5 - SAIR");
		System.out.println("");
		
		System.out.print("Digite a opção desejada: ");
		
		try 
		{
		    ent = new Scanner(System.in);	
		    int opcao = ent.nextInt();		   
		    verificarOpcao(opcao);
		} 
		catch (InputMismatchException e) 
		{
			System.err.println("Digite um valor numérico");
			System.out.println("");
			menu();
		}		
	}

	public static void verificarOpcao(int opcao) {
		boolean opcaoValida = false;
		
		switch (opcao) {
		case 1:	
			opcaoValida = true;
			consultar();
			break;
		case 2:		
			opcaoValida = true;
			incluir();
			break;
		case 3:			
			break;	
		case 4:		
			opcaoValida = true;
			excluir();
			break;
		case 5:		
			opcaoValida = true;
			sair();
			break;
		default:
			break;
		}
		
		if (!opcaoValida) {
			System.err.println("Digite uma opção valida!");
			System.out.println();
		}
		
		menu();
	}
	
	public static void incluir() {
		
		ent = new Scanner(System.in);		
		System.out.println("");
		
		codigoPedido = codigoPedido + 1;
		
		System.out.println("Nome do Cliente:");
		String nomeCliente = ent.nextLine();
		
		System.out.println("Nome da Filial:");
		String nomeFilial = ent.nextLine();
		
		String dataHoraInclusao = obterDataSistema();					
		String dataHoraAlteracao = null;
		
		System.out.println("Informar quantidade de itens:");
		int quantItens = ent.nextInt();		
	
        Pedido p = new Pedido();
		
		p.setCodigo(codigoPedido);
		p.setNomeCliente(nomeCliente);
		p.setNomeFilial(nomeFilial);
		p.setDataHoraAlteracao(dataHoraAlteracao);
		p.setDataHoraInclusao(dataHoraInclusao);
		
		List<Item> listItemPedido = new ArrayList<Item>();
		
		for (int i=1; i <= quantItens; i++) {
			ent = new Scanner(System.in);		
			System.out.println("");
			
			System.out.println("Descrição do item:");
			String descricaoItem = ent.nextLine();
			
			System.out.println("Quantidade:");
			int quantItem = ent.nextInt();
			
			System.out.println("Valor do Item:");
			Double valorItem = ent.nextDouble();
			
			Item itemPedido = new Item(i, descricaoItem, quantItem, valorItem);
			listItemPedido.add(itemPedido);
			
			p.setItens(listItemPedido);
			
		}				
		pedidos.add(p);		
	}
	
	public static void consultar() {
		System.out.println("");
		if (pedidos.size() == 0) {
			System.out.println("NENHUM PEDIDO CADASTRADO");
		}
		else
		{
			for (Pedido p : pedidos) {
				System.out.println(p);
			}
		}
	}

	public static void excluir() {
		System.out.println("");
		ent = new Scanner(System.in);
		if (pedidos.size() > 0) {
			System.out.println("Digite o pedido que deseja excluir: ");
			int pedidoDigitado = ent.nextInt();
			boolean pedidoEncontrado = false;
			for (Pedido p : pedidos) {
				if (p.getCodigo() == pedidoDigitado) {
					pedidos.remove(p);
					pedidoEncontrado = true;
					break;
				}
			}
			if(!pedidoEncontrado) {
				System.err.println("Pedido não encontrado!");
			} else {
				System.out.println("Pedido removido com sucesso!");
			}
		}else {
			System.err.println("NENHUM PEDIDO ENCONTRADO!");
		}
		System.out.println("");
	}	
	
	public static void sair() {
		System.out.println("");
		System.out.println("Finalizando o programa ...... ");
		System.out.println("");		
		System.exit(0);
	}
	
	public static String obterDataSistema() {
		Date data = new Date();
		SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dataFormatada = formatar.format(data);
		return dataFormatada;
	}
	
	public static void setEnt(Scanner ent) {
		PedidoDataSource.ent = ent;
	}	

	public static Scanner getEnt() {
		return ent;
	}	
	
}
