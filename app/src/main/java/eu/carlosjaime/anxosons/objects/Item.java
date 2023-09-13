package eu.carlosjaime.anxosons.objects;

import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable {
    private Integer drawableBaseImage;
    private Integer drawableActiveImage;
    private Integer rawSound;

    public Item(Integer drawableBaseImage, Integer drawableActiveImage, Integer rawSound) {
        this.drawableBaseImage = drawableBaseImage;
        this.drawableActiveImage = drawableActiveImage;
        this.rawSound = rawSound;
    }

    public Integer getDrawableBaseImage() {
        return drawableBaseImage;
    }

    public void setDrawableBaseImage(Integer drawableBaseImage) {
        this.drawableBaseImage = drawableBaseImage;
    }

    public Integer getDrawableActiveImage() {
        return drawableActiveImage;
    }

    public void setDrawableActiveImage(Integer drawableActiveImage) {
        this.drawableActiveImage = drawableActiveImage;
    }

    public Integer getRawSound() {
        return rawSound;
    }

    public void setRawSound(Integer rawSound) {
        this.rawSound = rawSound;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(drawableBaseImage, item.drawableBaseImage) && Objects.equals(drawableActiveImage, item.drawableActiveImage) && Objects.equals(rawSound, item.rawSound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drawableBaseImage, drawableActiveImage, rawSound);
    }

    @Override
    public String toString() {
        return "Item{" +
                "drawableBaseImage=" + drawableBaseImage +
                ", drawableActiveImage=" + drawableActiveImage +
                ", rawSound=" + rawSound +
                '}';
    }
}
