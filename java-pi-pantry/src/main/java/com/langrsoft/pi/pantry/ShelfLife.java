package com.langrsoft.pi.pantry;

public class ShelfLife {
    private int refrigerated;
    private int frozen;
    private int unopened;

    public ShelfLife(int refrigerated, int frozen, int unopened) {
        this.refrigerated = refrigerated;
        this.frozen = frozen;
        this.unopened = unopened;
    }

    public ShelfLife(int refrigerated, int frozen) {
        this(refrigerated, frozen, 0);
    }

    public int getRefrigerated() {
        return refrigerated;
    }

    public void setRefrigerated(int refrigerated) {
        this.refrigerated = refrigerated;
    }

    public int getFrozen() {
        return frozen;
    }

    public void setFrozen(int frozen) {
        this.frozen = frozen;
    }
}
