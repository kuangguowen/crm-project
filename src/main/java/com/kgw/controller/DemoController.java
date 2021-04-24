package com.kgw.controller;


import com.kgw.domin.entity.Brand;
import com.kgw.domin.entity.Category;
import com.kgw.commom.http.AxiosResult;
import com.kgw.service.BrandService;
import com.kgw.service.CategoryService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/4/17 10:05
 * 文件说明：
 */

@RestController
public class DemoController {


    /**
     *      <dependency>
     *             <groupId>org.jsoup</groupId>
     *             <artifactId>jsoup</artifactId>
     *             <version>1.11.3</version>
     *         </dependency>
     *
     *
     */
    @Autowired
    CategoryService categoryService;


    @Autowired
    BrandService brandService;


    @GetMapping("getCategoryFromJD")
    public AxiosResult<Void> setData() throws IOException {
        Document document = Jsoup.connect("https://www.jd.com/allSort.aspx").get();
        Elements elementsByClass = document.getElementsByClass("category-items");

        for (int i = 0; i < elementsByClass.size(); i++) {
            Element element = elementsByClass.get(i);
            Elements element1 = element.getElementsByClass("category-item");
            for (int j = 0; j < element1.size(); j++) {
                Element element2 = element1.get(j);


                //一级分类名
                String firstCategoryName = element2.getElementsByTag("span").text();
                Category firstCategory = new Category();
                firstCategory.setCatetoryName(firstCategoryName);
                firstCategory.setCategoryLevel(1);
                firstCategory.setParentId(0L);
                categoryService.add(firstCategory);

                //二级分类
                Elements dt = element2.getElementsByTag("dl");
                for (int k = 0; k < dt.size(); k++) {
                    Element element3 = dt.get(k);
                    String secondCategoryName = element3.getElementsByTag("dt").get(0).getElementsByTag("a").get(0).text();
                    Category sencondCategory = new Category();
                    sencondCategory.setParentId(firstCategory.getId());
                    sencondCategory.setCatetoryName(secondCategoryName);
                    sencondCategory.setCategoryLevel(2);
                    categoryService.add(sencondCategory);
                    Elements a = element3.getElementsByTag("dd").get(0).getElementsByTag("a");
                    for (int l = 0; l < a.size(); l++) {

                        Category threeCategory = new Category();
                        threeCategory.setParentId(sencondCategory.getId());
                        threeCategory.setCatetoryName(a.get(l).text());
                        threeCategory.setCategoryLevel(3);
                        categoryService.add(threeCategory);

                    }


                }


            }
        }
        return AxiosResult.success();

    }


    @GetMapping("getBrandFromJD")
    public AxiosResult<Void> getBrandFromJd() throws Exception {
        Document document = Jsoup.connect("https://www.jd.com/brand.aspx").get();

        Elements brandslist = document.getElementsByClass("brandslist");
        for (int i = 0; i < brandslist.size(); i++) {
            Element element1 = brandslist.get(i);
            Elements li = element1.getElementsByTag("li");
            for (int j = 0; j < li.size(); j++) {
                Element img = li.get(j).getElementsByTag("img").get(0);
                System.out.println(img);
                String src = img.attr("src");
                String alt = img.attr("alt");
                System.out.println(src);
                Element span = li.get(j).getElementsByTag("span").get(1).getElementsByTag("a").get(0);
                String text = span.text();
                Brand brand = new Brand();
                brand.setBrandName(text);
                brand.setBrandDesc(alt);
                brand.setBrandLogo(src);
                brand.setBrandSite("http://www.baidu.com");
                brandService.add(brand);

            }


        }


        return AxiosResult.success();

    }


}
