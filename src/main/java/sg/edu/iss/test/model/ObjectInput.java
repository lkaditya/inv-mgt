package sg.edu.iss.test.model;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

//for filtering or search purpose (sending parameter from page to controller)
public class ObjectInput {

	@DateTimeFormat (pattern="yyyy-MM-dd")
	private LocalDate start;
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private LocalDate end;
	@NotEmpty
	private String keyword;
	private String reportstate;
	public String getReportstate() {
		return reportstate;
	}
	public void setReportstate(String reportstate) {
		this.reportstate = reportstate;
	}
	private List<ProductUsage>usages;
	private Cart cart;
	
	public LocalDate getStart() {
		return start;
	}
	public void setStart(LocalDate start) {
		this.start = start;
	}
	public LocalDate getEnd() {
		return end;
	}
	public void setEnd(LocalDate end) {
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
