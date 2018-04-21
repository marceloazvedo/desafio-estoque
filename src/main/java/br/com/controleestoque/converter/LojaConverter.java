package br.com.controleestoque.converter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.controleestoque.dto.LojaDTO;
import br.com.controleestoque.model.Loja;
import br.com.controleestoque.util.StringUtils;

public class LojaConverter {

	public static Loja converter(LojaDTO lojaDTO) throws ParseException {
		if(lojaDTO == null) return null;
		Loja loja = new Loja();
		loja.setId(lojaDTO.getId());
		loja.setNome(lojaDTO.getNome());
		loja.setDataCadastro(
				lojaDTO.getDataCadastro() == null || lojaDTO.getDataCadastro().isEmpty() ? Calendar.getInstance()
						: StringUtils.getCalendarFullDate(lojaDTO.getDataCadastro()));
		loja.setProdutos(null);
		return loja;
	}

	public static LojaDTO converter(Loja loja) {
		if(loja == null) return null;
		LojaDTO lojaDTO = new LojaDTO();
		lojaDTO.setId(loja.getId());
		lojaDTO.setNome(loja.getNome());
		lojaDTO.setDataCadastro(StringUtils.getDataCompleta(loja.getDataCadastro()));
		return lojaDTO;
	}

	public static List<LojaDTO> converterListNormal(List<Loja> lojas) {
		List<LojaDTO> lista = new ArrayList<>();
		lojas.forEach(loja -> {
			lista.add(converter(loja));
		});
		return lista;
	}

	public static List<Loja> converterListDTO(List<LojaDTO> lojas) {
		List<Loja> lista = new ArrayList<>();
		lojas.forEach(loja -> {
			try {
				lista.add(converter(loja));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		});
		return lista;
	}

}
