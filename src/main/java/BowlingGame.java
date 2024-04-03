import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private final List<Integer> rolls;


    BowlingGame() {
        rolls = new ArrayList<>();
    }

    public void roll(Integer pin) {
        if(pin < 0 || pin > 10)
            throw new IllegalArgumentException();

        rolls.add(pin);
    }

    public Integer score() {
        List<Frame> frames = new ArrayList<>();
        for(int i=0; i<rolls.size();i++) {
            if(frames.size() < 9){
                if(rolls.get(i) == 10) {
                    frames.add(new Frame(10, 0, 0));
                } else {
                    if(rolls.get(i)+rolls.get(i+1) >10 || rolls.get(i)+rolls.get(i+1) <0){
                        throw new IllegalStateException();
                    }
                    Frame frame = new Frame(rolls.get(i), rolls.get(i+1), 0);
                    frames.add(frame);
                    i++;
                }
            }else if(frames.size()==9){
                if(i<rolls.size()-2){
                    Frame frame = new Frame(rolls.get(i),rolls.get(i+1),rolls.get(i+2));
                    frames.add(frame);
                }
            }
        }
//        frames.stream().mapToInt(Frame::baseScore).sum();
        Integer baseScore = 0;
        for(int i=0; i< frames.size(); i++){
            if(i < 9){
                baseScore += frames.get(i).baseScore();
            }else if(i == 9 && frames.get(i).isStrike() ){
                baseScore += frames.get(i).getRoll1();
            }else if(i == 9 && frames.get(i).isSpare() ){
                baseScore += frames.get(i).baseScore();
            }
        }
        Integer bonusScore = 0;
        for(int i=0; i<frames.size(); i++){
                Frame frame = frames.get(i);
                if(frame.isSpare()) {
                    if(i < frames.size()-1){
                        bonusScore += frames.get(i+1).getRoll1();
                    }else if(i==frames.size()){
                        bonusScore += frames.get(i).getRoll2();
                    }
                }
                if(frame.isStrike()) {
                    if(i<frames.size()-1){
                        bonusScore += frames.get(i+1).getRoll1();
                        if(frames.get(i+1).isStrike() && i<frames.size()-2){
                            bonusScore += frames.get(i+2).getRoll2();
                        }else{
                            bonusScore += frames.get(i+1).getRoll2();
                        }

                    }else if(i == frames.size()-1){
                        bonusScore += frames.get(i).getRoll1();
                        bonusScore += frames.get(i).getRoll2();
                        bonusScore += frames.get(i).getRoll3();
                    }

                }
        }
        return baseScore + bonusScore;
    }
}