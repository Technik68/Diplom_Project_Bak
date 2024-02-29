class INV_element implements Action{
    int Y = 0;
    private boolean input;
    private boolean output;
    @Override
    public void getInputValue(boolean[] in) {
        input = in[0];
    }

    @Override
    public void calculateOutput() {
        if(!input) output =true;
        if(input) output =false;
    }

    @Override
    public void printFormula() {
        Y = YValueManager.getYValue();
        System.out.println("Y" +Y+ " = !X[1] = " + output);
        YValueManager.incrementYValue();
    }


    @Override
    public boolean returnOutputValue() {
        return output;
    }

}
