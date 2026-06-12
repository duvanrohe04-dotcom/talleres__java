/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package modelo;

/**
 *
 * @author ASUS
 */
public class Admin {
    
    private Admin() {
    }
    
    public static Admin getInstance() {
        return AdminHolder.INSTANCE;
    }
    
    private static class AdminHolder {

        private static final Admin INSTANCE = new Admin();
    }
}
