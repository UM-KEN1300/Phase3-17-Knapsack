package DLX;

//Object class for node, part of Dancing Links 
public class Node {
    Node l, r, u, d;
    Header header;
    int rowInput;

    public Node() {
        l = r = u = d = this;
        rowInput = -1;
    }

    public Node(int rowInput) {
        l = r = u = d = this;
        this.rowInput = rowInput;
    }
    public Node(int rowInput, Header h) {
        this(rowInput);
        this.header = h;
    }
    
    public void unlinkFromRow() {
        this.l.r = r;
        this.r.l = l;
    }

    public void relinkToRow() {
        this.l.r = this;
        this.r.l = this;
    }

    public void unlinkFromColumn() {
        this.u.d = d;
        this.d.u = u;
        header.size--;
    }

    public void relinkToColumn() {
        this.u.d = this;
        this.d.u = this;
        this.header.size++;
    }
    public void linkVertically (Node node){
        node.d = this.d;
        node.u = this;
        node.d.u = node;
        this.d = node;
    }
    public void linkHorizontally (Node node){
        node.r = this.r;
        node.l = this;
        this.r.l = node;
        this.r = node;
    }


}
