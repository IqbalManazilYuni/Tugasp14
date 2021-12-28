
import java.sql.SQLException;

interface Barang {
    void tambahdata() throws SQLException;
    void lihatdata() throws SQLException;
    void hapus();
    void cari() throws SQLException;
    void update() throws SQLException;
}
