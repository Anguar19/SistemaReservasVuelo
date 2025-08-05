/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import modelo.Reservas;
import java.util.List;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public interface ReservaDAO {
    void insertar(Reservas r);
    List<Reservas> obtenerPorUsuario(int idUsuario);
    
}
