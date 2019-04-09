
public class DoubleRoom {
    private int prise;

    private boolean breakfast;
    private String roomType;
    private static int id = 200;

    public DoubleRoom(boolean breakfast) {//to idio me to single boolean gia breakfast

        this.breakfast = breakfast;
        this.prise = prise;
        prise = 120;
        id++;
        if (breakfast == true) {
            prise = prise + 16;
        }
        roomType = "Double Room";

    }

    public String roomsType() {//epistrefei ton tupoi dwmatiou

        return roomType;
    }

    public int ID() {//epistrefei to id
        return id;
    }

    public int getPrise() {//epistrefei thn timh
        return prise;
    }


    @Override
    public String toString() {
        return "DoubleRoom{" + "prise=" + prise + ", breakfast=" + breakfast + ", roomType=" + roomType + ", id=" + id + '}';
    }


}
    

