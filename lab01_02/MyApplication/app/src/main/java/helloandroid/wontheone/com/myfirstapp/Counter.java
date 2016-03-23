package helloandroid.wontheone.com.myfirstapp;

/**
 * Created by pghoo on 2016-03-15.
 */
public class Counter {

    int value;

    public Counter(){
        value = 5;
    }

    public void reset(){
        value = 5;
    }

    public void increment(){
        value++;
    }

    public void longClickIncrement(){
        value+=10;
    }

    public void decrement(){
        value--;
        if (value < 0)
            value=0;
    }

    public void longClickDecrement(){
        value-=10;
        if (value < 0)
            value=0;
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
