
public class TripleRoom {

    private int prise;

    private boolean breakfast;
    private String roomType;
    private static int id = 300;

    public TripleRoom(boolean breakfast) {//idio me ta upoloipa dwmatioa pairnei o constructor mia boolean gia to breakfast

        this.breakfast = breakfast;
        this.prise = prise;
        prise = 150;
        id++;
        if (breakfast == true) {
            prise = prise + 24;
        }
        roomType = "Triple Room";
    }

    public int getPrise() {//epistrefei timh

        return prise;
    }

    public String roomsType() {//epistrefei tupo dwmatiou

        return roomType;
    }

    public int ID() {//epistrefei id
        return id;
    }

    @Override
    public String toString() {
        return "TripleRoom{" + "prise=" + prise + ", breakfast=" + breakfast + ", roomType=" + roomType + ", id=" + id + '}';
    }

}
