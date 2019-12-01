package br.com.bahia.bancos.modelo;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import java.math.BigDecimal;

public class Client 
{
	public static void main(String[] args)
	{
		try
		{
			Banco b = (Banco) Naming.lookup("//localhost/Banco"); //Utiliza RMI para conectar com o servidor
			Scanner sc1 = new Scanner(System.in);
			System.out.println("-------------- BAHIA BANCO --------------\n");
			System.out.println("| Usu�rio:");
			String n_conta = sc1.nextLine();
			System.out.println("| Senha:");
			String senha = sc1.nextLine();
			if(b.acessar_conta(senha, n_conta)) {
				System.out.println("Conta acessada");
				
				if(b.acessar_conta(senha,n_conta)) {
					System.out.println("-------------- BAHIA BANCO --------------\n");
					System.out.println("---------------- OP��ES -----------------\n");
					System.out.println("1 - SACAR");
					System.out.println("2 - DEPOSITAR");
					System.out.println("3 - TRANSFER�NCIA");
					System.out.println("4 - SALDO");
					System.out.println("5 - EXTRATO");
					System.out.println("|OP��O: ");
					int opcao  = sc1.nextInt();
					sc1.nextLine();
					BigDecimal valor;
					switch(opcao) {
						case 1:
							System.out.println("||Valor do Saque: ");
							valor = sc1.nextBigDecimal();
							sc1.nextLine();
							System.out.println("||Confirme sua senha: ");
							senha = sc1.next();
							b.sacar(senha, n_conta, valor);
							break;
						case 2:
							System.out.println("||Valor do Dep�sito: ");
							valor = sc1.nextBigDecimal();
							sc1.nextLine();
							System.out.println("||Confirme sua senha: ");
							senha = sc1.next();
							b.deposito(senha, n_conta, valor);
							break;
						case 3:
							System.out.println("||Valor da Transfer�ncia: ");
							valor = sc1.nextBigDecimal();
							sc1.nextLine();
							System.out.println("||Conta de destino: ");
							String n_conta_dest = sc1.next();
							System.out.println("||Confirme sua senha: ");
							senha = sc1.next();
							b.transferencia(senha, n_conta, n_conta_dest, valor);
							break;
						case 4:
							System.out.println("||Confirme sua senha: ");
							senha = sc1.next();
							System.out.println(b.saldo(senha, n_conta));
							break;
						case 5:
							System.out.println("||Confirme sua senha: ");
							senha = sc1.next();
							System.out.println(b.extrato(senha, n_conta));
							break;
						default:
							System.out.println("Opera��o inv�lida");
					}
				}
			}
			else {
				System.out.println("N�o foi poss�vel acessar a conta");
			}
			
		}
		catch (Exception e)
		{
			System.out.println(e);
		
			try
			{
				///Banco b = (Banco) Naming.lookup("//127.0.0.1:1099/Banco");
			
			}
			
			catch (Exception i)
			{
				System.out.println("N�o foi Poss�vel acessar o Servidor 2");
			}
		}
	}
}