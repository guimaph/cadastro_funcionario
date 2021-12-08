package gui;

import java.sql.Connection;
import java.sql.ResultSet;
import application.Funcionario;
import application.ModelTable;
import dao.FuncionarioDAO;
import factory.ConnectionFactory;
import gui.util.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewController {
	
	@FXML
	private Button btnAdd;
	
	@FXML
	private Button btnEdit;
	
	@FXML
	private Button btnDelete;
	
	@FXML
	private Button btnSave;
	
	@FXML
	private Button btnCancel;
	
	@FXML
	private Button btnRefresh;
	
	@FXML
	private TextField txtCodigo;
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private TextField txtSobrenome;
	
	@FXML
	private TableView<ModelTable> tblData;
	
	@FXML
	private TableColumn<ModelTable, String> colCodigo;
	
	@FXML
	private TableColumn<ModelTable, String> colNome;
	
	@FXML
	private TableColumn<ModelTable, String> colSobrenome;
	
	@FXML
	private Label lblAction;
	
	ObservableList<ModelTable> obList = FXCollections.observableArrayList();
	
	public void getData() {
		
		try {
			
			Connection connection = ConnectionFactory.openConnection();
			
			ResultSet rset = connection.createStatement().executeQuery("SELECT * FROM funcionario");
			
			while (rset.next()) {
				obList.add(new ModelTable(rset.getString(1), rset.getString(2), rset.getString(3)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		colCodigo.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
		colNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		colSobrenome.setCellValueFactory(new PropertyValueFactory<>("Sobrenome"));
		
		tblData.setItems(obList);
	}
	
	public void btnRefreshOnClick() {
		// Limpa os dados da tabela caso já exista algo
		tblData.getItems().clear();
		
		// Consulta os dados no banco de dados e os exibe na tabela
		getData();
	}
	
	public void showNavigationButtons(boolean show) {
		
		// Mostra ou oculta os botões de navegação
		btnAdd.setVisible(show);
		btnEdit.setVisible(show);
		btnDelete.setVisible(show);
		
		// Mostra ou oculta os botões "Salvar" e "Editar"
		btnSave.setVisible(!show);
		btnCancel.setVisible(!show);
		
		// Libera ou não os text fields para edição
		txtNome.setDisable(show);
		txtSobrenome.setDisable(show);
		txtNome.setEditable(!show);
		txtSobrenome.setEditable(!show);
		
		// Foca no text field "Nome" quando a ação for Incluir ou Editar
		if (!show) {
			txtNome.requestFocus();
		}
	}
	
	@FXML
	public void btnAddOnClick() {
		showNavigationButtons(false);
		lblAction.setText("0"); // INSERT
	}
	
	@FXML
	public void btnEditOnClick() {
		showNavigationButtons(false);
		lblAction.setText("1"); // UPDATE 
	}
	
	@FXML
	public void btnDeleteOnClick() {
		boolean result = Alerts.showAlert("Cadastro de Funcionários", "Atenção", "Deseja excluir permanentemente este cadastro?", AlertType.CONFIRMATION);
		
		lblAction.setText("2"); // DELETE
		
		if (result) {
			btnSaveOnClick();
			btnRefreshOnClick();
		}
	}
	
	@FXML
	public void btnSaveOnClick() {
		showNavigationButtons(true);
		String action = lblAction.getText();
		
		// Obtém os dados inseridos nos text fields
		Funcionario funcionario = new Funcionario();
		
		if (action != "0" ) {
			funcionario.setCodigo(txtCodigo.getText());
		}
		
		funcionario.setNome(txtNome.getText());
		funcionario.setSobrenome(txtSobrenome.getText());
		
		// Executa sql de inserção, edição ou exclusão de dados
		FuncionarioDAO funcDAO = new FuncionarioDAO();
		funcDAO.execute(funcionario, Integer.parseInt(action));
		
		btnRefreshOnClick();
		lblAction.setText(null);
	}
	
	@FXML
	public void btnCancelOnClick() {		
		showNavigationButtons(true);
		lblAction.setText(null);
	}
	
	@FXML
	public void tblDataOnClick() {
		
		txtNome.setDisable(false);
		txtSobrenome.setDisable(false);
		txtNome.setEditable(false);
		txtSobrenome.setEditable(false);
		
		if (lblAction.getText() != null && lblAction.getText() != "2") {
			txtNome.setEditable(true);
			txtSobrenome.setEditable(true);
		}
		
		
		Funcionario selectedFunc = tblData.getSelectionModel().getSelectedItem();
		txtCodigo.setText(selectedFunc.getCodigo());
		txtNome.setText(selectedFunc.getNome());
		txtSobrenome.setText(selectedFunc.getSobrenome());
	}
}
