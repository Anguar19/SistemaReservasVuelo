/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;
import dao.VueloDAO;
import modelo.Vuelo;
import util.DBConnection;

import java.sql.*;
import java.util.*;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class VueloDAOImpl implements VueloDAO {
     @Override
    public void insertar(Vuelo v) {
        String sql = "INSERT INTO Vuelos (aerolinea, origen, destino, fecha, hora, escalas, espacioDisponible, precio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, v.getAerolinea());
            stmt.setString(2, v.getOrigen());
            stmt.setString(3, v.getDestino());
            stmt.setDate(4, v.getFecha());
            stmt.setTime(5, v.getHora());
            stmt.setInt(6, v.getEscalas());
            stmt.setInt(7, v.getEspacioDisponible());
            stmt.setDouble(8, v.getPrecio());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }

    @Override
    public List<Vuelo> buscarPorDestino(String origen, String destino) {
        List<Vuelo> lista = new ArrayList<>();
        String sql = "SELECT * FROM Vuelos WHERE origen = ? AND destino = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, origen);
            stmt.setString(2, destino);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Vuelo(
                    rs.getInt("id"), rs.getString("aerolinea"), rs.getString("origen"), rs.getString("destino"),
                    rs.getDate("fecha"), rs.getTime("hora"), rs.getInt("escalas"),
                    rs.getInt("espacioDisponible"), rs.getDouble("precio")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Vuelo> obtenerTodos() {
        List<Vuelo> lista = new ArrayList<>();
        String sql = "SELECT * FROM Vuelos";
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Vuelo(
                    rs.getInt("id"), rs.getString("aerolinea"), rs.getString("origen"), rs.getString("destino"),
                    rs.getDate("fecha"), rs.getTime("hora"), rs.getInt("escalas"),
                    rs.getInt("espacioDisponible"), rs.getDouble("precio")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
