package wasdev.sample.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.ibm.watson.developer_cloud.util.CredentialUtils;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
// VisualClassification (Watson Developer Cloud Java SDK 3.3.0 API)
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;


/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {

	//private VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2015_12_02);	
	private VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);	
	//service.setAPIKey("3f2699100532dc8c53159c0fc638fed38a93134e");

	@Override
	public void init() throws ServletException {
		super.init();
		String vcap = System.getProperty("VCAP_SERVICES");
		if (vcap == null){
			try {
				vcap = FileUtils.readFileToString(new File("D:\\ProjectWorkSpace\\BluemixTTS\\VisualRecogLivertyJavaApp\\vcap.txt"));
    	 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		CredentialUtils.setServices(vcap);
		service.setApiKey(CredentialUtils.getAPIKey("Visual_Recognition"));
	}
 
	public static void main(String[] args) throws ServletException{

		SimpleServlet ss = new SimpleServlet();
		ss.init();
		
	    //service.setApiKey("");
		// Classify an image
		System.out.println("Classify an image");
		ClassifyImagesOptions options = new ClassifyImagesOptions.Builder().images(new File("D:\\ProjectWorkSpace\\BluemixTTS\\VisualRecogLivertyJavaApp\\5.jpg")).build();
		
		VisualClassification result = ss.service.classify(options).execute();
		
		
		System.out.println(result);
		/*
		// Detect faces
	    VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_19);
	    service.setApiKey("");

	    System.out.println("Detect faces");
	    detectFaces = new detectFaces.Builder()
	        .images(new File("src/test/resources/visual_recognition/car.png"))
	        .build();
	    detectFaces result = service.classify(options).execute();
	    System.out.println(result);
	    */		
	}

	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().print("Hello World!");
    }
    /*
    public ServiceCall createClassifier(CreateClassifierOptions options) {
        Validator.notNull(options, " options cannot be null");

        Builder bodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        bodyBuilder.addFormDataPart(PARAM_NAME, options.classifierName());

        // Classes
        for (String className : options.classNames()) {
          String dataName = className + "_" + PARAM_POSITIVE_EXAMPLES;
          RequestBody requestBody =
              RequestBody.create(HttpMediaType.BINARY_FILE, options.positiveExamplesByClassName(className));
          bodyBuilder.addFormDataPart(dataName, options.positiveExamplesByClassName(className).getName(), requestBody);
        }

        if (options.negativeExamples() != null) {
          RequestBody requestBody = RequestBody.create(HttpMediaType.BINARY_FILE, options.negativeExamples());
          bodyBuilder.addFormDataPart(PARAM_NEGATIVE_EXAMPLES, options.negativeExamples().getName(), requestBody);
        }

        RequestBuilder requestBuilder = RequestBuilder.post(PATH_CLASSIFIERS);
        requestBuilder.query(VERSION, versionDate).body(bodyBuilder.build());

        return createServiceCall(requestBuilder.build(), ResponseConverterUtils.getObject(VisualClassifier.class));
    }  
    
    public ServiceCall getClassifier(String classifierId) {
        Validator.isTrue(classifierId != null && !classifierId.isEmpty(), "classifierId cannot be null or empty");

        RequestBuilder requestBuilder = RequestBuilder.get(String.format(PATH_CLASSIFIER, classifierId));
        requestBuilder.query(VERSION, versionDate);
        return createServiceCall(requestBuilder.build(), ResponseConverterUtils.getObject(VisualClassifier.class));
    }    
    
    public ServiceCall> getClassifiers() {
        RequestBuilder requestBuilder =
            RequestBuilder.get(PATH_CLASSIFIERS).query(VERSION, versionDate).query(VERBOSE, true);

        ResponseConverter> converter =
            ResponseConverterUtils.getGenericObject(TYPE_LIST_CLASSIFIERS, PARAM_CLASSIFIERS);
        return createServiceCall(requestBuilder.build(), converter);
    }   
    
    public ServiceCall<Void> deleteClassifier(String classifierId) {
        Validator.isTrue(classifierId != null && !classifierId.isEmpty(), "classifierId cannot be null or empty");

        RequestBuilder requestBuilder = RequestBuilder.delete(String.format(PATH_CLASSIFIER, classifierId));
        requestBuilder.query(VERSION, versionDate);
        return createServiceCall(requestBuilder.build(), ResponseConverterUtils.getVoid());
    }
    */    
}
