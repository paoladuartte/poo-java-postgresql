package projBancodeDadosPostgre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    private Connection con;

    // Conexão
    public PessoaDAO() throws SQLException {
        this.con = ConnectionFactory.getConnection();
    }

    // Adicionar
    public void adicionar(Pessoa pessoa) throws SQLException {
        String sql = "INSERT INTO pessoa "
                + "(nomePes, DataNascPes, cidadePes, ruaPes, numeroPes) "
                + "VALUES (?, ?, ?, ?, ?)";

 
        try (PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, pessoa.getNomePes());
            stmt.setDate(2, java.sql.Date.valueOf(pessoa.getDataNascPes()));
            stmt.setString(3, pessoa.getCidadePes());
            stmt.setString(4, pessoa.getRuaPes());
            stmt.setInt(5, pessoa.getNumeroPes());
            stmt.executeUpdate();

            System.out.println("Pessoa cadastrada com sucesso!");
        }
    }

    // Alterar
    public void alterar(Pessoa pessoa) throws SQLException {
        StringBuilder sql = new StringBuilder("UPDATE Pessoa SET ");
        boolean firstField = true;

        if (pessoa.getNomePes() != null) {
            sql.append("nomePes = ?");
            firstField = false;
        }

        if (pessoa.getDataNascPes() != null) {
            if (!firstField) sql.append(", ");
            sql.append("DataNascPes = ?");
            firstField = false;
        }

        if (pessoa.getCidadePes() != null) {
            if (!firstField) sql.append(", ");
            sql.append("cidadePes = ?");
            firstField = false;
        }

        if (pessoa.getRuaPes() != null) {
            if (!firstField) sql.append(", ");
            sql.append("ruaPes = ?");
            firstField = false;
        }

        if (pessoa.getNumeroPes() != 0) {
            if (!firstField) sql.append(", ");
            sql.append("numeroPes = ?");
            firstField = false;
        }

        if (firstField) {
            System.out.println("Nenhum campo foi selecionado.");
            return;
        }

        sql.append(" WHERE idPes = ?");
        try (PreparedStatement stmt = con.prepareStatement(sql.toString())) {

            int index = 1;

            if (pessoa.getNomePes() != null) {
                stmt.setString(index++, pessoa.getNomePes());
            }

            if (pessoa.getDataNascPes() != null) {
                stmt.setDate(index++, java.sql.Date.valueOf(pessoa.getDataNascPes()));
            }

            if (pessoa.getCidadePes() != null) {
                stmt.setString(index++, pessoa.getCidadePes());
            }

            if (pessoa.getRuaPes() != null) {
                stmt.setString(index++, pessoa.getRuaPes());
            }

            if (pessoa.getNumeroPes() != 0) {
                stmt.setInt(index++, pessoa.getNumeroPes());
            }

            stmt.setInt(index, pessoa.getIdPes());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Dados alterados com sucesso!");
            } else {
                System.out.println("Nenhuma pessoa encontrada com esse ID.");
            }
        }
    }

    // Apagar
    public void apagar(Pessoa pessoa) throws SQLException {

        String sql = "DELETE FROM pessoa WHERE idPes = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, pessoa.getIdPes());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Registro apagado com sucesso!");
            } else {
                System.out.println("Nenhuma pessoa encontrada com esse ID.");
            }
        }
    }

    // Listar dados
    public List<Pessoa> getLista() throws SQLException {

        String sql = "SELECT * FROM pessoa";
        List<Pessoa> pessoas = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setIdPes(rs.getInt("idPes"));
                pessoa.setNomePes(rs.getString("nomePes"));
                pessoa.setDataNascPes(rs.getString("DataNascPes"));
                pessoa.setCidadePes(rs.getString("cidadePes"));
                pessoa.setRuaPes(rs.getString("ruaPes"));
                pessoa.setNumeroPes(rs.getInt("numeroPes"));
                pessoas.add(pessoa);
            }
        }

        return pessoas;
    }
}
