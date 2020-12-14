package sg.edu.iss.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	//link when the record usage on the header is clicked
	@RequestMapping(value="/showrecord",method=RequestMethod.GET)
	public String showRecord(Model model) {
		List<RepairOrder> group=uservice.showAllRepairOrders();
		model.addAttribute("repairlist",group);
		ObjectInput f= new ObjectInput();
		model.addAttribute("filter",f);
		
		return "recordrepair";
	}
	//link for each repair order to be clicked
	@RequestMapping(value="/showspecific/{id}",method=RequestMethod.GET)
	public String showSpecificRecord(@PathVariable("id") Long id,Model model) {
	
		model.addAttribute("repairrecord",uservice.findRepairOrderById(id));
		ObjectInput f= new ObjectInput();
		model.addAttribute("filter",f);
		
		return "recordrepairdetails";
	}
	
	//link when filter of date is clicked
	@RequestMapping(value="/filter",method=RequestMethod.POST)
	public String showFilteredRecord(@ModelAttribute("filter") ObjectInput filter,BindingResult bindingResult,Model model) {
		List<RepairOrder> group=uservice.showRepairOrderByDate(filter.getStart(), filter.getEnd());
		model.addAttribute("repairlist",group);
		
		return "recordrepair";
	}
	
	//link when hidden search button is clicked
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String showRelevantRecord(@ModelAttribute("filter") ObjectInput filter,BindingResult bindingResult,Model model) {
		List<RepairOrder> group=uservice.showRepairOrderByKeyword(filter.getKeyword());
		model.addAttribute("repairlist",group);
		
		return "recordrepair";
	}
	
	//link for each edit repair to be clicked
	@RequestMapping(value="/editrepair/{id}",method=RequestMethod.GET)
	public String editSpecificRecord(@PathVariable("id") Long id,Model model) {
	
		model.addAttribute("repairrecord",uservice.findRepairOrderById(id));
		return "recordform";
	}
	
	@RequestMapping(value="/saverepair",method=RequestMethod.POST)
	public String saveRecord(@ModelAttribute("repair") RepairOrder rep,BindingResult bindingResult,Model model) {
	
		uservice.saveRepairOrder(rep);
		return "forward:/repair/showrecord";
	}
	
	//link for each delete repair to be clicked
	@RequestMapping(value="/deleterepair/{id}",method=RequestMethod.GET)
	public String deleteSpecificRecord(@PathVariable("id") Long id,Model model) {
		RepairOrder a=uservice.findRepairOrderById(id);
		uservice.deleteRepairOrder(a);
		
		return "forward:/repair/showrecord";
	}

}
