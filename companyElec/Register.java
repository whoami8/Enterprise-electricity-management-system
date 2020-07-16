package companyElec;

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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Register extends JFrame {

	static long globalID = 8891001;
	static Font login_fnt = new Font("宋体", Font.BOLD, 23);

	static JButton sureRes = new JButton("确定注册");

	// 注册页面的内容
	JLabel idLabel = new JLabel();
	JTextField idText = new JTextField();

	JLabel pswLabel = new JLabel();
	JPasswordField pswField = new JPasswordField();

	JLabel nameLabel = new JLabel("姓名：");
	static JTextArea nameText = new JTextArea();

	static JLabel telLabel = new JLabel("手机号：");
	static JTextArea telText = new JTextArea();

	JLabel conaLabel = new JLabel("所属企业id编号：");
	static JTextArea conaText = new JTextArea();

	JLabel mailLabel = new JLabel("邮箱：");
	static JTextArea mailText = new JTextArea();

	public Register() {
		FramMain.resJPan.setLayout(null);

		// id文字标签
		idLabel.setFont(login_fnt);
		idLabel.setForeground(Color.white);
		idLabel.setText("欢迎新用户--请输入相关信息");
		idLabel.setBounds(100, 70, 400, 30);
		FramMain.resJPan.add(idLabel);

		// id文字 文本框
		idText.setFont(login_fnt);
		idText.setBounds(185, 70, 200, 30);
//		FramMain.resJPan.add(idText);

		// 姓名 标签
		nameLabel.setFont(login_fnt);
		nameLabel.setForeground(Color.white);
		nameLabel.setBounds(85, 110, 150, 30);
		FramMain.resJPan.add(nameLabel);

		// 姓名 文本框
		nameText.setFont(login_fnt);
		nameText.setBounds(185, 110, 200, 30);
		FramMain.resJPan.add(nameText);

		// 密码文字标签
		pswLabel.setFont(login_fnt);
		pswLabel.setForeground(Color.white);
		pswLabel.setText("密码：");
		pswLabel.setBounds(85, 150, 150, 30);
		FramMain.resJPan.add(pswLabel);

		// 密码文字 文本框
		pswField.setFont(login_fnt);
		pswField.setBounds(185, 150, 200, 30);
		FramMain.resJPan.add(pswField);

		// 手机号 标签
		telLabel.setFont(login_fnt);
		telLabel.setForeground(Color.white);
		telLabel.setBounds(75, 190, 150, 30);
		FramMain.resJPan.add(telLabel);

		// 手机号 文本框
		telText.setFont(login_fnt);
		telText.setBounds(185, 190, 200, 30);
		FramMain.resJPan.add(telText);

		// 企业 标签
		conaLabel.setFont(login_fnt);
		conaLabel.setForeground(Color.white);
		conaLabel.setBounds(60, 230, 150, 30);
		FramMain.resJPan.add(conaLabel);

		// 企业 文本框
		conaText.setFont(login_fnt);
		conaText.setBounds(185, 230, 200, 30);
		FramMain.resJPan.add(conaText);

		// 邮箱 标签
		mailLabel.setFont(login_fnt);
		mailLabel.setForeground(Color.white);
		mailLabel.setBounds(85, 270, 150, 30);
		FramMain.resJPan.add(mailLabel);

		// 邮箱 文本框
		mailText.setFont(login_fnt);
		mailText.setBounds(185, 270, 200, 30);
		FramMain.resJPan.add(mailText);

		// 身份 单选
		ButtonGroup option;
		JRadioButton optionA;
		JRadioButton optionB;

		option = new ButtonGroup();
		optionA = new JRadioButton("企业用户");
		optionB = new JRadioButton("系统管理员");
//		option.setSelected((ButtonModel) optionA, true);
		optionA.setBounds(180, 320, 80, 25);
		optionB.setBounds(290, 320, 90, 25);

		FramMain.resJPan.add(optionA);
		FramMain.resJPan.add(optionB);
		// 把按钮加到同一个按钮组
		option.add(optionA);
		option.add(optionB);

		sureRes.setBounds(200, 360, 140, 40);
		
		sureRes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				long id;
				String name = null, psw = null, tel = null, coid = null, mail = null, type = null;

				id = globalID;
				name = nameText.getText();//
				psw = pswField.getText();//
				tel = telText.getText();//
				coid = conaText.getText();// gongsi
				mail = mailText.getText();//
				if (optionA.isSelected() == true) {
					type = "企业用户";
				} else if (optionB.isSelected() == true) {
					type = "系统管理员";
				}
				if (psw != null && tel != null && coid != null && mail != null && type != null) {
					String insertSql = "insert into userinfo values('" + type + "'," + coid + ",'" + psw + "','" + tel
							+ "','" + name + "','" + mail + "')";
					System.out.println(insertSql);
					try {
						
							String newid=newUser(insertSql);
							JOptionPane.showMessageDialog(null, "注册成功,您的id编码为 ："+newid, "提示", JOptionPane.PLAIN_MESSAGE);
							FramMain.resJPan.setVisible(false);
							FramMain.loginJPan.setVisible(true);
						
					} catch (HeadlessException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "请填写完整信息", "提示", JOptionPane.PLAIN_MESSAGE);
				}

//				FramMain.eleContainer.add(loginJPan);
//				loginJPan.setVisible(true);
//				loginJPan.repaint();
			}
		});
		FramMain.resJPan.add(sureRes);

		FramMain.resJPan.setVisible(true);
		FramMain.eleContainer.add(FramMain.resJPan);
	}

	public String newUser(String insertSql) throws SQLException {

		Connection connection = null;
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=zheng";// 数据源 // ！！！注意若出现加载或者连接数据库失败一般是这里出现问题
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

		int count = stmt.executeUpdate(insertSql);
		System.out.println("成功添加" + count + "行");
		
		ResultSet res=null;
		String id="";
		try {
			res = (ResultSet) stmt.executeQuery("select user_id from userinfo;");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (res != null) {
			try {
				while (res.next()) {
					id = res.getString(1);
					System.out.println("新的用户id编号为："+id);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			System.out.println("新的用户id编号为："+id);
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

		return id;
	}
}
