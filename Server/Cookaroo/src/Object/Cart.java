package Object;

public class Cart {
	private String username,dish;
    private int quantity;
    public Cart(String username,String dish,int quantity){
        this.username=username;
        this.dish=dish;
        this.quantity=quantity;
    }
	public String getUsername() {
		return username;
	}
	public String getDish() {
		return dish;
	}
	public int getQuantity() {
		return quantity;
	}
}
