/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;
import dao.ReservaDAO;
import modelo.Reservas;
import util.DBConnection;

import java.sql.*;
import java.util.*;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class ReservaDAOImpl implements ReservaDAO{
     @Override
    public void insertar(Reservas r) {
        String sql = "INSERT INTO Reservas (idUsuario, idVuelo, fechaReserva) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, r.getIdUsuario());
            stmt.setInt(2, r.getIdVuelo());
            stmt.setTimestamp(3, r.getFechaReserva());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

    @Override
    public List<Reservas> obtenerPorUsuario(int idUsuario) {
        List<Reservas> lista = new ArrayList<>();
        String sql = "SELECT * FROM Reservas WHERE idUsuario = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Reservas(
                    rs.getInt("id"), rs.getInt("idUsuario"), rs.getInt("idVuelo"), rs.getTimestamp("fechaReserva")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
