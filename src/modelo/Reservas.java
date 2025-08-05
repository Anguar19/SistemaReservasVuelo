/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.Timestamp;

/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class Reservas {
    private int id;
    private int idUsuario;
    private int idVuelo;
    private Timestamp fechaReserva;

    public Reservas(int id, int idUsuario, int idVuelo, Timestamp fechaReserva) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idVuelo = idVuelo;
        this.fechaReserva = fechaReserva;
    }

    public Reservas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public Timestamp getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Timestamp fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
    
    
}
