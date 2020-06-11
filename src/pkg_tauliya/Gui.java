package pkg_tauliya;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class Gui extends JFrame implements ActionListener {
    //FOR TESTING:
    Product product =new Product();
    Sql sql=new Sql();
    String date,date2,buyer_name,sold_product_name;
    int sold_quantity,sold_product_id;
    double sold_price,cost_price_sale;
    int flag_for_sale=0;
    boolean flagRender_for_sale=false;
    ArrayList obj=new ArrayList(),obj2=new ArrayList();
    private JFrame mainmenu,view_all,sale_frame=new JFrame("Add sale"),edit_Product=new JFrame("Edit"),view_all_sale;
    SqlWorker m=new SqlWorker();
    SaleWorker saleworker=new SaleWorker();
    JTextField name,check_name;
    JTextField[] textFields=new JTextField[4];
    DefaultTableModel dtm,dtm2;
    JTable table=new JTable(),table2;
    JLabel[] labels=new JLabel[4];
    JLabel q,sold;
    TextFormatter quantity,cost_price,sell_price,sale_sp,sale_quantity;
    JButton button_startup=new JButton("Click here to continue");
    JButton button_addProduct,button_editProduct,button_sale,button_viewAll,button_adding,button_delete,button_viewSale;
    JButton button_back_add,button_back_view,check_edit,delete_sale,button_back_view_sale;
    JButton[] buttons_sale=new JButton[5];
    JTextField tf_sales,tf_sales_name;
    JButton[] buttons=new JButton[4];
    int flag=0,quant=0,productId;
    Boolean flag_render=false,reRender=false,reRender_sale=false;
    double selling=0,cost=0;
    String name1;
//    boolean flag_for_sale=false;
    protected void startUp(){
        sql.selectAllSales();
        sql.createNewTable();
        JPanel panel= new JPanel();
        JPanel panel2= new JPanel();
        JLabel label= new JLabel();
        setSize(400,400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        label.setIcon(new ImageIcon("Logo-04-edited.png"));
        panel.add(label,BorderLayout.NORTH);
        add(panel);
        panel2.add(button_startup);
        panel2.setBorder(BorderFactory.createEmptyBorder(0,0,150,0));
        add(panel2,BorderLayout.AFTER_LAST_LINE);
        setResizable(false);
        validate();

        button_startup.addActionListener(this);
    }
    public void mainMenu(){
        mainmenu=new JFrame("LogIn");
        mainmenu.setSize(400,400);
        mainmenu.setVisible(true);
        mainmenu.setLayout(null);
        mainmenu.setDefaultCloseOperation(3);
        mainmenu.getContentPane().setBackground(new Color(121,53,112));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        button_addProduct=new JButton("Add Product");
        button_editProduct=new JButton("Edit Product");
        button_sale=new JButton("Add a Sale");
        button_viewAll=new JButton("View all the products");
        button_viewAll.setBounds(40,40,150,30);
        button_sale.setBounds(200,40,150,30);
        button_addProduct.setBounds(40,80,150,30);
        button_editProduct.setBounds(200,80,150,30);
        button_addProduct.setBackground(Color.WHITE);
        button_viewAll.setBackground(Color.WHITE);
        button_editProduct.setBackground(Color.WHITE);
        button_sale.setBackground(Color.WHITE);
        button_addProduct.setForeground(new Color(121,53,112));
        button_viewAll.setForeground(new Color(121,53,112));
        button_editProduct.setForeground(new Color(121,53,112));
        button_sale.setForeground(new Color(121,53,112));
        button_viewSale=new JButton("View all sales");
        button_viewSale.setBounds(120,120,150,30);
        button_viewSale.setForeground(new Color(121,53,112));
        button_viewSale.setBackground(Color.WHITE);
        mainmenu.add(button_addProduct);
        mainmenu.add(button_editProduct);
        mainmenu.add(button_sale);
        mainmenu.add(button_viewAll);
        mainmenu.add(button_viewSale);
        button_addProduct.addActionListener(this);
        button_viewAll.addActionListener(this);
        button_editProduct.addActionListener(this);
        button_sale.addActionListener(this);
        button_viewSale.addActionListener(this);
    }
    protected void addProduct(){
//        quantity=new TextFormatter();
        mainmenu.setLayout(null);
        JLabel[] label=new JLabel[7];
        label[0]=new JLabel("ADD PRODUCT");
        mainmenu.setSize(600,400);
        label[0].setBounds(160,40,300,40);
        label[0].setFont(new Font("Calibri",Font.BOLD+Font.ITALIC,40));
        label[0].setForeground(Color.WHITE);
        label[1]=new JLabel("Quantity");
        label[1].setBounds(40,151,70,30);
        quantity=new TextFormatter();
        quantity.setMaxLength(10);
        quantity.setPrecision(0);
        quantity.setAllowNegative(false);
        quantity.setBounds(130,151,130,30);
        label[2]=new JLabel("Name");
        label[2].setBounds(40,100,70,30);
        name=new JTextField();
        name.setBounds(130,100,130,30);
        label[3]=new JLabel("Cost Price");
        label[3].setBounds(40,202,70,30);
        cost_price=new TextFormatter();
        cost_price.setMaxLength(10);
        cost_price.setPrecision(1);
        cost_price.setAllowNegative(false);
        cost_price.setBounds(130,202,130,30);
        label[4]=new JLabel("Expected SP");
        label[4].setBounds(40,253,70,30);
        sell_price=new TextFormatter();
        sell_price.setMaxLength(10);
        sell_price.setPrecision(1);
        sell_price.setAllowNegative(false);
        sell_price.setBounds(130,253,130,30);
        button_adding=new JButton("Add");
        button_adding.setBounds(250,300,60,30);
        button_back_add=new JButton("Back");
        button_back_add.setBounds(500,340,70,30);
        button_back_add.addActionListener(this);
        for(int i=0;i<5;i++){
            mainmenu.add(label[i]);
            if(i>=1){
                label[i].setFont(new Font("Verdana",Font.BOLD,12));
                label[i].setForeground(Color.WHITE);}
        }
        mainmenu.add(name);
        mainmenu.add(quantity);
        mainmenu.add(cost_price);
        mainmenu.add(sell_price);
        mainmenu.add(button_adding);
        mainmenu.add(button_back_add);
        button_adding.addActionListener(this);
    }
    protected void viewAll(){
//        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        button_delete = new JButton("Remove");
        button_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // check for setlected row first
                if(table.getSelectedRow() != -1) {
                    // remove selected row from the model
                    int rows=table.getSelectedRow();
                    String toDelete=table.getValueAt(rows,1).toString();
                    dtm.removeRow(rows);
                    sql.delete_row(toDelete);
                    JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
                }
            }
        });
        button_back_view=new JButton("Back");
        button_back_view.addActionListener(this);
        view_all.add(button_back_view,BorderLayout.WEST);
        view_all.add(button_delete,BorderLayout.SOUTH);
    }
    protected void editProduct(){
        edit_Product.setLayout(null);
        edit_Product.setSize(400,600);
        edit_Product.setDefaultCloseOperation(3);
        edit_Product.setResizable(true);
        edit_Product.setVisible(true);
        JLabel enter_product=new JLabel("Enter Product Name");
        check_name=new JTextField();
        check_edit=new JButton("Check");
        enter_product.setBounds(50,10,300,40);
        enter_product.setFont(new Font("Calibri",Font.BOLD+Font.ITALIC,30));
        enter_product.setForeground(Color.WHITE);
        check_name.setBounds(50,60,150,40);
        check_name.setBackground(Color.WHITE);
        check_name.setBorder(BorderFactory.createLineBorder(new Color(121,53,112)));
        check_name.setFont(new Font("Calibri",Font.BOLD,20));
        check_name.setForeground(new Color(121,53,112));
        check_edit.setBounds(250,60,80,35);
        check_edit.setBackground(Color.WHITE);
        check_edit.setForeground(new Color(121,53,112));
        check_edit.setFont(new Font("Calibri",Font.BOLD,15));
        edit_Product.add(check_name);
        edit_Product.add(check_edit);
        edit_Product.add(enter_product);
        check_edit.addActionListener(this);
    }
    protected void renderEdit(){
        if(!reRender) {
            System.out.println("What is");
            edit_Product.getContentPane().setBackground(Color.ORANGE);
            //-----------------
            labels[0] = new JLabel("Enter Update Name");
            labels[0].setBounds(25, 110, 150, 30);
            textFields[0] = new JTextField(product.getName());
            textFields[0].setBounds(25, 145, 150, 30);
            buttons[0] = new JButton("Update Name");
            buttons[0].setBounds(200, 145, 80, 30);
            buttons[0].setEnabled(false);
            //--------
            labels[1] = new JLabel("Enter Update Quantity");
            labels[1].setBounds(25, 190, 150, 30);
            textFields[1] = new JTextField(String.valueOf(product.getQuantity()));
            textFields[1].setBounds(25, 225, 150, 30);
            buttons[1] = new JButton("Update Quantity");
            buttons[1].setBounds(200, 225, 80, 30);

            //--------
            labels[2] = new JLabel("Enter Update Selling");
            labels[2].setBounds(25, 270, 150, 30);
            textFields[2] = new JTextField(String.valueOf(product.getSaleprice()));
            textFields[2].setBounds(25, 305, 150, 30);
            buttons[2] = new JButton("Update Selling Price");
            buttons[2].setBounds(200, 305, 80, 30);
            //--------------
            labels[3] = new JLabel("Enter Update Cost");
            labels[3].setBounds(25, 350, 150, 30);
            textFields[3] = new JTextField(String.valueOf(product.getCostprice()));
            textFields[3].setBounds(25, 385, 150, 30);
            buttons[3] = new JButton("Update Cost");
            buttons[3].setBounds(200, 385, 80, 30);
            for (int k = 0; k < 4; k++) {
                edit_Product.add(labels[k]);
                edit_Product.add(textFields[k]);
                edit_Product.add(buttons[k]);
//            labels[k].setVisible(true);
            }
            reRender=true;
        }
        else{
            textFields[0].setText(product.getName());
            textFields[1].setText(String.valueOf(product.getQuantity()));
            textFields[2].setText(String.valueOf(product.getSaleprice()));
            textFields[3].setText(String.valueOf(product.getCostprice()));
        }
        //2	imran	2234	24.0	28.0	27-05-2020 00:46:56
        //3	mali	2234	2.0	3.0	27-05-2020 00:50:12
        for (int k=1;k<4;k++)
            edit_Product.add(buttons[k]);
        buttons[1].addActionListener(this);
        buttons[2].addActionListener(this);
        buttons[3].addActionListener(this);
    }
    protected void saleGui(){
        sale_frame.setResizable(false);
        sale_frame.setSize(600,600);
        sale_frame.setDefaultCloseOperation(3);
        sale_frame.setLayout(null);
        sale_frame.getContentPane().setBackground(Color.PINK);
        ////////////
        JLabel label_head=new JLabel("Congratulations");
        label_head.setBounds(150,20,300,40);
        label_head.setFont(new Font("Calibri",Font.BOLD+Font.ITALIC,40));
        sale_frame.add(label_head);
        ////////////
        JLabel product_name=new JLabel("Enter Product name");
        product_name.setBounds(30,70,150,30);
        tf_sales=new JTextField();
        tf_sales.setBounds(180,70,150,30);
        tf_sales.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
        buttons_sale[0]=new JButton("Get item");
        buttons_sale[0].setBounds(135,120,100,25);
        sale_frame.add(product_name);
        sale_frame.add(tf_sales);
        sale_frame.add(buttons_sale[0]);
        buttons_sale[2]=new JButton("Back");
        buttons_sale[2].setBounds(10,30,70,30);
        sale_frame.add(buttons_sale[2]);
        buttons_sale[2].addActionListener(this);
        buttons_sale[0].addActionListener(this);
        ////////////////
        sale_frame.validate();
        sale_frame.setVisible(true);
    }
    protected void renderSale(Product gotProduct){
        if(reRender_sale) {
            q.setText("");
            sold.setText("");
        }
            JLabel heading = new JLabel(gotProduct.getName().toLowerCase());
            heading.setBounds(200, 180, 400, 40);
            heading.setFont(new Font("Calibri", Font.BOLD + Font.ITALIC, 45));
            heading.setForeground(Color.WHITE);
            JLabel cp = new JLabel("Cost Price:" + gotProduct.getCostprice());
            cp.setFont(new Font("Monospaced", Font.BOLD, 35));
            cp.setBounds(50, 215, 400, 31);
            q = new JLabel("Quantity  :" + gotProduct.getQuantity());
            q.setFont(new Font("Monospaced", Font.BOLD, 35));
            q.setBounds(50, 260, 400, 31);
            sold = new JLabel("No of Sold:" + gotProduct.getSold_quantity());
            sold.setFont(new Font("Monospaced", Font.BOLD, 35));
            sold.setBounds(50, 305, 400, 31);
            JLabel label_quantity = new JLabel("Enter Quantity");
            label_quantity.setBounds(50, 355, 200, 30);
            label_quantity.setFont(new Font("Helvetica", Font.BOLD, 25));
            sale_quantity = new TextFormatter();
            sale_quantity.setMaxLength(10);
            sale_quantity.setPrecision(0);
            sale_quantity.setAllowNegative(false);
            sale_quantity.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            sale_quantity.setBounds(260, 355, 100, 30);
            JLabel sp = new JLabel("Enter Sell Price");
            sp.setFont(new Font("Helvetica", Font.BOLD, 25));
            sp.setBounds(50, 405, 200, 30);
            sale_sp = new TextFormatter();
            sale_sp.setMaxLength(10);
            sale_sp.setPrecision(3);
            sale_sp.setAllowNegative(false);
            sale_sp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            sale_sp.setBounds(260, 405, 100, 30);
            sale_sp.setDouble(product.getSaleprice());
            sale_sp.setEditable(true);
            JLabel jl_sales_name = new JLabel("Buyer Name");
            jl_sales_name.setBounds(50, 455, 200, 30);
            jl_sales_name.setFont(new Font("Helvetica", Font.BOLD, 25));
            tf_sales_name = new JTextField();
            tf_sales_name.setBounds(260, 455, 100, 30);
            tf_sales_name.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            buttons_sale[1] = new JButton("Add Sale");
            buttons_sale[1].setBounds(250, 500, 100, 30);
            buttons_sale[1].setBackground(Color.WHITE);
            buttons_sale[1].setBorder(BorderFactory.createLineBorder(Color.BLACK));

            sale_frame.add(heading);
            sale_frame.add(cp);
            sale_frame.add(q);
            sale_frame.add(sold);
            sale_frame.add(label_quantity);
            sale_frame.add(sale_quantity);
            sale_frame.add(sp);
            sale_frame.add(sale_sp);
            sale_frame.add(buttons_sale[1]);
            sale_frame.add(tf_sales_name);
            sale_frame.add(jl_sales_name);
            buttons_sale[1].addActionListener(this);
            sale_frame.setResizable(false);
//        sale_frame.validate();
//        sale_frame.setVisible(true);
    }
    protected void viewAll_Sale() {
//        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        delete_sale= new JButton("Remove");
        delete_sale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // check for setlected row first
                if (table2.getSelectedRow() != -1) {
                    // remove selected row from the model
                    int rows = table2.getSelectedRow();
                    System.out.println(table2.getModel().getValueAt(rows, 7));
                    String temp = table2.getModel().getValueAt(rows, 7).toString();
                    int toDelete=Integer.parseInt(temp);
                    System.out.println(toDelete);
                    dtm2.removeRow(rows);
                    sql.delete_row_sale(toDelete);
                    JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
                }
            }
        });
        button_back_view_sale=new JButton("Back");
        button_back_view_sale.addActionListener(this);
        view_all_sale.add(button_back_view_sale,BorderLayout.WEST);
        view_all_sale.add(delete_sale,BorderLayout.SOUTH);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==button_startup){
            saleworker.execute();
            m.execute();
            removeAll();
            dispose();
            mainMenu();
        }
        else if(e.getSource()==button_editProduct){
            mainmenu.dispose();
            editProduct();
        }
        else if(e.getSource()==check_edit){
            String getProductName=check_name.getText();
            System.out.println(getProductName);
            product=sql.getProduct(getProductName);
            System.out.println("brrr");
            renderEdit();
        }
        else if(e.getSource()==buttons[1]){
            int updatedQuant=Integer.parseInt(textFields[1].getText());
            sql.updateQuant(updatedQuant,check_name.getText());
//            sql.selectAll();
        }
        else if(e.getSource()==buttons[2]){
            double updatedSell=Double.parseDouble(textFields[2].getText());
            sql.updateSelling(updatedSell,check_name.getText());
//            sql.selectAll();
        }
        else if(e.getSource()==button_addProduct){
            mainmenu.getContentPane().removeAll();
            mainmenu.repaint();
            addProduct();

        }
        else if(e.getSource()==button_adding){
            try{
                quant=quantity.getInt();
                selling=sell_price.getDouble();
                cost=cost_price.getDouble();
                name1=name.getText();
                date=Main.getDate();
                sql.connect();
                sql.createNewTable();
                if(sql.addProduct(name1,quant,cost,selling,date)==1){
                    JOptionPane.showMessageDialog(mainmenu,"You have added : \t" + name1 +"Quantity:"+quant+"\t" +
                            "cost:"+cost+"\tselling"+selling);
                    flag_render=true;
                }
                else {
                    JOptionPane.showMessageDialog(mainmenu,"There is an error while connecting to database, You may" +
                            "have entered a name that already exists");
                }
            }catch (NumberFormatException err){
                System.out.println("The error is with number");
            }

        }
        else if(e.getSource()==button_back_add){
            mainmenu.removeAll();
            mainmenu.dispose();
            mainMenu();
        }
        else if(e.getSource()==button_back_view){
            view_all.setVisible(false);
            mainMenu();
        }
        else if(e.getSource()==button_back_view_sale){
            view_all_sale.setVisible(false);
            mainMenu();
        }
        else if(e.getSource()==button_viewAll){

            if(flag==0 ){
            obj=m.dueToDone();
            view_all=(JFrame)obj.get(0);
            dtm=(DefaultTableModel) obj.get(1);
            viewAll();
            table=m.setUpGUI();
            flag=1;
            mainmenu.dispose();
            }else{
                view_all.setVisible(true);
                mainmenu.dispose();
            }
            if(flag_render==true){
                dtm.addRow(new Object[]
                                {
                                        table.getRowCount()+1,
                                        name1,
                                        quant,
                                        0,
                                        cost,
                                        selling,
                                        date
                                }
                        );
                flag_render=false;
            }
            else {
                System.out.println("do nothing");
            }

        }
        else if(e.getSource()==button_sale){
            mainmenu.dispose();
            saleGui();
        }
        else if(e.getSource()==buttons_sale[0]){
            String product_name=tf_sales.getText();
            System.out.println(product_name);
            if(product_name!=null){
                product=sql.getProduct(product_name);
                if(product!=null) {
                    productId=product.getId();
                    System.out.println("I am a productID"+productId);
                    renderSale(product);

                    System.out.println("GOT A PRODUCT");
                }
            }
        }
        else if(e.getSource()==buttons_sale[2]){
            sale_frame.removeAll();
            sale_frame.dispose();
            mainMenu();
        }
        else if(e.getSource()==buttons_sale[1]){
            sql.createNewSalesTable();
            buyer_name=tf_sales_name.getText();
            sold_quantity=Integer.parseInt(sale_quantity.getText());
            sold_price=Double.parseDouble(sale_sp.getText());
            date2=Main.getDate();
            cost_price_sale=product.getCostprice();
            sold_product_id=productId;
            sold_product_name=product.getName();
            if(sql.addSale(buyer_name,sold_quantity,sold_price,product.getCostprice(),date,productId)==1){
                sql.updateQuant(product.getQuantity()-sold_quantity,product.getName());
                sql.updateSold(sold_quantity,product.getName());
                System.out.println("HEHEHEHE" +product.getQuantity());
                product.setQuantity(product.getQuantity()-sold_quantity);
                int productQuantity=product.getQuantity();
                product.setSold_quantity(sold_quantity);
                int soldQuantity=product.getSold_quantity();
                sold.setText("No of Sold:"+soldQuantity);
                q.setText("Quantity  :"+productQuantity);
                reRender_sale=true;
                flagRender_for_sale=true;
                sql.selectAllSales();
            }
        }
        else if(e.getSource()==button_viewSale){
            if(flag_for_sale==0) {
                obj2 = saleworker.dueToDone();
                view_all_sale = (JFrame) obj2.get(0);
                dtm2 = (DefaultTableModel) obj2.get(1);
                table2 = saleworker.setUpGUI();
                TableColumnModel tcm = table2.getColumnModel();
                table2.removeColumn(table2.getColumnModel().getColumn(7));
                flag_for_sale=1;
                viewAll_Sale();
            }
            else {
                view_all_sale.setVisible(true);
                mainmenu.dispose();
            }
            if(flagRender_for_sale== true){
                dtm2.addRow(new Object[]{
                        table2.getRowCount()+1,
                        buyer_name,
                        sold_product_name,
                        sold_price,
                        cost_price_sale,
                        date2
                });
            }

        }
    }
}
