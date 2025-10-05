package RequestHandling;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gson.Gson;
import Common.Variables;
import Object.FoodInfo.FoodItem;
import Object.FoodInfo.Ingredient;

public class FoodList extends Thread {
	private DataInputStream dis;
	private DataOutputStream dos;
	public FoodList(DataInputStream dis,DataOutputStream dos) {
		this.dis=dis;
		this.dos=dos;
	}
	public void run() {
		ResultSet rsFood=null;
		ResultSet rsIngredient=null;
		try {
			System.out.println("Il cliente richiede la lista dei piatti appartenente alla categoria, e le loro informazioni:INGREDIENTI(allergeni????), prezzo, voto media:");
			String tipologia=dis.readUTF();
			String sqlpiatto=(String.format("select*from piatto where tipologia='%s';", tipologia));
			System.out.println(sqlpiatto);
			rsFood=Variables.getStmt().executeQuery(sqlpiatto);
			ArrayList<FoodItem> foodlist=new ArrayList<FoodItem>();
			while(rsFood.next()) {
				String sqlIngredient=(String.format("select*from ingrediente where piatto='%s';", rsFood.getString("codicep")));
				System.out.println(sqlIngredient);
				rsIngredient=Variables.getStmt().executeQuery(sqlIngredient);
				ArrayList<Ingredient> foodIngredient=new ArrayList<Ingredient>();
				while(rsIngredient.next())
					foodIngredient.add(new Ingredient(rsIngredient.getString("nome"),rsIngredient.getBoolean("allergeni")));
				foodlist.add(new FoodItem(rsFood.getString("nome"),rsFood.getFloat("prezzo"),rsFood.getString("image"),rsFood.getFloat("avg_valutazione"),rsFood.getString("codicep"),foodIngredient));
			}
			String json=new Gson().toJson(foodlist);
			System.out.println(json+"\n");
			dos.writeUTF(json);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rsFood.close();
				rsIngredient.close();
				dos.close();
				dis.close();
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
