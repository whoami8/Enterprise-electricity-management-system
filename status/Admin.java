package status;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import companyElec.FramMain;

public class Admin {
	// ��Ƭ���
	private JButton menu_btn_1 = new JButton("��ҵ����");
	private JButton menu_btn_2 = new JButton("�����õ����");
	private JButton menu_btn_3 = new JButton("�õ���ҵ��Ϣ");

	// �ֲ��л�ҳ��
	public static JPanel comMsg = new JPanel();
	public static JPanel comInfo = new JPanel();
	public static JPanel areaInfo = new JPanel();

	private CardLayout card = null;

	static JTextField text = new JTextField("0");
	static BorderLayout border = new BorderLayout(6, 6);

	public Admin() {
		card = new CardLayout(5, 5);// ��Ƭ����
		FramMain.admiJPan = new JPanel(card);// �ֲ���
		// perJPn = new JPanel();//������ť

		JPanel menu_button = new JPanel();
		menu_button.setLayout(new GridLayout(1, 3, 13, 10));
		menu_button.setBounds(10, 10, 600, 30);

		// ������������ʾ�����
		menu_button.add(menu_btn_1);
		menu_button.add(menu_btn_2);
		menu_button.add(menu_btn_3);

		FramMain.roleContainer.add(menu_button, border.SOUTH);
		
		
		//�����������Ĳ��� ������ɫ
		comMsg.setLayout(null);
		comMsg.setBackground(Color.lightGray);

		comInfo.setLayout(null);
		comInfo.setBackground(Color.PINK);

		areaInfo.setLayout(null);
		areaInfo.setBackground(Color.GREEN);

		FramMain.admiJPan.add(comMsg, "comMsg");
		FramMain.admiJPan.add("comInfo", comInfo);
		FramMain.admiJPan.add("areaInfo", areaInfo);
		
		
		//	��Ӽ����¼�
		ButtonactionPerformedmsg bp_msg = new ButtonactionPerformedmsg();
		menu_btn_1.addActionListener(bp_msg);
		ButtonactionPerformedinfo bp_info = new ButtonactionPerformedinfo();
		menu_btn_2.addActionListener(bp_info);
		ButtonactionPerformedarea bp_area = new ButtonactionPerformedarea();
		menu_btn_3.addActionListener(bp_area);

		menu_btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				card.show(FramMain.admiJPan, "comMsg");

			}
		});
		menu_btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(FramMain.admiJPan, "comInfo");

			}
		});
		menu_btn_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(FramMain.admiJPan, "areaInfo");
			}
		});

		FramMain.admiJPan.setVisible(true);
		FramMain.roleContainer.add(FramMain.admiJPan, border.CENTER);

	}

	// չʾ��ҵ��Ϣ
	public void showAllCom() {
		String[] columnNames = { "���", "����", "��ַ", "��ϵ�绰", "��ϵ��" };
		String[][] tableValues = {};
		DefaultTableModel tableModel = new DefaultTableModel(tableValues, columnNames);
		JTable table = new JTable(tableModel);
		@SuppressWarnings("unused")
		JScrollPane jscrollpane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		int rowCount = 0;
		Connection connection = null;
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL���ݿ�����
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=zheng";// ����Դ // ������ע�������ּ��ػ����������ݿ�ʧ��һ���������������
		String Name = "sa";
		String Pwd = "86657148";

		ResultSet allres = null;
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
		// ��ѯ
		try {
			allres = (ResultSet) stmt.executeQuery("select * from companyinfo;");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//��ѯ�����Ϊ�� ��ȡ��Ϣ
		if (allres != null) {
			try {
				while (allres.next()) {
					String co_id = allres.getString(1);
					String co_name = allres.getString(2);
					String co_addr = allres.getString(3);
					String co_tel = allres.getString(4);
					String co_link = allres.getString(5);

					String[] rowValues = { co_id, co_name, co_addr, co_tel, co_link };
					tableModel.addRow(rowValues);
					rowCount = table.getRowCount() + 1;

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jscrollpane.setLocation(0, 0);
			jscrollpane.setViewportView(table);

			table.setRowHeight(35);
			table.setSelectionBackground(Color.PINK);
			table.setFont(new Font("����", Font.PLAIN, 17));
			table.setBounds(20, 20, 550, 500);
			table.setSize(new Dimension(550, 500));

			DefaultTableCellRenderer r = new DefaultTableCellRenderer();
			r.setHorizontalAlignment(JLabel.CENTER);
			table.setDefaultRenderer(Object.class, r);

			TableColumn oddColumn = table.getColumnModel().getColumn(0);
			oddColumn.setPreferredWidth(80);
			oddColumn.setMinWidth(30);

			jscrollpane.setBounds(400, 20, 750, 520);
			comMsg.add(jscrollpane);

		}
		// Result result=ResultSupport.toResult(res);
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

	// չʾ�ܵ������ܵ��
	public int showAllBill(String sql) {
		int flag = 0;
		String[] columnNames = { "���", "����", "�ܵ���", "�ܵ��", "ʱ��" };
		String[][] tableValues = {};
		DefaultTableModel tableModel = new DefaultTableModel(tableValues, columnNames);
		JTable table = new JTable(tableModel);
		@SuppressWarnings("unused")
		JScrollPane jscrollpane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		int rowCount = 0;
		Connection connection = null;
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL���ݿ�����
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=zheng";// ����Դ // ������ע�������ּ��ػ����������ݿ�ʧ��һ���������������
		String Name = "sa";
		String Pwd = "86657148";

		ResultSet allres = null;
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
		// ��ѯ
		String showSql = "select companyinfo.co_id,co_name,elec_sum,elec_cost,ym_data from companyinfo,elecbill,combill where companyinfo.co_id=combill.co_id and elecbill.bill_num=combill.bill_num";
		if (!sql.equals("")) {
			showSql = showSql + sql;
		}
		System.out.println(showSql);
		try {
			allres = (ResultSet) stmt.executeQuery(showSql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (allres != null) {

			try {
				while (allres.next()) {
					String co_id = allres.getString(1);
					String co_name = allres.getString(2);
					String elec_sum = allres.getString(3);
					String elec_cost = allres.getString(4);
					String ym_data = allres.getString(5);

					String[] rowValues = { co_id, co_name, elec_sum, elec_cost, ym_data };
					tableModel.addRow(rowValues);
					rowCount = table.getRowCount() + 1;

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jscrollpane.setLocation(0, 0);
			jscrollpane.setViewportView(table);

			table.setRowHeight(35);
			table.setSelectionBackground(Color.PINK);
			table.setFont(new Font("����", Font.PLAIN, 17));
			table.setBounds(20, 20, 550, 500);
			table.setSize(new Dimension(550, 500));

			DefaultTableCellRenderer r = new DefaultTableCellRenderer();
			r.setHorizontalAlignment(JLabel.CENTER);
			table.setDefaultRenderer(Object.class, r);

			TableColumn oddColumn = table.getColumnModel().getColumn(0);
			oddColumn.setPreferredWidth(80);
			oddColumn.setMinWidth(30);

			jscrollpane.setBounds(500, 20, 700, 520);
			areaInfo.add(jscrollpane);

		}
		// Result result=ResultSupport.toResult(res);
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
		return flag;
	}

	public int showAllElec(String sql, int pos) {
		String[] columnNames = { "���", "����", "�º��ܵ���", "���", "ʱ��" };
		String[][] tableValues = {};
		DefaultTableModel tableModel = new DefaultTableModel(tableValues, columnNames);
		JTable table = new JTable(tableModel);
		@SuppressWarnings("unused")
		JScrollPane jscrollpane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		int rowCount = 0;
		Connection connection = null;
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL���ݿ�����
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=zheng";// ����Դ // ������ע�������ּ��ػ����������ݿ�ʧ��һ���������������
		String Name = "sa";
		String Pwd = "86657148";

		ResultSet allres = null;
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
		// ��ѯ
		try {
			allres = (ResultSet) stmt.executeQuery(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (allres != null) {

			try {
				while (allres.next()) {
					String co_id = allres.getString(1);
					String co_name = allres.getString(2);
					String elec_sum = allres.getString(3);
					String elec_cost = allres.getString(4);
					String ym_data = allres.getString(5);

					String[] rowValues = { co_id, co_name, elec_sum, elec_cost, ym_data };
					tableModel.addRow(rowValues);
					rowCount = table.getRowCount() + 1;

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jscrollpane.setLocation(0, 0);
			jscrollpane.setViewportView(table);

			table.setRowHeight(35);
			table.setSelectionBackground(Color.PINK);
			table.setFont(new Font("����", Font.PLAIN, 17));
			table.setBounds(20, 20, 550, 500);
			table.setSize(new Dimension(550, 500));

			DefaultTableCellRenderer r = new DefaultTableCellRenderer();
			r.setHorizontalAlignment(JLabel.CENTER);
			table.setDefaultRenderer(Object.class, r);

			TableColumn oddColumn = table.getColumnModel().getColumn(0);
			oddColumn.setPreferredWidth(80);
			oddColumn.setMinWidth(30);

			jscrollpane.setBounds(550, pos, 600, 250);
			comInfo.add(jscrollpane);
		}
		// Result result=ResultSupport.toResult(res);
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
		return 0;
	}

	// ����
	// ��ɾ�� ��������
	public String updateCom(String updateSql, int req) throws SQLException {
		String id = null;
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
		int count = stmt.executeUpdate(updateSql);
		System.out.println("�ɹ��޸�" + count + "����ҵ��Ϣ");
		// ���ض������
		if (req == 1) {
			String reSql = "select max(bill_num) from elecbill";
			ResultSet res = null;
			res = (ResultSet) stmt.executeQuery(reSql);
			if (res.next()) {
				System.out.println(res.getString(1));
				id = res.getString(1);
			}
			System.out.println("id" + id);
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
		System.out.println("id" + id);
		return id;
	}

	// ��ѯ��ҵ
	public int findBill(String scanSql) throws SQLException {
		int flag = 0;
		int id = 0;
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
		// ��ѯ
		ResultSet res = null;
		res = (ResultSet) stmt.executeQuery(scanSql);
		if (res.next()) {
			System.out.println(res.getString(1));
//			flag = 1;
			flag = Integer.parseInt(res.getString(1));
		} else {
			flag = 2;
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
		return flag;
	}
//������Ϣ
	public String search(String sql) throws SQLException
	{
		String resStr="";
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
		// ��ѯ
		ResultSet res = null;
		res = (ResultSet) stmt.executeQuery(sql);
		while (res.next()) {
			resStr=res.getString(1)+resStr;
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
		return resStr;
	}
	
	//��һ����� չʾ��ҵ�����¼�
	private class ButtonactionPerformedmsg implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JLabel c1, c2, c3, c4, c5, c6, c7, c8, c9;
			JTextField text1, text2, text3, text4, text5, text6, text7, text8, text9;
			JTextField search;
			JLabel name, image;
			String facname = null;
			name = new JLabel("��������ҵ���:", JLabel.RIGHT);
			c1 = new JLabel("��ҵ����:", JLabel.RIGHT);
			c2 = new JLabel("��ҵ��ַ:", JLabel.RIGHT);
			c3 = new JLabel("��ҵ��ϵ��ַ:", JLabel.RIGHT);
			c4 = new JLabel("��ҵ��ϵ��:", JLabel.RIGHT);

			JButton addBtn = new JButton("���");
			JButton delBtn = new JButton("ɾ��");
			JButton chgBtn = new JButton("�޸�");
			JButton findBtn = new JButton("��ѯ");

			text1 = new JTextField(200);
			text2 = new JTextField(200);
			text3 = new JTextField(200);
			text4 = new JTextField(200);

			search = new JTextField(60);
			name.setBounds(60, 50, 120, 30);
			c1.setBounds(80, 100, 100, 30);
			c2.setBounds(80, 130, 100, 30);
			c3.setBounds(80, 160, 100, 30);
			c4.setBounds(80, 190, 100, 30);

			search.setBounds(200, 50, 100, 25);
			findBtn.setBounds(320, 52, 65, 25);

			text1.setBounds(200, 100, 180, 30);
			text2.setBounds(200, 130, 180, 30);
			text3.setBounds(200, 160, 180, 30);
			text4.setBounds(200, 190, 180, 30);

			addBtn.setBounds(120, 260, 65, 25);
			chgBtn.setBounds(200, 260, 65, 25);
			delBtn.setBounds(280, 260, 65, 25);

			comMsg.add(findBtn);
			comMsg.add(addBtn);
			comMsg.add(chgBtn);
			comMsg.add(delBtn);

			comMsg.add(search);
			comMsg.add(text1);
			comMsg.add(text2);
			comMsg.add(text3);
			comMsg.add(text4);
			comMsg.add(name);
			comMsg.add(c1);
			comMsg.add(c2);
			comMsg.add(c3);
			comMsg.add(c4);
			comMsg.setBackground(Color.lightGray);
			comMsg.setLayout(null);

//------------���Ұ�ť------------------------
			findBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String id = search.getText();// ��ҵid

					if (id.equals("")) {
						JOptionPane.showMessageDialog(null, "��������ҵ���", "��ʾ", JOptionPane.PLAIN_MESSAGE);
					} else {
						ResultSet findres = null;
						try {
							String findSql = "select * from companyinfo where co_id=" + id + ";";
							Connection connection = null;
							String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL���ݿ�����
							String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=zheng";// ����Դ //
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
							// ��ѯ
							findres = (ResultSet) stmt.executeQuery(findSql);
							boolean flag = true;

							while (findres.next()) {
								flag = false;
								System.out.println("findres.getString(1)" + findres.getString(1));
								text1.setText(findres.getString(2));
								text2.setText(findres.getString(3));
								text3.setText(findres.getString(4));
								text4.setText(findres.getString(5));

							}
							System.out.println("flag" + flag);
							if (flag) {
								search.setText("");
								text1.setText("");
								text2.setText("");
								text3.setText("");
								text4.setText("");
								JOptionPane.showMessageDialog(null, "δ�ҵ�����ҵ��Ϣ", "��ʾ", JOptionPane.PLAIN_MESSAGE);
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
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			});
			//------------�����ҵ��Ϣ��ť------------------------
			addBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String addSql = null;
					String id = search.getText();// ��ҵid
					String name = text1.getText();// ��ҵid
					String addr = text2.getText();// ��ҵid
					String tel = text3.getText();// ��ҵid
					String linker = text4.getText();// ��ҵid

					addSql = "insert into companyinfo values('" + name + "','" + addr + "','" + tel + "','" + linker
							+ "');";
					try {
						updateCom(addSql, 0);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					showAllCom();
					search.setText("");
					text1.setText("");
					text2.setText("");
					text3.setText("");
					text4.setText("");
				}
			});
			
			//------------�޸���ҵ��Ϣ��ť------------------------
			chgBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String updateSql = null;
					String id = search.getText();// ��ҵid
					String name = text1.getText();// ��ҵid
					String addr = text2.getText();// ��ҵid
					String tel = text3.getText();// ��ҵid
					String linker = text4.getText();// ��ҵid
					if (id.equals("")) {
						JOptionPane.showMessageDialog(null, "��������ҵ���", "��ʾ", JOptionPane.PLAIN_MESSAGE);
					} else {
						updateSql = "update companyinfo set co_name='" + name + "',co_addr='" + addr + "',co_tel='"
								+ tel + "',co_link='" + linker + "' where co_id='" + id + "';";
						System.out.println(updateSql);
						try {
							updateCom(updateSql, 0);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					search.setText("");
					text1.setText("");
					text2.setText("");
					text3.setText("");
					text4.setText("");
					showAllCom();
				}
			});
			//------------ɾ����ҵ��Ϣ��ť------------------------
			delBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String delSql = null;
					String id = search.getText();// ��ҵid
					if (id.equals("")) {
						JOptionPane.showMessageDialog(null, "��������ҵ���", "��ʾ", JOptionPane.PLAIN_MESSAGE);
					} else {
						delSql = "delete from companyinfo where co_id='" + id + "';";
						try {
							updateCom(delSql, 0);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					showAllCom();
					search.setText("");
					text1.setText("");
					text2.setText("");
					text3.setText("");
					text4.setText("");
				}
			});

		}
	}

	//�ڶ������ ��ҵ�õ���Ϣ��ɾ�Ĳ�
	private class ButtonactionPerformedinfo implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JLabel jl_luse, jl_huse, jl_sum, jl_cost, jl_time;
			JTextField text_id, text_luse, text_huse, text_sum, text_cost, text_time;

			JLabel jl_id;

			JComboBox xl_year = new JComboBox();
			JComboBox xl_month = new JComboBox();
			JComboBox xl_day = new JComboBox();
			for (int i = 2010; i < 2030; i++) {
				xl_year.addItem(i);
			}
			for (int i = 1; i < 13; i++) {
				xl_month.addItem(i);
			}
			for (int i = 1; i < 31; i++) {
				xl_day.addItem(i);
			}

			jl_id = new JLabel("��ҵ���:", JLabel.RIGHT);
			jl_luse = new JLabel("��ҵ�ȵ���:", JLabel.RIGHT);
			jl_huse = new JLabel("��ҵ�����:", JLabel.RIGHT);
			jl_sum = new JLabel("��ҵ�ܵ���:", JLabel.RIGHT);
			jl_cost = new JLabel("��ҵ���:", JLabel.RIGHT);
			jl_time = new JLabel("���ʱ��:", JLabel.RIGHT);

			JButton addBtn = new JButton("���");
			JButton delBtn = new JButton("ɾ��");
			JButton chgBtn = new JButton("�޸�");
			JButton findBtn = new JButton("��ѯ");

			text_id = new JTextField(200);
			text_luse = new JTextField(200);
			text_huse = new JTextField(200);
			text_sum = new JTextField(200);
			text_cost = new JTextField(200);
			text_time = new JTextField(200);

			jl_id.setBounds(30, 30, 100, 30);
			jl_luse.setBounds(30, 70, 100, 30);
			jl_huse.setBounds(30, 110, 100, 30);
			jl_sum.setBounds(30, 150, 100, 30);
			jl_cost.setBounds(30, 190, 100, 30);
			jl_time.setBounds(30, 220, 100, 30);

			text_id.setBounds(150, 30, 200, 30);
			text_luse.setBounds(150, 70, 200, 30);
			text_huse.setBounds(150, 110, 200, 30);
			text_sum.setBounds(150, 150, 200, 30);
			text_cost.setBounds(150, 190, 200, 30);
			// text_time.setBounds(200, 70, 200, 30);

			xl_year.setBounds(150, 230, 80, 30);
			xl_month.setBounds(240, 230, 60, 30);
			xl_day.setBounds(310, 230, 60, 30);

			addBtn.setBounds(130, 280, 65, 25);
			chgBtn.setBounds(210, 280, 65, 25);
			delBtn.setBounds(290, 280, 65, 25);
			
			//--------�����ҵ�õ���Ϣ--------
			addBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String addbillSql = null, hpay = null, lpay = null;
					String addelecSql = null;
					String comid = text_id.getText();
					String luse = text_luse.getText();
					String huse = text_huse.getText();
					String sum = text_sum.getText();
					String cost = text_cost.getText();

					String kongMon = "", kongDay = "";
					int year = (int) xl_year.getSelectedItem();
					int month = (int) xl_month.getSelectedItem();
					if (month < 10) {
						kongMon = "0";
					}
					int day = (int) xl_day.getSelectedItem();
					if (month < 10) {
						kongDay = "0";
					}
					String time = year + "-" + kongMon + month + "-" + kongDay + day;
					String isSql = "select * from elecbill,combill where elecbill.bill_num=combill.bill_num and ym_data='"
							+ time + "' and co_id='" + comid + "';";
					String billid = null;
					if (comid.equals("")) {
						JOptionPane.showMessageDialog(null, "��������ҵ��ź�ʱ��", "��ʾ", JOptionPane.PLAIN_MESSAGE);
					} else {
						try {
							if (findBill(isSql) != 2) {
								JOptionPane.showMessageDialog(null, "��ҵ" + comid + "���ʱ��Ϊ" + time + "�Ѿ���ӹ�", "��ʾ",
										JOptionPane.PLAIN_MESSAGE);
							} else {
								float lluse = Float.parseFloat(luse);
								float hhuse = Float.parseFloat(huse);
								addelecSql = "insert into elecbill(elec_low,elec_high,elec_sum,elec_cost,ym_data) values('"
										+ luse + "','" + huse + "','" + sum + "','" + cost + "','" + time + "')";
								System.out.println("addelecSq--" + addelecSql);

								try {
									billid = updateCom(addelecSql, 1);
									System.out.println(billid);

									String updateHigh = "update elecbill set pay_high=(select (elec_high*pc_high) from priceinfo,elecbill where bill_num="
											+ billid + ") where bill_num='" + billid + "';";
									System.out.println(updateHigh);
									String updateLow = "update elecbill set pay_low=(select (elec_low*pc_low) from priceinfo,elecbill where bill_num="
											+ billid + ") where bill_num='" + billid + "';";
									System.out.println(updateLow);
									updateCom(updateHigh, 0);
									updateCom(updateLow, 0);
									addbillSql = "insert into combill values('" + comid + "','" + billid + "','N');";
									updateCom(addbillSql, 0);
									System.out.println("addbillSq--" + addbillSql);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								showAllBill("");
							}
						} catch (HeadlessException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					text_id.setText("");
					text_luse.setText("");
					text_huse.setText("");
					text_cost.setText("");
				}
			});
			
			//--------�ı���ҵ�õ���Ϣ--------
			chgBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String updateSql = null, hpay = null, lpay = null;
					String addelecSql = null;
					String billid = null;
					String comid = text_id.getText();
					String luse = text_luse.getText();
					String huse = text_huse.getText();
					String sum = text_sum.getText();
					String cost = text_cost.getText();

					String kongMon = "", kongDay = "";
					int year = (int) xl_year.getSelectedItem();
					int month = (int) xl_month.getSelectedItem();
					if (month < 10) {
						kongMon = "0";
					}
					int day = (int) xl_day.getSelectedItem();
					if (month < 10) {
						kongDay = "0";
					}
					String time = year + "-" + kongMon + month + "-" + kongDay + day;
					System.out.println(time);
					String isSql = "select * from elecbill,combill where elecbill.bill_num=combill.bill_num and ym_data='"
							+ time + "' and co_id='" + comid + "';";
					if (comid.equals("")) {
						JOptionPane.showMessageDialog(null, "��������ҵ��ź�ʱ��", "��ʾ", JOptionPane.PLAIN_MESSAGE);
					} else {
						try {
							if (findBill(isSql) == 2) {
								JOptionPane.showMessageDialog(null, "��ҵ" + comid + "���ʱ��Ϊ" + time + "��û��ӹ�", "��ʾ",
										JOptionPane.PLAIN_MESSAGE);
							} else {
								updateSql = "update elecbill set elec_low='" + luse + "',elec_high='" + huse
										+ "',elec_sum='" + sum + "',elec_cost='" + cost + "'"
										+ "  where bill_num=(select elecbill.bill_num from elecbill,combill where elecbill.bill_num=combill.bill_num and ym_data='"
										+ time + "' and co_id='" + comid + "') ;";
								System.out.println(updateSql);
								try {
									billid = updateCom(updateSql, 1);
									String bill = "select elecbill.bill_num from elecbill,combill where co_id='" + comid
											+ "' and ym_data='" + time + "'";
									int billnum = findBill(bill);
									String updateHigh = "update elecbill set pay_high=(select (elec_high*pc_high) from priceinfo,elecbill where bill_num='"
											+ billnum + "') where bill_num='" + billnum + "';";
									System.out.println("updateHigh" + updateHigh);
									String updateLow = "update elecbill set pay_low=(select (elec_low*pc_low) from priceinfo,elecbill where bill_num="
											+ billnum + ") where bill_num='" + billnum + "';";
									System.out.println(updateLow);

									updateCom(updateHigh, 0);
									updateCom(updateLow, 0);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								showAllBill("");
							}
						} catch (HeadlessException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

					text_id.setText("");
					text_luse.setText("");
					text_huse.setText("");
					text_cost.setText("");
				}
			});
			//--------ɾ����ҵ�õ���Ϣ--------
			delBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String delSql = null;
					String comid = text_id.getText();// ��ҵid
					String kongMon = "", kongDay = "";
					int year = (int) xl_year.getSelectedItem();
					int month = (int) xl_month.getSelectedItem();
					if (month < 10) {
						kongMon = "0";
					}
					int day = (int) xl_day.getSelectedItem();
					if (month < 10) {
						kongDay = "0";
					}
					String time = year + "-" + kongMon + month + "-" + kongDay + day;
					// �жϸ�ʱ���bill�Ƿ��Ѿ�����
					String isSql = "select * from elecbill,combill where elecbill.bill_num=combill.bill_num and ym_data='"
							+ time + "' and co_id='" + comid + "';";
					if (comid.equals("")) {
						JOptionPane.showMessageDialog(null, "��������ҵ���", "��ʾ", JOptionPane.PLAIN_MESSAGE);
					} else {
						try {
							if (findBill(isSql) == 2) {
								JOptionPane.showMessageDialog(null, "��ҵ" + comid + "���ʱ��Ϊ" + time + "����Ϣ������", "��ʾ",
										JOptionPane.PLAIN_MESSAGE);
							} else {
								delSql = "delete from elecbill where elecbill.bill_num=(select elecbill.bill_num from elecbill,combill where elecbill.bill_num=combill.bill_num and ym_data='"
										+ time + "' and co_id='" + comid + "');";
								System.out.println("del--" + delSql);
								try {
									updateCom(delSql, 0);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								showAllBill("");
							}
						} catch (HeadlessException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					text_id.setText("");
					text_luse.setText("");
					text_huse.setText("");
					text_cost.setText("");
				}
			});

			JButton asghBtn = new JButton("�ܵ������");
			JButton asglBtn = new JButton("�ȵ�������");

			JButton deschBtn = new JButton("�ܵ�ѽ���");
			JButton desclBtn = new JButton("�ܵ�������");

			// λ�ú����
			asghBtn.setBounds(385, 80, 105, 30);
			asglBtn.setBounds(385, 180, 105, 30);
			deschBtn.setBounds(385, 280, 105, 30);
			desclBtn.setBounds(385, 380, 105, 30);

			areaInfo.add(asghBtn);
			areaInfo.add(asglBtn);
			areaInfo.add(deschBtn);
			areaInfo.add(desclBtn);

			showAllBill("");
			
			//��ťչʾ���
			asghBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showAllBill(" order by elec_cost asc");
				}
			});

			asglBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showAllBill(" order by elec_sum asc");
				}
			});

			deschBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showAllBill(" order by elec_cost desc");
				}
			});

			desclBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					showAllBill(" order by elec_sum desc");
				}
			});

			areaInfo.add(findBtn);
			areaInfo.add(addBtn);
			areaInfo.add(chgBtn);
			areaInfo.add(delBtn);

			areaInfo.setLayout(null);
			areaInfo.add(jl_id);
			areaInfo.add(jl_luse);
			areaInfo.add(jl_huse);
			areaInfo.add(jl_cost);
			areaInfo.add(jl_sum);
			areaInfo.add(jl_time);
			areaInfo.add(text_id);
			areaInfo.add(text_luse);
			areaInfo.add(text_huse);
			areaInfo.add(text_cost);
			areaInfo.add(text_time);
			areaInfo.add(text_sum);
			areaInfo.add(xl_year);
			areaInfo.add(xl_month);
			areaInfo.add(xl_day);
		}
	}

	//	����ͳ����Ϣ����
	private class ButtonactionPerformedarea implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JButton btn_elec, btn_cost;
			JTextField text_peak, text_vale, text_csa;
			JTextField text_huse, text_luse, text_aver, text_sum;
			JLabel jl_idfind, jl_datafind;
			JTextField text_find;
			JLabel jl_elec, jl_peak, jl_vale, jl_csa;
			JLabel jl_cost, jl_huse, jl_luse, jl_aver, jl_sum;

			jl_elec = new JLabel("�����õ����", JLabel.RIGHT);
			btn_elec = new JButton("ͳ��");

			jl_peak = new JLabel("�������ܵķ����Ϊ:", JLabel.RIGHT);
			jl_vale = new JLabel("�������ܵĹȵ���Ϊ:", JLabel.RIGHT);
			jl_csa = new JLabel("�� �ȵ�������:", JLabel.RIGHT);

			text_peak = new JTextField(200);
			text_vale = new JTextField(200);
			text_csa = new JTextField(200);

			text_peak.setEditable(false);
			text_vale.setEditable(false);
			text_csa.setEditable(false);

			jl_elec.setBounds(80, 100, 100, 30);
			btn_elec.setBounds(250, 100, 65, 25);

			jl_peak.setBounds(80, 140, 150, 30);
			jl_vale.setBounds(80, 180, 150, 30);
			jl_csa.setBounds(80, 220, 150, 30);

			text_peak.setBounds(250, 140, 200, 30);
			text_vale.setBounds(250, 180, 200, 30);
			text_csa.setBounds(250, 220, 200, 30);

			jl_cost = new JLabel("������:", JLabel.RIGHT);
			btn_cost = new JButton("ͳ��");

			jl_huse = new JLabel("�õ�������ҵΪ:", JLabel.RIGHT);
			jl_luse = new JLabel("�õ���С����ҵΪ:", JLabel.RIGHT);
			jl_aver = new JLabel("ƽ�����Ϊ:", JLabel.RIGHT);
			jl_sum = new JLabel("�ܵ��Ϊ:", JLabel.RIGHT);

			jl_cost.setBounds(80, 270, 150, 30);
			btn_cost.setBounds(250, 270, 65, 25);

			jl_huse.setBounds(80, 310, 150, 30);
			jl_luse.setBounds(80, 350, 150, 30);
			jl_aver.setBounds(80, 390, 150, 30);
			jl_sum.setBounds(80, 430, 150, 30);

			text_huse = new JTextField(200);
			text_luse = new JTextField(200);
			text_aver = new JTextField(200);
			text_sum = new JTextField(200);

			text_huse.setEditable(false);
			text_luse.setEditable(false);
			text_aver.setEditable(false);
			text_sum.setEditable(false);

			btn_elec.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String highsum="";
					String highSql = "select sum(elec_high) from elecbill";
					
					String lowsum="";
					String lowSql = "select sum(elec_low) from elecbill";

					String scaElec="";
					String scaSql="select (sum(elec_high)/sum(elec_low)) from elecbill";
					
					
					try {
						highsum = search(highSql);
						lowsum = search(lowSql);
						scaElec = search(scaSql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					text_peak.setText(highsum);
					text_vale.setText(lowsum);
					text_csa.setText(scaElec);
				}

				
			});

			btn_cost.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					String maxCom="";
					String maxSql = "select co_name from companyinfo,elecbill,combill where elec_sum=( select max(elec_sum) from elecbill) and companyinfo.co_id=combill.co_id and elecbill.bill_num=combill.bill_num";
					
					String minCom="";
					String minSql = "select co_name from companyinfo,elecbill,combill where elec_sum=( select min(elec_sum) from elecbill) and companyinfo.co_id=combill.co_id and elecbill.bill_num=combill.bill_num";

					String averElec="";
					String averSql="select avg(elec_sum) from elecbill";
					
					String sumElec="";
					String sumSql="select sum(elec_sum) from elecbill";
					
					try {
						maxCom = search(maxSql);
						minCom = search(minSql);
						averElec = search(averSql);
						sumElec = search(sumSql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					text_huse.setText(maxCom);
					text_luse.setText(minCom);
					text_aver.setText(averElec);
					text_sum.setText(sumElec);
				
				}
			});

			text_huse.setBounds(250, 310, 200, 30);
			text_luse.setBounds(250, 350, 200, 30);
			text_aver.setBounds(250, 390, 200, 30);
			text_sum.setBounds(250, 430, 200, 30);

			// �ұ�
			// ������ҵ��Ų�����ʱ����÷�
			jl_idfind = new JLabel("��ҵ���:", JLabel.RIGHT);
			text_find = new JTextField(200);
			JButton idfindBtn = new JButton("��ѯ");
			jl_idfind.setBounds(580, 10, 100, 30);
			text_find.setBounds(700, 10, 200, 30);
			idfindBtn.setBounds(1000, 10, 65, 25);

			idfindBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String id = text_find.getText();
					String idSql = "select companyinfo.co_id,co_name,elec_sum,elec_cost,ym_data from elecbill,combill,companyinfo where combill.co_id='"
							+ id + "' and elecbill.bill_num=combill.bill_num and companyinfo.co_id=combill.co_id;";
					System.out.println("idSql==" + idSql);
					showAllElec(idSql, 50);
				}
			});

			// ����ʱ���������ҵ���÷�
			jl_datafind = new JLabel("����:", JLabel.RIGHT);
			JComboBox xl_year = new JComboBox();
			JComboBox xl_month = new JComboBox();
			JComboBox xl_day = new JComboBox();
			for (int i = 2010; i < 2030; i++) {
				xl_year.addItem(i);
			}
			for (int i = 1; i < 13; i++) {
				xl_month.addItem(i);
			}
			for (int i = 1; i < 32; i++) {
				xl_day.addItem(i);
			}
			JButton datafindBtn = new JButton("��ѯ");

			jl_datafind.setBounds(580, 310, 100, 30);
			xl_year.setBounds(700, 310, 80, 30);
			xl_month.setBounds(790, 310, 60, 30);
			xl_day.setBounds(860, 310, 60, 30);
			datafindBtn.setBounds(1000, 310, 65, 25);

			datafindBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String kongMon = "", kongDay = "";
					int year = (int) xl_year.getSelectedItem();
					int month = (int) xl_month.getSelectedItem();
					if (month < 10) {
						kongMon = "0";
					}
					int day = (int) xl_day.getSelectedItem();
					if (month < 10) {
						kongDay = "0";
					}
					String time = year + "-" + kongMon + month + "-" + kongDay + day;

					String dataSql = "select companyinfo.co_id,co_name,elec_sum,elec_cost,ym_data from elecbill,combill,companyinfo where ym_data='"
							+ time + "' and elecbill.bill_num=combill.bill_num and companyinfo.co_id=combill.co_id;;";
					showAllElec(dataSql, 350);
				}

			});

			comInfo.add(jl_idfind);
			comInfo.add(text_find);
			comInfo.add(idfindBtn);

			comInfo.add(jl_datafind);
			comInfo.add(xl_year);
			comInfo.add(xl_month);
			comInfo.add(xl_day);
			comInfo.add(datafindBtn);

			comInfo.add(jl_elec);
			comInfo.add(btn_elec);
			comInfo.add(jl_peak);
			comInfo.add(jl_vale);
			comInfo.add(jl_csa);

			comInfo.add(text_peak);
			comInfo.add(text_vale);
			comInfo.add(text_csa);

			comInfo.add(jl_cost);
			comInfo.add(btn_cost);
			comInfo.add(jl_huse);
			comInfo.add(jl_luse);
			comInfo.add(jl_aver);
			comInfo.add(jl_sum);

			comInfo.add(text_huse);
			comInfo.add(text_luse);
			comInfo.add(text_aver);
			comInfo.add(text_sum);
		}
	}
}
