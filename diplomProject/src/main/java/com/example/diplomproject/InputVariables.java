package com.example.diplomproject;

public class InputVariables {
    boolean x1, x2, x3, x4, not_x1, not_x2, not_x3, not_x4;
    void getNumbersOfInputs(int in) {


        switch (in) {
            case 1:
                x1 = true;
                not_x1 = false;
                break;
            case 2:
                x1 = true;
                x2 = true;

                not_x1 = false;
                not_x2 = false;
                break;
            case 3:
                x1 = true;
                x2 = true;
                x3 = true;
                break;
            case 4:
                x1 = true;
                x2 = true;
                x3 = true;
                x4 = true;
                break;
        }
    }
    public boolean getX1() {
        return x1;
    }

    public boolean getX2() {
        return x2;
    }

    public boolean getX3() {
        return x3;
    }

    public boolean getX4() {
        return x4;
    }

    public boolean getNot_X1() {
        return not_x1;
    }

    public boolean getNot_X2() {
        return not_x2;
    }

    public boolean getNot_X3() {
        return not_x3;
    }

    public boolean getNot_X4() {
        return not_x4;
    }
}

