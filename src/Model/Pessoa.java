package Model;

public class Pessoa {
	
  private	int    IDpessoa;
  private 	String PESSOA_nome;
  private	String PESSOA_cpf;
  
  PessoaSexo  pessoaSexoEnum;            
  PessoaTipo  pessoaTipoEnum;  
  Turma Turma_idTurma;  
  
  
    
   
  
 public PessoaSexo getPessoaSexoEnum() {
	return pessoaSexoEnum;
}

public void setPessoaSexoEnum(PessoaSexo pessoaSexoEnum) {
	this.pessoaSexoEnum = pessoaSexoEnum;
}


public PessoaTipo getPessoaTipoEnum() {
	return pessoaTipoEnum;
}



public void setPessoaTipoEnum(PessoaTipo pessoaTipoEnum) {
	this.pessoaTipoEnum = pessoaTipoEnum;
}





public Turma getTurma_idTurma() {
	return Turma_idTurma;
}





public void setTurma_idTurma(Turma turma_idTurma) {
	Turma_idTurma = turma_idTurma;
}





public int getIDpessoa() {
	return IDpessoa;
}





public void setIDpessoa(int iDpessoa) {
	IDpessoa = iDpessoa;
}





public String getPESSOA_nome() {
	return PESSOA_nome;
}





public void setPESSOA_nome(String pESSOA_nome) {
	PESSOA_nome = pESSOA_nome;
}





public String getPESSOA_cpf() {
	return PESSOA_cpf;
}





public void setPESSOA_cpf(String pESSOA_cpf) {
	PESSOA_cpf = pESSOA_cpf;
}





public static void main(String[] args) {
	
	 
	 
}

  
  
  
  
  
  

}
