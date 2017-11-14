package pro.x_way.effect;

public abstract class Effect {
    private int time;

    public boolean isEnd() {
        return isEnd;
    }

    private boolean isEnd;

    public void start(int time){
        this.time = time;
    }


    public void tick(){
        if (time-- == 0) isEnd = true;

    }

    public void end(){

    }

    public static void setup(){

    }

    public abstract void printEffect();
}
