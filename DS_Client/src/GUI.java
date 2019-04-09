
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTextField;


public class GUI extends JFrame {
    //dhmiourgw ta pedia mou. Frames,Textfields
    private JFrame fr, frame, fr1, fr2, fra;
    private JTextField text, text2, textField, textField1, textField2, textField3, textField4, textarea;
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");//date format
    private Reservation res;//antikeimeno krathshs

    Socket sock = null;
    OutputStream os = null;
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    ObjectInputStream ois = null;
    ObjectOutputStream oos = null;

    public GUI() {
        fr = new JFrame("HOTEL");

        try {
            sock = new Socket("localhost", 8080);//syndesh socket me ton server
            //dhmiourgw output kai input stream gia ta object pou stelnw kai dexomai me ton server
            ois = new ObjectInputStream(sock.getInputStream());
            oos = new ObjectOutputStream(sock.getOutputStream());
            oos.writeObject(new String("Start"));//ksekinaw me to prwto antikeimeno pou einai string kai stelnei Start ston server gia na ksekinhsei h diadikasia
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        //grafika gia to kentriko mou frame me ta koumpia epiloghs gia krathsh anazhthsh diagrafh h kleisimo.
        fr.setBounds(100, 100, 450, 400);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.getContentPane().setLayout(null);
        JLabel make = new JLabel("Make a reservation:");
        make.setBounds(93, 67, 126, 14);
        fr.getContentPane().add(make);
        JLabel search = new JLabel("Search a reservation:");
        search.setBounds(93, 97, 126, 14);
        fr.getContentPane().add(search);
        JLabel delete = new JLabel("Delete a reservation:");
        delete.setBounds(93, 127, 126, 14);
        fr.getContentPane().add(delete);
        JButton bt = new JButton("Make");
        bt.setBounds(224, 64, 167, 20);
        fr.getContentPane().add(bt);
        JButton bt1 = new JButton("Search");
        bt1.setBounds(224, 94, 167, 20);
        fr.getContentPane().add(bt1);
        JButton bt3 = new JButton("End");
        bt3.setBounds(224, 154, 167, 20);
        fr.getContentPane().add(bt3);
        JButton bt2 = new JButton("Delete");
        bt2.setBounds(224, 124, 167, 20);
        fr.getContentPane().add(bt2);
        fr.setVisible(true);

        bt3.addActionListener(new ActionListener() {//edw einai h leitourgia tou koumpiou end
            public void actionPerformed(ActionEvent arg0) {
                try {
                    oos.writeObject(new String("End"));//stelnw ston server antikeimeno string me to periexomeno End gia na katalabei oti thelw na ginei h diadikasia tou kleisimatos tou socket
                    oos.flush();
                    fr.setVisible(false); // molis kleisei to socket kleinw kai to frame
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        bt.addActionListener(new ActionListener() {//edw einai to koumpi ths krathshs
            public void actionPerformed(ActionEvent arg0) {

                try {
                    oos.writeObject(new String("Insert"));//stelnw ston server antikeimeno string me to periexomeno Insert gia na katalabei oti thelw na ginei h diadikasia ths krathshs
                    oos.flush();
                    //grafika gia thn krathsh me ola ta textfields kai ta labels pou xreiazontai
                    frame = new JFrame("RESERVATION");
                    frame.setBounds(100, 100, 450, 400);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.getContentPane().setLayout(null);
                    JLabel title = new JLabel("Please fill them all.");
                    title.setBounds(93, 27, 106, 14);
                    frame.getContentPane().add(title);
                    JLabel Name = new JLabel("Name:");
                    Name.setBounds(93, 67, 46, 14);
                    frame.getContentPane().add(Name);
                    JLabel surName = new JLabel("Surname:");
                    surName.setBounds(93, 97, 66, 14);
                    frame.getContentPane().add(surName);
                    JLabel phone = new JLabel("Phone:");
                    phone.setBounds(93, 127, 86, 14);
                    frame.getContentPane().add(phone);
                    JLabel datein = new JLabel("Check in:");
                    datein.setBounds(93, 157, 106, 14);
                    frame.getContentPane().add(datein);
                    JLabel dateoun = new JLabel("Check out:");
                    dateoun.setBounds(93, 187, 126, 14);
                    frame.getContentPane().add(dateoun);
                    JLabel RoomType = new JLabel("Room type:");
                    RoomType.setBounds(93, 217, 146, 14);
                    frame.getContentPane().add(RoomType);
                    JLabel breakfast = new JLabel("Breakfast:");
                    breakfast.setBounds(93, 247, 166, 14);
                    frame.getContentPane().add(breakfast);
                    JButton Submit = new JButton("Submit");
                    Submit.setBounds(93, 291, 89, 23);
                    frame.getContentPane().add(Submit);
                    textField = new JTextField();
                    textField.setBounds(204, 64, 167, 20);
                    frame.getContentPane().add(textField);
                    textField.setColumns(10);
                    textField1 = new JTextField();
                    textField1.setBounds(204, 94, 167, 20);
                    frame.getContentPane().add(textField1);
                    textField1.setColumns(10);
                    textField2 = new JTextField();
                    textField2.setBounds(204, 124, 167, 20);
                    frame.getContentPane().add(textField2);
                    textField2.setColumns(10);
                    textField3 = new JTextField("''dd/MM/yyyy''");
                    textField3.setBounds(204, 154, 167, 20);
                    frame.getContentPane().add(textField3);
                    textField4 = new JTextField("''dd/MM/yyyy''");
                    textField4.setBounds(204, 184, 167, 20);
                    frame.getContentPane().add(textField4);
                    textField4.setColumns(10);
                    JComboBox c = new JComboBox();//dropdoun menu gia epilogh typou dwmatiou
                    c.addItem("Singe Room");
                    c.addItem("Double Room");
                    c.addItem("Triple Room");
                    c.setBounds(204, 214, 167, 20);
                    frame.getContentPane().add(c);
                    JComboBox c1 = new JComboBox();//dropdoun menu gia epilogh an thelei o xrhsths prwino h oxi
                    c1.addItem("Yes");
                    c1.addItem("No");

                    c1.setBounds(204, 244, 167, 20);
                    frame.getContentPane().add(c1);

                    frame.setVisible(true);
                    Submit.addActionListener(new ActionListener() {//edw einai b leitourgia tou koumpiou pou kanei thn krathsh
                        public void actionPerformed(ActionEvent arg0) {
                            //elegxos na mhn afhsei kapoio keno o xrhsths. einai ola upoxrewtika
                            if (textField.getText().equalsIgnoreCase("") || textField1.getText().equalsIgnoreCase("") || textField2.getText().equalsIgnoreCase("") || textField3.getText().equalsIgnoreCase("") || textField4.getText().equalsIgnoreCase("")) {
                                JOptionPane.showMessageDialog(null, "Please fill all the gaps");
                            } else {
                                try {
                                    JOptionPane.showMessageDialog(null, "Data Submitted");

                                    frame.setVisible(false);
                                    String nm = textField.getText();
                                    String sm = textField1.getText();
                                    String ph = textField2.getText();
                                    Date din = df.parse(textField3.getText());
                                    Date dout = df.parse(textField4.getText());

                                    String roomType = c.getSelectedItem().toString();

                                    boolean breakfast = false;//arxikh katastash gia prwino false

                                    if (roomType.equals("Singe Room")) {// an epileksei single room
                                        if (c1.getSelectedItem().equals("Yes")) {//elegxos an thelei prwino wste na allaksei h boolean se true

                                            breakfast = true;

                                            SingleRoom s = new SingleRoom(breakfast);//dhmiourgia single dwmatiou
                                            Room r = new Room(s.roomsType(), s.ID(), s.getPrise());//dhmiourgia dwmatiou
                                            Reservation res = new Reservation(din, dout, r, nm, sm, ph);//dhmiourgia krathshs me ta values twn textfieldkai genika tis epiloges tou xrhsth
                                            oos.writeObject(res);//stelnw antikeimeno krathshs
                                            oos.flush();
                                            String mes = ois.readObject().toString();//kai perimenw na diabasw object apo ton server
                                          JOptionPane.showMessageDialog(null,mes);
                                            
                                            frame.setVisible(false);
                                        } else {//to idio kai se periptwsh pou h epilogh sto prwino einai no mono pou twra h boolean tou prwinou einai false
                                            breakfast = false;

                                            SingleRoom s = new SingleRoom(breakfast);
                                            Room r = new Room(s.roomsType(), s.ID(), s.getPrise());
                                            Reservation res = new Reservation(din, dout, r, nm, sm, ph);
                                            oos.writeObject(res);
                                            oos.flush();
                                            String mes = ois.readObject().toString();
                                          JOptionPane.showMessageDialog(null,mes);
                                        }
                                    } else if (roomType.equals("Double Room")) {//omoiws kai auto to dwmatio
                                        if (c1.getSelectedItem().equals("Yes")) {

                                            breakfast = true;

                                            DoubleRoom d = new DoubleRoom(breakfast);
                                            Room r = new Room(d.roomsType(), d.ID(), d.getPrise());
                                            Reservation res = new Reservation(din, dout, r, nm, sm, ph);
                                            oos.writeObject(res);
                                            oos.flush();
                                           String mes = ois.readObject().toString();
                                          JOptionPane.showMessageDialog(null,mes);
                                        } else {
                                            breakfast = false;

                                            DoubleRoom d = new DoubleRoom(breakfast);
                                            Room r = new Room(d.roomsType(), d.ID(), d.getPrise());
                                            Reservation res = new Reservation(din, dout, r, nm, sm, ph);
                                            oos.writeObject(res);
                                            oos.flush();
                                           String mes = ois.readObject().toString();
                                          JOptionPane.showMessageDialog(null,mes);
                                        }
                                    } else if (roomType.equals("Triple Room")) {//omoiws kai auto to dwmatio
                                        if (c1.getSelectedItem().equals("Yes")) {

                                            breakfast = true;

                                            TripleRoom t = new TripleRoom(breakfast);
                                            Room r = new Room(t.roomsType(), t.ID(), t.getPrise());
                                            Reservation res = new Reservation(din, dout, r, nm, sm, ph);
                                            oos.writeObject(res);
                                            oos.flush();
                                            String mes = ois.readObject().toString();
                                          JOptionPane.showMessageDialog(null,mes);

                                        } else {
                                            breakfast = false;

                                            TripleRoom t = new TripleRoom(breakfast);
                                            Room r = new Room(t.roomsType(), t.ID(), t.getPrise());
                                            Reservation res = new Reservation(din, dout, r, nm, sm, ph);
                                            oos.writeObject(res);
                                            oos.flush();
                                           String mes = ois.readObject().toString();
                                          JOptionPane.showMessageDialog(null,mes);
                                        }
                                    }

                                } catch (ParseException ex) {
                                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    });
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        bt1.addActionListener(new ActionListener() {//edw ginetai h leitourgia tou koumpiou search
            public void actionPerformed(ActionEvent arg0) {
                try {
                    oos.writeObject(new String("Search"));//stelnw ston server antikeimeno string me to periexomeno Search gia na katalabei oti thelw na ginei h diadikasia ths anazhthshs
                    oos.flush();
                    //grafika gia to search
                    fr1 = new JFrame("HOTEL");
                    fr1.setBounds(100, 100, 450, 400);
                    fr1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    fr1.getContentPane().setLayout(null);
                    JLabel s = new JLabel("Surname:");
                    s.setBounds(93, 67, 126, 14);
                    fr1.getContentPane().add(s);
                    JLabel sid = new JLabel("Check in Date:");
                    sid.setBounds(93, 97, 126, 14);
                    fr1.getContentPane().add(sid);
                    JButton butt = new JButton("Search");
                    butt.setBounds(93, 291, 89, 23);
                    fr1.getContentPane().add(butt);
                    fr1.setVisible(true);
                    text = new JTextField();
                    text.setBounds(204, 64, 167, 20);
                    fr1.getContentPane().add(text);
                    text.setColumns(10);
                    text2 = new JTextField();
                    text2.setBounds(204, 94, 167, 20);
                    fr1.getContentPane().add(text2);
                    text2.setColumns(10);
                    fr1.setVisible(true);
                    butt.addActionListener(new ActionListener() {//edw ginetai h leitourgia tou koumpiou ths anazhthshs
                        public void actionPerformed(ActionEvent arg0) {

                            try {
                                String txt = text.getText();
                                
                                String txt2 = text2.getText();
                                //stelnw ston server ta periexomena twn 2 textfield gia thn anazhthsh
                                oos.writeObject(txt);
                                oos.writeObject(txt2);
                                oos.flush();
                                JOptionPane.showMessageDialog(null,ois.readObject().toString());//pairnw thn apanthsh me to periwxwmeno ths anazhthshs m kai epeita kleinw to frame
                                fr1.setVisible(false);
                            } catch (IOException ex) {
                                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    });
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        bt2.addActionListener(new ActionListener() {//leitourgia tou koumpiou delete
            public void actionPerformed(ActionEvent arg0) {
                try {

                    oos.writeObject(new String("Delete"));//stelnw ston server antikeimeno string me to periexomeno Delete gia na katalabei oti thelw na ginei h diadikasia ths diagrafhs kapoias krathshs
                    oos.flush();
                    //grafika gia to delete m
                    fra = new JFrame("Delete");
                    fra.setBounds(100, 100, 450, 400);
                    fra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    fra.getContentPane().setLayout(null);
                    JLabel ssss = new JLabel("ID:");
                    ssss.setBounds(93, 67, 126, 14);
                    fra.getContentPane().add(ssss);
                    JButton buttonn = new JButton("Submit");
                    buttonn.setBounds(93, 291, 89, 23);
                    fra.getContentPane().add(buttonn);
                    fra.setVisible(true);
                    textarea = new JTextField();
                    textarea.setBounds(204, 64, 167, 20);
                    fra.getContentPane().add(textarea);
                    textarea.setColumns(10);
                    fra.setVisible(true);
                    
                    buttonn.addActionListener(new ActionListener() {//edw ginetai h leitourgia tou koumpiou tou delete
                        public void actionPerformed(ActionEvent arg0) {
                           
                            try {
                                String txt3 = textarea.getText();

                                oos.writeObject(txt3);//stelnw to ID ston server san antikeimeno String afou to phra apo to periexomeno tou textField
                               
                                oos.flush();
                                String kk2 = (String) ois.readObject();//diabazw thn apanthsh...an dhladh egine h diagrafh h an den brethike to id ths krathshs pou prospathw na diagrapsw
                    JOptionPane.showMessageDialog(null, kk2);
                    fra.setVisible(false);//kleinw to frame
                                
                            } catch (IOException ex) {
                                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
}
