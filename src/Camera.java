public class Camera {
    private double x;
    private double y;
    private Hero center;

    public double getX() {return x;}
    public double getY() {return y;}

    //Camera centered on the hero
    public Camera(Integer x, Integer y, Hero center){
        this.x=x;
        this.y=y;
        this.center=center;
    }

    public void update(){
        x=center.getX()-150;
    }

    @Override
    public String toString(){
        return "("+x+";"+y+")"; }
}
