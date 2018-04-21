package br.com.controleestoque.repository.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.controleestoque.model.Loja;
import br.com.controleestoque.model.Produto;

public interface IProdutoRepository extends JpaRepository<Produto, Long> {

	List<Produto> findByLoja(Loja loja);
	
	Produto findOneById(Long id);
	
}
