package lira.leo.selecaocast.web.exception;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Classe responsável por encapsular os erros retornados pelos endpoints
 * 
 * @author leonardo.lira
 *
 */
@JsonPropertyOrder({"statusHttp","mensagemStatusHttp","metodoHttp","urlEntrada","parametros","dataErro","urlErro","mensagemErro","excecao"})
@JsonInclude(Include.NON_NULL)
public class RestError implements Serializable {

	private static final long serialVersionUID = 5856542669856536856L;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private int statusHttp;
	private String mensagemStatusHttp;
	private String mensagemErro;
	private String metodoHttp;
	private String urlEntrada;
	private String urlErro;
	private String parametros;
	private String dataErro;
	@JsonIgnore
	private Throwable excecao;
	
	
	public RestError() {}
	
	public RestError(HttpStatus status, String mensagem, Throwable excecao) {
		this.setStatusHttp(status.value());
		this.setMensagemStatusHttp(status.getReasonPhrase());
		this.setMensagemErro(mensagem);
		this.setDataErro(new Date());
		this.setExcecao(excecao);
	}

	public RestError(HttpServletRequest request, HttpServletResponse response, String mensagem, Throwable excecao) {
		this.statusHttp = response.getStatus();
		HttpStatus status = HttpStatus.valueOf(this.statusHttp);
		if (Objects.nonNull(status)) {
			this.mensagemStatusHttp = status.getReasonPhrase();
		}else{
			this.mensagemStatusHttp = "";
		}
		
		if (Objects.nonNull(mensagem)) {
			this.mensagemErro = mensagem;
		}else if (Objects.nonNull(excecao)) {
			this.mensagemErro = excecao.toString();
		}else{
			this.mensagemErro = "";
		}
		this.dataErro = dateFormat.format(new Date());
		this.excecao = excecao;
		this.urlErro=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI();
		this.urlEntrada = (String) request.getAttribute("javax.servlet.forward.request_uri");
		if (Objects.isNull(this.urlEntrada)) {
			this.urlEntrada = this.urlErro;
		}else{
			this.urlEntrada = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + this.urlEntrada;
		}
		this.metodoHttp = request.getMethod();
		this.parametros = request.getQueryString();
	}
	

	public int getStatusHttp() {
		return statusHttp;
	}

	public void setStatusHttp(int statusHttp) {
		this.statusHttp = statusHttp;
	}

	public String getMensagemStatusHttp() {
		return mensagemStatusHttp;
	}

	public void setMensagemStatusHttp(String mensagemStatusHttp) {
		this.mensagemStatusHttp = mensagemStatusHttp;
	}	

	public String getUrlErro() {
		return urlErro;
	}

	public void setUrlErro(String urlErro) {
		this.urlErro = urlErro;
	}
	
	public Throwable getExcecao() {
		return excecao;
	}

	public void setExcecao(Throwable excecao) {
		this.excecao = excecao;
	}

	public String getDataErro() {
		return dataErro;
	}

	public void setDataErro(String dataErro) {
		this.dataErro = dataErro;
	}
	
	public void setDataErro(Date dataErro) {
		this.dataErro = dateFormat.format(dataErro);
	}

	public String getUrlEntrada() {
		return urlEntrada;
	}

	public void setUrlEntrada(String urlEntrada) {
		this.urlEntrada = urlEntrada;
	}

	public String getMetodoHttp() {
		return metodoHttp;
	}

	public void setMetodoHttp(String metodoHttp) {
		this.metodoHttp = metodoHttp;
	}

	public String getParametros() {
		return parametros;
	}

	public void setParametros(String parametros) {
		this.parametros = parametros;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
	
	public String toJson() {
		ObjectMapper mapper = new ObjectMapper();
		String retorno = "";
		try {
			retorno = mapper.writeValueAsString(this);
		} catch (Exception e) {
			retorno = toString();
		}
		return retorno;
	}


}