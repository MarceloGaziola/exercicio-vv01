package program;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

import javafx.animation.PauseTransition;
import model.*;

public class PedidoDataSourceArquive {

	static Scanner ent;	
	static int codigoPedido = 0;	
	static List<Pedido> pedidos = new ArrayList<Pedido>();	
	static File arquivo = new File("c:\\geracao\\pedidos.txt");
	
	public static void main(String[] args) {
		if (arquivo.exists()) {
			carregar();
		}		
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
			
			System.out.println("Deseja salvar? (s/n)");
			String op = ent.next();
			op = op.toUpperCase();
			
			switch (op) {
				case "S":
					salvar();
					sair();
					break;
					
				case "N":				
					sair();
					break;
					
				default:
					System.out.println("Opção invalida!");
					break;
			}
			
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
			
			System.out.println("\nCodigo do Item:");
			String codItem = ent.next();
			System.out.println("Descrição do item:");
			String descricaoItem = ent.next();
			System.out.println("Quantidade:");
			int quantItem = ent.nextInt();
			System.out.println("Valor do Item:");
			Double valorItem = ent.nextDouble();		
			Item itemPedido = new Item(codItem, descricaoItem, quantItem, valorItem);
			listItemPedido.add(itemPedido);
			
			p.setItens(listItemPedido);

						
		}				
		pedidos.add(p);		
	}
	
	public static void consultar() {
		System.out.println("");
		if (pedidos.size() == 0) {
			System.err.println("NENHUM PEDIDO CADASTRADO!");
		}
		else
		{			
			for (Pedido p : pedidos) {			
			    String arq = p.toString();
			    
				arq = arq.replace("[", "");
				arq = arq.replace("]", "");
				
                String dados[] = arq.split(",");				
				int qtdeItem = (dados.length - 4) / 4;
				
				System.out.println("----Pedido -------------------------------\n");
				System.out.println("Codigo do Pedido.: " + dados[0]);
				System.out.println("Hora de Inclusao.: " + dados[1]);
				System.out.println("Hora de Alteracao: " + "null");
				System.out.println("Nome do Cliente..: " + dados[2]);				
				System.out.println("Nome da Filial...: " + dados[3]);
				
				int ind = 4;
				
				System.out.println("\n----Itens do Pedido ---------------------\n");
				
				for (int i=1; i <= qtdeItem; i++) {
					System.out.println("Codigo do Item...: " + dados[ind]);					
					ind++;
					System.out.println("Descrição do Item: " + dados[ind]);					
					ind++;
					System.out.println("Quantidade.......: " + dados[ind]);					
					ind++;
					System.out.println("Valor do Item....: " + dados[ind] + "\n");					
					ind++;				
				}
				
			}
		}
	}

	public static void salvar() {
		FileUtils.deleteQuietly(arquivo);
		for (Pedido p : pedidos) {				
				try 
				{					
					FileUtils.writeStringToFile(arquivo, p.toString(),true);					
				} 		
				catch (IOException e) 
				{				
					e.printStackTrace();
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
			System.err.println("\nNENHUM PEDIDO ENCONTRADO!");
		}
		System.out.println("");
	}	

	public static void carregar() {

		try 
		{			
			List<String> lista = FileUtils.readLines(arquivo);
				
			for (String arq : lista) {			
				
				arq = arq.replace("[", "");
				arq = arq.replace("]", "");
				
				String dados[] = arq.split(",");
				
				int qtdeItem = (dados.length - 4) / 4;
				
				Pedido p = new Pedido();
				
				codigoPedido = Integer.parseInt(dados[0]);
				p.setCodigo(Integer.parseInt(dados[0]));
				p.setDataHoraInclusao(dados[1]);
				p.setDataHoraAlteracao(null);
				p.setNomeCliente(dados[2]);
				p.setNomeFilial(dados[3]);
				
				List<Item> listItemPedido = new ArrayList<Item>();
				
				int ind = 4;
				
				for (int i=1; i <= qtdeItem; i++) {
					String codItem = dados[ind];
					ind++;
					String descricaoItem = dados[ind];
					ind++;
					int	quantItem = Integer.parseInt(dados[ind]);
					ind++;
					double valorItem = Double.parseDouble(dados[ind]);
					ind++;
					Item itemPedido = new Item(codItem, descricaoItem, quantItem, valorItem);
					listItemPedido.add(itemPedido);
					p.setItens(listItemPedido);
				}
				pedidos.add(p);
			}			
		} 
		catch (IOException e) 
		{
			System.out.println("Erro na leitura do arquivo - " + e.getMessage());
		}	
	}	
	
	public static void sair() {			
		System.out.println("Programa finalizado!");
		System.exit(0);
	}
	
	public static String obterDataSistema() {
		Date data = new Date();
		SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dataFormatada = formatar.format(data);
		return dataFormatada;
	}
	
	public static void setEnt(Scanner ent) {
		PedidoDataSourceArquive.ent = ent;
	}	

	public static Scanner getEnt() {
		return ent;
	}	
	
}
