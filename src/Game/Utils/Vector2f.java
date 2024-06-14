package Game.Utils;

public class Vector2f {
    public float x, y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2f copy() {
        return new Vector2f(this.x, this.y);
    }

    @Override
    public String toString() {
        return "{x = " + x + ", y= " + y  + "}";
    }
}
