package bll;

import java.util.ArrayList;
import java.util.List;

public class BLLException extends Exception{
    private List<String> errors = new ArrayList<>();

    public boolean hasErrors(){
        if(errors.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    public String getMessages(){
        return errors.toString();
    }

    @Override
    public String toString() {
        return "BLLException{" +
                "errors=" + errors +
                '}';
    }
}
