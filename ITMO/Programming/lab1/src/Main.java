public class Main {
    public static void main(String[] args) {
        short[] c = new short[7];
        float[] x = new float[12];
        double[][] a = new double[7][12];
        short lastEvenNumber = 6;

        for (short i = 0; i < c.length; i++) {
            c[i] = lastEvenNumber;
            lastEvenNumber += 2;
        }

        for (int i = 0; i < x.length; i++) {
            x[i] = (float) (-9.0 + Math.random() * (8.0 - (-9.0)));
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 12; j++) {
                switch (c[i]) {
                    case 16 -> a[i][j] = Math.atan(Math.sin(Math.atan(Math.cos(x[j]))));
                    case 6, 8, 10 -> a[i][j] = 2.0 / 3.0 * Math.asin(1 / Math.pow(Math.E, Math.abs(x[j])));
                    default -> a[i][j] = Math.atan(Math.sin(Math.log(Math.acos((x[j] - 0.5) / 17))));
                }
                System.out.printf("%-100.2f ", a[i][j]);
            }
            System.out.println();
        }
    }
}
