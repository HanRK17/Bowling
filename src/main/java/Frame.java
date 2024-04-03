public class Frame {

    private final Integer roll1;
    private final Integer roll2;
    private final Integer roll3;

    Frame(Integer roll1, Integer roll2, Integer roll3){
        this.roll1 = roll1;
        this.roll2 = roll2;
        this.roll3 = roll3;
    }

    public boolean isSpare() {
        return this.roll1 + this.roll2 == 10 && this.roll2 != 0;
    }

    public boolean isStrike() {
        return this.roll1 == 10 && this.roll2 == 0;
    }

    public Integer baseScore() {
        return this.roll1 + this.roll2;
    }

    public Integer getRoll1() {
        return roll1;
    }

    public Integer getRoll2() {
        return roll2;
    }

    public Integer getRoll3(){
        return roll3;
    }

}