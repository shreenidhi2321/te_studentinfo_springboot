package com.te.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.te.springmvc.bean.UserBean;

@Controller
public class SpringMvcController {

	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView gethome() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/WEB-INF/views/homePage.jsp");
		return mv;

	}
	@RequestMapping(path="/search", method=RequestMethod.GET)
	public ModelAndView getData(ModelAndView modelAndView, HttpServletRequest  request) {
		String name=request.getParameter("name");
		//modelAndView.addObject("username", name);
		request.setAttribute("username", name);
		modelAndView.setViewName("newHome");
		return modelAndView;
		
	}
	@GetMapping("/login")
	public String getForm() {
		return "loginForm";
		
	}
	@PostMapping("/login")
	public String getFormData(HttpServletRequest request, ModelMap modelMap) {
		//get formdata using request
		String name=request.getParameter("user");
		int password=Integer.parseInt(request.getParameter("pwd"));
		
		modelMap.addAttribute("name", name);
		modelMap.addAttribute("pwd", password);
		return "userDetails";
	}//end of getformdata
	
	@PostMapping("/login1")
	public String name(ModelMap modelMap, int pwd, String user) {
		//get formdata using request
		
		
		modelMap.addAttribute("name", user);
	modelMap.addAttribute("pwd", pwd);
		return "userDetails";
	}//end of getformdata
	
	@PostMapping("/login2")
	public String name(UserBean bean, ModelMap modelMap) {
		//get formdata using request
		
		
		modelMap.addAttribute("name", bean.getUser());
	modelMap.addAttribute("pwd", bean.getPwd());
		return "userDetails";
	}//end of getformdata
	@PostMapping("/login3")
	public String getFormRequestParam(ModelMap modelMap,
			@RequestParam(name="")String user,
			@RequestParam(name="pwd")int password) {
		modelMap.addAttribute("name", user);
		modelMap.addAttribute("pwd", password);
			return "userDetails";
		
	}
	
	
	

}
