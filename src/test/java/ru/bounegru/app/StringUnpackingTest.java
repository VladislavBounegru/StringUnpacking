package ru.bounegru.app;


import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class StringUnpackingTest {
    @Test
    public void unpacking_Should_Return_Correct_Answer_First() {
        String str = "3[xyz]4[xy]z";
        assertThat(StringUnpacking.unpacking(str)).isEqualTo("xyzxyzxyzxyxyxyxyz");
    }
    @Test
    public void unpacking_Should_Return_Correct_Answer_Second() {
        String str = "2[3[x]y]";
        assertThat(StringUnpacking.unpacking(str)).isEqualTo("xxxyxxxy");
    }
    @Test
    public void unpacking_Should_Return_Correct_Answer_Third() {
        String str = "2[3[x]y]3[xyz]4[xy]z";
        assertThat(StringUnpacking.unpacking(str)).isEqualTo("xxxyxxxyxyzxyzxyzxyxyxyxyz");
    }

    @Test
    public void unpacking_With_Empty_Brackets() {
        String str = "x123[]";
        assertThat(StringUnpacking.unpacking(str)).isEqualTo("String isn't correct");
    }
    @Test
    public void unpacking_With_Wrong_Amount_Of_Brackets() {
        String str = "1[x][]][";
        assertThat(StringUnpacking.unpacking(str)).isEqualTo("String isn't correct");
    }

    @Test
    public void unpacking_With_Wrong_Chars() {
        String str = "2[x,]";
        assertThat(StringUnpacking.unpacking(str)).isEqualTo("String isn't correct");
    }

}
