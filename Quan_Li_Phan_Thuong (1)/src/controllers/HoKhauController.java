/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.so_ho_khau;
import services.MysqlConnection;

public class HoKhauController {
    public static List<so_ho_khau> get_hokhau() throws SQLException, ClassNotFoundException{
        List<so_ho_khau> l_hokhau = new ArrayList<>();
        Connection connection = MysqlConnection.getMysqlConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM so_ho_khau");
        while (rs.next()) {                
            so_ho_khau hokhau = new so_ho_khau(rs.getInt(1),rs.getString(2),rs.getString(3));
            l_hokhau.add(hokhau);
        }
        connection.close();
        return l_hokhau;
    }
    public static List<so_ho_khau> get_hokhau(String s) throws SQLException, ClassNotFoundException{
        List<so_ho_khau> l_hokhau = new ArrayList<>();
        Connection connection = MysqlConnection.getMysqlConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM so_ho_khau where ID like '%"+s+"%'");
        while (rs.next()) {                
            so_ho_khau hokhau = new so_ho_khau(rs.getInt(1),rs.getString(2),rs.getString(3));
            l_hokhau.add(hokhau);
        }
        connection.close();
        return l_hokhau;
    }
    public static int getslhokhau() throws SQLException, ClassNotFoundException{
        int sl = 0;
        Connection connection = MysqlConnection.getMysqlConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT count(ID) FROM so_ho_khau");
        while (rs.next()) {                
            sl = rs.getInt(1);
        }
        return sl;
    }
    public static void addHoKhau(so_ho_khau hokhau) throws SQLException, ClassNotFoundException{
        String sqlID = "select id from so_ho_khau ORDER BY id desc limit 1";
        int id = 0;
        Connection connection = MysqlConnection.getMysqlConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sqlID);
        while (rs.next()) {                
            id = rs.getInt(1);
        }
        id+=1;
        hokhau.setID(id);
        String sql = "insert into so_ho_khau(ID,TenChuHo,DiaChi) values "
        + "('"+hokhau.getID()+"','"+hokhau.getTenChuHo()+"','"+hokhau.getDiaChi()+"')";
        st.executeUpdate(sql);
    }
    public static void updateHoKhau(so_ho_khau hokhau) throws SQLException, ClassNotFoundException{
        String sql = "update so_ho_khau set TenChuHo='"+hokhau.getTenChuHo()+"',"
        + "DiaChi='"+hokhau.getDiaChi()+"' where ID = '"+hokhau.getID()+"'";
        Connection connection = MysqlConnection.getMysqlConnection();
        Statement st = connection.createStatement();
        st.executeUpdate(sql);
    }
    public static void deleteHoKhau(int id) throws SQLException, ClassNotFoundException{
        String sql = "delete from so_ho_khau where ID = "+id+"";
        Connection connection = MysqlConnection.getMysqlConnection();
        Statement st = connection.createStatement();
        st.executeUpdate(sql);
    }
}
