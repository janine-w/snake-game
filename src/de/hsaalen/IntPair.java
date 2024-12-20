package de.hsaalen;

public class IntPair {
    public int x;
    public int y;

    public IntPair( int x, int y){
        this.x = x;
        this.y = y;
    }

    public void move (Direction direction){
        switch (direction) {
            case left:
                x -= 1;
                break;
            case right:
                x += 1;
                break;
            case up:
                y -= 1;
                break;
            case down:
                y += 1;
                break;
        }
    }
    public IntPair clone(){
        return new IntPair(this.x, this.y);
    }

    public String toString(){
        String result = "(" + x + "/" + y + ")";
        return result;
    }
}
