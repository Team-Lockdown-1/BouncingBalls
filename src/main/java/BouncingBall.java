import java.util.Timer;
import java.util.TimerTask;

public class BouncingBall {

    //TODO infections

    private float x, y;         // Center of the Ball
    private float velX, velY;   // Speed in x and y direction
    private int radius;         // Radius
    private boolean collided;
    private boolean infected = false;
    private int hits = HomeScreen.hits;
    private float heal = HomeScreen.heal;
    //test variable ist für die liste in Security doors
    public static int test;
    public static int tryit = HomeScreen.balls;

    public void infetions(){
        int delay = HomeScreen.heal * 200;
        int period = 1000;
        test++;
        tryit--;

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {int count = 0;
            public void run()
            {

                if(count == 0){
                    test--;
                    tryit++;
                    count++;
                    System.out.println("infected!");
                }else{
                    timer.cancel();
                }

            }
        }, delay, period);
    }

    public BouncingBall(float x, float y, int vel, float angle, int radius) {
        this.x = x;
        this.y = y;
        velX = (float)(vel * Math.sin(Math.toRadians(angle)));
        velY = (float)(-vel * Math.cos(Math.toRadians(angle)));
        this.radius = radius;
        collided = false;
    }

    public float getX() {
        return x;
    }

    public float getVelX() {
        return velX;
    }

    public float getY() {
        return y;
    }

    public float getVelY() {
        return velY;
    }

    public int getRadius() {
        return radius;
    }

    public void setNewPos(){
        x += velX;
        y += velY;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public void setCollided(boolean col){
        collided = col;
    }

    public boolean isCollided() {
        return collided;
    }

    public void onHit(){
        if (hits == 0){
            infected = true;
        }else{
            hits--;
        }
    }

    public void setInfected(boolean infected) {
        infetions();
        this.infected = infected;
    }

    public boolean isInfected() {
        return infected;
    }

    public void genesung(){
        heal -= 0.1;
    }

    public float getHeal() {
        return heal;
    }

    public void setHeal(float heal) {
        this.heal = heal;
    }
}
