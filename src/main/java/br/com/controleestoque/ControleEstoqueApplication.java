package br.com.controleestoque;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import br.com.controleestoque.model.Usuario;
import br.com.controleestoque.repository.interfaces.IUsuarioRepository;

@ComponentScan(basePackages = {"br.com.controleestoque.filter", "br.com.controleestoque.controller", "br.com.controleestoque.service",
		"br.com.controleestoque.service.interfaces" })
@EnableAutoConfiguration
@SpringBootApplication
public class ControleEstoqueApplication implements CommandLineRunner {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(ControleEstoqueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(usuarioRepository.findAll().isEmpty()) {
			Usuario usuario = new Usuario();
			usuario.setDataCadastro(Calendar.getInstance());
			usuario.setEmail("admin@admin.com.br");
			usuario.setLogin("admin");
			usuario.setNome("Marcelo Santos de Azevedo");
			usuario.setSenha("123456");
			usuarioRepository.save(usuario);
		}
		System.out.println("Iniciando...");
	}
}
