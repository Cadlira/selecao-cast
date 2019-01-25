package lira.leo.selecaocast.questao1.service;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import lira.leo.selecaocast.questao1.dto.ResultDto;
import lira.leo.selecaocast.questao1.exception.DataRequiredException;
import lira.leo.selecaocast.questao1.service.util.FileSide;

/**
 * Classe contendo a regra de negócio da questão 01
 * 
 * @author leonardo.lira
 *
 */
@Service
public class Questao1Service implements IQuestao1Service {

	private static final String PATH = System.getProperty("user.home") + File.separator + "CAST" + File.separator;

	private final String FILE_PREFIX = PATH + "diff-";

	static {
		File pathDir = new File(PATH);
		if (!pathDir.exists()) {
			pathDir.mkdirs();
		}
	}

	/* (non-Javadoc)
	 * @see leo.lira.cast.questao1.service.IQuestao1Service#saveDataFile(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean saveDataFile(String base64, String id, FileSide fileSide) throws IOException {

		if (StringUtils.isEmpty(base64)) {
			throw new DataRequiredException();
		}

		File file = new File(FILE_PREFIX + fileSide.getSide() + "-" + id + ".bin");
		boolean isNew = !file.exists();
		byte[] decoded = Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));
		Path path = Paths.get(file.getAbsolutePath());
		Files.write(path, decoded, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);

		return isNew;
	}

	/* (non-Javadoc)
	 * @see leo.lira.cast.questao1.service.IQuestao1Service#compareDataFiles(java.lang.String)
	 */
	@Override
	public Object compareDataFiles(String id) throws IOException {
		RandomAccessFile left = new RandomAccessFile(FILE_PREFIX + FileSide.LEFT.getSide() + "-" + id + ".bin", "r");
		RandomAccessFile right = new RandomAccessFile(FILE_PREFIX + FileSide.RIGHT.getSide() + "-" + id + ".bin", "r");
		
		long leftLength = left.getChannel().size();
		long rightLength = right.getChannel().size();
		
		if (leftLength != rightLength) {
			left.getChannel().close();
			left.close();
			right.getChannel().close();
			right.close();
            return new ResultDto(leftLength, rightLength, null);
        }
		
		ByteBuffer bufferLeft = left.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, leftLength);
        ByteBuffer bufferRight = right.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, rightLength);
		List<Integer> offsets = new ArrayList<Integer>();
        for (int index = 0; index < leftLength; index++) {
            if (bufferLeft.get(index) != bufferRight.get(index)) {
            	offsets.add(index);
            }
        }
        
        left.getChannel().close();
		left.close();
		right.getChannel().close();
		right.close();
		
		if (CollectionUtils.isEmpty(offsets)) {
			return true;
		} else {
			return new ResultDto(leftLength, rightLength, offsets);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see leo.lira.cast.questao1.service.IQuestao1Service#deleteDataFiles(java.lang.String)
	 */
	public void deleteDataFiles(String id){
		File left = new File(FILE_PREFIX + FileSide.LEFT.getSide() + "-" + id + ".bin");
		File right = new File(FILE_PREFIX + FileSide.RIGHT.getSide() + "-" + id + ".bin");
		
		if (left.exists()) {
			left.deleteOnExit();
		}
		if (right.exists()) {
			right.deleteOnExit();
		}
	}

}
