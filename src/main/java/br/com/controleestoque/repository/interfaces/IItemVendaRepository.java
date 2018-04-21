package br.com.controleestoque.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.controleestoque.model.ItemVenda;

public interface IItemVendaRepository extends JpaRepository<ItemVenda, Long> {

}
