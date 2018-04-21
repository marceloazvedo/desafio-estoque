package br.com.controleestoque.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.controleestoque.dto.ItemDTO;
import br.com.controleestoque.dto.VendaDTO;
import br.com.controleestoque.exception.ErrorsValidationsException;
import br.com.controleestoque.exception.ValidationException;
import br.com.controleestoque.model.ItemVenda;
import br.com.controleestoque.model.Loja;
import br.com.controleestoque.model.Produto;
import br.com.controleestoque.model.Usuario;
import br.com.controleestoque.model.Venda;
import br.com.controleestoque.model.enums.MensagemAPI;
import br.com.controleestoque.repository.interfaces.ILojaRepository;
import br.com.controleestoque.repository.interfaces.IProdutoRepository;
import br.com.controleestoque.repository.interfaces.IVendaRepository;
import br.com.controleestoque.service.interfaces.IVendaService;

@Component("vendaService")
public class VendaServiceImpl extends GenericService implements IVendaService {

	@Autowired
	private IVendaRepository vendaRepository;
	@Autowired
	private IProdutoRepository produtoRepository;
	@Autowired
	private ILojaRepository lojaRepository;

	public Venda realizarVenda(Long idLoja, Usuario usuario, VendaDTO vendaDTO) throws ErrorsValidationsException {
		Loja loja = lojaRepository.findOneById(idLoja);
		if (loja == null)
			throw new ErrorsValidationsException(MensagemAPI.INSIRA_LOJA_VALIDA);
		if (vendaDTO == null)
			throw new ErrorsValidationsException(MensagemAPI.NADA_ENCONTRADO);
		if (!loja.getUsuario().equals(usuario))
			throw new ErrorsValidationsException(MensagemAPI.OPERACAO_NAO_PERMITIDA);
		if (vendaDTO.getItens() == null || vendaDTO.getItens().isEmpty())
			throw new ErrorsValidationsException(MensagemAPI.INSIRA_ITENS_PARA_VENDA);
		List<ItemVenda> itens = new ArrayList<>();
		List<ValidationException> errosValidacoes = new ArrayList<>();
		for (ItemDTO item : vendaDTO.getItens()) {
			Produto produto = produtoRepository.findOneById(item.getIdProduto());
			if (produto == null) {
				throw new ErrorsValidationsException(MensagemAPI.INSIRA_PRODUTO_VALIDO);
			}
			if (!produto.getLoja().equals(loja)) {
				throw new ErrorsValidationsException(MensagemAPI.PRODUTO_LOJA_DIFERENTE);
			}
			try {
				itens.add(getItemVenda(produto, item.getQuantidade()));
			} catch (ValidationException e) {
				System.out.println(produto);
				errosValidacoes.add(e);
			}
		}
		if (!errosValidacoes.isEmpty()) {
			throw new ErrorsValidationsException(MensagemAPI.ERRO_VALIDACAO, errosValidacoes,
					"Revise a quantidade de alguns itens");
		}
		
		Venda venda = new Venda();
		venda.setDataVenda(Calendar.getInstance());
		venda.setItens(itens);
		venda.setLoja(loja);
		venda.setValorTotal(getValorTotalCompra(itens));
		venda.setItens(itens);
		validateEntity(venda);
		realizarBaixarProdutos(venda.getItens());
		
		return vendaRepository.save(venda);
	}
	
	@Override
	public Venda buscarVendaPorIdEUsuario(Long id, Usuario usuario) throws ErrorsValidationsException {
		Venda venda  = vendaRepository.findOneById(id);
		if(venda == null) {
			throw new ErrorsValidationsException(MensagemAPI.INSIRA_VENDA_VALIDA);
		}
		if(!venda.getLoja().getUsuario().equals(usuario)) {
			throw new ErrorsValidationsException(MensagemAPI.OPERACAO_NAO_PERMITIDA);
		}
		return venda;
	}

	private ItemVenda getItemVenda(Produto produto, Integer quantidade) throws ValidationException {
		if (quantidade <= 0) {
			throw new ValidationException("Quantidade inválida", String.valueOf(produto.getId()),
					String.valueOf(quantidade));
		}
		if (produto.getQuantidade() < quantidade) {
			throw new ValidationException("Quantidade indisponível para este produto", String.valueOf(produto.getId()),
					String.valueOf(quantidade));
		}
		ItemVenda item = new ItemVenda();
		item.setDescricao(produto.getDescricao());
		item.setNome(produto.getNome());
		item.setProduto(produto);
		item.setQuantidade(quantidade);
		item.setValorUnitario(produto.getValor());
		BigDecimal valorTotal = produto.getValor().multiply(new BigDecimal(quantidade));
		item.setValorTotal(valorTotal);
		return item;
	}

	private BigDecimal getValorTotalCompra(List<ItemVenda> itens) {
		BigDecimal valorTotal = BigDecimal.ZERO;
		for (ItemVenda item : itens) {
			valorTotal = valorTotal.add(item.getValorTotal());
		}
		return valorTotal;
	}
	
	private void realizarBaixarProdutos(List<ItemVenda> itens) {
		itens.forEach(item -> {
			Produto produto = item.getProduto();
			produto.decrementarQuantidade(item.getQuantidade());
			produtoRepository.save(produto);
		});
	}
}
