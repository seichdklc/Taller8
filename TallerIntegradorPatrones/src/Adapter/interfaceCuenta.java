/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter;

/**
 *
 * @author Luis
 */
public interface interfaceCuenta {
    public double Balance();
    public boolean Retirar(double monto);
    public boolean Depositar(int n,double denominacion);
    
}
