package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FilesTest {
    ClassLoader cl = FilesTest.class.getClassLoader();

    @Test
    @DisplayName("Проверка записей в CSV файле в архиве")
    void csvInZipTest() throws Exception {
        try (ZipInputStream zis = openZipStream()) {
            Assertions.assertTrue(isFileInZip("csvtest.csv",zis));
            Reader reader = new InputStreamReader(zis);
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> content = csvReader.readAll();
            Assertions.assertEquals(3, content.size());

            final String[] firstRow = content.get(0);
            final String[] secondRow = content.get(1);
            final String[] thirdRow = content.get(2);

            Assertions.assertArrayEquals(new String[] {"Language","Framework"}, firstRow);
            Assertions.assertArrayEquals(new String[] {"Java","Selenide"}, secondRow);
            Assertions.assertArrayEquals(new String[] {"Python","Selene"}, thirdRow);

        }

    }

    @Test
    @DisplayName("Проверка XLSX в архиве")
    void xlsxInZipTest() throws Exception {
        try (ZipInputStream zis = openZipStream()) {
            Assertions.assertTrue(isFileInZip("xlsxtest.xlsx", zis));
            XLS xls = new XLS(zis);
            Assertions.assertEquals("$500",xls.excel
                    .getSheetAt(0).getRow(1)
                    .getCell(1).getStringCellValue());
        }
    }

    @Test
    @DisplayName("Проверка PDF в архиве")
    void pdfInZipTest() throws Exception {
        try (ZipInputStream zis = openZipStream()) {
            Assertions.assertTrue(isFileInZip("pdftest.pdf", zis));
            PDF pdf = new PDF(zis);
            Assertions.assertTrue(pdf.text.contains("Анисимов Дмитрий Александрович"));
        }
    }

    private ZipInputStream openZipStream() {
        InputStream stream = cl.getResourceAsStream("test.zip");
        return new ZipInputStream(stream);
    }

    private boolean isFileInZip(String fileName, ZipInputStream zipInputStream) throws Exception {
        ZipEntry entry;
        while ((entry = zipInputStream.getNextEntry()) != null) {
            if (entry.getName().equals(fileName)) {
                return true;
            }
        }
        return false;
    }

}

