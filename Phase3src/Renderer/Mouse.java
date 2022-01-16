package Renderer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {
    private  int mouseX=-1;
    private int mouseY=-1;
    private int mouseB=-1;
    private int scroll=0;

    public int getX(){
        return this.mouseX;

    }
    public int getY(){
        return this.mouseY;
    }
    public boolean isScrollingUp(){
        return this.scroll ==-1;
    }

    public boolean isScrollingDown(){
        return this.scroll == 1;
    }

    public void resetScroll(){
        this.scroll=0;
    }

    public ClickType getButton(){
        switch(this.mouseB){
            case 1:
            return ClickType.LeftClick;
            case 2:
            return ClickType.ScrollClick;
            case 3:
            return ClickType.RightClick;
            default:
            return ClickType.unknown;


        }
    }

    public void resetButton(){
        this.mouseB= -1;
    }



    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
       scroll=e.getWheelRotation();
        
    }

    @Override
    public void mouseDragged(MouseEvent event) {
      
        this.mouseX=event.getX();
        this.mouseY=event.getY();
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        this.mouseX=event.getX();
        this.mouseY=event.getY();
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      
        
    }

    @Override
    public void mousePressed(MouseEvent event) {
        this.mouseB=event.getButton();
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.mouseB= -1;
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
        
    }
    
}
