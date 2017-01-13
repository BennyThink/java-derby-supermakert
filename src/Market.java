
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author moon
 */
public class Market extends javax.swing.JFrame {

    /**
     * Creates new form Market
     */
    public Market() {
        initComponents();
        //@moon:居中显示
        setLocationRelativeTo(null);
        //@moon:icon与驱动
        setIconImage(Toolkit.getDefaultToolkit().getImage("huaji.png"));
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动程序失败!");
        }
        jPanel1.remove(jLabelWarning);
        //在这里初始化jTable
        //读取之前，我先把jTable都初始化了免得出错
        for (int i = 0; i <= 99; i++) {
            for (int j = 0; j < 7; j++) {
                ar.setValueAt("", i, j);
            }
        }
        //初始化jtable
        for (int i = 0; i <= 99; i++) {
            for (int j = 0; j < 7; j++) {
                jTable1.setValueAt("", i, j);
            }
        }
        //从某个表中读取数据，并存放到类中。
        class dbProduct {
            String pID = null;
            String pName = null;
            String pProducer = null;
            String pOrigin = null;
            String pDate = null;
            String pPrice1 = null;
            String pPrice2 = null;
        };
        dbProduct[] oldDbInfo = new dbProduct[100];
        //it worked!

        for (int i = 0; i <= 99; i++) {
            oldDbInfo[i] = new dbProduct();
        }
        oldDbInfo[0].pID = "类的对象数组创建成功";
      
        //读取 
        try {
            String url = "jdbc:derby:market;create=true";   
            Connection con = DriverManager.getConnection(url);
            String s = "select * from product ";
            Statement sql = con.createStatement();
            ResultSet rs = sql.executeQuery(s);
            int i = 0;
            //商品有很多，所以要循环
            while (rs.next()) {
                String pID = rs.getString(1);
                String pName = rs.getString(2);
                String pProducer = rs.getString(3);
                String pOrigin = rs.getString(4);
                String pDate = rs.getString(5);
                String pPrice1 = rs.getString(6);
                String pPrice2 = rs.getString(7);
                ar.setValueAt(pID, i, 0);
                ar.setValueAt(pName, i, 1);
                ar.setValueAt(pProducer, i, 2);
                ar.setValueAt(pOrigin, i, 3);
                ar.setValueAt(pDate, i, 4);
                ar.setValueAt(pPrice1, i, 5);
                ar.setValueAt(pPrice2, i, 6);
                i++;
            }
            con.close();
        } catch (SQLException g) {
            System.out.println("E Code" + g.getErrorCode());
            System.out.println("E M" + g.getMessage());
            System.out.println("全部库存显示失败");
        }

        //现在全部商品都在jTable中
        //那么就复制到所谓的“结构数组”中！
        // ar.getValueAt();
        //ar.getRowCount();JTable行数
        // ar.getColumnCount();//列数
        //oldDbInfo[0].pID=String.valueOf(ar.getRowCount());
        oldDbInfo[0].pID = ar.getValueAt(0, 0).toString();
        for (int row = 0; row <= 99; row++) {
            oldDbInfo[row].pID = ar.getValueAt(row, 0).toString();
            oldDbInfo[row].pName = ar.getValueAt(row, 1).toString();
            oldDbInfo[row].pProducer = ar.getValueAt(row, 2).toString();
            oldDbInfo[row].pOrigin = ar.getValueAt(row, 3).toString();
            oldDbInfo[row].pDate = ar.getValueAt(row, 4).toString();
            oldDbInfo[row].pPrice1 = ar.getValueAt(row, 5).toString();
            oldDbInfo[row].pPrice2 = ar.getValueAt(row, 6).toString();
        }

        //logintype
        //@moon:管理员权限判断
        if (Var.loginType.equals("0")) {
            System.out.println("管理员");
            jPanel1.remove(img1);
        } else {
            //jButton1.setEnabled(false);
            //jButton1.removeActionListener(null);
            jPanel1.removeAll();
            jPanel1.add(jLabelWarning);
            jPanel1.add(img1);
            jLabelWarning.setText("<html>你无权访问<br>这个页面！"
                    + "<br>我滑稽的<br>看着你(*@ο@*) <br>不信你点它看看</html>");

            jPanel2.remove(jTextField1);
            jPanel2.remove(jButton3);
            jPanel2.remove(jButton4);
            jPanel2.remove(jButton5);
            jPanel6.remove(jButton6);
            jPanel6.remove(jLabel2);
            jPanel6.remove(jLabel3);
            jPanel6.remove(jLabel4);
            jPanel6.remove(jTextReg);
            jPanel6.remove(jPasswdReg);
            jPanel6.remove(jCheckBox1);
            jPanel6.remove(jButton8);
        }

        //logintype
        //显示管理员
        int i = 0;
        try {
            String url = "jdbc:derby:market;create=true";    //这里
            Connection con = DriverManager.getConnection(url);
            String s = "select * from admin ";
            Statement sql = con.createStatement();
            ResultSet rs = sql.executeQuery(s);
            //商品有很多，所以要循环
            while (rs.next()) {
                String aID = rs.getString(1);
                if (aID.equals("0")) {
                    aID = "管理员";
                } else {
                    aID = "普通用户";
                }
                String aUser = rs.getString(2);
                jTable2.setValueAt(aID, i, 0);
                jTable2.setValueAt(aUser, i, 1);
                i++;
            }
            con.close();
        } catch (SQLException g) {
            System.out.println("E Code" + g.getErrorCode());
            System.out.println("E M" + g.getMessage());
            System.out.println("全部用户显示失败");
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        img1 = new javax.swing.JLabel();
        jLabelWarning = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ar = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextReg = new javax.swing.JTextField();
        jPasswdReg = new javax.swing.JPasswordField();
        jButton6 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabelHelp = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("超市管理系统 - 滑稽版");
        setResizable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "商品编号", "商品名", "生产商", "产地", "生产日期", "进价", "售价"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("插入记录");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        img1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/5.gif"))); // NOI18N
        img1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                img1MouseClicked(evt);
            }
        });

        jLabelWarning.setFont(new java.awt.Font("幼圆", 0, 14)); // NOI18N
        jLabelWarning.setForeground(new java.awt.Color(255, 0, 51));
        jLabelWarning.setText(" ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(img1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(img1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("入库管理", jPanel1);

        ar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "商品编号", "商品称", "生产商", "产地", "生产日期", "进价", "售价"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(ar);

        jButton2.setText("显示库存");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jTextField1.setText("请输入要查询/删除的编号");

        jButton3.setText("修改信息");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton4.setText("查询商品");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jButton5.setText("删除商品");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        jLabel1.setText("超市目前共有商品多少件呢？");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jButton5)
                        .addGap(53, 53, 53)
                        .addComponent(jButton3)))
                .addContainerGap(151, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton3))
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("库存管理", jPanel2);

        jLabel2.setText("新增用户");

        jLabel3.setText("用户名");

        jLabel4.setText("密码");

        jButton6.setText("新增");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });

        jCheckBox1.setText("是否为管理员？");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "用户角色", "用户名"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        jButton7.setText("显示用户");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });

        jButton8.setText("删除用户");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextReg, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                            .addComponent(jPasswdReg))
                        .addGap(125, 125, 125)
                        .addComponent(jButton6))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox1))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jButton7)
                        .addGap(111, 111, 111)
                        .addComponent(jButton8)))
                .addContainerGap(122, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jTextReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jButton6)))
                .addGap(24, 24, 24)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jPasswdReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("人员管理", jPanel6);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "程序说明", "开发者", "其实我才是开发者" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabelHelp.setFont(new java.awt.Font("华康少女文字W5", 0, 24)); // NOI18N
        jLabelHelp.setForeground(new java.awt.Color(0, 153, 0));
        jLabelHelp.setText("什么？你想干嘛？！");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/3.gif"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelHelp, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        jTabbedPane1.addTab("系统说明", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
       //@moon:这里是最后搞笑的啦~~
        Image img = null;

        if (jComboBox1.getSelectedItem() == "程序说明") {
            jLabelHelp.setText("什么程序不程序的");
            img = Toolkit.getDefaultToolkit().createImage("4.gif");
            jLabel5.setIcon(new ImageIcon(img));
            jLabel5.setText("");
        }

        if (jComboBox1.getSelectedItem() == "开发者") {
            jLabelHelp.setText("我是开发者");
            img = Toolkit.getDefaultToolkit().createImage("yinxian.png");
            jLabel5.setIcon(new ImageIcon(img));
            jLabel5.setText("");
        }
        
        if (jComboBox1.getSelectedItem() == "其实我才是开发者") {
            jLabelHelp.setText("楼上不是开发者，Bunny - 小兔子 才是！");
            img = Toolkit.getDefaultToolkit().createImage("guai.png");
            jLabel5.setIcon(new ImageIcon(img));
            jLabel5.setText("");
        }

    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        //@moon:删除用户
        //先显示出来
        int i = 0;
        try {
            String url = "jdbc:derby:market;create=true";    //这里
            Connection con = DriverManager.getConnection(url);
            String s = "select * from admin ";
            Statement sql = con.createStatement();
            ResultSet rs = sql.executeQuery(s);
            //商品有很多，所以要循环
            while (rs.next()) {
                String aID = rs.getString(1);
                if (aID.equals("0")) {
                    aID = "管理员";
                } else {
                    aID = "普通用户";
                }
                String aUser = rs.getString(2);
                jTable2.setValueAt(aID, i, 0);
                jTable2.setValueAt(aUser, i, 1);
                i++;
            }
            con.close();
        } catch (SQLException g) {
            System.out.println("E Code" + g.getErrorCode());
            System.out.println("E M" + g.getMessage());
            System.out.println("全部用户显示失败");
        }
        //获取当前游标什么的,选中第二列，getSelectedRow()会为1
        //删除
        //取得对应的0或者1
        // jTable2.getValueAt(0,jTable2.getSelectedRow());
        //这个代码能得到是普通用户还是管理员
        //System.out.println("ID "+jTable2.getValueAt(jTable2.getSelectedRow(),0));
        //这个得到唯一的用户名
        //System.out.println(jTable2.getValueAt(jTable2.getSelectedRow(),1));

        if (jTable2.getValueAt(jTable2.getSelectedRow(), 0) == "管理员") {
            JOptionPane.showMessageDialog(null, "都是管理员，为何要相爱相杀", "爱即是恨", JOptionPane.ERROR_MESSAGE);
        } else {
            //////////////////////////////////////////
            try {
                String url = "jdbc:derby:market;create=true";    //这里
                Connection con = DriverManager.getConnection(url);
                Statement sql = con.createStatement();
                String SelUser = String.valueOf(jTable2.getValueAt(jTable2.getSelectedRow(), 1)).trim();
                String s = "delete  from admin  where aUser ='" + SelUser + "'";
                sql = con.createStatement();
                int del = sql.executeUpdate(s);
                if (del == 1) {
                    JOptionPane.showMessageDialog(null, "删除成功！",
                            "信息", JOptionPane.YES_NO_OPTION);
                } else {
                    JOptionPane.showMessageDialog(null, "删除失败！",
                            "信息", JOptionPane.YES_NO_OPTION);
                }
                con.close();
            } catch (SQLException g) {
                System.out.println("E Code" + g.getErrorCode());
                System.out.println("E M" + g.getMessage());
                System.out.println("删除失败！请查看上述报错信息");
            }

            //////////////////////////
        }

    }//GEN-LAST:event_jButton8MouseClicked

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        // 显示用户
        int i = 0;
        try {
            String url = "jdbc:derby:market;create=true";    //这里
            Connection con = DriverManager.getConnection(url);
            String s = "select * from admin ";
            Statement sql = con.createStatement();
            ResultSet rs = sql.executeQuery(s);
            //商品有很多，所以要循环
            while (rs.next()) {
                String aID = rs.getString(1);
                if (aID.equals("0")) {
                    aID = "管理员";
                } else {
                    aID = "普通用户";
                }
                String aUser = rs.getString(2);
                jTable2.setValueAt(aID, i, 0);
                jTable2.setValueAt(aUser, i, 1);
                i++;
            }
            con.close();
        } catch (SQLException g) {
            System.out.println("E Code" + g.getErrorCode());
            System.out.println("E M" + g.getMessage());
            System.out.println("全部用户显示失败");
        }

    }//GEN-LAST:event_jButton7MouseClicked

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        // 新增管理员
        try {
            String url = "jdbc:derby:market;create=true";   //重要
            Connection con = DriverManager.getConnection(url);
            Statement sql = con.createStatement();
            //@moon:sha1
            getSHA1 sha = new getSHA1();
            String regUname = jTextReg.getText().trim();
            String regPasswd = sha.getSHA(jPasswdReg.getText().trim());
            String reg = null;
            if (jCheckBox1.isSelected()) {
                reg = "insert into admin values('0','" + regUname + "','" + regPasswd + "')";
            } else {
                reg = "insert into admin values('1','" + regUname + "','" + regPasswd + "')";
            }

            int regRes = sql.executeUpdate(reg);
            if (regRes == 1) {
                JOptionPane.showMessageDialog(rootPane, "注册成功", "提示", WIDTH);
                con.close();
            } else {
                JOptionPane.showMessageDialog(null, "注册失败", "提示！",
                        JOptionPane.YES_NO_OPTION);
            }
            jTextReg.setText("");
            jPasswdReg.setText("");

        } catch (SQLException g) {
            System.out.println("E Code" + g.getErrorCode());
            System.out.println("E M" + g.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(Market.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // 删除商品
        //需要做的事情是，先查询出来显示在Table中，然后问用户是否要删除
        //再根据返回值决定操作
        try {
            String url = "jdbc:derby:market;create=true";    //这里
            Connection con = DriverManager.getConnection(url);
            String inputID = jTextField1.getText().trim();
            String s = "select * from product  where pID ='" + inputID + "'";
            Statement sql = con.createStatement();
            ResultSet rs = sql.executeQuery(s);
            //ID是唯一的主键，所以查询到一个就停了
            if (rs.next()) {
                String pID = rs.getString(1);
                String pName = rs.getString(2);
                String pProducer = rs.getString(3);
                String pOrigin = rs.getString(4);
                String pDate = rs.getString(5);
                String pPrice1 = rs.getString(6);
                String pPrice2 = rs.getString(7);
                ar.setValueAt(pID, 0, 0);
                ar.setValueAt(pName, 0, 1);
                ar.setValueAt(pProducer, 0, 2);
                ar.setValueAt(pOrigin, 0, 3);
                ar.setValueAt(pDate, 0, 4);
                ar.setValueAt(pPrice1, 0, 5);
                ar.setValueAt(pPrice2, 0, 6);
                //删除代码应该写在这里，照样try catch
                //如果成功的查询到了信息，那么先询问
                int n = JOptionPane.showConfirmDialog
        (this, "你要删除这条记录吗", "删除提示", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) //用户同意删除，在这里写入删除的代码！
                {
                    try {
                        inputID = jTextField1.getText();
                        s = "delete  from product  where pID ='" + inputID + "'";
                        sql = con.createStatement();
                        int del = sql.executeUpdate(s);
                        if (del == 1) {
                            JOptionPane.showMessageDialog(null, "删除成功！",
                                    "信息", JOptionPane.YES_NO_OPTION);
                        } else {
                            JOptionPane.showMessageDialog(null, "删除失败！",
                                    "信息", JOptionPane.YES_NO_OPTION);
                        }
                        con.close();
                    } catch (SQLException g) {
                        System.out.println("E Code" + g.getErrorCode());
                        System.out.println("E M" + g.getMessage());
                        System.out.println("删除失败！请查看上述报错信息");
                    }
                } else {
                    System.out.println("删除已取消");
                }

                //删除代码
            } else {
                JOptionPane.showMessageDialog(null, "您输入的编号不存在，请重新输入",
                        "输入错误", JOptionPane.YES_NO_OPTION);
            }
            con.close();
        } catch (SQLException g) {
            System.out.println("E Code" + g.getErrorCode());
            System.out.println("E M" + g.getMessage());
            System.out.println("查询失败！请查看上述报错信息");
        }

        //弹出提示框询问用户是否要删除
        //测试下删除代码吧！
        jTextField1.setText("请输入要查询/删除的编号");

    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // 查询指定商品
        //全部清空
        for (int i = 0; i <= 99; i++) {
            for (int j = 0; j < 7; j++) {
                ar.setValueAt("", i, j);
            }
        }
        //清完了
        try {
            String url = "jdbc:derby:market;create=true";    //这里
            Connection con = DriverManager.getConnection(url);
            String inputID = jTextField1.getText().trim();
            String s = "select * from product  where pID ='" + inputID + "'";
            Statement sql = con.createStatement();
            ResultSet rs = sql.executeQuery(s);
            //ID是唯一的主键，所以查询到一个就停了
            if (rs.next()) {
                String pID = rs.getString(1);
                String pName = rs.getString(2);
                String pProducer = rs.getString(3);
                String pOrigin = rs.getString(4);
                String pDate = rs.getString(5);
                String pPrice1 = rs.getString(6);
                String pPrice2 = rs.getString(7);
                ar.setValueAt(pID, 0, 0);
                ar.setValueAt(pName, 0, 1);
                ar.setValueAt(pProducer, 0, 2);
                ar.setValueAt(pOrigin, 0, 3);
                ar.setValueAt(pDate, 0, 4);
                ar.setValueAt(pPrice1, 0, 5);
                ar.setValueAt(pPrice2, 0, 6);
            } else {
                JOptionPane.showMessageDialog(null, "您输入的编号不存在，请重新输入",
                        "输入错误", JOptionPane.YES_NO_OPTION);
            }
            con.close();
        } catch (SQLException g) {
            System.out.println("E Code" + g.getErrorCode());
            System.out.println("E M" + g.getMessage());
            System.out.println("查询失败！请查看上述报错信息");
        }

        jTextField1.setText("请输入要查询/删除的编号");

    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        //@moon:修改信息，这也是最困难的部分了
        //修改之前已经jTable已经有了内容了。所以就直接以ID为准每个都update
        //在这之前检测行尾
        int updateResult = 0;
        String updateMainStr = "update product set pName='";
        for (int i = 0; i <= 99; i++) {
            if (ar.getValueAt(i, 0).toString() != "") {
                //横着读取，所以是00 01 02 03 04 05 06
                updateMainStr = "update product set pName='";
                updateMainStr
                        = updateMainStr + ar.getValueAt(i, 1).toString().trim()
                        + "',pProducer='" + ar.getValueAt(i, 2).toString().trim()
                        + "',pOrigin='" + ar.getValueAt(i, 3).toString().trim()
                        + "',pDate='" + ar.getValueAt(i, 4).toString().trim()
                        + "',pPrice1='" + ar.getValueAt(i, 5).toString().trim()
                        + "',pPrice2='" + ar.getValueAt(i, 6).toString().trim()
                        + "' where pID='" + ar.getValueAt(i, 0).toString().trim() + "'";
                // System.out.println(updateMainStr);

                try {
                    String url = "jdbc:derby:market;create=true";
                    Connection con = DriverManager.getConnection(url);
                    Statement sql;
                    sql = con.createStatement();
                    System.out.println(updateMainStr);
                    updateResult = sql.executeUpdate(updateMainStr);

                    con.close();
                } catch (SQLException g) {
                    System.out.println("E Code" + g.getErrorCode());
                    System.out.println("E M" + g.getMessage());
                    System.out.println("更新失败啊");
                }
                /////////////////////当成一般的代码//////////////////////

            } else {
                System.out.println("ID为空，似乎是空行了哦");
                break;
            }
        }
        if (updateResult == 1) {
            JOptionPane.showMessageDialog(null, "修改成功！",
                    "信息", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "修改失败", "信息", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // 显示全部的库存代码
        int i = 0;
        String count = "xx";
        try {
            String url = "jdbc:derby:market;create=true";    //这里
            Connection con = DriverManager.getConnection(url);
            String s = "select * from product ";
            Statement sql = con.createStatement();
            ResultSet rs = sql.executeQuery(s);
            //商品有很多，所以要循环
            while (rs.next()) {
                String pID = rs.getString(1);
                String pName = rs.getString(2);
                String pProducer = rs.getString(3);
                String pOrigin = rs.getString(4);
                String pDate = rs.getString(5);
                String pPrice1 = rs.getString(6);
                String pPrice2 = rs.getString(7);
                ar.setValueAt(pID, i, 0);
                ar.setValueAt(pName, i, 1);
                ar.setValueAt(pProducer, i, 2);
                ar.setValueAt(pOrigin, i, 3);
                ar.setValueAt(pDate, i, 4);
                ar.setValueAt(pPrice1, i, 5);
                ar.setValueAt(pPrice2, i, 6);
                i++;
            }
            count = "" + i + "";
            jLabel1.setText("超市现有商品" + count + "个");
            con.close();
        } catch (SQLException g) {
            System.out.println("E Code" + g.getErrorCode());
            System.out.println("E M" + g.getMessage());
            System.out.println("全部库存显示失败");
        }

    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // 入库管理，插入记录。
        //这次要做的是一次性添加多行的内容
        //首先就要确定用户输入了几行，以每一行的ID为准，循环获取。
        //jTable1.getValueAt(0, 0).toString();
        //inputLines=1是用户输入了一行。
        //jTable1.getValueAt(99, 0).toString();
        int inputLines = 0;
        Connection con;
        PreparedStatement sql;
        String[] a = new String[7];
        int multiResult = 0;
        for (int i = 0; i <= 99; i++) {
            if (jTable1.getValueAt(i, 0).toString() != "") {
                inputLines++;
            }
        }
        System.out.println(inputLines + "多少行啊");
        //inputLines就是5行，数值为5

        for (int i = 0; i < inputLines; i++) {
            a[0] = jTable1.getValueAt(i, 0).toString().trim();
            a[1] = jTable1.getValueAt(i, 1).toString().trim();
            a[2] = jTable1.getValueAt(i, 2).toString().trim();
            a[3] = jTable1.getValueAt(i, 3).toString().trim();
            a[4] = jTable1.getValueAt(i, 4).toString().trim();
            a[5] = jTable1.getValueAt(i, 5).toString().trim();
            a[6] = jTable1.getValueAt(i, 6).toString().trim();
            //建立连接并插入

            try {
                String uri = "jdbc:derby:Market;create=true";
                con = DriverManager.getConnection(uri);
                String SQL
                        = "INSERT INTO product VALUES(?,?,?,?,?,?,?)";
                sql = con.prepareStatement(SQL);
                sql.setString(1, a[0].trim());
                sql.setString(2, a[1].trim());
                sql.setString(3, a[2].trim());
                sql.setString(4, a[3].trim());
                sql.setString(5, a[4].trim());
                sql.setString(6, a[5].trim());
                sql.setString(7, a[6].trim());
                multiResult = sql.executeUpdate();
                con.close();
            } catch (SQLException exp) {
                JOptionPane.showMessageDialog(null, "" + exp, "消息对话框", JOptionPane.WARNING_MESSAGE);
            }

            //////////////我就是注释狂魔/////////////////////////
        }
        //获取Table内容
        if (multiResult == 1) {
            JOptionPane.showMessageDialog(null, "插入记录成功", "消息对话框", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jButton1MouseClicked

    private void img1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_img1MouseClicked
        // 滑稽
        JOptionPane.showMessageDialog(rootPane, "谁让你点了？", "滑稽在此", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_img1MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        //jPanel7.remove(jLabel5);
        Image img = Toolkit.getDefaultToolkit().createImage("2.gif");
        jLabel5.setIcon(new ImageIcon(img));
        jLabel5.setText("魔法变变变！");
    }//GEN-LAST:event_jLabel5MouseClicked

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
            java.util.logging.Logger.getLogger(Market.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Market.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Market.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Market.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Market().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ar;
    private javax.swing.JLabel img1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelHelp;
    private javax.swing.JLabel jLabelWarning;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPasswordField jPasswdReg;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextReg;
    // End of variables declaration//GEN-END:variables

    private void dbProduct() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
