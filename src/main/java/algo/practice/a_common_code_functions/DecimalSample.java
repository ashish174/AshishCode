package algo.practice.a_common_code_functions;

import java.util.Arrays;
import java.util.Comparator;

public class DecimalSample {
  // float	Primitive	~7 decimal digits	Memory-saving, non-critical calculations
  // double	Primitive	~16 decimal digits	General purpose calculations; the default
  // BigDecimal	Class (Object)	Arbitrary/Exact	Financial calculations, exact precision required
    void sample(){
        float[][] remainingDistanceAndTime = new float[10][2];
        Arrays.sort(remainingDistanceAndTime, Comparator.comparing(arr -> arr[0]));
    }
}
