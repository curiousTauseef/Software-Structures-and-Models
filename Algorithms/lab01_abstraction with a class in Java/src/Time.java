
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Won Seob Seo <Wons at Metropolia UAS>
 */
public class Time implements Comparable<Time>{

    int hour, minute;
    Scanner in = new Scanner(System.in);

    public Time() {
    }

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }
    
    public void read(String input) {
        System.out.println(input);
        hour = in.nextInt();
        minute = in.nextInt();
    }

    public boolean lessThan(Time other) {
        return this.hour*60+this.minute<other.hour*60+other.minute;
    }

    public Time subtract(Time other) {
        int duration = ((hour*60) + minute) - ((other.hour*60)+other.minute);
        int durH = duration / 60;
        int durM = duration % 60;
        return new Time(durH, durM);
    }

    public void print(){
        System.out.println(this);
    }
    
    @Override
    public String toString() {
        if (minute<10)
            return hour+":0"+minute;
        else 
            return hour+":"+minute;
    }

    @Override
    public int compareTo(Time other) {
        int duration = ((hour*60) + minute) - ((other.hour*60)+other.minute);
        return duration;
    }
}
