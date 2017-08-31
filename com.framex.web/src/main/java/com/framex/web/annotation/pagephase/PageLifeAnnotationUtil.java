package com.framex.web.annotation.pagephase;

import java.util.Arrays;
import java.util.List;

public class PageLifeAnnotationUtil{
    public static List<Class<?>> getAllPageLifeAnnotations(){
        return Arrays.asList(new Class<?>[]{PageInit.class, PageLoad.class, PageRenderComponent.class, PageDestory.class});
    }
}
