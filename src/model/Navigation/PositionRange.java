package model.Navigation;

public class PositionRange {

    private  int _min = 0;
    private  int _max = 0;

    public PositionRange(int min, int max){
        if(min < 0)
            min = 0;
        if(max < min)
            max = min;

        _min = min;
        _max = max;
    }

    public int min(){
        return _min;
    }

    public int max(){
        return _max;
    }

    public int length(){
        return _max - _min + 1;
    }

    public static boolean isValidRange(int min, int max) {
        return min > 0 && max >= min;
    }

    // ------------------  ------------------

    public boolean contains(int val){
        return val >= _min && val <= _max;
    }
}
