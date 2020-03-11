package br.com.faculdadedelta.projetoprocessocombojsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.faculdadedelta.projetoprocessocombojsf.dao.AssuntoDAO;
import br.com.faculdadedelta.projetoprocessocombojsf.modelo.Assunto;

@FacesConverter(value = "assuntoConverter")
public class AssuntoConverter implements Converter {

	private AssuntoDAO dao = new AssuntoDAO();

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		if (valor != null) {
			try {
				return dao.pesquisarPorId(Long.valueOf(valor));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
		if (valor != null) {
			return String.valueOf(((Assunto) valor).getId());
		}
		return null;
	}

}
