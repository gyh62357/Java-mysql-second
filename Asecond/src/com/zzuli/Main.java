/**
 * 
 */
package com.zzuli;

import java.sql.SQLException;
import java.util.Scanner;

import com.zzuli.Example.user;


/**
 * 创建人：Administrator
 * 创建时间：下午3:04:47
 * 所在项目：Second
 * 所在包：com.zzuli
 * @version
 */
public class Main {
	public static void main(String args[]) throws SQLException{

		Example cd=new Example();

        user user=cd.new user();

        cd.ConnectMysql();
        
        System.out.print("请输入id：");
        
        Scanner input_id = new Scanner(System.in);
        
        int id = input_id.nextInt();

        user.setId(id);//每次测试运行都要改变该数字
        
        System.out.print("请输入姓名：");
        
        Scanner input_name = new Scanner(System.in);
        
        String name = input_name.next();
        
        user.setName(name);
        
        System.out.print("请输入密码：");
        
        Scanner input_password = new Scanner(System.in);
        
        String password = input_password.next();

        user.setPassword(password);
        
        System.out.print("邮箱：");
        
        Scanner input_email = new Scanner(System.in);
        
        String email = input_email.next();

        user.setEmail(email);

        cd.upsql="update user set username='郭迎辉' where userid=5";

        cd.delsql="delete from user where userid=100";

        cd.InsertSql(user);

        cd.DeletSql(cd.delsql);

        cd.UpdateSql(cd.upsql);

        cd.SelectSql(cd.sql);

        cd.CutConnection(cd.conn);

        }
}
