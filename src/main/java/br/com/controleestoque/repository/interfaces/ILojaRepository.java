package br.com.controleestoque.repository.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.controleestoque.model.Loja;
import br.com.controleestoque.model.Usuario;

public interface ILojaRepository extends JpaRepository<Loja, Long>{

	List<Loja> findByUsuario(Usuario usuario);
	
	Loja findOneByIdAndUsuario(Long id, Usuario usuario);
	
	Loja findOneById(Long id);
	
	
}
