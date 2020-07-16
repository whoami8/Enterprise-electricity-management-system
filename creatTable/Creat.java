package creatTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Creat {
	public Creat() throws SQLException {
		Connection connection = null;
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL���ݿ�����
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=zheng";// ����Դ ������ע�������ּ��ػ����������ݿ�ʧ��һ���������������
		String Name = "sa";
		String Pwd = "86657148";
		try {
			Class.forName(driverName);// �������ݿ��������
			connection = DriverManager.getConnection(dbURL, Name, Pwd);// ��ȡ����
			System.out.println("�������ݿ�ɹ�");
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
		String companyinfo = "create table companyinfo(co_id bigint not null identity(881001,1),co_name varchar(100),co_addr varchar(200),co_tel varchar(30),co_link varchar(30),primary key(co_id));";
		String userinfo = "create table userinfo(user_type varchar(30),user_id bigint unique not null identity(991001,1),co_id bigint,user_psw varchar(30),user_tel varchar(30),user_name varchar(100),user_mail varchar(30),foreign key(co_id) references companyinfo(co_id));";
		String combill = "create table combill(co_id bigint not null,bill_num bigint not null,primary key(co_id,bill_num))";
		String elecbill = "create table elecbill(bill_num bigint not null identity(771001,1),elec_low float,elec_high float,elec_sum float,pay_low float,pay_high float,elec_cost float,ym_data smalldatetime,primary key(bill_num));";
		String operatelog = "create table operatelog(bill_num bigint,user_id bigintprimary key(bill_num));";
		String priceinfo = "create table priceinfo(pc_low float,pc_high float);";


		String[] creatTable = new String[6];

		creatTable[0] = combill;
		creatTable[1] = elecbill;
		creatTable[2] = operatelog;
		creatTable[3] = priceinfo;
		creatTable[4] = companyinfo;
		creatTable[5] = userinfo;
		
		int count = 0;
		for (int i = 0; i < 6; i++) {
			try {
				count = stmt.executeUpdate(creatTable[i]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // ����ֵ��ʾ��ɾ�ļ�������
				// ������
			if (count > 0) {
				System.out.println("���³ɹ�!");
			}
		}
		//�ر����ݿ�
		connection.close();
		stmt.close();
		
	}
}
