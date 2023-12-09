package ru.sberbank.edu;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Financial servlet
 */
public class FinancialServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/finance.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        double startSum, rate, years;

        try {
            startSum = Double.parseDouble(req.getParameter("startSum"));
            rate = Double.parseDouble(req.getParameter("rate"));
            years = Double.parseDouble(req.getParameter("years"));
        }catch (NumberFormatException | NullPointerException e){
            responseError(req, resp, "Ошибка", new String[]{"Неверный формат данных", "Скорректируйте значения"});
            return;
        }

        if(startSum <= 0|| rate <= 0 || years <= 0){
            responseError(req, resp, "Ошибка данных", new String[]{"Неверный формат данных", "Скорректируйте значения"});
            return;
        }

        if ( startSum < 50000d ){
            responseError(req, resp, "Ошибка", new String[]{"Минимальная сумма на момент", " открытия вклада 50_000 рублей"});
            return;
        }

        Double result = startSum * Math.pow(1 + rate / 100, years);

        req.setAttribute("result", String.format("%.2f", result) );
        getServletContext().getRequestDispatcher("/result.jsp").forward(req, resp);
    }


    private void responseError(HttpServletRequest req, HttpServletResponse resp, String title , String[] messages) throws ServletException, IOException {
        req.setAttribute("title", title);
        req.setAttribute("messages", messages);

        getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
    }
}
