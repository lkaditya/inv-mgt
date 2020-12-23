package sg.edu.iss.test.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.test.model.Customer;
import sg.edu.iss.test.model.ObjectInput;
import sg.edu.iss.test.model.ProductUsage;
import sg.edu.iss.test.model.RepairOrder;
import sg.edu.iss.test.service.CustomerService;
import sg.edu.iss.test.service.ProductUsageImplementation;
import sg.edu.iss.test.service.ProductUsageService;

@Controller
@RequestMapping("/repair")
public class RepairOrderController {

	@Autowired
	private CustomerService custservice;
	
	@Autowired
	private ProductUsageService uservice;
	
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
		model.addAttribute("control","record");

		
		return "recordrepair";
	}
	//link for each repair order to be clicked
	@RequestMapping(value="/showspecific/{id}",method=RequestMethod.GET)
	public String showSpecificRecord(@PathVariable("id") Long id,Model model) {
	
		model.addAttribute("repairrecord",uservice.findRepairOrderById(id));
		model.addAttribute("ProductUsage",uservice.findRepairOrderById(id).getProductUsageList());
		ObjectInput f= new ObjectInput();
		model.addAttribute("filter",f);
		model.addAttribute("control","record");

		
		return "recordrepairdetails";
	}
	

	
	//link when filter of date is clicked
	@RequestMapping(value="/filter")
	public String showFilteredRecord(@ModelAttribute("filter") ObjectInput filter,BindingResult bindingResult,Model model) {
		LocalDate a=filter.getStart();
		LocalDate b=filter.getEnd();
		String keyword=filter.getReportstate();

		List<RepairOrder> group=uservice.showRepairOrderByDate(a, b);
		
		
		if(keyword.contentEquals("yes")) {
			List<String> usagelist= new ArrayList<String>();
			usagelist.add("Repair Order ID, Customer, Date, Product Usage ID, Product Used, Quantity Used");
			for(RepairOrder ro:group) {
				for(ProductUsage pu:ro.getProductUsageList()) {
					usagelist.add(String.valueOf(ro.getRepairId()) + ", " + ro.getCustomer().getName() + ", " + String.valueOf(ro.getRepairDate()) + ", " + pu.toString());
				}
			}
			try {
				textWriterCSV(usagelist);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("how many group size= "+group.size());
		model.addAttribute("repairlist",group);
		model.addAttribute("control","record");

		
		return "recordrepair";
	}

	
	public void textWriterCSV(List<String> content) throws IOException {
		FileWriter writer = new FileWriter("D:/ReportUsage.csv"); 
		for(String str: content) {
		  writer.write(str + System.lineSeparator());
		}
		writer.close();
	}
	

	
	//link when hidden search button is clicked
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String showRelevantRecord(@Valid @ModelAttribute("filter") ObjectInput filter,BindingResult bindingResult,Model model) {
		List<RepairOrder> group=uservice.showRepairOrderByKeyword(filter.getKeyword());
		model.addAttribute("repairlist",group);
		model.addAttribute("control","record");
		
		return "recordrepair";
	}
	
	//link when repair record is clicked
	@RequestMapping(value="/detailsearch/{id}",method=RequestMethod.POST)
	public String showUsageDetailRecord(@Valid @ModelAttribute("filter") ObjectInput filter,@PathVariable("id") Long id,BindingResult bindingResult,Model model) {
		List<ProductUsage> group=uservice.showProductUsageByKeyword(filter.getKeyword());
		model.addAttribute("repairrecord",uservice.findRepairOrderById(id));
		model.addAttribute("productusage",group);
		model.addAttribute("control","record");
		
		return "recordrepairdetails";
	}
	//link for each edit repair to be clicked
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String editSpecificRecord(@PathVariable("id") Long id,Model model) {
	
		model.addAttribute("repairrecord",uservice.findRepairOrderById(id));
		model.addAttribute("control","record");
		model.addAttribute("customers",custservice.listAllCustomerNames());
		return "recordrepairform";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveRecord(@Valid @ModelAttribute("repairrecord") RepairOrder rep,BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			//currently it is denying when the date is null
			model.addAttribute("customers",custservice.listAllCustomerNames());
			return "recordrepairform";
		}
		Customer a= custservice.findByName(rep.getCustomer().getName());
		rep.setCustomer(a);
		uservice.saveRepairOrder(rep);
		return "redirect:/repair/showrecord";
	}
	
	//link for each delete repair to be clicked
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String deleteSpecificRecord(@PathVariable("id") Long id,Model model) {
		RepairOrder a=uservice.findRepairOrderById(id);
		uservice.deleteRepairOrder(a);
		
		return "forward:/repair/showrecord";
	}

}
