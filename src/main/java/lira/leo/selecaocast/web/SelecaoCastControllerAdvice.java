package lira.leo.selecaocast.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lira.leo.selecaocast.questao1.exception.DataRequiredException;
import lira.leo.selecaocast.web.exception.RestError;

/**
 * Classe responsável por interceptar os erros lançados pelos endpoints
 * 
 * @author leonardo.lira
 *
 */
@ControllerAdvice
public class SelecaoCastControllerAdvice {

	/**
	 * Método responsável por tratar as exceções genericas
	 * 
	 * @param request 
	 * @param response
	 * @param e exceção capturada
	 * @return
	 * 		{@link RestError} - objeto com os dados do erro capturado
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public RestError exception(HttpServletRequest request, HttpServletResponse response, Exception e) {
		RestError restError = new RestError(request, response, null, e);
		restError.setStatusHttp(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		restError.setMensagemStatusHttp(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());	
		return restError;
	}
	
	/**
	 * Método responsável por tratar as exceções do tipo @see {@link DataRequiredException}
	 * 
	 * @param request 
	 * @param response
	 * @param e exceção capturada
	 * @return
	 * 		{@link RestError} - objeto com os dados do erro capturado
	 */
	@ExceptionHandler(DataRequiredException.class)
	@ResponseBody
	public RestError dataRequiredException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		RestError restError = new RestError(request, response, "O arquivo Base64 é obrigatório", e);
		restError.setStatusHttp(HttpStatus.EXPECTATION_FAILED.value());
		response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
		restError.setMensagemStatusHttp(HttpStatus.EXPECTATION_FAILED.getReasonPhrase());	
		return restError;
	}
}
