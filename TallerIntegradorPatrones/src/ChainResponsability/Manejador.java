/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChainResponsability;

public class Manejador implements interfaceManejador
{
    private Manejador next;
    protected int monto;
    protected double denominacion;
    public Manejador(int cantidad, double denominacion) {
        this.monto = cantidad; // Total de billetes
        this.denominacion = denominacion; // Valor de cada billete
    }
    @Override
    public int getMonto() {
        return this.monto;
    }

    @Override
    public double getDenominacion() {
        return this.denominacion;
    }

    @Override
    public void setMonto(int cantidad) {
        this.monto=cantidad;
    }

    @Override
    public void setNext(Manejador m) {
        this.next=m;
    }

    @Override
    public boolean retirar(double cantidad) {
        if(monto*denominacion>cantidad){
            monto-=(int)(cantidad/denominacion);
        }
       return false;
    }

    @Override
    public boolean depositar(double cantidad, double denominacion) {
        monto+=cantidad;
        return false;
    }

    
}