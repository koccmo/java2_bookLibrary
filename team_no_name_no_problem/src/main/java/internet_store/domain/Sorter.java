package internet_store.domain;

public class Sorter<E extends Product> {

    private final E[] data;

    public Sorter(E[] data) {
        this.data = data;
    }

    public E[] sort() {
        for (int i = data.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                Product first = data[j];
                Product second = data[j+1];
                if (second.getPrice() > first.getPrice()) {
                    swapElements(j);
                } else if (second.getPrice() == first.getPrice()) {
                        swapElements(j);
                }
            }
        }
        return data;
    }

    private void swapElements(int j) {
        E tmp = data[j];
        data[j] = data[j + 1];
        data[j + 1] = tmp;
    }

}
