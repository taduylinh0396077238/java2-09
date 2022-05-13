package Java2_09;

import java.sql.*;

public class JdbcPreparedStatementTest {
    public static void main(String[] args) {
        try (

                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookshop", "root", "");

                // Hai câu lệnh chuẩn bị, một cho INSERT và một cho SELECT
                PreparedStatement pstmt = conn.prepareStatement(
                        "insert into books values (?,?,?,?,?) "); // Năm tham số từ 1 đến 5
                PreparedStatement pttmtSelect = conn.prepareStatement("select * from books ");
                ) {
//             pstmt.setInt(1,7001); // Đặt giá trị cho các tham số từ 1 đến 5
//            pstmt.setString(2,"Duy Linh");
//            pstmt.setString(3, "QUÝ ĐƠ");
//            pstmt.setDouble(4,88.88);
//            pstmt.setInt(5,8);
//            int rowInserted = pstmt.executeUpdate(); // thực thi câu lệnh
//            System.out.println(rowInserted + "rows affected");

            conn.setAutoCommit(false);
//            Tắt tính năng tự động cam kết

            pstmt.setInt(1, 8003);  // Set values for parameters 1 to 5
            pstmt.setString(2, "Java 123");
            pstmt.setString(3, "Kevin Jones");
            pstmt.setDouble(4, 12.34);
            pstmt.setInt(5, 88);
            pstmt.addBatch(); //thêm câu lệnh để xử lý hàng loạt

            pstmt.setInt(1,8004);
            pstmt.setString(2,"Java 456");
            // Không thay đổi giá trị cho các tham số 3 đến 5
            pstmt.addBatch(); //thêm câu lệnh để xử lý hàng loạt

            int [] returnCodes = pstmt.executeBatch();
            // executeBatch () trả về một mảng int, giữ các mã trả về của tất cả các câu lệnh
            


            // Đưa ra một SELECT để kiểm tra các thay đổi
            ResultSet rset = pttmtSelect.executeQuery();
            while (rset.next()){
                System.out.println(rset.getInt("id")+ ","
                + rset.getString("title") + ","
                + rset.getString("author") + ","
                + rset.getFloat("price") + ","
                        + rset.getInt("qty")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
