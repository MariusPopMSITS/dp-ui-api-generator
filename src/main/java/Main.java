import generator.*;
import service.StringFormatService;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.*;

public class Main extends JDialog {

    private JComboBox pfPassword;
    private JTextField tfUsername;
    private JTextField txAttribute;
    private JTextField txAttribute1;
    private JLabel lbAttribute1;
    private JComboBox CmTypes1;

    private JTextField txAttribute2;
    private JLabel lbAttribute2;
    private JComboBox CmTypes2;

    private JTextField txAttribute3;
    private JLabel lbAttribute3;
    private JComboBox CmTypes3;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JLabel lbAttribute;
    private JComboBox CmTypes;
    private JButton btnLogin;
    private JButton btnCancel;
    private JButton btnAddAttribute;
    private boolean succeeded;
    private boolean level1;
    private boolean level2;
    private boolean level3;
    private boolean pressedClicks = false;

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

        lbAttribute1 = new JLabel("Attribute: ");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        lbAttribute1.setVisible(false);
        panel.add(lbAttribute1, cs);

        txAttribute1 = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 2;
        panel.add(txAttribute1, cs);
        txAttribute1.setVisible(false);
        panel.setBorder(new LineBorder(Color.GRAY));

        CmTypes1 = new JComboBox(types);
        cs.gridx = 3;
        cs.gridy = 3;
        cs.gridwidth = 2;
        panel.add(CmTypes1, cs);
        CmTypes1.setVisible(false);
        panel.setBorder(new LineBorder(Color.GRAY));

        lbAttribute2 = new JLabel("Attribute: ");
        cs.gridx = 0;
        cs.gridy = 4;
        cs.gridwidth = 1;
        lbAttribute2.setVisible(false);
        panel.add(lbAttribute2, cs);

        txAttribute2 = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 4;
        cs.gridwidth = 2;
        panel.add(txAttribute2, cs);
        txAttribute2.setVisible(false);
        panel.setBorder(new LineBorder(Color.GRAY));

        CmTypes2 = new JComboBox(types);
        cs.gridx = 3;
        cs.gridy = 4;
        cs.gridwidth = 2;
        panel.add(CmTypes2, cs);
        CmTypes2.setVisible(false);
        panel.setBorder(new LineBorder(Color.GRAY));

        lbAttribute3 = new JLabel("Attribute: ");
        cs.gridx = 0;
        cs.gridy = 5;
        cs.gridwidth = 1;
        lbAttribute3.setVisible(false);
        panel.add(lbAttribute3, cs);

        txAttribute3 = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 5;
        cs.gridwidth = 2;
        panel.add(txAttribute3, cs);
        txAttribute3.setVisible(false);
        panel.setBorder(new LineBorder(Color.GRAY));

        CmTypes3 = new JComboBox(types);
        cs.gridx = 3;
        cs.gridy = 5;
        cs.gridwidth = 2;
        panel.add(CmTypes3, cs);
        CmTypes3.setVisible(false);
        panel.setBorder(new LineBorder(Color.GRAY));

        btnLogin = new JButton("GenerateAPI");
        Map<String, String> attributes = new HashMap<>();

        btnLogin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e){

                if (level1 && !level2 && !level3) {
                    attributes.put(txAttribute1.getText(),CmTypes1.getSelectedItem().toString());
                }
                if (level1 && level2 && !level3) {
                    attributes.put(txAttribute1.getText(),CmTypes1.getSelectedItem().toString());
                    attributes.put(txAttribute2.getText(),CmTypes.getSelectedItem().toString());
                }
                if (level1 && level2 && level3) {
                    attributes.put(txAttribute1.getText(),CmTypes1.getSelectedItem().toString());
                    attributes.put(txAttribute2.getText(),CmTypes2.getSelectedItem().toString());
                    attributes.put(txAttribute3.getText(),CmTypes3.getSelectedItem().toString());
                }

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
                            "Api generated successfully ");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                }


        });
        btnCancel = new JButton("Cancel");
        btnAddAttribute = new JButton("+");

        btnAddAttribute.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e){

                pressedClicks = true;
                if (level1 == false && pressedClicks)
                {
                    System.out.println("am intrat aici 1 ");
                    lbAttribute1.setVisible(true);
                    txAttribute1.setVisible(true);
                    CmTypes1.setVisible(true);
                    level1 =true;
                    pressedClicks = false;
                }

                if (level1 == true && level2 == false && pressedClicks )
                {
                    System.out.println("am intrat aici 2 ");
                    lbAttribute2.setVisible(true);
                    txAttribute2.setVisible(true);
                    CmTypes2.setVisible(true);
                    level2 =true;
                    pressedClicks = false;
                }

                if (level1 == true && level2 == true && level3 == false && pressedClicks )
                {
                    System.out.println("am intrat aici 3");
                    lbAttribute3.setVisible(true);
                    txAttribute3.setVisible(true);
                    CmTypes3.setVisible(true);
                    level3 =true;
                    btnAddAttribute.setVisible(false);
                    pressedClicks = false;
                }




            }


        });

        btnCancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnAddAttribute);
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
                        loginDlg.setSize(500,500);
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