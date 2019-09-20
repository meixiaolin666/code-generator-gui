package personal.mxl;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MainGui {
    DatabaseMetaData dbmd;
    Set<String> selectField = new HashSet<>();
    Set<String> selectType = new HashSet<>();
    String codeType;
    public MainGui() {
        JFrame jFrame = new JFrame("代码生成器");
        jFrame.setSize(600, 600);
        jFrame.setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) screenSize.getWidth() / 2 - 600 / 2;
        int y = (int) screenSize.getHeight() / 2 - 600 / 2;
        jFrame.setLocation(x, y);
        jFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                JdbcUtils.close();
                super.windowClosing(e);
                System.exit(0);
            }
        });
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jFrame.add(jPanel);
        InitGlobalFont();
        Color greenColor = new Color(51, 102, 68);
        final JLabel tipsLabel = new JLabel("请先链接数据库:");
        tipsLabel.setBounds(240, 300, 150, 25);
        jPanel.add(tipsLabel);
        JLabel addressLabel = new JLabel("地  址:");
        addressLabel.setBounds(10, 20, 80, 25);
        jPanel.add(addressLabel);
        final JTextField addressText = new JTextField(20);
        addressText.setBounds(80, 20, 495, 25);
        jPanel.add(addressText);
        JLabel userNameLabel = new JLabel("用户名:");
        userNameLabel.setBounds(10, 50, 80, 25);
        jPanel.add(userNameLabel);
        final JTextField userNameText = new JTextField(20);
        userNameText.setBounds(80, 50, 160, 25);
        jPanel.add(userNameText);
        JLabel passwordLabel = new JLabel("密  码:");
        passwordLabel.setBounds(250, 50, 80, 25);
        jPanel.add(passwordLabel);
        final JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(310, 50, 160, 25);
        jPanel.add(passwordText);
        JLabel schemaLabel = new JLabel("数据库:");
        schemaLabel.setBounds(10, 80, 100, 25);
        JLabel codeTypeText = new JLabel("选择类型:");
        codeTypeText.setBounds(170, 510, 100, 25);
        String [] selected ={"宠宠","大桥"};
        JComboBox codeTypeBox=new JComboBox(selected);
        codeTypeBox.setBounds(250, 510, 100, 25);
        jPanel.add(schemaLabel);
        jPanel.add(codeTypeBox);
        jPanel.add(codeTypeText);
        final JLabel schemaText = new JLabel();
        schemaText.setBounds(80, 80, 160, 25);
        jPanel.add(schemaText);
        JLabel outputLabel = new JLabel("保  存:");
        outputLabel.setBounds(250, 80, 100, 25);
        jPanel.add(outputLabel);
        final JTextField outputText = new JTextField();
        outputText.setBounds(310, 80, 160, 25);
        outputText.setText(FileSystemView.getFileSystemView().getHomeDirectory().getAbsoluteFile().getAbsolutePath());
        jPanel.add(outputText);
        final JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory().getAbsoluteFile());//
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        JButton outputButton = new JButton("选  择");
        outputButton.setBounds(490, 80, 85, 25);
        outputButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jfc.showDialog(null, null);
                outputText.setText(jfc.getSelectedFile().getAbsolutePath());
            }
        });
        jPanel.add(outputButton);
        MyTableModel myTableModel = new MyTableModel();
        final JTable table = new JTable(myTableModel);
        table.getTableHeader().setFont(new Font("仿宋", Font.BOLD, 16));  // 设置表头名称字体样式
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(25);
        table.setBounds(10, 110, 565, 350);
        table.setPreferredScrollableViewportSize(new Dimension(560, 345));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 110, 565, 390);
        jPanel.add(scrollPane);
        JButton selectButton = new JButton("全选");
        selectButton.setBounds(10, 510, 70, 25);
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table.selectAll();
            }
        });
        jPanel.add(selectButton);
        JButton unSelectButton = new JButton("取消");
        unSelectButton.setBounds(85, 510, 70, 25);
        unSelectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table.clearSelection();
                selectField.clear();
            }
        });
        jPanel.add(unSelectButton);
        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JCheckBox checkBox = (JCheckBox) e.getSource();
                if (checkBox.isSelected()) {
                    selectType.add(checkBox.getText());
                } else {
                    selectType.remove(checkBox.getText());
                }
            }
        };
        JCheckBox checkBox1 = new JCheckBox("Mapper");
        checkBox1.setBounds(200, 510, 90, 25);
        checkBox1.addChangeListener(changeListener);
        checkBox1.setSelected(true);
//        jPanel.add(checkBox1);
        JCheckBox checkBox2 = new JCheckBox("Xml");
        checkBox2.setBounds(300, 510, 80, 25);
        checkBox2.addChangeListener(changeListener);
        checkBox2.setSelected(true);
//        jPanel.add(checkBox2);
        JCheckBox checkBox3 = new JCheckBox("Entity");
        checkBox3.setBounds(380, 510, 90, 25);
        checkBox3.addChangeListener(changeListener);
        checkBox3.setSelected(true);
//        jPanel.add(checkBox3);
        JButton runButton = new JButton("生成代码");
        runButton.setBounds(475, 510, 100, 25);
        runButton.setBackground(greenColor);
        runButton.setForeground(Color.WHITE);
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                selectField.clear();
                int[] rows = table.getSelectedRows();
                for (int row : rows) {
                    selectField.add(table.getValueAt(row, 0).toString());
                }
                if (selectField.size() == 0 || selectType.size() == 0) {
                    JOptionPane.showMessageDialog(null, "无可生成文件！");
                } else {
                    try {
                        codeType=codeTypeBox.getSelectedItem().toString();
                        CommonUtils.createFiles(selectField, selectType, dbmd, outputText.getText(),codeType);
                    } catch (Exception ex) {
                        CommonUtils.saveErrorLog(outputText.getText(), ex.getMessage());
                        JOptionPane.showMessageDialog(null, "少年，出错了！");
                    }
                }
            }
        });
        jPanel.add(runButton);
        JButton loginButton = new JButton("连  接");
        loginButton.setBackground(greenColor);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBounds(490, 50, 84, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if ("".equals(addressText.getText()) || "".equals(userNameText.getText()) || "".equals(passwordText.getText())) {
                        JOptionPane.showMessageDialog(null, "请正确填写数据库链接信息！");
                    } else {
                        tipsLabel.setVisible(false);
                        JdbcUtils.setURL(addressText.getText());
                        JdbcUtils.setUSER(userNameText.getText());
                        JdbcUtils.setPASS(passwordText.getText());
                        dbmd = JdbcUtils.getDataBaseInfo();
                        ResultSet tablesRs = dbmd.getTables(null, null, null, new String[]{"TABLE", "VIEW"});
                        tablesRs.last();
                        int rowCount = tablesRs.getRow();
                        tablesRs.beforeFirst();
                        if (rowCount > 0) {
                            Object[][] rowData = new Object[rowCount][];
                            int i = 0;
                            while (tablesRs.next()) {
                                if (i == 0) {
                                    String tableCat = tablesRs.getString("TABLE_CAT");
                                    schemaText.setText(tableCat);
                                }
                                String tableName = tablesRs.getString("TABLE_NAME");
                                String remarks = tablesRs.getString("REMARKS");
                                rowData[i] = new Object[]{tableName, CommonUtils.underline2Camel(tableName, false), remarks};
                                i++;
                            }
                            MyTableModel myTableModel = new MyTableModel();
                            myTableModel.setRowData(rowData);
                            table.setModel(myTableModel);
                        }
                    }
                } catch (Exception ex) {
                    CommonUtils.saveErrorLog(outputText.getText(), ex.getMessage());
                    tipsLabel.setVisible(true);
                    MyTableModel myTableModel = new MyTableModel();
                    table.setModel(myTableModel);
                    JOptionPane.showMessageDialog(null, "链接失败！" + ex.getMessage());
                }
            }
        });
        jPanel.add(loginButton);
        JMenu jm = new JMenu("配置");
        JMenuItem t1 = new JMenuItem("读取配置");
        t1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<String, String> map = CommonUtils.readConfig(outputText.getText());
                addressText.setText(map.get("url"));
                userNameText.setText(map.get("user"));
                passwordText.setText(map.get("pass"));
            }
        });
        JMenuItem t2 = new JMenuItem("保存配置");
        t2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommonUtils.saveConfig(outputText.getText(), new String[]{"url=" + addressText.getText(), "user=" + userNameText.getText(), "pass=" + passwordText.getText()});
            }
        });
        jm.add(t1);
        jm.add(t2);
        JMenu jm1 = new JMenu("帮助");
        JMenuItem t4 = new JMenuItem("帮助");
        t4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "这小东西还用写帮助吗？？？ 手动黑人问号？");
            }
        });
        JMenuItem t5 = new JMenuItem("关于");
        t5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "欢迎群友来交流！\r\n \r\n 提出宝贵意见！", "关于", JOptionPane.ERROR_MESSAGE, CommonUtils.getIcon());
            }
        });
        jm1.add(t4);
        jm1.add(t5);
        JMenuBar br = new JMenuBar();
        br.add(jm);
        br.add(jm1);
        jFrame.setJMenuBar(br);
        jFrame.add(jPanel);
        jFrame.setVisible(true);
        Map<String, String> map = CommonUtils.readConfig(outputText.getText());
        addressText.setText(map.get("url"));
        userNameText.setText(map.get("user"));
        passwordText.setText(map.get("pass"));
    }

    private void InitGlobalFont() {
        Font globalFont = new Font("宋体", Font.PLAIN, 16);
        FontUIResource fontRes = new FontUIResource(globalFont);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys();
             keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }


}
