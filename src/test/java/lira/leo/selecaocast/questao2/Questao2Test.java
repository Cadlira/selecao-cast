package lira.leo.selecaocast.questao2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class Questao2Test {

	@Test
	public void calcularResposta_sampleCase0(){
		Questao2 q2 = new Questao2();
		q2.setTamanho(7);
		int[]valores = {2, 3, 10, 2, 4, 8, 1};
		q2.setValores(valores);
		
		assertThat(q2.calcularResposta()).isEqualTo(8);
	}
	
	@Test
	public void calcularResposta_sampleCase1(){
		Questao2 q2 = new Questao2();
		q2.setTamanho(6);
		int[]valores = {7, 9, 5, 6, 3, 2};
		q2.setValores(valores);
		
		assertThat(q2.calcularResposta()).isEqualTo(2);
	}
}
