
import java.sql.*;

public class ConnectSql {

	public static void main(String[] args) {
		update();

	}

	public static void update() {
		Connection connection = null;
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=zheng";// 数据源 ！！！注意若出现加载或者连接数据库失败一般是这里出现问题
		String Name = "sa";
		String Pwd = "86657148";
		try {
			Class.forName(driverName);// 加载数据库的驱动类
			connection = DriverManager.getConnection(dbURL, Name, Pwd);// 获取连接
			System.out.println("连接数据库成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("连接失败");
		}
		
		Statement stmt = null;//使用Statement接口运行sql语句
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 增删改操作
		String sql_1 = "Insert INTO STUDENT VALUES('1101','佩奇','男','12','1')";
		int count_1 = 0;
		try {
			count_1 = stmt.executeUpdate(sql_1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 返回值表示增删改几条数据
			// 处理结果
		if (count_1 > 0) {
			System.out.println("更新成功!");
		}
		
		// 查询操作
		String sql_2 = "select * from student";

		// 关闭
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
