package com.javarush.task.task39.task3905;

public class PhotoPaint {
    private Color fill = null;
    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {
        if (image.length > y && image[0].length > x && x > -1 && y > -1 && !desiredColor.equals(image[y][x])) {
            if (fill == null)
                fill = image[y][x];
            if (image[y][x].equals(fill)) {
                image[y][x] = desiredColor;
                paintFill(image, x - 1, y, desiredColor);
                paintFill(image, x, y - 1, desiredColor);
                paintFill(image, x, y + 1, desiredColor);
                paintFill(image, x + 1, y, desiredColor);

                return true;
            }
            else
                return false;
        }
        else
            return false;
    }
}
