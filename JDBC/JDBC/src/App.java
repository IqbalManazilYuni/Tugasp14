import com.mysql.cj.protocol.Resultset;
import java.util.*;
import java.util.Date;

import java.io.*;
import java.sql.*;

public class App {
    static Connection conn;
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        String pilih;
        boolean kondisi = true;

        String url = "jdbc:mysql://localhost:3306/penjualan";
        String user = "root";
        String password = "";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("CONNECTED");
            while(kondisi){
                Date date = new Date();
                String str = String.format("Tanggal dan waktu : %tc", date);
                System.out.println(str);
                System.out.println("   DATA BASE PENJUALAN  ");
                System.out.println("1. Input Data ");
                System.out.println("2. Lihat Data ");
                System.out.println("3. Hapus Data ");
                System.out.println("4. Cari Data");
                System.out.println(" Masukkan Pilihan : ");
                pilih = input.next();
                switch(pilih){
                    case "1":
                    break;
                    case "2":
                    break;
                    case "3":
                    break;
                    case "4":
                    break;
                    default:
                    System.err.println("\n Menu Tidak Tersedia ");
                }
                System.out.println("\n Keluar Dari Program [t/f]? : ");
                pilih = input.next();
                kondisi = pilih.equalsIgnoreCase("f");
            }
            System.out.println("PROGRAM SELESAI");
        }
        catch(ClassNotFoundException ex){
            System.err.println("Driver ERROR");
            System.exit(0);
        }
        catch(SQLException e){
            System.err.println("DISCONNECTED");
        }
    }
}

