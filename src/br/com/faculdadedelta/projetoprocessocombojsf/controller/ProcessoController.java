package br.com.faculdadedelta.projetoprocessocombojsf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.projetoprocessocombojsf.dao.ProcessoDAO;
import br.com.faculdadedelta.projetoprocessocombojsf.modelo.Assunto;
import br.com.faculdadedelta.projetoprocessocombojsf.modelo.Processo;

@ManagedBean
@SessionScoped
public class ProcessoController {

	private Processo processo = new Processo();
	private ProcessoDAO dao = new ProcessoDAO();
	private Assunto assuntoSelecionado = new Assunto();

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}
	
	public Assunto getAssuntoSelecionado() {
		return assuntoSelecionado;
	}

	public void setAssuntoSelecionado(Assunto assuntoSelecionado) {
		this.assuntoSelecionado = assuntoSelecionado;
	}

	public void limparCampos() {
		processo = new Processo();
		assuntoSelecionado = new Assunto();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {
			if (processo.getId() == null) {
				processo.setAssunto(assuntoSelecionado);
				dao.incluir(processo);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			} else {
				processo.setAssunto(assuntoSelecionado);
				dao.alterar(processo);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, "
					+ "tente novamente mais tarde! " + e.getMessage());			
		}
		return "cadastroProcesso.xhtml";
	}

	public String editar() {
		assuntoSelecionado = processo.getAssunto();
		return "cadastroProcesso.xhtml";
	}
	
	public String excluir() {
		try {
			dao.excluir(processo);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, "
					+ "tente novamente mais tarde! " + e.getMessage());			
		}
		return "listaProcesso.xhtml";
	}
	
	public List<Processo> getLista() {
		List<Processo> listaRetorno = new ArrayList<>();
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

