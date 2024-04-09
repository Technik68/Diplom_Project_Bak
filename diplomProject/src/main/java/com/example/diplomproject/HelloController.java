package com.example.diplomproject;


import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ComboBox<Integer> numbers_of_inputs;

    private Integer selectedNumbersOfInputs;

    @FXML
    private void initialize() {
        // Создаем список элементов для ComboBox
        ObservableList<Integer> items = FXCollections.observableArrayList(1, 2, 3, 4);

        // Устанавливаем список в ComboBox
        numbers_of_inputs.setItems(items);

        // Устанавливаем слушатель изменения выбора в ComboBox
        numbers_of_inputs.setOnAction(event -> handleComboBoxSelection());

        // Устанавливаем реакцию на нажатие левой кнопки мыши
        canvas.setOnMouseClicked(this::handleCanvasClick);

    }

    @FXML
    private Canvas canvas;

    @FXML
    char overlining = '\u0304'; // Надчеркивание над буквой

    private void drawTextWithBackground(GraphicsContext gc, String text, double x, double y) {
        double fontSize = 16; // Размер шрифта
        double padding = -4; // Отступ текста от линии

        // Создаем объект Text для измерения размеров текста
        Text textNode = new Text(text);
        textNode.setFont(Font.font(fontSize)); // Установка размера шрифта

        // Получаем размеры текста
        double textWidth = textNode.getLayoutBounds().getWidth();
        double textHeight = textNode.getLayoutBounds().getHeight();

        // Отрисовываем прямоугольник белого цвета за текстом
        double textX = x + padding; // X-координата текста
        gc.setFill(Color.WHITE); // Цвет фона
        gc.fillRect(textX, y - textHeight, textWidth, textHeight); // Прямоугольник фона

        // Рисуем текст
        gc.setFill(Color.BLACK); // Цвет текста
        gc.fillText(text, textX, y); // Рисуем текст
    }

    boolean[] firstValues; // Создаем массив boolean для хранения значений

    @FXML
    private void handleComboBoxSelection() {
        selectedNumbersOfInputs = numbers_of_inputs.getValue();
        System.out.println("Selected: " + selectedNumbersOfInputs);
        // Здесь вы можете добавить код для обработки изменения выбора
        // Получаем GraphicsContext из Canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Очищаем предыдущие рисунки
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Получаем количество выбранных чисел
        int selectedNumber = numbers_of_inputs.getValue();

        // Рисуем вертикальные линии для каждого числа вдоль левого края списка
        double lineSpacing = canvas.getHeight() / (selectedNumber + 70);

        gc.setStroke(Color.BLACK);  // Цвет линии
        gc.setLineWidth(2);         // Ширина линии

        firstValues = new boolean[selectedNumber * 2];

        for (int i = 1; i <= selectedNumber * 2; i++) {
            double y = i * lineSpacing;
            gc.strokeLine(y, 0, y, canvas.getHeight());  // Вертикальные линии

            String text = ""; // Текст для отображения++
            boolean firstValue = false; // Значение переменной типа boolean

            if (i % 2 != 0) {
                text = "X" + (i / 2 + 1);
                firstValue = true;
            } else {
                text = "X" + overlining + (i / 2);
                firstValue = false;
            }

            firstValues[i - 1] = firstValue; // Записываем значения в массив

            drawTextWithBackground(gc, text, y, 12);
        }
    }

    private boolean isFirstClick = false;
    public boolean input_value=false;
    public void handleCanvasClick(javafx.scene.input.MouseEvent mouseEvent) {
        double mouseX = mouseEvent.getX(); // Получаем координату X щелчка мыши
        double mouseY = mouseEvent.getY();
        double lineSpacing = canvas.getHeight() / (selectedNumbersOfInputs + 70);
        int i=1;
        if(!isFirstClick){
            // Проверяем, был ли клик в пределах какой-либо вертикальной линии
            for (i = 1; i <= selectedNumbersOfInputs * 2; i++) {
                double y = i * lineSpacing;
                double lineX = y; // X-координата вертикальной линии
                // Проверяем, попадает ли координата X щелчка мыши в пределы вертикальной линии
                if (Math.abs(mouseX - lineX) < 5) { // Здесь 5 - это допустимый зазор\
                    System.out.println("Clicked on line " + i + ", value: " + firstValues[i - 1]);
                    input_value=firstValues[i-1];
                    break; // Мы нашли линию, поэтому нет смысла продолжать проверку
                }
            }
            isFirstClick =true;
        }
        else {
                System.out.println("value: " + input_value);
                //Проверяем, был ли клик в пределах какого либо INV_elements
                for (int j = 0; j < invElements.size(); j++) {
                    INV_element invElement = invElements.get(j);
                    if (invElement.isClicked(mouseX, mouseY)) {
                        System.out.println("Clicked on INV_element " + (j + 1));
                        invElement.getInputValue(new boolean[]{input_value});
                        invElement.calculateOutput();
                        invElement.printFormula();
                        break;
                    }
                }
                isFirstClick = false;
            }
    }


    // Кнопка Инверсия
    @FXML
    private Button INV_button;
    private List<INV_element> invElements = new ArrayList<>();
    @FXML
    private void handle_INV_button_click() {
     /*   InputVariables inputVariables = new InputVariables();
        inputVariables.getNumbersOfInputs(1);
        boolean inputValue1 = inputVariables.getNot_X1();

        System.out.println("INV Button clicked!");
        INV_element invElement = new INV_element();
        invElement.getInputValue(new boolean[]{inputValue1});
        invElement.calculateOutput();
        invElement.printFormula();*/

        System.out.println("INV Button clicked!");
        INV_element NewInvElement = new INV_element();
        invElements.add(NewInvElement);

        // Получаем GraphicsContext из Canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // Рисуем элемент на Canvas
        NewInvElement.drawElement(gc, canvas.getWidth(), canvas.getHeight());
    }

    public void handle_connect_elements() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.getCanvas().setOnMouseClicked(mouseEvent -> handleCanvasClick(mouseEvent));
    }
}
