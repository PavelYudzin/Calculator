package calculator;

class Calculation {

    static String calculate(double fNum, double sNum, String operation){
        double rez = 0;
        String result;
        switch (operation){
            case "+": rez = fNum + sNum;
                break;
            case "-": rez = fNum - sNum;
                break;
            case "x": rez = fNum * sNum;
                break;
            case "/": if(sNum != 0) {rez = fNum / sNum;}
                break;
        }

        double roundedRez = Math.rint(rez * 100000000)/100000000;

        if(sNum == 0 && operation.equals("/")) {result = "ERROR";}
        else result = Double.toString(roundedRez);


        return result;
    }
}
