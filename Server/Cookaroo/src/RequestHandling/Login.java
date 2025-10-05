package RequestHandling;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.google.gson.Gson;

import Common.Methods;
import Common.Variables;
import Object.Credentials;

public class Login extends Thread {
	private DataInputStream dis;
	private DataOutputStream dos;
	public Login(DataInputStream dis,DataOutputStream dos) {
		this.dis=dis;
		this.dos=dos;
	}
	public void run() {
		ResultSet rs=null;
		try {
			String json=dis.readUTF();
			System.out.println("Il cliente richiede di accedere all'account:");
			System.out.println(json);
			Credentials c=new Gson().fromJson(json, Credentials.class);
			String sql=String.format("select * from cliente where username='%s' and password='%s' limit 1;", c.getUsername(),Methods.Atbash(c.getPassword(), 'a', 'z'));
			System.out.println(sql);
			rs=Variables.getStmt().executeQuery(sql);
			Boolean b=rs.next();
			String esito=b?"Username esiste, password corretto\n":"Username o password errato\n";
			System.out.println(esito);
			dos.writeBoolean(b);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				dos.close();
				dis.close();
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
