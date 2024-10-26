package com.Esport.presentation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.Esport.presentation.Menu.MenuPrincipal;

public class Application {
    public static void main(String[] args) {
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	MenuPrincipal menu = context.getBean(MenuPrincipal.class);
	
	menu.afficherMenuPrincipal();

    }

}
