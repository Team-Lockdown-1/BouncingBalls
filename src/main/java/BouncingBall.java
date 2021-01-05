public class BouncingBall {

    //TODO infections

    private float x, y;         // Center of the Ball
    private float velX, velY;   // Speed in x and y direction
    private int radius;         // Radius
    private boolean collided;
    private boolean infected = false;
    private int hits = HomeScreen.hits;
    private float heal = HomeScreen.heal;
    //variable and methods for the list----------------
    public static int infectedBallsInList; //infected balls list number
    public static int healthyBallsInList = HomeScreen.balls; //healthy balls list number
    //this booleans are important, otherwise it would be conuted as infected if 2 infected balls collide
    public boolean hasNotBeenInfectedOnce = true; //has been infected, yes or no. important.
    public boolean hasBeenHealed = true; //has been healed, yes or no. important

    //increases the number of infected and decreases the number of healthy balls
    public static void infectedCounter(BouncingBall ball){
        //booleans has to be true / false, otherwise the counter doesn´t count properly
        ball.hasBeenHealed = true;
        ball.hasNotBeenInfectedOnce = false;
        BouncingBall.healthyBallsInList--;
        BouncingBall.infectedBallsInList++;

    }
    //decreases the number of infected and increases the number of healthy balls
    public static void regenerationCounter(BouncingBall ball){
        BouncingBall.healthyBallsInList++;
        BouncingBall.infectedBallsInList--;
        //booleans has to be true / false, otherwise the counter doesn´t count properly
        ball.hasBeenHealed = false;
        ball.hasNotBeenInfectedOnce = true;
    }
    //set the healthy/infected list back to default
    public static void resetList(){
        infectedBallsInList = 0;
        healthyBallsInList = HomeScreen.balls;
    }
//-------------------------------------------------------------

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
