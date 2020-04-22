import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Menu extends JFrame {
    JLabel lTitle;
    JButton bCreate, bRead, bUpdate, bDelete, bExit;

    public void displayMenu() {
        setTitle("GUI MAHASISWA");
        lTitle = new JLabel("MENU");
        lTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        lTitle.setForeground(new Color(236, 240, 241));
        bCreate = new JButton("1. Input Data Mahasiswa");
        bCreate.setFont(new Font("Tahoma", Font.BOLD, 14));
        bCreate.setForeground(new Color(255, 255, 255));
        bCreate.setBackground(new Color(34, 167, 240));
        bRead = new JButton("2. Tampilkan Seluruh Data");
        bRead.setFont(new Font("Tahoma", Font.BOLD, 14));
        bRead.setForeground(new Color(255, 255, 255));
        bRead.setBackground(new Color(34, 167, 240));
        bDelete = new JButton("3. Hapus Data Mahasiswa");
        bDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
        bDelete.setForeground(new Color(255, 255, 255));
        bDelete.setBackground(new Color(34, 167, 240));
        bUpdate = new JButton("4. Edit Data Mahasiswa");
        bUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
        bUpdate.setForeground(new Color(255, 255, 255));
        bUpdate.setBackground(new Color(34, 167, 240));
        bExit = new JButton("0. Exit");
        bExit.setFont(new Font("Tahoma", Font.BOLD, 14));
        bExit.setForeground(new Color(255, 255, 255));
        bExit.setBackground(new Color(34, 167, 240));

        getContentPane().setBackground(new Color(44, 62, 80));

        setLayout(null);
        add(lTitle);
        add(bCreate);
        add(bRead);
        add(bDelete);
        add(bUpdate);
        add(bExit);

        lTitle.setBounds(180, 30, 70, 30);
        bCreate.setBounds(100, 80, 220, 30);
        bRead.setBounds(100, 120, 220, 30);
        bDelete.setBounds(100, 160, 220, 30);
        bUpdate.setBounds(100, 200, 220, 30);
        bExit.setBounds(100, 240, 220, 30);

        setSize(430, 370);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        bCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateData();
            }
        });

        bRead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReadData();
            }
        });

        bDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new DeleteData();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });

        bUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    new UpdateData();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });

        bExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
