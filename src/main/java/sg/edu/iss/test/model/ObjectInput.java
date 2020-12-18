package sg.edu.iss.test.model;

import java.util.List;

//for filtering or search purpose (sending parameter from page to controller)
public class ObjectInput {
	private String start;
	private String end;
	private String keyword;
	private List<ProductUsage>usages;
	private Cart cart;
	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public void addusage(ProductUsage usage) {
		this.usages.add(usage);
	}
	public List<ProductUsage> getUsages() {
		return usages;
	}
	public void setUsages(List<ProductUsage> usages) {
		this.usages = usages;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	

}
