package funcao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import br.com.banco.ConexaoBO;
import br.com.getset.Cliente;

public class ClienteDP {
	private ArrayList<Cliente> cliente = new ArrayList<Cliente>();
	private String n;
	private String tab;
	private int cod;
	ConexaoBO conn = new ConexaoBO();

	public void inserir(int cod, String n) {
		PreparedStatement pstm;
		try {
			pstm = conn.getConect().prepareStatement(
					"INSERT INTO cliente(cod_cliente, nm_cliente) values(?,?)");
			pstm.setInt(1, cod);
			pstm.setString(2, n);

			pstm.execute();
			System.out.println("Cadastro executado com sucesso!");
			System.out.println();
			pstm.close();
			conn.getConect().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void listar() {
		Statement stmt;
		try {
			stmt = conn.getConect().createStatement();
			ResultSet rs = stmt
					.executeQuery("select * from cliente order by cod_cliente asc");
			Cliente temp;
			while (rs.next()) {
				temp = new Cliente();
				temp.setCod_Cliente(rs.getInt("cod_cLiente"));
				temp.setNom_Cliente(rs.getString("nm_cliente"));
				cliente.add(temp);
				System.out.println("-----Cliente----- \n" + "Código: "
						+ temp.getCod_Cliente() + "\n" + "Nome:"
						+ temp.getNom_Cliente() + "\n"
						+ "--------------------------");
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remover() {
		PreparedStatement ptmt = null;
		try {
			ptmt = conn.getConect().prepareStatement(
					"DELETE FROM `bancocad`.`cliente` WHERE `cliente`.`cod_cliente` = "
							+ cod);
			System.out.println("Cliente removido com sucesso.");
			ptmt.execute();
			ptmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void atualizar() {
		PreparedStatement ptmt = null;
		try {
			ptmt = conn.getConect().prepareStatement("UPDATE" + tab + " SET nm_cliente" + "=" + "'" + n + "'"+ " WHERE nm_cliente =" + cod);
			System.out.println("Cadastro atualizado com sucesso.");
			ptmt.execute();
			ptmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
