package Object;

public class Order {
	private String cliente,tempoconsegna;
    private float costototale;
    private CartItem[] cartItems;
    public Order(String cliente,String tempoconsegna,float costototale,CartItem[] cartItems){
        this.cliente=cliente;
        this.tempoconsegna=tempoconsegna;
        this.costototale=costototale;
        this.cartItems=cartItems;
    }
	public String getCliente() {
		return cliente;
	}
	public String getTempoconsegna() {
		return tempoconsegna;
	}
	public float getCostototale() {
		return costototale;
	}
	public CartItem[] getCartItems() {
		return cartItems;
	}
}
