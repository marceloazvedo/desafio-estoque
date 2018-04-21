package br.com.controleestoque.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.controleestoque.model.Venda;

public interface IVendaRepository extends JpaRepository<Venda, Long> {

	Venda findOneById(Long id);
	
}
