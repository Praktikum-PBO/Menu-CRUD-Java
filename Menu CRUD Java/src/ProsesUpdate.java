import javax.swing.*;
import java.awt.*;
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
        lTitle = new JLabel("FORM EDIT DATA");
        lTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        lTitle.setForeground(new Color(236, 240, 241));
        lName = new JLabel("Nama ");
        lName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lName.setForeground(new Color(236, 240, 241));
        lNIM = new JLabel("NIM ");
        lNIM.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lNIM.setForeground(new Color(236, 240, 241));
        lAddress = new JLabel ("Alamat ");
        lAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lAddress.setForeground(new Color(236, 240, 241));
        tfName = new JTextField();
        tfName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tfName.setForeground(new Color(245, 249, 250));
        tfName.setBackground(new Color(108, 122, 137));
        tfNIM = new JTextField();
        tfNIM.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tfNIM.setForeground(new Color(245, 249, 250));
        tfNIM.setBackground(new Color(108, 122, 137));
        tfAddress = new JTextField();
        tfAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tfAddress.setForeground(new Color(245, 249, 250));
        tfAddress.setBackground(new Color(108, 122, 137));
        bSave = new JButton("Edit");
        bSave.setFont(new Font("Tahoma", Font.BOLD, 14));
        bSave.setForeground(new Color(255, 255, 255));
        bSave.setBackground(new Color(34, 167, 240));
        bBack = new JButton("Kembali");
        bBack.setFont(new Font("Tahoma", Font.BOLD, 14));
        bBack.setForeground(new Color(255, 255, 255));
        bBack.setBackground(new Color(255, 40, 20, 204));

        getContentPane().setBackground(new Color(44, 62, 80));

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

        lTitle.setBounds(110, 30, 250, 30);
        lName.setBounds(90, 70, 90, 30);
        tfName.setBounds(170, 73, 160, 25);
        lNIM.setBounds(90, 110, 90, 30);
        tfNIM.setBounds(170, 113, 160, 25);
        lAddress.setBounds(90, 150, 90, 30);
        tfAddress.setBounds(170, 153, 160, 70);
        bSave.setBounds(120, 245, 90, 30);
        bBack.setBounds(220, 245, 90, 30);

        setSize(430, 370);
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
