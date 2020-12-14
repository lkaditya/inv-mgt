package sg.edu.iss.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.test.model.ObjectInput;
import sg.edu.iss.test.model.RepairOrder;
import sg.edu.iss.test.service.ProductUsageImplementation;
import sg.edu.iss.test.service.ProductUsageInterface;

@Controller
@RequestMapping("/repair")
public class RepairOrderController {
	
	@Autowired
	private ProductUsageInterface uservice;
	
	@Autowired
	public void setProductUsage(ProductUsageImplementation usage) {
		this.uservice=usage;
	}
	
	@RequestMapping(value="/showrecord",method=RequestMethod.GET)
	public String showRecord(Model model) {
		List<RepairOrder> group=uservice.showAllRepairOrders();
		model.addAttribute("repairlist",group);
		ObjectInput f= new ObjectInput();
		model.addAttribute("filter",f);
		
		return "recordusage";
	}
	
	@RequestMapping(value="/filter",method=RequestMethod.POST)
	public String showFilteredRecord(@ModelAttribute("filter") ObjectInput filter,BindingResult bindingResult,Model model) {
		List<RepairOrder> group=uservice.showRepairOrderByDate(filter.getStart(), filter.getEnd());
		model.addAttribute("repairlist",group);
		
		return "recordusage";
	}
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String showRelevantRecord(@ModelAttribute("filter") ObjectInput filter,BindingResult bindingResult,Model model) {
		List<RepairOrder> group=uservice.showRepairOrderByKeyword(filter.getKeyword());
		model.addAttribute("repairlist",group);
		
		return "recordusage";
	}

}
