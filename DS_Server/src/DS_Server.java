
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DS_Server {

    Socket sock = null;
    ObjectInputStream ois = null;
    ObjectOutputStream oos = null;

    public DS_Server() {
        try {
            //dhmiourgw ena socket gia thn syndesh server kai client
            ServerSocket ss = new ServerSocket(8080);
            //kanw accept ton client
            sock = ss.accept();
            //dhmiourgw output kai input stream gia thn apostolh kai lhpsh antikeimenwn
            oos = new ObjectOutputStream(sock.getOutputStream());
            ois = new ObjectInputStream(sock.getInputStream());
           //diabazw mhnymata antikeimenwn string gia tis diafores leitourgies.
            String mes = ois.readObject().toString();
            if (mes.equals("Start")) { //an einai start kanw tis parakatw leitourgies
                String mes2 = ois.readObject().toString();

                while (!mes2.equals("End")) {//elegxw na mhn einai end gia na ginoun ola parakatw.

                    if (mes2.equals("Insert")) {//an einai insert prosthetw  krathsh
                        Reservation r = (Reservation) ois.readObject();//diabazw antikeimeno krathshs
                        
                        if (r.canReservation(r.getResDate(), r.getOutDate())) {//elegxw an mporei na ginei h krathsh
                            r.addToList(r.getId(), r);
                            oos.writeObject(new String("OK! id=" + r.getId() + " cost=" + r.getPrice()));//stelnw ton client antikeimeno enos string me to id ths krathshs pou egine kai thn timh
                            oos.flush();

                        } else {
                            oos.writeObject(new String("den ginetai h kratisi"));
                            oos.flush();
                        }
                        System.out.println(r.getList());

                    } else if (mes2.equals("Delete")) {//an einai delete sbhnw thn krathsh me to id pou edwse o xrhsths
                        Reservation r = new Reservation();

                        String IDdel = (String) ois.readObject();

                        
                            oos.writeObject(r.Delete(IDdel));
                            oos.flush();
                       
                       
                    } else if (mes2.equals("Search")) {//an einai search kanw anahthsh me bash to onoma h me bash kai onoma kai check in date.
                        Reservation r = new Reservation();

                        String name = (String) ois.readObject();//diabazw to antikeimeno toy string gia to name
                        String date = (String) ois.readObject();//diabazw to antikeimeno toy string gia to date
                        System.out.println(name);
                        System.out.println(date);
                        if (date.isEmpty()) {//elegxw an to date einai adeio na psaksei mono me to onoma
                            oos.writeObject(r.search(name));
                            oos.flush();
                        } else {// alliws kai me ta duo
                            oos.writeObject(r.search(name, date));
                            oos.flush();

                        }

                    } else if (mes2.equals("End")) {//an to object tou string einai end totoe kleinei to socket mou

                        sock.close();
                        break;
                    }

                    mes2 = ois.readObject().toString();
                }

            } else if (mes.equals("End")) {//an to object tou string einai end totoe kleinei to socket mou
                sock.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(DS_Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DS_Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {

        DS_Server ds = new DS_Server();

    }

}
