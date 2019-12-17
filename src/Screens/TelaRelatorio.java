/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;

import java.sql.*;
import DAO.ModuloConexao;
import net.proteanit.sql.DbUtils;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Thainã
 */
public final class TelaRelatorio extends javax.swing.JInternalFrame {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
   
    public TelaRelatorio() {
        initComponents();
        conexao = ModuloConexao.conector();
        this.carregar_tabela();
        this.carregar_valor();
        this.carregar_total();
    }
    
    public void carregar_tabela(){
        DefaultTableModel modelo=(DefaultTableModel) tblOs.getModel();
        modelo.setNumRows(0);
        
        tblOs.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblOs.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblOs.getColumnModel().getColumn(6).setPreferredWidth(23);
        
        try {
            //pst=conexao.prepareStatement("SELECT * from tbos");
            pst=conexao.prepareStatement("SELECT O.os, data_os, C.nome, equipamento, defeito, situacao, valor FROM tbos AS O INNER JOIN tbclientes AS C ON (O.idcli = C.idcli)");
            rs=pst.executeQuery();
            
            while(rs.next()){
                modelo.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7)
                        
                    //rs.getString("os"),
                    //rs.getString("data_os"),
                    //rs.getString("nome"),
                    //rs.getString("equipamento"),
                    //rs.getString("defeito"),
                    //rs.getString("situacao"),
                    //rs.getString("valor")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void carregar_valor(){
        String sql = "select sum(valor) as total from tbos where situacao = \"ENTREGUE\"";
        
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                lblValorFaturado.setText("R$ "+rs.getString("total"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void carregar_total(){
        String sql = "select sum(valor) as total from tbos";
        
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                lblValorTotal.setText("R$ "+rs.getString("total"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblOs = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lblValorFaturado = new javax.swing.JLabel();
        lblValorTotal = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Resumo de OS");

        tblOs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "OS", "Data", "Nome", "Equipamento", "Defeito", "Situação", "Valor"
            }
        ));
        jScrollPane1.setViewportView(tblOs);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Total bruto [OS's finalizadas]");

        lblValorFaturado.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblValorFaturado.setText("0");

        lblValorTotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblValorTotal.setText("0");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Total");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblValorTotal)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblValorFaturado, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValorFaturado)
                    .addComponent(lblValorTotal))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        setBounds(0, 0, 665, 523);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblValorFaturado;
    private javax.swing.JLabel lblValorTotal;
    private static javax.swing.JTable tblOs;
    // End of variables declaration//GEN-END:variables
}
