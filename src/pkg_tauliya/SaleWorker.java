package pkg_tauliya;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SaleWorker extends SwingWorker<ArrayList<Sale>,Sale> implements ActionListener {
    ArrayList<Sale> sales=new ArrayList<Sale>();
    JButton button_back=new JButton("back");
    Sql sql=new Sql();
    JFrame frame=new JFrame("View All");
    Sale getSale=new Sale();
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
        dtm.addColumn("Buyer Name");
        dtm.addColumn("Product Name");
        dtm.addColumn("Quantity Bought");
        dtm.addColumn("SalePrice");
        dtm.addColumn("CostPrice");
        dtm.addColumn("Date Added");
        dtm.addColumn("Sale Id");
        table.setModel(dtm);
        for(int i=0;i<e;i++){
            dtm.addRow(new Object[]{
                    ++counter,
                    data[i][0],
                    data[i][1],
                    data[i][2],
                    data[i][3],
                    data[i][4],
                    data[i][5],
                    data[i][6]
            });
//            j=i;
//            id=Integer.parseInt(data[i][0]);
        }

        table.setBounds(10,30,600,600);
        ArrayList obj=new ArrayList();
        frame.add(scrollPane, BorderLayout.NORTH);
        obj.add(frame);
        obj.add(dtm);
        return obj;
    }
    protected JTable setUpGUI(){
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return table;
    }
    protected ArrayList<Sale> doInBackground() throws Exception {
        sql.connect();
        sales=sql.getAllSales();
        e=sales.size();
        System.out.println(e);
        data=new String[e][7];
        for(int i=0;i<e;i++){
            getSale=sales.get(i);
            data[i][0]=getSale.getBuyer_name();
            data[i][1]=getSale.getProduct_name();
            data[i][2]=Integer.toString(getSale.getQuantity_bought());
            data[i][3]=Double.toString(getSale.getCost_price());
            data[i][4]=Double.toString(getSale.getSale_price());
            data[i][5]=getSale.getDate_bought();
            data[i][6]=Integer.toString(getSale.getSale_id());
        }
        return sales;

    }


    protected void done() {
        System.out.println("Done");
    }


    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button_back){
            Main.getGui();
            frame.dispose();
        }
    }

}
