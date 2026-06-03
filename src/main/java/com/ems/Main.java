package com.ems;

import com.ems.controller.AuthController;
import com.ems.controller.EmployeeController;
import com.ems.view.MainMenuView;

/**
 * CogniFlow EMS — Application Entry Point
 * Initializes controllers and launches the console UI.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║    Employee Management System v1.0   ║");
        System.out.println("║      Java · JDBC · MySQL · MVC       ║");
        System.out.println("╚══════════════════════════════════════╝");

        AuthController     authCtrl = new AuthController();
        EmployeeController empCtrl  = new EmployeeController();
        MainMenuView       menu     = new MainMenuView(authCtrl, empCtrl);
        menu.launch();
    }
}
