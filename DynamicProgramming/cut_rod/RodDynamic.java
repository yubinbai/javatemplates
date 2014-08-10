package dynamic.programming.cut_rod;

public class RodDynamic {

    int[] rodValue;
    Integer[] memoized = new Integer[100];
    Integer[] selection = new Integer[100];

    public RodDynamic(int[] rodValue) {
        super();
        this.rodValue = rodValue;
    }

    public int cutRodTopDown(int length) {
        if (length == 0)
            return 0;
        if (memoized[length] != null)
            return memoized[length];
        int value = Integer.MIN_VALUE;
        for (int i = 1; i <= length; i++) {
            value = Math.max(value, rodValue[i] + cutRodTopDown(length - i));
        }
        memoized[length] = value;
        return value;
    }

    public int cutRodBottomUp(int length) {
        memoized[0] = 0;
        memoized[1] = rodValue[1];
        for (int partLength = 2; partLength <= length; partLength++) {
            // no cutting
            memoized[partLength] = rodValue[partLength];
            // cutting from 1 to partLength - 1
            for (int i = 1; i < partLength; i++) {
                memoized[partLength] =
                    Math.max(
                        memoized[partLength]
                        , rodValue[i] + memoized[partLength - i]
                    );
            }
        }

        return memoized[length];
    }

    public int extendCutRodBottomUp(int length) {
        memoized[0] = 0;
        selection[0] = 0;
        memoized[1] = rodValue[1];
        selection[1] = 1;
        for (int partLength = 2; partLength <= length; partLength++) {
            // no cutting
            memoized[partLength] = rodValue[partLength];
            selection[partLength] = partLength;
            // cutting from 1 to partLength - 1
            for (int i = 1; i < partLength; i++) {
                if (rodValue[i] + memoized[partLength - i] > memoized[partLength]) {
                    memoized[partLength] = rodValue[i]
                                           + memoized[partLength - i];
                    selection[partLength] = i;
                }
            }
        }

        return memoized[length];
    }

    public String printSelection(int Length) {
        if (Length > 0)
            return "The rod :" + selection[Length] + " with value "
                   + rodValue[selection[Length]] + " is selected. \n"
                   + printSelection(Length - selection[Length]);
        else
            return "";
    }

}
