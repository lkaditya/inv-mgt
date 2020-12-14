package sg.edu.iss.test.model;

import java.time.LocalDate;
//for filtering purpose (sending parameter from page to controller)
public class Filter {
	
	private LocalDate start;
	private LocalDate end;
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
	

}
