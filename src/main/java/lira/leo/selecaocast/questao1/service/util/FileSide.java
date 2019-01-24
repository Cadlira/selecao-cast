package lira.leo.selecaocast.questao1.service.util;

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
