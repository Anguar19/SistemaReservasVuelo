/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;
import dao.UsuarioDAO;
import modelo.Usuario;
import util.DBConnection;


import java.sql.*;
import java.util.*;
/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class UsuarioDAOImpl implements UsuarioDAO{
    @Override
    public void insertar(Usuario u) {
        String sql = "INSERT INTO Usuarios (nombre, correo, contraseña) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, u.getNombre());
            stmt.setString(2, u.getCorreo());
            stmt.setString(3, u.getContraseña());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario obtenerPorCorreo(String correo) {
        Usuario u = null;
        String sql = "SELECT * FROM Usuarios WHERE correo = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                u = new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("correo"), rs.getString("contraseña"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public List<Usuario> obtenerTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios";
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("correo"), rs.getString("contraseña")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    } 
}
