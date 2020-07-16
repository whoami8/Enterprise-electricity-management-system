package companyElec;
import java.awt.*;//awt���ṩ�˻�����java����GUI��ƹ��ߡ��������Components������Container�����ֹ�����Layout Manager
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

	// �������� ������������
	static FramMain elecJFrame = new FramMain();
	static Image img = new ImageIcon("./src/images/bg.jpg").getImage(); 
	public static JPanel loginJPan = new BackgroundPanel(img);//��¼ҳ��
	public static JPanel resJPan = new BackgroundPanel(img);// ע��ҳ��
	public static JPanel comJPan = new BackgroundPanel(img);//��ҵҳ��

	static FramMain roleJFrame = new FramMain();
	public static JPanel perJPan = new BackgroundPanel(img);// �û�ҳ��
	public static JPanel admiJPan = new BackgroundPanel(img);//����Աҳ��
	
	public static JPanel regJPan=new JPanel();//ע��ҳ��
	public static JPanel mainJPan=new JPanel();//������Ҫҳ��
	
	static JLabel title1 = new JLabel("****��ҵ�õ�ϵͳ***",JLabel.CENTER);
	static JLabel title2 = new JLabel("****��ҵ�õ�ϵͳ***",JLabel.CENTER);
	static Font title_fnt = new Font("Serief", Font.BOLD, 35);
	
	// ����
	static Font btn_fnt = new Font("Serief", Font.BOLD, 20);
	
	public static CardLayout prin_card = null;

	static String[] topbtn_menu;//�ײ���ť
	
	static CardLayout card = new CardLayout(5, 10);
	static FlowLayout layout=new FlowLayout();
	static BorderLayout border = new BorderLayout(6, 6);
	public static Container eleContainer = elecJFrame.getContentPane();
	public static Container roleContainer = roleJFrame.getContentPane();
	//----------------------------main-------------------------------------

		public static void main(String[] args) throws IOException, IOException, SQLException {
	//		@SuppressWarnings("unchecked");
			//���������� unchecked ������Ϣ,arrayList
			// ����������
			eleContainer.setLayout(border);
			roleJFrame.setLayout(border);
			//new Creat(); //������
			
			new Login();
			
			//��¼������
			elecJFrame.setVisible(true); // ����JFrameΪ�ɼ���ȱʡΪ���ɼ�
			elecJFrame.setResizable(false);//���ô�С��������
			elecJFrame.setTitle("��ҵ�õ�ϵͳ");
			elecJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			elecJFrame.setSize(500, 500);
			elecJFrame.setLocationRelativeTo(null); // ʹ������ʾ����Ļ����
			elecJFrame.getContentPane().setBackground(Color.pink);// ����JFrame��������屳����ɫ
			
			//�û�����
			roleJFrame.setVisible(false); // ����JFrameΪ�ɼ���ȱʡΪ���ɼ�
			roleJFrame.setResizable(false);//���ô�С��������
			roleJFrame.setTitle("��ҵ�õ�ϵͳ");
			roleJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			roleJFrame.setSize(1200, 720);
			roleJFrame.setLocationRelativeTo(null); // ʹ������ʾ����Ļ����


		}
		
}
