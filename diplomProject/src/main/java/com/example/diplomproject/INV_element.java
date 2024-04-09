package com.example.diplomproject;

import javafx.scene.canvas.GraphicsContext;
//Для клика мыши
import javafx.scene.input.MouseEvent;

class INV_element implements Action {
    private static int count = 0;
    private String name;
    private double RectangleX, RectangleY;
    private double rectangleWidth;
    private double rectangleHeight;

    public INV_element() {
        count++;
        this.name = "INV Element" + count;
        System.out.println(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void getRectangleInfo(double getX, double getY) {
        RectangleX = getX;
        RectangleY = getY;
    }

    @Override
    public void setRectangleInfo(double setX, double setY) {
        setX = this.RectangleX;
        setY = this.RectangleY;
    }

    int Y = 0;
    private boolean input;
    private boolean output;

    @Override
    public void getInputValue(boolean[] in) {
        input = in[0];
    }

    @Override
    public void calculateOutput() {
        if (!input) output = true;
        if (input) output = false;
    }


    @Override
    public void printFormula() {
        Y = YValueManager.getYValue();
        System.out.println("Y" + Y + " = !X[1] = " + output);
        YValueManager.incrementYValue();
    }

    @Override
    public boolean returnOutputValue() {
        return output;
    }

    @Override
    public void drawElement(GraphicsContext gc, double canvasWidth, double canvasHeight) {
        // Размеры прямоугольника
        rectangleWidth = 50;
        rectangleHeight = 91;
        // Вычисляем координаты прямоугольника таким образом, чтобы его левый край был по центру
        double rectangleX = (canvasWidth - rectangleWidth) / 2;
        double rectangleY = (canvasHeight - rectangleHeight) / 2;

        // Находим координаты для отображения текста "1" в центре прямоугольника
        double textX = rectangleX + rectangleWidth / 2;
        double textY = rectangleY + rectangleHeight / 2;

        // Рисуем текст "1" в центре прямоугольника
        gc.strokeText("1", textX, textY);

        // Рисуем прямоугольник
        gc.strokeRect(rectangleX, rectangleY, rectangleWidth, rectangleHeight);

        // Вычисляем координаты для линий
        double lineX1 = rectangleX;
        double lineX2 = rectangleX + rectangleWidth;
        double lineY = canvasHeight / 2; // Центр по вертикали

        // Рисуем линии
        gc.strokeLine(lineX1 - 8, lineY, lineX1, lineY); // Вертикальная линия слева от прямоугольника
        gc.strokeLine(lineX2, lineY, lineX2 + 8, lineY); // Вертикальная справа от прямоугольника

        // Сохраняем информацию о прямоугольнике
        getRectangleInfo(rectangleX, rectangleY);

        // Добавляем обработчик события клика мыши и передаем GraphicsContext
        gc.getCanvas().setOnMouseClicked(event -> handleClick(event, gc));
    }

    // Метод для обработки события клика мыши
    public boolean isFirstClick = true; // Флаг для отслеживания первого клика
    private void handleClick(MouseEvent event, GraphicsContext gc) {
        double clickX = event.getX();
        double clickY = event.getY();

        // Проверяем, что клик произошел в пределах прямоугольника
        if (clickX >= RectangleX && clickX <= RectangleX + rectangleWidth &&
                clickY >= RectangleY && clickY <= RectangleY + rectangleHeight) {
            if (isFirstClick) {
                System.out.println("First click on INV_element: " + getName());
                isFirstClick = false;
            }
        } else {
            if (!isFirstClick) {
                System.out.println("Second click on INV_element: " + getName());
                // Удаляем отрисовку текущего прямоугольника
                removeCurrentRectangle(gc);
                // Отрисовываем новый прямоугольник и передаем объект события MouseEvent
                //drawNewRectangle(gc, clickX - rectangleWidth / 2, clickY - rectangleHeight / 2);
                drawElement(gc, clickX *2, clickY *2);
                isFirstClick = true; // Сбрасываем флаг для следующего клика
            }
        }
    }

    // Метод для удаления отрисовки текущего прямоугольника
    private void removeCurrentRectangle(GraphicsContext gc) {
        gc.clearRect(RectangleX-10, RectangleY-10, rectangleWidth+20, rectangleHeight+20);

    }

    public boolean isClicked(double mouseX, double mouseY) {
        // Предположим, что x и y - это координаты верхнего левого угла элемента,
        // а width и height - его ширина и высота соответственно.

        // Проверяем, находится ли клик в пределах горизонтальной границы элемента
        boolean withinXBounds = mouseX >= RectangleY && mouseX <= (RectangleX + rectangleWidth);

        // Проверяем, находится ли клик в пределах вертикальной границы элемента
        boolean withinYBounds = mouseY >= RectangleY && mouseY <= (RectangleY + rectangleHeight);

        // Если клик находится в пределах обеих границ, возвращаем true, иначе - false
        return withinXBounds && withinYBounds;
    }
}