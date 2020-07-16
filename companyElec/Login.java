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

	static JButton login = new JButton("��¼");
	static JButton register = new JButton("ע��");

	static Font login_fnt = new Font("����", Font.BOLD, 23);

	// ***************************
	// �˺� ������ȷ ��¼ ������ ֻ��ע��
	public Login() {

		FramMain.loginJPan.setLayout(null);
		JLabel idLabel = new JLabel();
		JTextField idField = new JTextField();

		JLabel pswLabel = new JLabel();
		JPasswordField pswField = new JPasswordField();

		// id���ֱ�ǩ
		idLabel.setFont(login_fnt);
		idLabel.setForeground(Color.white);
		idLabel.setText("�û�ID��");
		idLabel.setBounds(80, 70, 150, 30);
		FramMain.loginJPan.add(idLabel);

		// id���� �ı���
		idField.setFont(login_fnt);
		idField.setBounds(185, 70, 200, 30);
		FramMain.loginJPan.add(idField);

		// �������ֱ�ǩ
		pswLabel.setFont(login_fnt);
		pswLabel.setForeground(Color.white);
		pswLabel.setText("���룺");
		pswLabel.setBounds(85, 120, 150, 30);
		FramMain.loginJPan.add(pswLabel);

		// �������� �ı���
		pswField.setFont(login_fnt);
		pswField.setBounds(185, 125, 200, 30);
		FramMain.loginJPan.add(pswField);

		login.setBounds(185, 205, 70, 23);
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FramMain.loginJPan.setVisible(false);
				//long id = 0;
				// ��ȡ��¼ҳ�������id������
				String id = idField.getText();
				System.out.print("id=" + id);
				String psw = pswField.getText();
				System.out.print("psw=" + psw);
				if (id.equals("") || psw.equals("")) {
					JOptionPane.showMessageDialog(null, "�û�ID�����벻��Ϊ��", "��ʾ", JOptionPane.PLAIN_MESSAGE);
					System.out.print("kong=");
					FramMain.loginJPan.setVisible(true);
				} else {
					// id = Long.parseLong(idstr);// ��ȡid

					Connection connection = null;
					String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL���ݿ�����
					String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=zheng";// ����Դ
																						// ������ע�������ּ��ػ����������ݿ�ʧ��һ���������������
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
					ResultSet res = null;

					// ��ѯ
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
								if(res.getString(1).equals("��ҵ�û�"))
								{
									new Person(res.getString(2));
								}
								else if(res.getString(1).equals("ϵͳ����Ա"))
								{
									
									Admin admin=new Admin();
									admin.showAllCom();
								}
								FramMain.elecJFrame.setVisible(false);
								FramMain.roleJFrame.setVisible(true);//ʵ����ת
								dispose();//�رմ��壬�ͷ���Դ
							} 
						}
						if(flag) {
							JOptionPane.showMessageDialog(null, "�û�ID��������Ϣ����ȷ", "��ʾ", JOptionPane.PLAIN_MESSAGE);//��ʾ����
							FramMain.loginJPan.setVisible(true);
						}
					} catch (HeadlessException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// �ر����ݿ�
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
