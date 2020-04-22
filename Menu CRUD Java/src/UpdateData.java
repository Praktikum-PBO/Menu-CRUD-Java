import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateData extends JFrame {
    JLabel lTitle;
    JButton bUpdate, bBack;
    Statement statement;
    ResultSet resultSet;
    String[][] datas = new String[500][3];
    String[] column = {"Nim","Nama","Alamat"};
    JTable table;
    JScrollPane scrollPane;

    public UpdateData() throws ClassNotFoundException, SQLException {
        lTitle = new JLabel ("Seluruh Data Mahasiswa");
        bUpdate = new JButton ("Edit");
        bBack = new JButton ("Kembali");
        table = new JTable(datas, column);
        scrollPane = new JScrollPane(table);

        setTitle("EDIT DATA MAHASISWA");
        setSize (700,600);
        lTitle.setHorizontalAlignment(SwingConstants.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(null);
        add(lTitle);
        add(bUpdate);
        add(bBack);

        lTitle.setBounds(150, 50, 250, 30);
        bUpdate.setBounds(150, 90, 100, 20);
        bBack.setBounds(270, 90, 100, 20);

        setLayout(new FlowLayout());
        add(scrollPane);

        DBConnection connec = new DBConnection();
        statement = connec.getConnection().createStatement();
        String sql = "SELECT *FROM data_mhs";
        resultSet = statement.executeQuery(sql);
        int p = 0;
        while (resultSet.next()){
            datas[p][0] = resultSet.getString("nama");
            datas[p][1] = resultSet.getString("nim");
            datas[p][2] = resultSet.getString("alamat");
            p++;
        }
        statement.close();

        bUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                new ProsesUpdate();
            }
        });

    }

}
