package com.charles.lesamisdelescalade.webapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.charles.lesamisdelescalade.business.config.BusinessConfig;
import com.charles.lesamisdelescalade.consumer.config.ConsumerConfig;
import com.charles.lesamisdelescalade.webapp.config.WebappConfig;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private WebappConfig webappConfig;
	
	@Autowired
	private BusinessConfig businessConfig;
	
	@Autowired
	private ConsumerConfig consumerConfig;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("webapp", webappConfig.webapp() );
		model.addAttribute("business", businessConfig.business() );
		model.addAttribute("consumer", consumerConfig.consumer() );
		
		return "home";
	}
	
}
