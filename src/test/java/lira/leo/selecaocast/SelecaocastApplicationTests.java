package lira.leo.selecaocast;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import lira.leo.selecaocast.questao1.Questao1ApplicationTests;
import lira.leo.selecaocast.questao1.service.Questao1ServiceTest;
import lira.leo.selecaocast.questao2.Questao2Test;
import lira.leo.selecaocast.questao3.Questao3ApplicationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		Questao1ApplicationTests.class,
		Questao1ServiceTest.class,
		Questao2Test.class,
		Questao3ApplicationTest.class
})
public class SelecaocastApplicationTests {

}

