package com.jecyhw.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URISyntaxException;

/**
 * Created by jecyhw on 16-8-29.
 */
final public class Freemarker {

    final static Logger logger = LoggerFactory.getLogger(Freemarker.class);

    private static Configuration configuration;

    private static Template getTemplate(String name) throws IOException {
        return configuration.getTemplate(name);
    }

    public static String process(String name, Object dataModel) {
        String result = "";
        StringWriter writer = new StringWriter();
        try {
            getTemplate(name).process(dataModel, writer);
            result = writer.toString();
        } catch (TemplateException var5) {
            var5.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    static {
        configuration = new Configuration(Configuration.VERSION_2_3_23);
        try {
            configuration.setDirectoryForTemplateLoading(new File(Freemarker.class.getResource("/").toURI().getPath() + "template"));
        } catch (IOException var1) {
            var1.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}
