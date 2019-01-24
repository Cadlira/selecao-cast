package lira.leo.selecaocast.questao1.service.util;

/**
 * Enum responsável para identificar o tipo do arquivo
 * 
 * @author leonardo.lira
 *
 */
public enum FileSide {
	LEFT("left"), RIGHT("right");
	
	private String side;

	FileSide(String side){
		this.side = side;
	}

	public String getSide() {
		return side;
	}
}
