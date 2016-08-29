package com.jecyhw;

import com.jecyhw.html.baidu.BaikeSerach;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by jecyhw on 16-8-26.
 */
public class DiseaseDiseasePestServiceTest {
    @Test
    public void test() throws IOException {
        BaikeSerach serach = new BaikeSerach(new BaikeSerach.Params("松毛虫"));
        serach.html();
    }
}
