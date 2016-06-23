package com.luosl.akhasi.utils;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * Created by Administrator on 2016/5/27.
 */
public class HtmlUtils {
    private static Whitelist whitelist = Whitelist.basic()
            .addTags(new String[]{"img"})
            .addAttributes("img", "align", "alt", "height", "src", "title", "width");
    public static String cleanScriptTag(String htmlStr){
        return htmlStr.replaceAll("<script[^>]*>[\\d\\D]*?</script>","");
    }

    public static String clean(String htmlStr){
        return Jsoup.clean(htmlStr,whitelist);
    }

}
