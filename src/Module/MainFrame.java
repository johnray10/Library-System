/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 *
 * @author Jayjomjohn
 */
public class MainFrame extends javax.swing.JFrame {

    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    Statement stm=null;
    
    DefaultTableModel model = new DefaultTableModel();
    int ComboBox;
    
    public MainFrame() {
        initComponents();
        conn=JavaConnector.ConnectDb();
 
        this.Menu.setSelected(true);         
        ImageIcon ic = new ImageIcon(getClass().getResource("/Module/icons8_Library_100px.png"));
        this.setIconImage(ic.getImage());
        
        LocalDate today = LocalDate.now();
        LocalDate reday = today.plus(3, ChronoUnit.DAYS);
        issuesDate.setText(today.toString());
        ReturnDate.setText(reday.toString());
        
         Bargraph();
         Record();
         Clocktime() ;
         Memberstbl();
         Bookstbl();
         booklendtbl();
         Returntbl();
         //Countmembers
         count1() ;
         //Countbooks
         count2();
         //booklends
         count3() ;
         //bookreturn
         count4();
         //History
       showallmembers() ; 
       showallbooks();
       showallbooklends();
       showallPending();
    }
//==================================================================================================    
   public void showallmembers() {  
       try{
            String sql = "SELECT * FROM members ";
            pst=(PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table1.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException e){        
        }
    }
//================================================================================================== 
public void showallbooks() {
         try{
            String sql = "SELECT BookID, BookName, Category, Author, Edition, Publisher, Date, Booktype, Totalbooks, Price, ISBN, Year FROM books ";
            pst=(PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table2.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException e){        
        }
}
//==================================================================================================
public void showallbooklends() {
    try {
        String sql ="SELECT * FROM booklends";
        pst=(PreparedStatement) conn.prepareStatement(sql);
        rs=pst.executeQuery();
        table3.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
    } catch (Exception e) {
    }
}
//==================================================================================================
public void showallPending() {
      try {
            String sql = "SELECT * FROM returns";
            pst=(PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table4.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
     } catch (Exception e) {
     }
}
//==================================================================================================   
  public final void Returntbl() {
     try {
            String sql = "SELECT * FROM returns";
            pst=(PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
            MainFrame.tablereturn.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
     } catch (Exception e) {
     }
 }     
//==================================================================================================
public final void booklendtbl() {
    try {
        String sql ="SELECT * FROM booklends";
        pst=(PreparedStatement) conn.prepareStatement(sql);
        rs=pst.executeQuery();
        tableRecords.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
    } catch (Exception e) {
    }
}        
//==================================================================================================
  public final void Bookstbl() {
      try{
            String sql = "SELECT BookID, BookName, Category, Author, Edition, Publisher, Date, Booktype, Totalbooks, Price, ISBN, Year FROM books ";
            pst=(PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tablebooks.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException e){        
        }
 }             
//==================================================================================================
 public final void Memberstbl(){
      try{
            String sql = "SELECT * FROM members ";
            pst=(PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tablemembers.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException e){        
        }
 }        
//==================================================================================================    
 private void Clocktime() {
    java.util.Date d =new java.util.Date();
    SimpleDateFormat dd = new SimpleDateFormat("EEE LLL/dd/yyyy");
    Date.setText(dd.format(d));
    
    new Timer(0, (ActionEvent e) -> {
    java.util.Date d1 = new java.util.Date();
    SimpleDateFormat dd1 = new SimpleDateFormat("hh:mm:ss aa");
    Time.setText(dd1.format(d1)); 
}).start();       
}     
 //==================================================================================================

 //==================================================================================================
 public void Record() {
     try {
         String sql = "SELECT RecordNo FROM booklends ORDER BY RecordNo DESC LIMIT 1";
         pst=conn.prepareStatement(sql);
         rs=pst.executeQuery();
         if(rs.next())
         {
             String rnno = rs.getString("RecordNo");
             int co = rnno.length();
             String txt = rnno.substring(0, 2);
             String num = rnno.substring(2, co);
             int n = Integer.parseInt(num);
             n++;
             String snum=Integer.toString(n);
             String ftxt=txt+snum;
             RecordID.setText(ftxt);
         }else{
             RecordID.setText("RN100");
         }
     } catch (Exception e) {
         JOptionPane.showMessageDialog(rootPane, e);
     }
 }           
//================================================================================================== 
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        Menu = new javax.swing.JButton();
        SideBar = new javax.swing.JPanel();
        labelImage = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        PN1 = new javax.swing.JPanel();
        labelDashboard = new javax.swing.JLabel();
        PN2 = new javax.swing.JPanel();
        lblAddmembers = new javax.swing.JLabel();
        PN3 = new javax.swing.JPanel();
        lblAddbooks = new javax.swing.JLabel();
        PN4 = new javax.swing.JPanel();
        lblBooklend = new javax.swing.JLabel();
        PN5 = new javax.swing.JPanel();
        lblBookReturn = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        FootBar = new javax.swing.JPanel();
        Time = new javax.swing.JLabel();
        Date = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        DashCard = new javax.swing.JPanel();
        DashBoard = new javax.swing.JPanel();
        PanelHead = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        PanelDash = new javax.swing.JPanel();
        D1 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        Count4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        Count1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        Count2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        Count3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        PanelChart = new javax.swing.JPanel();
        D2 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        Search = new javax.swing.JTextField();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        D3 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        BookID = new javax.swing.JTextField();
        D4 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        table3 = new javax.swing.JTable();
        SearchLend = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        D5 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        table4 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        AddMembers = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        FindMO = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        AddBooks = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        FindBook = new javax.swing.JTextField();
        BookLend = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        b1 = new javax.swing.JTextField();
        b2 = new javax.swing.JTextField();
        b3 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        m3 = new javax.swing.JTextField();
        m1 = new javax.swing.JTextField();
        m2 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        RecordID = new javax.swing.JTextField();
        Clear = new javax.swing.JButton();
        issues = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        issuesDate = new javax.swing.JTextField();
        ReturnDate = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        BookReturn = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        Fine = new javax.swing.JTextField();
        s1 = new javax.swing.JTextField();
        s3 = new javax.swing.JTextField();
        s4 = new javax.swing.JTextField();
        LateDays = new javax.swing.JTextField();
        Return = new javax.swing.JButton();
        s2 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        printingbill = new javax.swing.JTextArea();
        jButton9 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        Totalamount = new javax.swing.JTextField();
        bs1 = new javax.swing.JTextField();
        bs2 = new javax.swing.JTextField();
        Compute = new javax.swing.JButton();
        bs3 = new javax.swing.JTextField();
        SaleAmount = new javax.swing.JTextField();
        Discount = new javax.swing.JTextField();
        AmountBill = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Library Management System");
        setMaximumSize(new java.awt.Dimension(1300, 650));
        setMinimumSize(new java.awt.Dimension(1300, 650));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(1300, 650));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar1.setBackground(new java.awt.Color(52, 73, 94));
        jToolBar1.setRollover(true);
        jToolBar1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        Menu.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Menu.setForeground(new java.awt.Color(204, 204, 204));
        Menu.setText("Menu");
        Menu.setContentAreaFilled(false);
        Menu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Menu.setFocusable(false);
        Menu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Menu.setMargin(new java.awt.Insets(5, 100, 5, 100));
        Menu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuActionPerformed(evt);
            }
        });
        jToolBar1.add(Menu);

        getContentPane().add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 40));

        SideBar.setBackground(new java.awt.Color(52, 73, 94));
        SideBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Admin_100px.png"))); // NOI18N
        SideBar.add(labelImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 150, 120));
        SideBar.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 230, 20));

        PN1.setBackground(new java.awt.Color(37, 116, 169));
        PN1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelDashboard.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labelDashboard.setForeground(new java.awt.Color(204, 204, 204));
        labelDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Home_35px.png"))); // NOI18N
        labelDashboard.setText("Dashboard");
        labelDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        labelDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelDashboardMouseClicked(evt);
            }
        });
        PN1.add(labelDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 10, 240, 60));

        SideBar.add(PN1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 270, 80));

        PN2.setBackground(new java.awt.Color(52, 73, 94));
        PN2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAddmembers.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblAddmembers.setForeground(new java.awt.Color(204, 204, 204));
        lblAddmembers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Add_User_Group_Woman_Man_35px.png"))); // NOI18N
        lblAddmembers.setText("Add Members");
        lblAddmembers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAddmembers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAddmembersMouseClicked(evt);
            }
        });
        PN2.add(lblAddmembers, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 10, 240, 60));

        SideBar.add(PN2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 270, 80));

        PN3.setBackground(new java.awt.Color(52, 73, 94));
        PN3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAddbooks.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblAddbooks.setForeground(new java.awt.Color(204, 204, 204));
        lblAddbooks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Book_Shelf_35px.png"))); // NOI18N
        lblAddbooks.setText("Add Books");
        lblAddbooks.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAddbooks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAddbooksMouseClicked(evt);
            }
        });
        PN3.add(lblAddbooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 10, 240, 60));

        SideBar.add(PN3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 270, 80));

        PN4.setBackground(new java.awt.Color(52, 73, 94));
        PN4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBooklend.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblBooklend.setForeground(new java.awt.Color(204, 204, 204));
        lblBooklend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Borrow_Book_35px.png"))); // NOI18N
        lblBooklend.setText("Book Lend");
        lblBooklend.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBooklend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBooklendMouseClicked(evt);
            }
        });
        PN4.add(lblBooklend, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 10, 240, 60));

        SideBar.add(PN4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 270, 80));

        PN5.setBackground(new java.awt.Color(52, 73, 94));
        PN5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBookReturn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblBookReturn.setForeground(new java.awt.Color(204, 204, 204));
        lblBookReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Return_Book_35px.png"))); // NOI18N
        lblBookReturn.setText("Book Return");
        lblBookReturn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBookReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBookReturnMouseClicked(evt);
            }
        });
        PN5.add(lblBookReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 10, 240, 60));

        SideBar.add(PN5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 270, 80));

        jButton1.setBackground(new java.awt.Color(150, 40, 27));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Exit_35px.png"))); // NOI18N
        jButton1.setText("Log-Out");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        SideBar.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 566, 240, 40));

        getContentPane().add(SideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 270, 610));

        jPanel3.setBackground(new java.awt.Color(52, 73, 94));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 30, 30, 620));

        FootBar.setBackground(new java.awt.Color(52, 73, 94));
        FootBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Time.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Time.setForeground(new java.awt.Color(204, 204, 204));
        Time.setText("---------");
        FootBar.add(Time, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 0, 170, 50));

        Date.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Date.setForeground(new java.awt.Color(204, 204, 204));
        Date.setText("---------");
        FootBar.add(Date, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 190, 50));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Clock_35px.png"))); // NOI18N
        jLabel9.setText("Time");
        FootBar.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 110, 50));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Today_35px.png"))); // NOI18N
        jLabel10.setText("Date");
        FootBar.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, 50));

        getContentPane().add(FootBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 600, 1000, 50));

        DashCard.setLayout(new java.awt.CardLayout());

        DashBoard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelHead.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        PanelHead.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DashBoard", "All Members", "All Books", "Lend History", "Return History" }));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        PanelHead.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 15, 960, 40));

        DashBoard.add(PanelHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 980, 70));

        PanelDash.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        PanelDash.setLayout(new java.awt.CardLayout());

        D1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dashboard", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        D1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(30, 130, 76));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Count4.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        Count4.setForeground(new java.awt.Color(204, 204, 204));
        Count4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Count4.setText("---------");
        jPanel10.add(Count4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 50));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Return_Book_35px.png"))); // NOI18N
        jLabel6.setText("Book Return");
        jPanel10.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 180, 50));

        D1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, 200, 130));

        jPanel11.setBackground(new java.awt.Color(0, 102, 153));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Count1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        Count1.setForeground(new java.awt.Color(204, 204, 204));
        Count1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Count1.setText("---------");
        jPanel11.add(Count1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 50));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_User_Groups_35px.png"))); // NOI18N
        jLabel8.setText("Members");
        jPanel11.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 180, 50));

        D1.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 200, 130));

        jPanel12.setBackground(new java.awt.Color(52, 73, 94));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Count2.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        Count2.setForeground(new java.awt.Color(204, 204, 204));
        Count2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Count2.setText("---------");
        jPanel12.add(Count2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 50));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Read_35px.png"))); // NOI18N
        jLabel12.setText("Books");
        jPanel12.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 180, 50));

        D1.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 200, 130));

        jPanel13.setBackground(new java.awt.Color(207, 0, 15));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Count3.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        Count3.setForeground(new java.awt.Color(204, 204, 204));
        Count3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Count3.setText("---------");
        jPanel13.add(Count3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 50));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Borrow_Book_35px.png"))); // NOI18N
        jLabel14.setText("Book Lend");
        jPanel13.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 180, 50));

        D1.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 200, 130));

        PanelChart.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        PanelChart.setLayout(new java.awt.BorderLayout());
        D1.add(PanelChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 930, 270));

        PanelDash.add(D1, "card2");

        D2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "All Members", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        D2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MemberID", "MemberName", "DateRegister", "Email", "Contact", "Gender", "MemberType", "Birthday", "Address"
            }
        ));
        jScrollPane10.setViewportView(table1);

        D2.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 83, 950, 360));

        Search.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        Search.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        Search.setOpaque(false);
        Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchKeyReleased(evt);
            }
        });
        D2.add(Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 240, 50));

        jButton17.setBackground(new java.awt.Color(102, 102, 102));
        jButton17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton17.setForeground(new java.awt.Color(204, 204, 204));
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Eraser_35px.png"))); // NOI18N
        jButton17.setText("Clear");
        jButton17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jButton17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        D2.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 140, 50));

        jButton18.setBackground(new java.awt.Color(0, 102, 102));
        jButton18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton18.setForeground(new java.awt.Color(204, 204, 204));
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Print_35px.png"))); // NOI18N
        jButton18.setText("Report");
        jButton18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jButton18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        D2.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, 130, 50));

        jButton19.setBackground(new java.awt.Color(153, 0, 0));
        jButton19.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton19.setForeground(new java.awt.Color(204, 204, 204));
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Trash_Can_35px.png"))); // NOI18N
        jButton19.setText("Delete");
        jButton19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jButton19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        D2.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, 130, 50));

        PanelDash.add(D2, "card3");

        D3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "All Books", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        D3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane9.setViewportView(table2);

        D3.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 950, 360));

        jButton14.setBackground(new java.awt.Color(153, 0, 0));
        jButton14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton14.setForeground(new java.awt.Color(204, 204, 204));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Trash_Can_35px.png"))); // NOI18N
        jButton14.setText("Delete");
        jButton14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        D3.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, 130, 50));

        jButton15.setBackground(new java.awt.Color(0, 102, 102));
        jButton15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton15.setForeground(new java.awt.Color(204, 204, 204));
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Print_35px.png"))); // NOI18N
        jButton15.setText("Print");
        jButton15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jButton15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        D3.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, 130, 50));

        jButton16.setBackground(new java.awt.Color(102, 102, 102));
        jButton16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton16.setForeground(new java.awt.Color(204, 204, 204));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Eraser_35px.png"))); // NOI18N
        jButton16.setText("Clear");
        jButton16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jButton16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        D3.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 140, 50));

        BookID.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        BookID.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        BookID.setOpaque(false);
        BookID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BookIDKeyReleased(evt);
            }
        });
        D3.add(BookID, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 240, 50));

        PanelDash.add(D3, "card4");

        D4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lend History", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        D4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(table3);

        D4.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 83, 950, 360));

        SearchLend.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        SearchLend.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        SearchLend.setOpaque(false);
        SearchLend.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchLendKeyReleased(evt);
            }
        });
        D4.add(SearchLend, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 240, 50));

        jButton11.setBackground(new java.awt.Color(0, 102, 102));
        jButton11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton11.setForeground(new java.awt.Color(204, 204, 204));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Print_35px.png"))); // NOI18N
        jButton11.setText("Print");
        jButton11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        D4.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 130, 50));

        jButton12.setBackground(new java.awt.Color(153, 0, 0));
        jButton12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton12.setForeground(new java.awt.Color(204, 204, 204));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Trash_Can_35px.png"))); // NOI18N
        jButton12.setText("Delete");
        jButton12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        D4.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 20, 140, 50));

        jButton13.setBackground(new java.awt.Color(102, 102, 102));
        jButton13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton13.setForeground(new java.awt.Color(204, 204, 204));
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Eraser_35px.png"))); // NOI18N
        jButton13.setText("Clear");
        jButton13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        D4.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 130, 50));

        PanelDash.add(D4, "card5");

        D5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Return List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        D5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table4.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(table4);

        D5.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 950, 360));

        jButton6.setBackground(new java.awt.Color(0, 102, 102));
        jButton6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(204, 204, 204));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Print_35px.png"))); // NOI18N
        jButton6.setText("Print Report");
        jButton6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        D5.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, 160, 50));

        jButton7.setBackground(new java.awt.Color(153, 0, 0));
        jButton7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(204, 204, 204));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Trash_Can_35px.png"))); // NOI18N
        jButton7.setText("Delete");
        jButton7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        D5.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(811, 20, 140, 50));

        PanelDash.add(D5, "card6");

        DashBoard.add(PanelDash, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 980, 460));

        DashCard.add(DashBoard, "card2");

        AddMembers.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablemembers.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablemembers);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 960, 440));

        FindMO.setBackground(new java.awt.Color(238, 238, 238));
        FindMO.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        FindMO.setForeground(new java.awt.Color(0, 51, 102));
        FindMO.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Find", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        FindMO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FindMOKeyReleased(evt);
            }
        });
        jPanel1.add(FindMO, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 240, 50));

        jButton2.setBackground(new java.awt.Color(0, 102, 153));
        jButton2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(204, 204, 204));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Add_User_Group_Woman_Man_35px.png"))); // NOI18N
        jButton2.setText("Add Members");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, 170, 50));

        jButton3.setBackground(new java.awt.Color(0, 102, 153));
        jButton3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(204, 204, 204));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_User_Groups_35px.png"))); // NOI18N
        jButton3.setText("Update");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 20, 140, 50));

        AddMembers.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 980, 540));

        DashCard.add(AddMembers, "card3");

        AddBooks.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        AddBooks.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablebooks.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablebooks);

        AddBooks.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 90, 970, 450));

        jButton4.setBackground(new java.awt.Color(0, 102, 153));
        jButton4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(204, 204, 204));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Edit_File_35px.png"))); // NOI18N
        jButton4.setText("Update");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        AddBooks.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 20, 140, 50));

        jButton5.setBackground(new java.awt.Color(0, 102, 153));
        jButton5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(204, 204, 204));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Read_35px.png"))); // NOI18N
        jButton5.setText("Add Book");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        AddBooks.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, 140, 50));

        FindBook.setBackground(new java.awt.Color(238, 238, 238));
        FindBook.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        FindBook.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Find", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        FindBook.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FindBookKeyReleased(evt);
            }
        });
        AddBooks.add(FindBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 240, 50));

        DashCard.add(AddBooks, "card4");

        BookLend.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Borrow");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 530, 40));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Books", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        b1.setBackground(new java.awt.Color(238, 238, 238));
        b1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        b1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Book ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        b1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                b1KeyReleased(evt);
            }
        });
        jPanel4.add(b1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 240, 50));

        b2.setBackground(new java.awt.Color(238, 238, 238));
        b2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        b2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Book Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel4.add(b2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 240, 50));

        b3.setBackground(new java.awt.Color(238, 238, 238));
        b3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        b3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Book Type", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel4.add(b3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 240, 50));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 260, 180));

        tableRecords.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tableRecords);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 950, 210));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Members", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        m3.setBackground(new java.awt.Color(238, 238, 238));
        m3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        m3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Member Type", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel5.add(m3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 240, 50));

        m1.setBackground(new java.awt.Color(238, 238, 238));
        m1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        m1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Member ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        m1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                m1KeyReleased(evt);
            }
        });
        jPanel5.add(m1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 240, 50));

        m2.setBackground(new java.awt.Color(238, 238, 238));
        m2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        m2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Member Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel5.add(m2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 240, 50));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 260, 180));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Record Number", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RecordID.setBackground(new java.awt.Color(238, 238, 238));
        RecordID.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        RecordID.setForeground(new java.awt.Color(153, 0, 0));
        RecordID.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Record ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14), new java.awt.Color(153, 0, 0))); // NOI18N
        jPanel6.add(RecordID, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 390, 50));

        Clear.setBackground(new java.awt.Color(204, 204, 204));
        Clear.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Eraser_35px.png"))); // NOI18N
        Clear.setText("Clear");
        Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearActionPerformed(evt);
            }
        });
        jPanel6.add(Clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 76, 390, 40));

        issues.setBackground(new java.awt.Color(0, 102, 153));
        issues.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        issues.setForeground(new java.awt.Color(204, 204, 204));
        issues.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_CBR_35px.png"))); // NOI18N
        issues.setText("Issues");
        issues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issuesActionPerformed(evt);
            }
        });
        jPanel6.add(issues, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 390, 40));

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 410, 220));

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        issuesDate.setEditable(false);
        issuesDate.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        issuesDate.setForeground(new java.awt.Color(0, 102, 0));
        issuesDate.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Issues Date", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        issuesDate.setOpaque(false);
        jPanel7.add(issuesDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 390, 50));

        ReturnDate.setEditable(false);
        ReturnDate.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        ReturnDate.setForeground(new java.awt.Color(0, 102, 0));
        ReturnDate.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Return Date", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        ReturnDate.setOpaque(false);
        jPanel7.add(ReturnDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 390, 50));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("<==============>");
        jPanel7.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 130, 40));

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 950, 70));

        BookLend.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 980, 540));

        DashCard.add(BookLend, "card5");

        BookReturn.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153)));
        BookReturn.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Return");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        BookReturn.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 980, 30));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Return", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Fine.setBackground(new java.awt.Color(238, 238, 238));
        Fine.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Fine.setForeground(new java.awt.Color(153, 0, 0));
        Fine.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fine", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel8.add(Fine, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 240, 50));

        s1.setBackground(new java.awt.Color(238, 238, 238));
        s1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        s1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Book ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        s1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                s1KeyReleased(evt);
            }
        });
        jPanel8.add(s1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 240, 50));

        s3.setBackground(new java.awt.Color(238, 238, 238));
        s3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        s3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Member ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel8.add(s3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 240, 50));

        s4.setBackground(new java.awt.Color(238, 238, 238));
        s4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        s4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Member Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel8.add(s4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 240, 50));

        LateDays.setBackground(new java.awt.Color(238, 238, 238));
        LateDays.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LateDays.setForeground(new java.awt.Color(153, 0, 0));
        LateDays.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Late Days", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel8.add(LateDays, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 240, 50));

        Return.setBackground(new java.awt.Color(0, 51, 102));
        Return.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        Return.setForeground(new java.awt.Color(204, 204, 204));
        Return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Return_Book_35px.png"))); // NOI18N
        Return.setText("Return");
        Return.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnActionPerformed(evt);
            }
        });
        jPanel8.add(Return, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 240, 40));

        s2.setBackground(new java.awt.Color(238, 238, 238));
        s2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        s2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BookName", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel8.add(s2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 240, 50));

        BookReturn.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 260, 380));

        tablereturn.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tablereturn.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tablereturn);

        BookReturn.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 780, 120));

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Printing", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        printingbill.setBackground(new java.awt.Color(238, 238, 238));
        printingbill.setColumns(20);
        printingbill.setRows(5);
        jScrollPane5.setViewportView(printingbill);

        jPanel15.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 26, 490, 270));

        jButton9.setBackground(new java.awt.Color(0, 102, 102));
        jButton9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton9.setForeground(new java.awt.Color(204, 204, 204));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Bill_35px.png"))); // NOI18N
        jButton9.setText("Print Bill");
        jButton9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 500, 70));

        BookReturn.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 520, 380));

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Book Sales"));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Totalamount.setEditable(false);
        Totalamount.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        Totalamount.setForeground(new java.awt.Color(153, 0, 0));
        Totalamount.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Amount", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        Totalamount.setOpaque(false);
        jPanel16.add(Totalamount, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 160, 50));

        bs1.setBackground(new java.awt.Color(238, 238, 238));
        bs1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        bs1.setToolTipText("Enter Your Book Name");
        bs1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BookName", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        bs1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bs1KeyReleased(evt);
            }
        });
        jPanel16.add(bs1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 160, 50));

        bs2.setEditable(false);
        bs2.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        bs2.setForeground(new java.awt.Color(0, 102, 153));
        bs2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total Books", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        bs2.setOpaque(false);
        jPanel16.add(bs2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 160, 50));

        Compute.setBackground(new java.awt.Color(0, 102, 153));
        Compute.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Compute.setForeground(new java.awt.Color(204, 204, 204));
        Compute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Money_35px.png"))); // NOI18N
        Compute.setText("Compute");
        Compute.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        Compute.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Compute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComputeActionPerformed(evt);
            }
        });
        jPanel16.add(Compute, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 160, 80));

        bs3.setEditable(false);
        bs3.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        bs3.setForeground(new java.awt.Color(0, 102, 153));
        bs3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Price", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        bs3.setOpaque(false);
        jPanel16.add(bs3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 160, 50));

        SaleAmount.setEditable(false);
        SaleAmount.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        SaleAmount.setForeground(new java.awt.Color(153, 0, 0));
        SaleAmount.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sales Amount", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        SaleAmount.setOpaque(false);
        jPanel16.add(SaleAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 160, 50));

        Discount.setEditable(false);
        Discount.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        Discount.setForeground(new java.awt.Color(153, 0, 0));
        Discount.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Discount", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        Discount.setOpaque(false);
        jPanel16.add(Discount, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 160, 50));

        AmountBill.setEditable(false);
        AmountBill.setFont(new java.awt.Font("Monospaced", 1, 28)); // NOI18N
        AmountBill.setForeground(new java.awt.Color(0, 102, 51));
        AmountBill.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Amount Bill", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        AmountBill.setOpaque(false);
        jPanel16.add(AmountBill, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 160, 60));

        BookReturn.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, 180, 510));

        DashCard.add(BookReturn, "card6");

        getContentPane().add(DashCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 1000, 560));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void labelDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelDashboardMouseClicked
               PN1.setBackground(new Color(37, 116, 169));
               
               PN2.setBackground(new Color(52,73,94));  
               PN3.setBackground(new Color(52,73,94));
               PN4.setBackground(new Color(52,73,94));
               PN5.setBackground(new Color(52,73,94));
//========================================
          //removing panel
          DashCard.removeAll(); 
          DashCard.repaint();
          DashCard.revalidate();        
         //adding panel
         DashCard.add(DashBoard);
         DashCard.repaint();
         DashCard.revalidate();  
//==========================================
     
    }//GEN-LAST:event_labelDashboardMouseClicked

    private void lblAddmembersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAddmembersMouseClicked
               PN2.setBackground(new Color(37, 116, 169));
           
               PN1.setBackground(new Color(52,73,94));
               PN3.setBackground(new Color(52,73,94));
               PN4.setBackground(new Color(52,73,94));
               PN5.setBackground(new Color(52,73,94));
//=============================================
           //removing panel
         DashCard.removeAll();
         DashCard.repaint();
         DashCard.revalidate();        
         //adding panel
         DashCard.add(AddMembers);
         DashCard.repaint();
         DashCard.revalidate();
//=============================================
 
    }//GEN-LAST:event_lblAddmembersMouseClicked

    private void lblAddbooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAddbooksMouseClicked
               PN3.setBackground(new Color(37, 116, 169));
           
               PN1.setBackground(new Color(52,73,94));
               PN2.setBackground(new Color(52,73,94));
               PN4.setBackground(new Color(52,73,94));
               PN5.setBackground(new Color(52,73,94));
//====================================================
          //removing panel
         DashCard.removeAll();
         DashCard.repaint();
         DashCard.revalidate();        
         //adding panel
         DashCard.add(AddBooks);
         DashCard.repaint();
         DashCard.revalidate();  
    }//GEN-LAST:event_lblAddbooksMouseClicked

    private void lblBooklendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBooklendMouseClicked
               PN4.setBackground(new Color(37, 116, 169));
           
               PN1.setBackground(new Color(52,73,94));
               PN2.setBackground(new Color(52,73,94));
               PN3.setBackground(new Color(52,73,94));
               PN5.setBackground(new Color(52,73,94));
//========================================================
              //removing panel
             DashCard.removeAll();
             DashCard.repaint();
             DashCard.revalidate();        
            //adding panel
            DashCard.add(BookLend);
            DashCard.repaint();
            DashCard.revalidate();  
    }//GEN-LAST:event_lblBooklendMouseClicked

    private void lblBookReturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBookReturnMouseClicked
               PN5.setBackground(new Color(37, 116, 169));
           
               PN1.setBackground(new Color(52,73,94));
               PN2.setBackground(new Color(52,73,94));
               PN3.setBackground(new Color(52,73,94));
               PN4.setBackground(new Color(52,73,94));
//============================================================
        //removing panel
        DashCard.removeAll();
        DashCard.repaint();
        DashCard.revalidate();        
        //adding panel
        DashCard.add(BookReturn);
        DashCard.repaint();
        DashCard.revalidate();  
    }//GEN-LAST:event_lblBookReturnMouseClicked

    private void MenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuActionPerformed
              MainFrame c = new MainFrame();
              c.setVisible(true);
              this.dispose();
               Bargraph();
//          int position = this.SideBar.getX();
//        if( position < -1)
//        {
//            Animacion.Animacion.mover_derecha(-220, 0, 2, 2, SideBar);
//        }else{
//            Animacion.Animacion.mover_izquierda(0, -220, 2, 2, SideBar); 
//        }
    }//GEN-LAST:event_MenuActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     AddMembers a1 = new AddMembers();
     a1.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
     UpdateMembers a2 = new UpdateMembers();
     a2.setVisible(true);
     
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          int p = JOptionPane.showConfirmDialog(null,"Are you sure you want to logout?","System message!",JOptionPane.YES_NO_OPTION);  
    if (p==0) {
        JOptionPane.showMessageDialog(rootPane, "Thank You:D\n", "System Out!", 1);
       
    System.exit(0);
   new Login().setVisible(true);
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void FindMOKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FindMOKeyReleased
         try{
            String sql = "SELECT * FROM members WHERE membername LIKE '%"+FindMO.getText()+"%' ";
            pst=(PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tablemembers.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_FindMOKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        AddBooks a = new AddBooks();
        a.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        UpdateBook u = new UpdateBook();
        u.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void m1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m1KeyReleased
      try{
          String sql = "SELECT * FROM members WHERE memberID=?";
          pst=conn.prepareCall(sql);
          pst.setString(1,m1.getText());
          rs = pst.executeQuery();
           
            if(rs.next()) { 
             m1.setText(rs.getString("MemberID"));
             m2.setText(rs.getString("MemberName"));
             m3.setText(rs.getString("Membertype"));                
          }else{               
             m2.setText("");
             m3.setText("");
            }       
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_m1KeyReleased

    private void b1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b1KeyReleased
         try{
          String sql = "SELECT * FROM books WHERE bookID=?";
          pst=conn.prepareCall(sql);
          pst.setString(1,b1.getText());
          rs = pst.executeQuery();
           
            if(rs.next()) { 
             b1.setText(rs.getString("bookID"));
             b2.setText(rs.getString("bookName"));
             b3.setText(rs.getString("booktype"));                
          }else{               
             b2.setText("");
             b3.setText("");
            }       
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_b1KeyReleased

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
      m1.setText("");
      m2.setText("");
      m3.setText("");
      b1.setText("");
      b2.setText("");
      b3.setText("");
    }//GEN-LAST:event_ClearActionPerformed

    private void issuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issuesActionPerformed
             int x = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Add Record?","Add Record",JOptionPane.YES_NO_OPTION);
        if(x==0){  
        try {
            String sql = "INSERT INTO booklends "
            + "(RecordNo, MemberNo, MemberName, MemberType, BookNo, BookName, BookType, issuesDate, Returndate, Marks)"
            + "values(?,?,?,?,?,?,?,?,?,0)";

            pst=conn.prepareStatement(sql);
            pst.setString(1,RecordID.getText());
            pst.setString(2,m1.getText());
            pst.setString(3,m2.getText());
            pst.setString(4,m3.getText());
            pst.setString(5,b1.getText());
            pst.setString(6,b2.getText());
            pst.setString(7,b3.getText());
            pst.setString(8,issuesDate.getText());
            pst.setString(9,ReturnDate.getText());            
            pst.execute();
            booklendtbl();
            Update() ;
            Bargraph();
          
            JOptionPane.showMessageDialog(rootPane,"Booklend Record Has Been Save!","Successfull", 1);
            MainFrame c = new MainFrame();
            c.setVisible(true);
            this.dispose();
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }      
        }
    }//GEN-LAST:event_issuesActionPerformed

    private void s1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_s1KeyReleased
        try {
            String sql = "SELECT MemberNo, MemberName, BookName, ReturnDate FROM booklends WHERE BookNo= ? and marks= '0' ";
            pst=conn.prepareCall(sql);
            pst.setString(1,s1.getText());
            rs=pst.executeQuery();
            if(rs.next())
            {
                s2.setText(rs.getString("BookName"));
                s3.setText(rs.getString("MemberNo"));
                s4.setText(rs.getString("MemberName"));
                String rdate=rs.getString("ReturnDate");
                LocalDate today=LocalDate.now();
                LocalDate rday=LocalDate.parse(rdate);
                Long day_gap=ChronoUnit.DAYS.between(rday, today);
                if(day_gap>0)
                {
                    LateDays.setText(day_gap.toString());
                    long fine=20*day_gap;
                    Fine.setText(String.valueOf(fine));
                }else{
                    LateDays.setText("0");
                    Fine.setText("0");                  
                }
            }else{
                s2.setText("");
                s3.setText("");
                s4.setText("");
                LateDays.setText("");
                Fine.setText("");
            }
        }catch(Exception e){
        }
        printbill() ;
    }//GEN-LAST:event_s1KeyReleased

    private void bs1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bs1KeyReleased
            try{
          String sql = "SELECT TotalBooks, Price FROM books WHERE bookName=?";
          pst=conn.prepareCall(sql);
          pst.setString(1,bs1.getText());
          rs = pst.executeQuery();
           
            if(rs.next()) { 
             bs2.setText(rs.getString("TotalBooks"));   
             bs3.setText(rs.getString("Price"));
          }else{               
             bs2.setText("");
             bs3.setText("");
            }       
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_bs1KeyReleased

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        try {
            printingbill.print();
        } catch (PrinterException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void ComputeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComputeActionPerformed
        try {
            double a=Double.parseDouble(bs2.getText());
            double b=Double.parseDouble(bs3.getText());
            double c=Double.parseDouble(Fine.getText());
            double amount=a*b;
            double sales=amount*0.1;
            double totalamount=amount-sales;
            double netamount=totalamount+c;
                    
            SaleAmount.setText(""+amount);
            Discount.setText(""+sales);
            Totalamount.setText(""+totalamount);
            AmountBill.setText("P "+netamount);
            printbill();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_ComputeActionPerformed

    private void ReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnActionPerformed
              int x = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Add Record?","Add Record",JOptionPane.YES_NO_OPTION);
        if(x==0){  
        try {
            String sql = "INSERT INTO returns "
            + "(Returnid, Bookid, BookTitle, memberid, memberName, LateDays, Fine, AmountBill)"
            + "values(0,?,?,?,?,?,?,?)";

            pst=conn.prepareStatement(sql);
            pst.setString(1,s1.getText());
            pst.setString(2,s2.getText());
            pst.setString(3,s3.getText());
            pst.setString(4,s4.getText());
            pst.setString(5,LateDays.getText());
            pst.setString(6,Fine.getText());
            pst.setString(7,AmountBill.getText());         
            pst.execute();
            Returntbl();
            Bargraph();
          //Countmembers
          count1() ;
          //Countbooks
          count2();
          //booklends
          count3() ;
         //bookreturn
         count4();
            JOptionPane.showMessageDialog(rootPane,"BookReturn Record Has Been Save!","Successfull", 1);
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }      
        }
    }//GEN-LAST:event_ReturnActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
      ComboBox = jComboBox1.getSelectedIndex();
        
        if(ComboBox == 0) {           
        //removing panel
        PanelDash.removeAll();
        PanelDash.repaint();
        PanelDash.revalidate();        
        //adding panel
        PanelDash.add(D1);
        PanelDash.repaint();
        PanelDash.revalidate();       
        }
        
        if(ComboBox == 1) {            
        //removing panel
        PanelDash.removeAll();
        PanelDash.repaint();
        PanelDash.revalidate();        
        //adding panel
        PanelDash.add(D2);
        PanelDash.repaint();
        PanelDash.revalidate();      
        }
        if(ComboBox== 2) {
        //removing panel
        PanelDash.removeAll();
        PanelDash.repaint();
        PanelDash.revalidate();        
        //adding panel
        PanelDash.add(D3);
        PanelDash.repaint();
        PanelDash.revalidate();    
        }
        if(ComboBox == 3) {
        //removing panel
        PanelDash.removeAll();
        PanelDash.repaint();
        PanelDash.revalidate();        
        //adding panel
        PanelDash.add(D4);
        PanelDash.repaint();
        PanelDash.revalidate();    
        }
        if(ComboBox == 4){
        //removing panel
        PanelDash.removeAll();
        PanelDash.repaint();
        PanelDash.revalidate();        
        //adding panel
        PanelDash.add(D5);
        PanelDash.repaint();
        PanelDash.revalidate();    
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void FindBookKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FindBookKeyReleased
          try{
            String sql = "SELECT * FROM books WHERE BookID LIKE '%"+FindBook.getText()+"%' ";
            pst=(PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tablebooks.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_FindBookKeyReleased

    private void SearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyReleased
            try{
            String sql = "SELECT * FROM members WHERE memberid LIKE '%"+Search.getText()+"%' ";
            pst=(PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table1.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_SearchKeyReleased

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
       Search.setText("");
       showallmembers() ; 
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
         
        int p = JOptionPane.showConfirmDialog(null,"Do You Really Want To Delete?","DELETE",JOptionPane.YES_NO_OPTION);
    if(p==0){
    
        try{
            int row =table1.getSelectedRow();
            String value = (table1.getModel().getValueAt(row, 0).toString());
            String sql ="DELETE FROM members WHERE memberid ="+value;
            pst=conn.prepareStatement(sql);         
            pst.executeUpdate();
            showallmembers() ; 
           Bargraph();
            JOptionPane.showMessageDialog(null,"Entry Successfully Deleted","Deleted", 3);
        
            }catch(SQLException e){
                   JOptionPane.showMessageDialog(this, e);   
        }        
        }    
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
   try {
            String file = "src/Report/report1.jrxml ";
            JasperReport jr = JasperCompileManager.compileReport(file);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn=JavaConnector.ConnectDb());
            JasperViewer.viewReport(jp);
        } catch (Exception e) {
            
        }
        
    }//GEN-LAST:event_jButton18ActionPerformed

    private void BookIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BookIDKeyReleased
            try{
            String sql = "SELECT * FROM books WHERE bookid LIKE '%"+BookID.getText()+"%' ";
            pst=(PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table2.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_BookIDKeyReleased

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
  
        int p = JOptionPane.showConfirmDialog(null,"Do You Really Want To Delete?","DELETE",JOptionPane.YES_NO_OPTION);
    if(p==0){
    
        try{
            int row =table2.getSelectedRow();
            String value = (table2.getModel().getValueAt(row, 0).toString());
            String sql ="DELETE FROM books WHERE bookid ="+value;
            pst=conn.prepareStatement(sql);         
            pst.executeUpdate();
           showallbooks();
           Bargraph();
            JOptionPane.showMessageDialog(null,"Entry Successfully Deleted","Deleted", 3);
        
            }catch(SQLException e){
                   JOptionPane.showMessageDialog(this, e);   
        }        
        }    
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        MessageFormat header = new MessageFormat("Report");
        MessageFormat footer = new MessageFormat("Page {0,number,integer}");
        try{
            table1.print(JTable.PrintMode.FIT_WIDTH, header, footer, true, null, true, null);
        }catch(java.awt.print.PrinterException e){
            System.err.format("Cannot print %s%n", e.getMessage());
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
     BookID.setText("");
     showallbooks();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
    SearchLend.setText("");
    showallbooklends();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
       
        int p = JOptionPane.showConfirmDialog(null,"Do You Really Want To Delete?","DELETE",JOptionPane.YES_NO_OPTION);
    if(p==0){
    
        try{
            int row =table3.getSelectedRow();
            String value = (table3.getModel().getValueAt(row, 0).toString());
            String sql ="DELETE FROM booklends WHERE id="+value;
            pst=conn.prepareStatement(sql);         
            pst.executeUpdate();
           showallbooklends();
              Bargraph();
            JOptionPane.showMessageDialog(null,"Entry Successfully Deleted","Deleted", 3);
        
            }catch(SQLException e){
                   JOptionPane.showMessageDialog(this, e);   
        }        
        }    
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        MessageFormat header = new MessageFormat("Report");
        MessageFormat footer = new MessageFormat("Page {0,number,integer}");
        try{
            table3.print(JTable.PrintMode.FIT_WIDTH, header, footer, true, null, true, null);
        }catch(java.awt.print.PrinterException e){
            System.err.format("Cannot print %s%n", e.getMessage());
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void SearchLendKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchLendKeyReleased
            try{
            String sql = "SELECT * FROM booklends WHERE membername LIKE '%"+SearchLend.getText()+"%' ";
            pst=(PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table3.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_SearchLendKeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       
        int p = JOptionPane.showConfirmDialog(null,"Do You Really Want To Delete?","DELETE",JOptionPane.YES_NO_OPTION);
    if(p==0){
    
        try{
            int row =table4.getSelectedRow();
            String value = (table4.getModel().getValueAt(row, 0).toString());
            String sql ="DELETE FROM returns WHERE returnID="+value;
            pst=conn.prepareStatement(sql);         
            pst.executeUpdate();
            showallPending();
           Bargraph();
            JOptionPane.showMessageDialog(null,"Entry Successfully Deleted","Deleted", 3);
        
            }catch(SQLException e){
                   JOptionPane.showMessageDialog(this, e);   
        }        
        }    
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            String file = "src/Report/report2.jrxml ";
            JasperReport jr = JasperCompileManager.compileReport(file);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn=JavaConnector.ConnectDb());
            JasperViewer.viewReport(jp);
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_jButton6ActionPerformed
//==================================================================================================
   public void printbill() {
        //TextArea
    printingbill.setText("================================================================"+"\n"
    +"================Library Management System======================="+" \n"
    +"BookID\t"+s1.getText()+"\n"
    +"BookName\t"+s2.getText()+"\n"
    +"MemberID\t"+s3.getText()+"\n"
    +"MemberName\t"+s4.getText()+"\n"
    +"No of Days\t"+LateDays.getText()+"\n"
    +"Fine Amount\t"+Fine.getText()+"\n"
    +"TotalBooks\t"+bs2.getText()+"\n"
    +"Price\t"+bs3.getText()+"\n"
    +"Sale Amount\t"+SaleAmount.getText()+"\n"
    +"Discount\t"+Discount.getText()+"\n"
    +"Total Amount\t"+Totalamount.getText()+"\n"
    +"===============================================================\t\n"
    +"Amount Bill\t"+AmountBill.getText()+"\n"
    +"================================================================");
}    
//==================================================================================================
public void Update() {
    try {
        String sql = "UPDATE books SET marks='1' WHERE BookID='"+b1.getText()+"'";
        pst=(PreparedStatement) conn.prepareStatement(sql);
        pst.execute();
        
        JOptionPane.showMessageDialog(rootPane, "Updated Success");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, e);
    }
}      
//================================================================================================== 
 public void count1()  {         
          try {
                String sql = "select count(memberid) as memberid from members";
                pst = conn.prepareStatement(sql);
                rs=pst.executeQuery();             
                if(rs.next()) {                    
                    String sum=rs.getString("MemberID");
                    Count1.setText(sum);                
                }
          } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, e);
          }
      }      
//==================================================================================================
  public void count2()  {         
          try {
                String sql = "select sum(TotalBooks) from books";
                pst = conn.prepareStatement(sql);
                rs=pst.executeQuery();             
                if(rs.next()) {                    
                    String sum=rs.getString("sum(TotalBooks)");
                    Count2.setText(sum);                
                }
          } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, e);
          }
      }     
//==================================================================================================
    public void count3()  {         
          try {
                String sql = "select count(RecordNo) as RecordNo from booklends";
                pst = conn.prepareStatement(sql);
                rs=pst.executeQuery();             
                if(rs.next()) {                    
                    String sum=rs.getString("RecordNo");
                    Count3.setText(sum);                
                }
          } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, e);
          }
      }     
//==================================================================================================
    public void count4()  {         
          try {
                String sql = "select count(ReturnID) as ReturnID from returns";
                pst = conn.prepareStatement(sql);
                rs=pst.executeQuery();             
                if(rs.next()) {                    
                    String sum=rs.getString("ReturnID");
                    Count4.setText(sum);                
                }
          } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, e);
          }
      }     
//==================================================================================================
public void Bargraph() {  
    try{

            String query ="SELECT BookID, Totalbooks FROM books";
            JDBCCategoryDataset barChartData = new JDBCCategoryDataset(conn=JavaConnector.ConnectDb(), query);
            
JFreeChart barChart = ChartFactory.createBarChart("Total Stock Of Books "," Monthly", "Sales Report", barChartData, PlotOrientation.VERTICAL, true,true,true);
          CategoryPlot barchrt = barChart.getCategoryPlot();
          barchrt.setRangeGridlinePaint(Color.BLACK);
        
            ChartPanel barpanel = new ChartPanel(barChart);
            PanelChart.removeAll();
            PanelChart.add(barpanel,BorderLayout.CENTER);
            PanelChart.validate();
            PanelChart.updateUI();
            
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);      
        }
}          
//==================================================================================================    
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
               /*if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;*/
           UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AddBooks;
    private javax.swing.JPanel AddMembers;
    private javax.swing.JTextField AmountBill;
    private javax.swing.JTextField BookID;
    private javax.swing.JPanel BookLend;
    private javax.swing.JPanel BookReturn;
    private javax.swing.JButton Clear;
    private javax.swing.JButton Compute;
    private javax.swing.JLabel Count1;
    private javax.swing.JLabel Count2;
    private javax.swing.JLabel Count3;
    private javax.swing.JLabel Count4;
    private javax.swing.JPanel D1;
    private javax.swing.JPanel D2;
    private javax.swing.JPanel D3;
    private javax.swing.JPanel D4;
    private javax.swing.JPanel D5;
    private javax.swing.JPanel DashBoard;
    private javax.swing.JPanel DashCard;
    private javax.swing.JLabel Date;
    private javax.swing.JTextField Discount;
    private javax.swing.JTextField FindBook;
    private javax.swing.JTextField FindMO;
    private javax.swing.JTextField Fine;
    private javax.swing.JPanel FootBar;
    private javax.swing.JTextField LateDays;
    private javax.swing.JButton Menu;
    private javax.swing.JPanel PN1;
    private javax.swing.JPanel PN2;
    private javax.swing.JPanel PN3;
    private javax.swing.JPanel PN4;
    private javax.swing.JPanel PN5;
    private javax.swing.JPanel PanelChart;
    private javax.swing.JPanel PanelDash;
    private javax.swing.JPanel PanelHead;
    private javax.swing.JTextField RecordID;
    private javax.swing.JButton Return;
    private javax.swing.JTextField ReturnDate;
    private javax.swing.JTextField SaleAmount;
    private javax.swing.JTextField Search;
    private javax.swing.JTextField SearchLend;
    private javax.swing.JPanel SideBar;
    private javax.swing.JLabel Time;
    private javax.swing.JTextField Totalamount;
    private javax.swing.JTextField b1;
    private javax.swing.JTextField b2;
    private javax.swing.JTextField b3;
    private javax.swing.JTextField bs1;
    private javax.swing.JTextField bs2;
    private javax.swing.JTextField bs3;
    private javax.swing.JButton issues;
    private javax.swing.JTextField issuesDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel labelDashboard;
    private javax.swing.JLabel labelImage;
    private javax.swing.JLabel lblAddbooks;
    private javax.swing.JLabel lblAddmembers;
    private javax.swing.JLabel lblBookReturn;
    private javax.swing.JLabel lblBooklend;
    private javax.swing.JTextField m1;
    private javax.swing.JTextField m2;
    private javax.swing.JTextField m3;
    private javax.swing.JTextArea printingbill;
    private javax.swing.JTextField s1;
    private javax.swing.JTextField s2;
    private javax.swing.JTextField s3;
    private javax.swing.JTextField s4;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JTable table3;
    private javax.swing.JTable table4;
    public static final javax.swing.JTable tableRecords = new javax.swing.JTable();
    public static final javax.swing.JTable tablebooks = new javax.swing.JTable();
    public static final javax.swing.JTable tablemembers = new javax.swing.JTable();
    public static final javax.swing.JTable tablereturn = new javax.swing.JTable();
    // End of variables declaration//GEN-END:variables
}
