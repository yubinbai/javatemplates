package algorithm.clrs.backpack.integer;

/**
 * Solver for the 0-1 backpack problem
 *
 */
public class BackpackSolver {

    int numOfItems;
    double backpackCapacity;

    double[] itemValue = new double[numOfItems];
    double[] itemCost = new double[numOfItems];

    // variables for used by the solver
    boolean[] backpackContain;
    double remainingCapacity;
    double maxValue, currentValue;

    public BackpackSolver(int numOfItems, double backpackCapacity,
                          double[] itemValue, double[] itemCost) {
        this.numOfItems = numOfItems;
        this.backpackCapacity = backpackCapacity;
        this.itemValue = itemValue;
        this.itemCost = itemCost;
        this.backpackContain = new boolean[this.numOfItems];
        this.remainingCapacity = this.backpackCapacity;
        this.maxValue = 0.0;
    }

    /**
     * Generate all possible solutions and find the max by brute force
     *
     * @return max value of backpack
     */
    public double bruteforce() {
        int permutation = 1;
        permutation <<= this.numOfItems;

        this.maxValue = 0.0;
        this.currentValue = 0.0;

        for (int i = 0; i < permutation; i++) {
            int current = i;
            for (int j = 0; j < this.numOfItems; j++) {
                backpackContain[j] = (current & 1) == 1 ? true : false;
                current >>= 1;
            }
            this.updateMaxValue();
        }

        return this.maxValue;

    }

    public double backtrack() {
        for (int i = 0; i < this.numOfItems; i++)
            backpackContain[i] = false;

        this.maxValue = 0.0;
        this.remainingCapacity = this.backpackCapacity;

        this.search(0);

        return this.maxValue;
    }

    /**
     * perform the backtrack searching
     *
     * @param step
     *            depth in backtrack search
     */
    private void search(int step) {

        for (int i = step; i < this.numOfItems; i++)
            if (!backpackContain[i]) {
                this.putIn(i);
                // skip the ones that will not work
                if (this.remainingCapacity > 0)
                    search(step + 1);
                this.takeOut(i);
            }
    }

    private void takeOut(int i) {
        // take item out of backpack
        this.backpackContain[i] = false;
        this.remainingCapacity += itemCost[i];
        this.currentValue -= itemValue[i];

    }

    private void putIn(int i) {
        // put item in backpack
        this.backpackContain[i] = true;
        this.remainingCapacity -= itemCost[i];
        this.currentValue += itemValue[i];

        if (this.currentValue >= this.maxValue && this.remainingCapacity >= 0)
            this.maxValue = this.currentValue;
    }

    private void updateMaxValue() {
        double sumValue = 0.0, sumCost = 0.0;
        for (int i = 0; i < this.numOfItems; i++)
            if (backpackContain[i]) {
                sumValue += itemValue[i];
                sumCost += itemCost[i];
            }
        if (sumCost <= this.backpackCapacity && sumValue >= this.maxValue)
            this.maxValue = sumValue;

    }

}
