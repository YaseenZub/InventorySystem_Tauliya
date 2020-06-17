package pkg_tauliya;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Sql{

    protected Connection connect() {
        String url = "jdbc:sqlite:tauliya.db";
        Connection conn = null;
        // SQLite connection string
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    public int addProduct(String name, int capacity,double costprice,double initsell,String date){
        String sql = "INSERT INTO Products(name, capacity, costprice, initsell,date) VALUES(?,?,?,?,?)";

        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, capacity);
            pstmt.setDouble(3, costprice);
            pstmt.setDouble(4, initsell);
            pstmt.setString(5,date);
            pstmt.executeUpdate();
            conn.close();
            return 1;
        } catch (SQLException e) {
            System.out.println("Error in addProduct");
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public static void createNewTable(){
        // SQLite connection string
        String url = "jdbc:sqlite:tauliya.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Products (\n"
                + " id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " name text unique NOT NULL,\n"
                + " capacity int,\n"
                + " costprice real,\n"
                + " initsell real, \n"
                + " date text, \n"
                + " sold INTEGER DEFAULT 0"
                + ");";

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error in Create New table");
            System.out.println(e.getMessage());
        }
    }

    public static void createNewSalesTable(){
        // SQLite connection string
        String url = "jdbc:sqlite:tauliya.db";

        // SQL statement for creating  a new table
        String sql = "CREATE TABLE IF NOT EXISTS sales (\n"
                + " sale_id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " name_buyer text NOT NULL,\n"
                + " quantity int,\n"
                + " sell_saleprice real,\n"
                + " sell_costprice real, \n"
                + " date text,\n"
                + " id integer, \n"
                + " profit real"
                + ");";
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error in Create New Sale table");
            System.out.println(e.getMessage());
        }
    }

    public int addSale(String name, int quantity,double sell,double cost,String date,int id,double profit){
        String sql = "INSERT INTO sales(name_buyer, quantity, sell_saleprice,sell_costprice,date,id,profit) VALUES(?,?,?,?,?,?,?)";

        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2,quantity);
            pstmt.setDouble(3, sell);
            pstmt.setDouble(4,cost);
            pstmt.setString(5,date);
            pstmt.setInt(6,id);
            pstmt.setDouble(7,profit);
            pstmt.executeUpdate();
            conn.close();
            return 1;
        } catch (SQLException e) {
            System.out.println("Error in addSale");
            System.out.println(e.getMessage());
            return -1;
        }
    }

//    public void selectAll() {
//        String sql = "SELECT * FROM Products";
//
//        try {
//            Connection conn = this.connect();
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//
//            // loop through the result set
//            while (rs.next()) {
//                System.out.println(rs.getInt("id") + "\t" +
//                        rs.getString("name") + "\t" +
//                        rs.getInt("capacity")+"\t" +
//                        rs.getDouble("initsell")+"\t" +
//                        rs.getDouble("costprice")+"\t" +
//                        rs.getString("date")+"\t"+
//                        rs.getInt("sold"));
//            }
//            conn.close();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    public void selectAllSales() {
        String sql = "SELECT * FROM sales";

        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("sale_id") + "\t" +
                        rs.getString("name_buyer") + "\t" +
                        rs.getInt("quantity")+"\t" +
                        rs.getDouble("sell_saleprice")+"\t" +
                        rs.getDouble("sell_costprice")+"\t"+
                        rs.getString("date")+"\t"+
                        rs.getInt("id")+"t"+
                        rs.getDouble("profit"));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void getAllSold(){
        String sql ="Select quantity from sales";
        int i=0;
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
               i=rs.getInt("quantity")+i;
            }
            System.out.println("Total Quantity: "+i);
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


//    protected ArrayList showLast(){
//        ArrayList last_row=new ArrayList();
//        String sql = "SELECT * FROM Products ORDER BY id DESC LIMIT 1";
//        try {
//            Connection conn = this.connect();
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//
//            // loop through the result set
//            while (rs.next()) {
//                last_row.add(rs.getInt("id"));
//                last_row.add(rs.getString("name"));
//                last_row.add(rs.getInt("capacity"));
//                last_row.add(rs.getDouble("costprice"));
//                last_row.add(rs.getDouble("initsell"));
//                last_row.add(rs.getString("date"));
//                last_row.add(rs.getInt("sold"));
//            }
//            conn.close();
//        } catch (SQLException e) {
//            System.out.println("Error in showLast");
//            System.out.println(e.getMessage());
//        }
//        return last_row;
//    }




    protected ArrayList<Product> getAllProducts(){
        ArrayList<Product> products=new ArrayList<Product>();
        String sql = "SELECT * FROM Products";
        System.out.println("In get all products");
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                Product product=new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setQuantity(rs.getInt("capacity"));
                product.setCostprice(rs.getDouble("costprice"));
                product.setSaleprice(rs.getDouble("initsell"));
                product.setDateAdded(rs.getString("date"));
                product.setSold_quantity(rs.getInt("sold"));
                products.add(product);
                System.out.println(products);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error in showLast");
            System.out.println(e.getMessage());
        }
        return products;
    }

    protected ArrayList<Sale> getAllSales(){
        ArrayList<Sale> Sales=new ArrayList<Sale>();
        String sql = "SELECT * FROM sales";
        System.out.println("In get all sales");
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                Sale sale=new Sale();
                sale.setSale_id(rs.getInt("sale_id"));
                sale.setBuyer_name(rs.getString("name_buyer"));
                sale.setQuantity_bought(rs.getInt("quantity"));
                sale.setSale_price(rs.getDouble("sell_saleprice"));
                sale.setCost_price(rs.getDouble("sell_costprice"));
                sale.setDate_bought(rs.getString("date"));
                sale.setProfit(rs.getDouble("profit"));
                String name=getProductName(rs.getInt("id"));
                sale.setProduct_name(name);
                Sales.add(sale);
                System.out.println(sale);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error in showLast");
            System.out.println(e.getMessage());
        }
        return Sales;
    }



    protected void delete_row(String toDelete){
        String sql = "DELETE FROM Products WHERE name = ?";
        System.out.println("1");
        try {
            Connection conn = this.connect();
            System.out.println("2");
            PreparedStatement pstmt=conn.prepareStatement("Select * From Products");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,toDelete);
            // execute the delete statement
            pstmt.executeUpdate();
            System.out.println(pstmt.executeUpdate());
            conn.close();

        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void delete_row_sale(int toDelete){
        String sql = "DELETE FROM sales WHERE sale_id = ?";
        System.out.println("1");
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt=conn.prepareStatement("Select * From Products");
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,toDelete);
            // execute the delete statement
            pstmt.executeUpdate();
            System.out.println(pstmt.executeUpdate());
            conn.close();

        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    protected void updateQuant(int quantity,String name) {
        String sql = "UPDATE Products SET capacity = ? WHERE name= ?";
        try {
            Connection conn=this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setInt(1, quantity);
            pstmt.setString(2,name);
            //updated
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    protected void updateSelling(double selling,String name) {
        String sql = "UPDATE Products SET initsell = ? WHERE name= ?";
        try {
            Connection conn=this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setDouble(1, selling);
            pstmt.setString(2,name);
            // update
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    protected int getSold(String name){
        String sql = "SELECT sold FROM Products WHERE name = ?";
        try {
            Connection conn=this.connect();
            PreparedStatement pstmt= conn.prepareStatement(sql);
            pstmt.setString(1,name);
            ResultSet rs = pstmt.executeQuery();
            int i=rs.getInt("sold");
            conn.close();
            return i;
        }catch (Exception e){
            System.out.println("Error in getSold");
        }
        return 0;
    }
    protected void updateSold(int sold,String name) {
        String sql = "UPDATE Products SET sold = ? WHERE name= ?";
        try {
            Connection conn=this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            int current_sold=getSold(name);
            sold=sold+current_sold;
            pstmt.setInt(1, sold);
            pstmt.setString(2, name);
            // update
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    protected String getProductName(int id) {
        String sql = "SELECT name FROM Products WHERE id = ?";
        try {
            System.out.println(id+"Hehehe getproduct");
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            String name=rs.getString("name");
            conn.close();
            return name;
        }catch (Exception e){
            System.out.println("error in get Product Name");
            System.out.println(e.getMessage());
        }
        return null;
    }
    protected Product getProduct(String name){
            String sql = "SELECT * FROM Products WHERE name = ?";
            try {
                Connection conn=this.connect();
                PreparedStatement pstmt= conn.prepareStatement(sql);
                pstmt.setString(1,name);
                ResultSet rs = pstmt.executeQuery();
                Product product=new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setQuantity(rs.getInt("capacity"));
                product.setCostprice(rs.getDouble("costprice"));
                product.setSaleprice(rs.getDouble("initsell"));
                product.setDateAdded(rs.getString("date"));
                product.setSold_quantity(rs.getInt("sold"));
                conn.close();
                return product;
            }catch (Exception e){
                System.out.println("Error in getProduct");
            }
            return null;
        }
}