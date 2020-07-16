package companyElec;
import java.awt.*;//awt包提供了基本的java程序GUI设计工具。包括组件Components，容器Container，布局管理器Layout Manager
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import status.Company;
import status.Person;
import creatTable.Creat;;

public class FramMain  extends JFrame  {

	// 创建对象 创建顶层容器
	static FramMain elecJFrame = new FramMain();
	static Image img = new ImageIcon("./src/images/bg.jpg").getImage(); 
	public static JPanel loginJPan = new BackgroundPanel(img);//登录页面
	public static JPanel resJPan = new BackgroundPanel(img);// 注册页面
	public static JPanel comJPan = new BackgroundPanel(img);//企业页面

	static FramMain roleJFrame = new FramMain();
	public static JPanel perJPan = new BackgroundPanel(img);// 用户页面
	public static JPanel admiJPan = new BackgroundPanel(img);//管理员页面
	
	public static JPanel regJPan=new JPanel();//注册页面
	public static JPanel mainJPan=new JPanel();//中心主要页面
	
	static JLabel title1 = new JLabel("****企业用电系统***",JLabel.CENTER);
	static JLabel title2 = new JLabel("****企业用电系统***",JLabel.CENTER);
	static Font title_fnt = new Font("Serief", Font.BOLD, 35);
	
	// 字体
	static Font btn_fnt = new Font("Serief", Font.BOLD, 20);
	
	public static CardLayout prin_card = null;

	static String[] topbtn_menu;//底部按钮
	
	static CardLayout card = new CardLayout(5, 10);
	static FlowLayout layout=new FlowLayout();
	static BorderLayout border = new BorderLayout(6, 6);
	public static Container eleContainer = elecJFrame.getContentPane();
	public static Container roleContainer = roleJFrame.getContentPane();
	//----------------------------main-------------------------------------

		public static void main(String[] args) throws IOException, IOException, SQLException {
	//		@SuppressWarnings("unchecked");
			//编译器忽略 unchecked 警告信息,arrayList
			// 主界面设置
			eleContainer.setLayout(border);
			roleJFrame.setLayout(border);
			//new Creat(); //创建表
			
			new Login();
			
			//登录祖册界面
			elecJFrame.setVisible(true); // 设置JFrame为可见，缺省为不可见
			elecJFrame.setResizable(false);//设置大小不可设置
			elecJFrame.setTitle("企业用电系统");
			elecJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			elecJFrame.setSize(500, 500);
			elecJFrame.setLocationRelativeTo(null); // 使窗口显示在屏幕中央
			elecJFrame.getContentPane().setBackground(Color.pink);// 设置JFrame的内容面板背景颜色
			
			//用户界面
			roleJFrame.setVisible(false); // 设置JFrame为可见，缺省为不可见
			roleJFrame.setResizable(false);//设置大小不可设置
			roleJFrame.setTitle("企业用电系统");
			roleJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			roleJFrame.setSize(1200, 720);
			roleJFrame.setLocationRelativeTo(null); // 使窗口显示在屏幕中央


		}
		
}
