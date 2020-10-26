package org.elay.web;

import java.util.List;

import javax.validation.Valid;

import org.elay.Dao.ProduitRepository;
import org.elay.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProduitController {
	
	@Autowired
	private ProduitRepository prry;
	
	@GetMapping(path="/index")
	public String index() {
		return "index";
	}
	
	@GetMapping(path="/produits")
	public String produits(Model model,@RequestParam(name="page", defaultValue = "0") int page,
			@RequestParam(name="size", defaultValue = "4") int size,@RequestParam(name="motcle", defaultValue = "") String motcle) {
		
		Page<Produit> pageproduits=prry.findAll(PageRequest.of(page, size));
		Page<Produit> pageSearch=prry.findByDesignationContains(motcle, PageRequest.of(page, size));
		model.addAttribute("listsproduits", pageSearch);
		
		model.addAttribute("pages", new int[pageproduits.getTotalPages()]);
		model.addAttribute("pagecurrent", page);
		model.addAttribute("motcle", motcle);
		model.addAttribute("size", size);
		return "produits";
	}
	
	
	@GetMapping(path="/deletProduit")
	public String delete(@RequestParam(name="id") Long id, String page, String size, String motcle) {
		prry.deleteById(id);
		return "redirect:produits?page="+page+"&motcle="+motcle+"&size="+size;
	}
	
	@GetMapping(path="/formProduit")
	public String formProduit(Model model) {
		model.addAttribute("produit", new Produit());
		return "formProduit";
	}
	
	@GetMapping(path="/saveProduit")
	public String saveProduit(@Valid Produit produit, BindingResult bindingresult,Model model) {
		if(bindingresult.hasErrors()) return "formProduit";
		prry.save(produit);
		model.addAttribute("produit", produit);
		return "confirmation";
	}
	
	
	@GetMapping(path="/editProduit")
	public String editProduit(Model model, Long id) {
		Produit p=prry.findById(id).get();
		model.addAttribute("produit", p);
		return "formProduit";
	}
//	@GetMapping(path="/confirmation")
//	public String Confirmation(Model model, Produit produit) {
//		
//		return "confirmation";
//	}
	
	@GetMapping(path="/lesproduits")
	@ResponseBody
	public List<Produit> listProduit(){
		return prry.findAll();
	}
	
	@GetMapping(path="/produit/{id}")
	@ResponseBody
	public Produit OneProduit(@PathVariable Long id){
		return prry.findById(id).get();
	}
	
	

}
