import javax.swing.*;
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
        lTitle = new JLabel("Hapus Data Mahasiswa");
        bDelete = new JButton("Hapus");
        bBack = new JButton("Kembali");
        table = new JTable(datas, column);
        scrollPane = new JScrollPane(table);

        setTitle("Hapus Data Mahasiswa");
        lTitle.setHorizontalAlignment(SwingConstants.CENTER);
        DBConnection connec = new DBConnection();
        statement = connec.getConnection().createStatement();
        String sql = "SELECT * FROM data_mhs";
        resultSet = statement.executeQuery(sql);
        int p = 0;
        while (resultSet.next()) {
            datas[p][0] = resultSet.getString("nama");
            datas[p][1] = resultSet.getString("nim");
            datas[p][2] = resultSet.getString("alamat");
            p++;
        }
        setLayout(null);
        add(lTitle);
        add(bDelete);
        add(bBack);

        lTitle.setBounds(50, 10, 150, 25);
        bBack.setBounds(30, 90, 100, 25);
        bDelete.setBounds(140, 90, 100, 25);
        setSize(700,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
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
