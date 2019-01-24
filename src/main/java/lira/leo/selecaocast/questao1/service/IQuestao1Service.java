package lira.leo.selecaocast.questao1.service;

import java.io.IOException;

import lira.leo.selecaocast.questao1.dto.ResultDto;
import lira.leo.selecaocast.questao1.service.util.FileSide;

/**
 * @author leonardo.lira
 *
 */
public interface IQuestao1Service {

	/**
	 * Método responsável por salvar o arquivo utilizando os bytes serializados em base64
	 * 
	 * @param base64 bytes serializados em base64
	 * @param id identificador unico para o processo de comparação
	 * @param fileSide parâmetros para identificar qual o a tipo do arquivo
	 * @return 
	 * 		true - Caso o arquivo seja novo
	 * 		false - Caso o arquivo já exista.
	 * @throws IOException
	 */
	boolean saveDataFile(String base64, String id, FileSide fileSide) throws IOException;

	/**
	 * Método responsável pela comparação dos arquivos em base64
	 * 
	 * @param id identificador unico para o processo de comparação
	 * @return 
	 * 		true - caso os arquivos sejam iguais
	 * 		@see ResultDto - Caso os arquivos tenham o mesmo tamanho, retorna o tamanho e o offset
	 * 						 dos bytes onde estão as diferenças. Caso possuam tamnhos diferentes 
	 * 						 retorna apenas os tamanhos.
	 * 						
	 * @throws IOException
	 */
	Object compareDataFiles(String id) throws IOException;
	
	/**
	 * Método utilizado para delatar os arquivos físicos 
	 * 
	 * @param id identificador unico para o processo de remoção
	 */
	void deleteDataFiles(String id);

}