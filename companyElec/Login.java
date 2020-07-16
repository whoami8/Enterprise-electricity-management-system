package companyElec;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import status.Admin;
import status.Company;
import status.Person;

public class Login extends JFrame {

	static JButton login = new JButton("登录");
	static JButton register = new JButton("注册");

	static Font login_fnt = new Font("宋体", Font.BOLD, 23);

	// ***************************
	// 账号 密码正确 登录 ；错误 只能注册
	public Login() {

		FramMain.loginJPan.setLayout(null);
		JLabel idLabel = new JLabel();
		JTextField idField = new JTextField();

		JLabel pswLabel = new JLabel();
		JPasswordField pswField = new JPasswordField();

		// id文字标签
		idLabel.setFont(login_fnt);
		idLabel.setForeground(Color.white);
		idLabel.setText("用户ID：");
		idLabel.setBounds(80, 70, 150, 30);
		FramMain.loginJPan.add(idLabel);

		// id文字 文本框
		idField.setFont(login_fnt);
		idField.setBounds(185, 70, 200, 30);
		FramMain.loginJPan.add(idField);

		// 密码文字标签
		pswLabel.setFont(login_fnt);
		pswLabel.setForeground(Color.white);
		pswLabel.setText("密码：");
		pswLabel.setBounds(85, 120, 150, 30);
		FramMain.loginJPan.add(pswLabel);

		// 密码文字 文本框
		pswField.setFont(login_fnt);
		pswField.setBounds(185, 125, 200, 30);
		FramMain.loginJPan.add(pswField);

		login.setBounds(185, 205, 70, 23);
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FramMain.loginJPan.setVisible(false);
				//long id = 0;
				// 获取登录页面填入的id和密码
				String id = idField.getText();
				System.out.print("id=" + id);
				String psw = pswField.getText();
				System.out.print("psw=" + psw);
				if (id.equals("") || psw.equals("")) {
					JOptionPane.showMessageDialog(null, "用户ID或密码不能为空", "提示", JOptionPane.PLAIN_MESSAGE);
					System.out.print("kong=");
					FramMain.loginJPan.setVisible(true);
				} else {
					// id = Long.parseLong(idstr);// 获取id

					Connection connection = null;
					String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
					String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=zheng";// 数据源
																						// ！！！注意若出现加载或者连接数据库失败一般是这里出现问题
					String Name = "sa";
					String Pwd = "86657148";
					try {
						Class.forName(driverName);// 加载数据库的驱动类
						connection = DriverManager.getConnection(dbURL, Name, Pwd);// 获取连接
						System.out.println("数据库连接成功");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("连接失败");
					}

					Statement stmt = null;// 使用Statement接口运行sql语句
					try {
						stmt = connection.createStatement();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ResultSet res = null;

					// 查询
					try {
						res = (ResultSet) stmt.executeQuery("select * from userinfo ");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					try {
						boolean flag=true;
						while (res.next()) {
//							System.out.println(res.getString(1) instanceof String);
//							System.out.print(res.getString(2));
//							System.out.print(res.getString(2).equals(id) );
//							System.out.print(res.getString(4));
//							System.out.print(res.getString(4).equals(psw));
							if (res.getString(2).equals(id) && res.getString(4).equals(psw)) {
								flag=false;
								if(res.getString(1).equals("企业用户"))
								{
									new Person(res.getString(2));
								}
								else if(res.getString(1).equals("系统管理员"))
								{
									
									Admin admin=new Admin();
									admin.showAllCom();
								}
								FramMain.elecJFrame.setVisible(false);
								FramMain.roleJFrame.setVisible(true);//实现跳转
								dispose();//关闭窗体，释放资源
							} 
						}
						if(flag) {
							JOptionPane.showMessageDialog(null, "用户ID或密码信息不正确", "提示", JOptionPane.PLAIN_MESSAGE);//提示弹框
							FramMain.loginJPan.setVisible(true);
						}
					} catch (HeadlessException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// 关闭数据库
					try {
						res.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		register.setBounds(285, 205, 70, 23);
		register.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				FramMain.loginJPan.setVisible(false);
				new Register();
				// loginJPan.setVisible(true);
			}
		});
		
		FramMain.loginJPan.add(login);
		FramMain.loginJPan.add(register);
		FramMain.eleContainer.add(FramMain.loginJPan);
	}
}
