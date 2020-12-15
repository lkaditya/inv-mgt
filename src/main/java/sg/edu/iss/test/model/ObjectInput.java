package sg.edu.iss.test.model;

import java.time.LocalDate;
//for filtering or search purpose (sending parameter from page to controller)
public class ObjectInput {
	
	private LocalDate start;
	private LocalDate end;
	private String keyword;
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
	

}
