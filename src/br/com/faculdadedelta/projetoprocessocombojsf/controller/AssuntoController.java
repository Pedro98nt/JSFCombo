package br.com.faculdadedelta.projetoprocessocombojsf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.projetoprocessocombojsf.dao.AssuntoDAO;
import br.com.faculdadedelta.projetoprocessocombojsf.modelo.Assunto;

@ManagedBean
@SessionScoped
public class AssuntoController {

	private Assunto assunto = new Assunto();
	private AssuntoDAO dao = new AssuntoDAO();

	public Assunto getAssunto() {
		return assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}

	public void limparCampos() {
		assunto = new Assunto();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {
			if (assunto.getId() == null) {
				dao.incluir(assunto);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			} else {
				dao.alterar(assunto);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, "
					+ "tente novamente mais tarde! " + e.getMessage());
		}
		return "cadastroAssunto.xhtml";
	}

	public String editar() {
		return "cadastroAssunto.xhtml";
	}
	
	public String excluir() {
		try {
			dao.excluir(assunto);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, "
					+ "tente novamente mais tarde! " + e.getMessage());			
		}
		return "listaAssunto.xhtml";
	}
	
	public List<Assunto> getLista() {
		List<Assunto> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, "
					+ "tente novamente mais tarde! " + e.getMessage());			
		}
		return listaRetorno;
	}
	
}
