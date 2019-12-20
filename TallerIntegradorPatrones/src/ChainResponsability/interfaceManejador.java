/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChainResponsability;

/**
 *
 * @author Luis
 */
public interface interfaceManejador {
    public int getMonto();
    public double getDenominacion();
    public void setMonto(int cantidad);

    public void setNext(Manejador m);
    
    public boolean retirar(double monto);
    public boolean depositar(double cantidad, double denominacion);
}
