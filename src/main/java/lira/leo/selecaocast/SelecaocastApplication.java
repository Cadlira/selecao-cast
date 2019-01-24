package lira.leo.selecaocast;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lira.leo.selecaocast.questao2.Questao2;

@SpringBootApplication
public class SelecaocastApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelecaocastApplication.class, args);
		
		
		verificarQuestao2();
	}
	
	private static void verificarQuestao2(){
		Scanner scanner = new Scanner(System.in);
		String valor = "";
		do{
			valor = scanner.next();
		}while(!("questao2".equalsIgnoreCase(valor)||"questao02".equalsIgnoreCase(valor)||"sair".equalsIgnoreCase(valor)));
		
		if (!"sair".equalsIgnoreCase(valor)) {
			new Questao2().iniciarQuestao2(scanner);
			System.out.println("\n\n");
			verificarQuestao2();
		} else {
			scanner.close();
		}
		
	}

}

