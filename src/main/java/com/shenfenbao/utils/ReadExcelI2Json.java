package com.shenfenbao.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSONArray;
import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSONObject;

public class ReadExcelI2Json {


    public static void main(String[] args) {
        String srcF="C:/upload/basefund.xlsx";
        String dstF="C:/upload/BasicFace/BasicFaceData-";
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
        String dateStr = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时
        dstF +=dateStr +".json";
        excel2Json(srcF,dstF);
        System.out.println(dstF);
    }

    public static void excel2Json(String srcFileName,String dstFileName)  {
        try {
            JSONArray array = new JSONArray();
            File excelFile = new File(srcFileName);//excel目录 "C:/Users/lizhenhong/Desktop/数据统计/基础人像库数据范例.xlsx"); // 创建文件对象

            FileInputStream is;
            is = new FileInputStream(excelFile);

            Workbook workbook = WorkbookFactory.create(is); // 这种方式 Excel
            // 2003/2007/2010
            // 都是可以处理的
            int sheetCount = workbook.getNumberOfSheets();
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);
            Map<Integer, String> map = new HashMap<Integer, String>();
            // 获取第一行数据
            int i = 0;
            Cell cell = row.getCell(i);
            while (cell != null) {
                map.put(i, cell.getStringCellValue().toString());
                i++;
                cell = row.getCell(i);
            }
            // 获取其他行数据
            int rowValue = 1;
            Row row2 = sheet.getRow(rowValue);
            while (row2 != null) {
                JSONObject json = new JSONObject(new LinkedHashMap());
                for (int j = 0; j < 50; j++) {
                    Cell cell2 = row2.getCell(j);
                    if (!StringUtils.isEmpty(map.get(j))) {
                        if (cell2 == null) {
                            json.put(map.get(j), "");
                        } else {
                            // System.out.println(
                            // UUID.randomUUID().toString().toLowerCase());
                            switch (map.get(j).toString()) {
                                case "face_image_id":
                                    String faceId= UUID.randomUUID().toString();
                                    json.put(map.get(j), faceId);
                                    break;
                                case "timestamp":
                                    int time = (int) (cell2.getDateCellValue()
                                            .getTime() / 1000);
                                    json.put(map.get(j), time);
                                    break;
                                case "gender":
                                    double gender=cell2.getNumericCellValue();
                                    json.put(map.get(j), gender);
                                    break;
                                case "st_address_gps":
                                    String stGps=cell2.getStringCellValue();
                                    json.put(map.get(j), StringToList(stGps));
                                    break;

                                default:
                                    cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
                                    json.put(map.get(j), cell2.getStringCellValue());
                                    break;
                            }
							 /* if("timestamp".equals(map.get(j))){ int time =
							  (int) (cell2.getDateCellValue().getTime()/1000);
							  json.put(map.get(j), time); } else{
							  if("Gender".equals(map.get(j))){ double
							  gender=cell2.getNumericCellValue();
							  json.put(map.get(j), gender); } else{
							  cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
							  json.put(map.get(j), cell2.getStringCellValue());
							  } }*/


                        }

                    }
                }

                rowValue++;
                row2 = sheet.getRow(rowValue);
                array.add(json);
            }
            // 写入json 文件


//             File f=new File("C:/Users/lizhenhong/Desktop/新建文件夹/"+date+".txt");

            BufferedWriter bw = new BufferedWriter(new FileWriter(dstFileName));
            // 文件将会创建在程序所在的文件夹中，

            String a = array.toString();

            bw.write(a);
            // 关闭
            bw.close();
            //这是个临时测试用例
            //getFileMD5(new File("C:/Users/lizhenhong/Desktop/新建文件夹/4399.png"));
            is.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * String转换List
     *
     * @param listText
     *            :需要转换的文本
     * @return List<?>
     */
    public static List<Object> StringToList(String listText) {

        if (listText == null || listText.equals("")) {
            return null;
        }
        listText = listText.substring(1,listText.length()-1);

        List<Object> list = new ArrayList<Object>();
        String[] text = listText.split("\\,");
        for (String str : text) {
            if (!str.equals("")) {
                list.add(Double.parseDouble(str));
            }
        }
        return list;
    }
}
