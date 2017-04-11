package testArtificial;

import com.qian.service.Artificial;
import org.junit.Test;

/**
 * Created by wuhuaiqian on 17-3-12.
 */
public class TestArtifical {
    @Test
    public void test1(){
        int [] [] chessBoard = new int[4][4];
        new Artificial(chessBoard);

    }
}
