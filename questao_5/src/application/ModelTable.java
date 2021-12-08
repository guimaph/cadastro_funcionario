package application;

public class ModelTable extends Funcionario{
	
	String fun_codigo, fun_nome, fun_sobrenome;

	public ModelTable(String fun_codigo, String fun_nome, String fun_sobrenome) {
		this.fun_codigo = fun_codigo;
		this.fun_nome = fun_nome;
		this.fun_sobrenome = fun_sobrenome;
	}

	public String getCodigo() {
		return fun_codigo;
	}

	public void setCodigo(String codigo) {
		this.fun_codigo = codigo;
	}

	public String getNome() {
		return fun_nome;
	}

	public void setNome(String fun_nome) {
		this.fun_nome = fun_nome;
	}

	public String getSobrenome() {
		return fun_sobrenome;
	}

	public void setSobrenome(String fun_sobrenome) {
		this.fun_sobrenome = fun_sobrenome;
	}
	
}
