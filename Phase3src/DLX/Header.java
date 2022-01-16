package DLX;

//Object class for header, part of Dancing Links 
public class Header extends Node {
    int size;
    int columnID;

    //constructor for the root
    public Header() {
        super();
        this.header = this;
        this.size = 0;
        this.columnID = -1;
    }
    //constructor for other headers
    public Header (int ID){
        super();
        this.header = this;
        this.size = 0;
        this.columnID = ID;
    }

    public void unlink() {
        unlinkFromRow();
        for (Node i = this.d; i != this; i = i.d) {
            for (Node j = i.r; j != i; j = j.r) {
                // System.out.println("t");
                j.unlinkFromColumn();
            }
        }
    }
    public void link() {
        for (Node i = this.u; i != this; i = i.u) {
            for (Node j = i.l; j != i; j = j.l) {
                j.relinkToColumn();
            }
        }
        relinkToRow();
    }

}
