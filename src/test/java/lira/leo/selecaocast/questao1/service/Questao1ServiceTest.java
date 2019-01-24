package lira.leo.selecaocast.questao1.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Base64;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import lira.leo.selecaocast.questao1.dto.ResultDto;
import lira.leo.selecaocast.questao1.service.util.FileSide;

@RunWith(SpringRunner.class)
public class Questao1ServiceTest {


	private IQuestao1Service service = new Questao1Service();


	private String encodeFile(String filePath) {
		String base64Image = "";
		File file = new File(filePath);
		try (FileInputStream is = new FileInputStream(file)) {
			byte fileDate[] = new byte[(int) file.length()];
			is.read(fileDate);
			base64Image = Base64.getEncoder().encodeToString(fileDate);
		} catch (FileNotFoundException e) {
			System.out.println(String.format("File \"%s\" not found", filePath) + e);
		} catch (IOException ioe) {
			System.out.println(String.format("Exception while reading the file \"%s\"", filePath) + ioe);
		}
		return base64Image;
	}
	
	@Test
	public void saveDataFile_thenFileInserted() throws URISyntaxException, IOException{
		File file1 = new File(getClass().getClassLoader().getResource("file1.txt").toURI());
		String encFile1 = encodeFile(file1.getAbsolutePath());
		
		boolean exist = service.saveDataFile(encFile1, "teste01", FileSide.LEFT);
		assertThat(exist).isEqualTo(true);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		service.deleteDataFiles("teste01");
	}
	
	@Test
	public void saveDataFile_thenFileUpdated() throws URISyntaxException, IOException{
		File file1 = new File(getClass().getClassLoader().getResource("file1.txt").toURI());
		String encFile1 = encodeFile(file1.getAbsolutePath());
		
		boolean exist = service.saveDataFile(encFile1, "teste02", FileSide.LEFT);
		assertThat(exist).isEqualTo(true);
		exist = service.saveDataFile(encFile1, "teste02", FileSide.LEFT);
		assertThat(exist).isEqualTo(false);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		service.deleteDataFiles("teste02");
	}

	@Test
	public void compareDataFiles_thenFilesAreEqual() throws IOException, URISyntaxException {
		File file1 = new File(getClass().getClassLoader().getResource("file1.txt").toURI());
		File copiaFile1 = new File(getClass().getClassLoader().getResource("copia_file1.txt").toURI());
		String encFile1 = encodeFile(file1.getAbsolutePath());
		String encCopiaFile1 = encodeFile(copiaFile1.getAbsolutePath());
		
		service.saveDataFile(encFile1, "teste03", FileSide.LEFT);
		service.saveDataFile(encCopiaFile1, "teste03", FileSide.RIGHT);
		
		Object result = service.compareDataFiles("teste03");
		assertThat(result).isEqualTo(true);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		service.deleteDataFiles("teste03");
	}

	@Test
	public void compareDataFiles_thenFilesAreSameLengthButDiferentOffset() throws IOException, URISyntaxException {
		
		File file1 = new File(getClass().getClassLoader().getResource("file1.txt").toURI());
		File file2 = new File(Questao1ServiceTest.class.getClassLoader().getResource("file2.txt").toURI());
		String encFile1 = encodeFile(file1.getAbsolutePath());
		String encFile2 = encodeFile(file2.getAbsolutePath());
		
		service.saveDataFile(encFile1, "teste04", FileSide.LEFT);
		service.saveDataFile(encFile2, "teste04", FileSide.RIGHT);
		
		Object result = service.compareDataFiles("teste04");
		assertThat(result).isInstanceOf(ResultDto.class).hasFieldOrPropertyWithValue("leftLength", 5L)
		.hasFieldOrPropertyWithValue("rightLength", 5L).hasFieldOrProperty("offsetDiffs");
		ResultDto resultDto = (ResultDto) result;
		
		assertThat(resultDto.getOffsetDiffs()).isNotNull().contains(4);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		service.deleteDataFiles("teste04");
	}
	
	@Test
	public void compareDataFiles_thenFilesAreDiferentLength() throws IOException, URISyntaxException {
		
		File file1 = new File(getClass().getClassLoader().getResource("file1.txt").toURI());
		File file3 = new File(Questao1ServiceTest.class.getClassLoader().getResource("file3.txt").toURI());
		String encFile1 = encodeFile(file1.getAbsolutePath());
		String encFile3 = encodeFile(file3.getAbsolutePath());
		
		service.saveDataFile(encFile1, "teste05", FileSide.LEFT);
		service.saveDataFile(encFile3, "teste05", FileSide.RIGHT);
		
		Object result = service.compareDataFiles("teste05");
		assertThat(result).isInstanceOf(ResultDto.class).hasFieldOrPropertyWithValue("leftLength", 5L)
				.hasFieldOrPropertyWithValue("rightLength", 15L);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		service.deleteDataFiles("teste05");
		
		
	}
	
	
}
