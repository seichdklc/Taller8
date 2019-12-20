/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Singleton;

import Adapter.adapterCuenta;
import ChainResponsability.Manejador;
import ChainResponsability.interfaceManejador;
import Patrones.Account;
import static Singleton.AtmUK.anotherTransaction;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

public class AtmUK {
    protected static AtmUK instance=null;
    protected static Currency moneda=null;
    protected final Locale currency=Locale.UK;
    private static double dinero = 0;
    protected Account cuenta;
    static ArrayList <interfaceManejador> manejadores; // Cada manejador puede entregar dinero de una sola denominación
    static Scanner in;
    // -----------------
    
    
    public AtmUK() {
      
    }
    // -----------------
    public static AtmUK getInstancia()
    {
         if (instance == null) {
             instance = new AtmUK();
             moneda= Currency.getInstance(Locale.US);
             manejadores=new ArrayList<>();
            System.out.println("Su cajero ha sido creado");
        }
        else {
            System.out.println("Ya existe el cajero");
        }
        return instance;
    }
    
    public static double getTotal() {
        return dinero;
    }

    // -----------------
    public boolean sacarDinero(double monto) {
        int contador=0;
        if(dinero>=monto){
           while(contador<monto){
               for(interfaceManejador manejadores: manejadores){
                   manejadores.retirar(monto);
                   contador+=monto;
           } 
        }
        }else{
            System.out.println("Insufficient funds at our ATM, Sorry.. Try later ");
        }
        dinero =dinero - monto;
        return true;
    }

    // -----------------
    public void ingresarDinero(double dinero, int denominacion) {
        this.dinero += dinero;
        // Todo: Sólo se puede depositar billetes de una sola denominación y agregarse al manejador correspondiente
    }

    public void addManejador(interfaceManejador m){
        manejadores.add(m);
    }
    public interfaceManejador removeManejador(int i){
        return manejadores.remove(i);
    }

    //Dentro de las transacciones se debe llamar al ATM para hacer el retiro o deposito de la cuenta correspondiente
    public static void transaction(adapterCuenta cuenta){
        // here is where most of the work is
        in=new Scanner(System.in);
        int choice; 
        System.out.println("Please select an option"); 
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Balance");
        System.out.println("4. Balance ATM");
        choice= in.nextInt();
        switch(choice){
            case 1:
                float amount; 
                System.out.println("Please enter amount to withdraw: "); 
                amount = in.nextFloat();
                if(amount > cuenta.getCuenta().getAmount() || amount == 0){
                    System.out.println("You have insufficient funds\n\n"); 
                    anotherTransaction(cuenta); // ask if they want another transaction
                } else {
                    if(amount<=cuenta.getCuenta().getAmount()){
                        cuenta.getCuenta().withdraw(amount);
                        instance.sacarDinero(amount);
                        System.out.println("You have withdrawn "+amount+" and your new balance is "+cuenta.Balance());
                        anotherTransaction(cuenta); 
                    }
                    // Todo: verificar que se puede realizar el retiro del atm
                    // Todo: actualizar tanto la cuenta como el atm y de los manejadores
                    // Todo: Mostrar resumen de transacción o error
                    
                }
            break; 
            case 2:
                    // option number 2 is depositing 
                    float deposit; 
                    System.out.println("Please enter amount you would wish to deposit: "); 
                    deposit = in.nextFloat();
                    System.out.println("Please enter the denomintion dollars you'd wish deposit it: ");
                    int denomination;
                    denomination=in.nextInt();
                    for(interfaceManejador manejador: manejadores){
                        if(manejador.getDenominacion()==denomination){
                            instance.ingresarDinero(deposit, denomination);
                            cuenta.getCuenta().deposit(deposit);
                            System.out.println("You have withdrawn "+deposit+" and your new balance is "+cuenta.Balance());
                            anotherTransaction(cuenta); 
                        }   
                    }
                    // Todo: Mostrar resumen de transacción o error
                    // Todo: actualizar tanto la cuenta como el atm
            break; 
            case 3:
                    // Todo: mostrar el balance de la cuenta
                    System.out.println("Your balance is "+cuenta.Balance());
                    anotherTransaction(cuenta); 
            break;
            case 4:
                    // Todo: mostrar el balance del ATM con los billetes en cada manejador
                    System.out.println("The ATM's balance according with our system is :" +getTotal());
                    anotherTransaction(cuenta); 
            break;
            default:
                    System.out.println("Invalid option:\n\n"); 
                    anotherTransaction(cuenta);
            break;
        }
    }
    public static void anotherTransaction(adapterCuenta cuenta){
        System.out.println("Do you want another transaction?\n\nPress 1 for another transaction\n2 To exit");
        in=new Scanner(System.in);
        int anotherTransaction = in.nextInt();
        if(anotherTransaction == 1){
            transaction(cuenta); // call transaction method
        } else if(anotherTransaction == 2){
            System.out.println("Thanks for choosing us. Good Bye!");
        } else {
            System.out.println("Invalid choice\n\n");
            anotherTransaction(cuenta);
        }
    }

    
}
