import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//go through readme.txt before running the files
// ***Please perform the steps mentioned in the comments before start of project***
// use Xamp or local sql database server to create database 
// add the jar file in references in classpath to ensure database connectivity
// Create Databse named "bank_data"
// insert a table "users" containing column as follows:
// acc
// passwords
// age
// naam
// contact
// balance
// set primary key to "acc" in "users"
// create another table "token" containing following column
// token
// insert value 1 in token
/*You can use the following queries to perform the above action
 * create table users(
	acc varchar(100),
	naam varchar(100),
    passwords varchar(100),
    age varchar(100),
    contact varchar(100),
    balance varchar(100),
    primary key(acc)
	);
 * create table token(
	token varchar(6)
    );
 * 
 */

class customer {
    public
    static Scanner sc=new Scanner(System.in);
    int accountnumber;
    String password;
    int age;
    String Name;
    long Contact;
    Double Balance;
    Double debit;
    float credit_score;
    double credit;
    long loan_amount;
    long installment_left;
    long installment_amount;
    static void query(String q){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bank_data";
            String user = "root";
            String password = "Amanshivdasani012";

            Connection connection = DriverManager.getConnection(url, user, password);
            connection.prepareStatement(q).executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    void insert(String acc){
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String Url="jdbc:mysql://localhost:3306/bank_data";
        String user="root";
        String pass="Amanshivdasani012";
        Connection connection=DriverManager.getConnection(Url, user, pass);
        ResultSet details =connection.createStatement().executeQuery("select * from users\nwhere acc="+acc+";");
        details.next();
        accountnumber=details.getInt("acc");
        password=details.getString("passwords");
        age=details.getInt("age");
        Name=details.getString("naam");
        Contact=details.getLong("contact");
        Balance=details.getDouble("balance");
        details.close();
        connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }


    void show_bal(){
        JFrame frame = new JFrame("Balance");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 400);
                frame.setLayout(new BorderLayout());
                frame.setBackground(new Color(58,175,169));

                JPanel panel = new JPanel();
                panel.setLayout(null);
                

                JLabel showbal=new JLabel("The balance is "+Balance);
                showbal.setBounds(30,100,380,60);
                Font customfont= new Font("calibri", Font.ITALIC, 30);
                showbal.setFont(customfont);
                panel.add(showbal);
                
                JButton goback = new JButton("Back");
                goback.setBounds(80, 250, 165, 35);
                goback.setBackground(new Color(23,37,42));
                goback.setBorder(BorderFactory.createLineBorder(Color.white, 2));
                goback.setForeground(Color.WHITE);
                goback.setFocusPainted(false);
                panel.add(goback);
                panel.setBackground(new Color(58,175,169));
                frame.add(panel,BorderLayout.CENTER);
                goback.addActionListener( new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        frame.dispose();
                    }
                });
                frame.setVisible(true);
                // System.out.println("The balance is "+Balance);
            }

    void withdraw(JFrame page){
        JFrame frame = new JFrame("Withdraw MOney");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(58,175,169));

        JLabel Amount = new JLabel("Enter Amount: ");
        Amount.setBounds(10, 20, 110, 25);
        Amount.setForeground(Color.WHITE);
        JTextField amountField = new JTextField(10);
        amountField.setBounds(10, 45, 165, 25);
        amountField.setForeground(Color.WHITE);
        amountField.setBackground(new Color(43, 122, 120));
        amountField.setBorder(null);

        JLabel passlabel= new JLabel("Password: ");
        passlabel.setBounds(10, 90, 110, 25);
        passlabel.setForeground(Color.WHITE);
        JTextField passField = new JTextField(10);
        passField.setBounds(10, 115, 165, 25);
        passField.setForeground(Color.WHITE);
        passField.setBackground(new Color(43, 122, 120));
        passField.setBorder(null);
        Font customfont= new Font("Arial", Font.BOLD, 14);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(10, 200, 135, 35);
        withdrawButton.setBackground(new Color(23,37,42));
        withdrawButton.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        withdrawButton.setForeground(Color.WHITE);
        withdrawButton.setFocusPainted(false);
        withdrawButton.setFont(customfont);

        JButton goback = new JButton("Back");
        goback.setBounds(160, 200, 135, 35);
        goback.setBackground(new Color(23,37,42));
        goback.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        goback.setForeground(Color.WHITE);
        goback.setFocusPainted(false);
        goback.setFont(customfont);
        panel.add(passlabel);
        panel.add(Amount);
        panel.add(amountField);
        panel.add(passField);
        panel.add(withdrawButton);
        panel.add(goback);
        frame.add(panel,BorderLayout.CENTER);
        withdrawButton.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Double amount=Double.parseDouble(amountField.getText());
                String pass=passField.getText();
                if (pass.equals(password)){
                    Balance-=amount;
                    query("insert into "+accountnumber+"debit(amount)values("+amount+");");
                    query("Update users Set balance ="+Balance+" where acc ="+accountnumber+";");
                    JLabel complete= new JLabel("Amount withdrawn Successfully");
                    complete.setBounds(30, 160, 200, 15);
                    panel.add(complete);
                    panel.repaint();
                }
                else{
                    JLabel complete= new JLabel("Wrong Password!!");
                    complete.setBounds(10, 240, 200, 15);
                    panel.add(complete);
                    panel.repaint();

                }
            }
        });
        goback.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                page.setVisible(true);
                frame.dispose();
            }
        });
        frame.setVisible(true);
    }
    void transfer(JFrame page){

        JFrame frame = new JFrame("Deposit MOney");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(58,175,169));

        JLabel other = new JLabel("Reciever account no.: ");
        other.setBounds(10, 20, 160, 25);
        other.setForeground(Color.WHITE);
        JTextField otherField = new JTextField(10);
        otherField.setBounds(10, 45, 165, 25);
        otherField.setForeground(Color.WHITE);
        otherField.setBackground(new Color(43, 122, 120));
        otherField.setBorder(null);

        JLabel Amount = new JLabel("Enter Amount: ");
        Amount.setBounds(10, 90, 110, 25);
        Amount.setForeground(Color.WHITE);
        JTextField amountField = new JTextField(10);
        amountField.setBounds(10, 115, 165, 25);
        amountField.setForeground(Color.WHITE);
        amountField.setBackground(new Color(43, 122, 120));
        amountField.setBorder(null);

        JLabel passlabel= new JLabel("Password: ");
        passlabel.setBounds(10, 160, 110, 25);
        passlabel.setForeground(Color.WHITE);
        JTextField passField = new JTextField(10);
        passField.setBounds(10, 185, 165, 25);
        passField.setForeground(Color.WHITE);
        passField.setBackground(new Color(43, 122, 120));
        passField.setBorder(null);
        Font customfont= new Font("Arial", Font.BOLD, 14);

        JButton transferButton = new JButton("transfer");
        transferButton.setBounds(10, 220, 145, 35);
        transferButton.setBackground(new Color(23,37,42));
        transferButton.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        transferButton.setForeground(Color.WHITE);
        transferButton.setFocusPainted(false);
        transferButton.setFont(customfont);
        JButton goback = new JButton("Back");
        goback.setBounds(170, 220, 145, 35);
        goback.setBackground(new Color(23,37,42));
        goback.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        goback.setForeground(Color.WHITE);
        goback.setFocusPainted(false);
        goback.setFont(customfont);
        panel.add(goback);
        panel.add(other);
        panel.add(otherField);
        panel.add(passlabel);
        panel.add(Amount);
        panel.add(amountField);
        panel.add(passField);
        panel.add(transferButton);
        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);
        transferButton.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                    double amount= Double.parseDouble(amountField.getText());
                    int rec=Integer.parseInt(otherField.getText());
                    String pass=passField.getText();
        if (pass.equals(password)){
            Balance-=amount;
            query("insert into "+accountnumber+"debit(amount)values("+amount+");");
            query("insert into "+rec+"credit(amount)values("+amount+");");
            JLabel complete= new JLabel("Amount transfered Successfully");
                complete.setBounds(10, 270, 200, 15);
                panel.add(complete);
                panel.repaint();
            try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String Url="jdbc:mysql://localhost:3306/bank_data";
            String user="root";
            String w="Amanshivdasani012";
            Connection connection=DriverManager.getConnection(Url, user, w);
            ResultSet details =connection.createStatement().executeQuery("select * from users\nwhere acc="+rec+";");
            while (details.next()) {
                double bal=details.getDouble("balance");
                bal+=amount;
                query("Update users Set balance ="+bal+" where acc ="+rec+";");
            }
            }catch(Exception err){
                System.out.println(err);
            }
            }
        else{
            JLabel complete= new JLabel("wrong Password!");
            complete.setBounds(10, 290, 200, 15);
            panel.add(complete);
            panel.repaint();
        }
        }
        });
        goback.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                page.setVisible(true);
                frame.dispose();
            }
        });
    }
    void Deposit(JFrame page){
        JFrame frame = new JFrame("Deposit MOney");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(58,175,169));

        JLabel Amount = new JLabel("Enter Amount: ");
        Amount.setBounds(10, 20, 110, 25);
        Amount.setForeground(Color.WHITE);
        JTextField amountField = new JTextField(10);
        amountField.setBounds(10, 45, 165, 25);
        amountField.setForeground(Color.WHITE);
        amountField.setBackground(new Color(43, 122, 120));
        amountField.setBorder(null);

        JLabel passlabel= new JLabel("Password: ");
        passlabel.setBounds(10, 90, 110, 25);
        passlabel.setForeground(Color.WHITE);
        JTextField passField = new JTextField(10);
        passField.setBounds(10, 115, 165, 25);
        passField.setForeground(Color.WHITE);
        passField.setBackground(new Color(43, 122, 120));
        passField.setBorder(null);
        Font customfont= new Font("Arial", Font.BOLD, 14);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(10, 200, 135, 35);
        depositButton.setBackground(new Color(23,37,42));
        depositButton.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        depositButton.setForeground(Color.WHITE);
        depositButton.setFocusPainted(false);
        depositButton.setFont(customfont);

        JButton goback = new JButton("Back");
        goback.setBounds(67, 250, 165, 35);
        goback.setBounds(160, 200, 135, 35);
        goback.setBackground(new Color(23,37,42));
        goback.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        goback.setForeground(Color.WHITE);
        goback.setFocusPainted(false);
        goback.setFont(customfont);
        panel.add(goback);
    
        panel.add(passlabel);
        panel.add(Amount);
        panel.add(amountField);
        panel.add(passField);
        panel.add(depositButton);
        frame.add(panel,BorderLayout.CENTER);
        depositButton.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Double amount=Double.parseDouble(amountField.getText());
                String pass=passField.getText();
                if (pass.equals(password)){
                    Balance+=amount;
                    query("insert into "+accountnumber+"credit(amount)values("+amount+");");
                    query("Update users SET Balance ="+Balance+ "where acc ="+accountnumber+";");
                    JLabel complete= new JLabel("Amount deposited Successfully");
                    complete.setBounds(30, 160, 200, 15);
                    panel.add(complete);
                    panel.repaint();
                }
                else{
                    JLabel complete= new JLabel("Wrong Password!!");
                    complete.setBounds(10, 240, 200, 15);
                    panel.add(complete);
                    panel.repaint();

                }
            }

        });
        goback.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                page.setVisible(true);
                frame.dispose();
            }
        });
        frame.setVisible(true);
    }

    void show_payments_done(JFrame page){
        JFrame frame = new JFrame("payment Transactions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());
        Font customfont= new Font("Arial", Font.BOLD, 14);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(58,175,169));
        JButton goback = new JButton("Back");
        goback.setBounds(67, 250, 165, 35);
        goback.setBackground(new Color(23,37,42));
        goback.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        goback.setForeground(Color.WHITE);
        goback.setFocusPainted(false);
        goback.setFont(customfont);
        panel.add(goback);
        panel.setLayout(null);
        frame.add(panel,BorderLayout.CENTER);
        goback.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                page.setVisible(true);
                frame.dispose();
            }
        });
        frame.setVisible(true);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bank_data";
            String user = "root";
            String password = "Amanshivdasani012";

            Connection connection = DriverManager.getConnection(url, user, password);
            ResultSet resultSet = connection.createStatement().executeQuery("select * from "+accountnumber+"debit");
            System.out.println("Payment History");
            int i=0;
            while (resultSet.next()) {
                JLabel complete= new JLabel("Transaction of rupees :"+resultSet.getString("amount"));
                complete.setBounds(10, 20+i, 200, 15);
                panel.add(complete);
                panel.repaint();
                i+=30;
            }
            resultSet.close();
            connection.close();}
            catch(Exception e){
                System.out.println(e);
            }
    }
    void show_payments_recieved(JFrame page){
        JFrame frame = new JFrame("payment Transactions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(58,175,169));
        JButton goback = new JButton("Back");
        goback.setBounds(67, 250, 165, 35);
        goback.setBackground(new Color(23,37,42));
        goback.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        goback.setForeground(Color.WHITE);
        goback.setFocusPainted(false);
        panel.add(goback);
        frame.add(panel,BorderLayout.CENTER);
        goback.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                page.setVisible(true);
                frame.dispose();
            }
        });
        frame.setVisible(true);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bank_data";
            String user = "root";
            String password = "Amanshivdasani012";

            Connection connection = DriverManager.getConnection(url, user, password);
            ResultSet resultSet = connection.createStatement().executeQuery("select * from "+accountnumber+"credit");
            System.out.println("Payment History");
            int i=0;
            while (resultSet.next()) {
                JLabel complete= new JLabel("Transaction of rupees :"+resultSet.getString("amount"));
                complete.setBounds(10, 20+i, 200, 15);
                panel.add(complete);
                panel.repaint();
                i+=30;
            }
            resultSet.close();
            connection.close();}
            catch(Exception e){
                System.out.println(e);
            }
    }

  
    
}
public class myproject{
    
    static Scanner sc= new Scanner(System.in);

    static void start(){
        Font customfont= new Font("Arial", Font.BOLD, 14);
        JFrame frame = new JFrame("Login Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(58,175,169));

        
        JButton signinButton = new JButton("SIGN IN");
        signinButton.setBounds(50 ,70, 100, 60);
        signinButton.setBackground(new Color(23,37,42));
        signinButton.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        signinButton.setForeground(Color.WHITE);
        signinButton.setFocusPainted(false);
        signinButton.setFont(customfont);
        JButton signup = new JButton("SIGN UP");
        signup.setBounds(220 ,70 ,100, 60);
        signup.setBackground(new Color(23,37,42));
        signup.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        signup.setForeground(Color.WHITE);
        signup.setFocusPainted(false);
        signup.setFont(customfont);
        JButton forgot = new JButton("FORGOT PASSWORD");
        forgot.setBounds(80 ,180, 200, 60);
        forgot.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        forgot.setBackground(new Color(23,37,42));
        forgot.setForeground(Color.WHITE);
        forgot.setFocusPainted(false);
        forgot.setFont(customfont);
        
        panel.add(signinButton);
        panel.add(new JLabel());
        panel.add(signup);
        panel.add(forgot);

        frame.add(panel, BorderLayout.CENTER);

        
        signinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                signin();
            }
        });
        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                signup();

            }
        });
        forgot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                forgot();
                
            }
        });

        frame.setVisible(true);
    }

    static void signin(){
        Font cus = new Font("Arial", Font.BOLD, 14);
        JFrame frame = new JFrame("Login Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(58,175,169));

        JLabel accountNumberLabel = new JLabel("Account Number:");
        accountNumberLabel.setBounds(95, 20, 130, 25);
        accountNumberLabel.setForeground(Color.WHITE);
        JTextField accountNumberField = new JTextField(10);
        accountNumberField.setBounds(95, 45, 165, 25);
        accountNumberField.setBackground(new Color(43, 122, 120));
        accountNumberField.setBorder(null);
        accountNumberField.setForeground(Color.WHITE);
       


        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(95, 90, 110, 25);
        passwordLabel.setForeground(Color.WHITE);
        JPasswordField passwordField = new JPasswordField(10);
        passwordField.setBounds(95, 115, 165, 25);
        passwordField.setBackground(new Color(43, 122, 120));
        passwordField.setBorder(null);
        passwordField.setForeground(Color.WHITE);
        

        JButton loginButton = new JButton("LOGIN");
        loginButton.setBounds(120, 190, 120, 35);
        loginButton.setBackground(new Color(23,37,42));
        loginButton.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(cus);

        JButton goback = new JButton("BACK");
        goback.setBounds(120, 250, 120, 35);
        goback.setBackground(new Color(23,37,42));
        goback.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        goback.setForeground(Color.WHITE);
        goback.setFocusPainted(false);
        goback.setFont(cus);
        panel.add(goback);

        panel.add(accountNumberLabel);
        panel.add(accountNumberField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        frame.add(panel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = accountNumberField.getText();
                String password = passwordField.getText();
                verify(account, password,frame);
                JLabel trys = new JLabel("Please Try Again ");
                trys.setBounds(120, 160, 210, 25);
                trys.setFont(cus);
                panel.add(trys);
                panel.repaint();

                // System.out.println("Please Try again");
                return;
            }
        });
        goback.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                start();
            }
        });

        frame.setVisible(true);
    }

    static void signup(){
        Font cus = new Font("Arial", Font.BOLD, 14);
        customer wow =new customer();
        JFrame frame = new JFrame("Login Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(58,175,169));


        JLabel mobileNumberLabel = new JLabel("Mobile Number: ");
        mobileNumberLabel.setBounds(10, 20, 110, 25);
        mobileNumberLabel.setForeground(Color.WHITE);
        JTextField mobileNumberField = new JTextField(10);
        mobileNumberField.setBounds(10, 45, 165, 25);
        mobileNumberField.setBackground(new Color(43, 122, 120));
        mobileNumberField.setBorder(null);
        mobileNumberField.setForeground(Color.WHITE);
        
        

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(200, 20, 110, 25);
        passwordLabel.setForeground(Color.WHITE);
        JPasswordField passwordField = new JPasswordField(10);
        passwordField.setBounds(200, 45, 165, 25);
        passwordField.setBackground(new Color(43, 122, 120));
        passwordField.setBorder(null);
        passwordField.setForeground(Color.WHITE);
        

        JLabel balanceLabel = new JLabel("Balance: ");
        balanceLabel.setBounds(10, 90, 110, 25);
        balanceLabel.setForeground(Color.WHITE);
        JTextField balanceField = new JTextField(10);
        balanceField.setBounds(10, 115, 165, 25);
        balanceField.setBackground(new Color(43, 122, 120));
        balanceField.setBorder(null);
        balanceField.setForeground(Color.WHITE);
        

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(200, 90, 110, 25);
        nameLabel.setForeground(Color.WHITE);
        JTextField nameField = new JTextField(10);
        nameField.setBounds(200, 115, 165, 25);
        nameField.setBackground(new Color(43, 122, 120));
        nameField.setBorder(null);
        nameField.setForeground(Color.WHITE);
        

        JLabel ageLabel = new JLabel("Age: ");
        ageLabel.setBounds(10, 160, 110, 25);
        ageLabel.setForeground(Color.WHITE);
        JTextField ageField = new JTextField(10);
        ageField.setBounds(10, 185, 165, 25);
        ageField.setBackground(new Color(43, 122, 120));
        ageField.setBorder(null);
        ageField.setForeground(Color.WHITE);
        

        JButton loginButton = new JButton("Sign Up");
        loginButton.setBounds(10, 230, 145, 35);
        loginButton.setBackground(new Color(23,37,42));
        loginButton.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(cus);

        JButton goback = new JButton("Back");
        goback.setBounds(170, 230, 145, 35);
        goback.setBackground(new Color(23,37,42));
        goback.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        goback.setForeground(Color.WHITE);
        goback.setFocusPainted(false);
        goback.setFont(cus);
        panel.add(goback);

        panel.add(mobileNumberLabel);
        panel.add(mobileNumberField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(balanceLabel);
        panel.add(balanceField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(loginButton);

        frame.add(panel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                try{
                wow.Contact = Long.parseLong(mobileNumberField.getText());
                wow.password = passwordField.getText();
                wow.Balance = Double.parseDouble(balanceField.getText());
                wow.Name= nameField.getText();
                wow.age = Integer.parseInt(ageField.getText());
                }
                catch (Exception e){
                    JLabel endstate = new JLabel("No field Can be empty");
                    endstate.setBounds(50, 260, 400, 20);
                    panel.add(endstate);
                    frame.add(panel, BorderLayout.CENTER);
                    frame.repaint();
                    return;
                }
                if (wow.Name.length()>=1 && Double.toString(wow.Balance).length()>=1 && Integer.toString(wow.age).length()>=1){
                if (Long.toString(wow.Contact).length()==10){
                if (wow.password.length()>8){

                try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String Url="jdbc:mysql://localhost:3306/bank_data";
        String user="root";
        String pass="Amanshivdasani012";
        Connection connection=DriverManager.getConnection(Url, user, pass);

        ResultSet r=connection.createStatement().executeQuery("select * from token;");
        while (r.next()) {
            wow.accountnumber=r.getInt("token");
        }
        r.close();
        connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        query("Update token Set token="+(wow.accountnumber+1)+" where token ="+wow.accountnumber+";");
        query("insert into users(acc,naam,passwords,age,contact,balance)\nvalues("+Integer.toString(wow.accountnumber)+",\"Amanshivdasani012"+ wow.Name +"\" ,'"+wow.password+"',"+wow.age+","+wow.Contact+","+wow.Balance+");");
        query("create table "+wow.accountnumber+"debit(amount varchar(100));");
        query("create table "+wow.accountnumber+"credit(amount varchar(100));");
        query("create table "+wow.accountnumber+"loan(loan_id varchar(100),\n" + //
                "    loan_amount varchar(100),\n" + 
                "    installments varchar(100),\n" + //
                "    installment_amount varchar(100),\n" + //
                "    loan_left varchar(100),\n" + //
                "    installment_left varchar(100),\n" + //
                "    loan_status varchar(100)\n" + //
                ");");
        query("insert into "+wow.accountnumber+"loan() values(0,0,0,0,0,0,0)");
        wow.credit= wow.Balance;
        query("insert into "+wow.accountnumber+"credit(amount)\nvalues("+wow.credit+")");
        JLabel endstate = new JLabel("Account Created, \n account number which is "+wow.accountnumber);
        endstate.setBounds(10, 280, 400, 50);
        endstate.setForeground(Color.white);
        endstate.setFont(cus);
        panel.add(endstate);
        panel.remove(loginButton);
        frame.add(panel, BorderLayout.CENTER);
        frame.repaint();
    }
    else{
        JLabel endstate = new JLabel("Password Length must be greater than 8");
        endstate.setBounds(50, 310, 400, 20);
        panel.add(endstate);
        frame.add(panel, BorderLayout.CENTER);
        frame.repaint();
    }
}       
    else{
        JLabel endstate = new JLabel("Contact Length must be 10 digits");
        endstate.setBounds(50, 340, 400, 20);
        panel.add(endstate);
        frame.add(panel, BorderLayout.CENTER);
        frame.repaint();
    }
}
    else{
        JLabel endstate = new JLabel("No field should be empty");
        endstate.setBounds(50, 360, 400, 20);
        panel.add(endstate);
        frame.add(panel, BorderLayout.CENTER);
        frame.repaint();
    }
            }
        });
        goback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                frame.dispose();
                start();
            }
        });
        

        frame.setVisible(true);
    }
    static void forgot(){
        Font cus = new Font("Arial", Font.BOLD, 14);
        JFrame frame = new JFrame("Login Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(58,175,169));

        JLabel accountNumberLabel = new JLabel("Account Number: ");
        accountNumberLabel.setBounds(10, 20, 130, 25);
        accountNumberLabel.setForeground(Color.WHITE);
        JTextField accountNumberField = new JTextField(10);
        accountNumberField.setBounds(10, 45, 165, 25);
        accountNumberField.setBackground(new Color(43, 122, 120));
        accountNumberField.setBorder(null);
        accountNumberField.setForeground(Color.WHITE);

        JLabel mobileNumberLabel = new JLabel("Mobile Number: ");
        mobileNumberLabel.setBounds(10, 90, 130, 25);
        mobileNumberLabel.setForeground(Color.WHITE);
        JTextField mobileNumberField = new JTextField(10);
        mobileNumberField.setBounds(10,115, 165, 25);
        mobileNumberField.setBackground(new Color(43, 122, 120));
        mobileNumberField.setBorder(null);
        mobileNumberField.setForeground(Color.WHITE);

        JButton loginButton = new JButton("LOGIN");
        loginButton.setBounds(10, 200, 135, 35);
        loginButton.setBackground(new Color(23,37,42));
        loginButton.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(cus);

        JButton goback = new JButton("BACK");
        goback.setBounds(150, 200, 135, 35);
        goback.setBackground(new Color(23,37,42));
        goback.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        goback.setForeground(Color.WHITE);
        goback.setFocusPainted(false);
        goback.setFont(cus);
        panel.add(goback);

        panel.add(accountNumberLabel);
        panel.add( mobileNumberField);
        panel.add(mobileNumberLabel);
        panel.add(accountNumberField);
        panel.add(loginButton);
        frame.add(panel, BorderLayout.CENTER);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent err) {
                String account = accountNumberField.getText();
                String contact = mobileNumberField.getText();
                System.out.println(account);
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/bank_data";
                    String user = "root";
                    String password = "Amanshivdasani012";

                    Connection connection = DriverManager.getConnection(url, user, password);
                    ResultSet resultSet=connection.createStatement().executeQuery("select * from users");
                    while (resultSet.next()) {
                        try{
                            String a=resultSet.getString("acc");
                            String b=resultSet.getString("contact");
                            System.out.println(a);
                        
                            if  (account.equals(a) &&contact.equals(b)){
                                JLabel newplabel=new JLabel("Enter New password");
                                newplabel.setBounds(10, 20, 150, 25);
                                JTextField newp = new JTextField(10);
                                newp.setBounds(130, 20, 165, 25);
                                JLabel confirmlabel = new JLabel("Re Enter Password: ");
                                confirmlabel.setBounds(10, 50, 150, 25);
                                JTextField confirm = new JTextField(10);
                                confirm.setBounds(130, 50, 165, 25);
                                JButton verifyButton=new JButton("Verify");
                                verifyButton.setBounds(67, 200, 165, 35);
                                panel.removeAll();
                                panel.add(newplabel);
                                panel.add(newp);
                                panel.add(confirmlabel);
                                panel.add(confirm);
                                panel.add(verifyButton);
                                frame.add(panel,BorderLayout.CENTER);
                                frame.repaint();
                                    verifyButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent err) {
                                            String p=newp.getText();
                                            String np=confirm.getText();
                                            if (p.equals(np)){
                                                query("Update users Set passwords ="+np+" where acc ="+account);
                                                JLabel changed=new JLabel("password has been changed successfully");
                                                changed.setBounds(67, 200, 165, 35);
                                                panel.remove(verifyButton);
                                                panel.add(changed);
                                                frame.add(panel,BorderLayout.CENTER);
                                                frame.repaint();
                                                System.out.println("password has been changed successfully");
                                            }
                                            else{
                                                System.out.println("try again!!");
                                            }
                                        }
                                    });
                                return;
                            }
                            
                        }
                            
                        catch(Exception e){
                            System.out.println(e);
                        }
                    }
                    JLabel change=new JLabel("Details Don't match");
                    change.setBounds(100, 100, 165, 35);
                    panel.add(change);
                    frame.add(panel,BorderLayout.CENTER);
                    frame.repaint();
                    resultSet.close();
                    connection.close();                        
                } 
                catch (Exception e) {
                    System.out.println(e);
                }

            }
        });
        goback.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                start();
            }
        });
        frame.setVisible(true);
    }
            
    
    static void homepage(String acc){
        Font cus = new Font("Arial", Font.BOLD, 14);
        customer user=new customer();
        user.insert(acc);
        JFrame frame = new JFrame("Welcome to Bank Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(58,175,169));

        
        JButton BalanceButton = new JButton("SHOW BALANCE");
        BalanceButton.setBounds(10 ,30, 180, 45);
        BalanceButton.setBackground(new Color(23,37,42));
        BalanceButton.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        BalanceButton.setForeground(Color.WHITE);
        BalanceButton.setFocusPainted(false);
        BalanceButton.setFont(cus);
        JButton withdrawbutton = new JButton("WITHDRAW MONEY");
        withdrawbutton.setBounds(200 ,30, 180, 45);
        withdrawbutton.setBackground(new Color(23,37,42));
        withdrawbutton.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        withdrawbutton.setForeground(Color.WHITE);
        withdrawbutton.setFocusPainted(false);
        withdrawbutton.setFont(cus);
        JButton depositButton = new JButton("DEPOSIT MONEY");
        depositButton.setBounds(10 ,100 ,180, 45);
        depositButton.setBackground(new Color(23,37,42));
        depositButton.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        depositButton.setForeground(Color.WHITE);
        depositButton.setFocusPainted(false);
        depositButton.setFont(cus);
        JButton transferButton = new JButton("TRANSFER MONEY");
        transferButton.setBounds(200 ,100, 180, 45);
        transferButton.setBackground(new Color(23,37,42));
        transferButton.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        transferButton.setForeground(Color.WHITE);
        transferButton.setFocusPainted(false);
        transferButton.setFont(cus);
        JButton payments_doneButton = new JButton("PAYMENTS DONE");
        payments_doneButton.setBounds(10 ,170, 180, 45);
        payments_doneButton.setBackground(new Color(23,37,42));
        payments_doneButton.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        payments_doneButton.setForeground(Color.WHITE);
        payments_doneButton.setFocusPainted(false);
        payments_doneButton.setFont(cus);
        JButton payments_recievedButton = new JButton("PAYMENT RECEIVED");
        payments_recievedButton.setBounds(200 ,170, 180, 45);
        payments_recievedButton.setBackground(new Color(23,37,42));
        payments_recievedButton.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        payments_recievedButton.setForeground(Color.WHITE);
        payments_recievedButton.setFocusPainted(false);
        payments_recievedButton.setFont(cus);
        JButton loansectionButton = new JButton("LOAN");
        loansectionButton.setBounds(80 ,240, 200, 45);
        loansectionButton.setBackground(new Color(23,37,42));
        loansectionButton.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        loansectionButton.setForeground(Color.WHITE);
        loansectionButton.setFocusPainted(false);
        loansectionButton.setFont(cus);
        
        panel.add(BalanceButton);
        panel.add(withdrawbutton);
        panel.add(depositButton);
        panel.add(transferButton);
        panel.add(payments_doneButton);
        panel.add(payments_recievedButton);
        panel.add(loansectionButton);

        frame.add(panel, BorderLayout.CENTER);

        
        BalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.show_bal();
            }
        });
        withdrawbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                user.withdraw(frame);
            }
        });
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                user.Deposit(frame);;
                
            }
        });
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                user.transfer(frame);
                
            }
        });
        payments_doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                user.show_payments_done(frame);;
                
            }
        });        
        payments_recievedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                user.show_payments_recieved(frame);
                
            }
        });
        loansectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                loan(user);
                
            }
        });

        frame.setVisible(true);
    }

    
    
    static void query(String q){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bank_data";
            String user = "root";
            String password = "Amanshivdasani012";

            Connection connection = DriverManager.getConnection(url, user, password);
            connection.prepareStatement(q).executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
    }
    static void loan(customer obj){
        JFrame frame = new JFrame("Login Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(58,175,169));
        Font customfont= new Font("Arial", Font.BOLD, 14);

        
        JButton apply = new JButton("Apply for Loan");
        apply.setBounds(80 ,20, 200, 35);
        apply.setBackground(new Color(23,37,42));
        apply.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        apply.setForeground(Color.WHITE);
        apply.setFocusPainted(false);
        apply.setFont(customfont);
        JButton status = new JButton("check loan status");
        status.setBounds(80 ,100 ,200, 35);
        status.setBackground(new Color(23,37,42));
        status.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        status.setForeground(Color.WHITE);
        status.setFocusPainted(false);
        status.setFont(customfont);
        JButton score = new JButton("check credit score");
        score.setBounds(80 ,180, 200, 35);
        score.setBackground(new Color(23,37,42));
        score.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        score.setForeground(Color.WHITE);
        score.setFocusPainted(false);
        score.setFont(customfont);

        JButton goback = new JButton("Back");
        goback.setBounds(80, 260, 200, 35);
        goback.setBackground(new Color(23,37,42));
        goback.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        goback.setForeground(Color.WHITE);
        goback.setFocusPainted(false);
        goback.setFont(customfont);
        panel.add(goback);
        
        panel.add(apply);
        panel.add(status);
        panel.add(score);

        frame.add(panel, BorderLayout.CENTER);

        
        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                loan_application(obj);
            }
        });
        status.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                loan_status(obj);

            }
        });
        score.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                creditscore(obj);
                
            }
        });
        goback.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                homepage(Integer.toString(obj.accountnumber));
            }
        });

        frame.setVisible(true);
    }

    
    //Loan status prints all the loan applied by the user by using database table named {accountnumber}loan
    static void loan_status(customer obj){
        JFrame frame = new JFrame("Login Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(58,175,169));
        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);
        JButton goback = new JButton("Back");
        goback.setBounds(67, 0, 165, 35);
        goback.setBackground(new Color(23,37,42));
        goback.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        goback.setForeground(Color.WHITE);
        goback.setFocusPainted(false);
        panel.add(goback);

        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String Url="jdbc:mysql://localhost:3306/bank_data";
        String user="root";
        String pass="Amanshivdasani012";
        Connection connection=DriverManager.getConnection(Url, user, pass);
        ResultSet details =connection.createStatement().executeQuery("select * from "+obj.accountnumber+"loan;");
        details.next();
        int i=0;
        while (details.next()) {
            String a="|  loan_id:"+details.getString(1)+"  |  Loan Amount :"+details.getString(1)+"  |  Total installments "+details.getString(2)+"  |  Installment Amount"+details.getString(3)+"  |  Loan Left "+details.getString(4)+"  |  Installment Left"+details.getString(5)+"  |  Loan status"+details.getString(6)+"  |  ";
            JLabel complete= new JLabel(a);
            complete.setBounds(50, 40+i, 200, 15);
            panel.add(complete);
            panel.repaint();
            i+=30;
        }
        if(i==0){
            JLabel found= new JLabel("No Loans Taken");
            found.setBounds(110, 20+i, 200, 15);
            panel.add(found);
            panel.repaint();
        }
        details.close();
        connection.close();
        }catch(Exception e){
            System.out.println(e);
        }
        goback.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                homepage(Integer.toString(obj.accountnumber));
            }
        });
    }

    //Loan Application: Apply for Loan 

    static void loan_application(customer obj){
        if (obj.credit_score==0.0){
                creditscore(obj);
                return;
            }
        JFrame frame = new JFrame("Loan Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(58,175,169));
        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);

        JLabel amountlabel= new JLabel("Loan amount:");
        amountlabel.setBounds(10, 20, 200, 25);
        amountlabel.setForeground(Color.white);
        JTextField amountField = new JTextField(10);
        amountField.setBounds(10, 45, 165, 25);
        amountField.setForeground(Color.WHITE);
        amountField.setBackground(new Color(43, 122, 120));
        amountField.setBorder(null);
        JLabel timelabel = new JLabel("Duration in months:");
        timelabel.setBounds(10, 90, 200, 25);
        timelabel.setForeground(Color.white);
        JTextField timeField = new JTextField(10);
        timeField.setBounds(10, 115, 165, 25);
        timeField.setForeground(Color.WHITE);
        timeField.setBackground(new Color(43, 122, 120));
        timeField.setBorder(null);

        Font customfont= new Font("Arial", Font.BOLD, 14);

        JButton Apply = new JButton("Check");
        Apply.setBounds(10,160,135,35);
        Apply.setBackground(new Color(23,37,42));
        Apply.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        Apply.setForeground(Color.WHITE);
        Apply.setFocusPainted(false);
        Apply.setFont(customfont);

        JButton goback = new JButton("Back");
        goback.setBounds(160, 160, 135, 35);
        goback.setBackground(new Color(23,37,42));
        goback.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        goback.setForeground(Color.WHITE);
        goback.setFocusPainted(false);
        goback.setFont(customfont);
        panel.add(goback);

        panel.add(amountlabel);
        panel.add(amountField);
        panel.add(timelabel);
        panel.add(timeField);
        panel.add(Apply);
        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);

        Apply.addActionListener(new ActionListener() {
            Font clr = new Font("Arial", Font.BOLD, 12);
            @Override
            public void actionPerformed(ActionEvent e){
                long loan_amount=Long.parseLong(amountField.getText());
                int time =Integer.parseInt(timeField.getText());
                obj.installment_left=time;
                obj.installment_amount=loan_amount/time;
                
                
                
                
                if (time<600&&time>240&&obj.credit_score>600){
                    long interest=loan_amount*30/100;
                    long total=loan_amount+interest;
                    long installment=total/time;
                    
                    JTextArea complete= new JTextArea("The loan will impose a 30% interest rate of rupees "+interest+" \nthe total amount will be rupees "+ total +" \nwhich will be taken in monthly Installments of rupees "+installment);
                    complete.setBounds(10, 150, 400, 60);
                    complete.setBackground(new Color(58,175,169));
                    complete.setForeground(Color.white);
                    complete.setFont(clr);
                    panel.add(complete);
                    panel.repaint();

                }
                else if(time>180&&obj.credit_score>600){
                    long interest=loan_amount*25/100;
                    long total=loan_amount+interest;
                    long installment=total/time;

                    JTextArea complete= new JTextArea("The loan will impose a 30% interest rate of rupees "+interest+"\n the total amount will be rupees "+ total +"\n which will be taken in monthly Installments of rupees "+installment);
                    complete.setBounds(10,  250, 400, 60);
                    complete.setBackground(new Color(58,175,169));
                    complete.setForeground(Color.white);
                    complete.setFont(clr);
                    panel.add(complete);
                    panel.repaint();

                }
                else if(time>120&&obj.credit_score>600){
                    long interest=loan_amount*20/100;
                    long total=loan_amount+interest;
                    long installment=total/time;

                    JTextArea complete= new JTextArea("The loan will impose a 20% interest rate of rupees "+interest+"\n the total amount will be rupees "+ total +" \nwhich will be taken in monthly Installments of rupees "+installment);
                    complete.setBounds(10, 250, 400, 60);
                    panel.add(complete);
                    complete.setBackground(new Color(58,175,169));
                    complete.setForeground(Color.white);
                    complete.setFont(clr);
                    panel.repaint();

                }
                else if(time>60&&obj.credit_score>600){
                    long interest=loan_amount*15/100;
                    long total=loan_amount+interest;
                    long installment=total/time;

                    
                    JTextArea complete= new JTextArea("The loan will impose a 20% interest rate of rupees "+interest+"\n the total amount will be rupees "+ total +" \nwhich will be taken in monthly Installments of rupees "+installment);
                    complete.setBounds(10, 250, 400, 60);
                    complete.setBackground(new Color(58,175,169));
                    complete.setForeground(Color.white);
                    complete.setFont(clr);
                    panel.add(complete);
                    panel.repaint();

                }
                else if(time<60&&0<time&&obj.credit_score>600){
                    long interest=loan_amount*12/100;
                    long total=loan_amount+interest;
                    long installment=total/time;

                    JTextArea complete= new JTextArea("The loan will impose a 20% interest rate of rupees "+interest+"\n the total amount will be rupees "+ total +" \nwhich will be taken in monthly Installments of rupees "+installment);
                    complete.setBounds(10, 250, 400, 60);
                    complete.setBackground(new Color(58,175,169));
                    complete.setForeground(Color.white);
                    complete.setFont(clr);
                    panel.add(complete);
                    panel.repaint();
                }
                else{
                    JTextArea complete= new JTextArea("Enter the correct data and time");
                    complete.setBounds(10, 240, 200, 15);
                    complete.setBackground(new Color(58,175,169));
                    complete.setForeground(Color.white);
                    complete.setFont(clr);
                    panel.add(complete);
                    panel.repaint();
                }
                if((obj.credit_score>600)){
                    JTextArea complete= new JTextArea("You are eligible for loan.");
                    complete.setBounds(10, 220, 200, 15);
                    complete.setBackground(new Color(58,175,169));
                    complete.setFont(clr);
                    complete.setForeground(Color.white);
                    panel.add(complete);
                    panel.repaint();
                    query("insert into "+obj.accountnumber+"loan(loan_id,loan_amount,installments,installment_amount,loan_left,installment_left,loan_status)\nvalues("+obj.accountnumber+","+obj.loan_amount+","+time+","+obj.installment_amount+","+obj.loan_amount+","+time+","+"'Not yet approved/');");
                    JTextArea completed= new JTextArea("Your loan application is submitted \nPlease wait for 3-4 days for us to look into it for approval");
                    completed.setBounds(10, 250, 300, 15);
                    complete.setBackground(new Color(58,175,169));
                    complete.setForeground(Color.white);
                    complete.setFont(clr);
                    panel.add(completed);
                    panel.repaint();
                }
                
                else{
                    JTextArea complete= new JTextArea("Sorry! You are not eligible for loan");
                    complete.setBounds(10, 200, 200, 32);
                    complete.setBackground(new Color(58,175,169));
                    complete.setForeground(Color.white);
                    complete.setFont(clr);
                    panel.add(complete);
                    panel.repaint();
                }
            }
        });
        goback.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                homepage(Integer.toString(obj.accountnumber));
            }
        });
        
    }
    static void creditscore(customer obj){
        JFrame frame = new JFrame("Check credit score");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(58,175,169));
        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);
        obj.credit_score=0;

        JLabel question= new JLabel("HELLO "+obj.Name);
        question.setBounds(100, 10, 400, 25);
        question.setForeground(Color.white);
        JLabel housevallabel= new JLabel("Enter the estimate value of house");
        housevallabel.setBounds(10, 30, 200, 25);
        housevallabel.setForeground(Color.white);
        JTextField housevalField = new JTextField(10);
        housevalField.setBounds(10, 55, 165, 25);
        housevalField.setForeground(Color.WHITE);
        housevalField.setBackground(new Color(43, 122, 120));
        housevalField.setBorder(null);
        JLabel salLabel = new JLabel("Enter the salary");
        salLabel.setBounds(10, 100, 200, 25);
        salLabel.setForeground(Color.white);
        JTextField salField = new JTextField(10);
        salField.setBounds(10, 125, 165, 25);
        salField.setForeground(Color.WHITE);
        salField.setBackground(new Color(43, 122, 120));
        salField.setBorder(null);
        Font customfont= new Font("Arial", Font.BOLD, 14);

        JButton Submit = new JButton("Submit");
        Submit.setBounds(10, 180, 165, 35);
        Submit.setBackground(new Color(23,37,42));
        Submit.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        Submit.setForeground(Color.WHITE);
        Submit.setFocusPainted(false);
        Submit.setFont(customfont);

        JButton go = new JButton("apply");
        go.setBounds(10, 225, 165, 35);
        go.setBackground(new Color(23,37,42));
        go.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        go.setForeground(Color.WHITE);
        go.setFocusPainted(false);
        go.setFont(customfont);

        JButton goback = new JButton("Back");
        goback.setBounds(190, 180, 165, 35);
        goback.setBackground(new Color(23,37,42));
        goback.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        goback.setForeground(Color.WHITE);
        goback.setFocusPainted(false);
        goback.setFont(customfont);
        panel.add(goback);

        panel.add(question);
        panel.add(housevallabel);
        panel.add(housevalField);
        panel.add(salField);
        panel.add(salLabel);
        panel.add(Submit);
        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);
        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                loan_application(obj);
            }});
        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                double val = Double.parseDouble(housevalField.getText());
                double sal = Double.parseDouble(salField.getText());
                if (val>1000000)
                    obj.credit_score+=1000;
                else if (val>5000000)
                    obj.credit_score+=900;
                else if (val>1000000)
                obj.credit_score+=800;
                else if (val>500000)
                    obj.credit_score+=700;
                else if (val>100000)
                    obj.credit_score+=500;
                else if  (val>50000)
                    obj.credit_score+=400;
                else if (val>10000)
                    obj.credit_score+=300;
                else
                    obj.credit_score+=100;

                if (sal>1000000)
                    obj.credit_score+=1000;
                else if (sal>5000000)
                    obj.credit_score+=900;
                else if (sal>1000000)
                obj.credit_score+=800;
                else if (sal>500000)
                    obj.credit_score+=700;
                else if (sal>100000)
                    obj.credit_score+=500;
                else if  (sal>50000)
                    obj.credit_score+=400;
                else if (sal>10000)
                    obj.credit_score+=300;
                else
                    obj.credit_score+=100;
                JLabel complete= new JLabel("Here is your credit score :"+obj.credit_score);
                complete.setBounds(10, 150, 200, 15);
                panel.add(complete);
                panel.add(go);
                panel.repaint();

                }
        });
        goback.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                homepage(Integer.toString(obj.accountnumber));
            }
        });
        }

    static void verify(String anum,String pass,JFrame frame){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bank_data";
            String user = "root";
            String password = "Amanshivdasani012";

            Connection connection = DriverManager.getConnection(url, user, password);
            ResultSet resultSet=connection.createStatement().executeQuery("select * from users");
            while (resultSet.next()) {
                try{
                String a=resultSet.getString("acc");
                String b=resultSet.getString("passwords");
                
                if  (anum.equals(a) &&pass.equals(b)){
                    frame.dispose();
                    homepage(anum);
                    break;
                }
                }
                catch(Exception e){
                    System.out.println(e);
                    break;
                }
            }
            resultSet.close();
            connection.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args){
        start();
    }
    }

