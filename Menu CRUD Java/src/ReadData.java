import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadData extends JFrame {
    String[][] datas = new String[500][3];
    String[] column = {"NIM", "Nama", "Alamat"};
    JLabel lTitle;
    JTable table;
    JButton bBack;
    JScrollPane scrollPane;
    Statement statement;
    ResultSet resultSet;

    public ReadData() {
        setTitle("TAMPILKAN DATA MAHASISWA");

        lTitle = new JLabel("Seluruh Data Mahasiswa");
        lTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        lTitle.setForeground(new Color(236, 240, 241));
        bBack = new JButton("Kembali");
        bBack.setFont(new Font("Tahoma", Font.BOLD, 14));
        bBack.setForeground(new Color(255, 255, 255));
        bBack.setBackground(new Color(255, 40, 20, 204));
        table = new JTable(datas, column);
        scrollPane = new JScrollPane(table);

        getContentPane().setBackground(new Color(69, 91, 113));

        setLayout(null);
        add(lTitle);
        add(bBack);
        add(scrollPane);

        lTitle.setBounds(130, 30, 300, 30);
        bBack.setBounds(210, 70, 90, 30);
        scrollPane.setBounds(70, 120, 400, 400);

        setSize(570, 650);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        bBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Main();
            }
        });

        DBConnection connec = new DBConnection();
        try {
            statement = connec.getConnection().createStatement();
            String sql = "SELECT * FROM data_mhs";
            resultSet = statement.executeQuery(sql);

            int p = 0;
            while (resultSet.next()) {
                datas[p][0] = resultSet.getString("nim");
                datas[p][1] = resultSet.getString("nama");
                datas[p][2] = resultSet.getString("alamat");
                p++;
            }
            statement.close();

        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(rootPane, "Data gagal ditampilkan!" + sqle);
        } catch (ClassNotFoundException classe) {
            JOptionPane.showMessageDialog(rootPane, "Class Tidak ditemukan!");
        }
    }
}
