package sg.edu.iss.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import sg.edu.iss.test.model.Product;
import sg.edu.iss.test.model.ProductQuery;
import sg.edu.iss.test.service.CatalogueImplementation;
import sg.edu.iss.test.service.CatalogueInterface;

@Controller
@RequestMapping("/catalogue")
public class CatalogueController {
	
	@Autowired
	private CatalogueInterface cservice;
	
	@Autowired
	public void setCatalogue(CatalogueImplementation catalogue) {
		this.cservice = catalogue;
	}
	
	@RequestMapping(value = "/showform", method = RequestMethod.GET)
	public String showForm(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "catalogueform";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
		cservice.save(product);
		return "redirect:/catalogue/findByFilter";
	}


	@RequestMapping("/findByFilter")
	public String findProduct(ProductQuery productQuery,Model model, @RequestParam(value = "page", defaultValue = "0") Integer page,
							  @RequestParam(value ="size", defaultValue = "2") Integer size){
		if (productQuery.getBrandName()==null&&productQuery.getSupplierName()==null&&productQuery.getProductName()==null){
			productQuery.setBrandName("");
			productQuery.setProductName("");
			productQuery.setSupplierName("");
		}
		System.out.println(size);
		System.out.println(page);
		Page<Product> productByFliter = cservice.findProductByFliter(page,size,productQuery);
		System.out.println(productByFliter);
		model.addAttribute("products",productByFliter);
		model.addAttribute("pageCount",productByFliter.getTotalPages()-1);
		model.addAttribute("condition", productQuery);
		model.addAttribute("size", size);
		return "catalogue";
	}
}
