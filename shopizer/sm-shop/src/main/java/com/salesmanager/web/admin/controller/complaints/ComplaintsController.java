package com.salesmanager.web.admin.controller.complaints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.salesmanager.core.business.catlog.complaints.ComplaintsService;
import com.salesmanager.core.business.complaint.model.ComplaintsReason;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.country.service.CountryService;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.business.reference.language.service.LanguageService;
import com.salesmanager.core.utils.ajax.AjaxResponse;
import com.salesmanager.web.admin.entity.web.Menu;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.utils.LabelUtils;

@Controller
public class ComplaintsController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComplaintsController.class);
	
	@Autowired
	LanguageService languageService;
	
	
	
	@Autowired
	CountryService countryService;
	
	
	@Autowired
	ComplaintsService complaintsService;
	@Autowired
	LabelUtils messages;

	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/complaints/editCategory.html", method=RequestMethod.GET)
	public String displayCategoryEdit(@RequestParam("id") long categoryId, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return displayCategory(categoryId,model,request,response);

	}
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/complaints/createCategory.html", method=RequestMethod.GET)
	public String displayCategoryCreate(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return displayCategory(null,model,request,response);

	}
	
	
	
	private String displayCategory(Long categoryId, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		//display menu
		setMenu(model,request);
		
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		Language language = (Language)request.getAttribute("LANGUAGE");
		
		//get parent complaints
		List<ComplaintsReason> complaints = complaintsService.listByStore(language);
		

		List<Language> languages = store.getLanguages();
		ComplaintsReason category = new ComplaintsReason();
		
		if(categoryId!=null && categoryId!=0) {//edit mode
			category = complaintsService.getById(categoryId);
		
			
			
			if(category==null ) {
				return "catalogue-complaints";
			}
		}
		
		
	

		
		model.addAttribute("category", category);
		model.addAttribute("complaints", complaints);
		

		
		return "complaints-reason";
	}
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/complaints/save.html", method=RequestMethod.POST)
	public String saveCategory(@Valid @ModelAttribute("category") ComplaintsReason category, BindingResult result, Model model, HttpServletRequest request) throws Exception {

		Language language = (Language)request.getAttribute("LANGUAGE");
		
		//display menu
		setMenu(model,request);
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

		if(category.getId() != null && category.getId() >0) { //edit entry
			
			//get from DB
			ComplaintsReason currentCategory = complaintsService.getById(category.getId());
			
			if(currentCategory==null ) {
				return "catalogue-complaints";
			}

		}

			
			
			
			
		
		if (result.hasErrors()) {
			return "complaints-reason";
		}
		
		
		
		
		complaintsService.saveOrUpdate(category);

			
		
		
		
		//get parent complaints
		List<ComplaintsReason> complaints = complaintsService.listByStore(language);
		model.addAttribute("complaints", complaints);
		

		model.addAttribute("success","success");
		return "complaints-reason";
	}
	
	
	//category list
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/complaints/complaints.html", method=RequestMethod.GET)
	public String displaycomplaints(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		
		setMenu(model,request);
		
		//does nothing, ajax subsequent request
		
		
		return "catalogue-complaints";
	}
	
	@SuppressWarnings({ "unchecked"})
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/complaints/paging.html", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String pagecomplaints(HttpServletRequest request, HttpServletResponse response) {
		String categoryName = request.getParameter("name");
		String categoryCode = request.getParameter("code");


		AjaxResponse resp = new AjaxResponse();

		
		try {
			
			Language language = (Language)request.getAttribute("LANGUAGE");
				
		
			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			
			List<ComplaintsReason> complaints = null;
					
			if(!StringUtils.isBlank(categoryName)) {
				
				
				complaints = complaintsService.getByName( categoryName, language);
				
			} else if(!StringUtils.isBlank(categoryCode)) {
				
				//categoryService.listByCodes(store, new ArrayList<String>(Arrays.asList(categoryCode)), language);
			
			} else {
				
				complaints = complaintsService.listByStore( language);
				
			}
					
					
			
			for(ComplaintsReason category : complaints) {
				
				@SuppressWarnings("rawtypes")
				Map entry = new HashMap();
				entry.put("id", category.getId());
				
				
				
				entry.put("name", category.getEnglishName());
				
				resp.addDataEntry(entry);
				
				
			}
			
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
			

		
		} catch (Exception e) {
			LOGGER.error("Error while paging complaints", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
		}
		
		String returnString = resp.toJSONString();
		
		return returnString;
	}
	
	
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/complaints/remove.html", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String deleteCategory(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		String sid = request.getParameter("id");

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		AjaxResponse resp = new AjaxResponse();

		
		try {
			
			Long id = Long.parseLong(sid);
			
			ComplaintsReason category = complaintsService.getById(id);
			
			if(category==null  ) {

				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				
			} else {
				complaintsService.delete(category);
				resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);
				
			}
		
		
		} catch (Exception e) {
			LOGGER.error("Error while deleting category", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		
		return returnString;
	}
	
	
	
	
	private void setMenu(Model model, HttpServletRequest request) throws Exception {
		
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		
		activeMenus.put("complaints", "complaints");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("catalogue");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		//
		
	}

}
