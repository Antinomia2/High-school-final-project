package RequestHandling;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import Common.Variables;

public class DeleteCart extends Thread {
	private DataInputStream dis;
	private DataOutputStream dos;
	public DeleteCart(DataInputStream dis,DataOutputStream dos) {
		this.dis=dis;
		this.dos=dos;
	}
	public void run() {
		try {
			String username=dis.readUTF();
			String sql=String.format("delete from aggiunge_al_carrello where cliente=(select codicec from cliente where username='%s')",username);
			Variables.getStmt().executeUpdate(sql);
			System.out.println("Il cliente richiede di svuotare il carrello:");
			System.out.println(sql+"\n");
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		} finally {
			try {
				dos.close();
				dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		}
	}
}
