package br.com.controleestoque.service.interfaces;

import br.com.controleestoque.dto.VendaDTO;
import br.com.controleestoque.exception.ErrorsValidationsException;
import br.com.controleestoque.model.Usuario;
import br.com.controleestoque.model.Venda;

public interface IVendaService {

	Venda realizarVenda(Long id, Usuario usuario, VendaDTO vendaDTO) throws ErrorsValidationsException;
	
	Venda buscarVendaPorIdEUsuario(Long id, Usuario usuario) throws ErrorsValidationsException;
	
}
