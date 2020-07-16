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
	static Font login_fnt = new Font("����", Font.BOLD, 23);

	static JButton sureRes = new JButton("ȷ��ע��");

	// ע��ҳ�������
	JLabel idLabel = new JLabel();
	JTextField idText = new JTextField();

	JLabel pswLabel = new JLabel();
	JPasswordField pswField = new JPasswordField();

	JLabel nameLabel = new JLabel("������");
	static JTextArea nameText = new JTextArea();

	static JLabel telLabel = new JLabel("�ֻ��ţ�");
	static JTextArea telText = new JTextArea();

	JLabel conaLabel = new JLabel("������ҵid��ţ�");
	static JTextArea conaText = new JTextArea();

	JLabel mailLabel = new JLabel("���䣺");
	static JTextArea mailText = new JTextArea();

	public Register() {
		FramMain.resJPan.setLayout(null);

		// id���ֱ�ǩ
		idLabel.setFont(login_fnt);
		idLabel.setForeground(Color.white);
		idLabel.setText("��ӭ���û�--�����������Ϣ");
		idLabel.setBounds(100, 70, 400, 30);
		FramMain.resJPan.add(idLabel);

		// id���� �ı���
		idText.setFont(login_fnt);
		idText.setBounds(185, 70, 200, 30);
//		FramMain.resJPan.add(idText);

		// ���� ��ǩ
		nameLabel.setFont(login_fnt);
		nameLabel.setForeground(Color.white);
		nameLabel.setBounds(85, 110, 150, 30);
		FramMain.resJPan.add(nameLabel);

		// ���� �ı���
		nameText.setFont(login_fnt);
		nameText.setBounds(185, 110, 200, 30);
		FramMain.resJPan.add(nameText);

		// �������ֱ�ǩ
		pswLabel.setFont(login_fnt);
		pswLabel.setForeground(Color.white);
		pswLabel.setText("���룺");
		pswLabel.setBounds(85, 150, 150, 30);
		FramMain.resJPan.add(pswLabel);

		// �������� �ı���
		pswField.setFont(login_fnt);
		pswField.setBounds(185, 150, 200, 30);
		FramMain.resJPan.add(pswField);

		// �ֻ��� ��ǩ
		telLabel.setFont(login_fnt);
		telLabel.setForeground(Color.white);
		telLabel.setBounds(75, 190, 150, 30);
		FramMain.resJPan.add(telLabel);

		// �ֻ��� �ı���
		telText.setFont(login_fnt);
		telText.setBounds(185, 190, 200, 30);
		FramMain.resJPan.add(telText);

		// ��ҵ ��ǩ
		conaLabel.setFont(login_fnt);
		conaLabel.setForeground(Color.white);
		conaLabel.setBounds(60, 230, 150, 30);
		FramMain.resJPan.add(conaLabel);

		// ��ҵ �ı���
		conaText.setFont(login_fnt);
		conaText.setBounds(185, 230, 200, 30);
		FramMain.resJPan.add(conaText);

		// ���� ��ǩ
		mailLabel.setFont(login_fnt);
		mailLabel.setForeground(Color.white);
		mailLabel.setBounds(85, 270, 150, 30);
		FramMain.resJPan.add(mailLabel);

		// ���� �ı���
		mailText.setFont(login_fnt);
		mailText.setBounds(185, 270, 200, 30);
		FramMain.resJPan.add(mailText);

		// ��� ��ѡ
		ButtonGroup option;
		JRadioButton optionA;
		JRadioButton optionB;

		option = new ButtonGroup();
		optionA = new JRadioButton("��ҵ�û�");
		optionB = new JRadioButton("ϵͳ����Ա");
//		option.setSelected((ButtonModel) optionA, true);
		optionA.setBounds(180, 320, 80, 25);
		optionB.setBounds(290, 320, 90, 25);

		FramMain.resJPan.add(optionA);
		FramMain.resJPan.add(optionB);
		// �Ѱ�ť�ӵ�ͬһ����ť��
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
					type = "��ҵ�û�";
				} else if (optionB.isSelected() == true) {
					type = "ϵͳ����Ա";
				}
				if (psw != null && tel != null && coid != null && mail != null && type != null) {
					String insertSql = "insert into userinfo values('" + type + "'," + coid + ",'" + psw + "','" + tel
							+ "','" + name + "','" + mail + "')";
					System.out.println(insertSql);
					try {
						
							String newid=newUser(insertSql);
							JOptionPane.showMessageDialog(null, "ע��ɹ�,����id����Ϊ ��"+newid, "��ʾ", JOptionPane.PLAIN_MESSAGE);
							FramMain.resJPan.setVisible(false);
							FramMain.loginJPan.setVisible(true);
						
					} catch (HeadlessException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "����д������Ϣ", "��ʾ", JOptionPane.PLAIN_MESSAGE);
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
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL���ݿ�����
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=zheng";// ����Դ // ������ע�������ּ��ػ����������ݿ�ʧ��һ���������������
		String Name = "sa";
		String Pwd = "86657148";

		try {
			Class.forName(driverName);// �������ݿ��������
			connection = DriverManager.getConnection(dbURL, Name, Pwd);// ��ȡ����
			System.out.println("���ݿ����ӳɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("����ʧ��");
		}

		Statement stmt = null;// ʹ��Statement�ӿ�����sql���
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int count = stmt.executeUpdate(insertSql);
		System.out.println("�ɹ����" + count + "��");
		
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
					System.out.println("�µ��û�id���Ϊ��"+id);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			System.out.println("�µ��û�id���Ϊ��"+id);
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
