package Object;

public class RegisterInfo {
	private String username,password,nome,cognome,telefono,indirizzo;
    public RegisterInfo(String username,String password,String nome,String cognome,String telefono,String indirizzo){
        this.username=username;
        this.password=password;
        this.nome=nome;
        this.cognome=cognome;
        this.telefono=telefono;
        this.indirizzo=indirizzo;
    }
    public String getUsername() {
    	return username;
    }
    public String getPassword() {
    	return password;
    }
    public String getNome() {
    	return nome;
    }
    public String getCognome() {
    	return cognome;
    }
    public String getTelefono() {
    	return telefono;
    }
    public String getIndirizzo() {
    	return indirizzo;
    }
}
