package status;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import companyElec.BackgroundPanel;
import companyElec.FramMain;

public class Company {

	static JButton sureCom = new JButton("确定添加");

	// 企业页面的内容
	JLabel idLabel = new JLabel();
	JTextField idText = new JTextField();

	JLabel nameLabel = new JLabel("企业名：");
	static JTextArea nameText = new JTextArea();
	
	JLabel addrLabel = new JLabel("所属企业名：");
	static JTextArea addrText = new JTextArea();

	static JLabel telLabel = new JLabel("手机号：");
	static JTextArea telText = new JTextArea();

	JLabel linkLabel = new JLabel("邮箱：");
	static JTextArea linkText = new JTextArea();

	static long globalID=9981001;
	static Font fnt = new Font("宋体", Font.BOLD, 23);
	
	public Company() {
		FramMain.comJPan.setLayout(null);
	
		//id文字标签
		idLabel.setFont(fnt);
		idLabel.setForeground(Color.white);
		idLabel.setText("用户ID：");
		idLabel.setBounds(80,70,150,30);
		FramMain.comJPan.add(idLabel);
		
		//id文字 文本框
		idText.setFont(fnt);
		globalID++;
		String id=globalID+"";
		idText.setText(id);
		idText.setEditable(false);
		idText.setBounds(185,70,200,30);
		FramMain.comJPan.add(idText);

		//姓名 标签
		nameLabel.setFont(fnt);
		nameLabel.setForeground(Color.white);
		nameLabel.setBounds(85,110,150,30);
		FramMain.comJPan.add(nameLabel);
		
		//姓名 文本框
		nameText.setFont(fnt);
		nameText.setBounds(185,110,200,30);
		FramMain.comJPan.add(nameText);
		
		//密码文字标签
		addrLabel.setFont(fnt);
		addrLabel.setForeground(Color.white);
		addrLabel.setText("密码：");
		addrLabel.setBounds(85,150,150,30);	
		FramMain.comJPan.add(addrLabel);
		
		//密码文字 文本框
		addrText.setFont(fnt);
		addrText.setBounds(185,150,200,30);	
		FramMain.comJPan.add(addrText);
		
		
		//手机号 标签
		telLabel.setFont(fnt);
		telLabel.setForeground(Color.white);
		telLabel.setBounds(75,190,150,30);	
		FramMain.comJPan.add(telLabel);
		
		//手机号 文本框
		telText.setFont(fnt);
		telText.setBounds(185,190,200,30);	
		FramMain.comJPan.add(telText);
		
		
		//企业 标签
		linkLabel.setFont(fnt);
		linkLabel.setForeground(Color.white);
		linkLabel.setBounds(60,230,150,30);	
		FramMain.comJPan.add(linkLabel);
		
		//企业 文本框
		linkText.setFont(fnt);
		linkText.setBounds(185,230,200,30);	
		FramMain.comJPan.add(linkText);

		sureCom.setBounds(200,360,140,40);
		sureCom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				long id=globalID;
				String name=null,addr=null,
						tel=null,link=null;
						
				name=nameText.getText();
				addr=addrText.getText();
				tel=telText.getText();
				link=linkText.getText();

				if(name!=null&&addr!=null&&tel!=null&&link!=null)
				{
					String insertSql="Insert INTO UserInfo VALUES(id,name,psw,tel,cona,mail,type)";
				}
//				JOptionPane.showMessageDialog(null,"添加成功","提示",JOptionPane.PLAIN_MESSAGE);
//				FramMain.comJPan.setVisible(true);
//				FramMain.loginJPan.setVisible(false);
			}
		});
		FramMain.comJPan.add(sureCom);
		FramMain.comJPan.setVisible(true);
		FramMain.eleContainer.add(FramMain.comJPan);
	}
}
