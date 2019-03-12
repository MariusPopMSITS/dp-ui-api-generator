import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.*;

public class Main extends JDialog {

    private JTextField pfPassword;
    private JTextField tfUsername;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;

    public Main(Frame parent) {
        super(parent, "Login", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

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

        pfPassword = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
        panel.setBorder(new LineBorder(Color.GRAY));

        btnLogin = new JButton("GenerateAPI");

        btnLogin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e){

                String entity = tfUsername.getText();
                String withoutLast = entity.substring(0,entity.length()-1);
                String getAllVariable="";
                if (entity.endsWith("s") ||
                        entity.endsWith("ss") ||
                        entity.endsWith("sh") ||
                        entity.endsWith("ch") ||
                        entity.endsWith("x") ||
                        entity.endsWith("z")) {
                    getAllVariable = entity + "es";
                }
                else {
                    if (entity.endsWith("y")) {
                        getAllVariable = withoutLast + "ies";
                    }else {
                        getAllVariable = entity + "s";
                    }

                }

                String variable = entity.substring(0, 1).toLowerCase() + entity.substring(1);
                String author = pfPassword.getText();
                String s = MainGenerator.generateDb(entity);
                String apiName = MainGenerator.generateApiName(entity);

                String a = s.replace(",","");
                String b=  a.replace("[","");
                String c = b.replace("]","");
                String d = c.replaceAll("\\s","");

                String ap = apiName.replace(",","");
                String bp=  ap.replace("[","");
                String cp = bp.replace("]","");
                String dp = cp.replaceAll("\\s","");

                String entityFinal = "";

                if (dp.endsWith("s") ||
                        dp.endsWith("ss") ||
                        dp.endsWith("sh") ||
                        dp.endsWith("ch") ||
                        dp.endsWith("x") ||
                        dp.endsWith("z")) {
                    entityFinal = dp + "es";
                }
                else {
                    if (dp.endsWith("y")) {
                        entityFinal = String.valueOf(dp.substring(0,dp.length()-1)).toLowerCase()+"ies";
                    }else {
                        entityFinal = dp + "s";
                    }
                }
                try {
                    EntityGenerator.usingBufferedWritter(entity,d,author);
                    RepositoryGenerator.usingBufferedWritter(entity,author);
                    ServiceGenerator.usingBufferedWritter(entity, variable,author);
                    ResourceGenerator.usingBufferedWritter(entity, variable, entityFinal,author, getAllVariable);

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