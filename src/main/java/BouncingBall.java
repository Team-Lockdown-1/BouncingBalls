public class BouncingBall {

    //TODO infections

    private float x, y;         // Center of the Ball
    private float velX, velY;   // Speed in x and y direction
    private int radius;         // Radius

    public BouncingBall(float x, float y, int vel, float angle, int radius) {
        this.x = x;
        this.y = y;
        velX = (float)(vel * Math.sin(Math.toRadians(angle)));
        velY = (float)(-vel * Math.cos(Math.toRadians(angle)));
        this.radius = radius;
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

}
