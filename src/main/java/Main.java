import generator.*;
import service.StringFormatService;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.*;

public class Main extends JDialog {

    private JComboBox pfPassword;
    private JTextField tfUsername;
    private JTextField txAttribute;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JLabel lbAttribute;
    private JComboBox CmTypes;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;

    public Main(Frame parent) {
        super(parent, "Login", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        String[] types = { "String", "Long", "Boolean"};
        String[] authors = { "Stelian Galmati", "Marius Pop"};
        cs.fill = GridBagConstraints.HORIZONTAL;

        lbUsername = new JLabel("Entity Name: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);

        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);

        lbPassword = new JLabel("Author: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);

        pfPassword = new JComboBox(authors);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));

        lbAttribute = new JLabel("Attribute: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(lbAttribute, cs);

        txAttribute = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(txAttribute, cs);
        panel.setBorder(new LineBorder(Color.GRAY));

        CmTypes = new JComboBox(types);
        cs.gridx = 3;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(CmTypes, cs);
        panel.setBorder(new LineBorder(Color.GRAY));

        btnLogin = new JButton("GenerateAPI");

        btnLogin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e){

                String entity = tfUsername.getText();
                String variable = entity.substring(0, 1).toLowerCase() + entity.substring(1);
                String author = pfPassword.getSelectedItem().toString();
                try {
                    String attribute = txAttribute.getText();
                    String type = CmTypes.getSelectedItem().toString();

                    EntityGenerator.usingBufferedWritter(entity,StringFormatService.formatDb(entity),author, type, attribute);
                    RepositoryGenerator.usingBufferedWritter(entity,author);
                    ServiceGenerator.usingBufferedWritter(entity, variable,author);
                    ResourceGenerator.usingBufferedWritter(entity, variable, StringFormatService.adjustPlural(StringFormatService.adjustDatabaseFormat(entity)),author, StringFormatService.adjustPlural(entity));
                    ResourceIntTestGenerator.usingBufferedWritter(entity, StringFormatService.formatDb(entity), author, type, attribute, variable, StringFormatService.adjustPlural(StringFormatService.adjustDatabaseFormat(entity)));
                    JOptionPane.showMessageDialog(Main.this,
                            "Api generated successfully ");                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                }


        });
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public String getUsername() {
        return tfUsername.getText().trim();
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public static void main(String[] args) {
        final JFrame frame = new JFrame("JDialog Demo");
        final JButton btnLogin = new JButton("API-Generator");

        btnLogin.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        Main loginDlg = new Main(frame);
                        loginDlg.setVisible(true);
                        // if logon successfully
                        if(loginDlg.isSucceeded()){
                            btnLogin.setText("Hi " + loginDlg.getUsername() + "!");
                        }
                    }
                });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().add(btnLogin);
        frame.setVisible(true);
    }

}