import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.StatementWrapper;
import com.mysql.cj.protocol.Resultset;

public class Transaksi implements Barang {
    static Connection conn;
    String url = "jdbc:mysql://localhost:3306/penjualan";
    String user = "root";
    String password = "";
    Scanner scanner = new Scanner(System.in);
    Long subtotal,diskon,totalbayar;
    //metode untuk mencari data di database dengan memasukkan nofaktur
    @Override
    public void cari() throws SQLException {
        String chap4 = "\n Cari Data\t";
        System.out.println(chap4.toUpperCase());
        try{
            System.out.print("Masukkan NoFaktur : ");
            String key = scanner.nextLine();
            String sql = "SELECT * FROM histori WHERE nofaktur LIKE '%"+key+"%'";
            conn=DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()){
                System.out.print("  Barang ke : ");
                System.out.println(result.getInt("no"));
                System.out.print("  Nama Barang : ");
                System.out.println(result.getString("namabarang"));
                System.out.print("  Nofaktur Barang : ");
                System.out.println(result.getString("nofaktur"));
                System.out.print("  Harga Barang : ");
                System.out.println(result.getInt("harga"));
                System.out.print("  Jumlah Pembelian Barang : ");
                System.out.println(result.getInt("jumlah"));
                System.out.print("  Subtotal harga Barang : ");
                System.out.println(result.getInt("subtotal"));
                System.out.print("  Harga Diskon Barang : ");
                System.out.println(result.getInt("diskon"));
                System.out.print("  Total Bayar Barang : ");
                System.out.println(result.getInt("totalbayar"));
            }
            System.out.println("data tidak ada");
        }
        catch(SQLException ex ){
            System.out.println("Error");
        }
    }
    @Override
    public void hapus() {
        String chap3 = "\n hapus data\t";
        System.out.println(chap3.toUpperCase());
        try{
            lihatdata();
            System.out.println("Cari no data yang akan dihapus : ");
            Integer nomor = Integer.parseInt(scanner.nextLine());
            String sql = "DELETE FROM histori WHERE no = "+nomor;
            conn=DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            if(statement.executeUpdate(sql) > 0){
                System.out.println("DATA BERHASIL TERHAPUS");
            }
            else{
                System.out.println("Data tidak berhasil di hapus");
            }
        }
        catch(SQLException ex){
            System.err.println("ERROR");
        }
    }

    @Override
    public void lihatdata() throws SQLException {
        String chap2 = "\n tampilkan data\t";
        System.out.println(chap2.toUpperCase());
        try{
            String sql = "SELECT * FROM histori";
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()){
                System.out.print("  Barang ke : ");
                System.out.println(result.getInt("no"));
                System.out.print("  Nama Barang : ");
                System.out.println(result.getString("namabarang"));
                System.out.print("  Nofaktur Barang : ");
                System.out.println(result.getString("nofaktur"));
                System.out.print("  Harga Barang : ");
                System.out.println(result.getInt("harga"));
                System.out.print("  Jumlah Pembelian Barang : ");
                System.out.println(result.getInt("jumlah"));
                System.out.print("  Subtotal harga Barang : ");
                System.out.println(result.getInt("subtotal"));
                System.out.print("  Harga Diskon Barang : ");
                System.out.println(result.getInt("diskon"));
                System.out.print("  Total Bayar Barang : ");
                System.out.println(result.getInt("totalbayar"));
                System.out.println("");
            }
        }
        catch(SQLException ex ){
            System.out.println("Error");
        }    
    }

    @Override
    public void tambahdata() throws SQLException {
       String chap1 = "\n tambah data\t";
       System.out.println(chap1.toUpperCase());
       boolean kondisi = true;
       do{
        try{
            System.out.println("Nama Pegawai : Arif Rahmat");
            System.out.print("Masukkan Nama Barang :  ");
            String namabarang = scanner.nextLine();
            System.out.print("Masukkan NoFaktur : ");
            String nofaktur = scanner.nextLine();
            System.out.print("Masukkan Nomor Barang ke : ");
            Integer nomor = scanner.nextInt();
            System.out.print("Harga Barang : ");
            Long biaya = scanner.nextLong();
            System.out.print("Jumlah Barang : ");
            Integer jumlah = scanner.nextInt();
            subtotal = biaya*jumlah;
            System.out.println("Subtotal : "+subtotal);
            if(subtotal > 10000000){
                diskon = subtotal*7/100;
                System.out.println("Mendapatkan Potongan Harga : "+diskon);
            }
            else if(subtotal > 7000000 && subtotal <= 10000000 ){
                diskon = subtotal*5/100;
                System.out.println("Mendapatkan Potongan Harga Menjadi : "+diskon);
            }
            else if(subtotal > 1000000 && subtotal <= 7000000 ){
                diskon = subtotal*3/100;
                System.out.println("Mendapatkan Potongan Harga : "+diskon);
            }
            else if(subtotal > 500000 && subtotal <= 1000000){
                diskon = subtotal*2/100;
                System.out.println("Mendapatkan Potongan Harga : "+diskon);
            }
            else{
                diskon = (long)(0);
                System.out.println("Maaf Anda tidak Mendapatkan Potongan harga");
            }
            totalbayar = subtotal - diskon;
            System.out.println("Harga yang harus dibayar : "+totalbayar);
            String sql = "INSERT INTO histori (no, namabarang, nofaktur, harga, jumlah, subtotal, diskon, totalbayar) VALUES ('"+nomor+"','"+namabarang+"','"+nofaktur+"','"+biaya+"','"+jumlah+"','"+subtotal+"','"+diskon+"','"+totalbayar+"')";
            conn = DriverManager.getConnection(url,"root","");
            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Data Berhasil Dinput");
            kondisi = false;
        }
        catch(InputMismatchException e){
            System.err.println("Input lagi");
            break;
        }
        catch(SQLException e){
            System.err.println("Terjadi Kesalahan");
            break;
        }
     }while(kondisi);
    }
    @Override
    public void update() throws SQLException {
        String chap5 = "\n ubah data\t";
        System.out.println(chap5.toUpperCase());
        try{
            lihatdata();
            System.out.print("Masukkan Nomor data yang akan diubah : ");
            Integer no = Integer.parseInt(scanner.nextLine());
            String sql = "SELECT * FROM histori WHERE no = "+no;
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if(result.next())
            {
                System.out.print("Nama Barang ["+result.getString("namabarang")+"] \t : ");
                String namabarang = scanner.nextLine();
                sql = "UPDATE histori SET namabarang = '"+namabarang+"' WHERE no = '"+no+"'";

                if(statement.executeUpdate(sql)>0){
                    System.out.println("DATA BERHASIL DI UBAH");
                }
            }
            statement.close();
        }
        catch(SQLException e){
            System.err.println("Terjadi Kesalahan");
            System.err.println(e.getMessage());
        }
    }
    
}
