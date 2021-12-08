package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import application.Funcionario;
import factory.ConnectionFactory;
import gui.util.Alerts;
import javafx.scene.control.Alert.AlertType;

public class FuncionarioDAO {
	
	public void execute(Funcionario funcionario, int action) {
		
		Connection connection = null;
		String sql = null, message = null;
		PreparedStatement execSql = null;
		if(action == 0) {
			sql = "INSERT INTO funcionario (fun_nome, fun_sobrenome) VALUES (?, ?)";
			
		} else if(action == 1) {
			sql = "UPDATE funcionario SET fun_nome = ?, fun_sobrenome = ? WHERE fun_codigo = ?";
			
		} else if(action == 2) {
			sql = "DELETE from funcionario WHERE fun_codigo = ?";
			
		} else {
			Alerts.showAlert("Funcionário", "Erro", "Algo deu errado!\n\nFeche o programa e tente novamente.", AlertType.ERROR);
		}
		
		
		try {
			connection = ConnectionFactory.openConnection();
			
			execSql = connection.prepareStatement(sql);
			
			// Prepara a query SQL
			
			if (action != 2) {
				execSql.setString(1, funcionario.getNome());
				execSql.setString(2, funcionario.getSobrenome());
			}
			
			if (action == 2) {
				execSql.setString(1, funcionario.getCodigo());
			} else if (action == 1) {
				execSql.setString(3, funcionario.getCodigo());
			}
			
			// Executa a query SQL e armazena o resultado ("sucesso" ou "erro")
			execSql.execute();
			
			if (action == 0) {
				message = "Funcionário Cadastrado!";
			} else if (action == 1) {
				message = "Dados atualizados!";
			} else if (action == 2) {
				message = "Funcionário excluído!";
			}
			
			Alerts.showAlert("Cadastro de Funcionários", "Sucesso", message, AlertType.INFORMATION);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (execSql != null) {
					execSql.close();
				}
				
				if (connection != null) {
					connection.close();
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

/*	public List<Funcionario> getFuncionarios() {
		String sql = "SELECT * FROM funcionario";
		
		List<Funcionario> funcionarioList = new ArrayList<Funcionario>();
		
		Connection connection = null;
		PreparedStatement execSql = null;
		ResultSet rset = null;
		
		try {
			connection = ConnectionFactory.openConnection();
			
			execSql = connection.prepareStatement(sql);
			
			rset = execSql.executeQuery();
			
			while (rset.next()) {
				Funcionario funcionario = new Funcionario();
				
				funcionario.setCodigo(rset.getString("fun_codigo"));
				funcionario.setNome(rset.getString("fun_nome"));
				funcionario.setSobrenome(rset.getString("fun_sobrenome"));
				
				funcionarioList.add(funcionario);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				
				if (connection != null) {
					connection.close();
				}
				
				if (rset != null) {
					rset.close();
				}
				
				if (execSql != null) {
					execSql.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return funcionarioList;
	}*/
} 
