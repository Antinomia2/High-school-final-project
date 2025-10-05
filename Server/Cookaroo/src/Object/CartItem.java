package Object;

public class CartItem {
	private String name,image,codicep;
	private int quantity;
    private float subtotal;
    public CartItem(String name,String image,int quantity,float subtotal,String codicep){
        this.name=name;
        this.image=image;
        this.quantity=quantity;
        this.subtotal=subtotal;
        this.codicep=codicep;
    }
    public String getName() {
		return name;
	}
	public String getImage() {
		return image;
	}
	public String getCodicep() {
		return codicep;
	}
	public int getQuantity() {
		return quantity;
	}
	public float getSubtotal() {
		return subtotal;
	}
}
