package br.com.faculdadedelta.projetoprocessocombojsf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.projetoprocessocombojsf.modelo.Assunto;
import br.com.faculdadedelta.projetoprocessocombojsf.util.Conexao;

public class AssuntoDAO {

	public void incluir(Assunto assunto) 
			throws Exception {
		
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO assuntos (descricao) VALUES (?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, assunto.getDescricao().trim());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public void alterar(Assunto assunto) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE assuntos SET descricao = ? WHERE id_assunto = ?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, assunto.getDescricao().trim());
			ps.setLong(2, assunto.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public void excluir(Assunto assunto) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM assuntos WHERE id_assunto = ?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, assunto.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public List<Assunto> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_assunto, descricao FROM assuntos";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Assunto> listaRetorno = new ArrayList();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Assunto assunto = new Assunto();
				assunto.setId(rs.getLong("id_assunto"));
				assunto.setDescricao(rs.getString("descricao").trim());
				listaRetorno.add(assunto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return listaRetorno;
	}
	
	public Assunto pesquisarPorId(Long id) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_assunto, descricao FROM assuntos WHERE id_assunto = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Assunto retorno = new Assunto();
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				retorno.setId(rs.getLong("id_assunto"));
				retorno.setDescricao(rs.getString("descricao").trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		
		return retorno;
	}
}




