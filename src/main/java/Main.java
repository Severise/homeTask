
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, SQLException {

        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        Statement stmt = conn.createStatement();
        String sql = "SELECT TABLE_LIST.TABLE_NAME, TABLE_LIST.PK, TABLE_COLS.COLUMN_TYPE FROM TABLE_LIST, TABLE_COLS WHERE LOWER(TABLE_LIST.PK)=LOWER(TABLE_COLS.COLUMN_NAME) AND LOWER(TABLE_LIST.TABLE_NAME)=LOWER(TABLE_COLS.TABLE_NAME)";
        ResultSet rs = stmt.executeQuery(sql);
        StringBuilder res = new StringBuilder();
        while (rs.next()) {
            res.append(rs.getString("TABLE_NAME"));
            res.append(", ");
            res.append(rs.getString("PK"));
            res.append(", ");
            res.append(rs.getString("COLUMN_TYPE"));
            res.append("\n");
        }
        conn.close();

        PrintWriter out = new PrintWriter(new File("output.txt"));
        out.print(res.toString());
        out.close();
    }
}
