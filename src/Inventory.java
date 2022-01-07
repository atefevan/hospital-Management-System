
import com.sun.glass.events.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Evan
 */
public class Inventory extends javax.swing.JFrame {

    /**
     * Creates new form Inventory
     */
    public Inventory() {
        initComponents();
        
    }

    String pnoo;
    String npno;
    
    public Inventory(String pno) {
        initComponents();
        Connect();
        sales();
        this.pnoo = pno;
        npno = pnoo;
        lblpid.setText(npno);
    }
    
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/hospital","root","");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void sales()
    {
        DateTimeFormatter daa = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String date = daa.format(now);
        
        
        String subtot = txtcost.getText();
        String pay = txtpay.getText();
        String balance = txtbal.getText();
        
        int lastinsertid = 0;
        
        try {
            String query = "insert into sales(date,subtotal,pay,balance)values(?,?,?,?)";
            pst = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, date);
            pst.setString(2, subtot);
            pst.setString(3, pay);
            pst.setString(4, balance);
            
            
            pst.executeUpdate();
            
            rs = pst.getGeneratedKeys();
            if(rs.next())
            {
                lastinsertid = rs.getInt(1);
            }
            int rows = jTable1.getColumnCount();
            String query1 = "insert into sale_product(sales_id,prod_id,sellprice,qty,total)values(?,?,?,?,?)";
            pst = con.prepareStatement(query1);
            String pres_id;
            String item_id;
            String item_name;
            int price;
            String qty;
            int total;
            
            for(int i=0;i<jTable1.getRowCount();i++)
            {
                pres_id = (String)jTable1.getValueAt(i, 0);
                item_id = (String)jTable1.getValueAt(i, 1);
                
                
                price = (int)jTable1.getValueAt(i, 4);
                qty =jTable1.getValueAt(i, 5).toString();
                int qty1 = Integer.parseInt(qty);
                
                
                total = (int)jTable1.getValueAt(i, 6);
                
                pst.setInt(1,lastinsertid);
                pst.setString(2,item_id);
               // pst.setInt(3,item_name);
                pst.setInt(4,price);
                pst.setInt(5,qty1);
                pst.setInt(6,total);
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(this,"Record Added");
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblpid = new javax.swing.JLabel();
        txtcode = new javax.swing.JTextField();
        txtname = new javax.swing.JTextField();
        txtqty = new javax.swing.JSpinner();
        txtcost = new javax.swing.JTextField();
        txtpay = new javax.swing.JTextField();
        txtbal = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Prescription ID", "Drug Code", "Drug Name", "Quantity", "Price", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setPreferredSize(new java.awt.Dimension(453, 0));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, 340, 290));

        lblpid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblpid.setText("jLabel6");
        getContentPane().add(lblpid, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, -1, -1));

        txtcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcodeActionPerformed(evt);
            }
        });
        txtcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcodeKeyPressed(evt);
            }
        });
        getContentPane().add(txtcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 221, 167, 24));
        getContentPane().add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 252, 167, 24));
        getContentPane().add(txtqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 284, 167, 24));
        getContentPane().add(txtcost, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 316, 167, 24));
        getContentPane().add(txtpay, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 347, 167, 24));
        getContentPane().add(txtbal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 379, 167, 24));

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sales Back-button.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 420, 70, 22));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sales Update-button.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 420, 100, 20));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sales Add-button.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, 70, 20));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sales-01.jpg"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodeActionPerformed

    private void txtcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodeKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            String dcode = txtcode.getText();
            try {
                pst = con.prepareStatement("select * from item where itemid = ?");
                pst.setString(1, dcode);
                rs = pst.executeQuery();
                
                
                
                if(rs.next() == false)
                {
                    JOptionPane.showMessageDialog(this,"Drug not found");
                }
                
                else
                {
                    String dname = rs.getString("itemname");
                    txtname.setText(dname.trim());
                    txtqty.requestFocus();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
    }//GEN-LAST:event_txtcodeKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        
        String dcode = txtcode.getText();
        try {
            pst = con.prepareStatement("select * from item where itemid = ?");
             pst.setString(1, dcode);
             rs = pst.executeQuery();
             
             while(rs.next())
             {
                 int currentqty;
                 int sellprice;
                 int qty;
                 
                 
                 currentqty = rs.getInt("qty");
                 sellprice = rs.getInt("sellprice");
                 qty = Integer.parseInt(txtqty.getValue().toString());
                 
                 int tot = sellprice * qty;
                 if(qty >= currentqty)
                 {
                     JOptionPane.showMessageDialog(this, "Availble Item" + currentqty);
                     JOptionPane.showMessageDialog(this, "Amount Not Enough" + currentqty);
                 }
                 else
                 {
                     DefaultTableModel DF = (DefaultTableModel)jTable1.getModel();
                     DF.addRow(new Object[]
                     {
                         
                      lblpid.getText(),
                      txtcode.getText(),
                      txtname.getText(),
                      txtqty.getValue().toString(),
                        sellprice,
                      
                        tot,
                     }); 
                     
                     int sum = 0;
                     for(int i = 0; i <jTable1.getRowCount();i++) 
                     {
                         sum =sum+(Integer.parseInt(jTable1.getValueAt(i,5).toString()));
                         
                     }
                     txtcost.setText(Integer.toString(sum));
                     txtcode.setText("");
                     txtname.setText("");
                     txtqty.setValue(0);
                     txtcode.requestFocus();
                 }
             }
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex); 
        }      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int pay = (Integer.parseInt(txtpay.getText()));
        int totcost = (Integer.parseInt(txtcost.getText()));
        int bal = pay - totcost;
        txtbal.setText(String.valueOf(bal)); 
      //  sales();
        try {
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblpid;
    private javax.swing.JTextField txtbal;
    private javax.swing.JTextField txtcode;
    private javax.swing.JTextField txtcost;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtpay;
    private javax.swing.JSpinner txtqty;
    // End of variables declaration//GEN-END:variables
}