package sg.edu.iss.test.controller;

import java.time.LocalDate;
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
import sg.edu.iss.test.model.ProductUsage;
import sg.edu.iss.test.model.RepairOrder;
import sg.edu.iss.test.service.CustomerInterface;
import sg.edu.iss.test.service.ProductUsageImplementation;
import sg.edu.iss.test.service.ProductUsageInterface;

@Controller
@RequestMapping("/repair")
public class RepairOrderController {
	
	//To-Do: put the customer interface for editting the the repair order
	@Autowired
	private CustomerInterface custservice;
	
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
		//model.addAttribute("highlight","active");

		
		return "recordrepair";
	}
	//link for each repair order to be clicked
	@RequestMapping(value="/showspecific/{id}",method=RequestMethod.GET)
	public String showSpecificRecord(@PathVariable("id") Long id,Model model) {
	
		model.addAttribute("repairrecord",uservice.findRepairOrderById(id));
		model.addAttribute("productusage",uservice.findRepairOrderById(id).getProductUsageList());
		ObjectInput f= new ObjectInput();
		model.addAttribute("filter",f);

		
		return "recordrepairdetails";
	}
	
	
	//link when filter of date is clicked
	@RequestMapping(value="/filter")
	public String showFilteredRecord(@ModelAttribute("filter") ObjectInput filter,BindingResult bindingResult,Model model) {
		
		String start=filter.getStart();
		//System.out.println(start);
		String end=filter.getEnd();
		//System.out.println(end);
		LocalDate a= LocalDate.of(Integer.parseInt(start.split("-")[0]), Integer.parseInt(start.split("-")[1]), Integer.parseInt(start.split("-")[2]));
		LocalDate b= LocalDate.of(Integer.parseInt(end.split("-")[0]), Integer.parseInt(end.split("-")[1]), Integer.parseInt(end.split("-")[2]));
		
		List<RepairOrder> group=uservice.showRepairOrderByDate(a, b);
		System.out.println("how many group size= "+group.size());
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
	
	//link when hidden search button is clicked
	@RequestMapping(value="/detailsearch/{id}",method=RequestMethod.POST)
	public String showUsageDetailRecord(@ModelAttribute("filter") ObjectInput filter,@PathVariable("id") Long id,BindingResult bindingResult,Model model) {
		List<ProductUsage> group=uservice.showProductUsageByKeyword(filter.getKeyword());
		model.addAttribute("repairrecord",uservice.findRepairOrderById(id));
		model.addAttribute("productusage",group);
		
		return "recordrepairdetails";
	}
	//link for each edit repair to be clicked
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String editSpecificRecord(@PathVariable("id") Long id,Model model) {
	
		model.addAttribute("repairrecord",uservice.findRepairOrderById(id));
		return "recordrepairform";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveRecord(@ModelAttribute("${repairrecord}") RepairOrder rep,BindingResult bindingResult,Model model) {
		uservice.saveRepairOrder(rep);
		return "forward:/repair/showrecord";
	}
	
	//link for each delete repair to be clicked
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String deleteSpecificRecord(@PathVariable("id") Long id,Model model) {
		RepairOrder a=uservice.findRepairOrderById(id);
		uservice.deleteRepairOrder(a);
		
		return "forward:/repair/showrecord";
	}

}
