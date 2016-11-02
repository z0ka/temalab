package servlet.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import hu.vkrissz.bme.raytracer.RayTracer;
import hu.vkrissz.bme.raytracer.core.C;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Path("/pre")
public class PreProcessorServlet {


    @POST
    @Path("/image")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String preprocess(String inputJsonObj) {
        HttpURLConnection connection = null;
        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(inputJsonObj).getAsJsonObject();

        //vk magic
        C[][] canvas = RayTracer.render(inputJsonObj);


        //http kérés a képrészekre    servlet.calc.Asd hívása http-n
        try {

            URL url = new URL("/asd/imagePart");
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                DataOutputStream wr = new DataOutputStream(
                        connection.getOutputStream());
                wr.writeBytes("maaagic");
                wr.close();
            }
            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();


        } catch (
                ProtocolException e
                )

        {
            e.printStackTrace();
        } catch (
                MalformedURLException e
                )

        {
            e.printStackTrace();
        } catch (
                IOException e
                )

        {
            e.printStackTrace();
        }

        //vk magic összerakás





        return o.toString();
    }

    @GET
    @Path("/fgh")
    @Produces(MediaType.TEXT_PLAIN)
    public String preprocess() {

        return "Done";
    }

//    @POST
//    @Path("/doCalc/{token}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public String calcIso(@PathParam("token") String token, JsonObject inputJsonObj) throws NoSuchAlgorithmException {
//    return "aw";}
}
