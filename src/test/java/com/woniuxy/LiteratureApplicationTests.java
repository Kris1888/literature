package com.woniuxy;

import com.woniuxy.controller.EditorController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LiteratureApplicationTests {

    @Test
    void contextLoads() {

        EditorController editorController=new EditorController();
        editorController.findBook();
    }

}
