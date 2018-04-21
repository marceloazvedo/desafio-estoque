package br.com.controleestoque.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.controleestoque.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByLogin(String login);

}
