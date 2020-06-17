package pkg_tauliya;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.*;

public class SqlWorker extends SwingWorker<ArrayList<Product>,Product> implements ActionListener {
    ArrayList<Product> products=new ArrayList<Product>();
    JButton button_back=new JButton("back");
    Sql sql=new Sql();
    JFrame frame=new JFrame("View All");
    Product getProduct=new Product();
    String column[]={"ID","Name","Quantity","CostPrice","SellPrice"};;
    String data[][];
    JTable table=new JTable(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    DefaultTableModel dtm=new DefaultTableModel(0,0);
    int e=1,j=0,counter=0;


    protected ArrayList dueToDone(){
        frame.setSize(800,600);
        JScrollPane scrollPane=new JScrollPane(table);
        dtm.addColumn("Id");
        dtm.addColumn("Name");
        dtm.addColumn("Quantity");
        dtm.addColumn("sold");
        dtm.addColumn("CostPrice");
        dtm.addColumn("SalePrice");
        dtm.addColumn("Date Added");
        table.setModel(dtm);
        for(int i=0;i<e;i++){
            dtm.addRow(new Object[]{
                    ++counter,
                    data[i][0],
                    data[i][1],
                    data[i][2],
                    data[i][3],
                    data[i][4],
                    data[i][5]
            });
            j=i;
//            id=Integer.parseInt(data[i][0]);
        }
        table.setBounds(10,30,600,600);
        ArrayList obj=new ArrayList();
        frame.add(scrollPane,BorderLayout.NORTH);
        obj.add(frame);
        obj.add(dtm);
        return obj;
    }
    protected JTable setUpGUI(){
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            return table;
    }
    protected ArrayList<Product> doInBackground() throws Exception {
        sql.connect();
        products=sql.getAllProducts();
        e=products.size();
        System.out.println(e);
        data=new String[e][6];
        for(int i=0;i<e;i++){
            getProduct=products.get(i);
            data[i][0]=getProduct.getName();
            data[i][1]=Integer.toString(getProduct.getQuantity());
            data[i][2]=Integer.toString(getProduct.getSold_quantity());
            data[i][3]=Double.toString(getProduct.getCostprice());
            data[i][4]=Double.toString(getProduct.getSaleprice());
            data[i][5]=getProduct.getDateAdded();
        }
        return products;

    }

    @Override
    protected void done() {
        System.out.println("Done");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button_back){
            Main.getGui();
            frame.dispose();
        }
    }
}
