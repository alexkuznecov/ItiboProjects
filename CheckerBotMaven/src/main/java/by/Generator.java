package by;

import by.dao.AnimeDao;
import by.domain.AnimeReportBean;
import by.services.AnimeService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Generator {

    public void create() throws JRException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        AnimeService animeService = (AnimeService) ctx.getBean("animeService");

        AnimeDao animeDao = new AnimeDao();
        ArrayList<AnimeReportBean> animeReport = animeDao.getAnimeReportList(animeService);

        long startTime = System.nanoTime();
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(animeReport);
        Map<String,Object> parameters = new HashMap<String, Object>();
        parameters.put("DATE", new Date());

        //File reportPattern = new File("/opt/anim.jrxml");
        //JasperDesign jasperDesign = JRXmlLoader.load(reportPattern);
        //JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        //plugin
        //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
        JasperPrint jasperPrint = JasperFillManager.fillReport("reports/anim.jasper", parameters, beanCollectionDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "/opt/Result.pdf");
        long estTime = System.nanoTime() - startTime;
        System.out.println(estTime / 1e+9);
    }

    public static void main(String[] args) {
        System.out.println("Начало генерации отчёта");
        try {
            new Generator().create();
            System.out.println("Генерация отчёта завершена");
        } catch (Exception e) {
            System.out.println("Во время генерации возникла ошибка: " + e);
        }
    }

}
