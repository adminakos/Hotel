
import java.io.Serializable;

public class Room implements Serializable {

    private String roomType;
    private int id;
    private int price;

    public Room(String roomType, int id, int price) {//o contructor mou pairnei  tupo dwmatiou id kai timh.
        this.roomType = roomType;
        this.id = id;
        this.price=price;

    }

    public String roomsType() {//epistrefei tupo dwmatiou
        return roomType;
    }

    public int ID() {//epistrefei id
        return id;
    }

    public int getPrice() {//epistrefei timh
        return price;
    }

    public void setPrice(int price) {//seter gia thn timh
        this.price = price;
    }

    
    @Override
    public String toString() {
        return "Room{" + "roomType=" + roomType + ", id=" + id + '}';
    }

}
