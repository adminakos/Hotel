
import java.io.Serializable;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reservation implements Serializable {

    SecureRandom random = new SecureRandom();
    byte bytes[] = new byte[20];
    private String id;
    private Date resDate;
    private Date outDate;
    private int price;
    Room r;
    String name;
    String surname;
    String tel;
    static HashMap<String, Reservation> list = new HashMap();//static gia na krataw times

    public Reservation() {
        
    }

    public Reservation(Date resDate, Date outDate, Room r, String name, String surname, String tel) {//o constructor mou pairnei hmeromhnies check in,check out,tupo dwmatiou,name,surname kai thlefwno

        random.nextBytes(bytes);//random id krathshs gia kathe mia ksexwrista

        id = bytes.toString();
        this.resDate = resDate;
        this.outDate = outDate;
        this.r = r;
        this.price = r.getPrice();
        this.name=name;
        this.surname=surname;
        this.tel=tel;

        if (resDate.getMonth() + 1 < 6 || resDate.getMonth() + 1 > 9) { //edw ftiaxnw tis times ektos season gia kathe dwmatio ksexwrista
            if (r.roomsType().equals("Single Room")) {
                price = r.getPrice() - 40;
            } else if (r.roomsType().equals("Double Room")) {
                price = r.getPrice() - 50;

            } else if (r.roomsType().equals("Triple Room")) {
                price = r.getPrice() - 65;
            }
        }
        //parakatw upologizw tis times symfwna me tis meres 
        if (outDate.getDate() != resDate.getDate()) {
            int x = outDate.getDate() - resDate.getDate();
            price = x * price;
        } else {
            price = r.getPrice();
        }
        
    }

    public void addToList(String id, Reservation r) {//bazei sth lista mou tis krathseis
        list.put(id, r);
    }

    public String getId() {//epistrefei to id
        return id;
    }
    
    public String search( String name) {//synarthsh gia to search mono me name
        
        String kratisi = null;
        for (Map.Entry<String, Reservation> entry : list.entrySet()) {
            if (name.equals(entry.getValue().getSurname())&& !name.equals(null)) {//elegxos an to onoma yparxei sth lista kai an den einai null
                kratisi = entry.getValue().toString();
               
                break;

            } else {
                kratisi = "den vrethike";
                
            }

        }

        return kratisi;
    }

    public String search( String name, String date) {//synarthsh gia to search me name kai check in date
        String kratisi = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            Date d1 = format.parse(date);

            

            for (Map.Entry<String, Reservation> entry : list.entrySet()) {
                if (d1.equals(entry.getValue().getResDate()) || d1.equals(entry.getValue().getOutDate())) {//elegxos gia tis hmeromhnies
                    if (name.equals(entry.getValue().getSurname())&& !name.equals(null)) {//elegxos an to onoma yparxei sth lista kai an den einai null
                        kratisi = entry.getValue().toString();
                        break;

                    } else {
                        kratisi = "den vrethike";
                    }
                } else {
                    kratisi = "den vrethike";
                }

            }

        } catch (ParseException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kratisi;
    }
    
    
    
   public boolean canReservation(Date din, Date dout) {
         boolean resrv=true;
         for (Map.Entry<String, Reservation> entry : list.entrySet()) {
             //parakatw oi elegxoi pou ginontai aforoun tis hmeromhnies check in kai check out an sympiptoun me aytes pou einai sthn lista h an einai endiamesa se aytes kai se syndiasmo me to idio dwmatio
             //me apotelesma na mhn afhnei ton pelath na kanei thn krathsh afou exei hdh to dwmatio kraththei apo allon
             if (din.equals(entry.getValue().getResDate()) || dout.equals(entry.getValue().getOutDate())||din.after(entry.getValue().getResDate())|| dout.before(entry.getValue().getOutDate())) {
                 if(r.roomsType().equals(entry.getValue().getRoom().roomsType()) || din.equals(entry.getValue().getResDate().getDate()) || dout.equals(entry.getValue().getOutDate().getDate()) 
                        || din.equals(entry.getValue().getResDate().getMonth()) 
                        || dout.equals(entry.getValue().getOutDate().getMonth())){
                     resrv=false;
                     break;
                 }else if(!entry.getValue().getRoom().roomsType().equals(r.roomsType())){// se antitheti periptwsh afhnei thn krathsh na ginei kanonika. epistrefei true kai h rathsh ginetai 
                     resrv=true;
                 }
                 
                 
             } else {
                 resrv=true;
             }
             
         }
         return resrv;
    }
     
     public Room getRoom(){//epistrefei to dwmatio
        return r;
    }

    public String getName() {//epistrefei to name
        return name;
    }
    
    public Date getResDate() {//epistrefei to check in date
        return resDate;
    }

    public Date getOutDate() {//epistrefei to check out date
        return outDate;
    }

    public String getSurname() {//epistrefei to surname
        return surname;
    }
    
    

    public int getPrice() {//epistrefei thn timh
        return price;
    }

    public HashMap<String, Reservation> getList() {//epistrefei thn lista
        return list;
    }
    
    public String Delete(String IDdel){
        String del = null;
        HashMap<String, Reservation> map = list;
    for (Map.Entry<String, Reservation> entry : map.entrySet()) {
            if (IDdel.equals(entry.getKey())) {//an to id sumpiptei me kapoio mesa sth lista diagrafw thn krathsh kai epistrefw katallhlo mhnyma
               list.remove(entry.getKey());
               
               del="Room is deleted!";
                break;

            } else {
                del="h krathsh den vrethike"; 
            }

        }
    return del;
    }
    
    @Override
    public String toString() {
        return "reservation{" + "id=" + id + ", resDate=" + resDate + ", outDate=" + outDate + ", price=" + price + ", r=" + r + ", name=" + name + ", surname=" + surname + ", tel=" + tel + '}';
    }

    

}
