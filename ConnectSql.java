
import java.sql.*;

public class ConnectSql {

	public static void main(String[] args) {
		update();

	}

	public static void update() {
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
		
		Statement stmt = null;//ʹ��Statement�ӿ�����sql���
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// ��ɾ�Ĳ���
		String sql_1 = "Insert INTO STUDENT VALUES('1101','����','��','12','1')";
		int count_1 = 0;
		try {
			count_1 = stmt.executeUpdate(sql_1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // ����ֵ��ʾ��ɾ�ļ�������
			// ������
		if (count_1 > 0) {
			System.out.println("���³ɹ�!");
		}
		
		// ��ѯ����
		String sql_2 = "select * from student";

		// �ر�
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
