/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DTO.Bill;
import com.mycompany.projectjava.DAO.BillDAO;
import com.mycompany.projectjava.DAO.Currency;
import com.mycompany.projectjava.DAO.FormatDate;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author hoang
 */
public class ChartBLL {
    public static DefaultCategoryDataset createDataset(String from, String to, String fromOriginal, String toOriginal, List<Bill> listBill) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //Nếu trùng ngày thì sẽ lấy thống kê theo time
        if (Long.parseLong(from) == Long.parseLong(to)) {
            dataset.addValue(0, "REVENUE", "...");
            for (int i = 0; i < listBill.size(); i++) {
                if (Long.parseLong(FormatDate.returnDateFromDB(listBill.get(i).getDateCreate())) == Long.parseLong(from)) {
                    dataset.addValue(BillDAO.getPrice(listBill.get(i).getId()), "REVENUE", listBill.get(i).getDateCreate());
                }
            }
        } else {
            List<Bill> listByDay = new ArrayList<>();   //Add bill theo khoảng thời gian vào list "listByDay"
            for (int i = 0; i < listBill.size(); i++) {
                if (Long.parseLong(FormatDate.returnDateFromDB(listBill.get(i).getDateCreate())) >= Long.parseLong(from)
                        && Long.parseLong(FormatDate.returnDateFromDB(listBill.get(i).getDateCreate())) <= Long.parseLong(to)) {
                    listByDay.add(listBill.get(i));
                }
            }

            dataset.addValue(0, "REVENUE", ""); // Thêm giá trị đầu với revenue = 0
            //System.out.println(fromOriginal);
            //System.out.println("Day: " + FormatDate.getDay(fromOriginal));

            // Lay gia tri ngay bat dau
            String ddTo = FormatDate.getDay(toOriginal);
            String MMTo = FormatDate.getMonth(toOriginal);
            String yyyyTo = FormatDate.getYear(toOriginal);
            String Todt = FormatDate.dateString(ddTo, MMTo, yyyyTo);

            // Lay gia tri ngay cuoi cung
            String dd = FormatDate.getDay(fromOriginal);
            String MM = FormatDate.getMonth(fromOriginal);
            String yyyy = FormatDate.getYear(fromOriginal);
            String Fromdt = FormatDate.dateString(dd, MM, yyyy);

            //Dem tong so ngay trong khoang thoi gian
            int countday = 0;
            String ddCount = dd;
            String MMCount = MM;
            String yyyyCount = yyyy;

            String countDate = Fromdt;
            while (!Todt.equals(countDate)) {
                countday++;
                int dayOmonth = FormatDate.dayOfMonth(MMCount, yyyyCount);
                String temp = "0";
                if (( Integer.parseInt(ddCount) + 1) <= dayOmonth) {
                    if ((Integer.parseInt(ddCount) + 1) < 10) {
                        ddCount = temp.concat(String.valueOf(Integer.parseInt(ddCount) + 1));
                    }else{
                        ddCount = String.valueOf(Integer.parseInt(ddCount) + 1);
                    }
                } else {
                    
                    if ((Integer.parseInt(MMCount) + 1) <= 12) {
                        if ((Integer.parseInt(MMCount) + 1) < 10) {
                            MMCount = temp.concat(String.valueOf(Integer.parseInt(MMCount) + 1));
                            ddCount = "01";
                            dayOmonth = FormatDate.dayOfMonth(MMCount, yyyyCount);
                        } else{
                            MMCount = String.valueOf(Integer.parseInt(MMCount) + 1);
                            ddCount = "01";
                            dayOmonth = FormatDate.dayOfMonth(MMCount, yyyyCount);
                        }
                    } else {
                        yyyyCount = String.valueOf(Integer.parseInt(yyyyCount) + 1);
                        ddCount = "01";
                        MMCount = "01";
                        dayOmonth = FormatDate.dayOfMonth(MMCount, yyyyCount);
                    }
                }
                countDate = FormatDate.dateString(ddCount, MMCount, yyyyCount);
                //System.out.println(countday);

            }
            countday++; // Cong them 1 (Lấy cả ngày cuối)
            int dayOmonth = FormatDate.dayOfMonth(MM, yyyy);
            //System.out.println(dayOmonth);
            boolean check = false;
            for (long i = 0; i < countday; i++) {
                if (check) {
                    if ( (Integer.parseInt(dd) + 1) <= dayOmonth) {
                        if ((Integer.parseInt(dd) + 1) < 10) {
                           dd = "0".concat(String.valueOf(Integer.parseInt(dd) + 1));
                        }else{
                            dd = String.valueOf(Integer.parseInt(dd) + 1);
                        }
                    } else {
                        if ((Integer.parseInt(MM) + 1) <= 12) {
                            if ((Integer.parseInt(MM) + 1) < 10) {
                                MM = "0".concat(String.valueOf(Integer.parseInt(MM) + 1));
                                dd = "01";
                                dayOmonth = FormatDate.dayOfMonth(MM, yyyy);
                            } else{
                                MM = String.valueOf(Integer.parseInt(MM) + 1);
                                dd = "01";
                                dayOmonth = FormatDate.dayOfMonth(MM, yyyy);
                            }
                        } else {
                            yyyy = String.valueOf(Integer.parseInt(yyyy) + 1);
                            dd = "01";
                            MM = "01";
                            dayOmonth = FormatDate.dayOfMonth(MM, yyyy);
                        }
                    }
                }
                String thisday = String.valueOf(dd).concat("/").concat(String.valueOf(MM)).concat("/").concat(String.valueOf(yyyy));
                System.out.println(thisday);
                double total = 0.0;
                for (int j = 0; j < listByDay.size(); j++) {
                    String day = FormatDate.getDayFromDB(listByDay.get(j).getDateCreate());
                    String month = FormatDate.getMonthFromDB(listByDay.get(j).getDateCreate());
                    String year = FormatDate.getYearFromDB(listByDay.get(j).getDateCreate());
                    String toCheckDate = String.valueOf(day).concat("/").concat(String.valueOf(month)).concat("/").concat(String.valueOf(year));
                    if (thisday.equals(toCheckDate)) {
                        total += BillDAO.getPrice(listByDay.get(j).getId());
                    }
                }
                dataset.addValue(total, "REVENUE", thisday);
                check = true;
            }
        }

        return dataset;
    }

    public static String loadSold(List<Bill> SearchListBill) {
        double revenue = 0;
        for (int i = 0; i < SearchListBill.size(); i++) {
            revenue += BillDAO.getPrice(SearchListBill.get(i).getId());
        }
        NumberFormat currency = Currency.toCurrency();
        return currency.format(revenue);
    }
    
    public static String loadBills(List<Bill> SearchListBill) {
        return String.valueOf(SearchListBill.size());
    }
}
