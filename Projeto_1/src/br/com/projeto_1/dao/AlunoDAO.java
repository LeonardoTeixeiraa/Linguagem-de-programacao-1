/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_1.dao;

import br.com.projeto_1.dto.AlunoDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Leonardo
 */
public class AlunoDAO {

    protected Statement stmt;

   public boolean inserirAluno(AlunoDTO aluno) {
    try {
        ConexaoDAO.connectionDB();
        stmt = ConexaoDAO.conn.createStatement();
        String query = "INSERT INTO alunos (nome, cidadeOrigem, horarioIda, "
                + "horarioVolta, telefone, sobrenome, email, curso, periodo, cep, rg) VALUES ("
                + "'" + aluno.getNome() + "',"
                + "'" + aluno.getCidadeOrigem() + "',"
                + "'" + aluno.getHorarioIda() + "',"
                + "'" + aluno.getHorarioVolta() + "',"
                + "'" + aluno.getTelefone() + "',"
                + "'" + aluno.getSobrenome() + "',"
                + "'" + aluno.getEmail() + "',"
                + "'" + aluno.getCurso() + "',"
                + "'" + aluno.getPeriodo() + "',"
                + "'" + aluno.getCep() + "',"
                + "'" + aluno.getRg() + "')";
        stmt.execute(query.toUpperCase());
        ConexaoDAO.conn.commit();
        stmt.close();
        return true;
    } catch (SQLException e) {
        System.out.println("Erro: " + e.getMessage());
        return false;
    } finally {
        ConexaoDAO.closeDB();
    }
}
    public ResultSet consultarAluno(AlunoDTO alunoDTO, int opcao) {
        try {
            ConexaoDAO.connectionDB();

            stmt = ConexaoDAO.conn.createStatement();

            String query = "";

            switch (opcao) {
                case 1:
                    query = "SELECT c.* FROM alunos c "
                            + "WHERE nome like '" + alunoDTO.getNome() + "%'"
                            + "ORDER BY c.nome";
                    break;
                case 2:
                    query = "SELECT c.* FROM alunos c "
                            + "WHERE c.prontuario = " + alunoDTO.getProntuario();
                    break;
                case 3:
                    query = "SELECT c.prontuario, c.nome from alunos c";
                    break;
            }

            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public boolean atualizarAluno(AlunoDTO aluno) {
        try {
            ConexaoDAO.connectionDB();

            stmt = ConexaoDAO.conn.createStatement();

            String query = "UPDATE alunos SET "
                    + "nome = '" + aluno.getNome()+ "', "
                    + "periodo = '" + aluno.getPeriodo()+ "', "
                    + "curso = '" + aluno.getCurso()+ "', "
                    + "sobrenome = '" + aluno.getSobrenome()+ "', "
                    + "horarioIda = '" + aluno.getHorarioIda()+ "', "
                    + "horarioVolta = '" + aluno.getHorarioVolta()+ "', "
                    + "cidadeOrigem = '" + aluno.getCidadeOrigem()+ "', "
                    + "rg = '" + aluno.getRg() + "', "
                    + "email = '" + aluno.getEmail()+ "', "
                    + "telefone = '" + aluno.getTelefone()+ "' "
                    + "WHERE prontuario = " + aluno.getProntuario();

            stmt.execute(query.toUpperCase());

            ConexaoDAO.conn.commit();

            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;

        } finally {
            ConexaoDAO.closeDB();
        }

        return true;
    }

    public boolean excluirAluno(AlunoDTO alunoDTO) {
        try {
            ConexaoDAO.connectionDB();

            stmt = ConexaoDAO.conn.createStatement();
            String comando = "Delete from alunos where prontuario= "
                    + alunoDTO.getProntuario();
            stmt.execute(comando);
            ConexaoDAO.conn.commit();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoDAO.closeDB();
        }
    }

}
