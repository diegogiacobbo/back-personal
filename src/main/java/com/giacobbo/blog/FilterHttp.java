package com.giacobbo.blog;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FilterHttp implements Filter {

	private final Logger log = LoggerFactory.getLogger(FilterHttp.class);

	public void SimpleCORSFilter() {
		log.info("giacobbo --> # habilitando.CORS.manual (init)");
	}

	@Override
	@Deprecated
	/**
	 * @author diego
	 * @deprectated Utilizamos um proxy midleware, que corrige cabecalhos CORS e
	 *              manipula todas as informacoes de cabecalho de origem e destino.
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse response = (HttpServletResponse) res;

		/**
		 * TODO: devemos futuramente alterar corretamente as informacoes e enviar apenas
		 * as informacoes que o host origem precisa, dessa forma pouco importa o
		 * cabecalho abaixo que e enviado na resposta. FIXME: corrigir cabecalhos para
		 * que o CORS de origem aceite, como o proxy ativado atualmente faz.
		 */
		response.setHeader("Access-Control-Allow-Origin", "https://flamboyant-boyd-b6719d.netlify.com");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"origin, x-request-with, Content-Type, Accept, X-Requested-With, remember-me");

		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void destroy() {
	}

}