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

	static JButton sureCom = new JButton("ȷ�����");

	// ��ҵҳ�������
	JLabel idLabel = new JLabel();
	JTextField idText = new JTextField();

	JLabel nameLabel = new JLabel("��ҵ����");
	static JTextArea nameText = new JTextArea();
	
	JLabel addrLabel = new JLabel("������ҵ����");
	static JTextArea addrText = new JTextArea();

	static JLabel telLabel = new JLabel("�ֻ��ţ�");
	static JTextArea telText = new JTextArea();

	JLabel linkLabel = new JLabel("���䣺");
	static JTextArea linkText = new JTextArea();

	static long globalID=9981001;
	static Font fnt = new Font("����", Font.BOLD, 23);
	
	public Company() {
		FramMain.comJPan.setLayout(null);
	
		//id���ֱ�ǩ
		idLabel.setFont(fnt);
		idLabel.setForeground(Color.white);
		idLabel.setText("�û�ID��");
		idLabel.setBounds(80,70,150,30);
		FramMain.comJPan.add(idLabel);
		
		//id���� �ı���
		idText.setFont(fnt);
		globalID++;
		String id=globalID+"";
		idText.setText(id);
		idText.setEditable(false);
		idText.setBounds(185,70,200,30);
		FramMain.comJPan.add(idText);

		//���� ��ǩ
		nameLabel.setFont(fnt);
		nameLabel.setForeground(Color.white);
		nameLabel.setBounds(85,110,150,30);
		FramMain.comJPan.add(nameLabel);
		
		//���� �ı���
		nameText.setFont(fnt);
		nameText.setBounds(185,110,200,30);
		FramMain.comJPan.add(nameText);
		
		//�������ֱ�ǩ
		addrLabel.setFont(fnt);
		addrLabel.setForeground(Color.white);
		addrLabel.setText("���룺");
		addrLabel.setBounds(85,150,150,30);	
		FramMain.comJPan.add(addrLabel);
		
		//�������� �ı���
		addrText.setFont(fnt);
		addrText.setBounds(185,150,200,30);	
		FramMain.comJPan.add(addrText);
		
		
		//�ֻ��� ��ǩ
		telLabel.setFont(fnt);
		telLabel.setForeground(Color.white);
		telLabel.setBounds(75,190,150,30);	
		FramMain.comJPan.add(telLabel);
		
		//�ֻ��� �ı���
		telText.setFont(fnt);
		telText.setBounds(185,190,200,30);	
		FramMain.comJPan.add(telText);
		
		
		//��ҵ ��ǩ
		linkLabel.setFont(fnt);
		linkLabel.setForeground(Color.white);
		linkLabel.setBounds(60,230,150,30);	
		FramMain.comJPan.add(linkLabel);
		
		//��ҵ �ı���
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
//				JOptionPane.showMessageDialog(null,"��ӳɹ�","��ʾ",JOptionPane.PLAIN_MESSAGE);
//				FramMain.comJPan.setVisible(true);
//				FramMain.loginJPan.setVisible(false);
			}
		});
		FramMain.comJPan.add(sureCom);
		FramMain.comJPan.setVisible(true);
		FramMain.eleContainer.add(FramMain.comJPan);
	}
}
