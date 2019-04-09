
import java.io.Serializable;

public class SingleRoom implements Serializable {

    private boolean breakfast = false;
    private int prise = 80;
    private String roomType;
    private static int id=100;

    public SingleRoom(boolean breakfast) {// o constructor mmou pairnei mia boolean gia to prwino to opoio an einai true allazei thn timh

        this.breakfast = breakfast;
        this.prise = prise;
        id++;
        if (breakfast == true) {
            prise = prise + 8;
        }
        roomType = "Single Room";

    }

    public int getPrise() {//epistrefei thn timh

        return prise;
    }

    public String roomsType() {//epistrefei ton typo dwmatiou
        
        return roomType;
    }

    public int ID() {//epistrefei to id
        return id;
    }

    @Override
    public String toString() {
        return "singleRoom{" + "breakfast=" + breakfast + ", prise=" + prise + ", roomType=" + roomType + ", id=" + id + '}';
    }

}
