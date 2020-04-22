import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProsesUpdate extends JFrame {
    JLabel lTitle, lName, lNIM, lAddress;
    JTextField tfName, tfNIM, tfAddress;
    JButton bSave, bBack;
    Statement statement;

    public ProsesUpdate() {

        setTitle("FORM EDIT DATA");
        lTitle = new JLabel("Form Edit Data");
        lName = new JLabel("Nama   : ");
        lNIM = new JLabel("NIM      :");
        lAddress = new JLabel("Alamat : ");

        tfName = new JTextField("");
        tfNIM = new JTextField("");
        tfAddress = new JTextField("");

        bSave = new JButton("Simpan");
        bBack = new JButton("Kembali");

        setLayout(null);
        add(lTitle);
        add(lName);
        add(lNIM);
        add(lAddress);

        add(tfName);
        add(tfNIM);
        add(tfAddress);

        add(bSave);
        add(bBack);
        lTitle.setBounds(150, 50, 150, 30);
        lName.setBounds(150, 90, 50, 20);
        lNIM.setBounds(150, 130, 50, 20);
        lAddress.setBounds(150, 170, 50, 20);
        tfName.setBounds(200, 90, 120, 20);
        tfNIM.setBounds(200, 130, 120, 20);
        tfAddress.setBounds(200, 170, 120, 50);
        bSave.setBounds(150, 230, 90, 20);
        bBack.setBounds(250, 230, 90, 20);

        setSize(500, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        bSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                btnUpdateactionListener();
            }

            private void btnUpdateactionListener() {
                DBConnection connec = new DBConnection();
                try {
                    statement = connec.getConnection().createStatement();
                    statement.executeUpdate("UPDATE data_mhs SET nim='" + tfNIM.getText() + "'," + "nama='" +
                            tfName.getText() + "'," + "nim='" + tfAddress.getText() + "'");

                    JOptionPane.showMessageDialog(null, "Data berhasil di Update!", "Hasil", JOptionPane.INFORMATION_MESSAGE);
                    statement.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Data gagal diupdate!", "Hasil", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Driver Tidak Ditemukan!", "Hasil", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        bBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                try {
                    new UpdateData();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ProsesUpdate.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ProsesUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
