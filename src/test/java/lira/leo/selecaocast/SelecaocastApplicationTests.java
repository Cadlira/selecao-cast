package lira.leo.selecaocast;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import lira.leo.selecaocast.questao1.Questao1ApplicationTests;
import lira.leo.selecaocast.questao1.service.Questao1ServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		Questao1ApplicationTests.class,
		Questao1ServiceTest.class
})
public class SelecaocastApplicationTests {

}

