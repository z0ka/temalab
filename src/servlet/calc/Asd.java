package servlet.calc;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import hu.vkrissz.bme.raytracer.RayTracer;
import hu.vkrissz.bme.raytracer.core.C;
import hu.vkrissz.bme.raytracer.model.RenderPart;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/asd")
public class Asd {

    @POST
    @Path("/imagePart")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.MULTIPART_FORM_DATA)
    public C[][] preprocess(String inputJsonObj) {

        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(inputJsonObj).getAsJsonObject();

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            Math.sqrt(Math.cos(Math.random()));
        }

//        return o.toString();
        C[][] canvas = RayTracer.render(inputJsonObj);
//        RayTracer.compose(1920, 1080, )
        return canvas;

    }


}
