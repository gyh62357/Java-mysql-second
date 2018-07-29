/**
 * 
 */
package com.zzuli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * 创建人：Administrator
 * 创建时间：下午3:04:17
 * 所在项目：Second
 * 所在包：com.zzuli
 * @version
 */
public class Example {
	String drivename="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost/shuishengmu?useUnicode=true&characterEncoding=UTF-8";
    String user="root";
	String password="123456";
	String insql;
	String upsql;
	String delsql;
	String sql="select * from user";
	//String name;
	Connection conn;
	ResultSet rs=null;
	public Connection ConnectMysql(){
		try{
			 Class.forName(drivename);
			 conn = (Connection) DriverManager.getConnection(url, user, password);
			 if (!conn.isClosed()) {
				 System.out.println("Succeeded connecting to the Database!");
			 } else {
				 System.out.println("Falled connecting to the Database!");
			 }
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }
		 return conn;
	 }
	 public void CutConnection(Connection conn) throws SQLException{
		 try{
			 if(rs!=null);
			 if(conn!=null);
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 rs.close();
			 conn.close();
		 }
	 }
	 class user{//内部类，其字段对应用来存放、提取数据库中的数据
		 int userid;
		 String username="";
		 String password="";
		 String email="";
		 //通过set方法，往类的实例里“存放”数据
         //通过get方法，从类的实例里“获得”数据，然后再通过插入数据库
		 public void setId(int userid){
			 this.userid=userid;
		 }
		 public void setName(String username){
			 this.username=username;
         }
         public void setPassword(String password){
             this.password=password;
         }
         public void setEmail(String email){
             this.email=email;
         }
         public Integer getId(){
             return userid;
         }
         public String getName(){
             return username;
         }
         public String getPassword(){
             return password;
         }
         public String getEmail(){
             return email;
         }
	 }
	 //插入、删除、更新的方法是一样的，不一样的是数据库参数
	 public boolean InsertSql(user user){
		 try{
			 insql="insert into user(userid,username,password,email) values(?,?,?,?)";
			 PreparedStatement ps=conn.prepareStatement(insql);
			 ps.setInt(1, user.getId());
			 ps.setString(2, user.getName());
			 ps.setString(3, user.getPassword());
             ps.setString(4, user.getEmail());
             int result=ps.executeUpdate();//ps.executeUpdate();无法判断是否已经插入
             if(result>0)
            	 return true;
	             }catch(Exception e){
	                 e.printStackTrace();
	             }
	             return false;
	         }
	 //与其他操作相比较，查询语句在查询后需要一个查询结果集（ResultSet）来保存查询结果
	 public void SelectSql(String sql){
		 try{
			 Statement statement=conn.createStatement();
             rs=statement.executeQuery(sql);
             System.out.println("编号"+"\t"+"姓名"+"\t"+"密码"+"\t"+"邮箱");
             while(rs.next()){
            	 System.out.println(rs.getString("userid")+"\t"+
            			 			rs.getString("username")+"\t"+
            			 			rs.getString("password")+"\t"+
            			 			rs.getString("email"));
             }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 public boolean UpdateSql(String upsql){
		 try {
			 PreparedStatement ps = conn.prepareStatement(upsql);
			 int result=ps.executeUpdate();//ps.executeUpdate();无法判断是否已经插入
			 if(result>0)
				 return true;
		 } catch (SQLException ex) {
			 Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
		 }
		 return false;
	 }
	 public boolean DeletSql(String delsql){
		 try {
			 PreparedStatement ps = conn.prepareStatement(upsql);
			 int result=ps.executeUpdate(delsql);
			 if(result>0)
				 return true;
		 } catch (SQLException ex) {
			 Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
		 }
		 return false;
	 }

}
