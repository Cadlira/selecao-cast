package lira.leo.selecaocast.questao2;

import java.util.Scanner;
import java.util.TreeSet;

public class Questao2 {

	private int tamanho;
	private int[] valores;

	public void iniciarQuestao2(Scanner scanner) {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");

		System.out.println("***************************************************");
		System.out.println("*                                                 *");
		System.out.println("*                   QUESTAO 02                    *");
		System.out.println("*                                                 *");
		System.out.println("***************************************************");

		System.out.println();
		System.out.println();

		carregarTamanho(scanner);

		carregarValores(scanner);

		calcularResposta();


	}

	public int calcularResposta() {
		TreeSet<Integer> resultado = new TreeSet<Integer>();
		for (int i=1; i<this.tamanho;i++){
			for (int j=0; j<i; j++) {
				if (this.valores[i] > this.valores[j]) {
					resultado.add(this.valores[i] - this.valores[j]);
				}
			}
		}
		if (resultado.isEmpty()) {
			return -1;
		}
		System.out.println();
		System.out.println(String.format("O resultado é %s", resultado.last()));
		return resultado.last();
	}

	private void carregarValores(Scanner scanner) {
		System.out.println("\n\n");
		this.valores = new int[this.tamanho];

		for (int indice = 0; indice < this.tamanho; indice++) {
			do {
				this.valores[indice] = getInt(String.format("Digite o %sº valor: ", (indice + 1)), scanner);
				if ((this.valores[indice] < -106 || this.valores[indice] > 106)) {
					System.out.println("Os valores devem ser maior ou igual a -106 e menor ou igual a 106");
				}
			} while (this.valores[indice] < -106 || this.valores[indice] > 106);
		}

	}

	private void carregarTamanho(Scanner scanner) {
		boolean valido = false;
		do {
			this.tamanho = getInt("Digite a quantidade de valores no array: ", scanner);
			if (tamanho >= 1 && tamanho <= 2 * 105) {
				valido = true;
			} else {
				System.out.println("A quatidade de valores deve ser maior ou igual a 1 e menor ou igual a 2x105");
			}
		} while (!valido);
	}

	private int getInt(String texto, Scanner scanner) {
		System.out.print(texto);
		String val = scanner.next();

		if (isInt(val)) {
			return Integer.parseInt(val);
		} else {
			System.out.print("O valor digitado nao é um número válido!!!");
			System.out.println();
			return getInt(texto, scanner);
		}
	}

	private boolean isInt(String numero) {
		try {
			Integer.parseInt(numero);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException nfe) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		new Questao2().iniciarQuestao2(scanner);
		scanner.close();
	}

}
