package algs;

public class Week1AssessmentAnalisysOfAlgorithms {

    private final double[][] in;
    private double a;
    private double b;

    public Week1AssessmentAnalisysOfAlgorithms(double[][] in) {
        this.in = in;
        this.b = 0;
        this.a = 0;
        for (int i = 0; i < in.length - 1; i++) {
            if (in[i][1] != 0 && in[i][0] != 0) {
                if (2 * in[i][0] != in[i + 1][0]) {
                    throw new IllegalArgumentException();
                }
                //System.out.format("\nin[i + 1][1] / in[i][1]=%s\n", in[i + 1][1] / in[i][1]);
                double bi = Math.log(in[i + 1][1] / in[i][1]) / Math.log(2);
                //System.out.format("\nb[%d]=%f\n", i, bi);
                b = b + bi;
            }
        }
        b = b / (in.length - 1);
        for (int i = 0; i < in.length; i++) {
            a = a + in[i][1] / Math.pow(in[i][0], b);
        }
        a = a / in.length;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        sb.append("a=").append(a);
        sb.append(", b=").append(b);
        sb.append('}');
        return sb.toString();
    }

    public double getB() {
        return b;
    }

    public double getA() {
        return a;
    }
}
