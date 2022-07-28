public class Item {
    private int chave;

    public Item(int chave) {
        this.chave = chave;
    }

    public int getChave() {
        return chave;
    }

    public int compara(Item it) {
        if (this.chave < it.chave)
            return -1;
        else if (this.chave > it.chave)
            return 1;
        return 0;
    }
}