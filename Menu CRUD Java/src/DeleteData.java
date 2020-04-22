import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteData extends JFrame {

    Statement statement;
    ResultSet resultSet;
    JButton bDelete, bBack;
    JLabel lTitle;
    String[][] datas = new String[500][3];
    String[] column = {"Nim", "Nama", "Alamat"};
    JTable table;
    JScrollPane scrollPane;

    public DeleteData() throws ClassNotFoundException, SQLException {
        lTitle = new JLabel("Seluruh Data Mahasiswa");
        lTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        lTitle.setForeground(new Color(236, 240, 241));
        bDelete = new JButton("Hapus");
        bDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
        bDelete.setForeground(new Color(255, 255, 255));
        bDelete.setBackground(new Color(34, 167, 240));
        bBack = new JButton("Kembali");
        bBack.setFont(new Font("Tahoma", Font.BOLD, 14));
        bBack.setForeground(new Color(255, 255, 255));
        bBack.setBackground(new Color(255, 40, 20, 204));
        table = new JTable(datas, column);
        scrollPane = new JScrollPane(table);

        getContentPane().setBackground(new Color(93, 119, 146));

        setTitle("Hapus Data Mahasiswa");
        DBConnection connec = new DBConnection();
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
        setLayout(null);
        add(lTitle);
        add(bDelete);
        add(bBack);
        add(scrollPane);

        lTitle.setBounds(160, 30, 300, 30);
        bDelete.setBounds(170, 70, 90, 25);
        bBack.setBounds(280, 70, 90, 25);
        scrollPane.setBounds(70, 120, 400, 400);

        setSize(550,650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        bBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                new Menu();
            }
        });

        bDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                DBConnection connec = new DBConnection();
                try {
                    statement = connec.getConnection().createStatement();

                    statement.executeUpdate("DELETE FROM data_mhs");
                    JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus!", "Hasil", JOptionPane.INFORMATION_MESSAGE);
                    statement.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Data gagal dihapus!", "Hasil", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Driver Tidak Ditemukan!", "Hasil", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

}
