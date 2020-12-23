package sg.edu.iss.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.test.model.Brand;
import sg.edu.iss.test.model.Product;
import sg.edu.iss.test.model.ProductQuery;
import sg.edu.iss.test.model.ProductUsage;
import sg.edu.iss.test.model.Returned;
import sg.edu.iss.test.model.Supplier;
import sg.edu.iss.test.repo.BrandRepository;
import sg.edu.iss.test.repo.SupplierRepository;
import sg.edu.iss.test.service.BrandService;
import sg.edu.iss.test.service.CatalogueImplementation;
import sg.edu.iss.test.service.CatalogueService;
import sg.edu.iss.test.service.ProductUsageService;
import sg.edu.iss.test.service.ReturnedService;
import sg.edu.iss.test.service.SupplierService;

@Controller
@RequestMapping("/catalogue")
public class CatalogueController {
	
	@Autowired
	private CatalogueService cservice;

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private BrandService brandService;

	@Autowired
	private ReturnedService rservice;
	
	@Autowired
	private ProductUsageService uservice;
	
	@Autowired
	public void setCatalogue(CatalogueImplementation catalogue) {
		this.cservice = catalogue;
	}
	
	@RequestMapping(value = "/showform", method = RequestMethod.GET)
	public String showForm(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		ArrayList<Brand> allBrands = brandService.findAllBrands();
		ArrayList<Supplier> allSuppliers = supplierService.findAllSuppliers();
		model.addAttribute("suppliers",allSuppliers);
		model.addAttribute("brands",allBrands);
		return "catalogueform";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
		String supplierName = product.getSupplier().getSupplierName();
		Supplier supplier = supplierRepository.findSupplierBySupplierName(supplierName);
		product.setSupplier(supplier);
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
		Page<Product> productByFliter = cservice.findProductByFliter(page,size,productQuery);
		System.out.println(productByFliter);
		model.addAttribute("products",productByFliter);
		model.addAttribute("pageCount",productByFliter.getTotalPages()-1);
		model.addAttribute("condition", productQuery);
		model.addAttribute("size", size);
		model.addAttribute("control", "product");
		return "catalogue";
	}


	@RequestMapping("/edit/{id}")
	public String editProduct(@PathVariable("id") Long id, Model model){
		Product product = cservice.findById(id);
		model.addAttribute("product", product);
		ArrayList<Supplier> allSuppliers = supplierService.findAllSuppliers();
		model.addAttribute("suppliers",allSuppliers);
		ArrayList<Brand> allBrands = brandService.findAllBrands();
		model.addAttribute("brands",allBrands);
		return "catalogueformForModify";
	}

	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") Long id, Model model){
		List<Returned> r = rservice.findReturnedByProId(id);
		List<ProductUsage> u = uservice.findProductUsageByProId(id);
		if (r.size()>0 || u.size()>0){
			  model.addAttribute("msg","Can not delete! There are still return or repair recording under this product!");
			  model.addAttribute("url","/catalogue/findByFilter");
		      return "erro";
		}else {
			cservice.delete(id);
			return "redirect:/catalogue/findByFilter";
		}	
		
	}

}
