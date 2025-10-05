package RequestHandling;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.google.gson.Gson;

import Common.Variables;
import Object.Category;

public class CategoryList extends Thread {
	private DataInputStream dis;
	private DataOutputStream dos;
	public CategoryList(DataInputStream dis,DataOutputStream dos) {
		this.dis=dis;
		this.dos=dos;
	}
	public void run() {
		try (ResultSet rs=Variables.getStmt().executeQuery("select*from tipologia;")){
			ArrayList<Category> list=new ArrayList<Category>();
			while(rs.next())
				list.add(new Category(rs.getString("nome"),rs.getString("image")));
			String json=new Gson().toJson(list);
			dos.writeUTF(json);
			System.out.println("Il cliente vuole ottenere la lista di tipologia dei piatti:");
			System.out.println("select*from tipologia");
			System.out.println(json+"\n");
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dos.close();
				dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
