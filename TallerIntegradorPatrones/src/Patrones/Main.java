/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

import Adapter.adapterCuenta;
import ChainResponsability.Manejador;
import ChainResponsability.interfaceManejador;
import Singleton.AtmUK;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        // Crear un único cajero Automático de dólares con 100 billetes de 20, 100 de 10, 
        // 10 monedas de 0.50, 10 de 0.25 y 1000 de 0.05
        List<adapterCuenta> lista=new LinkedList<>();
        interfaceManejador manejador20=new Manejador(100,20.0);
        interfaceManejador manejador10=new Manejador(100,10.0);
        interfaceManejador moneda050=new Manejador(10,0.50);
        interfaceManejador moneda025=new Manejador(10,0.25);
        interfaceManejador moneda005= new Manejador(1000,0.05);
        AtmUK atm=AtmUK.getInstancia();
        atm.addManejador(moneda005);
        atm.addManejador(moneda025);
        atm.addManejador(moneda050);
        atm.addManejador(manejador20);
        atm.addManejador(manejador10);
        
        // Crear 10 cuentas nuevas en dólares locale.US con un saldo inicial entre 100.00 y 1000.00 USD cada una.
        adapterCuenta cuenta1=new adapterCuenta(1,200);
        adapterCuenta cuenta2=new adapterCuenta(2,250);
        adapterCuenta cuenta3=new adapterCuenta(3,500);
        adapterCuenta cuenta4=new adapterCuenta(4,550);
        adapterCuenta cuenta5=new adapterCuenta(5,850);
        adapterCuenta cuenta6=new adapterCuenta(6,900);
        adapterCuenta cuenta7=new adapterCuenta(7,1000);
        adapterCuenta cuenta8=new adapterCuenta(8,580);
        adapterCuenta cuenta9=new adapterCuenta(9,750);
        adapterCuenta cuenta10=new adapterCuenta(10,150);
        lista.add(cuenta1);
        lista.add(cuenta2);
        lista.add(cuenta3);
        lista.add(cuenta4);
        lista.add(cuenta5);
        lista.add(cuenta6);
        lista.add(cuenta7);
        lista.add(cuenta8);
        lista.add(cuenta9);
        lista.add(cuenta10);
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el id de su cuenta:");
        int ingreso=sc.nextInt();
        for(adapterCuenta cuenta: lista){
            if(cuenta.getCuenta().getId()==ingreso){
                AtmUK.transaction(cuenta);
            }
        }
    }

    
}
