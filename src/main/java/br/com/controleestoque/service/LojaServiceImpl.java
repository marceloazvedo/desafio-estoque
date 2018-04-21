package br.com.controleestoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.controleestoque.exception.ErrorsValidationsException;
import br.com.controleestoque.model.Loja;
import br.com.controleestoque.model.Usuario;
import br.com.controleestoque.model.enums.MensagemAPI;
import br.com.controleestoque.repository.interfaces.ILojaRepository;
import br.com.controleestoque.service.interfaces.ILojaService;

@Component("lojaService")
public class LojaServiceImpl extends GenericService implements ILojaService {

	@Autowired
	private ILojaRepository lojaRepository;

	@Override
	public Loja salvarOuAtualizar(Loja loja) throws ErrorsValidationsException {
		validateEntity(loja);
		if (loja.getId() != null) {
			Loja l = lojaRepository.findOneById(loja.getId());
			if (l == null)
				throw new ErrorsValidationsException(MensagemAPI.INSIRA_LOJA_VALIDA);
			if (!l.getUsuario().equals(loja.getUsuario()))
				throw new ErrorsValidationsException(MensagemAPI.NADA_ENCONTRADO);
		}
		return lojaRepository.save(loja);
	}

	@Override
	public List<Loja> listar(Usuario usuario) throws ErrorsValidationsException {
		return lojaRepository.findByUsuario(usuario);
	}

	@Override
	public Loja getLojaPorIdEUsuario(Long id, Usuario usuario) throws ErrorsValidationsException {
		Loja loja = lojaRepository.findOneByIdAndUsuario(id, usuario);
		if (loja == null) {
			throw new ErrorsValidationsException(MensagemAPI.INSIRA_LOJA_VALIDA);
		}
		return loja;
	}

}
