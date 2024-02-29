public class OR_element implements Action{
    int Y = 0;
    private boolean[] input;
    private boolean output;

    @Override
    public void getInputValue(boolean[] in) {
        if (in == null) {
            throw new IllegalArgumentException("Входной массив не может быть null");
        }

        input = new boolean[in.length];
        System.arraycopy(in, 0, input, 0, in.length);
    }

    @Override
    public void calculateOutput() {
        if (input == null) {
            throw new IllegalArgumentException("Входной массив не может быть null");
        }
        output = input[0];

        for (int i = 1; i < input.length; i++) {
            output = output || input[i];
        }
    }

    @Override
    public void printFormula() {
        Y = YValueManager.getYValue();
        System.out.print("Y" +Y+ " X[1]");
        YValueManager.incrementYValue();

        for (int i = 2; i < input.length + 1; i++) {
            System.out.print(" || X[" + i + "]");
        }
        System.out.print(" = " + output);
        System.out.println();
    }

    @Override
    public boolean returnOutputValue() {
        return output;
    }
}
