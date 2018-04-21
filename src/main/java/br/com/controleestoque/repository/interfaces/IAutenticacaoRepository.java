package br.com.controleestoque.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.controleestoque.model.Autenticacao;

public interface IAutenticacaoRepository extends JpaRepository<Autenticacao, Long> {

	Autenticacao findOneByToken(String token); 
	
}
