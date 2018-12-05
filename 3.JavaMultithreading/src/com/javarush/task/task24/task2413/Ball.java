package com.javarush.task.task24.task2413;

/**
 * Класс для шарика в игре
 */
public class Ball extends BaseObject {
    // скорость
    private double speed;
    // направление  (в градусах от 0 до 360)
    private double direction;

    // текущее значение вектора движения (dx,dy)
    private double dx;
    private double dy;

    // заморожен ли объект или может двигаться
    private boolean isFrozen;

    public Ball(double x, double y, double speed, double direction) {
        super(x, y, 1);

        this.direction = direction;
        this.speed = speed;

        this.isFrozen = true;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDirection() {
        return direction;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Устанавливаем новое направление движения.
     * Тут же вычисляем и новый вектор.
     * Такой подход удобно использовать при отскоках от стен.
     */
    void setDirection(double direction) {
        this.direction = direction;

        double angle = Math.toRadians(direction);
        dx = Math.cos(angle) * speed;
        dy = -Math.sin(angle) * speed;
    }

    /**
     * Рисуем себя на "канвасе".
     */
    @Override
    void draw(Canvas canvas) {
        canvas.setPoint(x, y, 'O');
    }

    /**
     * Двигаем себя на один шаг.
     */
    public void move() {
        if (isFrozen) return;

        x += dx;
        y += dy;

        checkRebound(1, Arkanoid.game.getWidth(), 1, Arkanoid.game.getHeight() + 1);
    }

    /**
     * Проверяем не улетел ли шарик за стенку.
     * Если да - отражаем его.
     */
    void checkRebound(int minx, int maxx, int miny, int maxy) {
        if (x < minx) {
            x = minx + (minx - x);
            dx = -dx;
        }

        if (x > maxx) {
            x = maxx - (x - maxx);
            dx = -dx;
        }

        if (y < miny) {
            y = miny + (miny - y);
            dy = -dy;
        }

        if (y > maxy) {
            y = maxy - (y - maxy);
            dy = -dy;
        }
    }

    /**
     * Запускам шарик.
     * isFrozen = false.
     * Пересчитываем вектор движения (dx,dy).
     */
    void start() {
        this.setDirection(direction);
        this.isFrozen = false;
    }

    int isIntersec(BaseObject o) {
        double x1 = x, x2 = x + dx;
        double y1 = y, y2 = y + dy;
        double x3, y3, x4, y4;

        if (o instanceof Stand) {
            return Intersec(x, y, x + dx, y + dy, o.x - o.radius, o.y - 1.0, o.x + o.radius, o.y - 1.0) ? 1 : 0;
        }
        else {
            return Intersec(x, y, x + dx, y + dy, o.x - o.radius + 0.05, o.y - 0.95, o.x + 1.95, o.y - 0.95) ? 1 :
                   Intersec(x, y, x + dx, y + dy, o.x - o.radius + 0.05, o.y - 0.95, o.x - o.radius + 0.05, o.y + o.radius + 0.95) ? 2 :
                   Intersec(x, y, x + dx, y + dy, o.x + 1.95, o.y - 0.95, o.x + 1.95, o.y + o.radius + 0.95) ? 3 :
                   Intersec(x, y, x + dx, y + dy, o.x - o.radius + 0.05, o.y + o.radius + 0.95, o.x + 1.95, o.y + o.radius + 0.95) ? 4 : 0;
        }
    }

    private double matrix_Px(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        return ((x1*y2 - y1*x2) * (x3 - x4) - (x1 - x2) * (x3*y4 - y3*x4))
                /((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));
    }

    private double matrix_Py(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        return ((x1*y2 - y1*x2) * (y3 - y4) - (y1 - y2) * (x3*y4 - y3*x4))
                /((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));
    }

    private boolean pointInSection(double x1, double y1, double x2, double y2, double Px, double Py) {
        double a1, b1, a2, b2;
        if (x1 < x2) {
            a1 = x1;
            a2 = x2;
        }
        else {
            a1 = x2;
            a2 = x1;
        }
        if (y1 < y2) {
            b1 = y1;
            b2 = y2;
        }
        else {
            b1 = y2;
            b2 = y1;
        }
        return (a1 <= Px) && (Px <= a2) && (b1 <= Py) && (Py <= b2);
    }

    private boolean Intersec(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4){
        double Px, Py;

        if (y3 == y4) {
            Py = y3;
            Px = matrix_Px(x1, y1, x2, y2, x3, y3, x4, y4);
        }
        else {
            Px = x3;
            Py = matrix_Py(x1, y1, x2, y2, x3, y3, x4, y4);
        }

        return pointInSection(x1, y1, x2, y2, Px, Py) && pointInSection(x3, y3, x4, y4, Px, Py);

    }
}
