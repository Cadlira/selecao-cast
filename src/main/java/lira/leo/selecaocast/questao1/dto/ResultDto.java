package lira.leo.selecaocast.questao1.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Classe responsável pelo retorno do método de comparação 
 *  
 * @author leonardo.lira
 */
@JsonInclude(Include.NON_NULL)
public class ResultDto {


	public long leftLength;
	
	public long rightLength;
	
	public List<Integer> offsetDiffs = new ArrayList<Integer>();

	public ResultDto(long leftLength, long rightLength, List<Integer> offsetDiffs) {
		super();
		this.leftLength = leftLength;
		this.rightLength = rightLength;
		this.offsetDiffs = offsetDiffs;
	}
	
	

	public long getLeftLength() {
		return leftLength;
	}

	public void setLeftLength(long leftLength) {
		this.leftLength = leftLength;
	}

	public long getRightLength() {
		return rightLength;
	}

	public void setRightLength(long rightLength) {
		this.rightLength = rightLength;
	}

	public List<Integer> getOffsetDiffs() {
		return offsetDiffs;
	}

	public void setOffsetDiffs(List<Integer> offsetDiffs) {
		this.offsetDiffs = offsetDiffs;
	}
	
	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return super.toString();
		}
	}
	
}
