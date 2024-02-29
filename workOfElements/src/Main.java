public class Main {
    public static void main(String[] args) {
        //Пример с обратной связью
        boolean v1, v2;
        boolean v3 = false;
        boolean inputValue1 = false;
        boolean inputValue2 = true;

        //Инверсия
        INV_element invElement = new INV_element();
        invElement.getInputValue(new boolean[]{inputValue1});
        invElement.calculateOutput();
        invElement.printFormula();
        v1 = invElement.returnOutputValue();
        System.out.println(v1);

        // умножение
        AND_element andElement = new AND_element();

        boolean[] inputValues = {v1, v3};

        andElement.getInputValue(inputValues);
        andElement.calculateOutput();
        andElement.printFormula();
        v2 = andElement.returnOutputValue();
        System.out.println(v2);

        //или
        OR_element orElement = new OR_element();

        boolean[] inputValues2 = {v2, inputValue2};

        orElement.getInputValue(inputValues2);
        orElement.calculateOutput();
        orElement.printFormula();
        v3 = orElement.returnOutputValue();
        System.out.println(v3);

        inputValues = new boolean[]{v1, v3};
        andElement.getInputValue(inputValues);
        andElement.calculateOutput();
        andElement.printFormula();
        v2 = andElement.returnOutputValue();
        System.out.println(v2);





/*// Пример без обратной связи
        // Создаем объект класса INV_element
        INV_element invElement = new INV_element();

        // Устанавливаем входное значение
        boolean inputValue = false;
        invElement.getInputValue(new boolean[]{inputValue});

        // Вычисляем выходное значение
        invElement.calculateOutput();

        // Выводим формулу и результат
        invElement.printFormula();

        // Создаем объект класса AND_element
        AND_element andElement = new AND_element();
        boolean[] inputValues = {true, false, true};
        andElement.getInputValue(inputValues);
        andElement.calculateOutput();
        andElement.printFormula();

        // Создаем объект класса OR_element
        OR_element orElement = new OR_element();
        orElement.getInputValue(inputValues);
        orElement.calculateOutput();
        orElement.printFormula();*/
    }
}