/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;
import dao.impl.UsuarioDAOImpl;
import dao.impl.VueloDAOImpl;
import dao.impl.ReservaDAOImpl;
import modelo.Usuario;
import modelo.Vuelo;
import modelo.Reservas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Anguar Alberto Rodriguez Fonseca
 */
public class SistemaReservasVueloFrame extends javax.swing.JFrame {
    private Usuario usuarioActual;
    private DefaultTableModel modeloVuelos;
    /**
     * Creates new form SistemaReservasVueloFrame
     */
    public SistemaReservasVueloFrame() {
        initComponents();
        configurarTabla();
         cargarVuelos();
    }
    
    private void crearPanelEstado() {
    JPanel panelEstado = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    lblEstado = new JLabel("Sin usuario conectado");
    JButton btnCerrarSesion = new JButton("Cerrar Sesión");
    
    btnCerrarSesion.addActionListener(e -> {
        usuarioActual = null;
        lblEstado.setText("Sin usuario conectado");
        JOptionPane.showMessageDialog(this, "Sesión cerrada");
    });
    
    panelEstado.add(lblEstado);
    panelEstado.add(btnCerrarSesion);
}
    
    // Cambiar color de campos vacíos
private void validarCampos() {
    if (txtCorreo.getText().trim().isEmpty()) {
        txtCorreo.setBackground(Color.PINK);
    } else {
        txtCorreo.setBackground(Color.WHITE);
    }
}
    
    private void configurarTabla() {
    modeloVuelos = new DefaultTableModel(
        new Object[][]{},
        new String[]{"ID", "Aerolínea", "Origen", "Destino", "Fecha", "Hora", "Escalas", "Disponibles", "Precio"}
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Hacer la tabla no editable
        }
    };
    tablaVuelos.setModel(modeloVuelos);
}
    
    private void cargarVuelos() {
    VueloDAOImpl dao = new VueloDAOImpl();
    List<Vuelo> vuelos = dao.obtenerTodos(); // Cargar todos los vuelos disponibles
    cargarVuelosEnTabla(vuelos);
}
    
    private void cargarVuelosEnTabla(List<Vuelo> vuelos) {
    modeloVuelos.setRowCount(0); // Limpiar la tabla
    
    for (Vuelo v : vuelos) {
        modeloVuelos.addRow(new Object[]{
            v.getId(), 
            v.getAerolinea(), 
            v.getOrigen(), 
            v.getDestino(), 
            v.getFecha(), 
            v.getHora(),
            v.getEscalas(), 
            v.getEspacioDisponible(), 
            String.format("%.2f", v.getPrecio())
        });
    }
}
    
    private void PanelEstado() {
    JPanel panelEstado = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    lblEstado = new JLabel("Sin usuario conectado");
    JButton btnCerrarSesion = new JButton("Cerrar Sesión");
    
    btnCerrarSesion.addActionListener(e -> {
        usuarioActual = null;
        lblEstado.setText("Sin usuario conectado");
        JOptionPane.showMessageDialog(this, "Sesión cerrada");
    });
    
    panelEstado.add(lblEstado);
    panelEstado.add(btnCerrarSesion);
}

    private void registrar() {
        UsuarioDAOImpl dao = new UsuarioDAOImpl();
        Usuario nuevo = new Usuario();
        nuevo.setNombre(txtNombre.getText());
        nuevo.setCorreo(txtCorreo.getText());
        nuevo.setContraseña(txtContraseña.getText());
        dao.insertar(nuevo);
        JOptionPane.showMessageDialog(this, "Usuario registrado.");
    }

    private void buscarVuelos() {
    String origen = txtOrigen.getText().trim();
    String destino = txtDestino.getText().trim();
    
    VueloDAOImpl dao = new VueloDAOImpl();
    List<Vuelo> vuelos;
    
    // Si ambos campos están vacíos, buscar todos los vuelos
    if (origen.isEmpty() && destino.isEmpty()) {
        vuelos = dao.obtenerTodos(); // Asumiendo que existe este método
    } else {
        vuelos = dao.buscarPorDestino(origen, destino);
    }
    
    cargarVuelosEnTabla(vuelos);
}

    private void reservarVuelo() {
        if (usuarioActual == null) {
            JOptionPane.showMessageDialog(this, "Primero inicie sesión.");
            return;
        }
        int fila = tablaVuelos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un vuelo.");
            return;
        }
        int idVuelo = (int) tablaVuelos.getValueAt(fila, 0);
        Reservas reserva = new Reservas();
        reserva.setIdUsuario(usuarioActual.getId());
        reserva.setIdVuelo(idVuelo);
        reserva.setFechaReserva(new Timestamp(System.currentTimeMillis()));
        ReservaDAOImpl dao = new ReservaDAOImpl();
        dao.insertar(reserva);
        JOptionPane.showMessageDialog(this, "Reserva realizada con éxito.");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelAutenticacion = new javax.swing.JPanel();
        lblAutenticacion = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        lblContraseña = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtContraseña = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVuelos = new javax.swing.JTable();
        panelBusqueda = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        lblOrigen = new javax.swing.JLabel();
        txtOrigen = new javax.swing.JTextField();
        lblDestino = new javax.swing.JLabel();
        txtDestino = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnReservar = new javax.swing.JButton();
        btnActualizarTabla = new javax.swing.JButton();
        panel = new javax.swing.JPanel();
        lblEstado = new javax.swing.JLabel();
        lblConectado = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        panelAutenticacion.setBackground(new java.awt.Color(255, 204, 204));

        lblAutenticacion.setText("Autenticacion");

        lblCorreo.setText("Correo");

        lblContraseña.setText("Contraseña");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAutenticacionLayout = new javax.swing.GroupLayout(panelAutenticacion);
        panelAutenticacion.setLayout(panelAutenticacionLayout);
        panelAutenticacionLayout.setHorizontalGroup(
            panelAutenticacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAutenticacionLayout.createSequentialGroup()
                .addGroup(panelAutenticacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAutenticacionLayout.createSequentialGroup()
                        .addGroup(panelAutenticacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAutenticacionLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(btnLogin))
                            .addGroup(panelAutenticacionLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(panelAutenticacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblContraseña))))
                        .addGap(28, 28, 28)
                        .addGroup(panelAutenticacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRegistrar)))
                    .addGroup(panelAutenticacionLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(lblAutenticacion)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        panelAutenticacionLayout.setVerticalGroup(
            panelAutenticacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAutenticacionLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblAutenticacion)
                .addGap(18, 18, 18)
                .addGroup(panelAutenticacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCorreo))
                .addGap(18, 18, 18)
                .addGroup(panelAutenticacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContraseña)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(panelAutenticacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(btnRegistrar))
                .addGap(24, 24, 24))
        );

        tablaVuelos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaVuelos);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");

        lblOrigen.setText("Origen");

        lblDestino.setText("Destino");

        jLabel1.setText("Busqueda de Vuelos");

        javax.swing.GroupLayout panelBusquedaLayout = new javax.swing.GroupLayout(panelBusqueda);
        panelBusqueda.setLayout(panelBusquedaLayout);
        panelBusquedaLayout.setHorizontalGroup(
            panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBusquedaLayout.createSequentialGroup()
                .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBusquedaLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(btnBuscar))
                    .addGroup(panelBusquedaLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar))
                .addGap(53, 53, 53))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBusquedaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );
        panelBusquedaLayout.setVerticalGroup(
            panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBusquedaLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrigen)
                    .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDestino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(btnLimpiar))
                .addGap(18, 18, 18))
        );

        btnReservar.setText("Reservar");
        btnReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservarActionPerformed(evt);
            }
        });

        btnActualizarTabla.setText("Actualizar Tabla");

        lblEstado.setText("Estado");

        lblConectado.setText("Conectado");

        btnCerrar.setText("Cerrar");

        txtNombre.setText("jTextField1");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(lblConectado)
                .addGap(71, 71, 71)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addComponent(btnCerrar)
                .addGap(49, 49, 49))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstado)
                    .addComponent(lblConectado)
                    .addComponent(btnCerrar)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelAutenticacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(btnReservar)
                        .addGap(85, 85, 85)
                        .addComponent(btnActualizarTabla))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelAutenticacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReservar)
                    .addComponent(btnActualizarTabla))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        String correo = txtCorreo.getText();
    String contraseña = txtContraseña.getText();
    UsuarioDAOImpl dao = new UsuarioDAOImpl();
    Usuario u = dao.obtenerPorCorreo(correo);
    if (u != null && u.getContraseña().equals(contraseña)) {
        JOptionPane.showMessageDialog(this, "Bienvenido, " + u.getNombre());
        usuarioActual = u;
    } else {
        JOptionPane.showMessageDialog(this, "Credenciales incorrectas.");
    }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
       JOptionPane.showMessageDialog(this, "Reserva realizada con éxito.");
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        VueloDAOImpl dao = new VueloDAOImpl();
    List<Vuelo> vuelos = dao.buscarPorDestino(txtOrigen.getText(), txtDestino.getText());
    DefaultTableModel model = (DefaultTableModel) tablaVuelos.getModel();
    model.setRowCount(0);
    for (Vuelo v : vuelos) {
        model.addRow(new Object[]{
            v.getId(), v.getAerolinea(), v.getOrigen(), v.getDestino(), v.getFecha(), v.getHora(),
            v.getEscalas(), v.getEspacioDisponible(), v.getPrecio()
        });
    }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservarActionPerformed
        // TODO add your handling code here:
      if (usuarioActual == null) {
        JOptionPane.showMessageDialog(this, "Primero inicie sesión.");
        return;
    }
    int fila = tablaVuelos.getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione un vuelo.");
        return;
    }
    int idVuelo = (int) tablaVuelos.getValueAt(fila, 0);
    Reservas reserva = new Reservas();
    reserva.setIdUsuario(usuarioActual.getId());
    reserva.setIdVuelo(idVuelo);
    reserva.setFechaReserva(new Timestamp(System.currentTimeMillis()));
    ReservaDAOImpl dao = new ReservaDAOImpl();
    dao.insertar(reserva);
    JOptionPane.showMessageDialog(this, "Reserva realizada con éxito.");
  
    }//GEN-LAST:event_btnReservarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SistemaReservasVueloFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SistemaReservasVueloFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SistemaReservasVueloFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SistemaReservasVueloFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SistemaReservasVueloFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarTabla;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnReservar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAutenticacion;
    private javax.swing.JLabel lblConectado;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblDestino;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblOrigen;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panelAutenticacion;
    private javax.swing.JPanel panelBusqueda;
    private javax.swing.JTable tablaVuelos;
    private javax.swing.JTextField txtContraseña;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDestino;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtOrigen;
    // End of variables declaration//GEN-END:variables
}
