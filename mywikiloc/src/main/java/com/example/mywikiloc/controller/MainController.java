package com.example.mywikiloc.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mywikiloc.model.Image;
import com.example.mywikiloc.model.MyUserDetails;
import com.example.mywikiloc.model.Route;
import com.example.mywikiloc.model.User;
import com.example.mywikiloc.model.UserWithRoleOption;
import com.example.mywikiloc.service.RouteService;
import com.example.mywikiloc.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService service;
	@Autowired
	private RouteService routeService;
	
	
	/*
	@GetMapping("/login")
	public String showloginForm() {
		System.out.println("From log in");
		// the authentication context that represents the authenticated user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
			// if user is not authenticated, or is authenticated as anonymous
			if (auth == null || auth instanceof AnonymousAuthenticationToken ) {
				System.out.println("User is not authenticated");
				return "login";
			}
			else {
				String name = auth.getName();
				
				System.out.println("User is Authenticated"+ name);
				return "redirect:/";
			}
		
	}
	*/
	
	/*
	@GetMapping("/login")
	public String showloginForm(Model model, User user, String error, String logout) {
		 if (error != null)
	            model.addAttribute("msg", error);
        if (logout != null)
	            model.addAttribute("msg", "You have been logged out successfully.");
	       
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
     // if user is not authenticated, or is authenticated as anonymous
     	if (auth == null || auth instanceof AnonymousAuthenticationToken ) 
     		System.out.println("User is not authenticated");
     	else
     		System.out.println("User is Authenticated"+ auth.getName());
     	
		return "logInForm";
	}
	*/
	/*
	@GetMapping("/login-error")
    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model, User user) {
        if(user != null)
        	System.out.println(user.toString());
		HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }
        System.out.println("--->" + errorMessage);
        model.addAttribute("msg", errorMessage);
        return "logInForm";
    }
    */
	
	
	
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	/*
	@GetMapping("/delete/*")
	public String getDelete() {
		System.out.println("getDelete");
		return "delete";
	}
	*/
	/*
	 * 
	 * @RequestMapping("/edit/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_product");
		
		Product product = service.get(id);
		mav.addObject("product", product);
		
		return mav;
	}	
	 * 
	 * 
	 * 
	 */
	
	
	
	
	
	
	
	
		
	
	/*		
	Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
	
	authorities = auth.getAuthorities();
	 
	System.out.println("Auth size: "+ authorities.size());
	*/
	
	
	
	@GetMapping("/showAll")
	public String viewAllUsers(Model model) {
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);
		boolean isAdmin = false;
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority gAuth : auth.getAuthorities()) {
			if ("ADMIN".equals(gAuth.getAuthority()))
				isAdmin = true;
	      } 
		 if(isAdmin) {
			 model.addAttribute("edit", "Edit");
		     model.addAttribute("delete", "Delete");
		 }
		 else 
			 model.addAttribute("edit", "Edit");
		model.addAttribute("userName", "Welcome User: "+auth.getName());
		return "showAll";
	}
	
	 
	 @GetMapping("/myRoutes")
		public String myRoutes() {
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 User logedIn = ((MyUserDetails)principal).getLogedUser();
		 List<Route> routes = routeService.getAllUserRoutes(logedIn.getId());
		
		 for (Route route : routes) {
			 System.out.println("Print the All routes: "+route.toString());
		 }
		 return "myRoutes";
		}
	
	 @GetMapping("/newRoute")
	    public String newRoute() {
		 
	       return "newRouteForm";
	    }
		
	 
	 
	 @GetMapping("/newUser")
    public String add(Model model) {
        model.addAttribute("user", new User());
                    
        return "newUserForm";
    }
	
	
	 @PostMapping("/saveRoute")
	    public String newRoute(
	    		@RequestParam("routeName") String routeName,
	    		@RequestParam("imageName1") String imageName1,
	    		@RequestParam("latitude1") float latitude1,
	    		@RequestParam("longitude1") float longitude1,
	    		@RequestParam("description1") String description1,
	    		@RequestParam("imageName2") String imageName2,
	    		@RequestParam("latitude2") float latitude2,
	    		@RequestParam("longitude2") float longitude2,
	    		@RequestParam("description2") String description2,
	    		@RequestParam("comment") String comment
	    		) {
		 
		 Integer id = 0;
		 Image image1 = new Image(id,imageName1,latitude1,longitude1,description1);
		 
		 Route route = new Route();
		 route.setName(routeName);
		System.out.println("Route save msg: "+routeService.saveRoute(route));
		 
		 return "newRouteForm";
	 }
	 
	
	/*
	 @PostMapping("/saveRoute")
	    public String newRoute(@RequestParam Map<String,Object> allParams) {
		 System.out.println("Parameters are " + allParams.entrySet());
		 
		 return "newRouteForm";
	 }
	*/
	
	
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(Model model, User user) {
      		
		String msg = service.saveUser(user);
		if(msg.equals("OK"))		
			return "redirect:/showAll";
		else {
			model.addAttribute("msg", msg);
			return "newUserForm";
		}
    }
	
	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable(name = "id") int id) {
	System.out.println("From deleteUser id: " + id);
	 
	service.deteUserById(id);
	// reduce number of users in db
	//seqService.reductSequenceNumber(User.SEQUENCE_NAME);
	 return "redirect:/showAll";
	}
	 
	
	
	@RequestMapping("/edit/{id}")
		public String showUserEditForum(@PathVariable(name = "id") int id, Model model) {
		 	User user = service.getUserById(id);
		 	user.setPasswd("");
		 	model.addAttribute(user);
		 	
			boolean isAdmin = false;
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			for (GrantedAuthority gAuth : auth.getAuthorities()) {
				if ("ADMIN".equals(gAuth.getAuthority()))
					isAdmin = true;
		      } 
			
			model.addAttribute("userName", "Welcome user " + auth.getName() );
			 if(isAdmin) {
				 model.addAttribute("admin","true");
				}    
					
			return "editUserForm";
		}
	
	
	 @RequestMapping(value = "/saveEditedUser", method = RequestMethod.POST)
	 public String saveEditedUser(@ModelAttribute("user") User user) {
		  String msg = service.updateUser(user);
		
		  if(msg.equals("OK")) {
			  return "redirect:/showAll";
		  }
		  else {
			  return "redirect:/edit/"+user.getId();
		  }
	}
	
	 		 
	 
	 @GetMapping("/login")
	    public String showLogInForm() {
	         return "logInForm";
	    }
	
	 @RequestMapping("/403")
		public String show403() {
			return "403";
		}
	

}
