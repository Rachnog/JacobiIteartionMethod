import java.util.Arrays;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Rachnogstyle & FlyingPirate
 * Date: 16.11.13
 * Time: 12:57
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        double[][] mat1;
        mat1 = new double[][]{
                {7.5, 2.1541, 0, 0},
                {2.1541, 8.4901, 2.2147, 0},
                {0, 2.2147, 6.4226, -0.7075},
                {0, 0, -0.7075, 7.8649},
        };

        for (int r = 0; r < 3; r++) {
            mat1 = yakobi(mat1);

            for (int i = 0; i < mat1.length; i++) {
                for (int j = 0; j < mat1.length; j++) {
                    System.out.print(mat1[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat1.length; j++) {
                System.out.print(mat1[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static double[][] multiply(double[][] mat1, double[][] mat2) {
        double[][] mat3 = new double[4][4];
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat2.length; j++) {
                for (int k = 0; k < mat3.length; k++) {
                    mat3[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
        }
        return mat3;
    }

    public static double[][] zeroing(double[][] a) {

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (i != j)
                    if (a[i][j] < 0.0000000001)
                        a[i][j] = 0;
            }
        }
        return a;
    }

    public static double[][] yakobi(double[][] a) {
        double[][] t = new double[4][4];
        for (int i = 0; i < t.length; i++) {
            t[i][i] = 1;
        }
        int maxi = 0;
        int maxj = 1;
        double max = Math.abs(a[0][1]);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (i != j)
                    if (Math.abs(max) < Math.abs(a[i][j])) {
                        max = a[i][j];
                        maxi = i;
                        maxj = j;
                    }
            }
        }
        double miu = (2 * max) / (a[maxi][maxi] - a[maxj][maxj]);
        double c = Math.sqrt((1 + 1 / Math.sqrt(1 + miu * miu)) / 2);
        double s = Math.signum(miu) * Math.sqrt((1 - 1 / Math.sqrt(1 + miu * miu)) / 2);
        t[maxi][maxi] = c;
        t[maxi][maxj] = -s;
        t[maxj][maxi] = s;
        t[maxj][maxj] = c;

        double[][] a1 = multiply(a, t);
        double[][] tt = transpose(t);
        return multiply(tt, a1);
    }

    public static double[][] transpose(double[][] t) {
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t.length; j++) {
                if (i < j) {
                    double x = t[i][j];
                    t[i][j] = t[j][i];
                    t[j][i] = x;
                }
            }
        }
        return t;
    }
}
