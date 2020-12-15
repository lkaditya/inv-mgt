package sg.edu.iss.test.model;


//for filtering or search purpose (sending parameter from page to controller)
public class ObjectInput {
	private String start;
	private String end;
	private String keyword;
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
	

}
