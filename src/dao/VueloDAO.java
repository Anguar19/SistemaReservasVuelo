/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import modelo.Vuelo;
import java.util.List;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public interface VueloDAO {
    void insertar(Vuelo v);
    List<Vuelo> buscarPorDestino(String origen, String destino);
    List<Vuelo> obtenerTodos();
    
}
