package status;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import companyElec.FramMain;

public class Person extends JFrame {

	// 卡片面板
	private JButton menu_btn_1 = new JButton("个人及所属企业信息");
	private JButton menu_btn_2 = new JButton("所属企业电费信息");
	private JButton menu_btn_3 = new JButton("所属企业缴费信息");

	// 轮播切换页面
	public static JPanel perMsg = new JPanel();
	public static JPanel costMsg = new JPanel();
	public static JPanel payMsg = new JPanel();

	private CardLayout card = null;

	static JTextField text = new JTextField("0");
	static BorderLayout border = new BorderLayout(6, 6);

	public Person(String userid) {
		perMsg.setLayout(null);
		costMsg.setLayout(null);
		payMsg.setLayout(null);
		card = new CardLayout(5, 5);// 卡片布局
		FramMain.perJPan = new JPanel(card);// 轮播的
		// perJPn = new JPanel();//包含按钮

		JPanel menu_button = new JPanel();
		menu_button.setLayout(new GridLayout(1, 3, 13, 10));
		menu_button.setBounds(10, 10, 600, 30);

		// 设置容器中显示的组件
		menu_button.add(menu_btn_1);
		menu_button.add(menu_btn_2);
		menu_button.add(menu_btn_3);

		FramMain.roleContainer.add(menu_button, border.SOUTH);

		perMsg.setBackground(Color.lightGray);
		costMsg.setBackground(Color.GREEN);
		payMsg.setBackground(Color.PINK);
		

		FramMain.perJPan.add(perMsg, "perMsg");
		FramMain.perJPan.add("costMsg", costMsg);
		FramMain.perJPan.add("payMsg", payMsg);
		
		
		//--------展示个人信息和用户所属企业信息--------
		menu_btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JLabel jl_id, jl_co, jl_tel, jl_name, jl_mail;
				JTextField text_id, text_tel, text_name, text_mail;
				JButton btn_tel, btn_name, btn_mail;

				JLabel jl_comid, jl_cona, jl_addr, jl_comtel, jl_link;
				JTextField text_comid, text_cona, text_addr, text_comtel, text_link;

				jl_id = new JLabel("用户id号码:", JLabel.RIGHT);
				jl_name = new JLabel("用户姓名", JLabel.RIGHT);
				jl_tel = new JLabel("联系电话:", JLabel.RIGHT);
				jl_mail = new JLabel("邮箱:", JLabel.RIGHT);

				text_id = new JTextField(230);
				text_id.setEditable(false);
				text_name = new JTextField(230);
				text_tel = new JTextField(230);
				text_mail = new JTextField(230);

				btn_name = new JButton("修改");
				btn_tel = new JButton("修改");
				btn_mail = new JButton("修改");

				text_id.setText(userid);
				String comid = "";

				String userid = text_id.getText();
				//-------修改用户个人信息--------
				btn_name.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String changeText = text_name.getText();
						String changeSql = "update userinfo set user_name='" + changeText + "' where user_id='" + userid
								+ "'";
						try {
							update(changeSql);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});

				btn_tel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String changeText = text_tel.getText();
						String changeSql = "update userinfo set user_tel='" + changeText + "' where user_id='" + userid
								+ "'";
						try {
							update(changeSql);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});

				btn_mail.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String changeText = text_mail.getText();
						String changeSql = "update userinfo set user__name='" + changeText + "' where user_id='"
								+ userid + "'";
						try {
							update(changeSql);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});

				jl_comid = new JLabel("企业id号码:", JLabel.RIGHT);
				jl_cona = new JLabel("企业名字", JLabel.RIGHT);
				jl_addr = new JLabel("企业地址", JLabel.RIGHT);
				jl_comtel = new JLabel("企业联系电话:", JLabel.RIGHT);
				jl_link = new JLabel("企业联系人:", JLabel.RIGHT);

				text_comid = new JTextField(230);
				text_cona = new JTextField(230);
				text_addr = new JTextField(230);
				text_comtel = new JTextField(230);
				text_link = new JTextField(230);

				text_comid.setEditable(false);
				text_cona.setEditable(false);
				text_addr.setEditable(false);
				text_comtel.setEditable(false);
				text_link.setEditable(false);

				
				//-------展示用户个人信息--------
				String id = null;
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
				ResultSet res = null;
				Statement stmt = null;// 使用Statement接口运行sql语句
				try {
					stmt = connection.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String sql = "select * from userinfo,companyinfo where companyinfo.co_id=userinfo.co_id and user_id='"
						+ userid + "';";
				try {
					System.out.println(sql);
					res = (ResultSet) stmt.executeQuery(sql);
					while (res.next()) {
						text_name.setText(res.getString(6));
						text_tel.setText(res.getString(5));
						text_mail.setText(res.getString(7));
						text_comid.setText(res.getString(8));
						text_cona.setText(res.getString(9));
						text_addr.setText(res.getString(10));
						text_comtel.setText(res.getString(11));
						text_link.setText(res.getString(12));
					}
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

				text_id.setBounds(180, 80, 250, 30);
				text_name.setBounds(180, 130, 250, 30);
				text_tel.setBounds(180, 190, 250, 30);
				text_mail.setBounds(180, 250, 250, 30);

				text_comid.setBounds(680, 80, 250, 30);
				text_cona.setBounds(680, 130, 250, 30);
				text_addr.setBounds(680, 190, 250, 30);
				text_comtel.setBounds(680, 250, 250, 30);
				text_link.setBounds(680, 310, 250, 30);

				jl_comid.setBounds(550, 80, 100, 35);
				jl_cona.setBounds(550, 130, 100, 35);
				jl_addr.setBounds(550, 190, 100, 35);
				jl_comtel.setBounds(550, 250, 100, 35);
				jl_link.setBounds(550, 310, 100, 35);

				jl_id.setBounds(50, 80, 100, 35);
				jl_name.setBounds(50, 130, 100, 35);
				jl_tel.setBounds(50, 190, 100, 35);
				jl_mail.setBounds(50, 250, 100, 35);

				btn_name.setBounds(450, 130, 65, 30);
				btn_tel.setBounds(450, 190, 65, 30);
				btn_mail.setBounds(450, 250, 65, 30);

				perMsg.add(text_id);
				perMsg.add(text_name);
				perMsg.add(text_tel);
				perMsg.add(text_mail);

				perMsg.add(text_comid);
				perMsg.add(text_cona);
				perMsg.add(text_addr);
				perMsg.add(text_comtel);
				perMsg.add(text_link);

				perMsg.add(jl_comid);
				perMsg.add(jl_cona);
				perMsg.add(jl_addr);
				perMsg.add(jl_comtel);
				perMsg.add(jl_link);

				perMsg.add(jl_id);
				perMsg.add(jl_name);
				perMsg.add(jl_tel);
				perMsg.add(jl_mail);

				perMsg.add(btn_name);
				perMsg.add(btn_tel);
				perMsg.add(btn_mail);

				card.show(FramMain.perJPan, "perMsg");
			}
		});
		//-------用户操作的所属企业用电信息--------
		menu_btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel jl_comid = new JLabel("企业id号码:", JLabel.RIGHT);
				JTextField text_comid = new JTextField(230);

				JLabel jl_time = new JLabel("查表时间:", JLabel.RIGHT);
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
				String com = "";
				try {
					com = findbill("select co_id from userinfo where user_id='" + userid + "'");
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				text_comid.setText(com);

				JButton btn_pay = new JButton("缴费");

				jl_comid.setBounds(50, 80, 80, 35);
				jl_time.setBounds(50, 130, 80, 35);
				btn_pay.setBounds(70, 180, 65, 40);

				text_comid.setBounds(140, 80, 200, 35);

				xl_year.setBounds(140, 130, 80, 30);
				xl_month.setBounds(230, 130, 60, 30);
				xl_day.setBounds(300, 130, 60, 30);

				costMsg.add(jl_comid);
				costMsg.add(jl_time);
				costMsg.add(btn_pay);
				costMsg.add(text_comid);
				costMsg.add(xl_year);
				costMsg.add(xl_month);
				costMsg.add(xl_day);
				String sql = "select elecbill.bill_num,elec_cost,ym_data,user_id,is_pay  from elecbill,combill,operatelog  where elecbill.bill_num=combill.bill_num  and operatelog.bill_num=elecbill.bill_num and user_id='"
						+ userid + "'";
				btn_pay.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String comid = text_comid.getText();// 企业id

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
						String findsql = "select bill_num from time_billl where co_id='" + comid + "' and ym_data='"
								+ time + "'";

						try {
							String billnum = findbill(findsql);
							//System.out.println("billnum--" + billnum);

							String addlog = "insert into operatelog values('" + billnum + "','" + userid + "') ";
							//System.out.println("addlog--" + addlog);

							String updatecom = "update combill set is_pay='Y' where bill_num='" + billnum + "'";
							//System.out.println("updatecom--" + updatecom);
							update(addlog);
							update(updatecom);

							showuser(sql);
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
				});

				try {

					showuser(sql);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				card.show(FramMain.perJPan, "costMsg");
			}
		});
		//-------展示企业用电信息--------
		menu_btn_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton unpay_Btn = new JButton("查看未缴费账单");
				JButton haspay_Btn = new JButton("查看已缴费账单");
				JButton user_Btn = new JButton("按缴费人员查询");
				JButton time_Btn = new JButton("统计年度用电");
				JButton all_Btn = new JButton("全部");

				JLabel jl_userid = new JLabel("请人员id:", JLabel.RIGHT);
				JTextField text_userid = new JTextField(200);

				JTextField text_year = new JTextField(200);

				JLabel jl_time = new JLabel("请选择年份", JLabel.RIGHT);
				JComboBox xl_year = new JComboBox();
				for (int i = 2010; i < 2030; i++) {
					xl_year.addItem(i);
				}
				
				time_Btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int year = (int) xl_year.getSelectedItem();
						try {
							String com = "";//企业ID
							try {
								com = findbill("select co_id from userinfo where user_id='" + userid + "'");
							} catch (SQLException e3) {
								// TODO Auto-generated catch block
								e3.printStackTrace();
							}
							String yearsum = findbill(
									" select sum(elec_cost) from elecbill,combill where co_id='"+com+"' and elecbill.bill_num=combill.bill_num and elecbill.ym_data like'%" + year + "%'");
							System.out.println(yearsum);
							text_year.setText(yearsum);
							payMsg.add(text_year);

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				});

				unpay_Btn.setBounds(270, 80, 130, 40);
				haspay_Btn.setBounds(270, 140, 130, 40);

				jl_userid.setBounds(10, 200, 100, 40);
				text_userid.setBounds(120, 200, 120, 40);
				user_Btn.setBounds(270, 200, 130, 40);

				all_Btn.setBounds(270, 260, 130, 40);

				jl_time.setBounds(10, 350, 100, 40);
				xl_year.setBounds(130, 350, 80, 40);
				time_Btn.setBounds(270, 350, 130, 40);
				text_year.setBounds(270, 410, 130, 40);
				

				//-------分类查看信息-------
				//-------查看未交费的信息-------		
				unpay_Btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							String com = "";//企业ID
							try {
								com = findbill("select co_id from userinfo where user_id='" + userid + "'");
							} catch (SQLException e3) {
								// TODO Auto-generated catch block
								e3.printStackTrace();
							}
							showpay(" and combill.co_id='"+com+"' and is_pay='N'",userid);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				//-------查看已缴费信息-------
				haspay_Btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							String com = "";//企业ID
							try {
								com = findbill("select co_id from userinfo where user_id='" + userid + "'");
							} catch (SQLException e3) {
								// TODO Auto-generated catch block
								e3.printStackTrace();
							}
							showpay(" and combill.co_id='"+com+"' and is_pay='Y'",userid);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				//-------根据用户id操作查找信息-------
				user_Btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String com = "";//企业ID
						try {
							com = findbill("select co_id from userinfo where user_id='" + userid + "'");
						} catch (SQLException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
						try {
							String uid = text_userid.getText();
							String addSql = " and combill.co_id='"+com+"'and operatelog.user_id='" + uid + "'";
							showpay(addSql,userid);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				//-------查看所有信息-------
				all_Btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String com = "";//企业ID
						try {
							com = findbill("select co_id from userinfo where user_id='" + userid + "'");
						} catch (SQLException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
						try {
							showpay(" and combill.co_id='"+com+"'",userid);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});

				payMsg.add(unpay_Btn);
				payMsg.add(haspay_Btn);
				payMsg.add(user_Btn);
				payMsg.add(all_Btn);
				payMsg.add(jl_userid);
				payMsg.add(text_userid);
				
				payMsg.add(jl_time);
				payMsg.add(time_Btn);
				payMsg.add(xl_year);
				String com = "";//企业ID
				try {
					com = findbill("select co_id from userinfo where user_id='" + userid + "'");
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				try {
					//-------展示信息-------
					showpay(" and combill.co_id='"+com+"'",userid);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				card.show(FramMain.perJPan, "payMsg");
			}
		});
		FramMain.perJPan.setVisible(true);
		FramMain.roleContainer.add(FramMain.perJPan, border.CENTER);
	}
	//-------更新信息方法-------
	public void update(String updateSql) throws SQLException {
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
	//-------展示企业用电量情况信息-------
	public void showpay(String addsql,String userid) throws SQLException {

		String[] columnNames = { "账单编号", "谷电量", "峰电量", "电费", "时间", "缴费人员", "是否缴清账单" };
		String[][] tableValues = {};
		DefaultTableModel tableModel = new DefaultTableModel(tableValues, columnNames);
		JTable table = new JTable(tableModel);
		@SuppressWarnings("unused")
		JScrollPane jscrollpane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		int rowCount = 0;
		Connection connection = null;
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=zheng";
		String Name = "sa";
		String Pwd = "86657148";
		String com = "";//企业ID
		
		String sql = "select distinct elecbill.bill_num,elec_high,elec_low,elec_cost, ym_data,userinfo.user_id,is_pay from elecbill,combill,operatelog,userinfo where elecbill.bill_num=combill.bill_num and userinfo.user_id='"+userid+"' ";
		sql = sql + addsql;
		System.out.println("findsql--"+sql);
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
					String billnum = allres.getString(1);
					String elecow = allres.getString(2);
					String elechigh = allres.getString(3);
					String eleccost = allres.getString(4);
					String time = allres.getString(5);
					String payuesr = allres.getString(6);
					String iapsy = allres.getString(7);

					String[] rowValues = { billnum, elecow, elechigh, eleccost, time, payuesr, iapsy };
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
			payMsg.add(jscrollpane);

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
	//-------查找账单编号方法-------
	public String findbill(String sql) throws SQLException {
		Connection connection = null;
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=zheng";// 数据源 // ！！！注意若出现加载或者连接数据库失败一般是这里出现问题
		String Name = "sa";
		String Pwd = "86657148";
		String resbill = "";
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
					resbill = allres.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		return resbill;

	}

	//-------展示用户操作的账单信息-------
	public void showuser(String usersql) throws SQLException {

		String[] columnNames = { "账单编号", "电费", "时间", "缴费人员", "是否缴清账单" };
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
		String sql = "select * from elecbill,combill,operatelog where elecbill.bill_num=combill.bill_num and operatelog.bill_num=elecbill.bill_num";
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
			allres = (ResultSet) stmt.executeQuery(usersql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (allres != null) {

			try {
				while (allres.next()) {
					String billnum = allres.getString(1);
					String elecost = allres.getString(2);
					String time = allres.getString(3);
					String userid = allres.getString(4);
					String ispay = allres.getString(5);

					String[] rowValues = { billnum, elecost, time, userid, ispay };
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
			costMsg.add(jscrollpane);

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
