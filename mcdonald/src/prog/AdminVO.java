package prog;

public class AdminVO {
	private int id;
	private String itemName;
	private String itemExp;
	private String itemInfo;
	private int price;
	private String itemType;
	
	
	public AdminVO(int id, String itemName, String itemExp, String itemInfo, int price, String itemType) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.itemExp = itemExp;
		this.itemInfo = itemInfo;
		this.price = price;
		this.itemType = itemType;
	}
	@Override
	public String toString() {
		return "BoardVO [id=" + id + ", itemName=" + itemName + ", itemExp=" + itemExp + ", itemInfo=" + itemInfo
				+ ", price=" + price + ", itemType=" + itemType + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemExp() {
		return itemExp;
	}
	public void setItemExp(String itemExp) {
		this.itemExp = itemExp;
	}
	public String getItemInfo() {
		return itemInfo;
	}
	public void setItemInfo(String itemInfo) {
		this.itemInfo = itemInfo;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	

	
}
