/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter;

import Patrones.Account;
import java.util.Currency;
import java.util.Locale;


/**
 *
 * @author Luis
 */
public class adapterCuenta implements interfaceCuenta {
    
    protected Account cuenta;
    protected Currency moneda;

    public adapterCuenta(int id, double monto) {
        this.cuenta = new Account(id, monto);
        this.moneda = Currency.getInstance(Locale.US);
    }

    @Override
    public double Balance() {
        return cuenta.getAmount();
    }

    @Override
    public boolean Retirar(double monto) {
        String valor[] = cuenta.withdraw(monto).split(" ");
        return Double.parseDouble(valor[1]) >= 0;
    }

    @Override
    public boolean Depositar(int n, double monto) {
        double total = n * monto;
        if (total > 0) {
            cuenta.deposit(total);
            return true;
        }else
            return false;
    }

    public Account getCuenta() {
        return cuenta;
    }

    public void setCuenta(Account cuenta) {
        this.cuenta = cuenta;
    }
    
}
