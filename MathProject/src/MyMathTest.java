import static org.junit.jupiter.api.Assertions.*;

class MyMathTest {
    static final double EPSILON = 1e-7;

    @org.junit.jupiter.api.Test
    void test01() {
        assertEquals(25.0, MyMath.sqr(5.0), EPSILON);
    }

    @org.junit.jupiter.api.Test
    void test02() {
        assertEquals(16.0, MyMath.sqr(-4.0), EPSILON);
    }

    @org.junit.jupiter.api.Test
    void test03() {
        assertEquals(9.0, MyMath.sqr(3), EPSILON);
    }
}