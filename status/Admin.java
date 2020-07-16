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
	// 卡片面板
	private JButton menu_btn_1 = new JButton("企业管理");
	private JButton menu_btn_2 = new JButton("区域用电情况");
	private JButton menu_btn_3 = new JButton("用电企业信息");

	// 轮播切换页面
	public static JPanel comMsg = new JPanel();
	public static JPanel comInfo = new JPanel();
	public static JPanel areaInfo = new JPanel();

	private CardLayout card = null;

	static JTextField text = new JTextField("0");
	static BorderLayout border = new BorderLayout(6, 6);

	public Admin() {
		card = new CardLayout(5, 5);// 卡片布局
		FramMain.admiJPan = new JPanel(card);// 轮播的
		// perJPn = new JPanel();//包含按钮

		JPanel menu_button = new JPanel();
		menu_button.setLayout(new GridLayout(1, 3, 13, 10));
		menu_button.setBounds(10, 10, 600, 30);

		// 设置容器中显示的组件
		menu_button.add(menu_btn_1);
		menu_button.add(menu_btn_2);
		menu_button.add(menu_btn_3);

		FramMain.roleContainer.add(menu_button, border.SOUTH);
		
		
		//设置三个面板的布局 背景颜色
		comMsg.setLayout(null);
		comMsg.setBackground(Color.lightGray);

		comInfo.setLayout(null);
		comInfo.setBackground(Color.PINK);

		areaInfo.setLayout(null);
		areaInfo.setBackground(Color.GREEN);

		FramMain.admiJPan.add(comMsg, "comMsg");
		FramMain.admiJPan.add("comInfo", comInfo);
		FramMain.admiJPan.add("areaInfo", areaInfo);
		
		
		//	添加监听事件
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

	// 展示企业信息
	public void showAllCom() {
		String[] columnNames = { "编号", "名字", "地址", "联系电话", "联系人" };
		String[][] tableValues = {};
		DefaultTableModel tableModel = new DefaultTableModel(tableValues, columnNames);
		JTable table = new JTable(tableModel);
		@SuppressWarnings("unused")
		JScrollPane jscrollpane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		int rowCount = 0;
		Connection connection = null;
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=zheng";// 数据源 // ！！！注意若出现加载或者连接数据库失败一般是这里出现问题
		String Name = "sa";
		String Pwd = "86657148";

		ResultSet allres = null;
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
		// 查询
		try {
			allres = (ResultSet) stmt.executeQuery("select * from companyinfo;");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//查询结果不为空 获取信息
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
			table.setFont(new Font("楷体", Font.PLAIN, 17));
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

	// 展示总电量和总电费
	public int showAllBill(String sql) {
		int flag = 0;
		String[] columnNames = { "编号", "名字", "总电量", "总电费", "时间" };
		String[][] tableValues = {};
		DefaultTableModel tableModel = new DefaultTableModel(tableValues, columnNames);
		JTable table = new JTable(tableModel);
		@SuppressWarnings("unused")
		JScrollPane jscrollpane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		int rowCount = 0;
		Connection connection = null;
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=zheng";// 数据源 // ！！！注意若出现加载或者连接数据库失败一般是这里出现问题
		String Name = "sa";
		String Pwd = "86657148";

		ResultSet allres = null;
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
		// 查询
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
			table.setFont(new Font("楷体", Font.PLAIN, 17));
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
		String[] columnNames = { "编号", "名字", "月耗总电量", "电费", "时间" };
		String[][] tableValues = {};
		DefaultTableModel tableModel = new DefaultTableModel(tableValues, columnNames);
		JTable table = new JTable(tableModel);
		@SuppressWarnings("unused")
		JScrollPane jscrollpane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		int rowCount = 0;
		Connection connection = null;
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=zheng";// 数据源 // ！！！注意若出现加载或者连接数据库失败一般是这里出现问题
		String Name = "sa";
		String Pwd = "86657148";

		ResultSet allres = null;
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
		// 查询
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
			table.setFont(new Font("楷体", Font.PLAIN, 17));
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

	// 方法
	// 增删改 都可以用
	public String updateCom(String updateSql, int req) throws SQLException {
		String id = null;
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
		int count = stmt.executeUpdate(updateSql);
		System.out.println("成功修改" + count + "行企业信息");
		// 返回订单编号
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

	// 查询企业
	public int findBill(String scanSql) throws SQLException {
		int flag = 0;
		int id = 0;
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
		// 查询
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
//查找信息
	public String search(String sql) throws SQLException
	{
		String resStr="";
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
		// 查询
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
	
	//第一个面板 展示企业监听事件
	private class ButtonactionPerformedmsg implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JLabel c1, c2, c3, c4, c5, c6, c7, c8, c9;
			JTextField text1, text2, text3, text4, text5, text6, text7, text8, text9;
			JTextField search;
			JLabel name, image;
			String facname = null;
			name = new JLabel("请输入企业编号:", JLabel.RIGHT);
			c1 = new JLabel("企业名字:", JLabel.RIGHT);
			c2 = new JLabel("企业地址:", JLabel.RIGHT);
			c3 = new JLabel("企业联系地址:", JLabel.RIGHT);
			c4 = new JLabel("企业联系人:", JLabel.RIGHT);

			JButton addBtn = new JButton("添加");
			JButton delBtn = new JButton("删除");
			JButton chgBtn = new JButton("修改");
			JButton findBtn = new JButton("查询");

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

//------------查找按钮------------------------
			findBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String id = search.getText();// 企业id

					if (id.equals("")) {
						JOptionPane.showMessageDialog(null, "请输入企业编号", "提示", JOptionPane.PLAIN_MESSAGE);
					} else {
						ResultSet findres = null;
						try {
							String findSql = "select * from companyinfo where co_id=" + id + ";";
							Connection connection = null;
							String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
							String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=zheng";// 数据源 //
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
							// 查询
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
								JOptionPane.showMessageDialog(null, "未找到该企业信息", "提示", JOptionPane.PLAIN_MESSAGE);
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
			//------------添加企业信息按钮------------------------
			addBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String addSql = null;
					String id = search.getText();// 企业id
					String name = text1.getText();// 企业id
					String addr = text2.getText();// 企业id
					String tel = text3.getText();// 企业id
					String linker = text4.getText();// 企业id

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
			
			//------------修改企业信息按钮------------------------
			chgBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String updateSql = null;
					String id = search.getText();// 企业id
					String name = text1.getText();// 企业id
					String addr = text2.getText();// 企业id
					String tel = text3.getText();// 企业id
					String linker = text4.getText();// 企业id
					if (id.equals("")) {
						JOptionPane.showMessageDialog(null, "请输入企业编号", "提示", JOptionPane.PLAIN_MESSAGE);
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
			//------------删除企业信息按钮------------------------
			delBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String delSql = null;
					String id = search.getText();// 企业id
					if (id.equals("")) {
						JOptionPane.showMessageDialog(null, "请输入企业编号", "提示", JOptionPane.PLAIN_MESSAGE);
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

	//第二个面板 企业用电信息增删改查
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

			jl_id = new JLabel("企业编号:", JLabel.RIGHT);
			jl_luse = new JLabel("企业谷电量:", JLabel.RIGHT);
			jl_huse = new JLabel("企业峰电量:", JLabel.RIGHT);
			jl_sum = new JLabel("企业总电量:", JLabel.RIGHT);
			jl_cost = new JLabel("企业电费:", JLabel.RIGHT);
			jl_time = new JLabel("查表时间:", JLabel.RIGHT);

			JButton addBtn = new JButton("添加");
			JButton delBtn = new JButton("删除");
			JButton chgBtn = new JButton("修改");
			JButton findBtn = new JButton("查询");

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
			
			//--------添加企业用电信息--------
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
						JOptionPane.showMessageDialog(null, "请输入企业编号和时间", "提示", JOptionPane.PLAIN_MESSAGE);
					} else {
						try {
							if (findBill(isSql) != 2) {
								JOptionPane.showMessageDialog(null, "企业" + comid + "查表时间为" + time + "已经添加过", "提示",
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
			
			//--------改变企业用电信息--------
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
						JOptionPane.showMessageDialog(null, "请输入企业编号和时间", "提示", JOptionPane.PLAIN_MESSAGE);
					} else {
						try {
							if (findBill(isSql) == 2) {
								JOptionPane.showMessageDialog(null, "企业" + comid + "查表时间为" + time + "还没添加过", "提示",
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
			//--------删除企业用电信息--------
			delBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String delSql = null;
					String comid = text_id.getText();// 企业id
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
					// 判断改时间的bill是否已经有了
					String isSql = "select * from elecbill,combill where elecbill.bill_num=combill.bill_num and ym_data='"
							+ time + "' and co_id='" + comid + "';";
					if (comid.equals("")) {
						JOptionPane.showMessageDialog(null, "请输入企业编号", "提示", JOptionPane.PLAIN_MESSAGE);
					} else {
						try {
							if (findBill(isSql) == 2) {
								JOptionPane.showMessageDialog(null, "企业" + comid + "查表时间为" + time + "的信息不存在", "提示",
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

			JButton asghBtn = new JButton("总电费升序");
			JButton asglBtn = new JButton("谷电量升序");

			JButton deschBtn = new JButton("总电费降序");
			JButton desclBtn = new JButton("总电量降序");

			// 位置和添加
			asghBtn.setBounds(385, 80, 105, 30);
			asglBtn.setBounds(385, 180, 105, 30);
			deschBtn.setBounds(385, 280, 105, 30);
			desclBtn.setBounds(385, 380, 105, 30);

			areaInfo.add(asghBtn);
			areaInfo.add(asglBtn);
			areaInfo.add(deschBtn);
			areaInfo.add(desclBtn);

			showAllBill("");
			
			//按钮展示语句
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

	//	区域统计信息界面
	private class ButtonactionPerformedarea implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JButton btn_elec, btn_cost;
			JTextField text_peak, text_vale, text_csa;
			JTextField text_huse, text_luse, text_aver, text_sum;
			JLabel jl_idfind, jl_datafind;
			JTextField text_find;
			JLabel jl_elec, jl_peak, jl_vale, jl_csa;
			JLabel jl_cost, jl_huse, jl_luse, jl_aver, jl_sum;

			jl_elec = new JLabel("区域用电情况", JLabel.RIGHT);
			btn_elec = new JButton("统计");

			jl_peak = new JLabel("该区域总的峰电量为:", JLabel.RIGHT);
			jl_vale = new JLabel("该区域总的谷电量为:", JLabel.RIGHT);
			jl_csa = new JLabel("峰 谷电量比例:", JLabel.RIGHT);

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

			jl_cost = new JLabel("电费情况:", JLabel.RIGHT);
			btn_cost = new JButton("统计");

			jl_huse = new JLabel("用电最大的企业为:", JLabel.RIGHT);
			jl_luse = new JLabel("用电最小的企业为:", JLabel.RIGHT);
			jl_aver = new JLabel("平均电费为:", JLabel.RIGHT);
			jl_sum = new JLabel("总电费为:", JLabel.RIGHT);

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

			// 右边
			// 根据企业编号查所有时间的用费
			jl_idfind = new JLabel("企业编号:", JLabel.RIGHT);
			text_find = new JTextField(200);
			JButton idfindBtn = new JButton("查询");
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

			// 根据时间查所有企业的用费
			jl_datafind = new JLabel("日期:", JLabel.RIGHT);
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
			JButton datafindBtn = new JButton("查询");

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
