package br.com.controleestoque.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.controleestoque.model.Autenticacao;
import br.com.controleestoque.repository.interfaces.IAutenticacaoRepository;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AppFilter implements Filter {

	private static final int NAO_AUTORIZADO = 401;
	private static final List<String> PUBLIC_URLS = Arrays.asList(new String[] { "/api/autenticar" });
	private static final int HOUR_INCREMENT = 5; 
	
	@Autowired
	private IAutenticacaoRepository autenticacaoRepository;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String token = request.getHeader("Authorization");
		if(request.getRequestURI().contains("/api/") && !PUBLIC_URLS.contains(request.getRequestURI())) {
			if(token == null || token.isEmpty()) {
				response.setStatus(NAO_AUTORIZADO);
				return;
			}
			Autenticacao autenticacao = autenticacaoRepository.findOneByToken(token);
			if(autenticacao == null || autenticacao.getValidade().before(Calendar.getInstance())) {
				response.setStatus(NAO_AUTORIZADO);
				return;
			} else {
				Calendar validade = Calendar.getInstance();
				validade.add(Calendar.HOUR, HOUR_INCREMENT);
				autenticacao.setValidade(validade);
				autenticacaoRepository.save(autenticacao);
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
