package lira.leo.selecaocast.questao1.service;

import java.io.IOException;

import lira.leo.selecaocast.questao1.service.util.FileSide;

public interface IQuestao1Service {

	boolean saveDataFile(String base64, String id, FileSide fileSide) throws IOException;

	Object compareDataFiles(String id) throws IOException;
	
	void deleteDataFiles(String id);

}