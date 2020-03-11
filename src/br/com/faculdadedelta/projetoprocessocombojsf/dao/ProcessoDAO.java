package br.com.faculdadedelta.projetoprocessocombojsf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.projetoprocessocombojsf.modelo.Assunto;
import br.com.faculdadedelta.projetoprocessocombojsf.modelo.Processo;
import br.com.faculdadedelta.projetoprocessocombojsf.util.Conexao;

public class ProcessoDAO {

	public void incluir(Processo processo) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO processos (numero_proc, valor_proc, "
				+ " quantidade_proc, id_assunto) VALUES (?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, processo.getNumero().trim());
			ps.setDouble(2, processo.getValor());
			ps.setInt(3, processo.getQuatidade());
			ps.setLong(4, processo.getAssunto().getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public void alterar(Processo processo) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE processos "
				+ " SET numero_proc = ?, "
				+ " valor_proc = ?, "
				+ " quantidade_proc = ?, "
				+ " id_assunto = ? "
				+ " WHERE id_processo = ?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, processo.getNumero().trim());
			ps.setDouble(2, processo.getValor());
			ps.setInt(3, processo.getQuatidade());
			ps.setLong(4, processo.getAssunto().getId());
			ps.setLong(5, processo.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public void excluir(Processo processo) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM processos WHERE id_processo = ?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, processo.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public List<Processo> listar() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT "
				+ " p.id_processo AS idProcesso, "
				+ " p.numero_proc AS numeroProcesso, "
				+ " p.valor_proc AS valorProcesso, "
				+ " p.quantidade_proc AS quantidadeProcesso, "
				+ " a.id_assunto AS idAssunto, "
				+ " a.descricao AS descricaoAssunto "
				+ " FROM processos p "
				+ " INNER JOIN assuntos a ON p.id_assunto = a.id_assunto";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Processo> listaRetorno = new ArrayList();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Processo processo = new Processo();
				processo.setId(rs.getLong("idProcesso"));
				processo.setNumero(rs.getString("numeroProcesso").trim());
				processo.setValor(rs.getDouble("valorProcesso"));
				processo.setQuatidade(rs.getInt("quantidadeProcesso"));
				
				Assunto assunto = new Assunto();
				assunto.setId(rs.getLong("idAssunto"));
				assunto.setDescricao(rs.getString("descricaoAssunto").trim());
				
				processo.setAssunto(assunto);
				
				listaRetorno.add(processo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return listaRetorno;
	}
	
	/*public List<Processo> listar2() throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT "
				+ " p.id_processo AS idProcesso, "
				+ " p.numero_proc AS numeroProcesso, "
				+ " p.valor_proc AS valorProcesso, "
				+ " p.quantidade_proc AS quantidadeProcesso, "
				+ " a.id_assunto AS idAssunto "
				+ " FROM processos p ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Processo> listaRetorno = new ArrayList();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Processo processo = new Processo();
				processo.setId(rs.getLong("idProcesso"));
				processo.setNumero(rs.getString("numeroProcesso").trim());
				processo.setValor(rs.getDouble("valorProcesso"));
				processo.setQuatidade(rs.getInt("quantidadeProcesso"));
				
				AssuntoDAO dao = new AssuntoDAO();
				
				Assunto assunto = dao.pesquisarPorId(rs.getLong("idAssunto"));
				
				processo.setAssunto(assunto);
				
				listaRetorno.add(processo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return listaRetorno;
	}*/
}





