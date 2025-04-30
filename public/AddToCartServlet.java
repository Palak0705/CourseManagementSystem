import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.JSONObject;

public class AddToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read JSON data from the request
        BufferedReader reader = request.getReader();
        StringBuilder jsonBuffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuffer.append(line);
        }
        reader.close();

        // Convert JSON string to object
        JSONObject json = new JSONObject(jsonBuffer.toString());

        // Extract course details
        String title = json.getString("title");
        double price = json.getDouble("price");
        String instructor = json.getString("instructor");
        String image = json.getString("image");

        // Store course in session (for future database storage)
        HttpSession session = request.getSession();
        session.setAttribute("cart_" + title, json.toString());

        // Send response
        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"Course added to cart successfully\"}");
    }
}
